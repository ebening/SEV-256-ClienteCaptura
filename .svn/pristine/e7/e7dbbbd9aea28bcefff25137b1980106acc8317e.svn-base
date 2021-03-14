package com.adinfi.sevCaptura.model;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import com.adinfi.sevCaptura.dao.CargaMasivaMercanciaDAO;
import com.adinfi.sevCaptura.dao.CargaMasivaMercanciaDAOInterface;
import com.adinfi.sevCaptura.entities.Atributo;
import com.adinfi.sevCaptura.entities.Documento;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.ibm.mm.sdk.common.DKException;

public class CargaMasivaMercanciaModel extends SwingWorker<Boolean, Integer> {
	
	public static Logger logger = Logger.getLogger(CargaMasivaMercanciaModel.class);
	private Mensaje message;
	private  CargaMasivaMercanciaDAOInterface DAO=null;
	private JTextField txtDocsLote=null;
	private JTextField txtDocsAProcesar=null;
	private JTextField txtDocsImportados=null;
	private JTextField txtDocsNoImportados=null;
	public JButton btnAceptarCM=null;
	private JProgressBar progressBar=null;
	private JInternalFrame jFrameMensajes = null;
	private String lote = null;
	private ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
	private int  numFacturas=0;
	private String idUsuario;
	private int factListas=0;
	private int docsImportados=0;
	private int docsNomimportados=0;
	private String titulo = "Sistema de Captura";
	private int procesados=0;
	
	public void setLote(String lote) {
		this.lote=lote;
		
	}
	
	public CargaMasivaMercanciaModel(){
		this.DAO= new CargaMasivaMercanciaDAO();
		
	}
	public String getLote(){
		return lote;
	}
	public JButton getBtnAceptarStatusCM() {
		if (btnAceptarCM == null) {
			btnAceptarCM = new JButton("Aceptar");
		}
		return btnAceptarCM;
	}
	public JTextField getTxtDocsLote(){
		if(txtDocsLote==null){
			txtDocsLote = new JTextField();
		}
		return txtDocsLote;
	}
	
	public JTextField getTxtDocsAProcesar(){
		if(txtDocsAProcesar==null){
			txtDocsAProcesar = new JTextField();
		}
		return txtDocsAProcesar;
	}
	public JTextField getTxtDocsNoImportados(){
		if(txtDocsNoImportados==null){
			txtDocsNoImportados = new JTextField();
		}
		return txtDocsNoImportados;
	}
	public JTextField getTxtDocsAImportados(){
		if(txtDocsImportados==null){
			txtDocsImportados = new JTextField();
		}
		return txtDocsImportados;
	}
	
	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar(0,100);
		}
		return progressBar;
	}

	public boolean establecerConexionDB2(){
		boolean conexionAbierta = false;
		
		
		try {
			
			conexionAbierta = DAO.establecerConexionDB2();
			
		} catch (SQLException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			
		} catch (InstantiationException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			
		} catch (IllegalAccessException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			
		} catch (ClassNotFoundException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
		}
		
		return conexionAbierta;
		
	}
	
	public void cerrarConexionDB2(){
		try {
			
			DAO.cerrarConexionDB2();
			
		} catch (SQLException e) {
			logger.error("Error al intentar cerrar conexion con DB2", e);
		}
	}
	
	private boolean establecerConexionCM(){
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
	
	
	private void cerrarConecionCM(){
		try {
			
			DAO.cerrarConexionCM();
			
		} catch (DKException e) {
			logger.error("Error al intentar cerrar conexion a CM", e);
			
		} catch (Exception e) {
			logger.error("Error al intentar cerrar conexion a CM", e);
		}
	}

	public boolean muestraFacturasPorLote(String idUsuario)  {
		logger.info("Iniciando carga Masiva");
		boolean exito =false;
		String idUser=idUsuario;
		this.idUsuario=idUsuario;
	
		if(this.establecerConexionDB2()){
			try {
			
			
				int numFacXlote=DAO.consultaAllFacturasPorlote(this.lote,idUser);
				
				if(numFacXlote!=0){
					this.txtDocsLote.setText(""+numFacXlote);
					factListas=DAO.consultaFacturasPorloteSinEnviarACM(lote,idUser);//captu
					this.txtDocsAProcesar.setText(""+factListas);
					docsImportados=0;//DAO.consultaFactEnviadasACM(lote,idUser);
					this.txtDocsImportados.setText(docsImportados+"");
					docsNomimportados=factListas-docsImportados;
					this.txtDocsNoImportados.setText(docsNomimportados+"");
					this.numFacturas=factListas;
					
					exito=true;			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.cerrarConexionDB2();
		}
		return exito;
		
	}
	
	
	
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		logger.info("Iniciando carga Masiva");
		
		boolean procesoExito=false;
		if(this.numFacturas>0){
			
			if(this.establecerConexionDB2()){//seleccionar * fact 
				try {
					
					listaFacturas=DAO.getFacturasLoteUsuario(this.lote,idUsuario);

						if(this.establecerConexionCM()){
							logger.info("Extraiendo atribuots Factura-Mercancia");
							
							Documento plantillaFactura = new Documento();
							plantillaFactura.setNombre(Constants.ITEM_TYPE_MERCANCIA);
							plantillaFactura.setAtributos(DAO.getAtributosListByItemType(Constants.ITEM_TYPE_MERCANCIA));
							
							logger.info("Iterando atributos Factura Mercancia");
							
						for(int i=0; i<listaFacturas.size();i++){
									Factura factura = listaFacturas.get(i);
									Documento doc = plantillaFactura.clone();
									doc.setUrl(factura.getUrl());
									
									ArrayList<Atributo> atributos = doc.getAtributos();
									
									for (Atributo atributo : atributos) {
										String nombre = atributo.getName();
										
										if(nombre.equals(Constants.ATTR_NO_FACTURA)){
											atributo.setValor(factura.getNoFact());
											logger.info("NO Factura "
													+ factura.getNoFact());
										}else if(nombre.equals(Constants.ATTR_NO_PROVEEDOR)){
											atributo.setValor(factura.getProveedor().getNumero());
											logger.info("NO proveedor "
													+ factura.getProveedor()
															.getNumero());
										}else if(nombre.equals(Constants.ATTR_EMPRESA)){
												atributo.setValor(factura.getEmpresa().getNombre());
												logger.info("Empresa "
														+ factura.getEmpresa().getNombre());
										}else if(nombre.equals(Constants.ATTR_MONTO_FACTURA)){
												atributo.setValor(factura.getMonto());
												logger.info("Monto " + factura.getMonto());
										}else if(nombre.equals(Constants.ATTR_NOM_PROVEEDOR)){
											atributo.setValor(factura.getProveedor().getNombre());
											logger.info("Nombre Proveedor "+ factura.getProveedor()
														.getNombre());
										}else if(nombre.equals(Constants.ATTR_RFC_PROVEEDOR)){
											atributo.setValor(factura.getProveedor().getRfc());
											logger.info("Rfc Proveedor "
													+ factura.getProveedor().getRfc());
										}else if(nombre.equals(Constants.ATTR_SUC_PROVEEDOR)){
											atributo.setValor(factura.getProveedor().getSucursal());
											logger.info("Sucursal Proveedor "
													+ factura.getProveedor()
															.getSucursal());
										}else if(nombre.equals(Constants.ATTR_PLAZA)){
											atributo.setValor(factura.getPlaza());
											logger.info("Plaza" + factura.getPlaza());
										}else if(nombre.equals(Constants.ATTR_FECHAFACTURA)){
											if(factura.getFechaFactura()==null){
												atributo.setValor(null);
												logger.info("Fecha Factura null");
											}else{
												atributo.setValor(factura.getFechaFactura().toString());
												logger.info("Fecha Factura "+ factura.getFechaFactura().toString());
											}
										
										
										}else if(nombre.equals(Constants.ATTR_FECHARECEPCION)){
											atributo.setValor(factura.getFechaRecepcion().toString());
											logger.info("Fecha Factura "+ factura.getFechaRecepcion().toString());
										}else if(nombre.equals(Constants.ATTR_FOLIO_RECEP)){
											atributo.setValor(factura.getConcepto().getDescripcion());
											logger.info("Folio "+ factura.getConcepto().getDescripcion());
										}
									}
									
									/*insertar fact en CM*/
									logger.info("Importando  Factura.. ");
									if(DAO.importaDocumento(doc)){
										logger.info("se ha importando la Factura  "
												+ factura.getId());
										docsImportados++; //DAO.consultaFactEnviadasACM(lote,idUser);
										this.txtDocsImportados.setText(docsImportados+"");
										
										docsNomimportados=factListas-docsImportados;
										this.txtDocsNoImportados.setText(docsNomimportados+"");
										 
										factura.setPID(DAO.getNewPid());
										
										SwingUtilities.invokeAndWait(new Runnable() {
											int numProce=procesados;
											int avance = ((numProce+1) * 100)
													/ listaFacturas.size();
											
											
											public void run() {

												progressBar.setValue(avance);
											}

										});
										
										 logger.info("borrando de facturaCaptura");
											if(DAO.borrarStatusCaptura(factura.getId())){
												if(DAO.borrarFacturaCaptura(factura.getId())){
													logger.info("se ha borrado la Factura "
															+ factura.getId());
												}
											}
										procesados++;
										actualizarDocumentosProcesado(factura);
										Thread.sleep(500);
									}
									else{
									int status=Constants.STATUS_ERROR_IMPORTADO;
									int actualizo=DAO.actualizaStatus(factura, status);
										if(actualizo>0){
											factura.getEstado().setId(Constants.STATUS_ERROR_IMPORTADO);
										}
										
									}
									
									if(this.numFacturas==this.docsImportados){
										procesoExito=true;
										SwingUtilities.invokeAndWait(new Runnable() {
											public void run() {
												message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG019"),titulo,1);
												showMessage(message);
												  
												
											}

										});
										  
									}
								
						}
						this.cerrarConecionCM();
					}
					else{
						SwingUtilities.invokeAndWait(new Runnable() {
							public void run() {
								message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG027"),titulo,1);
								showMessage(message);
								  
								
							}

						});
						 
						}
		
		}
				
				catch (SQLException e) {
					logger.error(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001"), e);
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							message = new Mensaje(BundleManager.getValue(
									Constants.MESSAGE_BUNDLE, "ERR001"),
									titulo, 1);
							showMessage(message);
						}

					});
			
				} catch (DKException e) {
					logger.error(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001"), e);
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							message = new Mensaje(BundleManager.getValue(
									Constants.MESSAGE_BUNDLE, "ERR001"),
									titulo, 1);
							showMessage(message);
						}

					});
				
				} catch (Exception e) {
					logger.error(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001"), e);
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							message = new Mensaje(BundleManager.getValue(
									Constants.MESSAGE_BUNDLE, "ERR001"),
									titulo, 1);
							showMessage(message);
						}

					});
				} 
				
				this.cerrarConexionDB2();
		}else{
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG002"),titulo,1);
					showMessage(message);	
				}

			});
		}
	}
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				btnAceptarCM.setEnabled(true);
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
		if(this.establecerConexionDB2()){
			String tipoCaptura=Constants.REPORTECARGA_FACTURA_MERCANCIA;
			numDocActual = DAO.getNumeroDocumentosProcesado(factura,tipoCaptura);
			numDocActual++;
			System.out.println("Doc procesados="+numDocActual);
			if(DAO.actualizaDocumentosprocesados(numDocActual,factura,tipoCaptura)){
				logger.info("Se actualizo el num de Documentos Procesados del lote "
							+factura.getLote()+" ,Usuario "+factura.getUsuario().getIdUsuario()+" y tipo Captura "+tipoCaptura);
			}else{
				logger.info("Hubo un error al intentar actualizar  el num de Documentos Procesados del lote "
						+factura.getLote()+" Usuario "+factura.getUsuario().getIdUsuario()+" y tipo Captura "+tipoCaptura);
			}
		}
		
	}




}
