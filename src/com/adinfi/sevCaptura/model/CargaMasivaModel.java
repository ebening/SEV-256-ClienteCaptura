package com.adinfi.sevCaptura.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import com.adinfi.sevCaptura.dao.CargaMasivaDAO;
import com.adinfi.sevCaptura.dao.CargaMasivaDAOInterface;
import com.adinfi.sevCaptura.entities.Aprobacion;
import com.adinfi.sevCaptura.entities.Atributo;
import com.adinfi.sevCaptura.entities.Documento;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Notificacion;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.ibm.mm.sdk.common.DKException;

public class CargaMasivaModel extends SwingWorker<Boolean, Integer> {
	public static Logger logger = Logger.getLogger(CargaMasivaModel.class);
	private Mensaje message;
	private CargaMasivaDAOInterface DAO = null;
	private JTextField txtDocsLote = null;
	private JTextField txtDocsAProcesar = null;
	private JTextField txtDocsImportados = null;
	private JTextField txtDocsNoImportados = null;
	public JButton btnAceptarCM = null;
	private JProgressBar progressBar = null;
	private JInternalFrame jFrameMensajes = null;
	private String lote = null;
	private ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
	private ArrayList<Factura> listaSoporte = new ArrayList<Factura>();
	private String titulo = "Sistema de Captura";
	private int numFacturas = 0;
	private String idUsuario;
	private int factListas = 0;
	private int docsImportados = 0;
	private int docsNomimportados = 0;
	private int procesados = 0;
	private boolean banderaCargaMasiva=true;

	public void setBanderaCargaMasiva(boolean banderaCargaMasiva) {
		this.banderaCargaMasiva = banderaCargaMasiva;
	}

	public void setLote(String lote) {
		this.lote = lote;

	}

	public CargaMasivaModel() {
		this.DAO = new CargaMasivaDAO();

	}

	public String getLote() {
		return lote;
	}

	public JButton getBtnAceptarStatusCM() {
		if (btnAceptarCM == null) {
			btnAceptarCM = new JButton("Aceptar");
		}
		return btnAceptarCM;
	}

	public JTextField getTxtDocsLote() {
		if (txtDocsLote == null) {
			txtDocsLote = new JTextField();
		}
		return txtDocsLote;
	}

	public JTextField getTxtDocsAProcesar() {
		if (txtDocsAProcesar == null) {
			txtDocsAProcesar = new JTextField();
		}
		return txtDocsAProcesar;
	}

	public JTextField getTxtDocsNoImportados() {
		if (txtDocsNoImportados == null) {
			txtDocsNoImportados = new JTextField();
		}
		return txtDocsNoImportados;
	}

	public JTextField getTxtDocsAImportados() {
		if (txtDocsImportados == null) {
			txtDocsImportados = new JTextField();
		}
		return txtDocsImportados;
	}

	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar(0, 100);
		}
		return progressBar;
	}

	public boolean establecerConexionDB2() {
		boolean conexionAbierta = false;
		try {

			conexionAbierta = DAO.establecerConexionDB2();

		} catch (SQLException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);

		} catch (InstantiationException e) {

			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
		} catch (IllegalAccessException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);

		} catch (ClassNotFoundException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
		}

		return conexionAbierta;

	}

	public void cerrarConexionDB2() {
		try {

			DAO.cerrarConexionDB2();

		} catch (SQLException e) {
			logger.error("Error al intentar cerrar conexion con DB2", e);
		}
	}

	private boolean establecerConexionCM() {
		boolean conexionvalida = false;
		try {

			conexionvalida = DAO.establecerConexionCM();

		} catch (DKException e) {
			logger.error("Error al intentar conectar a CM", e);

		} catch (Exception e) {
			logger.error("Error al intentar conectar a CM", e);

		}
		return conexionvalida;
	}

	private void cerrarConecionCM() {
		try {

			DAO.cerrarConexionCM();

		} catch (DKException e) {
			logger.error("Error al intentar cerrar conexion con CM", e);

		} catch (Exception e) {
			logger.error("Error al intentar cerrar conexion con CM", e);
		}
	}

	public boolean muestraFacturasPorLote(String idUsuario) {

		boolean exito = false;
		String idUser = idUsuario;
		this.idUsuario = idUsuario;

		if (this.establecerConexionDB2()) {
			try {

				int numFacXlote = DAO.consultaAllFacturasPorlote(this.lote, idUser);

				if (numFacXlote != 0) {
					this.txtDocsLote.setText("" + numFacXlote);
					factListas = DAO.consultaFacturasPorloteSinEnviarACM(lote, idUser);// captu
					this.txtDocsAProcesar.setText("" + factListas);
					docsImportados = 0;// DAO.consultaFactEnviadasACM(lote,idUser);
					this.txtDocsImportados.setText(docsImportados + "");
					docsNomimportados = factListas - docsImportados;
					this.txtDocsNoImportados.setText(docsNomimportados + "");
					this.numFacturas = factListas;

					exito = true;
				}
			} catch (SQLException e) {
				logger.error("Error al Mostrar datos de carga Masiva", e);
			}

			this.cerrarConexionDB2();
		}

		return exito;

	}

	@Override
	protected Boolean doInBackground() throws Exception {
		logger.info("Iniciando carga Masiva");
		
		boolean procesoExito = false;
		this.progressBar.setValue(0);
		if (this.numFacturas > 0) {
			if (this.establecerConexionDB2()) {// seleccionar * fact
				try {
					listaFacturas = DAO.getFacturasLoteUsuario(this.lote, idUsuario);
					// Establecer conexion a CM
					if (this.establecerConexionCM()) {
						logger.info("Extraiendo atributos Factura-gastos");
						Documento plantillaFactura = new Documento();
						plantillaFactura.setNombre(Constants.ITEM_TYPE_FACTURA);
						plantillaFactura.setAtributos(DAO.getAtributosListByItemType(Constants.ITEM_TYPE_FACTURA));

						logger.info("Extraiendo atributos Factura-Soporte");
						Documento plantillaSoporte = new Documento();
						plantillaSoporte.setNombre(Constants.ITEM_TYPE_SOPORTE);
						plantillaSoporte.setAtributos(DAO.getAtributosListByItemType(Constants.ITEM_TYPE_SOPORTE));

						logger.info("Iterando atributos Factura-Gastos");
						for (int i = 0; i < listaFacturas.size(); i++) {
							Factura factura = listaFacturas.get(i);

							Documento doc = plantillaFactura.clone();
							doc.setUrl(factura.getUrl());

							ArrayList<Atributo> atributos = doc.getAtributos();

							for (Atributo atributo : atributos) {
								String nombre = atributo.getName();

								if (nombre.equals(Constants.ATTR_NO_FACTURA)) {
									atributo.setValor(factura.getNoFact());
									logger.info("NO Factura " + factura.getNoFact());
								} else if (nombre.equals(Constants.ATTR_NO_PROVEEDOR)) {
									atributo.setValor(factura.getProveedor().getNumero());
									logger.info("NO proveedor " + factura.getProveedor().getNumero());
								} else if (nombre.equals(Constants.ATTR_AREA)) {
									atributo.setValor(factura.getArea().getNombre());
									logger.info("Area " + factura.getArea().getId());
								} else if (nombre.equals(Constants.ATTR_CONCEPTO_FACTURA)) {
									atributo.setValor(factura.getConcepto().getDescripcion());
									logger.info("Concepto " + factura.getConcepto().getDescripcion());
								} else if (nombre.equals(Constants.ATTR_EMPRESA)) {
									atributo.setValor(factura.getEmpresa().getNombre());
									logger.info("Empresa " + factura.getEmpresa().getNombre());
								} else if (nombre.equals(Constants.ATTR_IDUSUARIO)) {
									atributo.setValor(factura.getUsuario().getIdUsuario());
									logger.info("Usuario " + factura.getUsuario().getIdUsuario());
								} else if (nombre.equals(Constants.ATTR_MONEDA_FACTURA)) {
									atributo.setValor(factura.getModeda());
									logger.info("Moneda " + factura.getModeda());
								} else if (nombre.equals(Constants.ATTR_MONTO_FACTURA)) {
									atributo.setValor(factura.getMonto());
									logger.info("Monto " + factura.getMonto());
								} else if (nombre.equals(Constants.ATTR_NOM_PROVEEDOR)) {
									atributo.setValor(factura.getProveedor().getNombre());
									logger.info("Nombre Proveedor " + factura.getProveedor().getNombre());
								} else if (nombre.equals(Constants.ATTR_RFC_PROVEEDOR)) {
									atributo.setValor(factura.getProveedor().getRfc());
									logger.info("Rfc Proveedor " + factura.getProveedor().getRfc());
								} else if (nombre.equals(Constants.ATTR_SUC_PROVEEDOR)) {
									atributo.setValor(factura.getProveedor().getSucursal());
									logger.info("Sucursal Proveedor " + factura.getProveedor().getSucursal());
								} else if (nombre.equals(Constants.ATTR_PLAZA)) {
									atributo.setValor(factura.getPlaza());
									logger.info("Plaza" + factura.getPlaza());
								} else if (nombre.equals(Constants.ATTR_ORDEN_COMPRA)) {
									atributo.setValor(factura.getOrdenCompra());
									logger.info("Orden COmpra " + factura.getOrdenCompra());
								} else if (nombre.equals(Constants.ATTR_NOSAF)) {
									atributo.setValor(factura.getNoSAF());
									logger.info("No SAF " + factura.getNoSAF());
								} else if (nombre.equals(Constants.ATTR_MONEDA_FACTURA)) {
									atributo.setValor(factura.getModeda());
									logger.info("Moneda " + factura.getModeda());
								} else if (nombre.equals(Constants.ATTR_FECHAFACTURA)) {
									atributo.setValor(factura.getFechaFactura().toString());
									logger.info("Fecha Factura " + factura.getFechaFactura().toString());
								} else if (nombre.equals(Constants.ATTR_FECHARECEPCION)) {
									atributo.setValor(factura.getFechaRecepcion().toString());
									logger.info("Fecha Factura " + factura.getFechaRecepcion().toString());
								}
							}

							boolean insertoFacturaGastos = false;
							int idFactGastos = 0;

							/* insertar fact en CM */
							logger.info("Importando  Factura.. ");
							if (DAO.importaDocumento(doc)) {
								logger.info("Se ha importado la factura  " + factura.getId());
								/*actualizar el reporteCarga*/
								actualizarDocumentosProcesado(factura);
								
								docsImportados++; // DAO.consultaFactEnviadasACM(lote,idUser);
								this.txtDocsImportados.setText(docsImportados + "");
								docsNomimportados = factListas - docsImportados;
								this.txtDocsNoImportados.setText(docsNomimportados + "");

								factura.setPID(DAO.getNewPid());
								
								/*
								 * TODO
								 * Modificar POJO para conteneer fecha inicioa a VOBO.
								 * Agregar a registro de factura la fecha inicio de vobo
								 * segun la fecha actual.
								 */
								factura.setFechaInicioVoBo(new Date());
								logger.info("Insertando factura de Factura-Gastos " + factura.getId());

								idFactGastos = DAO.insertarFacturaEnFacturaGastos(factura);

								if (idFactGastos != 0) {
									insertoFacturaGastos = true;
									logger.info("Se inserto factura en Factura-Gastos");
									listaFacturas.get(i).setAccion(Constants.STATUS_DES_IMPORTADO_A_CM);
									int status = Constants.STATUS_IMPORTADO_A_CM;
									int idMovimiento = DAO.insertarBitacoraFactura(factura, status);
									String idDestinatario = factura.getDestinatario().getIdUsuario();

									if (DAO.insertarStatusFactura(idFactGastos, idMovimiento, status, idDestinatario)) {
										logger.info("se actualizo el status de la nueva factura");
									}

									/*Insertar en Notificacion*/
									String idUsuarioOrigen = factura.getUsuario().getIdUsuario();
									String idUsuarioDestino = factura.getDestinatario().getIdUsuario();
									String fecha = Utilities.nowTimeStamp();
									Timestamp timesTamp = null;
									timesTamp = Utilities.parseSqlTimeStamp(fecha);
									
									Notificacion notificacion = new Notificacion();
									notificacion.setIdfactura(idFactGastos);
									notificacion.setIdEstado(status);
									notificacion.setIdUsuarioOrigen(idUsuarioOrigen);
									notificacion.setIdUsuarioDestino(idUsuarioDestino);
									notificacion.setAutomatica(false);
									notificacion.setEscalamiento(false);
									notificacion.setFecha(timesTamp);
									notificacion.setDias(0);

									if (DAO.insertarNotificacion(notificacion)) {
										logger.info("se ha agregado a Notificaciones la factura " + idFactGastos);
									}else{
										logger.info("Ha ocurrido un error al insetar la factura " + idFactGastos+"En Notificaciones");
									}
									
									/*Insertar en Aprobación*/
									Aprobacion aprobacion= new Aprobacion();
									aprobacion.setIdFactura(idFactGastos);
									aprobacion.setFecha(timesTamp);
									aprobacion.setIdUsuarioOrigen(idUsuarioOrigen);
									aprobacion.setIdUsuarioDestino(idUsuarioDestino);
									
									if (DAO.insertNewAprobacion(aprobacion)) {
										logger.info("se ha agregado al Historial_aprobaciones  la factura " + idFactGastos);
									}else{
										logger.error("Ha ocurrido un error al insetar la factura " + idFactGastos+"En Historial_aprobaciones");
									}
									
									
								}
								logger.info("Borrando de facturaCaptura");
								if (insertoFacturaGastos) {
									if (DAO.borrarStatusCaptura(factura.getId())) {
										if (DAO.borrarFacturaCaptura(factura.getId())) {
											logger.info("Se ha borrado la Factura de la tabla de captura." + factura.getId());
										}
									}
								}
								
								
									/*Documentos Soporte*/
								String noFactura = listaFacturas.get(i).getNoFact();
								String noProveedor = listaFacturas.get(i).getProveedor().getNumero();
								logger.info("Consultando si la factura tiene Soportes..");
								listaSoporte = DAO.getFacturasSoporteLoteUsuario(this.lote, this.idUsuario, noFactura, noProveedor);
								if (listaSoporte.size() > 0) {
									logger.info("Iterando soportes..");
									for (int j = 0; j < listaSoporte.size(); j++) {
										Factura factSoporte = listaSoporte.get(j);
										Documento docSop = plantillaSoporte.clone();
										docSop.setUrl(factSoporte.getUrl());
										ArrayList<Atributo> atributosFacSop = docSop.getAtributos();
										logger.info("Iterando atributos soporte..");
										for (Atributo atributo : atributosFacSop) {
											String nombre = atributo.getName();
											if (nombre.equals(Constants.ATTR_NO_FACTURA)) {
												atributo.setValor(factSoporte.getNoFact());
												logger.info("Numero de factura" + factSoporte.getNoFact());
											} else if (nombre.equals(Constants.ATTR_NO_PROVEEDOR)) {
												atributo.setValor(factSoporte.getProveedor().getNumero());
												logger.info("NO proveedor" + factSoporte.getProveedor().getNumero());
											} else if (nombre.equals(Constants.ATTR_TIPO_DOC)) {
												atributo.setValor(factSoporte.getNombreSoporte());
												logger.info("Nombre soporte" + factSoporte.getNombreSoporte());
											}
										}
										boolean insertoAdjunto = false;
										logger.info("Importando Soporte. ");
										if (DAO.importaDocumento(docSop)) {
											logger.info("Se ha importado el soporte. " + factSoporte.getId());
											docsImportados++;
											this.txtDocsImportados.setText(docsImportados + "");
											docsNomimportados = factListas - docsImportados;
											this.txtDocsNoImportados.setText(docsNomimportados + "");
											factSoporte.setPID(DAO.getNewPid());
											logger.info("Insertando en tabla Adjuntos.. ");
											int idAdjunto = DAO.insertarSoporteaAdjuntos(factSoporte);
											if (idAdjunto > 0) {
												logger.info("se inserto el soporte con el idAdjunto." + idAdjunto);
												DAO.insertarFacturaAdjuntos(idFactGastos, idAdjunto);
												insertoAdjunto = true;
												logger.info("se inseto el soporte con en Factura-adjuntos." + idAdjunto);
											}
											if (insertoAdjunto) {
												logger.info("borrando de Factura-captura " + idAdjunto);
												if (DAO.borrarStatusCaptura(factSoporte.getId())) {
													if (DAO.borrarFacturaCaptura(factSoporte.getId())) {
														logger.info("se ha borrando el soporte " + factSoporte.getId());
													}
												}
											}
										} else {
											int status = Constants.STATUS_ERROR_IMPORTADO;
											int actualizo = DAO.actualizaStatus(factSoporte, status);
											if (actualizo > 0) {
												factSoporte.getEstado().setId(Constants.STATUS_ERROR_IMPORTADO);
											}
											logger.error("Ha ocurrido un error al importar El Soporte de la factura " + factura.getId());
											
										}

									}
								}else{
									logger.info("La Factura: "+noFactura+ " No tiene Soportes Relacionados");
								}
								
							} else {
								logger.error("Ha ocurrido un error al importar factura " + factura.getId());
								int status = Constants.STATUS_ERROR_IMPORTADO;
								int actualizo = DAO.actualizaStatus(factura, status);
								if (actualizo > 0) {
									factura.getEstado().setId(Constants.STATUS_ERROR_IMPORTADO);
								}
							}

						
							SwingUtilities.invokeAndWait(new Runnable() {

								int numProce = procesados;
								int avance = ((numProce + 1) * 100) / listaFacturas.size();

								public void run() {
									progressBar.setValue(avance);
								}

							});
							procesados++;
							
						
							
							Thread.sleep(100);
							if (this.listaFacturas.size()==procesados) {
								procesoExito = true;
								SwingUtilities.invokeAndWait(new Runnable() {
									public void run() {
										message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG019"), titulo, 1);
										showMessage(message);
									}

								});

							}

						}
						logger.info("La importacion ha finalizado con exito");
						this.cerrarConecionCM();
					} else {
						SwingUtilities.invokeAndWait(new Runnable() {
							public void run() {
								message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG027"), titulo, 1);
								showMessage(message);
								atualizarFacturasStatus();
							}
						});
						

					}
				} catch (SQLException e) {
					logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
							showMessage(message);
							atualizarFacturasStatus();
						}
					});

					

				} catch (Exception e) {
					logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);

					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
							showMessage(message);
							atualizarFacturasStatus();
						}

					});
				}

				this.cerrarConexionDB2();
			} else {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), titulo, 1);
						showMessage(message);
						atualizarFacturasStatus();
					}

				});
				
			}
		}
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				btnAceptarCM.setEnabled(true);
				atualizarFacturasStatus();
			}

		});		
		return procesoExito;
	}

	/**
	 * actualiza el campo documentosProcesados
	 * de la tabla 	REPORTECARGA
	 * @param procesados2
	 * @throws SQLException 
	 */
	private void actualizarDocumentosProcesado(Factura factura) throws SQLException {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		int numDocActual=0;
		//if(this.establecerConexionDB2()){
			String tipoCaptura=Constants.REPORTECARGA_FACTURA_GASTO;
			numDocActual = DAO.getNumeroDocumentosProcesado(factura,tipoCaptura);
			numDocActual++;
			System.out.println("Doc procesados="+numDocActual);
			if(DAO.actualizaDocumentosprocesados(numDocActual,factura,tipoCaptura)){
				logger.info("Se actualizo el num de Documentos Procesados del lote "
						+factura.getLote()+" ,Usuario "+factura.getUsuario().getIdUsuario()+" y tipo Captura "+tipoCaptura);
			/*}else{
				logger.error("Hubo un error al intentar actualizar en REPORTECARGA  el num de Documentos Procesados del lote "
						+factura.getLote()+" Usuario "+factura.getUsuario().getIdUsuario()+" y tipo Captura "+tipoCaptura);
			}*/
		}logger.error("Hubo un error al intentar actualizar en REPORTECARGA  el num de Documentos Procesados del lote "
				+factura.getLote()+" Usuario "+factura.getUsuario().getIdUsuario()+" y tipo Captura "+tipoCaptura);
		
	}

	public void atualizarFacturasStatus() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		if(this.establecerConexionDB2()){
			try {
				ArrayList<Factura> listaFacturasStatusEnvACM = DAO.consultaAllFacturaStatusStatusCM(this.lote, idUsuario);
				if (listaFacturasStatusEnvACM.size() > 0) {
					for (int i = 0; i < listaFacturasStatusEnvACM.size(); i++) {
						Factura facturaCap = listaFacturasStatusEnvACM.get(i);
						int status = Constants.STATUS_CAPTURA_COMPLETA;
						int actualizo = DAO.actualizaStatus(facturaCap, status);
						if (actualizo > 0) {
							logger.info("Se actualizaron las facturas con el status: " + Constants.STATUS_CAPTURA_COMPLETA);
						}
					}

				}

			} catch (SQLException e) {
				logger.error("Error al intentar traer las facturas para cambiar su status a Capturadas", e);
			
				if (this.validarConexionDB2()) {
					logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG007" + e), titulo, 1);
					this.showMessage(message);
					System.exit(0);
				}

			}

			
		this.cerrarConexionDB2();	
		}
		
		
	}

	/**
	 * Método que valida la conexión con DB2.
	 * 
	 * @return true - Si la conexión es valida.
	 */
	private boolean validarConexionDB2() {
		boolean conexionValida = false;

		try {
			conexionValida = DAO.validaConexionDB2();
		} catch (SQLException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
		}

		return conexionValida;
	}

	/**
	 * Muestra un mensaje JOptionPane.showMessageDialog
	 * 
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje) {
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}

	public boolean getBanderaCargaMasiva() {
		return banderaCargaMasiva;
	}

}
