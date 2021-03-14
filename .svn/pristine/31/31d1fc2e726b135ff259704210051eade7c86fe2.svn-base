package com.adinfi.sevCaptura.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;

/**
 * Clase que contiene los metodos requeridos para establecer y cerrar una
 * conexión con DB2.
 * 
 * @author Adanm.
 * @version 1.0, 23-02-2012.
 * 
 *          Se agrego Pool de Conexiones
 * @author guadalupec.
 * @version 1.1, 30-08-2012.
 * 
 */
public class ConnectionManagerDB2 {

	public static Logger logger = Logger.getLogger(ConnectionManagerDB2.class);
	private Connection dataSourceDB2 = null;

	// private static BoneCP connectionPool = null;

	/**
	 * @return Objeto de conexión a DB2.
	 */
	public Connection getConnectionDB2() {
		return dataSourceDB2;
	}
	
	

	/**
	 * Este método genera una conexión a la base de datos de DB2.
	 * 
	 * @return true - Si la conexión fue exitosa.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	
	public boolean connectDB2() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean connectionActive = false;

		String jdbcDriver = "com.ibm.db2.jcc.DB2Driver";
		String connectionURL = BundleManager.getValue(Constants.CONECTION_BUNDLE, "db2.url");
		String connectionUser = BundleManager.getValue(Constants.CONECTION_BUNDLE,"db2.username");
		String connectionPassword = BundleManager.getValue(Constants.CONECTION_BUNDLE,"db2.password");
	
		Class.forName(jdbcDriver).newInstance();
		dataSourceDB2 = DriverManager.getConnection(connectionURL,connectionUser, connectionPassword);
		connectionActive = true;
		logger.info("Inicia conexión a DB2.");
			
		return connectionActive;
	}

	/**
	 * Método que valida la conexión con DB2.
	 * 
	 * @return true - Si la conexion es valida.
	 * @throws SQLException
	 */
	public boolean validaConexionDB2() throws SQLException {
		boolean conexionAbierta = false;

		if (dataSourceDB2 != null && !dataSourceDB2.isClosed()) {
			/*
			 * if( dataSourceDB2.isValid(5)){ conexionAbierta = true; }
			 */
			// conexionAbierta = dataSourceDB2.isValid(5);
			String query = "SELECT COUNT(*) FROM " + Constants.ESQUEMA_DB2 + "ESTADOS";
			PreparedStatement prep = this.dataSourceDB2.prepareStatement(query);
			ResultSet res = prep.executeQuery();

			if (res != null) {
				while (res.next()) {
					if (res.getInt(1) > 0) {
						conexionAbierta = true;
					}
				}
			}

			res.close();
			prep.close();
		}

		return conexionAbierta;
	}

	/**
	 * Desconecta la conexión de de DB2.
	 * 
	 * @throws SQLException
	 */
	public void disconectDB2() throws SQLException {
		if (dataSourceDB2 != null) {
			dataSourceDB2.close();
			dataSourceDB2 = null;
			logger.info("Cerrar Conexion a DB2");

		}
	}
	
	
	public static void main(String[] args) {
		ConnectionManagerDB2 test = new ConnectionManagerDB2();
		
		
			try {
				if(test.connectDB2()){
					test.disconectDB2();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}