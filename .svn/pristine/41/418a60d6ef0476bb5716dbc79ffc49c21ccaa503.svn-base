package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class LotesDAOQuery {

	public static final String COUNT_FACTURAS_POR_LOTE						 = "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+
																				" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.IDUSUARIO = ?  AND SC.IDSTATUS!="+Constants.STATUS_CANCELADO 
																				+" AND FC.TIPOFACTURA != '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
	
	public static final String SELECT_LOTES_PENDIENTES 						= "SELECT LOTE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+ " AS FC  INNER JOIN "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.IDUSUARIO = ? and SC.IDSTATUS!="+Constants.STATUS_CANCELADO+
																				" and  FC.TIPOFACTURA != '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA +"' GROUP BY FC.LOTE";
	
	public static final String LOTES_PENDIENTES_MERCANCIA 					= "SELECT LOTE FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+ " AS FC  INNER JOIN "
			 																	+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.IDUSUARIO = ? and SC.IDSTATUS!="+Constants.STATUS_CANCELADO+" and  FC.TIPOFACTURA = '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"' GROUP BY FC.LOTE";
	
	public static final String COUNT_FACTURAS_POR_LOTE_MERCANCIA			 = "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND FC.IDUSUARIO = ?  AND SC.IDSTATUS!="+Constants.STATUS_CANCELADO 
																				+" AND FC.TIPOFACTURA = '"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA+"'";
}
