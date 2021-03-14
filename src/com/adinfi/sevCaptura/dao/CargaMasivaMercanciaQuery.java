package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class CargaMasivaMercanciaQuery {
	
	public static final String COUNT_ALL_FACTURAS_POR_LOTE_					= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.IDUSUARIO=? "
																			+" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String COUNT_FACTURAS_POR_LOTE_LISTASCM				= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS="+Constants.STATUS_IMPORTANDO_CM
																			+" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String COUNT_FACTURAS_POR_LOTE_EN_CM				= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  LEFT JOIN "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ?  AND IDUSUARIO=? AND SC.IDSTATUS="+Constants.STATUS_IMPORTADO_A_CM
																			+" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	public static final String SELECT_FACTUTAS_LOTE_USUARIO_IMPORTACION 	= "SELECT * FROM "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																			" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.SOPORTE=0 AND FC.IDUSUARIO=?  AND IDSTATUS="+Constants.STATUS_IMPORTANDO_CM +" AND FC.TIPOFACTURA= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"
	
	public static final String SELECT_FACTURAS_SOPORTE_LOTE_USUARIO 	= "SELECT * FROM "
																			+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC " +
																			" LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC " +
																			" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.SOPORTE=1 AND FC.IDUSUARIO=? AND FC.NOFACTURA=? AND FC.NOPROVEEDOR=?  AND IDSTATUS!="+Constants.STATUS_CANCELADO +" AND FC.TIPOFACTURA!= '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";//and tipo doc !="soporte"
	
	public static final String DELETE_STATUS_FACTURA 					="DELETE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA+" WHERE IDFACTURA=? ";
	
	public static final String DELETE_FACTURA_CAPTURA					="DELETE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" WHERE IDFACTURA=?";

}
