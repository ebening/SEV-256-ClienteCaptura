package com.adinfi.sevCaptura.resources;

public class Constants {
	
	//APLICATIVOS
	public static final int APP_CM_FINANZAS_CAPTURA 		= 1;

	public static final int VENTANA_CAPTURA_GASTOS 				= 1;
	public static final int VENTANA_LOTES 						= 2;
	public static final int VENTANA_CAPTURA_MERCANCIAS			= 3; 
	public static final int VENTANA_CARGA_MASIVA				= 4; 
	public static final int VENTA_CARGA_MASIVA_MERCANCIA 		= 6;
	public static final int VENTANA_REPORTE_CARGA				= 5;
	public static final int VENTANA_LOTES_MERCANCIA 			= 7;

	
	public static final String URL_FOLDER_ORIGENa 			= "C:\\CM_CONTENT";
	
	public static final String CONECTION_BUNDLE 			= "conexiones";
	public static final String MESSAGE_BUNDLE				= "messages";
	public static final String CONFIG_BUNDLE 				= "configuracion";
	

	public static final String ICONO_DOCUMENTO		    = 	new String("/com/adinfi/sevCaptura/resources/images/New_24x24.png");
	public static final String ICONO_CARPETA				= 	new String("/com/adinfi/sevCaptura/resources/images/Folder_24x24.png");
	public static final String ICONO_CARGA_MASIVA		= 	new String("/com/adinfi/sevCaptura/resources/images/CM_1.png");
	public static final String ICONO_CAMBIAR_URL			= 	new String("/com/adinfi/sevCaptura/resources/images/Preview_24x24.png");
	public static final String ICONO_DESCARTAR			= 	new String("/com/adinfi/sevCaptura/resources/images/Delete_24x24.png");
	public static final String ICONO_GUARDAR				= 	new String("/com/adinfi/sevCaptura/resources/images/Save-icon.png");
	public static final String ICONO_EDITAR				= 	new String("/com/adinfi/sevCaptura/resources/images/edit24x2.png");
	public static final String ICONO_BACKPAGE			= 	new String("/com/adinfi/sevCaptura/resources/images/backPage.png");
	public static final String ICONO_NEXTPAGE			= 	new String("/com/adinfi/sevCaptura/resources/images/nextPage.png");
	public static final String LOGON						= 	new String("/com/adinfi/sevCaptura/resources/images/Logon.png");


	public static final String CMD_PAGINA_SIGUIENTE					= "PaginaSiguiete";
	public static final String CMD_PAGINA_ANTERIOR					= "PaginaAnterior";
	public static final String CMD_CARGAR_ARCHIVO 					= "CargarArchivo";
	public static final String CMD_CARGAR_CARPETA 					= "CargaCarpeta";
	public static final String CMD_CARGA_MASIVA 					= "CargaMasiva";
	public static final String CMD_CAMBIAR_URL 						= "CambiarURL";
	public static final String CMD_DESCARTAR						= "Descartar";
	public static final String CMD_GUARDAR_FACTURA					= "GuardarFactura";
	public static final String CMD_EDITAR_FACTURA					= "EditarFactura";
	public static final String CMD_TIPO_DOCUMENTO_FACTURA 			= "TipoDocumentoFactura";
	public static final String CMD_TIPO_DOCUMENTO_FACTURA48hrs 		= "TipoDocumentoFactura48hrs";
	public static final String CMD_TIPO_DOCUMENTO_SOPORTE 			= "TipoDocumentoSoporte";
	public static final String CMD_CONCEPTO 						= "Concepto";
	public static final String CMD_RAZON_SOCIAL						= "RazonSocial";
	public static final String CMD_PLAZA 							= "Plaza";
	public static final String CMD_AREA 							= "Area";
	public static final String CMD_ENVIAR_FACTURA 					= "EnviarFactura";
	public static final Object CMD_ASIGNAR_FACTURA_DOCUMENTO 		= "AsignarFactDocumento";
	public static final String CMD_ACEPTAR_STATUS_CM 				= "AceptarStatusCM";
	
	
	//consultas
	public static final String ESQUEMA_DB2							= /*"DB2INST1"; */"DB2ADMIN.";
	public static final String TABLA_FACTURA_CAPTURA				="FACTURACAPTURA";
	public static final String TABLA_STATUS_CAPTURA					="FACTURASTATUSCAPTURA";
	public static final String TABLA_USUARIOS						="USUARIOS";
	public static final String TABLA_AREA							= "AREAS";
	public static final String TABLA_EMPRESA						= "EMPRESAS";
	public static final String TABLA_PLAZA 							= "PLAZACAPTURA";
	public static final String TABLA_DEPARTAMENTO 					= "DEPARTAMENTO";
	public static final String TABLA_TIPO_USUARIO 					="TIPOUSUARIOS";
	public static final String PDF_NOT_FOUND						= "/com/adinfi//resources/PDF/pdf_fileNotFound.pdf";
	public static final String TABLA_CONCEPTOS_GASTOS				= "CONCEPTOS_GASTO";
	public static final String TABLA_ADJUNTOS						="ADJUNTOS";
	public static final String TABLA_FACTURA_GASTOS 				= "FACTURAGASTO";
	public static final String TABLA_FACTURA_ADJUNTOS 				= "FACTURAADJUNTOS";
	public static final String TABLA_REPORTE_CARGA 				    = "REPORTECARGA";
	
	public static final int IMPORTA_UN_DOCUMENTO 					=1;
	public static final int IMPORTA_CARPETA 						=2;
	
	
	
	
	//estados
	public static final int STATUS_PENDIENTE_CAPTURA				=1;
	public static final int STATUS_CAPTURA_COMPLETA					=2;
	public static final int STATUS_IMPORTANDO_CM					=3;
	public static final int STATUS_IMPORTADO_A_CM					=4;
	public static final int STATUS_ERROR_IMPORTADO					=19;
	public static final int STATUS_CANCELADO						=15;
	
	
	
	
	
	public static final String STATUS_DES_PENDIENTE_CAPTURA			= "Pendiente Captura";//1
	public static final String STATUS_DES_EN_CAPTURA				="En captura";//2
	public static final String STATUS_DES_CAPTURA_COMPLETA			="Captura Completa";//3
	public static final String STATUS_DES_CAPTURA_INCOMPLETA		="Captura Incompleta";//4
	public static final String STATUS_DES_EN_IMPORTACION_CM			= "Importando a CM";//5
	public static final String STATUS_DES_IMPORTADO_A_CM			= "Importada a Content Manager";//6
	public static final String STATUS_DES_ERROR_IMPORTADO			= "Error De Importado";//6
	public static final String STATUS_DES_CANCELADO					= "Cancelado";	
	
	//TIPO DE DOCUMENTOS
	public static final String  DOCUMENTO_TIPO_FACTURA						= "FacturaNormal";
	public static final String  DOCUMENTO_TIPO_FACTURA48hrs					= "Factura48hrs";
	public static final String  DOCUMENTO_TIPO_SOPORTE						= "DocumentoDeSoporte";
	public static final String DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA  	= "FacturaMercancia";
	//TIPOS DE USUARIOS
	public static final String USUARIO_MESA_CONTROL 						= "Mesa de Control";
	
	
	public static final String ICONO_OK 				= new String("/com/adinfi/sevCaptura/resources/images/icon_ok.png");
	public static final String ICONO_WRONG			= new String("/com/adinfi/sevCaptura/resources/images/icon_wrong.png");
	public static final String ICONO_DESACTIVADO		= new String("/com/adinfi/sevCaptura/resources/images/icon_disabled.gif");
	public static final String ICONO_TRANSICION		= new String("/com/adinfi/sevCaptura/resources/images/icon_clock.png");
	public static final String ICONO_IMPORTADO		= new String("/com/adinfi/sevCaptura/resources/images/icon_importedToCM.png");
	public static final String ICONO_NO_IMPORTADO	= new String("/com/adinfi/sevCaptura/resources/images/icon_wrong.png");
	public static final String ICONO_IMPORTANDO		= new String("/com/adinfi/sevCaptura/resources/images/icon_importedToCM.png");
	public static final String ICONO_CANCELADO		= new String("/com/adinfi/sevCaptura/resources/images/icon_delete.png");

	/*ATRIBUTOS FACTURAS NORMAL*/
	public static final String ITEM_TYPE_FACTURA 		= "FactGastos_SEV";
	public static final String ATTR_NO_FACTURA			= "num_FacturaSEV";
	public static final String ATTR_NO_PROVEEDOR		= "num_ProveedorSEV";
	public static final String ATTR_NOM_PROVEEDOR		= "nom_ProveedorSEV";
	public static final String ATTR_SUC_PROVEEDOR		= "suc_ProveedorSEV";
	public static final String ATTR_RFC_PROVEEDOR		= "rfc_ProveedorSEV";
	public static final String ATTR_FECHAFACTURA		= "fecha_FactSEV";
	public static final String ATTR_FECHARECEPCION		= "fecha_RecepSEV";
	public static final String ATTR_MONTO_FACTURA		= "monto_FactSEV";
	public static final String ATTR_EMPRESA				= "empresaSEV";
	public static final String ATTR_PLAZA				= "plazaSEV";
	public static final String ATTR_AREA				= "areaSEV";
	public static final String ATTR_IDUSUARIO			= "destinatarioSEV";
	public static final String ATTR_CONCEPTO_FACTURA	= "conceptoSEV";
	public static final String ATTR_ORDEN_COMPRA		= "oc_FactSEV";
	public static final String ATTR_NOSAF				= "num_SafSEV";
	public static final String ATTR_MONEDA_FACTURA		= "monedaSEV";
	/*public static final String ATTR_TIPO_FACTURA		= "SEV_TIPO_FACTURA";
	public static final String ATTR_URL					= "SEV_URL";
	public static final String ATTR_LOTE				= "SEV_LOTE";*/

	/*ATRIBUTOS FACTURAS SOPORTE*/
	public static final String ITEM_TYPE_SOPORTE			= "SopFacGas_SEV";
	public static final String ATTR_TIPO_DOC				= "tipoDocSEV";
	public static final String ATTR_NOMBRE_SOPORTE			= "SEV_NOM_SOPORTE";
	
	/*ATRIBUTOS FACTURAS MERCANCIAL*/
	public static final String ITEM_TYPE_MERCANCIA			= "FactMerca_SEV";
	public static final String ATTR_FOLIO_RECEP				="folioRecepSEV";

	/*TIPO CAPTURAS*/
	public static final String REPORTECARGA_FACTURA_GASTO ="FACTURA GASTO";
	public static final String REPORTECARGA_FACTURA_MERCANCIA ="FACTURA MERCANCIA";
	
	public static final int   TIPO_FACTURA_GASTO 		=1;
	public static final int   TIPO_FACTURA_MERCANCIA 	=2;
	
	
	
	
	
	
	

	//Constantes Reporte Carga

  

	public static final String LISTA_TIPOCAPTURA [] = {REPORTECARGA_FACTURA_GASTO , REPORTECARGA_FACTURA_MERCANCIA};
	
	public static final int NUM_FILAS=40;
	
	public static final String ICONO_EXCEL				= 	new String("/com/adinfi/sevCaptura/resources/images/excel.png");
	

   //Constantes Reporte Carga para la exportacion a excel del reporte de carga

    public static final String  NOMBRE_ARCHIVO_REPORTECARGA 			= "ReporteCarga-";
	

	public static final String TITULO_HOJA_EXCEL_REPORTECARGA ="ReporteCarga";
	public static final String EXCEL_LOTE =   "   Lote   ";
	public static final String EXCEL_FECHACAPTURA =        " Fecha de captura     ";
	public static final String EXCEL_TIPOCAPTURA =           " Tipo de captura    ";
	public static final String EXCEL_DOCUMENTOSPROCESADOS = " Documentos procesados ";
	public static final String EXCEL_HORAINICIOCAPTURA=        " Hora inicio captura   ";
	public static final String EXCEL_FINCAPTURA=        "      Fin captura       ";
	public static final int    EXCEL_CELDA_LOTE = 0;
	public static final int    EXCEL_CELDA_FECHACAPTURA = 1;
	public static final int    EXCEL_CELDA_TIPOCAPTURA = 2;
	public static final int    EXCEL_CELDA_DOCUMENTOSPROCESADOS = 3;
	public static final int    EXCEL_CELDA_HORAINICIOCAPTURA = 4;
	public static final int    EXCEL_CELDA_FINCAPTURA = 5;
	
	public static final String NOMBRE_COLUMNA [] = {EXCEL_LOTE , EXCEL_FECHACAPTURA , EXCEL_TIPOCAPTURA , EXCEL_DOCUMENTOSPROCESADOS , EXCEL_HORAINICIOCAPTURA, EXCEL_FINCAPTURA};




	
	




	


	

	



	

	







	
	
}
