package com.adinfi.sevCaptura.connections;

import org.apache.log4j.Logger;


import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.ibm.mm.sdk.common.*;
import com.ibm.mm.sdk.server.*;

/**
 * Clase que contiene los metodos requeridos para 
 * establecer y cerrar una conexion con Content Manager.
 * @author Adanm.
 * @version 1.0, 23-02-2012.
 */
public class ConnectionManagerCM {

	public static Logger logger = Logger.getLogger(ConnectionManagerCM.class);
	private DKDatastoreICM dsICM = null;
	
	/**
	 * @return El objeto de conexión con Content Manager.
	 */
	public DKDatastoreICM getConnectionCM(){
		return dsICM;
	}
	
	/**
	 * Este método genera una conexión a Content Manager.
	 * @return true - Si la conexión fue exitosa.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean connectCM() throws DKException, Exception {
		boolean conexionAbierta = false;
		
		String connectionLS = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.library");
		String connectionUser = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.admin");
		String connectionPassword = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.password");
		String connectionSchema = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.schema");

		dsICM = new DKDatastoreICM(); 
		dsICM.connect(connectionLS, connectionUser, connectionPassword, connectionSchema); 
		conexionAbierta = true;
		logger.info("Inicia conexión a Content Manager.");
		return conexionAbierta;
	}
	
	/**
	 * Crea una conexión a Content Manager tomando 
	 * el usuaro y password de los parametros de entrada.
	 * @param user - usuario para sesion.
	 * @param password -  password del usuario.
	 * @return true - si la conexión fue exitosa.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean connectCM(String user, String password) throws DKException, Exception {
		boolean conexionAbierta = false;
		
		String connectionLS = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.library");
		String connectionSchema = BundleManager.getValue(Constants.CONECTION_BUNDLE, "icm.schema");

		dsICM = new DKDatastoreICM();							
		dsICM.connect(connectionLS, user, password, connectionSchema);
		conexionAbierta = true;
		logger.info("Inicia conexión a Content Manager.");
		
		return conexionAbierta;
	}
	
	/**
	 * Método que verifica si existe 
	 * una conexión a CM y esta es valida..
	 * @return true - Si existe una conexión.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean validaConexionCM() throws DKException, Exception{
		boolean conexionValida = false;
		
		if(dsICM != null){
			if(dsICM.isConnected()){
				/* Lista de Estados
				 * 0: OK
				 * 1: connection is invalid
				 * 2: no connection exist
				 * 3: unknown (for connectors can't do validation)
				 */
				int estado = dsICM.validateConnection();
				
				if(estado==0){
					conexionValida = true;
				}
			}
		}
		
		return conexionValida;
	}
	
	/**
	 * Desconecta la conexión a Content Manager.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public void disconectCM() throws DKException, Exception{
		if(dsICM != null){
			dsICM.disconnect();
			dsICM.destroy();
			logger.info("Termina conexión a Content Manager.");
		}
	}
	
	

}
