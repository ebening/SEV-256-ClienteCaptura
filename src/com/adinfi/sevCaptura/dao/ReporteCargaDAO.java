package com.adinfi.sevCaptura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.entities.FiltroReporteCarga;
import com.adinfi.sevCaptura.entities.ReporteCarga;

public class ReporteCargaDAO implements ReporteCargaDAOInterface {
	private ConnectionManagerDB2 connManagerDB2 = null;
	private Connection connDB2 = null;
	private PreparedStatement preparedStat = null;
	private ResultSet result = null;

	@Override
	public boolean establecerConexionDB2() throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		this.connManagerDB2 = new ConnectionManagerDB2();
		if (connManagerDB2.connectDB2()) {
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
	public Connection getConnectionDB2() {
		return connManagerDB2.getConnectionDB2();
	}

	@Override
	public void cerrarConexionDB2() throws SQLException {
		connManagerDB2.disconectDB2();

	}

	private void closeQuery() throws SQLException {
		if (preparedStat != null) {
			preparedStat.close();
			preparedStat = null;
		}

		if (result != null) {
			result.close();
			result = null;
		}
	}

	@Override
	public ArrayList<ReporteCarga> getReporteCargaIdUsuario(String idUsuario)
			throws SQLException {
		ArrayList<ReporteCarga> resultadoLista = new ArrayList<ReporteCarga>();
		preparedStat = connDB2
				.prepareStatement(ReporteCargaDAOQuery.SELECT_REPORTE_CARGA_IDUSUARIO);
		preparedStat.setString(1, idUsuario);

		result = preparedStat.executeQuery();

		while (result.next()) {
			ReporteCarga reporteCarga = new ReporteCarga();
			reporteCarga.setLote(result.getString("LOTE"));
			reporteCarga.setFechaCaptura(result.getString("FECHACAPTURA"));
			reporteCarga.setTipoCaptura(result.getString("TIPOCAPTURA"));
			reporteCarga.setDocumentosProcesados(result
					.getString("DOCUMENTOSPROCESADOS"));
			reporteCarga.setHoraInicioCaptura(result
					.getString("FECHAINICIOCAPTURA"));
			reporteCarga.setFinCaptura(result.getString("FECHAFINCAPTURA"));
			resultadoLista.add(reporteCarga);
		}

		closeQuery();

		return resultadoLista;
	}

	@Override
	public ArrayList<ReporteCarga> getReporteCargaIdUsuario(String idUsuario,
			FiltroReporteCarga filtro) throws SQLException {
		ArrayList<ReporteCarga> resultadoLista = new ArrayList<ReporteCarga>();

		String query = crearQueryDinamico(idUsuario, filtro);
		Statement statement = connDB2.createStatement();
		result = statement.executeQuery(query);

		while (result.next()) {
			ReporteCarga reporteCarga = new ReporteCarga();
			reporteCarga.setLote(result.getString("LOTE"));
			reporteCarga.setFechaCaptura(result.getString("FECHACAPTURA"));
			reporteCarga.setTipoCaptura(result.getString("TIPOCAPTURA"));
			reporteCarga.setDocumentosProcesados(result
					.getString("DOCUMENTOSPROCESADOS"));
			reporteCarga.setHoraInicioCaptura(result
					.getString("FECHAINICIOCAPTURA"));
			reporteCarga.setFinCaptura(result.getString("FECHAFINCAPTURA"));
			resultadoLista.add(reporteCarga);
		}

		closeQuery();

		return resultadoLista;
	}

	public int getReporteTotalReg(String idUsuario) throws SQLException {

		int consultaReg = 0;
		preparedStat = connDB2
				.prepareStatement(ReporteCargaDAOQuery.SELECT_REPORTE_CARGA_IDUSUARIO_TOTALREG);
		preparedStat.setString(1, idUsuario);

		result = preparedStat.executeQuery();

		while (result.next()) {
			consultaReg = result.getInt("COUNTREG");
		}

		closeQuery();

		return consultaReg;

	}

	private String crearQueryDinamico(String idUsuario,
			FiltroReporteCarga filtroReporteCarga) {
		String queryResult = null;
		StringBuffer sbQuery = new StringBuffer();
		String queryInicial = ReporteCargaDAOQuery.SELECT_REPORTE_CARGA_FILTRO;
		String queryFinal = " ORDER BY FECHACAPTURA DESC ";

		sbQuery.append(queryInicial);
		sbQuery.append("'");
		sbQuery.append(idUsuario);
		sbQuery.append("'");
		sbQuery.append("AND TIPOCAPTURA=");
		sbQuery.append("'");
		sbQuery.append(filtroReporteCarga.getTipoCaptura());
		sbQuery.append("'");

		if (filtroReporteCarga != null) {

			if (filtroReporteCarga.getLote() != null) {
				sbQuery.append(" AND  LOTE = ");
				sbQuery.append("'");
				sbQuery.append(filtroReporteCarga.getLote());
				sbQuery.append("'");
			}

			if ((filtroReporteCarga.getFechaInicioCaptura() != null && !filtroReporteCarga.getFechaInicioCaptura().isEmpty())
					&& (filtroReporteCarga.getFechaFinCaptura() != null && !filtroReporteCarga.getFechaFinCaptura().isEmpty())) {

				sbQuery.append(" AND  FECHACAPTURA BETWEEN ");
				sbQuery.append("'");
				sbQuery.append(filtroReporteCarga.getFechaInicioCaptura());
				sbQuery.append("'");
				sbQuery.append(" AND ");
				sbQuery.append("'");
				sbQuery.append(filtroReporteCarga.getFechaFinCaptura());
				sbQuery.append("'");

			} else if ((filtroReporteCarga.getFechaInicioCaptura() != null && !filtroReporteCarga.getFechaInicioCaptura().isEmpty())
					&&(filtroReporteCarga.getFechaFinCaptura() == null
					|| filtroReporteCarga.getFechaFinCaptura().isEmpty())){
			
				sbQuery.append(" AND FECHACAPTURA >= ");
				sbQuery.append("'");
				sbQuery.append(filtroReporteCarga.getFechaInicioCaptura());
				sbQuery.append("'");

			} else if ((filtroReporteCarga.getFechaInicioCaptura() == null || filtroReporteCarga.getFechaInicioCaptura().isEmpty())
					&&(filtroReporteCarga.getFechaFinCaptura() != null 
					&& !filtroReporteCarga.getFechaFinCaptura().isEmpty())){		
				
				sbQuery.append(" AND  FECHACAPTURA <= ");
				sbQuery.append("'");
				sbQuery.append(filtroReporteCarga.getFechaFinCaptura());
				sbQuery.append("'");

			}

		}

		sbQuery.append(queryFinal);
		
		System.out.println("QUERY REP:" + sbQuery.toString());

		queryResult = sbQuery.toString();

		return queryResult;
	}

}
