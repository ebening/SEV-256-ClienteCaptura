package com.adinfi.sevCaptura.model;


import java.sql.SQLException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import org.apache.log4j.Logger;
import com.adinfi.sevCaptura.dao.MenuDAO;
import com.adinfi.sevCaptura.dao.MenuDAOInterface;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Usuario2;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;

public class MenuModel {

	public static Logger logger = Logger.getLogger(MenuModel.class);
	public Usuario2 usuario = null;
	private MenuDAOInterface DAO = null;
	private String plaza;
	private Mensaje message;
	private JInternalFrame jFrameMensajes = null;
	private String titulo = "Cliente-Captura";

	public MenuModel() {
		this.DAO = new MenuDAO();
		//this.setPlaza(trearPlaza());
	}

	/*private String trearPlaza() {
		String plazaDesktop= BundleManager.getValue(Constants.CONECTION_BUNDLE, "plaza.nombre");
		return plazaDesktop;
	}*/

	private JDesktopPane jDesktopPanePrincipal = null;

	public JDesktopPane getJDesktopPanePrincipal() {
		if (jDesktopPanePrincipal == null) {
			jDesktopPanePrincipal = new JDesktopPane();
		}
		return jDesktopPanePrincipal;
	}

	/**
	 * cierra conexion con DB2
	 */
	public void cerrarConexionDB2() {
		try {

			DAO.cerrarConexionDB2();

		} catch (SQLException e) {
			logger.error("Error al intentar cerrar conexion con DB2", e);
		}

	}

	/**
	 * 
	 * @return true si la conexion fue exitosa
	 */
	public boolean establecerConexionDB2() {
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;

		try {

			conexionAbierta = DAO.establecerConexionDB2();

		} catch (SQLException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR002"),
					e);
			seGeneroExcepcion = true;

		} catch (InstantiationException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR002"),
					e);
			seGeneroExcepcion = true;

		} catch (IllegalAccessException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR002"),
					e);
			seGeneroExcepcion = true;

		} catch (ClassNotFoundException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR002"),
					e);
			seGeneroExcepcion = true;
		}

		if (seGeneroExcepcion) {

			message = new Mensaje(BundleManager.getValue(
					Constants.MESSAGE_BUNDLE, "MSG002"), titulo, 1);
			this.showMessage(message);
		}
		return conexionAbierta;

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
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
		}

		return conexionValida;
	}

	public boolean lotesPendientes(String idUsuario,int tipoFact) {
		boolean lotesPendientes = false;
		if (this.establecerConexionDB2()) {

			try {
				if (DAO.lotesPendientes(idUsuario,tipoFact))
				lotesPendientes = true;
			} catch (SQLException e) {

			}

			this.cerrarConexionDB2();
		}
		return lotesPendientes;
	}

	/**
	 * metodo que verifica si hay facturas pendientes de capturar cuenao el
	 * usuario intenta cerrar el sistema
	 * 
	 * @param idUsuario
	 * @return
	 */
	public int countFacturasPendientesCapturar(String idUsuario) {
		int factPendientes = 0;
		if (this.establecerConexionDB2()) {
			String lote = Utilities.getLoteActual();
			try {
				factPendientes = DAO.consultaFacturasPendientesCaptura(lote,
						idUsuario);
			} catch (SQLException e) {
				if (this.validarConexionDB2()) {
					logger.error(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001"), e);

					message = new Mensaje(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001")
							+ e.getMessage(), titulo, 1);
					this.showMessage(message);
				}

			}
			this.cerrarConexionDB2();
		}
		return factPendientes;
	}

	/**
	 * Muestra el emnsaje
	 * 
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje) {
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

}
