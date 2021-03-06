package com.adinfi.sevCaptura.model;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.adinfi.sevCaptura.dao.LoginDAO;
import com.adinfi.sevCaptura.dao.LoginDAOInterface;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import com.ibm.mm.sdk.common.DKException;


public class LoginModel {
	
	public static Logger logger = Logger.getLogger(LoginModel.class);
	private JTextField jTxtUser = null;
	private JPasswordField jPass = null;
	private JButton jBtnEntrar = null;
	private JButton jBtnSalir= null;
	private LoginDAOInterface DAO=null;
	private Usuario usuario=null;
	private Mensaje message;
	private JInternalFrame jFrameMensajes = null;
	private String titulo="Cliente-Captura";
	
	public LoginModel(){
		this.DAO= new LoginDAO();
	}
	
	public Mensaje getMensaje(){
		
		return this.message;
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public JTextField getJTextFieldUser() {
		if (jTxtUser == null) {
			jTxtUser = new JTextField("");
		}
		return jTxtUser;
	}
	
	public JPasswordField getJPasswordField() {
		if (jPass == null) {
			jPass = new JPasswordField("");
		}
		return jPass;
	}
	
	public JButton getJButtonEntrar() {
		if (jBtnEntrar == null) {
			jBtnEntrar = new JButton();
			jBtnEntrar.setText("Entrar");
		}
		return jBtnEntrar;
	}
	public JButton getJButtonCancelar() {
		if (jBtnSalir == null) {
			jBtnSalir = new JButton();
			jBtnSalir.setText("Salir");
		}
		return jBtnSalir;
	}
	
	/**
	 * valida al
	 * usuario que intenta entrar
	 * al sistema en base al nombre
	 * y password
	 * @return true si el usuario tiene acceso
	 */
	public boolean validaLogin() {

		boolean acceso = false;
		String titulo="Login ";
		String noEmpleado = jTxtUser.getText();
		char[] pass = jPass.getPassword();
		String contrasena = new String(pass);
		
		if (!userEmpty()) {
			
			 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG001"),titulo,1);
			
		} else if (!passwordEmpty()) {
			message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG001"),titulo,1);
			
		}else {//falta validar conexion CM
			
		if(this.establecerConexionCM()){
			this.cerrarConecionCM();
			if(establecerConexionDB2()){
					   try {
						   	this.usuario=DAO.validaUsuario(noEmpleado,contrasena);
						    if( usuario!=null){
						    	logger.info("Se ha logueado el usuario "+usuario.getIdUsuario()+":"+usuario.getNombre());
						    	if(DAO.extraerListasAcceso(this.usuario)){
						    		if(this.usuario.tieneAccesoAplicativo(Constants.APP_CM_FINANZAS_CAPTURA)){
										acceso=true;
									}else{
										message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG004"),titulo,1);
									}
						    		
						    	}
						    	
						    }
						    else{
						    	message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG003"),titulo,1);
								
						    }		    				
					} catch (SQLException e) {
						logger.error("Error al intentar loguearce el usuario");
					}
					   cerrarConexionDB2();
			}else{
				message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG002"),titulo,1);
				   }
		}else{
			message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG027"),titulo,1);
		}
		}
			
		return acceso;
	}
	
	public boolean userEmpty() {
		String user = jTxtUser.getText();
		if (!user.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean passwordEmpty() {
		char[] pass = jPass.getPassword();
		String password = new String(pass);
		if (!password.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	

	
	/**
	 * metodo que establece 
	 * conexion a DB"
	 * @return true
	 * si la conexion 
	 * fue exitosa
	 */
	public boolean establecerConexionDB2(){
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;
		
		try {
			
			conexionAbierta = DAO.establecerConexionDB2();
			
		} catch (SQLException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			seGeneroExcepcion = true;

		} catch (InstantiationException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			seGeneroExcepcion = true;

		} catch (IllegalAccessException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			seGeneroExcepcion = true;

		} catch (ClassNotFoundException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
			seGeneroExcepcion = true;
		}

		if (seGeneroExcepcion) {
			
			 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG002"),titulo,1);
			 this.showMessage( message);
		}
		return conexionAbierta;
		
	}
	public void cerrarConexionDB2(){
		try {
			
			DAO.cerrarConexionDB2();
			
		} catch (SQLException e) {
			//log
			System.out.println("Error al intentar cerrar " +
					"la conexion con DB2."+e.getMessage());
		}
	}
	
	/**
	 * establecer conexion
	 * a CM.
	 * @return true si la conexion
	 * resulta exitosa
	 */
	private boolean establecerConexionCM(){
		boolean conexionvalida = false;
		
		try {
			
			conexionvalida = DAO.establecerConexionCM();
			
		} catch (DKException e) {
			e.printStackTrace();
			logger.error("Error al intentar conectar a CM",e);
			
		} catch (Exception e) {
			logger.error("Error al intentar conectar a CM",e);
			
			
		}
		return conexionvalida;
	}
	
	/**
	 * Cierra la 
	 * conexion a 
	 * la base de datos
	 * de Oracle
	 */
	private void cerrarConecionCM(){
		try {
			
			DAO.cerrarConexionCM();
			
		} catch (DKException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra el emnsaje
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}
	
	
	
}
