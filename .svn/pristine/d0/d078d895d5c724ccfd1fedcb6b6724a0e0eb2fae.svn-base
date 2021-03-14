package com.adinfi.sevCaptura.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.adinfi.sevCaptura.controller.CapturaGastosListener;
import com.adinfi.sevCaptura.controller.CapturaMercanciaListener;
import com.adinfi.sevCaptura.controller.FocusListenerCaptura;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.model.CapturaGastosModel;
import com.adinfi.sevCaptura.model.CapturaMercanciaModel;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.TGenericDocViewer;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;

public class CapturaMercanciaView extends JInternalFrame {

	private JPanel panelVisor=null;
	private JPanel panelDatosCaptura = null;
	private JPanel panelOpciones = null;
	private JPanel pnlCargarArchivos=null;
	private JPanel panelBtns =null;
	private JScrollPane scrollPaneTablaFacturas=null;
	private JScrollPane scrollPaneTablaFacturasSoporte=null;
	private JPanel panelPaginador =null;
	private JTable jTableFactura;
	private CapturaMercanciaModel model;
	private CapturaMercanciaListener listener;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel jPanelPrincipal;
	private JTextField txtNoFact;
	//private JTextField txtNoProveedor;
	private JFormattedTextField txtNoProveedor;
	private JTextField txtNombreProv;
	private JComboBox txtSucursalProv;
	private JTextField txtMonto;
	private JTextField txtNombreDoc;
	private JComboBox cbxRazonSocial;
	private JTextField txtRfcProveedor;
	private JLabel lblNewLabel;
	private JTextField txtFolioRecepcion;	
	private JDateChooser jDateChooserFechaRecepcion = null;
	private JDateChooser jDateChooserFechaFactura = null;
	private JTextField txtUrl=null;
	private JButton btnCargarCarpeta=null;
	private JButton btnCargarArchivo=null;
	private JButton btnCargaMasiva=null;
	private JButton btnCambiarUrl=null;
	private JButton btnDescartar=null;
	private JButton btnAnteriorPag=null;
	private JButton btnSiguientePag=null;
	private JTextField lblActualPag=null;
	private JTextField lblTotalPag=null;
	private JButton btnGuardar =null;
	private JButton btnEditar =null;
	private JTextArea textAreaVisor=null;
	private JButton btnAceptarJDialogSop=null;
	private JRadioButton rdbtnTipoFactura=null;
	private ButtonGroup  rdBtnsTipoDocumento=null;
	private JDialog jDialogDocumentoSoporte = null;
	private TGenericDocViewer visorManager = null;
	private FocusListenerCaptura listenerCapturaFocus =new FocusListenerCaptura();
	private JLabel lblNewLabel_1;
	
	private JComboBox<String> cmbPlaza;
	
	/**
	 * Launch the application.
	 */
	public CapturaMercanciaView(CapturaMercanciaModel model) {
		this.model = model; 
		this.visorManager = new TGenericDocViewer();
		this.initialize();
		this.setVisible(true);		
	}
	private void initialize(){
		this.setBounds(0, 0, 1190, 680);	
		this.setIconifiable(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.setResizable(false);
		this.setMaximizable(false);
		this.setContentPane(this.getJPanelPrincipal());
	}
	private JPanel getJPanelPrincipal() {
		if(jPanelPrincipal == null){
			jPanelPrincipal = new JPanel();
			jPanelPrincipal.setLayout(null);
			jPanelPrincipal.add(this.getPanelVisor());
			jPanelPrincipal.add(this.getPanelDatosCaptura());
			jPanelPrincipal.add(this.getScrollPaneTablaFacturas());
			jPanelPrincipal.add(this.getPanelOpciones());
			jPanelPrincipal.add(this.getPnlCargarArchivos());
			jPanelPrincipal.add(getPanelBtns());
			jPanelPrincipal.add(this.getPanelPaginador());
			
			jPanelPrincipal.add(this.getPanelPaginador());			

		}
		return jPanelPrincipal;
	}
	

	public void cierraDocuments(){
		this.visorManager.getVisor().closeDocument();	
	}
	
	/** Opens a document in the viewer.  The filename of the document is either passed in (originally
	 * coming from the program arguments) or is prompted for. */
	public void openDocument(String filename,boolean activate) {
		this.visorManager.openDocument(filename, activate);
	}
private JPanel getPanelVisor(){
		
		if(panelVisor==null){
			//panelVisor = new JPanel();
			panelVisor = visorManager.getPanelVisor();
			panelVisor.setBounds(2,37 ,790, 599);
		}
		return panelVisor;
	}
	
private JPanel getPnlCargarArchivos(){
	if(pnlCargarArchivos==null){
		pnlCargarArchivos = new JPanel();
		pnlCargarArchivos.setBounds(2, 2, 791, 32);
		pnlCargarArchivos.setLayout(null);
		pnlCargarArchivos.add(gettxtUrl());
		pnlCargarArchivos.add(getbtnCargarCarpeta());
		pnlCargarArchivos.add(getbtnCargarArchivo());	
	}
	
	return pnlCargarArchivos;
}
	private JTextField gettxtUrl() {
		if (txtUrl == null) {
			txtUrl=model.gettxtUrl();
			//txtUrl = new JTextField();
			txtUrl.setBounds(0, 2, 713, 28);
			txtUrl.setEnabled(false);
			txtUrl.setColumns(10);
		}
		return txtUrl;
	}
	private JButton getbtnCargarCarpeta(){
		if(btnCargarCarpeta==null){
			btnCargarCarpeta=model.getbtnCargarCarpeta();
			//btnCargarCarpeta = new JButton("");
			btnCargarCarpeta.setBounds(753, 2, 38, 28);
			btnCargarCarpeta.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_CARPETA)));
			btnCargarCarpeta.setToolTipText("Seleccionar Carpeta");
			btnCargarCarpeta.setActionCommand(Constants.CMD_CARGAR_CARPETA);
			
		}
		return btnCargarCarpeta;
	}
	
	private JButton getbtnCargarArchivo(){
		if(btnCargarArchivo==null){
			btnCargarArchivo=model.getbtnCargarArchivo();
			//btnCargarArchivo = new JButton("");
			btnCargarArchivo.setBounds(714, 2, 38, 28);
			btnCargarArchivo.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_DOCUMENTO)));
			btnCargarArchivo.setToolTipText("Seleccionar Archivo");
			btnCargarArchivo.setActionCommand(Constants.CMD_CARGAR_ARCHIVO);
		}
		return btnCargarArchivo;
	}
	
	private JPanel getPanelBtns(){
		if(panelBtns==null){
			panelBtns = new JPanel();
			panelBtns.setBackground(new Color(245, 222, 179));
			panelBtns.setBounds(1082, 2, 98, 32);
			panelBtns.setLayout(null);
			panelBtns.add(getBtnCargaMasiva());
			panelBtns.add(getBtnCambiarUrl());
			panelBtns.add(getBtnDescartar());		
		}
		return panelBtns;	
	}
	
	private JButton getBtnCargaMasiva(){
		if(btnCargaMasiva==null){
			btnCargaMasiva=model.getBtnCargaMasiva();
			//btnCargaMasiva= new JButton("");
			btnCargaMasiva.setBounds(2, 2, 28, 28);
			btnCargaMasiva.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_CARGA_MASIVA)));
			btnCargaMasiva.setToolTipText("Carga Masiva");
			btnCargaMasiva.setActionCommand(Constants.CMD_CARGA_MASIVA);
			btnCargaMasiva.setEnabled(false);
		}
		return btnCargaMasiva;
	}
	private JButton getBtnCambiarUrl(){
		if(btnCambiarUrl==null){
			btnCambiarUrl=model.getBtnCambiarURL();
			//btnCambiarUrl= new JButton("");
			btnCambiarUrl.setBounds(35, 2, 28, 28);
			btnCambiarUrl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_CAMBIAR_URL)));
			btnCambiarUrl.setToolTipText("Cambiar URL");
			btnCambiarUrl.setActionCommand(Constants.CMD_CAMBIAR_URL);
			btnCambiarUrl.setEnabled(false);
		}
		return btnCambiarUrl;
	}
	
	private JButton getBtnDescartar(){
		if(btnDescartar==null){
			btnDescartar=model.getBtnDescartar();
			//btnDescartar= new JButton("");
			btnDescartar.setBounds(68, 2, 28, 28);
			btnDescartar.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_DESCARTAR)));
			btnDescartar.setToolTipText("Descartar");
			btnDescartar.setActionCommand(Constants.CMD_DESCARTAR);
			btnDescartar.setEnabled(false);
		}
		return btnDescartar;
	}
	
	private JPanel getPanelDatosCaptura(){
		if(panelDatosCaptura==null){
			panelDatosCaptura = new JPanel();
			panelDatosCaptura.setBounds(795, 37, 385, 244);
			panelDatosCaptura.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panelDatosCaptura.setLayout(null);
			
			JLabel lblNoFact = new JLabel("No. Factura*");
			lblNoFact.setBounds(2, 2, 97, 14);
			panelDatosCaptura.add(lblNoFact);
			panelDatosCaptura.add(getTxtNoFact());
			
			JLabel lblProveedor = new JLabel("No. Proveedor*");
			lblProveedor.setBounds(137, 2, 108, 14);
			panelDatosCaptura.add(lblProveedor);
			panelDatosCaptura.add(getTxtNoProveedor());
			
			JLabel	lblNombreProv = new JLabel("Proveedor");
			lblNombreProv.setBounds(2, 40, 130, 14);
			panelDatosCaptura.add(lblNombreProv);
			panelDatosCaptura.add(getTxtNombreProv());
			
			JLabel lblRfcProv = new JLabel("RFC Proveedor");
			lblRfcProv.setBounds(272, 40, 113, 14);
			//lblRfcProv.setBounds(2, 76, 113, 14);
			panelDatosCaptura.add(lblRfcProv);
			panelDatosCaptura.add(getTxtRfcProveedor());
			
			/*JLabel lblSucursalProv = new JLabel("Plaza");
			lblSucursalProv.setBounds(2, 76, 183, 14);
			panelDatosCaptura.add(lblSucursalProv);
			panelDatosCaptura.add(getTxtSucursalProv());*/
			
			JLabel lblMonto = new JLabel("Monto");
			lblMonto.setBounds(2, 112, 46, 14);
			panelDatosCaptura.add(lblMonto);
			panelDatosCaptura.add(getTxtMonto());
			
			JLabel lblRazonSocial = new JLabel("Raz\u00F3n Social*");
			lblRazonSocial.setBounds(2, 148, 86, 14);
			panelDatosCaptura.add(lblRazonSocial);
			panelDatosCaptura.add(getCbxRazonSocial());
			
			JLabel lblPlaza = new JLabel("Plaza*");
			//lblPlaza.setBounds(2, 184, 46, 14);
			lblPlaza.setBounds(2, 76, 113, 14);
			panelDatosCaptura.add(lblPlaza);
			panelDatosCaptura.add(getCbxPlaza());

			JLabel lblFechaRecepcion = new JLabel("Fecha Recepcion*");
			lblFechaRecepcion.setBounds(272, 2, 112, 14);
			panelDatosCaptura.add(lblFechaRecepcion);
			panelDatosCaptura.add(getJDateChooserFechaRecepcion());
			
			JLabel lblFechaFact = new JLabel("Fecha Factura");
			panelDatosCaptura.add(getJDateChooserFechaFactura());
			lblFechaFact.setBounds(272, 76, 112, 14);
			panelDatosCaptura.add(lblFechaFact);
			
			panelDatosCaptura.add(getLblNewLabel());
			panelDatosCaptura.add(getTxtFolioRecepcion());
		}
		return panelDatosCaptura;
	}
	

	
	private JScrollPane getScrollPaneTablaFacturas(){
		if(scrollPaneTablaFacturas==null){
			 scrollPaneTablaFacturas = new JScrollPane();
			 scrollPaneTablaFacturas.setBounds(795, 281, 385, 309);
			scrollPaneTablaFacturas.setBorder(new LineBorder(Color.LIGHT_GRAY));
			scrollPaneTablaFacturas.setViewportView(getJTableDatos());
		}
		
		return scrollPaneTablaFacturas;
	}
	

	
	private JTable getJTableDatos() {
		if (jTableFactura == null) {
			//jTableDatos = new JTable(modelo); // Habilita para modificar la vista
			jTableFactura = model.getJTableDatos(); //Deshabilita para modificar la vista
			jTableFactura.setEnabled(false);
			jTableFactura.setRowHeight(20);
			jTableFactura.setName("jTableFactura");
			jTableFactura.setFocusable(false);
			
		}
		return jTableFactura;
	}
	

	
		

	private JButton getbtnGuardar(){
		if(btnGuardar==null){
			//btnGuardar = new JButton("");
			btnGuardar=model.getbtnGuardar();
			btnGuardar.setBounds(256, 1, 127, 26);
			btnGuardar.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_GUARDAR)));
			btnGuardar.setActionCommand(Constants.CMD_GUARDAR_FACTURA);
			btnGuardar.setMnemonic(KeyEvent.VK_ENTER);
			btnGuardar.registerKeyboardAction(btnGuardar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			btnGuardar.registerKeyboardAction(btnGuardar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);

			btnGuardar.setText("Guardar");
			btnGuardar.setEnabled(false);
			
		}
		return btnGuardar;
	}
	
	
	private JPanel getPanelPaginador(){
		if(panelPaginador==null){
			panelPaginador = new JPanel();
			panelPaginador.setBounds(795, 595, 386, 28);
			panelPaginador.setBorder(new LineBorder(new Color(192, 192, 192), 0));
			panelPaginador.setLayout(null);	
			panelPaginador.add(getBtnAnteriorPag());
			panelPaginador.add(getBtnSiguientePag());
			panelPaginador.add(getLblActualPag());
			lblNewLabel_1 = new JLabel("De");
			lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(97, 1, 38, 26);
			panelPaginador.add(lblNewLabel_1);
			panelPaginador.add(getLblTotalPag());
			panelPaginador.add(getbtnGuardar());
			//panelPaginador.add(getbtnEditar());
	
		}
			return panelPaginador;
		
	}


	private JPanel getPanelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new JPanel();
			panelOpciones.setBackground(new Color(245, 222, 179));
			panelOpciones.setBorder(new LineBorder(Color.LIGHT_GRAY));
		//	panelOpciones.setBounds(604, 2, 289, 32);
			panelOpciones.setBounds(795, 2, 284, 32);
			panelOpciones.setLayout(null);
			this.getBtnGroupTipoDucumento();
			panelOpciones.add(getRdnBtnFact());
			rdBtnsTipoDocumento.add(rdbtnTipoFactura);
			
		}
		return panelOpciones;
	}
	
	private ButtonGroup getBtnGroupTipoDucumento(){
		if(rdBtnsTipoDocumento==null){
			rdBtnsTipoDocumento=model.getBtnGroupTipoDucumento();
		}
		return rdBtnsTipoDocumento;
	}
	private JRadioButton getRdnBtnFact() {
		if (rdbtnTipoFactura == null) {
			rdbtnTipoFactura=model.getRdnBtnFact();
			rdbtnTipoFactura.setBounds(6, 7, 166, 23);
			rdbtnTipoFactura.setEnabled(false);
		}
		return rdbtnTipoFactura;
	}


	private JTextField getTxtNoFact() {
		if (txtNoFact == null) {
			//txtNoFact = new JTextField();
			txtNoFact=model.getTxtNoFact();
			txtNoFact.setBounds(2, 18, 130, 20);
			txtNoFact.addFocusListener(listenerCapturaFocus);
			txtNoFact.setColumns(10);
			txtNoFact.setEnabled(false);
		}
		return txtNoFact;
	}

	
	private JFormattedTextField getTxtNoProveedor() {
		if (txtNoProveedor == null) {
			//txtNoProveedor = new JFormattedTextField();
			txtNoProveedor=model.getTxtNoProveedor();
			txtNoProveedor.setBounds(137, 18, 130, 20);
			txtNoProveedor.setColumns(10);
			txtNoProveedor.addFocusListener(listenerCapturaFocus);
			txtNoProveedor.setEnabled(false);
			txtNoProveedor.setActionCommand("NoProveedor");
		}
		return txtNoProveedor;
	}

	private JTextField getTxtNombreProv() {
		if (txtNombreProv == null) {
			//txtNombreProv = new JTextField();
			txtNombreProv=model.getTxtNombreProv();
			txtNombreProv.setBounds(2, 54, 265, 20);
			//txtNombreProv.setBounds(2, 54, 380, 20);
			txtNombreProv.setColumns(10);
			txtNombreProv.setEditable(false);
		}
		return txtNombreProv;
	}
	
	
	
	private JComboBox getTxtSucursalProv() {
		if (txtSucursalProv == null) {
			//txtSucursalProv = new JTextField();
			txtSucursalProv=model.getTxtSucursalProv();
			txtSucursalProv.setBounds(2, 90, 265, 20);;
			txtSucursalProv.setEnabled(false);
		}
		return txtSucursalProv;
	}

	private JTextField getTxtMonto() {
		if (txtMonto == null) {
			//txtMonto = new JTextField();
			txtMonto=model.getTxtMonto();
			txtMonto.addFocusListener(listenerCapturaFocus);
			txtMonto.setBounds(2, 126, 128, 20);
			txtMonto.setColumns(10);
			txtMonto.setEnabled(false);
		}
		return txtMonto;
	}

	
	
	//	JComboBox cbxConcepto = new JComboBox();
	//cbxConcepto.setBounds(120, 133, 340, 20);
	

	private JComboBox getCbxRazonSocial() {
		if (cbxRazonSocial == null) {
			//cbxRazonSocial = new JComboBox();
			cbxRazonSocial=model.getCbxRazonSocial();
			cbxRazonSocial.addFocusListener(listenerCapturaFocus);
			cbxRazonSocial.setBounds(2, 162, 381, 20);
			cbxRazonSocial.setActionCommand(Constants.CMD_RAZON_SOCIAL);
			cbxRazonSocial.setEnabled(false);
		}
		return cbxRazonSocial;
	}

	private JComboBox<String> getCbxPlaza() {
		if (cmbPlaza == null) {
			//cbxPlaza = new JComboBox();
			cmbPlaza = model.getCbxPlaza();
			//txtPlaza.setBounds(2, 198, 381, 20);
			cmbPlaza.setBounds(2, 90, 265, 20);
			cmbPlaza.setActionCommand(Constants.CMD_PLAZA);
			cmbPlaza.addFocusListener(listenerCapturaFocus);
			cmbPlaza.setEnabled(false);
			//txtPlaza.setFocusable(false);
			//txtPlaza.setVisible(false);
		}
		return cmbPlaza;
	}

	private JDateChooser getJDateChooserFechaRecepcion() {
		if (jDateChooserFechaRecepcion == null) {
			//TODO Habilita para modificar la vista
			//jDateChooserFechaRecepcion = new JDateChooser(); 
			//TODO Deshabilita para modificar la vista
			jDateChooserFechaRecepcion = model.getJDateChooserFechaRecepcion();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			jDateChooserFechaRecepcion.setCalendar(gregorianCalendar);
			jDateChooserFechaRecepcion.setDateFormatString("dd-MM-yyyy");
			jDateChooserFechaRecepcion.setBounds(new Rectangle(272,18, 110,20));
			jDateChooserFechaRecepcion.setDate(null);
			jDateChooserFechaRecepcion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e){
					Calendar calendar = Calendar.getInstance();
				}
			});
			jDateChooserFechaRecepcion.setInheritsPopupMenu(true);
			jDateChooserFechaRecepcion.setEnabled(false);
			
		}
		return jDateChooserFechaRecepcion;
	}
	

	
	
	private JTextField getTxtRfcProveedor() {
		if (txtRfcProveedor == null) {
			//txtRfcProveedor = new JTextField();
			txtRfcProveedor=model.getTxtRfcProveedor();
			txtRfcProveedor.setBounds(272, 54, 110, 20);
			//txtRfcProveedor.setBounds(2, 90, 265, 20);
			txtRfcProveedor.setColumns(10);
			txtRfcProveedor.setEnabled(false);
			
		}
		return txtRfcProveedor;
	}


	
	private JDateChooser getJDateChooserFechaFactura() {
		if (jDateChooserFechaFactura == null) {
			//TODO Habilita para modificar la vista
			//jDateChooserFechaFactura = new JDateChooser();
			//TODO Deshabilita para modificar la vista
			jDateChooserFechaFactura = model.getJDateChooserFechaFactura(); 
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			jDateChooserFechaFactura.setCalendar(gregorianCalendar);
			jDateChooserFechaFactura.setDateFormatString("dd-MM-yyyy");
			jDateChooserFechaFactura.setBounds(new Rectangle(272, 90, 110, 20));
			jDateChooserFechaFactura.setDate(null);
			jDateChooserFechaFactura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e){
					Calendar calendar = Calendar.getInstance();
				}
			});
			jDateChooserFechaFactura.setInheritsPopupMenu(true);
			jDateChooserFechaFactura.setEnabled(false);
		}
		return jDateChooserFechaFactura;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Folio De Recepcion");
			lblNewLabel.setBounds(2, 184, 130, 14);
		}
		return lblNewLabel;
	}
	private JTextField getTxtFolioRecepcion() {
		if (txtFolioRecepcion == null) {
			//txtFolioRecepcion = new JTextField();
			txtFolioRecepcion=model.getTxtFolioRecepcion();
			txtFolioRecepcion.setBounds(2, 198, 381, 20);
			txtFolioRecepcion.setColumns(10);
			txtFolioRecepcion.setEnabled(false);
			txtFolioRecepcion.setFocusable(false);
		}
		return txtFolioRecepcion;
	}
	
	private JButton getBtnAnteriorPag() {
		if (btnAnteriorPag == null) {
			//btnAnteriorPag = new JButton("");
			btnAnteriorPag =model.getBtnAnteriorPag();
			btnAnteriorPag.setBounds(2, 1, 26, 26);
			btnAnteriorPag.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_BACKPAGE)));
			btnAnteriorPag.setLayout(null);
			btnAnteriorPag.setActionCommand(Constants.CMD_PAGINA_ANTERIOR);
			btnAnteriorPag.setEnabled(false);
			btnAnteriorPag.setFocusable(false); 
		}
		return btnAnteriorPag;
	}
	
	private JButton getBtnSiguientePag() {
		if (btnSiguientePag == null) {
			//btnSiguientePag = new JButton();
			btnSiguientePag =model.getBtnSiguientePag();
			btnSiguientePag.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_NEXTPAGE)));
			btnSiguientePag.setLayout(null);
			btnSiguientePag.setActionCommand(Constants.CMD_PAGINA_SIGUIENTE);
			btnSiguientePag.setBounds(205, 1, 26, 26);
			btnSiguientePag.setEnabled(false);
			btnSiguientePag.setFocusable(false);
		}
		return btnSiguientePag;
	}
	
	private JTextField getLblActualPag() {
		if (lblActualPag == null) {
			lblActualPag = new JTextField("1");
			lblActualPag=model.getLblActualPag();
			lblActualPag.setHorizontalAlignment(SwingConstants.RIGHT);
			lblActualPag.setBounds(33, 1, 60, 26);
			lblActualPag.setEnabled(false);
		}
		return lblActualPag;
	}
	
	private JTextField getLblTotalPag() {
		if (lblTotalPag == null) {
			lblTotalPag = new JTextField("2");
			lblTotalPag=model.getLblTotalPag();
			lblTotalPag.setHorizontalAlignment(SwingConstants.LEFT);
			lblTotalPag.setBounds(140, 1, 60, 26);
			lblTotalPag.setEnabled(false);
		}
		return lblTotalPag;
	}
	
	private JButton getBtnAceptarDocumentoSoporte() {
		if (btnAceptarJDialogSop == null) {
			//btnAceptarJDialogSop = new JButton("Aceptar");
			btnAceptarJDialogSop = model.getBtnAceptarDocumentoSoporte();
			btnAceptarJDialogSop.setBounds(366, 250, 89, 23);
			btnAceptarJDialogSop.setActionCommand("AsignarFactDocumento");
		}
		return btnAceptarJDialogSop;
	}
	

	
	public JTextField getTxtNombreDocumento(){
		if(txtNombreDoc==null){
			//txtNombreDoc = new JTextField();
			txtNombreDoc=model.getTxtNombreDoc();
			txtNombreDoc.setBounds(140, 250, 171, 20);
			txtNombreDoc.setColumns(10);
		}
		return txtNombreDoc;
	}
	public void cerrarJDialogSoporte(){
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		jDialogDocumentoSoporte.setVisible(false);
		System.out.println(nombreMetodo + ": Se cerro ventana de Importacion.");
	}
	
	
	
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this, mensaje);
	}
	
	public int  showMessageConfirmacion(Mensaje mensaje){
		int resultado= Utilities.showMensajeConfirmacion(this, mensaje);
		return resultado;
	}
	
	public void addCapturaMercanciaListener(
			CapturaMercanciaListener listener) {
		this.listener=listener;
		this.btnCargarArchivo.addActionListener(listener);
		this.btnCargarCarpeta.addActionListener(listener);
		this.btnCargaMasiva.addActionListener(listener);
		this.btnCambiarUrl.addActionListener(listener);
		this.btnDescartar.addActionListener(listener);
		this.btnGuardar.addActionListener(listener);
		//this.btnEditar.addActionListener(listener);
		this.btnSiguientePag.addActionListener(listener);
		this.btnAnteriorPag.addActionListener(listener);
		this.rdbtnTipoFactura.addActionListener(listener);
		this.cbxRazonSocial.addActionListener(listener);
		this.jTableFactura.addMouseListener(listener);
		this.txtNoProveedor.addFocusListener(listener);
		this.addInternalFrameListener(listener);
		this.jDateChooserFechaRecepcion.addPropertyChangeListener(listener);
		
		/**
		 * @author Adan
		 * Se agrega action listener al combo de plaza
		 * para que cuando se cambie la opcion
		 * se actualize el folio del registro.
		 */
		this.cmbPlaza.addActionListener(listener);
	}

}



