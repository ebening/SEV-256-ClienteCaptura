package com.adinfi.sevCaptura.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.entities.Area;
import com.adinfi.sevCaptura.entities.Concepto;
import com.adinfi.sevCaptura.entities.Destinatario;
import com.adinfi.sevCaptura.entities.Empresa;
import com.adinfi.sevCaptura.entities.Estado;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Plaza;
import com.adinfi.sevCaptura.entities.Proveedor;
import com.adinfi.sevCaptura.entities.ReporteCarga;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.resources.Utilities;

public class CapturaMercanciaDAO implements CapturaMercanciaDAOInterface {
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
	
	public Connection getConnectionDB2() {
		return connManagerDB2.getConnectionDB2();
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
	public int ConsultaExisteLote(String lote,String idUsuario) throws SQLException {
		int existeLote = 0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.COUNT_FACTURAS_POR_LOTE);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, idUsuario);
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				existeLote = result.getInt(1);
			}
		}
		this.closeQuery();
		return existeLote;
	}
	@Override
	public int  actualizarFacturaDB2(Factura factura) throws SQLException {
		int actualizo=0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.UPDATE_FACTURA);
		preparedStat.setString(1, factura.getEmpresa().getNombre());
		
		if(factura.getPlaza() != null){
			preparedStat.setString(2, factura.getPlaza());
		}else{
			preparedStat.setNull(2, java.sql.Types.VARCHAR);
		}

		preparedStat.setString(3, factura.getUsuario().getIdUsuario());
		preparedStat.setString(4, factura.getNoFact());
		preparedStat.setString(5, factura.getProveedor().getNumero());
		preparedStat.setString(6, factura.getProveedor().getNombre());
		preparedStat.setString(7, factura.getProveedor().getRfc());
		preparedStat.setString(8, factura.getProveedor().getSucursal());
		
		/**
		 * @author Adan
		 * Si plaza == null se inserta null.
		 */
		if(factura.getFechaFactura()==null){
			preparedStat.setDate(9,null);
		}else{
			preparedStat.setDate(9, java.sql.Date.valueOf(getStringDateSql(factura.getFechaFactura())));
		}
		
		
		BigDecimal monto=Utilities.convertToBigDecimal(factura.getMonto());
		preparedStat.setBigDecimal(10, monto);
		
		preparedStat.setString(11, factura.getConcepto().getDescripcion());

		if(factura.getFechaRecepcion()==null){
			preparedStat.setDate(12,null);
		}else{
			preparedStat.setDate(12, java.sql.Date.valueOf(getStringDateSql(factura.getFechaRecepcion())));
		}
	
		preparedStat.setString(13,factura.getTipoDoc());
		preparedStat.setInt(14, factura.getSoporte());
		preparedStat.setInt(15, factura.getId());
		
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}
	
	public  String getStringDateSql(java.util.Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		String date = calendar.get(Calendar.YEAR)+"-"+completeData((calendar.get(Calendar.MONTH)+1))+"-"+completeData(calendar.get(Calendar.DAY_OF_MONTH));
		return date;
	}
	private String completeData(int number) {
		if(number < 10){
			return "0"+number;
		}
		return ""+number;
	}
	
	@Override
	public ArrayList<Factura> getListaFacturasPorLote(String lote, int indInf, int indSup,String idUsuario)throws SQLException {
		
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.SELECT_FACTUTAS_LOTE);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, idUsuario);
		preparedStat.setInt(3, indInf);
		preparedStat.setInt(4, indSup);
		result = preparedStat.executeQuery();
		
		while (result.next()){
			Factura factura = new Factura();
			Estado estado = new Estado();
			Concepto concepto=new Concepto();
			concepto.setDescripcion(result.getString("FRECEPCION"));// folio recepcion
			estado.setId(result.getInt("IDSTATUS"));
			factura.setId(result.getInt("IDFACTURA"));
			factura.setConcepto(concepto);
			factura.setEstado(estado);
			factura.setUrl(result.getString("URL"));
			listaFacturas.add(factura);
		}
		
		this.closeQuery();
		return listaFacturas;
	}

	@Override
	public int insertarDocumento(Factura factura) throws SQLException {
		int idFact=0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.INSERT_DOCUMENTO);
		preparedStat.setString(1, factura.getUrl()); 
		preparedStat.setString(2, factura.getUsuario().getIdUsuario());
		preparedStat.setInt(3, factura.getSoporte());
		preparedStat.setString(4, factura.getTipoDoc());
		String fecha=Utilities.nowDate();
		String fecheFormat=Utilities.convertDateFormat(fecha);
		Date date=Utilities.parserSqlDate(fecheFormat);
		preparedStat.setDate(5, date);
		preparedStat.setString(6, factura.getPlaza());
		preparedStat.setString(7, factura.getLote());
		
		
		result = preparedStat.executeQuery();
		
		if(result.next()){
			idFact = result.getInt(1);
		}
		
	    this.closeQuery();
	    return idFact;
	}

	@Override
	public boolean insertarStatusFactura(Factura factura) throws SQLException {
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.INSERT_ESTATUS_FACTURA);
		preparedStat.setInt(1, factura.getId());
		preparedStat.setInt(2, factura.getEstado().getId());
		preparedStat.setTimestamp(3,timesTamp);
		preparedStat.execute();
		closeQuery();
		return true;
	}

	@Override
	public int getStatusFactura(int idFactura) throws SQLException {
		int status=0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.SELECT_STATUS_FACTURA);
		preparedStat.setInt(1, idFactura);
		result = preparedStat.executeQuery();
		if(result!=null){
			while (result.next()){
				status=result.getInt(1);
			}	
		}
		
		
		this.closeQuery();
		return status;
	}

	@Override
	public Factura getAllFactura(Factura factura) throws SQLException {
		Factura facturaCopia= new Factura();
		Proveedor proveedor= new Proveedor();
		Empresa empresa= new Empresa();
		Concepto concepto =new Concepto();//folio recepcion
		//Destinatario destinatario= new Destinatario();// se tiene q traer de la otra tabla
		
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.SELECT_ALL_FACTURAS);
		preparedStat.setInt(1,factura.getId());
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				
				facturaCopia.setId(result.getInt("IDFACTURA"));
				facturaCopia.setNoFact(result.getString("NOFACTURA"));
				empresa.setNombre(result.getString("EMPRESA"));
				facturaCopia.setEmpresa(empresa);
				proveedor.setNumero(result.getString("NOPROVEEDOR"));
				proveedor.setNombre(result.getString("NOMPROVEEDOR"));
				facturaCopia.setFechaFactura(result.getDate("FECHAFACTURA"));
				proveedor.setRfc(result.getString("RFCPROVEEDOR"));
				proveedor.setSucursal(result.getString("SUCPROVEEDOR"));
				facturaCopia.setProveedor(proveedor);
				facturaCopia.setPlaza(result.getString("PLAZA"));
				facturaCopia.setFechaRecepcion(result.getDate("FECHARECEPCION"));
				concepto.setDescripcion(result.getString("CONCEPTOFACTURA"));
				facturaCopia.setConcepto(concepto);
				BigDecimal monto=result.getBigDecimal("MONTOFACTURA");
			
				if(monto==null){
					factura.setMonto("");
				}else{
				facturaCopia.setTipoDocumento(result.getString("TIPOFACTURA"));
				facturaCopia.setMonto(monto.toPlainString());}
				facturaCopia.setUrl(result.getString("URL"));
				
				concepto.setDescripcion(result.getString("FRECEPCION"));/*folio recepcion*/
				facturaCopia.setConcepto(concepto);
			}
		}
		this.closeQuery();
		return facturaCopia;
	}
	
	@Override
	public int actualizaURL(int idFact, String url) throws SQLException {
		int actualizo=0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.UPDATE_URL);
		preparedStat.setString(1, url);
		preparedStat.setInt(2,idFact);
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}
	
	@Override
	public int actualizaStatus(Factura factura, int status) throws SQLException {
		int actualizo=0;
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
		
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.UPDATE_STATUS);
		preparedStat.setInt(1, status);
		preparedStat.setTimestamp(2,timesTamp);
		preparedStat.setInt(3,factura.getId());
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}

	@Override
	public int consultaAllFacturasListasCM(String lote, String usuario)
			throws SQLException {
		int existeLote = 0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.COUNT_FACTURAS_POR_LOTE_CAPTURADAS);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		result = preparedStat.executeQuery();
		if(result != null){
			while(result.next()){
				existeLote = result.getInt(1);
				
			}
		}
		this.closeQuery();
		return existeLote;
	}
	
	@Override
	public ArrayList<Factura> getFacturasLoteUsuario(String lote, String usuario)
			throws SQLException {
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.SELECT_FACTUTAS_LOTE_USUARIO_CAPTURADAS);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		
		result = preparedStat.executeQuery();
		
		while (result.next()){
			Factura factura = new Factura();
			Area area= new Area();
			Empresa empresa= new Empresa();
			Destinatario destinatario= new Destinatario();
			Concepto concepto =new Concepto();
			Proveedor proveedor= new Proveedor();
			Usuario usuarioCaptura= new Usuario();
			
			factura.setId(result.getInt("IDFACTURA"));
			factura.setNoFact(result.getString("NOFACTURA"));
			
			empresa.setNombre(result.getString("EMPRESA"));
			factura.setEmpresa(empresa);
			
			area.setId(result.getInt("IDAREA"));
			factura.setArea(area);
			
		
		
			proveedor.setNumero(result.getString("NOPROVEEDOR"));
			proveedor.setNombre(result.getString("NOMPROVEEDOR"));
			factura.setFechaFactura(result.getDate("FECHAFACTURA"));
			proveedor.setRfc(result.getString("RFCPROVEEDOR"));
			proveedor.setSucursal(result.getString("SUCPROVEEDOR"));
			factura.setProveedor(proveedor);
			factura.setPlaza(result.getString("PLAZA"));
			factura.setFechaRecepcion(result.getDate("FECHARECEPCION"));
			BigDecimal monto=result.getBigDecimal("MONTOFACTURA");
			
			if(monto==null){
				factura.setMonto("");
			}else{
				factura.setMonto(monto.toPlainString());}
		
			factura.setOrdenCompra(result.getString("ORDENCOMPRA"));
			factura.setNoSaf(result.getString("NOSAF"));
			factura.setMoneda(result.getString("MONEDAFACTURA"));	
			
			
			destinatario.setIdUsuario(result.getString("ID_DESTINATARIO"));
			factura.setDestinatario(destinatario);
			
			concepto.setDescripcion(result.getString("CONCEPTOFACTURA"));
			factura.setConcepto(concepto);	
			
			factura.setTipoDocumento(result.getString("TIPOFACTURA"));
			factura.setUrl(result.getString("URL"));
			factura.setLote(result.getString("LOTE"));
			
			usuarioCaptura.setIdUsuario(result.getString("IDUSUARIO"));
			factura.setUsuario(usuarioCaptura);
			
			listaFacturas.add(factura);
		}
		this.closeQuery();
		return listaFacturas;
	}
	public ArrayList<Empresa> getListaEmpresas() throws SQLException {
		ArrayList<Empresa> empresas=new ArrayList<Empresa>();
		Empresa empresa;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_EMPRESAS);
		result=preparedStat.executeQuery();
		if(result!=null){
		while(result.next()) 
		{ 
			empresa =new Empresa(result.getString(1));
			empresas.add(empresa);
			
		}
		}
		closeQuery();
		return empresas;
	}

	@Override
	public int consultaFacturasPendientesCaptura(String lote, String usuario)
			throws SQLException {
		int facturas = 0;
		preparedStat = connDB2.prepareStatement(CapturaMercanciaQuery.COUNT_FACTURAS_PENDIENTES_CAPTURA);
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

	@Override
	public ArrayList<Proveedor> consultaDatosProveedor(String noProveedor) throws SQLException {
		ArrayList<Proveedor> proveedorArray= new ArrayList<Proveedor>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_PROVEEDOR);
		preparedStat.setString(1, noProveedor);
		result=preparedStat.executeQuery();
		if(result!=null){
			while(result.next()) {
				Proveedor proveedor= new Proveedor();
				proveedor.setNumero(result.getString("NUMERO_PROVEEDOR"));		
				proveedor.setNombre(result.getString("NOMBRE_PROVEEDOR"));
				proveedor.setRfc(result.getString("RFC"));
				proveedor.setSucursal(result.getString("SUCURSAL"));	
				proveedor.setCorreo(result.getString("CORREO_PROVEEDOR"));
				proveedorArray.add(proveedor);
				}
		}
		closeQuery();
		return proveedorArray;
	}
	
	@Override
	public void eliminarRegistro(Factura factura) throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.DELETE_FACTURA_CAPTURA);
		preparedStat.setInt(1, factura.getId());		
		preparedStat.execute();
		this.closeQuery();

	}
	
	@Override
	public boolean borrarStatusCaptura(int idFactura) throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.DELETE_STATUS_FACTURA);
		preparedStat.setInt(1,idFactura );
		preparedStat.execute();
		closeQuery();
		return true;
	}

	@Override
	public int ConsultaExisteLoteReporteCarga(String idUsuario, String lote, String tipoCaptura) throws SQLException{
		int existeReporte = 0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_LOTE_REPORTE_CARGA);
		preparedStat.setString(1, idUsuario);
		preparedStat.setString(2, lote);
		preparedStat.setString(3, tipoCaptura );
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				existeReporte = result.getInt(1);
				
			}
		}
		this.closeQuery();
		return existeReporte;
	}
	
	@Override
	public boolean insertarLoteReporteCarga(ReporteCarga reporte)
			throws SQLException {
		
		String formatoFecha=Utilities.convertDateFormat(reporte.getFechaCaptura());
		Date date =Utilities.parserSqlDate(formatoFecha);
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_NEW_LOTE_REPORTE);
		preparedStat.setString(1, reporte.getIdUsuario());
		preparedStat.setString(2, reporte.getLote());
		preparedStat.setDate(3, date);
		preparedStat.setString(4, reporte.getTipoCaptura());
		preparedStat.setInt(5, Integer.parseInt(reporte.getDocumentosProcesados()));
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
	}
	
	@Override
	public Timestamp consultarFechaInicioCapturaReporteCarga(String idUsuario,
			String lote,String tipoCaptura) throws SQLException  {
		Timestamp fechaInicioCaptura=null;
		
			preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.GET_FECHA_INICIO_CAPTURA);
			preparedStat.setString(1, idUsuario);
			preparedStat.setString(2, lote);
			preparedStat.setString(3, tipoCaptura);
			result = preparedStat.executeQuery();
			
			if(result != null){
				while(result.next()){
					fechaInicioCaptura = result.getTimestamp(1);
				}
			}
			this.closeQuery();
		return fechaInicioCaptura;
	}
	
	@Override
	public boolean actualizarFechaInicioCaptura(Timestamp fechaInicioCaptura,String lote,String idUsuario,String tipoCaptura)
			throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.ACTUALIZAR_FECHA_INICIO_CAPTURA);
		preparedStat.setTimestamp(1,fechaInicioCaptura);
		preparedStat.setString(2, idUsuario);
		preparedStat.setString(3, lote);
		preparedStat.setString(4, tipoCaptura);
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
	}
	
	@Override
	public boolean actualizarFechaFinCaptura(Timestamp fechaFinCap,String lote, String idUsuario,String tipoCaptura) throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.ACTUALIZAR_FECHA_FIN_CAPTURA);
		preparedStat.setTimestamp(1,fechaFinCap);
		preparedStat.setString(2, idUsuario);
		preparedStat.setString(3, lote);
		preparedStat.setString(4, tipoCaptura);
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
	}

	@Override
	public ArrayList<Plaza> getListaPlazas() throws SQLException {
		ArrayList<Plaza> plazas = new ArrayList<Plaza>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_PLAZAS);
		result	=	preparedStat.executeQuery();

		while(result.next()) { 
			Plaza plaza = new Plaza(result.getInt(1),result.getString(2));
			plazas.add(plaza);
		}

		this.closeQuery();
		return plazas;
	}
	


}
