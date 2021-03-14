package com.adinfi.sevCaptura.controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.adinfi.sevCaptura.controller.MenuListener;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.entities.VentanaCapturaGastos;
import com.adinfi.sevCaptura.entities.VentanaCapturaMercancia;
import com.adinfi.sevCaptura.entities.VentanaCargaMasiva;
import com.adinfi.sevCaptura.entities.VentanaCargaMasivaMercancia;
import com.adinfi.sevCaptura.entities.VentanaLotes;
import com.adinfi.sevCaptura.entities.VentanaLotesMercancia;
import com.adinfi.sevCaptura.entities.VentanaReporteCarga;
import com.adinfi.sevCaptura.model.CapturaGastosModel;
import com.adinfi.sevCaptura.model.CapturaMercanciaModel;
import com.adinfi.sevCaptura.model.CargaMasivaMercanciaModel;
import com.adinfi.sevCaptura.model.CargaMasivaModel;
import com.adinfi.sevCaptura.model.LotesMercanciaModel;
import com.adinfi.sevCaptura.model.LotesModel;
import com.adinfi.sevCaptura.model.MenuModel;
import com.adinfi.sevCaptura.model.ReporteCargaModel;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.adinfi.sevCaptura.view.CapturaGastosView;
import com.adinfi.sevCaptura.view.CapturaMercanciaView;
import com.adinfi.sevCaptura.view.CargaMasivaMercancia;
import com.adinfi.sevCaptura.view.CargaMasivaVista;
import com.adinfi.sevCaptura.view.LotesMercanciaView;
import com.adinfi.sevCaptura.view.LotesView;
import com.adinfi.sevCaptura.view.MenuView;
import com.adinfi.sevCaptura.view.ReporteCargaView;

public class MenuController implements Observer {

	public static Logger logger = Logger.getLogger(MenuController.class);
	private MenuModel menuModel = null;
	private MenuView menuView = null;
	private VentanaCapturaGastos ventanaCapturaGastos = null;
	private VentanaCapturaMercancia ventanaCapturaMercancia = null;
	private VentanaLotes ventanaLotes = null;
	private VentanaLotesMercancia ventanaLotesMercancia=null;
	private VentanaCargaMasiva ventanaCargaMasiva = null;
	private VentanaCargaMasivaMercancia ventanaCargaMasivaMercancia = null;
	private Mensaje message;
	private VentanaReporteCarga ventanaReporteCarga = null;
	private String titulo = "Sistema de Captura";

	public MenuController(MenuModel menuModel, MenuView menuView) {
		this.menuModel = menuModel;
		this.menuView = menuView;
		this.menuView.addMenuListen(new MenuListener(this));
	}
	
	/**
	 * Verificar si hay lotes pendientes
	 * para mostrar Mensaje al usuario
	 * @return
	 */
	void lotesPendientes() {
		boolean lotesPendientesGastos=false;
		boolean lotesPendientesMerca=false;
		String idUsuario=this.usuario.getIdUsuario();
		lotesPendientesGastos=this.menuModel.lotesPendientes(idUsuario,Constants.TIPO_FACTURA_GASTO);
		lotesPendientesMerca=this.menuModel.lotesPendientes(idUsuario,Constants.TIPO_FACTURA_MERCANCIA);
		
		if(lotesPendientesGastos && lotesPendientesMerca){
			message = new Mensaje(BundleManager.getValue(
					Constants.MESSAGE_BUNDLE, "MSG039"), titulo, 1);
			
			int resultado = Utilities.showMensajeConfirmacion(this.menuView,
					message);
			if (resultado == JOptionPane.YES_OPTION) {
				this.abrirVentana(Constants.VENTANA_LOTES, null);
				this.abrirVentana(Constants.VENTANA_LOTES_MERCANCIA,null);
			}
		}else if(lotesPendientesGastos){
			message = new Mensaje(BundleManager.getValue(
					Constants.MESSAGE_BUNDLE, "MSG032"), titulo, 1);
			
			int resultado = Utilities.showMensajeConfirmacion(this.menuView,
					message);
			if (resultado == JOptionPane.YES_OPTION) {
				this.abrirVentana(Constants.VENTANA_LOTES, null);
			}
		}else if(lotesPendientesMerca){
			message = new Mensaje(BundleManager.getValue(
					Constants.MESSAGE_BUNDLE, "MSG040"), titulo, 1);
			
			int resultado = Utilities.showMensajeConfirmacion(this.menuView,
					message);
			if (resultado == JOptionPane.YES_OPTION) {
				this.abrirVentana(Constants.VENTANA_LOTES_MERCANCIA, null);
			}
		}
	
		
	}

	public Usuario usuario = null;

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	/**
	 * invoca al metodo abrirVentana 
	 * 
	 * @param idVentana es el número de 
	 * ventana a abrir.
	 * @param param
	 */
	public void abrirVentana(int idVentana, String param) {

		switch (idVentana) {
		case Constants.VENTANA_CAPTURA_GASTOS:
			this.AbrirVentanaCapturaGastos(param);
			break;

		case Constants.VENTANA_LOTES:
			this.abrirVentanaLotes();
			break;

		case Constants.VENTANA_CAPTURA_MERCANCIAS:
			this.abrirVentanaMercancia(param);
			break;
			
		case Constants.VENTANA_CARGA_MASIVA:
			this.abrirVentanaCargaMasiva(param);
			break;

		case Constants.VENTA_CARGA_MASIVA_MERCANCIA:
			this.abrirVentanaCargaMasivaMercancia(param);
			break;

		case Constants.VENTANA_REPORTE_CARGA:
			this.abrirVentanaReporteCarga();
			break;
			
		case Constants.VENTANA_LOTES_MERCANCIA:
			this.abrirVentanaLotesMercancia();
			break;

		default:
			break;
		}
	}

	/**
	 * Este metodo es responsable de identificar el origen de la indicacion para
	 * cerrar una ventana y se encarga de cerrar esa ventana.
	 */
	private void cerrarVentana(int idVentana) {

		switch (idVentana) {
		case Constants.VENTANA_CAPTURA_GASTOS:
			this.ventanaCapturaGastos.getView().dispose();
			this.ventanaCapturaGastos = null;
			break;

		case Constants.VENTANA_LOTES:
			this.ventanaLotes.getView().dispose();
			this.ventanaLotes = null;
			break;

		case Constants.VENTANA_CAPTURA_MERCANCIAS:
			this.ventanaCapturaMercancia.getView().dispose();
			this.ventanaCapturaMercancia = null;
			break;

		case Constants.VENTANA_CARGA_MASIVA:
			this.ventanaCargaMasiva.getView().dispose();
			this.ventanaCargaMasiva = null;
			break;
		case Constants.VENTA_CARGA_MASIVA_MERCANCIA:
			this.ventanaCargaMasivaMercancia.getView().dispose();
			this.ventanaCargaMasivaMercancia = null;
			break;
		case Constants.VENTANA_REPORTE_CARGA:
			this.ventanaReporteCarga.getView().dispose();
			this.ventanaReporteCarga = null;
			break;
		case Constants.VENTANA_LOTES_MERCANCIA:
			this.ventanaLotesMercancia.getView().dispose();
			this.ventanaLotesMercancia=null;
			break;

		default:
			break;
		}
	}

	/**
	 * abre la ventana de carga masiva 
	 * de facturas-gasto
	 * 
	 * @param lote
	 */

	private void abrirVentanaCargaMasiva(String lote) {

		if (this.ventanaCargaMasiva == null) {
			String idUsuario = this.usuario.getIdUsuario();
			
			CargaMasivaModel model = new CargaMasivaModel();
			model.setLote(lote);
			
			CargaMasivaVista view = new CargaMasivaVista(model);

			CargaMasivaController controller = new CargaMasivaController(model,view, idUsuario);
			controller.setUsuario(idUsuario);
			controller.addObserver(this);
			controller.addObserver(ventanaCapturaGastos.getController());
			
			this.ventanaCargaMasiva = new VentanaCargaMasiva(model, controller, view);
			this.ventanaCargaMasiva.getView().setTitle("Carga de documentos de Lote-" + lote);
			this.menuView.add(ventanaCargaMasiva.getView());
		} 
		
		ventanaCargaMasiva.getView().toFront();
		logger.info("El usuario "+this.usuario.getNombre()+" Ha entrado al modulo de Carga-Masiva");
	}

	/**
	 * abre la ventana carga masiva 
	 * de facturas mercancia
	 * 
	 * @param lote
	 */
	private void abrirVentanaCargaMasivaMercancia(String lote) {

		if (this.ventanaCargaMasivaMercancia == null) {

			CargaMasivaMercanciaModel model = new CargaMasivaMercanciaModel();
			model.setLote(lote);
			CargaMasivaMercancia view = new CargaMasivaMercancia(model);
			String idUsuario = this.usuario.getIdUsuario();

			CargaMasivaMercanciaController controller = new CargaMasivaMercanciaController(model, view, idUsuario);
			controller.setUsuario(idUsuario);
			controller.addObserver(this);

			controller.addObserver(ventanaCapturaMercancia.getController());

			this.ventanaCargaMasivaMercancia = new VentanaCargaMasivaMercancia(model, controller, view);
			this.ventanaCargaMasivaMercancia.getView().setTitle("Carga De Documentos a Content Manager Lote-" + lote);
			this.menuView.add(ventanaCargaMasivaMercancia.getView());

		} 
		ventanaCargaMasivaMercancia.getView().toFront();
		logger.info("El usuario "+this.usuario.getNombre()+"Ha entrado al modulo de Carga-Masiva Facturas Mercancia");
	}

	/**
	 * abre la ventana de reportes
	 */
	private void abrirVentanaReporteCarga() {
		if (ventanaReporteCarga == null) {

			String idUsuario = this.usuario.getIdUsuario();
			ReporteCargaModel model = new ReporteCargaModel();
			model.setIdUsuario(idUsuario);
			ReporteCargaView view = new ReporteCargaView(model);

			ReporteCargaController controller = new ReporteCargaController(
					model, view, idUsuario);

			controller.addObserver(this);
			this.ventanaReporteCarga = new VentanaReporteCarga(model, view,
					controller);
			this.menuView.add(ventanaReporteCarga.getView());

		} 
		this.ventanaReporteCarga.getView().toFront();
		logger.info("El usuario "+this.usuario.getNombre()+"Ha entrado al modulo de Reportes");
	}

	/**
	 * 
	 * @param lote
	 */
	private void AbrirVentanaCapturaGastos(String lote) {
		if (ventanaCapturaGastos == null) {
			String idUsuario = this.usuario.getIdUsuario();
			
			CapturaGastosModel model = new CapturaGastosModel();
			model.setLote(lote);
			model.setIdUsuario(idUsuario);
			//model.setPlaza(this.menuModel.getPlaza());
			
			CapturaGastosView view = new CapturaGastosView(model);
			
			CapturaGastosController controller = new CapturaGastosController(model, view);
			controller.setUsuario(this.getUsuario());
			controller.addObserver(this);
			
			this.ventanaCapturaGastos = new VentanaCapturaGastos(model, view, controller);
			if (model.getLote() == null) {
				this.ventanaCapturaGastos.getView().setTitle("Captura de Factura-Gasto");
			} else {
				this.ventanaCapturaGastos.getView().setTitle("Captura de Factura-Gasto Lote: " + model.getLote());
			}

			this.menuView.add(ventanaCapturaGastos.getView());
		}

		ventanaCapturaGastos.getView().toFront();
		message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG036"),titulo,1);
		ventanaCapturaGastos.getView().showMessage(message);
		logger.info("El usuario "+this.usuario.getNombre()+" Ha entrado al modulo de Captura-Gastos");
	}

	/**
	 * muestra ventana de captura factura-Mercancia
	 */
	private void abrirVentanaMercancia(String lote) {
		if (ventanaCapturaMercancia == null) {
			String idUsuario = this.usuario.getIdUsuario();
			
			CapturaMercanciaModel model = new CapturaMercanciaModel();
			model.setLote(lote);
			model.setIdUsuario(idUsuario);
			//model.setPlaza(this.menuModel.getPlaza());
			
			CapturaMercanciaView view = new CapturaMercanciaView(model);
			
			CapturaMercanciaController controller = new CapturaMercanciaController(
					model, view);
			controller.setUsuario(this.getUsuario());
			controller.addObserver(this);

			this.ventanaCapturaMercancia = new VentanaCapturaMercancia(model,
					view, controller);
			
			if (model.getLote() == null) {
				this.ventanaCapturaMercancia.getView().setTitle(
						"Captura de Factura-Mercancía");
			} else {
				this.ventanaCapturaMercancia.getView().setTitle(
						"Captura de Factura-Mercancía Lote: "
								+ model.getLote());
			}
			this.menuView.add(ventanaCapturaMercancia.getView());
		}

		ventanaCapturaMercancia.getView().toFront();
		message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG036"),titulo,1);
		ventanaCapturaMercancia.getView().showMessage(message);
		logger.info("El usuario "+this.usuario.getNombre()+" Ha entrado al modulo de Captura-Mercancia");
	}

	/**
	 * muestra la ventana de lotes
	 */
	private void abrirVentanaLotes() {
		if (ventanaLotes == null) {
			LotesModel model = new LotesModel();
			String idUsuario = this.usuario.getIdUsuario();
			model.setIdUsuario(idUsuario);
			LotesView view = new LotesView(model);
			LotesController controller = new LotesController(model, view);
			controller.addObserver(this);
			this.ventanaLotes = new VentanaLotes(model, view, controller);
			this.menuView.add(ventanaLotes.getView());

		}

		ventanaLotes.getView().toFront();
		logger.info("El usuario "+this.usuario.getNombre()+" Ha entrado al modulo de Lotes");
	}
	
	private void abrirVentanaLotesMercancia() {
		if (ventanaLotesMercancia == null) {
			LotesMercanciaModel model = new LotesMercanciaModel();
			String idUsuario = this.usuario.getIdUsuario();
			model.setIdUsuario(idUsuario);
			LotesMercanciaView view = new LotesMercanciaView(model);
			LotesMercanciaController controller = new LotesMercanciaController(model, view);
			controller.addObserver(this);
			this.ventanaLotesMercancia = new VentanaLotesMercancia(model, view, controller);
			this.menuView.add(ventanaLotesMercancia.getView());
		}

		ventanaLotesMercancia.getView().toFront();
		logger.info("El usuario "+this.usuario.getNombre()+" Ha entrado al modulo de Lotes Mercancía");
	}
	
	

	@Override
	public void update(Observable obs, Object msg) {
		String mensaje = msg.toString().split("_")[0];

		if (obs instanceof CapturaGastosController) {

			if (mensaje.equals("CerrarVentanaCapturaGastos")) {
				this.cerrarVentana(Constants.VENTANA_CAPTURA_GASTOS);
			} else if (mensaje.equals("AbrirCargaMasiva")) {
				String lote = msg.toString().split("_")[1];

				if (ventanaCargaMasiva == null) {
					this.abrirVentana(Constants.VENTANA_CARGA_MASIVA, lote);
				}
			} else if (mensaje.equals("CerrarVentanaCargaMasiva")) {

				this.cerrarVentana(Constants.VENTANA_CARGA_MASIVA);
			}
		} else if (obs instanceof CapturaMercanciaController) {

			if (mensaje.equals("CerrarVentanaCapturaMercancia")) {
				this.cerrarVentana(Constants.VENTANA_CAPTURA_MERCANCIAS);
			} else if (mensaje.equals("AbrirCargaMasivaMercancia")) {
				String lote = msg.toString().split("_")[1];
				this.abrirVentana(Constants.VENTA_CARGA_MASIVA_MERCANCIA, lote);
			} else if (mensaje.equals("cerrarVentanaCargaMasivaMercancia")) {
					this.cerrarVentana(Constants.VENTA_CARGA_MASIVA_MERCANCIA);
			}
		} else if (obs instanceof LotesController) {

			if (mensaje.equals("CerrarVentanaLotes")) {
				this.cerrarVentana(Constants.VENTANA_LOTES);

			} else if (mensaje.equals("AbrirLote")) {
				if (ventanaCapturaGastos == null) {
					this.cerrarVentana(Constants.VENTANA_LOTES);
					String lote = msg.toString().split("_")[1];

					this.abrirVentana(Constants.VENTANA_CAPTURA_GASTOS, lote);

				} else {
					Mensaje message = new Mensaje(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "MSG015"), titulo, 1);
					Utilities.showMensaje(menuView, message);

				}
			}

		} else if (obs instanceof CargaMasivaController) {
			if (mensaje.equals("CerrarVentanaCargaMasiva")) {

				this.cerrarVentana(Constants.VENTANA_CARGA_MASIVA);
			}
		} else if (obs instanceof ReporteCargaController) {

			if (mensaje.equals("CerrarVentanaReporteCarga")) {
				this.cerrarVentana(Constants.VENTANA_REPORTE_CARGA);
			}

		}else if(obs instanceof LotesMercanciaController){
			if(mensaje.equals("CerrarVentanaLotesMercancia")){
				this.cerrarVentana(Constants.VENTANA_LOTES_MERCANCIA);
			}else if(mensaje.equals("AbrirLoteMercancia")){
				if (ventanaCapturaMercancia == null) {
					this.cerrarVentana(Constants.VENTANA_LOTES_MERCANCIA);
					String lote = msg.toString().split("_")[1];
					this.abrirVentana(Constants.VENTANA_CAPTURA_MERCANCIAS, lote);

				} else {
					Mensaje message = new Mensaje(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "MSG015"), titulo, 1);
					Utilities.showMensaje(menuView, message);

				}
			}
			
		}

	}

	/**
	 * metodo wue valida si hay facturas pendientes de capturar si el usaurio
	 * intenta cerrar la ventana de Fctura-Gastos
	 */
	public void validaFacturasPendientesCaptura() {
		String idUsuario = this.usuario.getIdUsuario();
		int facturas = 0;
		facturas = menuModel.countFacturasPendientesCapturar(idUsuario);
		if (facturas != 0) {
			message = new Mensaje(BundleManager.getValue(
					Constants.MESSAGE_BUNDLE, "MSG016"), titulo, 1);
			int resultado = Utilities.showMensajeConfirmacion(this.menuView,
					message);
			if (resultado == JOptionPane.YES_OPTION) {
				this.menuView.dispose();
			}

		} else {
			this.menuView.dispose();
		}

	}
	
	/**
	 * Muestra un mensaje de
	 * confirmacion al usuario
	 * que si desea salir del
	 * sistema
	 */
	public void confirmarSalirUsuario() {
		
		message = new Mensaje(BundleManager.getValue(
				Constants.MESSAGE_BUNDLE, "MSG031"), titulo, 1);
		
		int resultado = Utilities.showMensajeConfirmacion(this.menuView,
				message);
		if (resultado == JOptionPane.YES_OPTION) {
			this.validaFacturasPendientesCaptura();
		}
		
		
	}

}
