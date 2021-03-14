package com.adinfi.sevCaptura.resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.log4j.Logger;

import com.adinfi.sevCaptura.entities.*;


public class ReporteCargaExcel {

	public static Logger logger = Logger.getLogger(ReporteCargaExcel.class);
	HSSFWorkbook objWB = null;
	HSSFSheet hoja1;
	HSSFCellStyle estiloCelda;
	int posicionFila = 0;

	public ReporteCargaExcel() {
		objWB = new HSSFWorkbook();
		hoja1 = objWB.createSheet(Constants.TITULO_HOJA_EXCEL_REPORTECARGA);

	}

	public void exportarExcel(List<ReporteCarga> peticionesResult, String path) {

		if (peticionesResult != null && path != null) {
			try {
				File dir = new File(path);

				String nombreArchivoExcel = Constants.NOMBRE_ARCHIVO_REPORTECARGA;
				;
				Date fecha = new Date();
				SimpleDateFormat formato = new SimpleDateFormat(
						"ddMMyyyy_hmmssSS");
				nombreArchivoExcel = nombreArchivoExcel + formato.format(fecha)
						+ ".xls";

				FileOutputStream fileOut = new FileOutputStream(new File(dir,
						nombreArchivoExcel));

				EncabezadoExcel();

				ReporteCarga reporteCarga = new ReporteCarga();
				Iterator<ReporteCarga> iterador = peticionesResult.iterator();
				while (iterador.hasNext()) {
					posicionFila = posicionFila + 1;
					int poscCelda = 0;
					reporteCarga = (ReporteCarga) iterador.next();
					HSSFRow filaRegistro = hoja1
							.createRow((short) posicionFila);
					HSSFCellStyle estiloCelda = null;

					if (reporteCarga != null) {
						objWB.createDataFormat();
						// LOTE
						if (reporteCarga.getLote() != null) {

							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_LOTE,
									reporteCarga.getLote());
						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_LOTE, " ");
						}

						if (reporteCarga.getFechaCaptura() != null) {

							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_FECHACAPTURA,
									reporteCarga.getFechaCaptura());
						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_FECHACAPTURA, " ");
						}

						if (reporteCarga.getTipoCaptura() != null) {

							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_TIPOCAPTURA,
									reporteCarga.getTipoCaptura());
						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_TIPOCAPTURA, " ");
						}

						if (reporteCarga.getDocumentosProcesados() != null) {

							setNumero(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_DOCUMENTOSPROCESADOS,
									Integer.parseInt(reporteCarga
											.getDocumentosProcesados()));

						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_DOCUMENTOSPROCESADOS,
									" ");
						}

						if (reporteCarga.getHoraInicioCaptura() != null) {

							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_HORAINICIOCAPTURA,
									reporteCarga.getHoraInicioCaptura());
						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_HORAINICIOCAPTURA,
									" ");
						}

						if (reporteCarga.getFinCaptura() != null) {

							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_FINCAPTURA,
									reporteCarga.getFinCaptura());
						} else {
							setTexto(filaRegistro, estiloCelda,
									Constants.EXCEL_CELDA_FINCAPTURA, " ");
						}

					}
					poscCelda = poscCelda + 1;

				}// while
				objWB.write(fileOut);
				fileOut.flush();
				fileOut.close();

				Runtime rt = Runtime.getRuntime();
				rt.exec("cmd.exe /C start " + path + "\\" + nombreArchivoExcel);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.info("Error al intentar crear el archivo excel del Reporte de Carga"
						+ e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error al intentar crear el archivo excel del Reporte de Carga"
						+ e.getMessage());
			}

		}

	}

	// -----Este metodo genera el encabezado del fichero Excel
	public void EncabezadoExcel() {
		// Se genera la primera fila con el encabezado
		HSSFCellStyle estiloEncabezado = estiloEncabezado();

		

		// Fila de titulos de las columnas
		HSSFRow fila = hoja1.createRow((short) posicionFila);

		// ----Se crea la cabecela del archivo excel
		for (int i = 0; i < 6; i++) {
			HSSFCell celda = fila.createCell(i);
			celda.setCellStyle(estiloEncabezado);
			celda.getCellStyle().setWrapText(false);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			// Finalmente, se setea el valor
			celda.setCellValue(Constants.NOMBRE_COLUMNA[i]);
			hoja1.autoSizeColumn(i);
		}
	}

	public HSSFCellStyle estiloEncabezado() {
		// Primero, establecemos el tipo de fuente
		HSSFFont fuente = objWB.createFont();
		fuente.setFontHeightInPoints((short) 11);
		fuente.setFontName(HSSFFont.FONT_ARIAL);
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Luego creamos el objeto que se encargará de aplicar el estilo a la
		// celda
		HSSFCellStyle estiloCelda = objWB.createCellStyle();
		estiloCelda.setWrapText(true);
		estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCelda.setFont(fuente);

		// También, podemos establecer bordes...
		estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		estiloCelda.setBottomBorderColor((short) 8);
		estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		estiloCelda.setLeftBorderColor((short) 8);
		estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		estiloCelda.setRightBorderColor((short) 8);
		estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		estiloCelda.setTopBorderColor((short) 8);

		// Establecemos el tipo de sombreado de nuestra celda
		estiloCelda.setFillForegroundColor((short) 22);
		estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return estiloCelda;
	}

	// ------Este metodo define el estilo de las celdas
	public HSSFCellStyle estiloCelda() {

		if (estiloCelda == null) {
			// Primero, establecemos el tipo de fuente
			HSSFFont fuente = objWB.createFont();
			fuente.setFontHeightInPoints((short) 8);
			fuente.setFontName(HSSFFont.FONT_ARIAL);

			// Luego creamos el objeto que se encargará de aplicar el estilo a
			// la celda
			estiloCelda = objWB.createCellStyle();
			estiloCelda.setWrapText(true);
			estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			estiloCelda.setFont(fuente);

			// También, podemos establecer bordes...
			estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			estiloCelda.setBottomBorderColor((short) 8);
			estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
			estiloCelda.setLeftBorderColor((short) 8);
			estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			estiloCelda.setRightBorderColor((short) 8);
			estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			estiloCelda.setTopBorderColor((short) 8);
		}
		return estiloCelda;
	}

	// Este metodo establece un elemento texto en una celda
	public void setTexto(HSSFRow filaRegistro, HSSFCellStyle estiloCelda,
			int posc, String valor) {
		HSSFCell celda = filaRegistro.createCell(posc);
		estiloCelda = estiloCelda();
		estiloCelda.setWrapText(true);
		celda.setCellStyle(estiloCelda);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellValue(valor);
	}

	// Este metodo establece un elemento numero en una celda
	public void setNumero(HSSFRow filaRegistro, HSSFCellStyle estiloCelda,
			int posc, int valor) {
		HSSFCell celda = filaRegistro.createCell(posc);
		estiloCelda = estiloCelda();
		estiloCelda.setWrapText(true);
		celda.setCellStyle(estiloCelda);
		celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		celda.setCellValue(valor);
	}

	// Este metodo establece un elemento fecha en una celda
	public void setFecha(HSSFRow filaRegistro, HSSFCellStyle estiloCelda,
			int posc, Date valor) {
		HSSFDataFormat df = objWB.createDataFormat();
		HSSFCell celda = filaRegistro.createCell(posc);
		estiloCelda = estiloCelda();
		estiloCelda.setWrapText(true);
		estiloCelda.setDataFormat(df.getFormat("dd/mm/yyyy"));
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(valor);
	}
}