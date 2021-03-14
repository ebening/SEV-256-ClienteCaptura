package com.adinfi.sevCaptura.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.adinfi.sevCaptura.entities.Area;
import com.adinfi.sevCaptura.entities.Concepto;
import com.adinfi.sevCaptura.entities.Destinatario;
import com.adinfi.sevCaptura.entities.Empresa;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Plaza;
import com.adinfi.sevCaptura.entities.Proveedor;
import com.adinfi.sevCaptura.entities.ReporteCarga;


public interface CapturaGastosDAOInterface {
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	boolean validaConexionDB2() throws SQLException;
	

	public void cerrarConexionDB2() throws SQLException;
	
	public int  insertarDocumento(Factura factura) throws SQLException;
	
	public ArrayList<Area> getListaAreas() throws SQLException;
	
	public ArrayList<Empresa> getListaEmpresas() throws SQLException;
	
	public ArrayList<Plaza> getListaPlazas() throws SQLException;
	
	public ArrayList<Destinatario> getListaDestinatarios(int areaId) throws SQLException;
	
	public ArrayList<Concepto> getListaConceptos(int areaId) throws SQLException;
	
	public boolean  insertarStatusFactura(Factura factura) throws SQLException;

	public int actualizarFacturaDB2(Factura factura)throws SQLException;
	
	public int actualizaStatus(Factura factura,int status)throws SQLException;
	
	public int actualizaStatusSoporte(int idFact,int status) throws SQLException;
	
	public int actualizaURL(int idFact,String url)throws SQLException;
	
	public Connection getConnectionDB2();
	
	public ArrayList<Factura> getListaFacturasPorLote(String lote, int indInf, int indSup,String idUsuario)throws SQLException;
	
	public ArrayList<Factura> getListaFacturasSoportePorLote(String lote, int idFact,String idUsuario)throws SQLException;
	
	public Factura getAllFactura(Factura factura) throws SQLException;
	
	public int getStatusFactura(int idFactura)throws SQLException;
	
	public int ConsultaExisteLote(String lote,String idUsuario) throws SQLException;
	
	public ArrayList <String>  consultaDatosProveedor()throws SQLException;
	
	public int signarDocumentoAFactura(String noFact,String noProv,int Soporte,String nombreDoc, int idFact ,String tipoFactura)throws SQLException; 
	
	public int consultaFacturasPorloteSinEnviarACM(String lote)throws SQLException;
	
	public int consultaAllFacturasPorlote(String lote)throws SQLException ;
	
	public int consultaAllFacturasListasCM(String lote,String usuario)
			throws SQLException ;
	
	public ArrayList<Factura> getFacturasLoteUsuario(String lote, String usuario)
			throws SQLException;


	public int consultaFacturasPendientesCaptura(String lote, String usuario)	throws SQLException;

	public ArrayList<Proveedor> consultaDatosProveedor(String noProveedor) throws SQLException;


	public String  getNombreSoporte(int idFactura) throws SQLException;


	public void eliminarRegistro(Factura factura) throws SQLException;
	
	public boolean borrarStatusCaptura(int idFactura) throws SQLException;

	public boolean insertarLoteReporteCarga(ReporteCarga reporte) throws SQLException;


	public int ConsultaExisteLoteReporteCarga(String idUsuario, String lote,String tipoCaptura)throws SQLException;


	public Timestamp consultarFechaInicioCapturaReporteCarga(String idUsuario, String lote,String tipoCaptura)throws SQLException;


	public boolean actualizarFechaInicioCaptura(Timestamp fechaInicioCaptura,String lote,String idUsuario,String tipoCaptura)throws SQLException;


	public boolean actualizarFechaFinCaptura(Timestamp fechaFinCap,String lote, String idUsuario,String tipoCaptura)throws SQLException;
	
	public boolean selectFacturaByNumFact(Factura factura)throws SQLException;
      
}
