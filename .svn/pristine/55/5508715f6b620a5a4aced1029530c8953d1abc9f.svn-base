package com.adinfi.sevCaptura.view;


import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.adinfi.sevCaptura.entities.ReporteCarga;
import com.adinfi.sevCaptura.resources.ReporteCargaExcel;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;


public class AbsCoordinate{
	private static JTextField textFieldLote;
	private static JDateChooser dateChooserInicioCaptura;
	private static JDateChooser dateChooserFechaFinCaptura;
  public static void main(String[] args) {
  JFrame frame = new JFrame();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel panel = new JPanel();
  panel.setLayout(null);

  
  JTable tablaReporteCarga; 
  JScrollPane scrollpaneReporteCarga;




  //definimos un modelo de tabla estandar
 /* model = new DefaultTableModel(new Object[][]{{""}},new String[]{"Lote", 
		  "Fecha de captura","Tipo de captura", "Doc procesados", "Hora inicio captura", "Fin captura"});
  *///creamos la tabla con el modelo de tabla estandar; DefaultTableModel
  
  Vector encabezadosTabla = new Vector();
  encabezadosTabla.add(0, "Lote");
  encabezadosTabla.add(1, "Fecha de captura");
  encabezadosTabla.add(2, "Tipo de captura");
  encabezadosTabla.add(3,"Doc procesados");
  encabezadosTabla.add(4,"Hora inicio captura");
  encabezadosTabla.add(5,"Fin captura");
  
  Vector dataTabla = new Vector();
  Vector row= new Vector();
  row.addElement("2012-23");
  row.addElement("2012-08-15");
  row.addElement("FACTURA GASTO");
  row.addElement("20");
  row.addElement("2012-08-15 09:33:20");
  row.addElement("2012-08-15 18:10:20");
  
  dataTabla.addElement(row);

  String[] lista = {"Lote","Fecha de captura","Tipo de captura", "Doc procesados", "Hora inicio captura", "Fin captura"};
	//model = new DefaultTableModel(lista, 50);
 
 // tabla = new JTable(model);
  tablaReporteCarga = new JTable(dataTabla,encabezadosTabla);
   tablaReporteCarga.setFont(new Font("Tahoma", Font.BOLD, 9));
	

  //agregar el ScrollPane a la tabla
   scrollpaneReporteCarga=new JScrollPane();
   scrollpaneReporteCarga.setViewportView(tablaReporteCarga);
   scrollpaneReporteCarga.setBounds(230,100 , 750, 500);
  
  
 /* label1.setBounds(100, 50, 100, 50);
  field.setBounds(75, 100, 200, 25);
  btnBuscar.setBounds(40, 200, 100, 25);
  btnLimpiar.setBounds(150, 200, 100, 25);
 */ panel.add(scrollpaneReporteCarga);
  //panel.add(label1);
  //panel.add(field);
 // panel.add(btnBuscar);
 // panel.add(btnLimpiar);
  frame.getContentPane().add(panel);
  
  JLabel lblExportarExcel = new JLabel("Exportar a XLS");
  lblExportarExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
  lblExportarExcel.setBounds(833, 63, 99, 14);
  panel.add(lblExportarExcel);
  
  JButton btnExcel = new JButton(new ImageIcon("C:\\prueba\\Excel\\dir1\\dir2\\excel.png"));
  btnExcel.setBounds(770, 49, 53, 40);
  btnExcel.setBorder(BorderFactory.createEmptyBorder());
  btnExcel.setContentAreaFilled(false); 

  btnExcel.addActionListener(new ActionListener() {
	  
      public void actionPerformed(ActionEvent e)
      {
          //Execute when button is pressed
          System.out.println("You clicked the button");
          
          List<ReporteCarga> resultadoLista = new ArrayList<ReporteCarga>();
          
          ReporteCarga reporteCarga = new ReporteCarga();
          reporteCarga.setLote("2012-23");
          reporteCarga.setFechaCaptura("2012-08-15");
          reporteCarga.setTipoCaptura("FACTURA GASTO");
          reporteCarga.setDocumentosProcesados("20");
          reporteCarga.setHoraInicioCaptura("2012-08-15 09:33:20");
          reporteCarga.setFinCaptura("2012-08-15 18:10:20");
          resultadoLista.add(reporteCarga);
          String path="C:\\prueba\\Excel\\dir1\\dir2";
          
          

          ReporteCargaExcel reporteCargaExcel= new ReporteCargaExcel();
          reporteCargaExcel.exportarExcel(resultadoLista,path); 
          
          
      }
  });      

    
  panel.add(btnExcel);
  
  JComboBox comboTipoCaptura = new JComboBox();
  comboTipoCaptura.setBounds(36, 98, 169, 20);
  panel.add(comboTipoCaptura);
  
  JLabel lblTipoCaptura = new JLabel("Tipo de captura");
  lblTipoCaptura.setBounds(36, 75, 107, 14);
  panel.add(lblTipoCaptura);
  
  JLabel lblLote = new JLabel("Lote");
  lblLote.setBounds(46, 129, 46, 14);
  panel.add(lblLote);
  
  textFieldLote = new JTextField();
  textFieldLote.setBounds(36, 154, 152, 20);
  panel.add(textFieldLote);
  textFieldLote.setColumns(10);
  
  JLabel lblFechaInicioCaptura = new JLabel("Fecha Inicio de captura");
  lblFechaInicioCaptura.setBounds(36, 204, 152, 14);
  panel.add(lblFechaInicioCaptura);
  
    dateChooserInicioCaptura = new JDateChooser();
	//jDateChooserFechaRecepcion = model.getJDateChooserFechaRecepcion();
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	dateChooserInicioCaptura.setCalendar(gregorianCalendar);
	dateChooserInicioCaptura.setDateFormatString("dd-MM-yyyy");
	dateChooserInicioCaptura.setBounds(new Rectangle(36, 229, 159, 20));
	dateChooserInicioCaptura.setDateFormatString(Utilities.nowDate());
	dateChooserInicioCaptura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
		public void propertyChange(java.beans.PropertyChangeEvent e){
			Calendar calendar = Calendar.getInstance();
		}
	});
	dateChooserInicioCaptura.setInheritsPopupMenu(true);
	dateChooserInicioCaptura.setEnabled(true);
	panel.add(dateChooserInicioCaptura);
	
	
	
	
	
  
  JLabel lblFechaFinalCaptura = new JLabel("Fecha de Final de captura");
  lblFechaFinalCaptura.setBounds(39, 283, 166, 14);
  panel.add(lblFechaFinalCaptura);
  
  
  dateChooserFechaFinCaptura = new JDateChooser();
	//jDateChooserFechaRecepcion = model.getJDateChooserFechaRecepcion();
	GregorianCalendar gregorianCalendarFin = new GregorianCalendar();
	dateChooserFechaFinCaptura.setCalendar(gregorianCalendarFin);
	dateChooserFechaFinCaptura.setDateFormatString("dd-MM-yyyy");
	dateChooserFechaFinCaptura.setBounds(new Rectangle(36, 308, 159, 20));
	dateChooserFechaFinCaptura.setDateFormatString(Utilities.nowDate());
	dateChooserFechaFinCaptura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
		public void propertyChange(java.beans.PropertyChangeEvent e){
			Calendar calendar = Calendar.getInstance();
		}
	});
	dateChooserFechaFinCaptura.setInheritsPopupMenu(true);
	dateChooserFechaFinCaptura.setEnabled(true);
	panel.add(dateChooserFechaFinCaptura);
	
	JButton btnBuscar = new JButton("Buscar");
	btnBuscar.setBounds(10, 359, 89, 23);
	panel.add(btnBuscar);
	
	JButton btnLimpiar = new JButton("Limpiar");
	btnLimpiar.setBounds(109, 359, 89, 23);
	panel.add(btnLimpiar);

  frame.setBounds(100, 100, 1000, 700);
  frame.setVisible(true);
  frame.setResizable(false);

  frame.setTitle("Reporte");
	
  
  }
}