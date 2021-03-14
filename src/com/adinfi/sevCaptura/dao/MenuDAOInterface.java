package com.adinfi.sevCaptura.dao;


import java.sql.SQLException;


public interface MenuDAOInterface {
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	boolean validaConexionDB2() throws SQLException;
	

	public void cerrarConexionDB2() throws SQLException;
	
	public boolean lotesPendientes(String idusuario,int tipoFact)throws SQLException;
	
	public int consultaFacturasPendientesCaptura(String lote, String usuario)
			throws SQLException;

}
