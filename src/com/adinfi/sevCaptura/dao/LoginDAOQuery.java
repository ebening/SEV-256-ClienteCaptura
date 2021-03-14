package com.adinfi.sevCaptura.dao;

import com.adinfi.sevCaptura.resources.Constants;

public class LoginDAOQuery {
	
	
	public static final String SELECT_BUSQUEDA_USUARIO 				="Select count(*) from "+Constants.ESQUEMA_DB2+Constants.TABLA_USUARIOS  +" where NOEMPLEADO =? and PASSEMPLEADO=?";
	
	public static final String SELECT_ID_USUARIO 					="Select IDUSUARIO  from "+Constants.ESQUEMA_DB2+Constants.TABLA_USUARIOS+" USUARIOS where NOEMPLEADO=? and PASSEMPLEADO=?";

	public static final String SELECT_TIPO_USUARIO 					="Select * from "+Constants.ESQUEMA_DB2+Constants.TABLA_USUARIOS+
																		"  AS US LEFT JOIN "+Constants.ESQUEMA_DB2+Constants.TABLA_TIPO_USUARIO +"  AS TP  ON US.IDTIPOUSUARIO=TP.IDTIPOUSUARIO WHERE US.IDUSUARIO=?";
	

	public static final String SELECT_USER_BY_USER_NAME 			= "SELECT * FROM "+Constants.ESQUEMA_DB2+"Usuarios AS us " +
																		"LEFT JOIN "+Constants.ESQUEMA_DB2+"Areas AS ar " +
																		"ON ar.id_area = us.id_area " +
																		"LEFT JOIN "+Constants.ESQUEMA_DB2+"Puestos AS pt " +
																		"ON pt.id_puesto = us.id_puesto " +
																		"WHERE  us.id_usuario = ? and  us.pass_empleado=? and us.activo=1 ";
	
	public static final String SELECT_PERMISOS_APLICATIVOS 			= "SELECT * FROM "+Constants.ESQUEMA_DB2+"Permisos_Aplicativos AS pa " +
																		"LEFT JOIN "+Constants.ESQUEMA_DB2+"Cat_Aplicativos AS ca " +
																		"ON pa.id_aplicativo = ca.id_aplicativo " +
																		"WHERE pa.id_tipo_usuario = ?";
	
	public static final String SELECT_PERMISOS_MODULOS				= "SELECT * FROM "+Constants.ESQUEMA_DB2+"Permisos_Modulos AS pm " +
																		"LEFT JOIN "+Constants.ESQUEMA_DB2+"Cat_Modulos AS cm " +
																		"ON pm.id_modulo = cm.id_modulo " +
																		"WHERE pm.id_tipo_usuario = ?";
	
	public static final String SELECT_USER_BY_USER_PASS 			= "SELECT * FROM "+Constants.ESQUEMA_DB2+"Usuarios " +
																		"WHERE noEmpleado = ? AND passEmpleado = ?";

	public static final String UPDATE_USER_PASS 					= "UPDATE "+Constants.ESQUEMA_DB2+"Usuarios SET " +
																		"pass_Empleado = ? WHERE id_usuario = ?";
}
 