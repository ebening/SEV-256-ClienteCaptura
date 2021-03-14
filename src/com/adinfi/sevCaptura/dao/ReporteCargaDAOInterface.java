package com.adinfi.sevCaptura.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adinfi.sevCaptura.entities.FiltroReporteCarga;
import com.adinfi.sevCaptura.entities.ReporteCarga;
public interface ReporteCargaDAOInterface {
	
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	public boolean validaConexionDB2() throws SQLException;
	
	public Connection getConnectionDB2();

	public void cerrarConexionDB2() throws SQLException;
	
	
	public ArrayList<ReporteCarga> getReporteCargaIdUsuario(String idUsuario)
			throws SQLException;
	
	public ArrayList<ReporteCarga> getReporteCargaIdUsuario(String idUsuario,FiltroReporteCarga filtro)
			throws SQLException;
	
	
	public int getReporteTotalReg(String idUsuario
			) throws SQLException ;
	

}
