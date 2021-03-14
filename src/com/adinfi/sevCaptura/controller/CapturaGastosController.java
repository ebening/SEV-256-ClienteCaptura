package com.adinfi.sevCaptura.controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;


import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.model.CapturaGastosModel;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.adinfi.sevCaptura.view.CapturaGastosView;

public class CapturaGastosController extends Observable implements Observer {

	public static Logger logger = Logger.getLogger(CapturaGastosController.class);

	private CapturaGastosModel model = null;
	private CapturaGastosView view = null;
	private Mensaje message;
	private String titulo = "Sistema de Captura";

	public CapturaGastosController(CapturaGastosModel model, CapturaGastosView view) {
		this.model = model;
		this.view = view;
		this.view.addCapturaGastosListener(new CapturaGastosListener(this));
		if (model.getLote() != null) {/*validacion para cuando se abre la ventana desde abrir lote de lotes Pendientes*/
			logger.info("Lote a Mostrar:" + model.getLote());
			model.setPaginaActual(1);
			model.actualizaTablaFacturas();
		} else {
			model.procesoValidarLote();
		}

	}

	public Usuario usuario = null;

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Este metodo escucha a los botones de importacion de documento manda
	 * ejecutar el JFileChooser para seleccionad la url.
	 * 
	 * @param tipo
	 *            - Define el tipo de busqueda a realizar. Ver comentarios del
	 *            metodo.
	 */
	public File executeFileChooser(int tipo, int nombreDialog) {
		File file = model.getImportDocumentURL(view, tipo, nombreDialog);
		return file;
	}

	/**
	 * Este metodo toma la direccion URL obtenida del FileChooser e importa el
	 * documeno a la aplicacion.
	 * 
	 * @param tipo
	 *            - Define el tipo de busqueda a realizar. Ver comentarios del
	 *            metodo.
	 */
	public void importaDocumento(int tipo) {
		int tipoDoc = tipo;
		File file = executeFileChooser(tipo, 1);

		if (file != null) {
			if (model.valExisteDireccion(file)) {
				if (tipoDoc == Constants.IMPORTA_UN_DOCUMENTO) {// Esta validación determina cuando se agrega un documento o carpeta.
				if (model.agregarDocumento(file, tipo, usuario)) {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									if (model.getActivarprimerRegistro()) {
										model.setFilaSeleccioanda(0);
										model.seleccionaFila(0);
										validarFilaSeleccionada();
										model.setActivarprimerRegistro(false);
									}
								}
							});
						}
						else{
							message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG037"), model.getTitulo(), 1);
							this.view.showMessage(message);
						}
					
				} else if (tipoDoc == Constants.IMPORTA_CARPETA) {
					
						if (model.agregarDocumento(file, tipo, usuario)) {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									if (model.getActivarprimerRegistro()) {
										model.setFilaSeleccioanda(0);
										model.seleccionaFila(0);
										validarFilaSeleccionada();
										model.setActivarprimerRegistro(false);
									}
								}
							});
						}
				}
			} else {

				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG024"), model.getTitulo(), 1);
				this.view.showMessage(message);
			}
		}
	}

	
	/**
	 * actualiza los datos
	 * de la factura seleccionada
	 * cuando se presiona el botoón de guardar
	 */
	public void guardarFacturaDB2() {
		
		/*
		 * TODOGuardar
		 * agregar validacion de clave unica
		 * fatura + proveedor
		 */
		if(model.isFacturaValida(model.listaFacturas.get(model.getFilaSeleccionada())) ){
			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG041"), model.getTitulo(), 1);
			this.view.showMessage(message);	
		}
		else{
			if (model.isValidoMoneda()) {
				if (model.isMontoValido()) {
					if(model.isNumTiendaValido()){
						if (model.procesoValidarcamposCaptura(usuario)) {
							if (model.getFilaSeleccionada() < (model.getNumRegistros() - 1)) {
								model.aumentarFila();
								this.validarFilaSeleccionada();
								model.setFechaRecepcion(null);
							}
						}
					}else{
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG038"), model.getTitulo(), 1);
						this.view.showMessage(message);	
					}
					
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG033"), model.getTitulo(), 1);
					this.view.showMessage(message);
				}
	
			} else {
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG030"), model.getTitulo(), 1);
				this.view.showMessage(message);
			}
	
			if (model.getUltimaCaptura()) {
				model.insertarFechaFinCaptura();
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG018"), titulo, 1);
				int resultado = Utilities.showMensajeConfirmacion(this.view, message);
				if (resultado == JOptionPane.YES_OPTION) {
					this.cargaMasiva();
				}
				
				model.setUltimaFilaCaptura(false);
			}
		
		}
	}

	/**
	 * reemplaza un doc por otro
	 * al presionar el boton de cambiar url
	 */
	public void cambiarUrl() {
		message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG020"), model.getTitulo(), 1);
		int resultado = view.showMessageConfirmacion(message);
		if (resultado == JOptionPane.YES_OPTION) {
			int tipo = Constants.IMPORTA_UN_DOCUMENTO;
			File file = executeFileChooser(tipo, 2);
			if (file != null) {
				if (model.valExisteDireccion(file)) {
					model.procesoCambiarUrl(file);
				}
			}
		}
	}

	/**
	 * elimina el doc de factura
	 * seleccionada
	 */
	public void descartarDocumento() {
		message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG021"), model.getTitulo(), 1);
		int resultado = view.showMessageConfirmacion(message);
		if (resultado == JOptionPane.YES_OPTION) {
			if (model.procesoDescartarDocumento(usuario)) {
				model.actualizaContadoresPagina(model.getTotalRegistros());
				model.muestraFacturasPorLote();
				model.setFilaSeleccioanda(0);
				model.getJTableDatos().addRowSelectionInterval(0, 0);
				if (model.getFilaSeleccionada() < (model.getNumRegistros())) {
					this.validarFilaSeleccionada();
				} else {
					model.limpiarCamposCaptura();
					model.habilitaCampoCaptura(false);
					model.habilitaBotonesFunciones(false);
					model.btnCargaMasiva.setEnabled(false);
					model.habilitaRdBtnsTipoDocumento(false);
					model.setPrimerCaptura(true);
					model.setTotalRegistros(0);
					model.seleccionaFila(0);
					model.setPaginaActual(0);
					model.setTotaldePaginas(0);
					model.setIndiceSup(0);
					model.setIndiceInf(0);
					model.actualizaContadoresPagina(model.getTotalRegistros());
					model.setUltimaFilaCaptura(false);
					view.cierraDocuments();
				}
			}
		}

	}

	public void cerrarVentanaCaptura() {
		String idUsuario = this.usuario.getIdUsuario();
		int facturas = 0;
		facturas = model.countFacturasPendientesCapturar(idUsuario);
		if (facturas != 0) {

			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG016"), titulo, 1);
			int resultado = Utilities.showMensajeConfirmacion(this.view, message);
			if (resultado == JOptionPane.YES_OPTION) {
				setChanged();
				notifyObservers("CerrarVentanaCapturaGastos");
			}

		} else {
			setChanged();
			notifyObservers("CerrarVentanaCapturaGastos");
		}
	}

	public void validarFilaSeleccionada() {
		int filaSeleccionada = 0;
		view.cierraDocuments();
		filaSeleccionada = model.getFilaSeleccionada();
		
		if (filaSeleccionada >= 0) {
			view.cierraDocuments();
			if (filaSeleccionada < model.getNumRegistros()) {
				String url = model.getUrl();
				File file = new File(url);
				if (model.valExisteDireccion(file)) {
					view.openDocument(url, true);
					
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG034"), model.getTitulo(), 1);
					this.view.showMessage(message);
				}
			}
			model.consultarFilaSeleccionada();
		}
	}

	public void cambiarPagina(String accion) {

		if (model.procesoCambiarPagina(accion)) {
			model.setFilaSeleccioanda(0);
			model.seleccionaFila(0);
			this.validarFilaSeleccionada();
		}

	}

	/**
	 * 
	 */
	public void actualizaDatosProveedor() {

		if (model.getNoProveedor()) {
			model.procesoActualizaDatosProveedor();
		}

	}

	public void ActualizaCombos() {
		model.procesoLlenarCbxDestinatario();
		model.procesoLlenarCbxConcepto();
	}

	public void validaTipoDocumento(int tipoDocumento) {
		if (tipoDocumento == 1) {
			model.setTipoDocumento(1);
			model.habilitaCampoCaptura(true);
			model.habilitaBotonesImportacion(true);
			model.habilitaBotonesFunciones(true);
		} else if (tipoDocumento == 2) {
			model.setTipoDocumento(2);
			model.habilitaCampoCaptura(true);
			model.habilitaBotonesImportacion(true);
			model.habilitaBotonesFunciones(true);
		} else if (tipoDocumento == 3) {
			String idUsuario = this.usuario.getIdUsuario();
			JDialog dialog = this.view.getJDialogFacturasoporte();
			model.muestraFacturasSoportePorLote(idUsuario);
			if (model.isSoporte()) {
				model.seleccionaFacturaDelSoporte();
			}
			dialog.setVisible(true);
		}

	}

	public void agregaDocuementoaFacturas() {
		int fila = 0;
		fila = model.getFilaSeleccionadaFactSoporte();
		int registros = model.getSizeJTablaFactSoporte();

		if (fila >= 0 && fila < registros) {
			if (model.nombreDocEmpty()) {
				if (model.procesoAsignarDocumentoAFactura()) {
					model.setTipoDocumento(3);
					JDialog dialog = this.view.getJDialogFacturasoporte();
					dialog.setVisible(false);
					model.limpiaComponentesJDialogSoporte();
					
					if (model.getUltimaCaptura()) {
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG018"), titulo, 1);
						int resultado = Utilities.showMensajeConfirmacion(this.view, message);
						if (resultado == JOptionPane.YES_OPTION) {
							this.cargaMasiva();
						}
						
						model.setUltimaFilaCaptura(false);
					}
					
				}
			} else {
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG026"), titulo, 1);
				this.view.showMessage(message);
			}
		} else {
			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG035"), titulo, 1);
			this.view.showMessage(message);
			model.setTipoDocumento(1);
			model.setTipoFactura();// para cambiar los rdbtns al e factura
			model.limpiaComponentesJDialogSoporte();
			this.view.getJDialogFacturasoporte().setVisible(false);

		}

	}

	public void cargaMasiva() {
		int numFact = 0;
		String lote = model.getLote();

		if (lote == null) {
			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG028"), titulo, 1);
			this.view.showMessage(message);
		} else {

			String idUsuario = this.usuario.getIdUsuario();
			numFact = model.procesoActualizarStatusEnvindoaCM(idUsuario);
			if (numFact > 0) {
				model.setActivaCargaMasiva(1);
				model.setFilaSeleccioanda(0);
				model.seleccionaFila(0);
				this.validarFilaSeleccionada();
				model.btnCargaMasiva.setEnabled(false);
				setChanged();
				notifyObservers("AbrirCargaMasiva_" + lote);

			} else {
				String titulo = "Error Carga Masiva";
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG028"), titulo, 1);
				this.view.showMessage(message);
			}

		}

	}

	public void cerrarStatusCargaMasiva() {
		this.view.cerrarJDialogEnviarACM();

	}

	@Override
	public void update(Observable obs, Object msj) {
		String mensaje = msj.toString();
		if (obs instanceof CargaMasivaController) {

			if (mensaje.equals("terminoCargaMasiva")) {

				model.setActivaCargaMasiva(0);
				model.btnCargaMasiva.setEnabled(true);

				setChanged();
				notifyObservers("CerrarVentanaCargaMasiva");
				
				model.procesoValidarLote();

				if (model.getNumRegistros() > 0) {
					model.setFilaSeleccioanda(0);
					model.seleccionaFila(0);
					this.validarFilaSeleccionada();
				} else {
					model.limpiarCamposCaptura();
					model.habilitaCampoCaptura(false);
					model.habilitaBotonesFunciones(false);
					model.habilitaRdBtnsTipoDocumento(false);
					model.setPrimerCaptura(true);
					model.setTotalRegistros(0);
					model.seleccionaFila(0);
					model.setPaginaActual(0);
					model.setTotaldePaginas(0);
					model.setIndiceSup(0);
					model.setIndiceInf(0);
					model.actualizaContadoresPagina(model.getTotalRegistros());
					model.muestraFacturasPorLote();
					model.setFilaSeleccioanda(0);
					model.setUltimaFilaCaptura(false);
					this.validarFilaSeleccionada();
					
				}
				
			

			}
		}

	}

	public void cambiarATipoDocFactura() {
		model.limpiaComponentesJDialogSoporte();
		view.cerrarJDialogSoporte();
		System.out.println();

		if (model.isSoporte()) {
			this.model.getRdnBtnSoporte().setSelected(true);
		} else {
			this.model.getRdnBtnFact().setSelected(true);
		}

	}

}
