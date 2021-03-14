package com.adinfi.sevCaptura.dao;

import java.sql.SQLException;

import com.adinfi.sevCaptura.entities.Usuario;
import com.ibm.mm.sdk.common.DKException;
public interface LoginDAOInterface {

	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	boolean validaConexionDB2() throws SQLException;
	
	public void cerrarConexionDB2() throws SQLException;

	public Usuario validaUsuario(String idUsuario, String password) throws SQLException;
	
	public boolean extraerListasAcceso(Usuario usuario) throws SQLException;
	
	public boolean establecerConexionCM()throws DKException, Exception;
	
	public void cerrarConexionCM()throws DKException, Exception;
	
	public boolean validaConexionCM() throws DKException, Exception;
	
	
}
