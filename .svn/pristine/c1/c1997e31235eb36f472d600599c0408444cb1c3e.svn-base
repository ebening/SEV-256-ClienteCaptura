package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class CapturaMercanciaQuery {
	
	public static final String COUNT_FACTURAS_POR_LOTE 		= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND idusuario = ? "
																		+" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	

public static final String SELECT_FACTUTAS_LOTE 			= "WITH ConsultaFacturas AS (SELECT ROW_NUMBER() OVER (ORDER BY FC.IDFACTURA) AS ROW, " +
															  "SC.IDSTATUS, FC.URL, FC.NOFACTURA, FC.NOPROVEEDOR , FC.IDFACTURA, FC.FRECEPCION, FC.TIPOFACTURA  FROM "
															  +Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
															  "LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
															  "ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO = ?  AND FC.TIPOFACTURA = 'FacturaMercancia') SELECT * FROM ConsultaFacturas " +
															  "WHERE ROW BETWEEN ? AND ? ";

public static final String  INSERT_DOCUMENTO				= "SELECT IDFACTURA FROM NEW TABLE (INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA +
																" (URL, IDUSUARIO, SOPORTE,TIPOFACTURA,FECHARECEPCION, PLAZA ,LOTE) VALUES (?, ?, ? ,?, ? , ?, ?))";

public static final String INSERT_ESTATUS_FACTURA			= "INSERT INTO "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+
																	" (IDFACTURA,IDSTATUS,FECHASTATUS) VALUES (?, ?, ?) ";

public static final String SELECT_STATUS_FACTURA 			= "SELECT IDSTATUS FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" WHERE IDFACTURA = ?";


public static final String SELECT_ALL_FACTURAS 				= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC" +
		  														" ON  FC.IDFACTURA = SC.IDFACTURA  WHERE FC.IDFACTURA= ?  AND SC.IDSTATUS!="+Constants.STATUS_CANCELADO+"  AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";// adn tipo fact se=mercancia

public static final String UPDATE_URL						= "UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" SET URL= ?  WHERE IDFACTURA= ?";

public static final String UPDATE_STATUS 					= "UPDATE "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" SET IDSTATUS = ? , FECHASTATUS=? WHERE IDFACTURA= ?";


public static final String UPDATE_FACTURA 					="UPDATE "+ Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+"  SET EMPRESA=?,  PLAZA=? , IDUSUARIO=?," +
															" NOFACTURA=?, NOPROVEEDOR= ? , NOMPROVEEDOR= ?, RFCPROVEEDOR= ?, SUCPROVEEDOR= ?, " +
															" FECHAFACTURA= ? , MONTOFACTURA= ?, FRECEPCION =? , FECHARECEPCION= ?,TIPOFACTURA=? ,SOPORTE=?" +
															"  WHERE IDFACTURA= ?  ";

public static final String COUNT_FACTURAS_POR_LOTE_CAPTURADAS			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																		+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS IN ("+Constants.STATUS_CAPTURA_COMPLETA+" , "+Constants.STATUS_ERROR_IMPORTADO+") "
																		+" AND FC.TIPOFACTURA = '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";

public static final String SELECT_FACTUTAS_LOTE_USUARIO_CAPTURADAS 			= "SELECT * FROM "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																			" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ?  AND FC.IDUSUARIO=?  AND IDSTATUS IN ("+Constants.STATUS_CAPTURA_COMPLETA +" , "+Constants.STATUS_ERROR_IMPORTADO+")" +" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"

public static final String COUNT_FACTURAS_PENDIENTES_CAPTURA			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																	+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS="+Constants.STATUS_PENDIENTE_CAPTURA
																	+" AND FC.TIPOFACTURA = '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";


	

}
