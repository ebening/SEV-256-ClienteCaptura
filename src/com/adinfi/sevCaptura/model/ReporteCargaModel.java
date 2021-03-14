package com.adinfi.sevCaptura.model;


import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.adinfi.sevCaptura.dao.ReporteCargaDAO;
import com.adinfi.sevCaptura.dao.ReporteCargaDAOInterface;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.ReporteCarga;
import com.adinfi.sevCaptura.resources.ReporteCargaExcel;
import com.adinfi.sevCaptura.resources.Utilities;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.StringEscapeUtils;
import com.adinfi.sevCaptura.entities.FiltroReporteCarga;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class ReporteCargaModel {
	
	public static Logger logger = Logger.getLogger(ReporteCargaModel.class);

	private ReporteCargaDAOInterface DAO = null;
	private JTextField textFieldLote = null;
	private JComboBox comboTipoCaptura = null;
	private JButton btnExcel = null;
	private JButton btnBuscar= null;
	private JTable tablaReporteCarga = null;
	private DefaultTableModel defaultTableModelReporteCarga = null;
	private JButton btnLimpiar=null;
	private JDateChooser dateChooserInicioCaptura;
	private JDateChooser dateChooserFechaFinCaptura;
	private DefaultComboBoxModel defaultComboTipoCaptura=null;
	private Mensaje message;
	private JInternalFrame jFrameMensajes = null;
	private String idUsuario = null;
	private 	List<ReporteCarga> resultadoLista = null;
	private String titulo="Reporte";
	private static final String LOTE_PATTERN = "^[A-Za-z0-9_-]{0,100}$";
	public ReporteCargaModel(){
		this.DAO=new ReporteCargaDAO();

	}



	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}





	public JTable getJTableDatos() {
		if (tablaReporteCarga == null) {
			tablaReporteCarga = new JTable(getTableModelDatos()){ 
				public boolean isCellEditable ( int rowIndex, int colIndex ) 
				{return false;}
			};
			tablaReporteCarga.setRowHeight(20);
			tablaReporteCarga.setEnabled(true);
			tablaReporteCarga.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaReporteCarga.getTableHeader().setReorderingAllowed(false);

			TableColumn columna = tablaReporteCarga.getColumnModel().getColumn(0);
			columna.setPreferredWidth(30);
			columna.setResizable(false);

		}
		return tablaReporteCarga;
	}

	private DefaultTableModel getTableModelDatos(){
		if(defaultTableModelReporteCarga == null){
			String[] lista = {"Lote","Fecha de captura","Tipo de captura", "Doc procesados", "Hora inicio captura", "Fin captura"};
			int consultaReg=0;
			consultaReg=this.consultaReg();

			if(consultaReg <= Constants.NUM_FILAS){
				defaultTableModelReporteCarga = new DefaultTableModel(lista, Constants.NUM_FILAS);
			}else{
				defaultTableModelReporteCarga = new DefaultTableModel(lista, consultaReg);	
			}
		
		}
		return defaultTableModelReporteCarga;
	}

	


	public void limpiarResultados(){

		if(this.resultadoLista!=null){
			int filas = this.resultadoLista.size();
		
			for (int i = 0; i < filas; i++) {
				this.defaultTableModelReporteCarga.setValueAt("", i, 0);
				this.defaultTableModelReporteCarga.setValueAt("", i, 1);
				this.defaultTableModelReporteCarga.setValueAt("", i, 2);
				this.defaultTableModelReporteCarga.setValueAt("", i, 3);
				this.defaultTableModelReporteCarga.setValueAt("", i, 4);
				this.defaultTableModelReporteCarga.setValueAt("", i, 5);
			}

		}

		this.btnExcel.setEnabled(false);

	}


	public void limpiarCamposCaptura() {
		this.textFieldLote.setText("");
		this.dateChooserInicioCaptura.setDate(null);
		this.dateChooserFechaFinCaptura.setDate(null);
		this.comboTipoCaptura.setSelectedIndex(0);
		if(this.resultadoLista!= null && this.resultadoLista.isEmpty()){
			this.resultadoLista.clear();
			
		}
	}

	public void mostrarReporte() {

		if(this.validarCampos()){

			limpiarResultados();

			FiltroReporteCarga filtroReporteCarga=new FiltroReporteCarga();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

			int comboIndex=this.comboTipoCaptura.getSelectedIndex();
			String comboItem=(String) this.comboTipoCaptura.getItemAt(comboIndex);
			filtroReporteCarga.setTipoCaptura(comboItem);



			String lote=this.textFieldLote.getText();
			if(lote!=null){
				lote=lote.trim();
			}

			if(!lote.isEmpty()){

				filtroReporteCarga.setLote(lote);
			}

			Date fechaInicioCaptura=dateChooserInicioCaptura.getDate();
			if(fechaInicioCaptura != null){
				filtroReporteCarga.setFechaInicioCaptura( formato.format(fechaInicioCaptura));
			}

			Date fechaFinCaptura=dateChooserFechaFinCaptura.getDate();
			if(fechaFinCaptura != null){

				filtroReporteCarga.setFechaFinCaptura( formato.format(fechaFinCaptura));
			}



			this.resultadoLista = new ArrayList<ReporteCarga>();
			if(this.establecerConexionDB2()){
				try {

					resultadoLista =DAO.getReporteCargaIdUsuario(this.getIdUsuario(), filtroReporteCarga);
					if( resultadoLista.size()!=0){
						this.btnExcel.setEnabled(true);
						for(int i = 0; i < resultadoLista.size(); i++){
							ReporteCarga reporteCarga = new ReporteCarga();
							reporteCarga = resultadoLista.get(i);
							this.defaultTableModelReporteCarga.setValueAt((reporteCarga.getLote()!=null)?reporteCarga.getLote():"",i,0);
							this.defaultTableModelReporteCarga.setValueAt((reporteCarga.getFechaCaptura()!=null)?reporteCarga.getFechaCaptura():"",i,1);
							this.defaultTableModelReporteCarga.setValueAt((reporteCarga.getTipoCaptura()!=null)?reporteCarga.getTipoCaptura():"",i,2);
							this.defaultTableModelReporteCarga.setValueAt( (reporteCarga.getDocumentosProcesados()!=null)?reporteCarga.getDocumentosProcesados():"",i,3);
							this.defaultTableModelReporteCarga.setValueAt( (reporteCarga.getHoraInicioCaptura()!=null)?reporteCarga.getHoraInicioCaptura():"",i,4);
							this.defaultTableModelReporteCarga.setValueAt( (reporteCarga.getFinCaptura()!=null)?reporteCarga.getFinCaptura():"",i,5);

						}
					}

					else{

						this.btnExcel.setEnabled(false);
			

						message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG023"),titulo,1);
						this.showMessage(this.getMensaje());
					}
				} catch (SQLException e) {
					logger.info("Error al intentar traer el resultado de la consulta del reporte por filtro");
					e.printStackTrace();
				}
				this.cerrarConexionDB2();
			}
		}



	}

	private int consultaReg(){
		int consultaReg=0;

		String idUsuario=this.getIdUsuario();

		

		if( idUsuario!=null)
			if(this.establecerConexionDB2()){
				try {

					consultaReg =DAO.getReporteTotalReg(this.getIdUsuario());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.cerrarConexionDB2();
			}

		return consultaReg;

	}

	private boolean validarCampos(){
		boolean valida=true;
		boolean bmensaje=false;
		StringBuffer sbmensaje=new StringBuffer("");

		String mensajeCombo="Seleccione Tipo de Captura ";
		String mensajeLote=" \n Campo Lote contiene caracteres no validos..";
		int comboIndex=this.comboTipoCaptura.getSelectedIndex();
		if (comboIndex==0){
			sbmensaje.append(mensajeCombo);
			bmensaje=true;
			valida=false;       

		}
		if(!esLoteValido()){
			sbmensaje.append(mensajeLote);
			bmensaje=true;
			valida=false;
		}


		if(bmensaje){

			message= new Mensaje(sbmensaje.toString(),this.titulo,1);
			this.showMessage(this.getMensaje());

		}

		return valida;
	}


	private boolean esLoteValido(){
		boolean valida=false;
		Pattern pattern;
		Matcher matcher;



		String lote=this.textFieldLote.getText();

		if(lote==null ){
			valida=true;
			return valida;
		}

		if(lote!=null){
			lote=lote.trim();
		}
		
		if("".equals(lote)){
			valida=true;
			return valida;
			
		}
		

		if(!lote.isEmpty()){

			pattern = Pattern.compile(LOTE_PATTERN);
			matcher = pattern.matcher(lote);
			valida=matcher.matches();

		}

		return valida; 

	}


	public void mostrarExcel(){
	
		File file = null;
		JFileChooser fileChooser = new JFileChooser();	
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int var = fileChooser.showDialog(this.jFrameMensajes, "Aceptar");
		if(var == JFileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
		}

		String path=null;
		if(file!=null){
			path=StringEscapeUtils.escapeJava(file.toString());
		}

	

		ReporteCargaExcel reporteCargaExcel= new ReporteCargaExcel();
		reporteCargaExcel.exportarExcel(this.resultadoLista, path); 	


	}





	public Mensaje getMensaje(){
		return this.message;
	}

	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}

	public void cerrarConexionDB2(){
		try {

			DAO.cerrarConexionDB2();

		} catch (SQLException e) {
			logger.info("Error al intentar hacer conexion con DB2."+e.getMessage());
		}
	}

	public boolean establecerConexionDB2(){
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;

		try {

			conexionAbierta = DAO.establecerConexionDB2();

		} catch (SQLException e) {
			logger.info("Error al intentar hacer conexion con DB2."+e.getMessage());
			seGeneroExcepcion = true;

		} catch (InstantiationException e) {
			logger.info("Error al intentar hacer conexion con DB2."+e.getMessage());
			seGeneroExcepcion = true;

		} catch (IllegalAccessException e) {
			logger.info("Error al intentar hacer conexion con DB2."+e.getMessage());
			seGeneroExcepcion = true;

		} catch (ClassNotFoundException e) {
			logger.info("Error al intentar hacer conexion con DB2."+e.getMessage());
			seGeneroExcepcion = true;
		}

		if(seGeneroExcepcion){

		}

		return conexionAbierta;

	}


	public JTextField getJTextFieldLote() {
		if(textFieldLote == null){
			textFieldLote = new JTextField();
		}
		return textFieldLote;
	}


	public JComboBox getComboTipoCaptura() {
		if(comboTipoCaptura == null){
			comboTipoCaptura = new JComboBox(getCbxModelTipoCaptura());
		}
		return comboTipoCaptura;
	}


	public DefaultComboBoxModel getCbxModelTipoCaptura(){
		if(defaultComboTipoCaptura==null){
			defaultComboTipoCaptura= new DefaultComboBoxModel();
			defaultComboTipoCaptura.addElement("Seleccione una opción");	

			for(int i = 0; i < 2; i++){	
				defaultComboTipoCaptura.addElement( (Constants.LISTA_TIPOCAPTURA[i]));
			}
		}
		return defaultComboTipoCaptura;
	}


	public JButton getBtnExcel() {
		if(btnExcel==null){
			btnExcel= new JButton("Procesar");
		}
		return btnExcel;
	}




	public JButton getBtnBuscar() {
		if(btnBuscar==null){
			btnBuscar= new JButton("Buscar");
		}	
		return btnBuscar;
	}

	public JButton getBtnLimpiar() {
		if(btnLimpiar==null){
			btnLimpiar= new JButton("Limpiar");
		}	
		return btnLimpiar;
	}

	public JDateChooser getJDateChooserInicioCaptura(){
		if(dateChooserInicioCaptura==null){
			dateChooserInicioCaptura=new JDateChooser();
		}
		return dateChooserInicioCaptura;
	}

	public JDateChooser getJDateChooserFechaFinCaptura(){
		if(dateChooserFechaFinCaptura==null){
			dateChooserFechaFinCaptura=new JDateChooser();
		}
		return dateChooserFechaFinCaptura;
	}






}
