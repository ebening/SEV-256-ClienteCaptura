package com.adinfi.sevCaptura.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
public interface LotesDAOInterface {
	
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	boolean validaConexionDB2() throws SQLException;
	
	public Connection getConnectionDB2();

	public void cerrarConexionDB2() throws SQLException;
	
	public ArrayList<String> getLotesPendientes(String idUsuario,int tipoFact)throws SQLException;

	public int getNumFacturasPendientesPorLote(String lote ,String idUsuario,int tipoFact) throws SQLException;



		
	

}
