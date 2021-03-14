package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class ReporteCargaDAOQuery {
  public static final String SELECT_REPORTE_CARGA_IDUSUARIO				= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_REPORTE_CARGA+ " WHERE ID_USUARIO= ? ORDER BY FECHACAPTURA DESC";
  
  public static final String SELECT_REPORTE_CARGA_FILTRO				= "SELECT * FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_REPORTE_CARGA + " WHERE ID_USUARIO= " ;
  
  public static final String SELECT_REPORTE_CARGA_IDUSUARIO_TOTALREG	= "SELECT COUNT(*) AS COUNTREG FROM "+Constants.ESQUEMA_DB2+Constants.TABLA_REPORTE_CARGA+ " WHERE ID_USUARIO= ?";

}