package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class MenuDAOQuery {
	public static final String COUNT_FACTURAS_PENDIENTES_CAPTURA			= "SELECT COUNT(*) FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC  INNER JOIN "
																				+Constants.ESQUEMA_DB2+Constants.TABLA_STATUS_CAPTURA +" AS SC"+" ON  FC.IDFACTURA = SC.IDFACTURA WHERE FC.LOTE = ? AND IDUSUARIO=?  AND SC.IDSTATUS="+Constants.STATUS_PENDIENTE_CAPTURA;
	
	public static String COUNT_LOTES_PENDIENTES_GASTOS						="SELECT COUNT (*)FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC WHERE FC.IDUSUARIO = ?" +
																			  " AND FC.TIPOFACTURA !='"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA +"'"+
																			  " GROUP BY LOTE";
	
	public static String COUNT_LOTES_PENDIENTES_MERCANCIA					="SELECT COUNT (*)FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_FACTURA_CAPTURA+" AS FC WHERE FC.IDUSUARIO = ?" +
																			 " AND FC.TIPOFACTURA ='"+Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA +"'"+
																			 " GROUP BY LOTE";
}
