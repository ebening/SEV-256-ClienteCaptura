package com.adinfi.sevCaptura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.resources.Constants;

public class MenuDAO implements MenuDAOInterface {
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
			return true;
		}

		return false;
	}
	@Override
	public boolean validaConexionDB2() throws SQLException {
		boolean conexionAbierta = false;
		conexionAbierta = this.connManagerDB2.validaConexionDB2();
		return conexionAbierta;
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
	public boolean lotesPendientes(String idUsuario,int tipoFact) throws SQLException {
		boolean lotesPendientes=false;
		
		if(tipoFact==Constants.TIPO_FACTURA_GASTO){
			preparedStat = connDB2.prepareStatement(MenuDAOQuery.COUNT_LOTES_PENDIENTES_GASTOS);
		}else if(tipoFact==Constants.TIPO_FACTURA_MERCANCIA){
			preparedStat = connDB2.prepareStatement(MenuDAOQuery.COUNT_LOTES_PENDIENTES_MERCANCIA);
		}

		preparedStat.setString(1, idUsuario);
		result = preparedStat.executeQuery();
		if(result!=null){
			while(result.next()){
			lotesPendientes=true;
			}
		}
	
		closeQuery();	
		return lotesPendientes;
	}
	
	
	
	@Override
	public int consultaFacturasPendientesCaptura(String lote, String usuario)
			throws SQLException {
		int facturas = 0;
		preparedStat = connDB2.prepareStatement(MenuDAOQuery.COUNT_FACTURAS_PENDIENTES_CAPTURA);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		result = preparedStat.executeQuery();
		
		
		if(result != null){
			while(result.next()){
				facturas = result.getInt(1);

			}
		}
		
		this.closeQuery();
		return facturas;
	}

}
