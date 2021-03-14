package com.adinfi.sevCaptura.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.sun.org.apache.bcel.internal.generic.CPInstruction;



public class CapturaGastosDAO implements CapturaGastosDAOInterface {
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
	public int insertarDocumento(Factura factura) throws SQLException {
		int idFact=0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_DOCUMENTO);
		preparedStat.setString(1, factura.getUrl()); 
		preparedStat.setString(2, factura.getUsuario().getIdUsuario());
		preparedStat.setInt(3, factura.getSoporte());
		preparedStat.setString(4, factura.getTipoDoc());
		preparedStat.setString(5, factura.getLote());
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
	
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_ESTATUS_FACTURA);
		preparedStat.setInt(1, factura.getId());
		preparedStat.setInt(2, factura.getEstado().getId());
		preparedStat.setTimestamp(3,timesTamp);
		preparedStat.execute();
		closeQuery();
		return true;
	}

	@Override
	public ArrayList<Area> getListaAreas() throws SQLException {
		ArrayList<Area> areas=new ArrayList<Area>();
		Area area;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_AREAS);
		result=preparedStat.executeQuery();
		if(result!=null){
		while(result.next()) 
		{ 
			area =new Area(result.getInt(1),result.getString(2),result.getString(3));
			areas.add(area);
			
		}
		}
		closeQuery();
		return areas;
	}
	@Override
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
	public ArrayList<Plaza> getListaPlazas() throws SQLException {
		ArrayList<Plaza> plazas=new ArrayList<Plaza>();
		Plaza plaza;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_PLAZAS);
		result=preparedStat.executeQuery();
		if(result!=null){
		while(result.next()) 
		{ 
			plaza =new Plaza(result.getInt(1),result.getString(2));
			plazas.add(plaza);
			
		}
		}
		closeQuery();
		return plazas;
	}

	@Override
	public ArrayList<Destinatario> getListaDestinatarios(int idArea) throws SQLException {
		ArrayList<Destinatario> destinatarios=null;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_DESTINATARIOS);
		preparedStat.setInt(1, idArea);
		result=preparedStat.executeQuery();
		if(result!=null){
		while(result.next()) 
		{ 
			if(destinatarios==null){destinatarios= new ArrayList<Destinatario>();}	
				Destinatario destinatario =new Destinatario(result.getString(1),result.getString(2),result.getString(3),
											result.getString(4),result.getString(5),result.getInt(6),result.getString(7),result.getInt(8));
				destinatarios.add(destinatario);
					}
		}
		closeQuery();
		return destinatarios;	
	}
	
	@Override
	public ArrayList<Concepto> getListaConceptos(int areaId)
			throws SQLException {
		ArrayList<Concepto> conceptos=new ArrayList<Concepto>();
		Concepto concepto=null;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_CONCEPTOS);
		preparedStat.setInt(1, areaId);
		result=preparedStat.executeQuery();
		if(result!=null){
			while(result.next()) 
			{ 
				concepto= new Concepto();
				concepto.setIdConcepto(result.getInt("ID_CONCEPTO"));
				
				Area area= new Area();
				area.setId(result.getInt("ID_AREA"));
				concepto.setArea(area);
				concepto.setDescripcion(result.getString("DESC_CONCEPTO"));
				concepto.setCuenta(result.getString("NUM_CUENTA"));
				conceptos.add(concepto);

			}
		}
		closeQuery();
		return conceptos;
	}

	@Override
	public ArrayList<Factura> getListaFacturasPorLote(String lote, int indInf, int indSup,String idUsuario)throws SQLException {
		
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTUTAS_LOTE);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, idUsuario);
		preparedStat.setInt(3, indInf);
		preparedStat.setInt(4, indSup);
		result = preparedStat.executeQuery();
		
		while (result.next()){
			Factura factura = new Factura();
			Proveedor proveedor= new Proveedor();
			proveedor.setNumero(result.getString("NOPROVEEDOR"));
			
			Estado estado = new Estado();
			estado.setId(result.getInt("IDSTATUS"));
			factura.setId(result.getInt("IDFACTURA"));
			factura.setProveedor(proveedor);
			factura.setNoFact(result.getString("NOFACTURA"));
			factura.setEstado(estado);
			factura.setUrl(result.getString("URL"));
			factura.setSoporte(result.getInt("SOPORTE"));
			listaFacturas.add(factura);
		}
		
		this.closeQuery();
		return listaFacturas;
	}

	@Override
	public int getStatusFactura(int idFactura) throws SQLException {
		int status=0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_STATUS_FACTURA);
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
	public int ConsultaExisteLote(String lote, String idUsuario) throws SQLException {
		int existeLote = 0;
	
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_POR_LOTE);
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
	public ArrayList<String> consultaDatosProveedor() throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_PROVEEDOR);
		return null;
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
	public int  actualizarFacturaDB2(Factura factura) throws SQLException {
		int actualizo=0;
		int area =factura.getArea().getId();

		if(area==0){
				preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_FACTURA);
				preparedStat.setString(1, factura.getEmpresa().getNombre());
				
				/**
				 * @author Adan
				 * Si plaza == null se inserta null.
				 */
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
				
			
				if(factura.getFechaFactura()==null){
					preparedStat.setDate(9, null);
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
				preparedStat.setString(13,factura.getOrdenCompra());
				preparedStat.setString(14, factura.getNoSAF());
				preparedStat.setString(15, factura.getModeda());
				preparedStat.setString(16,factura.getTipoDoc());
				preparedStat.setInt(17, factura.getSoporte());
				preparedStat.setString(18, factura.getNombreSoporte());
				preparedStat.setString(19, factura.getMercado());
				preparedStat.setString(20, factura.getConcepto().getCuenta());
				
				if(factura.getFechaCambio()==null){
					preparedStat.setDate(21, null);
				}else{
					preparedStat.setDate(21, java.sql.Date.valueOf(getStringDateSql(factura.getFechaCambio())));
				}
				
				preparedStat.setInt(22, factura.getNumTiendas());
				
				BigDecimal montoMaximo=Utilities.convertToBigDecimal(factura.getMontoMaximo());
				preparedStat.setBigDecimal(23,montoMaximo);
				System.out.println(montoMaximo);
			
				
				preparedStat.setInt(24, factura.getId());
			
		}else{
				preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_FACTURA_AREA);
				preparedStat.setString(1, factura.getEmpresa().getNombre());
				preparedStat.setInt(2, factura.getArea().getId());
				preparedStat.setString(3, factura.getPlaza());
				
				preparedStat.setString(4, factura.getUsuario().getIdUsuario());
				preparedStat.setString(5, factura.getNoFact());
				preparedStat.setString(6, factura.getProveedor().getNumero());
				preparedStat.setString(7, factura.getProveedor().getNombre());
				preparedStat.setString(8, factura.getProveedor().getRfc());
				preparedStat.setString(9, factura.getProveedor().getSucursal());
		
				if(factura.getFechaFactura()==null){
					preparedStat.setDate(10, null);
				}else{
					preparedStat.setDate(10, java.sql.Date.valueOf(getStringDateSql(factura.getFechaFactura())));
				}
				
				
				BigDecimal monto=Utilities.convertToBigDecimal(factura.getMonto());
				
				preparedStat.setBigDecimal(11, monto);
				preparedStat.setString(12, factura.getConcepto().getDescripcion());
				
		
				if(factura.getFechaRecepcion()==null){
					preparedStat.setDate(13,null);
				}else{
					preparedStat.setDate(13, java.sql.Date.valueOf(getStringDateSql(factura.getFechaRecepcion())));
				}
				preparedStat.setString(14,factura.getOrdenCompra());
				preparedStat.setString(15, factura.getNoSAF());
				preparedStat.setString(16, factura.getModeda());
				preparedStat.setString(17,factura.getTipoDoc());
				preparedStat.setInt(18, factura.getSoporte());
				preparedStat.setString(19, factura.getNombreSoporte());
				preparedStat.setString(20, factura.getMercado());
				preparedStat.setString(21,factura.getConcepto().getCuenta());
				
				
				
				if(factura.getFechaCambio()==null){
					preparedStat.setDate(22, null);
				}else{
					preparedStat.setDate(22, java.sql.Date.valueOf(getStringDateSql(factura.getFechaCambio())));
				}
				
				preparedStat.setInt(23, factura.getNumTiendas());
				
				BigDecimal montoMaximo=Utilities.convertToBigDecimal(factura.getMontoMaximo());
				preparedStat.setBigDecimal(24,montoMaximo);
				
				preparedStat.setInt(25, factura.getId());
			}	
			actualizo=preparedStat.executeUpdate();
			this.closeQuery();
			return actualizo;
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
	public int actualizaStatus(Factura factura, int status) throws SQLException {
		int actualizo=0;
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
	
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_STATUS);
		preparedStat.setInt(1, status);
		preparedStat.setTimestamp(2,timesTamp);
		
		preparedStat.setString(3, factura.getDestinatario().getIdUsuario());
		preparedStat.setInt(4,factura.getId());
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}
	@Override
	public int actualizaStatusSoporte(int idFact,int status) throws SQLException {
		int actualizo=0;
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_STATUS_SOPORTE);
		preparedStat.setInt(1, status);
		preparedStat.setTimestamp(2,timesTamp);
		preparedStat.setInt(3,idFact);
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}

	@Override
	public Factura getAllFactura(Factura factura) throws SQLException {
		Factura facturaCopia= new Factura();
		Proveedor proveedor= new Proveedor();
		Area area= new Area();
		Estado estado=new Estado();
		Empresa empresa= new Empresa();
		
		Destinatario destinatario= new Destinatario();
		Concepto concepto =new Concepto();
		//Destinatario destinatario= new Destinatario();// se tiene q traer de la otra tabla
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ALL_FACTURAS);
		preparedStat.setInt(1,factura.getId());
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				
				facturaCopia.setId(result.getInt("IDFACTURA"));
				facturaCopia.setNoFact(result.getString("NOFACTURA"));
				empresa.setNombre(result.getString("EMPRESA"));
				area.setId(result.getInt("IDAREA"));
				
				facturaCopia.setEmpresa(empresa);
				facturaCopia.setArea(area);
				//PLAZA CAMBIAR A VARCHAR
				
				facturaCopia.setPlaza(result.getString("PLAZA"));
				//concepto pendiente
				//factura.setDestinatario(result.getString(""));//idusuario
				proveedor.setNumero(result.getString("NOPROVEEDOR"));
				proveedor.setNombre(result.getString("NOMPROVEEDOR"));
				facturaCopia.setFechaFactura(result.getDate("FECHAFACTURA"));
				proveedor.setRfc(result.getString("RFCPROVEEDOR"));
				proveedor.setSucursal(result.getString("SUCPROVEEDOR"));
				facturaCopia.setProveedor(proveedor);
				facturaCopia.setFechaRecepcion(result.getDate("FECHARECEPCION"));
				BigDecimal monto=result.getBigDecimal("MONTOFACTURA");
				
				
				if(monto==null){
					//factura.setMonto("");
					facturaCopia.setMonto("");
				}else{
				facturaCopia.setMonto(monto.toPlainString());}
				
				BigDecimal montoMaximo=result.getBigDecimal("MONTO_MAXIMO");
				if(montoMaximo==null){
					//factura.setMontoMaximo("");
					facturaCopia.setMontoMaximo("");
				}else{
				facturaCopia.setMontoMaximo(montoMaximo.toPlainString());}
				
				facturaCopia.setMercado(result.getString("MERCADOFACTURA"));
				facturaCopia.setOrdenCompra(result.getString("ORDENCOMPRA"));
				facturaCopia.setNoSaf(result.getString("NOSAF"));
				facturaCopia.setMoneda(result.getString("MONEDAFACTURA"));	
				facturaCopia.setUrl(result.getString("URL"));
				facturaCopia.setSoporte(result.getInt("SOPORTE"));
				facturaCopia.setTipoDocumento(result.getString("TIPOFACTURA"));
				destinatario.setIdUsuario(result.getString("ID_DESTINATARIO"));
				facturaCopia.setDestinatario(destinatario);
				facturaCopia.setNombreSoporte(result.getString("TIPO_DOCUMENTO"));
				concepto.setCuenta(result.getString("DISTRIBUCIONCONTABLE"));
				concepto.setDescripcion(result.getString("CONCEPTOFACTURA"));
				facturaCopia.setConcepto(concepto);
				facturaCopia.setNumTiendas(result.getInt("NUM_TIENDAS"));
				
				estado.setId(result.getInt("IDSTATUS"));
				facturaCopia.setEstado(estado);
			}
		}
		this.closeQuery();
		return facturaCopia;
	}

	@Override
	public int actualizaURL(int idFact, String url) throws SQLException {
		int actualizo=0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_URL);
		preparedStat.setString(1, url);
		preparedStat.setInt(2,idFact);
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
	}

	@Override
	public ArrayList<Factura> getListaFacturasSoportePorLote(String lote, int idFact,String idUsuario)
			throws SQLException {
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTUTAS_SOPORTE_LOTE);
		preparedStat.setString(1, lote);
		preparedStat.setInt(2, idFact);
		preparedStat.setString(3, idUsuario);
		result = preparedStat.executeQuery();
		
		while (result.next()){
			Factura factura = new Factura();
			if(result.getString("NOFACTURA")!=null){
				factura.setNoFact(result.getString("IDFACTURA"));
				factura.setNoFact(result.getString("NOFACTURA"));
				Proveedor proveedor= new Proveedor();
				proveedor.setNombre(result.getString("NOMPROVEEDOR"));
				proveedor.setNumero(result.getString("NOPROVEEDOR"));
				factura.setProveedor(proveedor);
				BigDecimal monto=result.getBigDecimal("MONTOFACTURA");
				if(monto==null){
					factura.setMonto("");
				}else{
				factura.setMonto(monto.toPlainString());
				}
				listaFacturas.add(factura);
			}
		
		}
		this.closeQuery();
		return listaFacturas;
	}

	@Override
	public int signarDocumentoAFactura(String noFact,String noProv,int Soporte,String nombreDoc, int idFact,String tipoFactura )throws SQLException {
		int actualizo=0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_FACTURA_DOC);
		preparedStat.setString(1, noFact);
		preparedStat.setString(2, noProv);
		preparedStat.setString(3, tipoFactura);
		preparedStat.setInt(4, Soporte);
		preparedStat.setString(5, nombreDoc);
		preparedStat.setInt(6,  idFact);
		actualizo=preparedStat.executeUpdate();
		this.closeQuery();
		return actualizo;
		
	}

	@Override
	public int consultaAllFacturasPorlote(String lote)
			throws SQLException {
		int existeLote = 0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_ALL_FACTURAS_POR_LOTE_);
		preparedStat.setString(1, lote);
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
	public int consultaFacturasPorloteSinEnviarACM(String lote)
			throws SQLException {
		int existeLote = 0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_POR_LOTE_LISTASCM);
		preparedStat.setString(1, lote);
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
	public int consultaAllFacturasListasCM(String lote, String usuario)
			throws SQLException {
		
		int existeLote = 0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_POR_LOTE_CAPTURADAS);
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
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTUTAS_LOTE_USUARIO_CAPTURADAS);
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

	@Override
	public int consultaFacturasPendientesCaptura(String lote, String usuario)
			throws SQLException {
		int facturas = 0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_PENDIENTES_CAPTURA);
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
	public String getNombreSoporte(int idFactura) throws SQLException {
		String nombreSoporte=null;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_NOMBRE_SOPORTE);
		preparedStat.setInt(1, idFactura);
		result=preparedStat.executeQuery();
		if(result!=null){
			while(result.next()) {
				nombreSoporte=result.getString(1);
			}
		}
		return nombreSoporte;
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
	
	/**
	 * @autor Eliudc - 29/04/2013
	 * Se obtiene factura de BD por numero de factura
	 * y proveedor como parte del control de cambios [SEV-256-CC-011].
	 */
	public boolean selectFacturaByNumFact(Factura factura) throws SQLException {
		boolean isFacura = false;
		boolean isFacturaCancelada = false;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTURA_GASTOS);
		preparedStat.setString(1, factura.getNoFact());
		preparedStat.setString(2, factura.getProveedor().getNumero());
		preparedStat.setInt(3, factura.getId());
		preparedStat.setString(4, factura.getNoFact());
		preparedStat.setString(5, factura.getProveedor().getNumero());
		preparedStat.setInt(6, factura.getId());
		result = preparedStat.executeQuery();
		int idFactura = 0;
		if(result != null)
		{
			while (result.next()) {
				idFactura = result.getInt("idFactura");
				isFacturaCancelada = isFacturaCancelada(idFactura);
				if(!isFacturaCancelada)
				{	
					isFacura = true;
					break;
				}
			}
		}
		
		this.closeQuery();
		return isFacura;
	}
	/**
	 * @autor Eliudc - 29/04/2013
	 * Se obtiene el estatus de la factura de BD por idfactura
	 * como parte del control de cambios [SEV-256-CC-011].
	 */
	public boolean isFacturaCancelada(int idFactura) throws SQLException {
		boolean isFacura = false;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_ESTATUS_FACTURA);
		preparedStat.setInt(1, idFactura);
		preparedStat.setInt(2, 15);
		preparedStat.setInt(3, idFactura);
		preparedStat.setInt(4, 15);
		preparedStat.setInt(5, idFactura);
		preparedStat.setInt(6, 15);
		
		ResultSet result = preparedStat.executeQuery();
		
		if(result != null)
		{
			while (result.next()) {
				isFacura = true;
			}
		}
		result.close();
		//this.closeQuery();
		return isFacura;
	}
	
}

