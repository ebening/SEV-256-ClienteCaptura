package com.adinfi.sevCaptura.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import com.adinfi.sevCaptura.dao.CapturaMercanciaDAO;
import com.adinfi.sevCaptura.dao.CapturaMercanciaDAOInterface;
import com.adinfi.sevCaptura.entities.Area;
import com.adinfi.sevCaptura.entities.Concepto;
import com.adinfi.sevCaptura.entities.Destinatario;
import com.adinfi.sevCaptura.entities.Empresa;
import com.adinfi.sevCaptura.entities.Estado;
import com.adinfi.sevCaptura.entities.Factura;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.entities.Plaza;
import com.adinfi.sevCaptura.entities.Proveedor;
import com.adinfi.sevCaptura.entities.ReporteCarga;
import com.adinfi.sevCaptura.entities.Usuario;
import com.adinfi.sevCaptura.resources.ADComboBoxModel;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.ImageRenderer;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;

public class CapturaMercanciaModel {
	
	public static Logger logger = Logger.getLogger(CapturaMercanciaModel.class);
	public static String workDir = System.getProperty("user.dir");
	private JTable jTableDatosFact=null;
	private DefaultTableModel defaultTableModelDatos = null;
	private JRadioButton rdbtnTipoFactura=null;
	private ButtonGroup  rdBtnsTipoDocumento=null;
	private JTextField txtNoFact=null;
	private JFormattedTextField txtNoProveedor=null;
	private JTextField txtNombreProv=null;
	private JComboBox txtSucursalProv = null;
	private ADComboBoxModel jComboModel = null;
	private JTextField txtMonto=null;
	private JTextField txtNombreDoc=null;
	private JComboBox cbxRazonSocial=null;
	private DefaultComboBoxModel defaultComboEmpresa=null;
	private DefaultComboBoxModel defaultComboDestinatario=null;
	private JComboBox cbxEnviarFactura=null;
	private JTextField txtordenCompra=null;
	private JTextField txtNoSAF=null;
	private JTextField txtRfcProveedor=null;
	private JTextField txtFolioRecepcion=null;
	private JTextField txtUrl=null;
	private JDateChooser jDateChooserFechaRecepcion = null;
	private JDateChooser jDateChooserFechaFactura = null;
	private JButton btnCargarCarpeta=null;
	private JButton btnCargarArchivo=null;
	public JButton btnCargaMasiva=null;
	private JButton btnCambiarURL=null;
	private JButton btnDescartar=null;
	private JButton btnAnteriorPag=null;
	private JButton btnSiguientePag=null;
	private JButton btnAceptarJDialogSop=null;
	private JTextField lblActualPag=null;
	private JTextField lblTotalPag=null;
	private JButton btnGuardar =null;
	private JTextArea txtAreaVisor=null;
	private CapturaMercanciaDAOInterface DAO = null;
	private ArrayList<Factura> listaFacturasCapturadas=new ArrayList<Factura>();
	private ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
	private ArrayList<Empresa> listaEmpresas=null;
	private JInternalFrame jFrameMensajes = null;
	private JDialog jDialogDocumentoSoporte = null;
	private int activaCargaMasiva=0;
	//private String mensaje;
	private Mensaje message;
	private int fila=0;
	boolean checkSeleccionado=false;
	private int regPorPagina = 0;
	private int totalRegistros = 0;
	private int totalPaginas = 0;
	private int paginaActual = 0;
	private int indiceInf = 0;
	private int indiceSup = 0;
	boolean primeraCaptura = true;
	private boolean ultimaCaptura=false;
	private String idUsuario=null;
	/**ATRIBUTOS PARA EL CONTROL DE LA CLASE**/
	private int filaSeleccionada = 0; 	
	private String titulo = "Sistema de Captura";
	private boolean activarPrimerRegistro = false;
	private  String sucursalAnterior=null;
	
	private JComboBox<String> cmbPlaza=null;
	
	public CapturaMercanciaModel (){
		this.DAO=new CapturaMercanciaDAO();
		this.regPorPagina = Integer.valueOf(BundleManager.getValue(Constants.CONFIG_BUNDLE, "reg.por.pagina2"));
		
	}
	public Mensaje getMensaje(){	
		return this.message;
	}
	

	public String lote = null;
	public String plaza= null;
	public String getPlaza() {
		return plaza;
	}
	public void setLote(String lote){
		this.lote = lote;
	}
	
	public String getLote(){
		return lote;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	public String getIdUsuario(){
		return this.idUsuario;
	}
	public void setIdUsuario(String idUsuario){
		this.idUsuario=idUsuario;
	}
	
	public int getNumRegistros(){
		return this.listaFacturas.size();
	}
	public String getUrl() {
		
		String url=this.listaFacturas.get(filaSeleccionada).getUrl();
		//log System.out.println("Url a mostrar en el visor: "+url);
	
	return url;
	}

	public JTextField gettxtUrl() {
		if (txtUrl == null) {
			txtUrl = new JTextField();
		}
		return txtUrl;
	}

	
	public JTable getJTableDatos() {
		if (jTableDatosFact == null) {
			jTableDatosFact = new JTable(getTableModelDatos()){ 
				public boolean isCellEditable ( int rowIndex, int colIndex ) 
				{return false;}
			};
			jTableDatosFact.setRowHeight(20);
			jTableDatosFact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableDatosFact.getTableHeader().setReorderingAllowed(false);
			
			int columnas = defaultTableModelDatos.getColumnCount();
            
            TableColumn columna = null;
			for (int i = 0; i < columnas; i++) {
				columna = jTableDatosFact.getColumnModel().getColumn(i);

				if(i == 0){
			    	columna.setPreferredWidth(30);
			    }else if(i == 1){
			    	columna.setPreferredWidth(160);
			    }else if(i == 2){
			    	columna.setPreferredWidth(20);
					columna.setCellRenderer(new ImageRenderer());
			    }
			    columna.setResizable(false);
			}
			
		}
		return jTableDatosFact;
	}
	
	public DefaultTableModel getTableModelDatos(){
		if(defaultTableModelDatos == null){
			String[] lista = {"id","Folio Recepción", "     "};
			defaultTableModelDatos = new DefaultTableModel(lista, 20);
		}
		return defaultTableModelDatos;
	}

	public JButton getbtnCargarCarpeta(){
		if(btnCargarCarpeta==null){
			btnCargarCarpeta = new JButton("");
		}
		return btnCargarCarpeta;
	}
	
	public JButton getbtnCargarArchivo(){
		if(btnCargarArchivo==null){
			btnCargarArchivo = new JButton("");
		}
		return btnCargarArchivo;
	}
	public JButton getBtnCargaMasiva(){
		if(btnCargaMasiva==null){
			btnCargaMasiva= new JButton("");
		}
		return btnCargaMasiva;
	}
	public JButton getBtnCambiarURL(){
		if(btnCambiarURL==null){
			btnCambiarURL= new JButton("");
		}
		return btnCambiarURL;
	}
	
	public JButton getBtnDescartar(){
		if(btnDescartar==null){
			btnDescartar= new JButton("");
		}
		return btnDescartar;
	}
	
	
	public JTextField getTxtNoFact() {
		if (txtNoFact == null) {
			txtNoFact = new JTextField();
		}
		return txtNoFact;
	}

	public JTextField getTxtNombreProv() {
		if (txtNombreProv == null) {
			txtNombreProv = new JTextField();
			
		}
		return txtNombreProv;
	}

	public JComboBox getTxtSucursalProv() {
		if (txtSucursalProv == null) {
			txtSucursalProv = new JComboBox(getJComboModel());
		}
		return txtSucursalProv;
	}
	
	private ADComboBoxModel getJComboModel() {
		if(jComboModel==null){
			jComboModel=new ADComboBoxModel(new String[]{""});
		}
		return jComboModel;
	}
	
	public JTextField getTxtMonto() {
		if (txtMonto == null) {
			txtMonto = new JTextField();
		}
		return txtMonto;
	}

	public JComboBox getCbxRazonSocial() {
		if (cbxRazonSocial == null) {
			cbxRazonSocial = new JComboBox(getCbxModelEmpresa());
		}
		return cbxRazonSocial;
	}
	
	
	
	public DefaultComboBoxModel getCbxModelEmpresa(){
		if(defaultComboEmpresa==null){
			defaultComboEmpresa= new DefaultComboBoxModel();
			defaultComboEmpresa.addElement("Seleccione una opcion");
			this.llenarComboEmpresa();
			for(int i=0; i<listaEmpresas.size();i++){
				defaultComboEmpresa.addElement(listaEmpresas.get(i).getNombre());
				
			}
		}
		return defaultComboEmpresa;
	}
	public JComboBox<String> getCbxPlaza() {
		if (cmbPlaza == null) {
			cmbPlaza = new JComboBox<String>();
			cmbPlaza.addItem("<Seleccione una opción>");
			cmbPlaza.setSelectedIndex(0);
			this.llenaComboBoxPlaza();
		}
		return cmbPlaza;
	}
	
	/**
	 * @author Adan
	 * Método que cnsulta la base de datos de db2 para extraer
	 * la lista de plazas registradas en la tabla "PLAZACAPTURA"
	 * y agregar las opciones al combobox de plaza.
	 */
	private void llenaComboBoxPlaza(){
		if(this.establecerConexionDB2()){
			try {
				ArrayList<Plaza> listaPlazas = DAO.getListaPlazas();
				for (Plaza plaza : listaPlazas) {
					cmbPlaza.addItem(plaza.getNombre());
				}
			} catch (SQLException e) {
				logger.error("Error al extraer las palzas.", e);
			}
			this.cerrarConexionDB2();
		}
	}
	
	public void llenarComboEmpresa(){
		listaEmpresas=new ArrayList<Empresa>();
		if(establecerConexionDB2()){
		try {

			listaEmpresas=DAO.getListaEmpresas();
			//metodo donde te regrese todas las areas 
		} catch (SQLException e){
			logger.info("Error al intentar cargar las empresas"+e.getMessage());
			
			
		}
		this.cerrarConexionDB2();
		}
		else{
			logger.info("Error al conectarce a la base de datos para extraer las áreas");
		}
		
	}

	
	public DefaultComboBoxModel getCbxModelDestinatario(){
		if(defaultComboDestinatario==null){
			defaultComboDestinatario= new DefaultComboBoxModel();
			defaultComboDestinatario.addElement("Seleccione una opcion");
		}
		return defaultComboDestinatario;
	}
	
	
	public JComboBox getCbxDestinatario() {
		if (cbxEnviarFactura == null) {
			cbxEnviarFactura = new JComboBox(getCbxModelDestinatario());
		}
		return cbxEnviarFactura;
	}
	

	public JTextField getTxtOrdenCompra() {
		if (txtordenCompra == null) {
			txtordenCompra = new JTextField();
			txtordenCompra.setColumns(10);
		}
		return txtordenCompra;
	}

	public JTextField getTxtNoSAF() {
		if (txtNoSAF == null) {
			txtNoSAF = new JTextField();
			txtNoSAF.setColumns(10);
		}
		return txtNoSAF;
	}

	public JTextField getTxtRfcProveedor() {
		if (txtRfcProveedor == null) {
			txtRfcProveedor = new JTextField();
			txtRfcProveedor.setColumns(10);
		}
		return txtRfcProveedor;
	}
	
	
	public JFormattedTextField getTxtNoProveedor() {
		if (txtNoProveedor == null) {
				txtNoProveedor = new JFormattedTextField();

		}
		return txtNoProveedor;
	}

	public JTextField getTxtFolioRecepcion() {
		if (txtFolioRecepcion == null) {
			txtFolioRecepcion = new JTextField();
			txtFolioRecepcion.setColumns(10);
		}
		return txtFolioRecepcion;
	}
	
	public JTextArea getTxtAreaVisor(){
		if(txtAreaVisor==null){
			txtAreaVisor=new JTextArea();
		}
		return txtAreaVisor;
	}
	
	public JDateChooser getJDateChooserFechaRecepcion(){
		if(jDateChooserFechaRecepcion==null){
			jDateChooserFechaRecepcion=new JDateChooser();
		}
		return jDateChooserFechaRecepcion;
	}
	
	public JDateChooser getJDateChooserFechaFactura(){
		if (jDateChooserFechaFactura==null){
			jDateChooserFechaFactura= new JDateChooser();	
		}
		return jDateChooserFechaFactura;
		
	}
	public JButton getBtnAnteriorPag() {
		if (btnAnteriorPag == null) {
			btnAnteriorPag = new JButton();
		}
		return btnAnteriorPag;
	}
	public JButton getBtnSiguientePag() {
		if (btnSiguientePag == null) {
			btnSiguientePag = new JButton();
		}
		return btnSiguientePag;
	}
	public JTextField getLblActualPag() {
		if (lblActualPag == null) {
			lblActualPag = new JTextField("1");
		}
		return lblActualPag;
	}
	public JTextField getLblTotalPag() {
		if (lblTotalPag == null) {
			lblTotalPag = new JTextField("1");
		}
		return lblTotalPag;
	}
	public ButtonGroup getBtnGroupTipoDucumento(){
	if(this.rdBtnsTipoDocumento==null){
		rdBtnsTipoDocumento=new ButtonGroup();
	}
		return rdBtnsTipoDocumento;
	}
	
	public JRadioButton getRdnBtnFact() {
		if (rdbtnTipoFactura == null) {
			rdbtnTipoFactura = new JRadioButton("Factura Mercancia");
		}
		return rdbtnTipoFactura;
	}
	
	public JButton getbtnGuardar(){
		if(btnGuardar==null){
			btnGuardar = new JButton("");
		}
		return btnGuardar;
	}
	
	public JDialog getJDialogDocumentoSoporte() {
		if(jDialogDocumentoSoporte == null){
			jDialogDocumentoSoporte = new JDialog();
		}
		return jDialogDocumentoSoporte;
	}
	
	public JButton getBtnAceptarDocumentoSoporte() {
		if (btnAceptarJDialogSop == null) {
			btnAceptarJDialogSop = new JButton("Aceptar");
		}
		return btnAceptarJDialogSop;
	}
	public JTextField getTxtNombreDoc() {
		if(txtNombreDoc==null){
			txtNombreDoc = new JTextField();
		}
		return txtNombreDoc;
}
	public boolean establecerConexionDB2(){
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;
		
		try {
			
			conexionAbierta = DAO.establecerConexionDB2();
			
		} catch (SQLException e) {
			logger.info("Error al intentar conectarce a DB2"+e.getMessage());
			seGeneroExcepcion = true;
			
		} catch (InstantiationException e) {
			logger.info("Error al intentar conectarce a DB2"+e.getMessage());
			seGeneroExcepcion = true;
			
		} catch (IllegalAccessException e) {
			logger.info("Error al intentar conectarce a DB2"+e.getMessage());
			seGeneroExcepcion = true;
			
		} catch (ClassNotFoundException e) {
			logger.info("Error al intentar conectarce a DB2"+e.getMessage());
			seGeneroExcepcion = true;
		}
		
		if(seGeneroExcepcion){

		}
		return conexionAbierta;
		
	}
	public void cerrarConexionDB2(){
		try {
			
			DAO.cerrarConexionDB2();
			
		} catch (SQLException e) {
			logger.info("Error al intentar cerrar a DB2"+e.getMessage());
		}
	}
	
	/**
	 * Método que valida la conexión con DB2.
	 * 
	 * @return true - Si la conexión es valida.
	 */
	private boolean validarConexionDB2() {
		boolean conexionValida = false;

		try {
			conexionValida = DAO.validaConexionDB2();
		} catch (SQLException e) {
			logger.error(
					BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"),
					e);
		}

		return conexionValida;
	}

	/**
	 * Si no se abre la ventana desde lotes
	 * el sistema genera el lote actual luego
	 * verfica si en la base de datos 
	 * hay facturas para mostrarlas
	 */
	public void procesoValidarLote() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		String lote = Utilities.getLoteActual();
		this.listaFacturas.clear();
		if (lote != null) {

			logger.info("Lote :" + lote);
			logger.info("Usario : " + this.idUsuario);
			
			if (this.establecerConexionDB2()) {
				try {
					int existeLote = DAO.ConsultaExisteLote(lote, idUsuario);

					if (existeLote != 0) {
						setTotalRegistros(existeLote);//
						logger.info("Total de registros en el Lote actual" + getTotalRegistros());
						this.lote = lote;
						this.primeraCaptura = false;
						setTotalRegistros(existeLote);//
						this.paginaActual++;
						this.calcularTotalPaginas();
						this.actualizarIndicesPaginador();
						this.muestraFacturasPorLote();
						this.procesoActualizarDatosPginado();
					}else{/*El lote no existe agregar a la tabla de REPORTECARGA*/
						this.insertarLoteReporteCarga(lote);
					}

				} catch (SQLException e) {
					if (this.validarConexionDB2()) {
						logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);

						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
						this.showMessage(message);
					} else {
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG007"), titulo, 1);
						this.showMessage(message);
						System.exit(0);
					}
				}

				this.cerrarConexionDB2();
			}
		}
	}
	

	/**
	 * Inserta la fecha de inicio de
	 * Caprtura en la la tabla ReporteCarga
	 */
	private void insertarInicipoFechaCaptura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		Timestamp fechaInicioCap=null;
		if(this.establecerConexionDB2()){
		try {
			
			String tipoCaptura=Constants.REPORTECARGA_FACTURA_MERCANCIA;
			 fechaInicioCap=DAO.consultarFechaInicioCapturaReporteCarga(idUsuario,lote,tipoCaptura);
			 System.out.println("FechaInicioCaptura "+fechaInicioCap);
			if(fechaInicioCap==null){/*si aun es null insertar la fecha*/
				String fecha=Utilities.nowTimeStamp();
				fechaInicioCap=Utilities.parseSqlTimeStamp(fecha);
			
				if(DAO.actualizarFechaInicioCaptura(fechaInicioCap,this.lote,idUsuario,tipoCaptura)){
					logger.info("Se agrego la fecha incia de captura del lote "+this.lote+" ,usuario "+idUsuario +"  y tipo Captura "+tipoCaptura);
				}
				
			}else{
				logger.info("El lote "+this.lote+" , usuario "+idUsuario+ "  y tipo Captura "+tipoCaptura+" Ya tienen fecha de inicio de captura" );
			}
			
		} catch (SQLException e) {
			if (this.validarConexionDB2()) {
				
				logger.error("ha ocurrido un error al actualizar la fecha de inicio de captura", e);
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
				this.showMessage(message);
			}
		}
		
		}
		
	}
	
	/**
	 * Inserta la fecha de la ultima Capturada
	 */
	public void insertarFechaFinCaptura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		Timestamp fechaFinCap=null;
		
		if(this.establecerConexionDB2()){
			try {
					String tipoCaptura=Constants.REPORTECARGA_FACTURA_MERCANCIA;
					String fecha=Utilities.nowTimeStamp();
					fechaFinCap=Utilities.parseSqlTimeStamp(fecha);
				
					if(DAO.actualizarFechaFinCaptura(fechaFinCap,this.lote,idUsuario,tipoCaptura)){
						logger.info("Se agrego la fecha fin de captura de captura del lote "+this.lote+" , usuario "+idUsuario+" y tipoCaptura"+tipoCaptura);
					}
			} catch (SQLException e) {
				if (this.validarConexionDB2()) {
					
					logger.error("ha ocurrido un error al actualizar la fecha fin de captura", e);
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				}
			}
			
			}
		
	}
	
	private void calcularTotalPaginas(){
		System.out.println("total de registros = "+totalRegistros);
		
		
		int residuo = (totalRegistros % regPorPagina);
		
		if(residuo == 0){
			this.totalPaginas = (totalRegistros / regPorPagina);
		}else{
			this.totalPaginas = (totalRegistros / regPorPagina)+1;
		}
		System.out.println("total de paginas "+totalPaginas);
		if(this.totalPaginas>1){
			this.habilitaBotonesPaginador(true);
		}else{
			this.habilitaBotonesPaginador(false);
		
		}
		
	}
	
	/**
	 * Inserta el lote y el Id_usuario 
	 * en la la base de datos tabla 
	 * reporte Carga
	 * @throws SQLException 
	 */
	private void insertarLoteReporteCarga(String lote) throws SQLException {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		
		/*verificar si el lote ya existe en la tabla Reporte-Carga*/
		int numReport=DAO.ConsultaExisteLoteReporteCarga(idUsuario,lote,Constants.REPORTECARGA_FACTURA_MERCANCIA); 
		if(numReport==0){
			
			String fechaCaptura=Utilities.nowDate();
			ReporteCarga report= new ReporteCarga();
			report.setIdUsuario(this.idUsuario);
			report.setLote(lote);
			report.setFechaCaptura(fechaCaptura);
			report.setTipoCaptura(Constants.REPORTECARGA_FACTURA_MERCANCIA);
			report.setDocumentosProcesados("0");
		
			if(DAO.insertarLoteReporteCarga(report)){
				logger.info("Se ha insertado el Lote" +lote+" Usuario "+idUsuario+" En ReportesCarga");
			}else{
				logger.info(" el Lote "+lote+" Usuario "+idUsuario+" NO se inserto en reporte Carga");
			}	
			
		}else{
			logger.info(" el Lote " +lote+" ,  Usuario "+idUsuario+" y Tipo Captura "+Constants.REPORTECARGA_FACTURA_MERCANCIA+" Ya existen en reporte Carga");
		}
		
	}
	
	
	
	
	private void actualizarIndicesPaginador(){
		int dif = regPorPagina -1;
		this.indiceInf = (paginaActual*regPorPagina) - dif;
		this.indiceSup = indiceInf + dif;
	}
	
	public boolean procesoCambiarPagina(String accion) {
		
		boolean cambio=false;
		
		if(accion.equals(Constants.CMD_PAGINA_SIGUIENTE)){
			this.calcularTotalPaginas();
			if(paginaActual<totalPaginas){
				paginaActual++;
			}
			this.actualizarIndicesPaginador();
			this.muestraFacturasPorLote();
			this.procesoActualizarDatosPginado();
			cambio=true;

		}else if(accion.equals(Constants.CMD_PAGINA_ANTERIOR)){
			this.calcularTotalPaginas();
			if(paginaActual!=1){
				paginaActual--;
			}
			this.actualizarIndicesPaginador();
			this.muestraFacturasPorLote();
			this.procesoActualizarDatosPginado();
			cambio=true;
		}
		return cambio;
	
	}
	
	private void procesoActualizarDatosPginado() {
		if(paginaActual!=0 && totalPaginas!=0){
			this.lblActualPag.setText(paginaActual+"");
			this.lblTotalPag.setText(totalPaginas+"");
		}
	}
	/**
	 * COnsulta en la base de datos Todas las facturas 
	 * del lote Actual
		 */
	public void muestraFacturasPorLote() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		this.txtUrl.setText("");
			if(this.establecerConexionDB2()){
					try {
						this.listaFacturas = new ArrayList<Factura>();
						this.listaFacturas = DAO.getListaFacturasPorLote(this.lote, this.indiceInf, this.indiceSup,this.idUsuario);
						
						 this.limpiaJTableDatos();
		
						if(listaFacturas.size()!=0){
							for(int i = 0; i < listaFacturas.size(); i++){
								Factura fact = listaFacturas.get(i);
								int id = indiceInf+i;//verificar para cdo se agrga ya habiendo lote
								this.defaultTableModelDatos.setValueAt(id, i, 0);
								this.defaultTableModelDatos.setValueAt(fact.getConcepto().getDescripcion(), i, 1);
								this.defaultTableModelDatos.setValueAt(fact.getEstado().getId(), i, 2); // Para que aparezca icono
							}
						}
						else{
							logger.info("No se pudieron  mostrar los datos");
						}
						
					} catch (SQLException e) { 
						e.printStackTrace();
					}
				this.habilitaTablaDatos(true);
				this.cerrarConexionDB2();
			}
		}
	
	public void limpiaJTableDatos(){
		int filas = defaultTableModelDatos.getRowCount();
		for (int i = 0; i < filas; i++) {
			this.defaultTableModelDatos.setValueAt("", i, 0);
			this.defaultTableModelDatos.setValueAt("", i, 1);
			this.defaultTableModelDatos.setValueAt("", i, 2);
		}
	}
	public void habilitaTablaDatos(boolean b) {
		this.jTableDatosFact.setEnabled(b);
		
	}
	
	public String getDesEstado(int status) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		String descripcion=null;
		if(status==1){
			descripcion=(Constants.STATUS_DES_PENDIENTE_CAPTURA);
		}else if(status==2){
			descripcion=(Constants.STATUS_DES_EN_CAPTURA);
		}else if(status==3){
			descripcion=(Constants.STATUS_DES_CAPTURA_COMPLETA);
		}else if(status==4){
			descripcion=(Constants.STATUS_DES_CAPTURA_INCOMPLETA);
		}else if(status==5){
			descripcion=(Constants.STATUS_DES_EN_IMPORTACION_CM);
		}else if(status==6){
			descripcion=(Constants.STATUS_DES_IMPORTADO_A_CM);
		}
		return descripcion;
	}
	/**
	 * Muestra el emnsaje
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}
	
	public boolean valExisteDireccion(File file){	
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
	
		boolean resultado = false;
		
		if(file.exists()){
			resultado = true;
		}
		else {
			logger.info(nombreMetodo+"   "+ "La Direccion No existe");
		}
		
		return resultado;
	}
	
	/**
	 * valida que el path seleccionado
	 * no sea el mismo que la carpeta del 
	 * lote actual
	 * @param file
	 * @return
	 */
	public boolean isArchivoValido(File file){
		boolean valida=true;
		String direccion=file.getParent();

		String lote = Utilities.getLoteActual();
		StringBuffer stringBuff = new StringBuffer();
				stringBuff.append(workDir);
				stringBuff.append(File.separator);
				stringBuff.append(lote);
		
		String direccionLote=stringBuff.toString();
		if(direccion.equals(direccionLote)){
			valida=false;
		}
		return valida;
	}
	/**
	 * valida que la carpeta seleccionada
	 * no se  la misma de lote actual
	 * @param file
	 * @return
	 */
	public boolean isCarpetaValida(File file){
		boolean valida=true;
		String direccion=file.getPath();
		System.out.println("Direccion 1 "+direccion);
		String lote = Utilities.getLoteActual();
		StringBuffer stringBuff = new StringBuffer();
				stringBuff.append(workDir);
				stringBuff.append(File.separator);
				stringBuff.append(lote);
				System.out.println("Direccion 2 "+stringBuff);
		String direccionLote=stringBuff.toString();
		if(direccion.equals(direccionLote)){
			valida=false;
		}
		return valida;
	}

	public boolean agregarDocumento(File file, int tipo, Usuario usuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		
		this.txtUrl.setText("");
		String urlDocumento = null;
		Factura nuevoRegistro  = null;
		Estado status=null;
		int id_fact=0;
		boolean procesoExitoso=false;
		

		/*
		 * 1.- GenerarLote
		 */
	
		if(this.lote == null){
			this.lote = Utilities.getLoteActual();
			
		}
	
			
		if(file != null){
			if(valExisteDireccion(file)){
				
				if(tipo == Constants.IMPORTA_UN_DOCUMENTO){
					logger.info("Importando Documento");
				
					urlDocumento =file.toString();
					/**
					 * copiar archivo al folder Lote
					 */
					if(this.valTipoArchivo(urlDocumento)){
						File inFile = new File(urlDocumento);
						if(!inFile.isDirectory()){
							nuevoRegistro = new Factura(urlDocumento);
							status=new Estado(Constants.STATUS_PENDIENTE_CAPTURA);
							status.setDescripcion(Constants.STATUS_DES_PENDIENTE_CAPTURA);
							nuevoRegistro.setEstado(status);
							nuevoRegistro.setUsuario(usuario);
							Destinatario  destinatario= new Destinatario();
							destinatario.setIdUsuario(usuario.getIdUsuario());
							nuevoRegistro.setDestinatario(destinatario);
							nuevoRegistro.setPlaza(this.getPlaza());
							nuevoRegistro.setLote(this.lote);
							nuevoRegistro.setSoporte(0);
							nuevoRegistro.setTipoDocumento(Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA);
							if(this.establecerConexionDB2()){
								id_fact=insertarDocumento(nuevoRegistro);
								if(id_fact==0){
									logger.info("No se inserto en FACTURACAPTURA");
								 	 
								}else{
									nuevoRegistro.setId(id_fact);
									if(insertarEstadoDocumento(nuevoRegistro)){
									fila++; //fila
									
									nuevoRegistro.setFila(fila);
									if(listaFacturas.size() <= this.regPorPagina){
													this.agregarDocumentoArray(fila, nuevoRegistro);
												}
												this.totalRegistros++;
												this.calcularTotalPaginas();
											
												if (this.primeraCaptura) {
													this.paginaActual++;
													this.actualizarIndicesPaginador();
													this.procesoActualizarDatosPginado();
													this.primeraCaptura = false;
													this.activarPrimerRegistro=true;
												}
												
												this.actualizarIndicesPaginador();
												this.procesoActualizarDatosPginado();
												this.muestraFacturasPorLote();
												procesoExitoso = true;
												/*verificar si fecha captura ya tiene datos*/
												this.insertarInicipoFechaCaptura();
									}else{
										logger.info("No se inserto en FACTURASTATUSCAPTURA  con el ID "+id_fact);
									}	
								}
								cerrarConexionDB2();
								
							}	else{
								  message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG002"),titulo,1);
								  this.showMessage(message);
								
							   }	
							
							}
				}
			 }else if(tipo ==Constants.IMPORTA_CARPETA){
					logger.info("Importando doc de la Carpeta");
					String[] listaArchivosOrigen = file.list();
					this.txtUrl.setText(file.toString());
					/*
					 * 3.- Mover todos los archivos al folder del lote
					 */
					 if(listaArchivosOrigen.length>0){
						 if(this.establecerConexionDB2()){
							 for (String nombreArchivo : listaArchivosOrigen) {
									File inFile = new File(file.getAbsolutePath()+File.separator+nombreArchivo);
									if(!inFile.isDirectory()){
											if(valTipoArchivo(nombreArchivo)){
												urlDocumento=inFile.toString();
												status = new Estado(Constants.STATUS_PENDIENTE_CAPTURA); 
												status.setDescripcion(Constants.STATUS_DES_PENDIENTE_CAPTURA); 
												nuevoRegistro = new Factura(urlDocumento);
												nuevoRegistro.setEstado(status);
												nuevoRegistro.setUsuario(usuario);
												nuevoRegistro.setLote(this.lote);
												nuevoRegistro.setPlaza(this.getPlaza());
												nuevoRegistro.setSoporte(0);
												String tipoFactura=Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA;
												nuevoRegistro.setTipoDocumento(tipoFactura);
												
												id_fact = insertarDocumento(nuevoRegistro);
										
												if(id_fact == 0){
												
													logger.info("No se inserto en FACTURACAPTURA");
										 			
												}else{
													nuevoRegistro.setId(id_fact);
													if(insertarEstadoDocumento(nuevoRegistro)){
														fila++; //fila
														nuevoRegistro.setFila(fila);		
														if(listaFacturas.size() <= this.regPorPagina){
															this.agregarDocumentoArray(fila, nuevoRegistro);
														}
														this.totalRegistros++;
														this.calcularTotalPaginas();
											
														if (this.primeraCaptura) {

															this.paginaActual++;
															this.actualizarIndicesPaginador();
															this.procesoActualizarDatosPginado();
															this.activarPrimerRegistro=true;
															this.primeraCaptura = false;
														}

														procesoExitoso = true;
													}else{
														logger.info("No se inserto en FACTURASTATUSCAPTURA con el id "+id_fact);
														}
												}	
										}
									}
								}
								this.actualizarIndicesPaginador();
								this.procesoActualizarDatosPginado();
								this.muestraFacturasPorLote();
								
								this.cerrarConexionDB2();
						 }
						
						}else{
		
				 			message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG029"),titulo,1);
						 this.showMessage(message);
						}	 
					 
					}
			}else {
					// direccion no existe o invalida
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG0024"), titulo, 1);
					this.showMessage(message);
				}
		} 
		
		return procesoExitoso;
	}
			
	
	public void muestraPrimerRegistro(){
		filaSeleccionada = 0;
		this.seleccionaFila(filaSeleccionada);
	}
	
	
	public void seleccionaFila(int fila){
		this.consultarFilaSeleccionada();
		jTableDatosFact.changeSelection(fila, 0, false, false);
	}
	
	/**
	 * pone la fecha de recepcion del anterior registro en al actual
	 */
	private void ponerPlazaAnterior() {
		if (this.cmbPlaza.getSelectedIndex() == 0
				&& plaza != null) {
			this.cmbPlaza.setSelectedItem(plaza);
		}
	}
	
	/**
	 * Meodo que valida el estado de la fila seleccionada
	 */

	public void consultarFilaSeleccionada() {
		int status=0;
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		
		if (filaSeleccionada<this.listaFacturas.size()){
			logger.info("IDFACTURA seleccionada " + listaFacturas.get(filaSeleccionada).getId());
			status=consultaEstadoFactura();
			if(status!=0 && status==Constants.STATUS_PENDIENTE_CAPTURA ){
				this.txtNoFact.setText("");
				this.txtMonto.setText("");
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);
				this.procesoMuestraDatosCapturados();
				
				this.ponerPlazaAnterior();
				
			}else if (status!=0 && status==Constants.STATUS_CAPTURA_COMPLETA ){
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);	
				this.procesoMuestraDatosCapturados();
				
			}else if (status != 0 && status == Constants.STATUS_CANCELADO) {
				this.limpiarCamposCaptura();
				this.txtUrl.setText(listaFacturas.get(filaSeleccionada).getUrl());
				this.habilitaCampoCaptura(false);
				this.habilitaBotonesFunciones(false);
				this.btnCargaMasiva.setEnabled(false);
				this.habilitaRdBtnsTipoDocumento(false);
				
			}else if (status != 0 && status == Constants.STATUS_ERROR_IMPORTADO) {// captura completa habilitar todas las opciones
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);
				this.procesoMuestraDatosCapturados();
				
			}else if (status != 0 && status == Constants.STATUS_IMPORTANDO_CM) {// captura completa habilitar todas las opciones
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(false);
				this.habilitaBotonesFunciones(false);
				this.habilitaRdBtnsTipoDocumento(false);
				this.procesoMuestraDatosCapturados();
			}
		}else{
			this.limpiarCamposCaptura();
			this.habilitaCampoCaptura(false);
			this.habilitaBotonesFunciones(false);
			this.habilitaRdBtnsTipoDocumento(false);	
			plaza = null;
		}
	}
	
	public void habilitaBotonesImportacion(boolean b){
		this.btnCargarArchivo.setEnabled(b);
		this.btnCargarCarpeta.setEnabled(b);
	}
	
	public void habilitaCampoCaptura(boolean b){

		this.txtMonto.setEnabled(b);
		this.txtNoFact.setEnabled(b);
		this.txtNoProveedor.setEnabled(b);
		this.jDateChooserFechaFactura.setEnabled(b);
		this.jDateChooserFechaRecepcion.setEnabled(b);
		this.cbxRazonSocial.setEnabled(b);
		this.txtNoFact.setFocusable(b);
		//this.txtSucursalProv.setEnabled(b);
	
		/**
		 * @author Adan
		 * Habilitar cmbo de plazas.
		 */
		this.cmbPlaza.setEnabled(b);
	}
	 
	public void habilitaRdBtnsTipoDocumento(boolean b){
		this.rdbtnTipoFactura.setEnabled(b);
		rdbtnTipoFactura.setSelected(true);
	}
	
	
	public void habilitaBotonesFunciones(boolean b) {
		this.btnCargaMasiva.setEnabled(b);
		this.btnCambiarURL.setEnabled(b);
		this.btnDescartar.setEnabled(b);
		this.btnGuardar.setEnabled(b);
		
	}
	
	public File getImportDocumentURL(JInternalFrame frame, int tipoImportacion,int nombreDialog) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		
		
		File file = null;
		JFileChooser fileChooser = new JFileChooser();	
		
		
		
		if(tipoImportacion == Constants.IMPORTA_UN_DOCUMENTO){
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			fileChooser.setFileFilter(new FileFilter(){

				String[] extension = {"docx","DOCX","xml","XML","PDF","pdf","JPG","jpg","BMP","bmp","gif","GIF"};
				@Override
				public boolean accept(File f) {
					String[] strPath = f.getAbsolutePath().split("\\.");
					int position = (strPath.length-1);
					return (strPath[position].equals(extension[0]) 
							|| strPath[position].equals(extension[1])
							|| strPath[position].equals(extension[2])
							|| strPath[position].equals(extension[3])
							|| strPath[position].equals(extension[4])
							|| strPath[position].equals(extension[5])
							|| strPath[position].equals(extension[6])
							|| strPath[position].equals(extension[7])
							|| strPath[position].equals(extension[8])
							|| strPath[position].equals(extension[9])
							|| strPath[position].equals(extension[10])
							|| strPath[position].equals(extension[11])) 
							|| f.isDirectory();
				}

				@Override
				public String getDescription() 
				{ return null; }
			});
			
		}else if(tipoImportacion == Constants.IMPORTA_CARPETA){
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		
		if(nombreDialog==1){
			int var = fileChooser.showDialog(frame, "Importar");
			if(var == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
			}
		}else if(nombreDialog==2){
			int var = fileChooser.showDialog(frame, "Cambiar");
			if(var == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
			}
		}
		
		return file;
	}
	
	private boolean valTipoArchivo(String url){
		boolean resultado = false;
		
		int dot = url.lastIndexOf('.');
		String extencion = (dot == -1) ? "" : url.substring(dot+1);
		if(extencion.equals("pdf") || extencion.equals("PDF")
								   || extencion.equals("BMP")|| extencion.equals("BMP")
								   || extencion.equals("DOCX")|| extencion.equals("docx")
								   || extencion.equals("XML")|| extencion.equals("xml")
								   || extencion.equals("JPG")|| extencion.equals("jpg")
								   || extencion.equals("gif")|| extencion.equals("gif")
				)
		
		{
			resultado = true;
			
		}
		return resultado;
	}
	
	private void agregarDocumentoArray(int fila, Factura nuevoRegistro){
		if((fila != 0) && (listaFacturas.size() < 10)){ 
		
			listaFacturas.add(nuevoRegistro);
		}
	}
	private String generaDireccionURL(File file, String nombreArchivo) {
		String url = file.toString() + File.separator +  nombreArchivo;
		return url;
	}
	
	private int  insertarDocumento(Factura factura){
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		
		int idFact=0;
		
		try {
			idFact = DAO.insertarDocumento(factura);
		} catch (SQLException e) {
			logger.error("Error al insertar el documento (factura) en la base de datos",e);
		}
		
		
		return idFact;
	}
	
	private boolean insertarEstadoDocumento(Factura factura){
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		boolean inserto=false;
		try {
			inserto=DAO.insertarStatusFactura(factura);
			inserto=true;
		} catch (SQLException e) {
			logger.info("Error al insertarEstatusDocumento " +e.getMessage());
		}
		return inserto;
	}
	
	public int consultaEstadoFactura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		int status=0;
		int idFactura=listaFacturas.get(filaSeleccionada).getId();
		if(this.establecerConexionDB2()){
			try{
				status=DAO.getStatusFactura(idFactura);
				if(status!=0){
					logger.info("status de la factura "+status);
				}
			}catch(SQLException e){
				
				if (this.validarConexionDB2()) {
					logger.error(BundleManager.getValue(
						Constants.MESSAGE_BUNDLE, "ERR001"), e);
										
					message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"ERR001")+e.getMessage(),titulo,1);
					this.showMessage(this.getMensaje());					
				}
				else{
					message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG007"+e),titulo,1);
					this.showMessage(message);	
					System.exit(0);
				}
			}
			this.cerrarConexionDB2();
		}
		
		return status;
	}
	
	public void setPlaza(String plazaDesktop) {
		this.plaza = plazaDesktop;
	}

	public void limpiarCamposCaptura() {

		this.txtNoFact.setText("");
		this.txtMonto.setText("");
		this.txtFolioRecepcion.setText("");
		this.txtNoProveedor.setText("");
		this.txtNombreProv.setText("");
		/*jComboModel = new ADComboBoxModel(new String[]{""});
		this.txtSucursalProv.setModel(jComboModel);
		this.txtSucursalProv.setSelectedIndex(0);*/
		this.txtRfcProveedor.setText("");
		this.txtUrl.setText("");
		this.jDateChooserFechaFactura.setDate(null);
		//this.jDateChooserFechaRecepcion.setDate(null);
		this.cbxRazonSocial.setSelectedIndex(0);
		this.txtNoFact.setText("");
		this.txtMonto.setText("");
		
		/**
		 * @author Adan
		 * Se limpia combo de Plazas
		 */
		this.cmbPlaza.setSelectedIndex(0);
	}
	
	private void procesoMuestraDatosCapturados() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		if(this.establecerConexionDB2()){
			try{
				Factura facturaNueva= new  Factura();
				facturaNueva=DAO.getAllFactura(listaFacturas.get(filaSeleccionada));
				
				
				listaFacturas.get(filaSeleccionada).setNoFact(facturaNueva.getNoFact());
					this.txtNoFact.setText(facturaNueva.getNoFact());
					String numero =facturaNueva.getProveedor().getNumero();
					if(numero !=null){
						numero = numero.replaceAll(" ", "");
						this.txtNoProveedor.setValue(numero);
					}
					
					if(facturaNueva.getProveedor().getNombre()!=null){
						this.txtNombreProv.setText(facturaNueva.getProveedor().getNombre());
					}
					
					/*System.out.println("Sucursal"+facturaNueva.getProveedor().getSucursal()+"and");
					if(facturaNueva.getProveedor().getSucursal()==null  || facturaNueva.getProveedor().getSucursal().equals(" ") ){
						jComboModel = new ADComboBoxModel(new String[]{this.sucursalAnterior});
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);
					}else{
						jComboModel = new ADComboBoxModel(new String[]{facturaNueva.getProveedor().getSucursal()});
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);
					}*/
					
					if(facturaNueva.getProveedor().getRfc()!=null){
						this.txtRfcProveedor.setText(facturaNueva.getProveedor().getRfc());
					}
					
					this.txtUrl.setText(facturaNueva.getUrl());
								
					if(facturaNueva.getFechaRecepcion()==null){
						this.jDateChooserFechaRecepcion.setDateFormatString(Utilities.nowDate());
					}else{
						this.jDateChooserFechaRecepcion.setDate(facturaNueva.getFechaRecepcion());
					}
					if(facturaNueva.getFechaFactura()!=null){
						this.jDateChooserFechaFactura.setDate(facturaNueva.getFechaFactura());
					}
					
					this.txtMonto.setText(facturaNueva.getMonto());
					this.txtUrl.setText(facturaNueva.getUrl());
					
					if(facturaNueva.getConcepto().getDescripcion()!=null){
						this.txtFolioRecepcion.setText(facturaNueva.getConcepto().getDescripcion());/*Folio Recep*/
					}
					
						int indexEmpresa=0;
					if(facturaNueva.getEmpresa().getNombre()!=null){
						for(int i=0;i<=this.listaEmpresas.size();i++){
							
							if(facturaNueva.getEmpresa().getNombre().equals(listaEmpresas.get(i).getNombre())){
								indexEmpresa=(i+1);
								break;
							}
						}
						this.cbxRazonSocial.setSelectedIndex(indexEmpresa);
					}
					
					/**
					 * @author Adan
					 * Se selecciona del combo la plaza
					 * de la factura seleccionada.
					 */
					if(facturaNueva.getPlaza() != null && !facturaNueva.getPlaza().isEmpty()){
						this.cmbPlaza.setSelectedItem(facturaNueva.getPlaza());
					}else{
						this.cmbPlaza.setSelectedIndex(0);
					}
					
			}
			catch(SQLException e){
				logger.info("Error al traer los datos Capturados "+e.getMessage());
			}
			this.cerrarConexionDB2();
			
		}
	}
	
	public int  getFilaSeleccionada() {
		filaSeleccionada=this.jTableDatosFact.getSelectedRow();
		return filaSeleccionada;
	}
	
	public void procesoCambiarUrl(File file) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		String url=this.listaFacturas.get(filaSeleccionada).getUrl();
		
		File fileactual= new File(url);
		String nomnreActual =fileactual.getName();
		
		
		String NewUrlDocumento =file.toString();
		String nombreNuevo=file.getName();
		
		
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append(workDir);
		stringBuff.append(File.separator);
		stringBuff.append(this.lote);
		
		File folderLote = new File(stringBuff.toString());
		
		
		if(!folderLote.exists()){
			 folderLote.mkdirs();
		}
		
		if(nomnreActual.equals(nombreNuevo)){
			logger.info("No se pudo reemplazar el doc. ya que tiene el mismo nombre de archivo");
		}else{
			if(valTipoArchivo(nombreNuevo)){
				File inFile = new File(NewUrlDocumento);
					try{
						stringBuff = new StringBuffer();
						stringBuff.append(workDir);
						stringBuff.append(File.separator);
						stringBuff.append(this.lote);
						stringBuff.append(File.separator);
						stringBuff.append(inFile.getName());
						Utilities.FileCopy(NewUrlDocumento, stringBuff.toString());
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					NewUrlDocumento=generaDireccionURL(folderLote,nombreNuevo);
					if(this.establecerConexionDB2()){
						try {
							
							int idFact=this.listaFacturas.get(filaSeleccionada).getId();
						
							int actualizo=DAO.actualizaURL(idFact, NewUrlDocumento);
							if(actualizo!=0){
								
								this.listaFacturas.get(filaSeleccionada).setUrl(NewUrlDocumento);
								fileactual.delete();
								//this.muestraDocumentoEnPantalla();
							}
						} catch (SQLException e) {
							logger.info("Error al intentar Cambiar la Url"+e.getMessage());
						}
						this.cerrarConexionDB2();
					}
			}
		}
	}
	
	public boolean procesoDescartarDocumento(Usuario usuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		boolean procesoExito=false;
		if(this.establecerConexionDB2()){
			try {
				this.listaFacturas.get(filaSeleccionada).setUsuario(usuario);
				Destinatario destinatario= new Destinatario();
				destinatario.setIdUsuario(listaFacturas.get(filaSeleccionada).getUsuario().getIdUsuario());
				listaFacturas.get(filaSeleccionada).setDestinatario(destinatario);
			
				DAO.eliminarRegistro(this.listaFacturas.get(filaSeleccionada));
				DAO.borrarStatusCaptura(this.listaFacturas.get(filaSeleccionada).getId());
				procesoExito=true;
				this.totalRegistros--;
				int residuo = (getTotalRegistros() % regPorPagina);
			
				if (residuo == 0 && paginaActual != 1 ) {
					this.paginaActual--;
				} 
				
			} catch (SQLException e) {
				logger.info("Error al Descartar un documento"+e.getMessage());
			}
			this.cerrarConexionDB2();
		}
		
		return procesoExito;
	}
	
public boolean procesoValidarcamposCaptura(Usuario usuario) {
	String nombreMetodo = Thread.currentThread().getStackTrace()[1]
			.getMethodName();
	logger.info(nombreMetodo);
		boolean capturaCompleta=true;
		int status=0;
		
		if(this.filaSeleccionada>=0 && filaSeleccionada<=this.listaFacturas.size()){
			Proveedor proveedor= new Proveedor();
			Area area= new Area();
			Destinatario destinatario= new Destinatario();
			Empresa empresa= new Empresa();
			Concepto concepto= new Concepto();
			
			this.listaFacturas.get(filaSeleccionada).setProveedor(proveedor);
			this.listaFacturas.get(filaSeleccionada).setArea(area);
			this.listaFacturas.get(filaSeleccionada).setDestinatario(destinatario);
			this.listaFacturas.get(filaSeleccionada).setUsuario(usuario);
			this.listaFacturas.get(filaSeleccionada).setEmpresa(empresa);
			this.listaFacturas.get(filaSeleccionada).setConcepto(concepto);
			
								
			if (this.txtMonto.getText().isEmpty()){
					this.listaFacturas.get(filaSeleccionada).setMonto("0");
				}else{
					this.listaFacturas.get(filaSeleccionada).setMonto(this.txtMonto.getText());
					
				}
			
			if(this.txtNoFact.getText().isEmpty()){
					capturaCompleta=false;
				}else{
					this.listaFacturas.get(filaSeleccionada).setNoFact(this.txtNoFact.getText());
				}
					
			if(this.txtNombreProv.getText().isEmpty()){
					capturaCompleta=false;
				}else{
					
					this.listaFacturas.get(filaSeleccionada).getProveedor().setNombre(this.txtNombreProv.getText());
				}
					
			if (this.txtNoProveedor.getText().isEmpty()){
					capturaCompleta=false;
				}else{
					
					this.listaFacturas.get(filaSeleccionada).getProveedor().setNumero(this.txtNoProveedor.getText());
				}
			
			if(this.txtRfcProveedor.getText().isEmpty()){
					capturaCompleta=false;
				}else{
					this.listaFacturas.get(filaSeleccionada).getProveedor().setRfc(this.txtRfcProveedor.getText());
				}
			
			/*if(this.txtSucursalProv != null 
					&& this.txtSucursalProv.getSelectedItem() != null
					&& !this.txtSucursalProv.getSelectedItem().toString().isEmpty()){
				
				if (this.txtSucursalProv.getSelectedItem().toString().isEmpty()) {
					capturaCompleta = false;
					this.sucursalAnterior=null;
				} else {
					this.listaFacturas.get(filaSeleccionada).getProveedor().setSucursal(this.txtSucursalProv.getSelectedItem().toString());
					this.sucursalAnterior=this.txtSucursalProv.getSelectedItem().toString();
				}
			}*/
	
			if(this.jDateChooserFechaFactura.getDate()==null){
				this.listaFacturas.get(filaSeleccionada).setFechaFactura(null);
				}else{
					 Date date = jDateChooserFechaFactura.getDate();
					
					this.listaFacturas.get(filaSeleccionada).setFechaFactura(date);
				}
			
			if(this.jDateChooserFechaRecepcion.getDate()==null){
				capturaCompleta=false;
				this.listaFacturas.get(filaSeleccionada).setFechaRecepcion(null);
			}else{
				 Date date = jDateChooserFechaRecepcion.getDate();
			this.listaFacturas.get(filaSeleccionada).setFechaRecepcion(date);
			}
			
			
			if(defaultComboEmpresa.getIndexOf(defaultComboEmpresa.getSelectedItem())==0){
				capturaCompleta=false;
				this.listaFacturas.get(filaSeleccionada).getEmpresa().setNombre(null);
			}else{
				int index=defaultComboEmpresa.getIndexOf(defaultComboEmpresa.getSelectedItem());
				String empresaId=listaEmpresas.get(index-1).getNombre();
				this.listaFacturas.get(filaSeleccionada).getEmpresa().setNombre(empresaId);
			}
				this.listaFacturas.get(filaSeleccionada).setTipoDocumento(Constants.DOCUMENTO_TIPO_DOCUMENTO_FACTURA_MERCANCIA);
				this.listaFacturas.get(filaSeleccionada).setSoporte(0);
				
			
			/**
			 * @author Adan
			 * Se modifico para que solo se guarde una 
			 * ves que tenga toda la informacion necesaria
			 * para formar el folio.	
			 */
			if(this.txtFolioRecepcion.getText().isEmpty()){
				capturaCompleta=false;
			}else if(cmbPlaza.getSelectedIndex() != 0
					&& !txtNoProveedor.getText().isEmpty()
					&& jDateChooserFechaRecepcion.getDate() != null){
				this.listaFacturas.get(filaSeleccionada).getConcepto().setDescripcion(this.txtFolioRecepcion.getText());
			}
				
			/**
			 * @author Adan
			 * Agrego la plaza en una var para
			 * mostrarlo en el siguiente Regsitro
			 */
			this.plaza = null;
			
			/**
			 * @author Adan
			 * Se agrega condicion para hacer requerido 
			 * el campo de plaza de captura.
			 */
			if(cmbPlaza.getSelectedIndex() == 0){
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).setPlaza(null);
			}else{
				this.plaza = cmbPlaza.getSelectedItem().toString();
				this.listaFacturas.get(filaSeleccionada).setPlaza(cmbPlaza.getSelectedItem().toString());
			}
			
			//this.listaFacturas.get(filaSeleccionada).setPlaza(cmbPlaza.getText());
	}
		
		if(capturaCompleta){
		
			status=Constants.STATUS_CAPTURA_COMPLETA;
		}else{
		
			status=Constants.STATUS_PENDIENTE_CAPTURA;
		}
		
		
		String IdUsuario=usuario.getIdUsuario();
		return this.procesoActualizarFactura(status,IdUsuario);
}
	
	private boolean procesoActualizarFactura(int status, String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		boolean procesoExitoso=false;
		if(this.establecerConexionDB2()){
			
			try{
				int actualizo=DAO.actualizarFacturaDB2(this.listaFacturas.get(filaSeleccionada));
				if(actualizo!=0){
					int actualizaEstado= DAO.actualizaStatus(this.listaFacturas.get(filaSeleccionada),status);
					if(actualizaEstado!=0){
						procesoExitoso=true;
						this.listaFacturas.get(filaSeleccionada).getEstado().setId(status);
						message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG025"),titulo,1);
					
						/**
						 Si el el ultimos registro muestra une mensaje al usuarioq que si desea enviar a CM **/
						int factPendientes=0;
						 factPendientes=this.countFacturasPendientesCapturar(idUsuario);
						if(factPendientes==0){
							 this.ultimaCaptura=true;
						}
						this.showMessage(this.getMensaje());
						muestraFacturasPorLote();
						
						
					}else{
						message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG022"),titulo,1);
						this.showMessage(this.getMensaje());
					}
				}else{
					 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG022"),titulo,1);
					 this.showMessage(this.getMensaje());
				}
			}catch(SQLException e){
				logger.info("Error al intentar actualizar La Factura  " +this.listaFacturas.get(filaSeleccionada).getId()+": ");
				logger.info(e.getMessage());
			}
			this.cerrarConexionDB2();
		}else{
			 message= new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE,"MSG002"),titulo,1);
			 this.showMessage(this.getMensaje());
		}
		
		return procesoExitoso;
	}
	
	public boolean getUltimaCaptura() {
		return ultimaCaptura;
	}

	public void setUltimaFilaCaptura(boolean ultimaFilaCaptura) {
		this.ultimaCaptura = ultimaFilaCaptura;
	}
	
	

	
	

	public int procesoActualizarStatusEnvindoaCM(String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		int numFacXlote = 0;
		if(this.establecerConexionDB2()){
			
			try {
				numFacXlote = DAO.consultaAllFacturasListasCM(this.lote,idUsuario);
			} catch (SQLException e) {
				logger.info("Error al intentar traer las facturas para cambiar su status a "+Constants.STATUS_IMPORTANDO_CM);
			}
			if(numFacXlote>0){
				
				try {
					listaFacturasCapturadas=DAO.getFacturasLoteUsuario(this.lote,idUsuario);
				
					for(int i=0; i<listaFacturasCapturadas.size();i++){
						 Factura facturaCap= listaFacturasCapturadas.get(i);
						int status=Constants.STATUS_IMPORTANDO_CM;
						DAO.actualizaStatus(facturaCap, status);	
					}
					
					this.muestraFacturasPorLote();
					
				} catch (SQLException e) {
					logger.info("Error al intentar traer las facturas para cmabiar su status a "+Constants.STATUS_IMPORTANDO_CM);
				}
				
			}
			
			this.cerrarConexionDB2();	
		}
		
		return numFacXlote;
	}
	

	public int getActivaCargaMasiva() {
		return activaCargaMasiva;
	}

	public void setActivaCargaMasiva(int activaCargaMasiva) {
		this.activaCargaMasiva = activaCargaMasiva;
	}


	public int countFacturasPendientesCapturar(String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);
		int factPendientes=0;
		if(this.establecerConexionDB2()){
			
			try {
				 factPendientes=DAO.consultaFacturasPendientesCaptura(this.lote, idUsuario);
			} catch (SQLException e) {
				logger.info(" countFacturasPendientesCapturar: Error al intentar consultar si hay Facturas pendientes de capturar ");
			}
			this.cerrarConexionDB2();
		}
		return factPendientes;
	}
	
	
	/**
	 * consulta datos del proveedor en base al numero de proveedor que el
	 * usuario capturo
	 */
	public void procesoActualizaDatosProveedor() {
	
		String nombreMetodo = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		logger.info(nombreMetodo);

		if (this.txtNoProveedor.getText().isEmpty()) {
			this.txtNombreProv.setText("");
			this.txtRfcProveedor.setText("");
			/*jComboModel = new ADComboBoxModel(new String[]{""});
			this.txtSucursalProv.setModel(jComboModel);
			this.txtSucursalProv.setSelectedIndex(0);*/
			this.txtFolioRecepcion.setText("");

		} else {
			if (this.establecerConexionDB2()) {
				String numero = txtNoProveedor.getText();
				try {
					ArrayList<Proveedor> proveedorArray = DAO.consultaDatosProveedor(numero);
					if(proveedorArray.size()==0){
						this.txtNombreProv.setText("");
						this.txtRfcProveedor.setText("");
						/*jComboModel = new ADComboBoxModel(new String[]{""});
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);*/
						this.txtNoProveedor.setValue(numero);
					}
					else{
						//String[] proveedoresLista=new String[proveedorArray.size()];
						
						for(Proveedor pov:proveedorArray){
							if (pov.getNombre() == null) {
								this.txtNombreProv.setText("");
								this.txtRfcProveedor.setText("");
							} else {
								this.txtNombreProv.setText(pov.getNombre());
								this.txtRfcProveedor.setText(pov.getRfc());
								//proveedoresLista[proveedorArray.indexOf(pov)]=pov.getSucursal();
							}	
						}
						/*jComboModel = new ADComboBoxModel(proveedoresLista);
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);*/
						this.txtNoProveedor.setValue(numero);
					}
					
				} catch (SQLException e) {
					logger.info("Error al intentar traer los datos del Proveedor"
							+ e.getMessage());
					message = new Mensaje(BundleManager.getValue(
							Constants.MESSAGE_BUNDLE, "ERR001")
							+ e.getMessage(), titulo, 1);
					this.showMessage(message);
				}
				
				this.cerrarConexionDB2();
			}
				Date date = jDateChooserFechaRecepcion.getDate();
				StringBuffer folio = new StringBuffer();
				folio.append(txtNoProveedor.getText());
				folio.append("-");
				folio.append(getStringDate(date));
				folio.append("-");
				
				if(cmbPlaza.getSelectedIndex() == 0){
					folio.append("[PLAZA]");
				}else{
					folio.append(cmbPlaza.getSelectedItem().toString());
				}
				
				String folioRecepcion=folio.toString();
				this.txtFolioRecepcion.setText(folioRecepcion);
		}
	}
	
	/**
	 * te regresa un String en formato ddmmyear
	 * @param date2
	 * @return
	 */
	public  String getStringDate(java.util.Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		String date = calendar.get(Calendar.DAY_OF_MONTH)+completeData((calendar.get(Calendar.MONTH)+1))+completeData(calendar.get(Calendar.YEAR));
		return date;
	}
	
	/**
	 * agrega un 0 si al los 
	 * numeros del 1 al 9
	 * @param number
	 * @return
	 */
	private String completeData(int number) {
		if(number < 10){
			return "0"+number;
		}
		return ""+number;
	}
	/**
	 * valida Numero de 
	 * proveedor
	 * @return
	 */
	public boolean getNoProveedor() {
		String neNumber=txtNoProveedor.getText();
		boolean valido=false;
		if(neNumber.matches("[0-9]*")){
			valido=true;
		}
		return valido;
	}
	
	/**
	 * 
	 * @param fila
	 */
	public void setFilaSeleccioanda(int fila){
		this.filaSeleccionada=fila;
	}
	
	/**
	 * aumenta si se hizo alguna funncion en un registros
	 * cambia a 0 si se cambia de pagina
	 * @param param
	 */
	public void aumentarFila(){
			this.filaSeleccionada++;
			this.seleccionaFila(this.filaSeleccionada);
	}
	
	/**
	 * valida el campo Monto
	 * @return
	 */
	public boolean isMontoValido(){
		boolean valido=false;
		
		String cadenaInvalida="$,";
		String monto= this.txtMonto.getText();
		String montoSinCarac=Utilities.EliminaCaracteres(monto,cadenaInvalida);
		
		
		if(montoSinCarac.matches("[0-9]+.[0-9]*") || montoSinCarac.matches("[0-9]")|| monto.isEmpty()){
			valido=true;
		}
		return valido;
	}
	
	/**
	 * habilta botones del paginador
	 * @param b
	 */
	public void habilitaBotonesPaginador(boolean b){
		this.btnAnteriorPag.setEnabled(b);
		this.btnSiguientePag.setEnabled(b);
	}

	public boolean getActivarprimerRegistro() {
		return activarPrimerRegistro;
	}

	public void setActivarprimerRegistro(boolean activarprimerRefistro) {
		this.activarPrimerRegistro= activarprimerRefistro;
	}
	
	public void setPrimerCaptura(boolean b){
		this.primeraCaptura=b;
	}
	
	public int getTotalRegistros() {
		return totalRegistros;
	}
	
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	public void actualizaContadoresPagina(int totalRegistros2) {

		this.calcularTotalPaginas();
		if (this.primeraCaptura) {
			this.paginaActual++;
			this.actualizarIndicesPaginador();
			this.procesoActualizarDatosPginado();
			this.primeraCaptura = false;
			this.activarPrimerRegistro = true;
		}
		this.actualizarIndicesPaginador();
		this.procesoActualizarDatosPginado();
		
	}
	
	public void setPaginaActual(int numPagina) {
		this.paginaActual = numPagina;
	}
	
	public void setTotaldePaginas(int i) {
		this.totalPaginas=i;
		
	}
	
	public void setIndiceSup(int i) {
		this.indiceSup=i;
		
	}

	public void setIndiceInf(int i) {
		this.indiceInf=0;
		
	}
	
	/**
	 * inicializa los datos del paginador para mostrar los datos de las facturas
	 * cuando se abren desde el modulo de lotes
	 */
	public void actualizaTablaFacturas() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		if (this.establecerConexionDB2()) {
			try {
				int existeLote = DAO.ConsultaExisteLote(this.lote, idUsuario);
				if (existeLote != 0) {
					setTotalRegistros(existeLote);//

					this.primeraCaptura = false;
					setTotalRegistros(existeLote);//
					logger.info("Total de registros del lote " + getTotalRegistros());

					this.calcularTotalPaginas();

					this.actualizarIndicesPaginador();
					this.muestraFacturasPorLote();
					this.procesoActualizarDatosPginado();
				}

			} catch (SQLException e) {
				if (this.validarConexionDB2()) {
					logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG007"), titulo, 1);
					this.showMessage(message);
					System.exit(0);
				}
			}
		}

	}
}
