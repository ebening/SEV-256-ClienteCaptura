package com.adinfi.sevCaptura.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.adinfi.sevCaptura.entities.Atributo;
import com.adinfi.sevCaptura.entities.Documento;
import com.adinfi.sevCaptura.entities.Factura;
import com.ibm.mm.sdk.common.DKException;

public interface CargaMasivaMercanciaDAOInterface {
	/**
	 * Método que abre la conexión a CM utilizando
	 * el usuario y password ingresados
	 * @param usuario - Nombre de usuario.
	 * @param password - Password del usuario.
	 * @return true - Si se estableció la conexión.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean establecerConexionCM(String usuario, String password)throws DKException, Exception;
	
	/**
	 * Método que abre la conexión a CM.
	 * @return true - Si se estableció la conexión.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean establecerConexionCM()throws DKException, Exception;
	
	/**
	 * Método que cierra la conexión a CM.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public void cerrarConexionCM()throws DKException, Exception;
	
	/**
	 * Método que verifica si existe 
	 * una conexión a CM y esta es valida..
	 * @return true - Si existe una conexión.
	 * @throws Exception 
	 * @throws DKException 
	 */
	public boolean validaConexionCM() throws DKException, Exception;
	
	public int consultaAllFacturasPorlote(String lote,String usuario)
			throws SQLException ;
	
	public int consultaFacturasPorloteSinEnviarACM(String lote,String usuario)
			throws SQLException;
	
	public int consultaFactEnviadasACM(String lote,String usuario)throws SQLException;
	
	
	public boolean establecerConexionDB2() 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	

	boolean validaConexionDB2() throws SQLException;
	

	public void cerrarConexionDB2() throws SQLException;
	
	public ArrayList<Factura> getFacturasLoteUsuario(String lote,String usuario)
			throws SQLException ;
	
	
	
	

	public boolean insertarStatusFactura(int idFactura,int idMovimiento,int status, String idDestinatario)throws SQLException;
	public boolean borrarStatusCaptura(int idFactura) throws SQLException;
	
	public boolean borrarFacturaCaptura(int idFactura) throws SQLException;
	

	public ArrayList<Factura> getFacturasSoporteLoteUsuario(String lote,String usuario, String noFactura, String noProveedor)
			throws SQLException;
	
	public int actualizaStatus(Factura factura, int status) throws SQLException;

	public ArrayList<Atributo> getAtributosListByItemType(String itemType)
			throws DKException, Exception;
	
	public boolean importaDocumento(Documento item) throws DKException, Exception;

	public String getNewPid();

	public int getNumeroDocumentosProcesado(Factura factura, String tipoCaptura)throws SQLException;

	public boolean actualizaDocumentosprocesados(int numDocActual,Factura factura, String tipoCaptura)throws SQLException;
	



}
