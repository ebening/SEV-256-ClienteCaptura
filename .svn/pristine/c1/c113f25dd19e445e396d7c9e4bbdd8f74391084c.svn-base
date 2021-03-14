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
import com.adinfi.sevCaptura.connections.ConnectionManagerCM;
import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.entities.Aprobacion;
import com.adinfi.sevCaptura.entities.Area;
import com.adinfi.sevCaptura.entities.Atributo;
import com.adinfi.sevCaptura.entities.Concepto;
import com.adinfi.sevCaptura.entities.Destinatario;
import com.adinfi.sevCaptura.entities.Documento;
import com.adinfi.sevCaptura.entities.Empresa;
import com.adinfi.sevCaptura.entities.Estado;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Notificacion;
import com.adinfi.sevCaptura.entities.Proveedor;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.resources.MimeType;
import com.adinfi.sevCaptura.resources.Utilities;
import com.ibm.mm.sdk.common.DKAttrDefICM;
import com.ibm.mm.sdk.common.DKConstant;
import com.ibm.mm.sdk.common.DKConstantICM;
import com.ibm.mm.sdk.common.DKDDO;
import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.common.DKParts;
import com.ibm.mm.sdk.common.DKSequentialCollection;
import com.ibm.mm.sdk.common.DKTextICM;
import com.ibm.mm.sdk.common.dkIterator;
import com.ibm.mm.sdk.server.DKDatastoreICM;


public class CargaMasivaDAO implements  CargaMasivaDAOInterface {
	
	private ConnectionManagerCM connectionManager = null;
	private DKDatastoreICM connCM = null;
	private ConnectionManagerDB2 connManagerDB2 = null;
	private Connection connDB2 = null;
	private PreparedStatement preparedStat = null;
	private ResultSet result = null;
	
	private String newPid = null;
	
	@Override
	public String getNewPid(){
		return newPid;
	}

	
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
	

	@Override
	public boolean establecerConexionCM() throws DKException, Exception{
		connectionManager = new ConnectionManagerCM();
		
		if(connectionManager.connectCM()){
			connCM = connectionManager.getConnectionCM();
			return true;
		}		
		return false;
	}
	
	@Override
	public void cerrarConexionCM() throws DKException, Exception{
			this.connectionManager.disconectCM();
		
	}
	
	@Override
	public boolean validaConexionCM() throws DKException, Exception{
		boolean conexionValida = false;
				
		conexionValida = this.connectionManager.validaConexionCM();
		
		return conexionValida;
	}
	

	@Override
	public boolean establecerConexionCM(String usuario, String password)
			throws DKException, Exception {
		// TODO Auto-generated method stub
		return false;
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
	public int consultaAllFacturasPorlote(String lote,String usuario)
			throws SQLException {
		int existeLote = 0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_ALL_FACTURAS_POR_LOTE_);
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
	public int consultaFacturasPorloteSinEnviarACM(String lote,String usuario)//capturadas listas paea enviar a CM
			throws SQLException {
		
		int existeLote = 0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_POR_LOTE_LISTASCM);
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
	public int consultaFactEnviadasACM(String lote,String usuario) throws SQLException {
		
		int factCM = 0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.COUNT_FACTURAS_POR_LOTE_EN_CM);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		result = preparedStat.executeQuery();
		
		
		if(result != null){
			while(result.next()){
				factCM=result.getInt(1);
			}
		}
		
	
		this.closeQuery();
		return factCM;
		
	}

	@Override
	public ArrayList<Factura> getFacturasLoteUsuario(String lote, String usuario)
			throws SQLException {
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		System.out.println(CapturaGastosDAOQuery.SELECT_FACTUTAS_LOTE_USUARIO_IMPORTACION);
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTUTAS_LOTE_USUARIO_IMPORTACION);
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
			area.setNombre(result.getString("NOMBRE_AREA"));
			factura.setArea(area);
			proveedor.setNumero(result.getString("NOPROVEEDOR"));
			proveedor.setNombre(result.getString("NOMPROVEEDOR"));
			factura.setFechaFactura(result.getDate("FECHAFACTURA"));
			proveedor.setRfc(result.getString("RFCPROVEEDOR"));
			proveedor.setSucursal(result.getString("SUCPROVEEDOR"));
			factura.setProveedor(proveedor);
			factura.setPlaza(result.getString("PLAZA"));
			factura.setFechaRecepcion(result.getDate("FECHARECEPCION"));
			factura.setFechaCambio(result.getDate("FECHATCAMBIO"));
			BigDecimal monto=result.getBigDecimal("MONTOFACTURA");
			
			if(monto==null){
				factura.setMonto("");
			}else{factura.setMonto(monto.toPlainString());}
			
			BigDecimal montoMaximo=result.getBigDecimal("MONTO_MAXIMO");
			if(montoMaximo==null){
				factura.setMontoMaximo("");
			}else{factura.setMontoMaximo(montoMaximo.toPlainString());}
			
			factura.setOrdenCompra(result.getString("ORDENCOMPRA"));
			factura.setNoSaf(result.getString("NOSAF"));
			factura.setMoneda(result.getString("MONEDAFACTURA"));	
			factura.setMercado(result.getString("MERCADOFACTURA"));
			
			
			destinatario.setIdUsuario(result.getString("ID_DESTINATARIO"));
			factura.setDestinatario(destinatario);
			
			concepto.setDescripcion(result.getString("CONCEPTOFACTURA"));
			concepto.setCuenta(result.getString("DISTRIBUCIONCONTABLE"));
			factura.setConcepto(concepto);	
			
			factura.setTipoDocumento(result.getString("TIPOFACTURA"));
			factura.setUrl(result.getString("URL"));
			factura.setLote(result.getString("LOTE"));
			
			usuarioCaptura.setIdUsuario(result.getString("IDUSUARIO"));
			factura.setUsuario(usuarioCaptura);
			Estado estado = new Estado();
			estado.setId(result.getInt("IDSTATUS"));
			factura.setEstado(estado);
			factura.setNumTiendas(result.getInt("NUM_TIENDAS"));
			listaFacturas.add(factura);
		}
		this.closeQuery();
		return listaFacturas;
	}

	@Override
	public ArrayList<Factura> getFacturasSoporteLoteUsuario(String lote,String usuario,String noFactura,String noProveedor) throws SQLException {
		ArrayList<Factura> listaFacturasSoporte = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTURAS_SOPORTE_LOTE_USUARIO);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		preparedStat.setString(3, noFactura);
		preparedStat.setString(4, noProveedor);
		
		result = preparedStat.executeQuery();
		
		while (result.next()){
			Factura factura = new Factura();
			Usuario usuarioCaptura= new Usuario();
			Proveedor proveedor= new Proveedor();
			factura.setId(result.getInt("IDFACTURA"));
			factura.setNoFact(result.getString("NOFACTURA"));
			proveedor.setNumero(result.getString("NOPROVEEDOR"));
			factura.setProveedor(proveedor);
			//factura.setFechaFactura(result.getDate("FECHAFACTURA"));	
			factura.setUrl(result.getString("URL"));
			factura.setNombreSoporte(result.getString("TIPO_DOCUMENTO"));
			factura.setLote(result.getString("LOTE"));
			
			usuarioCaptura.setIdUsuario(result.getString("IDUSUARIO"));
			factura.setUsuario(usuarioCaptura);
			listaFacturasSoporte.add(factura);
		}
		this.closeQuery();
		return listaFacturasSoporte;
	}
	
	@Override
	public int insertarFacturaEnFacturaGastos(Factura factura)
			throws SQLException {
		int idFact=0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_FACTURA_EN_GASTOS);
		preparedStat.setString(1,factura.getEmpresa().getNombre()); 
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
		preparedStat.setString(18,factura.getPID());
		preparedStat.setString(19,factura.getLote());
		preparedStat.setString(20,factura.getMercado());
		
		if(factura.getFechaCambio()==null){
			preparedStat.setDate(21,null);
		}else{
			preparedStat.setDate(21, java.sql.Date.valueOf(getStringDateSql(factura.getFechaCambio())));
		}
		
		preparedStat.setString(22,factura.getConcepto().getCuenta());
		preparedStat.setInt(23, factura.getNumTiendas());
		
		BigDecimal montoMaximo=Utilities.convertToBigDecimal(factura.getMontoMaximo());
		preparedStat.setBigDecimal(24, montoMaximo);
		
		if(factura.getFechaInicioVoBo() == null)
		{
			preparedStat.setDate(25,null);
		}else{
			preparedStat.setDate(25, java.sql.Date.valueOf(getStringDateSql(factura.getFechaInicioVoBo())));
		}
		
		result = preparedStat.executeQuery();
		
		if(result.next()){
			idFact = result.getInt(1);
		}
		
	    this.closeQuery();
	    return idFact;
	}
	

	@Override
	public int insertarSoporteaAdjuntos(Factura soporte) throws SQLException {
		
		int idAdjunto=0;
		String fechaCarga=Utilities.nowDate();
		String fecgSql=Utilities.convertDateFormat(fechaCarga);
		
		Date fecha=Utilities.parserSqlDate(fecgSql);
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_SOPORTE_EN_ADJUNTOS);
		preparedStat.setDate(1, fecha); 
		preparedStat.setString(2, soporte.getPID());
		preparedStat.setString(3, soporte.getUsuario().getIdUsuario());
		preparedStat.setString(4, soporte.getNombreSoporte());
		result = preparedStat.executeQuery();
		
		if(result.next()){
			idAdjunto = result.getInt(1);
		}
		    
	   
	    
	    this.closeQuery();
	    return idAdjunto;
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
	public int insertarFacturaAdjuntos(int idFactGastos, int idAdjunto)
			throws SQLException {
		int actualizo=0;
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_FACTURA_ADJUNTOS);
		preparedStat.setInt(1, idFactGastos);
		preparedStat.setInt(2,idAdjunto);
		preparedStat.execute();
		
		this.closeQuery();
		return actualizo;
	}
	
	

	@Override
	public int insertarBitacoraFactura(Factura factura, int status)
			throws SQLException {
		int idMovimiento=0;
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_BITACORA_FACTURA);
		preparedStat.setString(1, factura.getNoFact());
		preparedStat.setInt(2,status);
		preparedStat.setTimestamp(3, timesTamp);
		preparedStat.setString(4,factura.getUsuario().getIdUsuario());
		preparedStat.setString(5,factura.getAccion());
		preparedStat.setString(6,factura.getProveedor().getNumero());
		preparedStat.setString(7,factura.getDestinatario().getIdUsuario());
		
		result = preparedStat.executeQuery();
		
		if(result.next()){
			idMovimiento = result.getInt(1);
		}
		    
	   
	    
	    this.closeQuery();
	    return idMovimiento;
	}
	
	@Override
	public boolean insertarStatusFactura(int idFactura, int idMovimiento,
			int status, String idDestinatario) throws SQLException {
	
		String fecha=Utilities.nowTimeStamp();
		Timestamp timesTamp=null;
		timesTamp=Utilities.parseSqlTimeStamp(fecha);
	
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_STATUS_FACTURA);
		preparedStat.setInt(1,idFactura );
		preparedStat.setInt(2, status);
		preparedStat.setTimestamp(3,timesTamp);
		preparedStat.setInt(4, idMovimiento );
		preparedStat.setString(5,idDestinatario );
		preparedStat.execute();
		closeQuery();
		return true;
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
	public boolean borrarFacturaCaptura(int idFactura) throws SQLException {
		
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.DELETE_FACTURA_CAPTURA);
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
	public ArrayList<Atributo> getAtributosListByItemType(String itemType) 
			throws DKException, Exception{
		ArrayList<Atributo> atributos = new ArrayList<Atributo>();
		
		DKSequentialCollection attrColl = (DKSequentialCollection) connCM.listEntityAttrs(itemType);
		
		if(attrColl != null){
			dkIterator iter = attrColl.createIterator();
	
			while (iter.more()) {
				DKAttrDefICM attr = (DKAttrDefICM) iter.next();
				Atributo atributo = new Atributo();
				atributo.setName(attr.getName());
				atributo.setDescription(attr.getDescription());
				atributo.setTipo(attr.getType());
				atributo.setLonguitud(attr.getMax());
				atributo.setRequerido(!attr.isNullable());
				atributo.setRepresentativo(attr.isRepresentative());
				atributos.add(atributo);
				
			}
		}
		
		return atributos;
	}


	public boolean importaDocumento(Documento item) {
		this.newPid = null;
		boolean statusProceso =false;
		try{
			String nombreItemType = item.getNombre();
			short itemType = item.getItemType();
			if(itemType == DKConstant.DK_CM_DOCUMENT){
				DKDDO ddoDocument = connCM.createDDO(nombreItemType, itemType);
				DKTextICM base =(DKTextICM) connCM.createDDO("ICMBASETEXT", DKConstantICM.DK_ICM_SEMANTIC_TYPE_BASE);
				((DKTextICM)base).setFormat("");
				((DKTextICM)base).setLanguageCode("");
				String mimeType = new MimeType().getCMMimeTypeByUrl(item.getUrl());
				base.setMimeType(mimeType);
				ArrayList<Atributo> atributos = item.getAtributos();
				for (Atributo attr : atributos) {
					String attrName = attr.getName();
					Short attrType = attr.getTipo();
					String attrValue = attr.getValor();
					
					boolean addAttr = false;
					if(attrValue != null){
						if(!attrValue.isEmpty()){
							addAttr = true;
						}
					}
					
					if(addAttr){
						if(attrType.equals(DKConstant.DK_DATE)){
							ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR,
									attr.getName()),
									Utilities.parserSqlDate(attr.getValor()));
							
						}else if(attrType.equals(DKConstant.DK_LONG) ){
							ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR,
									 attr.getName()), Utilities.convertToInteger(attr.getValor()));
							
						}else if(attrType.equals(DKConstant.DK_DECIMAL)){
							ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR,
									   attr.getName()), Utilities.convertToBigDecimal(attr.getValor()));
							
						}else if(attrType.equals(DKConstant.DK_FSTRING) 
								|| attrType.equals(DKConstant.DK_VSTRING)){
							ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR,
									   attr.getName()), attr.getValor());
							
						}else{
							throw new Exception("No se ha identificado el tipo de " +
									"dato para el atributo " + attrName);
						}
					}
				}
				
				if(item.getUrl() != null){
					base.setContentFromClientFile(item.getUrl());
					DKParts dkParts = (DKParts)ddoDocument.getData(ddoDocument.dataId(
							DKConstant.DK_CM_NAMESPACE_ATTR,DKConstant.DK_CM_DKPARTS));
					dkParts.addElement(base);	
				}
			
				ddoDocument.add();
				
				this.newPid = ddoDocument.getPidObject().pidString();
				
				statusProceso= true;
			}
		}
		catch(DKException e){
			System.out.println("Ha ocurrido un error en la importación: "+e.getMessage());
		}
		catch(Exception e){
			System.out.println("Ha ocurrido un error en la importación: "+e.getMessage());
		}
		finally{
			return statusProceso;
		}
	}
	
	@Override
	public ArrayList<Factura> consultaAllFacturaStatusStatusCM(String lote, String usuario)
			throws SQLException {
	
			ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.SELECT_FACTURAS_POR_LOTE_CM);
		preparedStat.setString(1, lote);
		preparedStat.setString(2, usuario);
		
		result = preparedStat.executeQuery();
		if(result!=null){
		while (result.next()){
			Factura factura = new Factura();
			Destinatario destinatario= new Destinatario();
			factura.setId(result.getInt("IDFACTURA"));
			destinatario.setIdUsuario(result.getString("id_destinatario"));
			factura.setDestinatario(destinatario);
			listaFacturas.add(factura);
		}
		}
		this.closeQuery();
		return listaFacturas;
	}
	
	@Override
	public boolean insertarNotificacion(Notificacion noti) throws SQLException {
		System.out.println(CapturaGastosDAOQuery.INSERT_NEW_NOTIFICACION);
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_NEW_NOTIFICACION);
		preparedStat.setInt(1, noti.getIdfactura());
		preparedStat.setInt(2, noti.getIdEstado());
		preparedStat.setString(3, noti.getIdUsuarioOrigen());
		preparedStat.setString(4, noti.getIdUsuarioDestino());
		preparedStat.setTimestamp(5, noti.getFecha());
		preparedStat.setInt(6, noti.isAutomatica()?1:0);
		preparedStat.setInt(7, noti.isEscalamiento()?1:0);
		preparedStat.setInt(8, noti.getDias());
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
		
	}
	
	@Override
	public boolean insertNewAprobacion(Aprobacion aprob) throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.INSERT_NEW_APROBACION);
		preparedStat.setInt(1, aprob.getIdFactura());
		preparedStat.setTimestamp(2, aprob.getFecha());
		preparedStat.setString(3, aprob.getIdUsuarioOrigen());
		preparedStat.setString(4, aprob.getIdUsuarioDestino());
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
	}


	@Override
	public int getNumeroDocumentosProcesado(Factura factura,String tipoCaptura)
			throws SQLException {
	
		int numDocProcesados=0;
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.GET_DOCUMENTOS_PROCESADOS);
		preparedStat.setString(1, factura.getUsuario().getIdUsuario());
		preparedStat.setString(2, factura.getLote());
		preparedStat.setString(3, tipoCaptura);
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				numDocProcesados = result.getInt(1);
			}
		}
		this.closeQuery();
		return numDocProcesados;
	}


	@Override
	public boolean actualizaDocumentosprocesados(int numDocActual,Factura factura,String tipoCaptura) throws SQLException {
		preparedStat = connDB2.prepareStatement(CapturaGastosDAOQuery.UPDATE_DOCUMENTOS_PROCESADOS);
		preparedStat.setInt(1, numDocActual);
		preparedStat.setString(2,factura.getUsuario().getIdUsuario());
		preparedStat.setString(3,factura.getLote());
		preparedStat.setString(4,tipoCaptura);
		preparedStat.executeUpdate();
		this.closeQuery();
		return true;
	}

	
	
}
