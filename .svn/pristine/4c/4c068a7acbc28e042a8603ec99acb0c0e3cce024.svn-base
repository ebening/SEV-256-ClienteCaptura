package com.adinfi.sevCaptura.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.adinfi.sevCaptura.entities.Empresa;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Plaza;
import com.adinfi.sevCaptura.entities.Proveedor;
import com.adinfi.sevCaptura.entities.ReporteCarga;

public interface CapturaMercanciaDAOInterface {
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	boolean validaConexionDB2() throws SQLException;
	

	public void cerrarConexionDB2() throws SQLException;
	
	public int ConsultaExisteLote(String lote,String idUsuario) throws SQLException;
	
	public ArrayList<Factura> getListaFacturasPorLote(String lote, int indInf, int indSup,String idUsuario)throws SQLException;
	
	public int  insertarDocumento(Factura factura) throws SQLException;
	
	public boolean  insertarStatusFactura(Factura factura) throws SQLException;
	
	public int getStatusFactura(int idFactura) throws SQLException;
	
	public Factura getAllFactura(Factura factura) throws SQLException;
	
	public int actualizaURL(int idFact, String url) throws SQLException;
	
	public int actualizaStatus(Factura factura, int status) throws SQLException;
	
	public int  actualizarFacturaDB2(Factura factura) throws SQLException;
	
	public int consultaAllFacturasListasCM(String lote,String usuario)
			throws SQLException ;
	public ArrayList<Factura> getFacturasLoteUsuario(String lote, String usuario)
			throws SQLException;


	public ArrayList<Empresa> getListaEmpresas() throws SQLException;
	
	public int consultaFacturasPendientesCaptura(String lote, String usuario)
			throws SQLException;


	public ArrayList<Proveedor> consultaDatosProveedor(String noProveedor) throws SQLException;
	
	public void eliminarRegistro(Factura factura) throws SQLException;
	
	public boolean borrarStatusCaptura(int idFactura) throws SQLException;

	public int ConsultaExisteLoteReporteCarga(String idUsuario, String lote,String reportecargaFacturaMercancia)throws SQLException;


	public boolean insertarLoteReporteCarga(ReporteCarga report)throws SQLException;


	public Timestamp consultarFechaInicioCapturaReporteCarga(String idUsuario,String lote,String tipoCaptura)throws SQLException;


	public boolean actualizarFechaInicioCaptura(Timestamp fechaInicioCap,String lote, String idUsuario, String tipoCaptura)throws SQLException;
	
	public boolean actualizarFechaFinCaptura(Timestamp fechaFinCap,String lote, String idUsuario,String tipoCaptura) throws SQLException ;

	/**
	 * @author Adan
	 * Método que consulta la base de datos de DB2 para extraer
	 * la lista de plazas de captura registradas dentro de la
	 * tabla de "PLAZASCAPTURA".
	 * @return Array con al lista de plazas existentes.
	 * @throws SQLException
	 */
	public ArrayList<Plaza> getListaPlazas() throws SQLException ;;

}
