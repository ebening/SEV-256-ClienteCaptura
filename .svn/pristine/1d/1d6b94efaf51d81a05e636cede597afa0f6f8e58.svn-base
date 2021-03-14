package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class CapturaGastosDAOQuery {
	public static final String  INSERT_DOCUMENTO					= "SELECT IDFACTURA FROM NEW TABLE (INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA +
																	" (URL, IDUSUARIO, SOPORTE, TIPOFACTURA ,LOTE) VALUES (?, ?, ? , ?, ?))";
	
	public static final String INSERT_ESTATUS_FACTURA				= "INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+
																  " (IDFACTURA,IDSTATUS,FECHASTATUS) VALUES (?, ?, ?) ";
	
	public static final String SELECT_ALL_AREAS						= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_AREA +" WHERE activa=1 ORDER BY nombre_area ASC";
	public static final String SELECT_ALL_PLAZAS					= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_PLAZA;
	public static final String SELECT_ALL_DESTINATARIOS				= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_USUARIOS+" WHERE ID_AREA = ? ORDER BY NOMBRES,APELLIDOS";


	public static final String SELECT_STATUS_FACTURA 					= "SELECT IDSTATUS FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" WHERE IDFACTURA = ?";
	
	
	public static final String SELECT_FACTUTAS_LOTE 					= "WITH ConsultaFacturas AS (SELECT ROW_NUMBER() OVER (ORDER BY FC.IDFACTURA) AS ROW, " +
																		"SC.IDSTATUS, FC.URL, FC.NOFACTURA, FC.NOPROVEEDOR , FC.IDFACTURA ,FC.TIPOFACTURA ,FC.SOPORTE  FROM "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																		"LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																		"ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND FC.TIPOFACTURA!= 'FacturaMercancia') SELECT * FROM ConsultaFacturas " +
																		"WHERE ROW BETWEEN ? AND ? "; 
	
	public static final String COUNT_FACTURAS_POR_LOTE 					= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND idusuario = ? "
																		+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String UPDATE_FACTURA_AREA 							="UPDATE "+ Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+"  SET EMPRESA=?, IDAREA=?, PLAZA=? , IDUSUARIO=?," +
																		" NOFACTURA=?, NOPROVEEDOR= ? , NOMPROVEEDOR= ?, RFCPROVEEDOR= ?, SUCPROVEEDOR= ?, " +
																		" FECHAFACTURA= ? , MONTOFACTURA= ?, CONCEPTOFACTURA=? , FECHARECEPCION= ?, ORDENCOMPRA= ? , NOSAF= ?, MONEDAFACTURA= ? ,TIPOFACTURA=? ,SOPORTE=? , TIPO_DOCUMENTO = ? , MERCADOFACTURA=? ," +
																		" DISTRIBUCIONCONTABLE = ?, FECHATCAMBIO = ? ,NUM_TIENDAS = ? ,MONTO_MAXIMO = ?"+
																		"  WHERE IDFACTURA= ?  ";
	
	public static final String UPDATE_FACTURA 							="UPDATE "+ Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+"  SET EMPRESA=?,  PLAZA=? , IDUSUARIO=?," +
																		 " NOFACTURA=?, NOPROVEEDOR= ? , NOMPROVEEDOR= ?, RFCPROVEEDOR= ?, SUCPROVEEDOR= ?, " +
																		 " FECHAFACTURA= ? , MONTOFACTURA= ?, CONCEPTOFACTURA=? , FECHARECEPCION= ?, ORDENCOMPRA= ? , NOSAF= ?, MONEDAFACTURA= ? ,TIPOFACTURA=? ,SOPORTE=? , TIPO_DOCUMENTO = ? , MERCADOFACTURA=? ," +
																		 " DISTRIBUCIONCONTABLE = ?,FECHATCAMBIO = ? ,NUM_TIENDAS = ? ,MONTO_MAXIMO = ? "+
																		 "  WHERE IDFACTURA = ?  ";
	

	public static final String UPDATE_STATUS 							= "UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" SET IDSTATUS = ? , FECHASTATUS=? , ID_DESTINATARIO=?  WHERE IDFACTURA= ?";
	
	public static final String UPDATE_STATUS_SOPORTE 					= "UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" SET IDSTATUS = ? , FECHASTATUS=?   WHERE IDFACTURA= ?";

	public static final String SELECT_ALL_FACTURAS 						= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC" +
																  		" ON  FC.IDFACTURA = SC.IDFACTURA  WHERE FC.IDFACTURA= ?  AND SC.IDSTATUS!="+Constants.STATUS_CANCELADO;

	public static final String SELECT_ALL_EMPRESAS 						= "SELECT * FROM  "+Constants.ESQUEMA_DB2+Constants.TABLA_EMPRESA;

	public static final String SELECT_ALL_CONCEPTOS 					="	SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_CONCEPTOS_GASTOS+" WHERE ID_AREA=?";

	public static final String UPDATE_URL								= "UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" SET URL= ?  WHERE IDFACTURA= ?";

	public static final String SELECT_FACTUTAS_SOPORTE_LOTE 			= "SELECT FC.IDFACTURA, FC.NOFACTURA,FC.NOPROVEEDOR, FC.NOMPROVEEDOR , FC.MONTOFACTURA  FROM "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																		" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																		" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.idfactura != ? AND idusuario = ? AND FC.SOPORTE!=1 AND IDSTATUS!="+Constants.STATUS_CANCELADO +" AND FC.TIPOFACTURA != '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"

	public static final String SELECT_PROVEEDOR 						= "SELECT * FROM DB2ADMIN.PROVEEDORES WHERE NUMERO_PROVEEDOR = ? AND ACTIVO = 1";
	 
	public static String UPDATE_FACTURA_DOC								="UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" SET  NOFACTURA=? , NOPROVEEDOR=?, TIPOFACTURA = ? , SOPORTE=? ,TIPO_DOCUMENTO=?  WHERE IDFACTURA=?";
	
	
	public static final String COUNT_ALL_FACTURAS_POR_LOTE_				= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.IDUSUARIO=? "
																		+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String COUNT_FACTURAS_POR_LOTE_LISTASCM			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS="+Constants.STATUS_IMPORTANDO_CM
																		+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String COUNT_FACTURAS_POR_LOTE_EN_CM			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  LEFT JOIN "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ?  AND IDUSUARIO=? AND SC.IDSTATUS="+Constants.STATUS_IMPORTADO_A_CM
																			+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String SELECT_FACTUTAS_LOTE_USUARIO_IMPORTACION = "SELECT * FROM "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																			" ON FC.idfactura= sc.idfactura"+
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_AREA+" AS AR "+
																			" ON FC.IDAREA= AR.ID_AREA"+
																			" WHERE FC.LOTE = ? AND FC.SOPORTE=0 AND FC.IDUSUARIO=?  AND IDSTATUS="+Constants.STATUS_IMPORTANDO_CM +" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"
	
	public static final String SELECT_FACTURAS_SOPORTE_LOTE_USUARIO 	= "SELECT * FROM "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																			" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.SOPORTE=1 AND FC.IDUSUARIO=? AND FC.NOFACTURA=? AND FC.NOPROVEEDOR=?  AND IDSTATUS!="+Constants.STATUS_CANCELADO +" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"
	
	
	
	public static final String  INSERT_SOPORTE_EN_ADJUNTOS					= "SELECT IDADJUNTO FROM NEW TABLE (INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_ADJUNTOS +
																			  " (FECHACARGA, PID, IDUSUARIO, TIPO_DOCUMENTO) VALUES (?, ?, ? , ?))";
	

	public static final String  INSERT_FACTURA_EN_GASTOS					= "SELECT IDFACTURA FROM NEW TABLE (INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_GASTOS 
																			  + " (EMPRESA, IDAREA, PLAZA, IDUSUARIO, NOFACTURA,"
																			  + " NOPROVEEDOR, NOMPROVEEDOR, RFCPROVEEDOR, SUCPROVEEDOR, FECHAFACTURA, " 
																			  + " MONTOFACTURA, CONCEPTOFACTURA, FECHARECEPCION, ORDENCOMPRA, NOSAF, "
																			  + " MONEDAFACTURA, TIPOFACTURA, PID, LOTE, MERCADOFACTURA, " 
																			  + " FECHATCAMBIO, DISTRIBUCIONCONTABLE, NUM_TIENDAS, MONTO_MAXIMO, FECHA_INICIO_VOBO)" 
																			  + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?))";

	public static final String INSERT_FACTURA_ADJUNTOS 						= " INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_ADJUNTOS+
																			  " (IDFACTURA,IDADJUNTO) VALUES (?,?)";

	public static final String INSERT_BITACORA_FACTURA 						= "SELECT IDMOVIMIENTO FROM NEW TABLE (INSERT INTO "+Constants.ESQUEMA_DB2+"BITACORAFACTURAS"+" (NOFACTURA,IDSTATUS,FECHAMOVIMIENTO,IDUSUARIO,ACCION,NOPROVEEDOR,IDUSUARIODESTINO) VALUES(?, ?, ?, ? , ? , ? ,?))" ;
	
	
	public static final String INSERT_STATUS_FACTURA				= "INSERT INTO "+Constants.ESQUEMA_DB2+"FACTURASTATUS"+
																	  " (IDFACTURA,IDSTATUS,FECHASTATUS,IDMOVIMIENTO,IDUSUARIO) VALUES (?, ?, ?, ?, ?) ";
	
	public static final String DELETE_STATUS_FACTURA 				="DELETE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" WHERE IDFACTURA=? ";
	
	public static final String DELETE_FACTURA_CAPTURA				="DELETE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" WHERE IDFACTURA=?";
	
	
	public static final String COUNT_FACTURAS_POR_LOTE_CAPTURADAS			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  "
																				+"INNER JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" "
																						+"ON  FC.IDFACTURA = SC.IDFACTURA "
																				+"WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS IN ("+Constants.STATUS_CAPTURA_COMPLETA+" , "+Constants.STATUS_ERROR_IMPORTADO+")"
																				+" AND  FC.SOPORTE = 0 AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String SELECT_FACTUTAS_LOTE_USUARIO_CAPTURADAS 			= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC "
																					+" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC "
																					+" ON  FC.IDFACTURA = SC.IDFACTURA "
																					+" WHERE FC.LOTE = ?  AND FC.IDUSUARIO=?  AND IDSTATUS IN ("+Constants.STATUS_CAPTURA_COMPLETA +" , "+Constants.STATUS_ERROR_IMPORTADO+")"
																					+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String COUNT_FACTURAS_PENDIENTES_CAPTURA			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS="+Constants.STATUS_PENDIENTE_CAPTURA
																				+" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";

	public static final String SELECT_FACTURAS_POR_LOTE_CM 					= "SELECT * FROM "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																				" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																				" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ?  AND FC.IDUSUARIO=?  AND IDSTATUS="+Constants.STATUS_IMPORTANDO_CM +" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";

	public static final String SELECT_NOMBRE_SOPORTE 						= "SELECT tipo_documento FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA + 
																			  " WHERE  idfactura = ?"	;

	public static final String INSERT_NEW_NOTIFICACION 						= "INSERT INTO "+Constants.ESQUEMA_DB2+"Notificaciones (ID_FACTURA, ID_ESTADO, ID_USUARIO_ORIGEN, " 
																			+ " ID_USUARIO_DESTINO, FECHA, AUTOMATICA, ESCALAMIENTO, DIAS) VALUES (?,?,?,?,?,?,?,?)";

	public static final String INSERT_NEW_APROBACION                         = "INSERT INTO "+Constants.ESQUEMA_DB2+"Historial_Aprobaciones (Id_Factura, fecha, "
																				+ "id_usuario_origen, id_usuario_destino) VALUES(?, ?, ?, ?)";

	public static final String INSERT_NEW_LOTE_REPORTE 						= "INSERT INTO "+Constants.ESQUEMA_DB2+"REPORTECARGA" +
																			  " (ID_USUARIO,LOTE,FECHACAPTURA,TIPOCAPTURA,DOCUMENTOSPROCESADOS) " +
																			  " VALUES(?, ?, ?, ?, ?)";

	public static final String COUNT_LOTE_REPORTE_CARGA 					= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+"REPORTECARGA WHERE ID_USUARIO= ? AND LOTE = ? AND TIPOCAPTURA = ?";

	public static final String GET_FECHA_INICIO_CAPTURA 					= "SELECT FECHAINICIOCAPTURA FROM "+Constants.ESQUEMA_DB2+"REPORTECARGA WHERE ID_USUARIO= ? AND LOTE = ? AND TIPOCAPTURA = ?";

	public static final String ACTUALIZAR_FECHA_INICIO_CAPTURA 				= "UPDATE "+Constants.ESQUEMA_DB2+"REPORTECARGA SET FECHAINICIOCAPTURA = ? WHERE ID_USUARIO= ? AND LOTE = ? AND TIPOCAPTURA = ?" ;

	public static final String ACTUALIZAR_FECHA_FIN_CAPTURA 				=  "UPDATE "+Constants.ESQUEMA_DB2+"REPORTECARGA SET FECHAFINCAPTURA = ? WHERE ID_USUARIO= ? AND LOTE = ?  AND TIPOCAPTURA = ?" ;

	public static final String GET_DOCUMENTOS_PROCESADOS 					= "SELECT DOCUMENTOSPROCESADOS FROM  "+Constants.ESQUEMA_DB2+"REPORTECARGA   WHERE ID_USUARIO= ? AND LOTE = ? AND TIPOCAPTURA = ?";

	public static final String UPDATE_DOCUMENTOS_PROCESADOS 				= "UPDATE "+Constants.ESQUEMA_DB2+"REPORTECARGA SET DOCUMENTOSPROCESADOS = ?  WHERE ID_USUARIO= ? AND LOTE = ? AND TIPOCAPTURA = ? ";
	
	/**
	 * @autor Eliudc - 29/04/2013
	 * Se agrega query para extraer factura de BD por numero de factura
	 * y proveedor como parte del control de cambios [SEV-256-CC-011].
	 */
	public static final String SELECT_FACTURA_GASTOS		    = "SELECT IDFACTURA FROM "+Constants.ESQUEMA_DB2+"FacturaGasto " 
            + "WHERE NoFactura = ? AND NoProveedor = ? and IdFactura != ?" +
            " UNION" +
            " SELECT IDFACTURA FROM "+Constants.ESQUEMA_DB2+"FacturaCaptura " +
            	"WHERE NoFactura = ? AND NoProveedor = ? and IdFactura != ?" ;
	
	public static final String SELECT_ESTATUS_FACTURA			= "SELECT idstatus FROM "+Constants.ESQUEMA_DB2+"FACTURASTATUSMULTIPLE WHERE IDFACTURA = ? AND IDSTATUS = ? " +
			"UNION " +
			"SELECT idstatus FROM "+Constants.ESQUEMA_DB2+"FACTURASTATUSCAPTURA WHERE IDFACTURA = ? AND IDSTATUS = ? " +
			"UNION " +
			"SELECT idstatus FROM "+Constants.ESQUEMA_DB2+"FACTURASTATUS WHERE IDFACTURA = ? AND IDSTATUS = ?  order by 1" ;

}
