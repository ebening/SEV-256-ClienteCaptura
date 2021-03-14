package com.adinfi.sevCaptura.controller;


import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.model.CapturaMercanciaModel;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.adinfi.sevCaptura.view.CapturaMercanciaView;

public class CapturaMercanciaController extends Observable implements Observer {
	
	public static Logger logger = Logger.getLogger(CapturaMercanciaController.class);
	
	private CapturaMercanciaModel model =null;
	private CapturaMercanciaView view=null;
	private Mensaje message;
	private String titulo = "Sistema de Captura";
	
	public CapturaMercanciaController(CapturaMercanciaModel model,CapturaMercanciaView view){
		this.model=model;
		this.view=view;
		this.view.addCapturaMercanciaListener(new CapturaMercanciaListener(this));
		
		if (model.getLote() != null) {/*validacion para cuando se abre la ventana desde abrir lote de lotes Pendientes*/
			logger.info("Lote a Mostrar :" + model.getLote());
			model.setPaginaActual(1);
			model.actualizaTablaFacturas();
		} else {
			model.procesoValidarLote();
		}
	}
	
	public Usuario usuario = null;
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public void cerrarVentanaCapturaMercancia() {
		
		String idUsuario=this.usuario.getIdUsuario();
		int facturas=0;
		 facturas=model.countFacturasPendientesCapturar(idUsuario);
		if(facturas!=0){
			
			
			
			message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG016"),titulo,1);
			//Utilities.showMensajeConfirmacion(this.view, message);
			 int resultado=Utilities.showMensajeConfirmacion(this.view, message);
				if(resultado==JOptionPane.YES_OPTION){
					setChanged();
					notifyObservers("CerrarVentanaCapturaMercancia");
				}
				
		}else{
			setChanged();
			notifyObservers("CerrarVentanaCapturaMercancia");
		}
	
	}
	
	/**
	 * Este metodo escucha a los botones de 
	 * importacion de documento manda ejecutar 
	 * el JFileChooser para seleccionad la url.
	 * @param tipo - Define el tipo de busqueda 
	 * a realizar. Ver comentarios del metodo.
	 */
	public File executeFileChooser(int tipo,int nombreDialog){
		File file = model.getImportDocumentURL(view, tipo,nombreDialog);		
		return file;
	}
	
	/**
	 * Este metodo toma la direccion URL obtenida
	 * del FileChooser e importa el documeno a
	 * la aplicacion.
	 * @param tipo - Define el tipo de busqueda 
	 * a realizar. Ver comentarios del metodo.
	 */
	public void importaDocumento(int tipo){		
		File file = executeFileChooser(tipo,1);
		int tipoDoc=tipo;
		
		if( file != null){
			if(model.valExisteDireccion(file)){
				if(tipoDoc==Constants.IMPORTA_UN_DOCUMENTO){
					if(model.isArchivoValido(file)){
						if(model.agregarDocumento(file, tipo, usuario)){
							SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								if(model.getActivarprimerRegistro()){
									model.setFilaSeleccioanda(0);
									model.seleccionaFila(0);
									validarFilaSeleccionada();
									model.setActivarprimerRegistro(false);
								}
							}
						});
						
						}
					}else{
						message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG024"),model.getTitulo(),1);
						model.showMessage(message);
					}
				}else if(tipoDoc==Constants.IMPORTA_CARPETA){
					if(model.isCarpetaValida(file)){
						if(model.agregarDocumento(file, tipo, usuario)){
							SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								if(model.getActivarprimerRegistro()){
									model.setFilaSeleccioanda(0);
									model.seleccionaFila(0);
									validarFilaSeleccionada();
									model.setActivarprimerRegistro(false);
								}
								/*model.habilitaBoronCargaMasiva();*/
							}
						});
						
						}
					}else{
						message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG024"),model.getTitulo(),1);
						model.showMessage(message);
					}
				}
				
			}else{
			
				message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG024"),model.getTitulo(),1);
				model.showMessage(message);
				}
		}
	}
	

	public void validarFilaSeleccionada() {
	 int filaSeleccionada=0;
	
	 filaSeleccionada=model.getFilaSeleccionada();
	System.out.println("FIla seleccionada "+model.getFilaSeleccionada());
	 if(filaSeleccionada>=0){
		 view.cierraDocuments();
		if(filaSeleccionada<model.getNumRegistros()){
		 String url=model.getUrl();
		 File file = new File(url);
		 if(model.valExisteDireccion(file)){
			 view.openDocument(url, true);
		 }else{
			 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG034"),model.getTitulo(),1);
			 this.view.showMessage(message);
		 }
	 } 
		model.consultarFilaSeleccionada();
	 }
	}

	public void cambiarUrl() {
		message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG020"),model.getTitulo(),1);
		int resultado=view.showMessageConfirmacion(message);
		if(resultado==JOptionPane.YES_OPTION){
			int tipo=Constants.IMPORTA_UN_DOCUMENTO;
			File file = executeFileChooser(tipo,2);
			if(file!=null){
				if(model.valExisteDireccion(file)){
					model.procesoCambiarUrl(file);
				}	
			}
			
		}
	}
	public void descartarDocumento() {
		message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG021"),model.getTitulo(),1);
		int resultado=view.showMessageConfirmacion(message);
		if(resultado==JOptionPane.YES_OPTION){
			if(model.procesoDescartarDocumento(usuario)){
				//model.setTotalRegistros(model.getTotalRegistros()-1);
				model.actualizaContadoresPagina(model.getTotalRegistros());
				model.muestraFacturasPorLote();
				model.setFilaSeleccioanda(0);
				model.getJTableDatos().addRowSelectionInterval(0, 0);
				if (model.getFilaSeleccionada() < (model.getNumRegistros())) {
					//model.aumentarFila();
					this.validarFilaSeleccionada();
				} else {
					model.limpiarCamposCaptura();
					model.habilitaCampoCaptura(false);
					model.habilitaBotonesFunciones(false);
					model.btnCargaMasiva.setEnabled(false);
					model.habilitaRdBtnsTipoDocumento(false);
					view.cierraDocuments();
			}
		}
		
	}
}
	
	
	public void cambiarPagina(String accion) {
		
		if(model.procesoCambiarPagina(accion)){
			model.setFilaSeleccioanda(0);
			model.seleccionaFila(0);
			this.validarFilaSeleccionada();
		}
		
	}
	
	public void guardarFacturaDB2(){
		
			if(model.isMontoValido()){
				if(model.procesoValidarcamposCaptura(usuario)){
					if (model.getFilaSeleccionada() < (model.getNumRegistros()-1)){
						model.aumentarFila();
						this.validarFilaSeleccionada();
					} 
				}
			}else{
				 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG033"),model.getTitulo(),1);
				 this.view.showMessage(message);
			}
			
			if(model.getUltimaCaptura()){
				model.insertarFechaFinCaptura();
				String titulo="Inciar Carga Masiva";
				 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG018"),titulo,1);
				 int resultado=Utilities.showMensajeConfirmacion(this.view, message);
					if(resultado==JOptionPane.YES_OPTION){
						this.cargaMasiva();
					}
					model.setUltimaFilaCaptura(false);
			}
	}
	
	public void actualizaDatosProveedor() {
		if(model.getNoProveedor()){
			model.procesoActualizaDatosProveedor();
		}
		
	}
	public void cargaMasiva() {
		int numFact=0;
		String lote = model.getLote();
		
		if(lote == null){
			String titulo="Importar a Content Manager";
			message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG028"),titulo,1);
			this.view.showMessage(message);
		}else{
				 
				String idUsuario= this.usuario.getIdUsuario();
				numFact=model.procesoActualizarStatusEnvindoaCM(idUsuario);
				
				if(numFact>0){
					model.setActivaCargaMasiva(1);
					model.setFilaSeleccioanda(0);
					model.seleccionaFila(0);
					this.validarFilaSeleccionada();
					model.btnCargaMasiva.setEnabled(false);
					setChanged();
					notifyObservers("AbrirCargaMasivaMercancia_"+lote);
					
				}else{
					String titulo="Error Carga Masiva";
					message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG028"),titulo,1);
					this.view.showMessage(message);
				}
				
		}

	}
	@Override
	public void update(Observable arg0, Object msj) {
		String mensaje = msj.toString();
		if(mensaje.equals("terminoCargaMasivaMercancia")){
			model.setActivaCargaMasiva(0);
			model.btnCargaMasiva.setEnabled(true);
			
			setChanged();
			notifyObservers("cerrarVentanaCargaMasivaMercancia");
			
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
			
			model.actualizaContadoresPagina(model.getTotalRegistros());
			model.muestraFacturasPorLote();
		}
		
	}


}
