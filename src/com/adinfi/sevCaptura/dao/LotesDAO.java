package com.adinfi.sevCaptura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.resources.Constants;

public class LotesDAO implements LotesDAOInterface {
	private ConnectionManagerDB2 connManagerDB2 = null;
	private Connection connDB2 = null;
	private PreparedStatement preparedStat = null;
	private ResultSet result = null;

	@Override
	public boolean establecerConexionDB2() throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
			
		this.connManagerDB2 = new ConnectionManagerDB2();
				if(connManagerDB2.connectDB2()){
					this.connDB2 = connManagerDB2.getConnectionDB2();
					return true;}
				return false;
	}

	@Override
	public boolean validaConexionDB2() throws SQLException {
		boolean conexionAbierta = false;
		conexionAbierta = this.connManagerDB2.validaConexionDB2();
		return conexionAbierta;
	}

	@Override
	public Connection getConnectionDB2() {
		return connManagerDB2.getConnectionDB2();
	}

	@Override
	public void cerrarConexionDB2() throws SQLException {
		connManagerDB2.disconectDB2();
		
	}
	private void closeQuery() throws SQLException{
		if(preparedStat != null){
			preparedStat.close();
			preparedStat = null;
		}
		
		if(result != null){
			result.close();
			result = null;
		}
	}

	@Override
	public ArrayList<String> getLotesPendientes(String idUsuario,int tipoFat) throws SQLException {
		ArrayList<String> listaLotes = new ArrayList<String>();
		if(tipoFat==Constants.TIPO_FACTURA_GASTO){
			System.out.println(LotesDAOQuery.SELECT_LOTES_PENDIENTES);
			preparedStat = connDB2.prepareStatement(LotesDAOQuery.SELECT_LOTES_PENDIENTES);
		}else if(tipoFat==Constants.TIPO_FACTURA_MERCANCIA){
			System.out.println(LotesDAOQuery.LOTES_PENDIENTES_MERCANCIA);
			preparedStat = connDB2.prepareStatement(LotesDAOQuery.LOTES_PENDIENTES_MERCANCIA);
		}
		
		preparedStat.setString(1, idUsuario);
		result=preparedStat.executeQuery();
		
		while(result.next()){
			String lote = result.getString("LOTE");

			if(lote != null && !lote.equals("null")){
				listaLotes.add(lote);
			}
		}
		closeQuery();
		return listaLotes;
	}
	


	@Override
	public int getNumFacturasPendientesPorLote(String lote,String idUsuario, int tipoFact) throws SQLException {
		int totalDocs = 0;
		if(tipoFact==Constants.TIPO_FACTURA_GASTO){
			preparedStat = connDB2.prepareStatement(LotesDAOQuery.COUNT_FACTURAS_POR_LOTE);
		}else if(tipoFact==Constants.TIPO_FACTURA_MERCANCIA){
			preparedStat = connDB2.prepareStatement(LotesDAOQuery.COUNT_FACTURAS_POR_LOTE_MERCANCIA);
		}
	
		preparedStat.setString(1, lote);
		preparedStat.setString(2, idUsuario);
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				totalDocs = result.getInt(1);
			}
		}
		
		this.closeQuery();
		return totalDocs;
	}

	

}
