package com.adinfi.sevCaptura.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import com.adinfi.sevCaptura.controller.CapturaGastosListener;
import com.adinfi.sevCaptura.controller.FocusListenerCaptura;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.model.CapturaGastosModel;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.TGenericDocViewer;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;


public class CapturaGastosView extends JInternalFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelVisor = null;
	private JPanel panelDatosCaptura = null;
	private JPanel panelOpciones = null;
	private JPanel pnlCargarArchivos = null;
	private JPanel panelBtns = null;
	private JPanel panelPaginador = null;
	private JPanel jPanelPrincipal = null;
	
	private JScrollPane scrollPaneTablaFacturas = null;
	private JScrollPane scrollPaneTablaFacturasSoporte = null;
	
	private JTable jTableFactura = null;
	private JTable jTableFacturaSoporte = null;

	//private JTextField txtNoProveedor;
	private JTextField txtNoFact;
	private JTextField txtNombreProv;
	private JTextField txtMonto;
	private JTextField txtNombreDoc;
	private JTextField txtordenCompra = null;
	private JTextField txtNoSAF = null;
	private JTextField txtMontoMaximo = null;
	private JTextField txtNumTiendas = null;
	private JTextField txtRfcProveedor = null;
	private JTextField txtDocsLote = null;
	private JTextField txtDocsAProcesar = null;
	private JTextField txtDocsImportados = null;
	private JTextField txtDocsNoImportados = null;
	private JTextField txtMoneda = null;	
	private JTextField txtUrl = null;
	private JTextField lblActualPag = null;
	private JTextField lblTotalPag = null;
	private JTextField jTextFieldMercado = null;
	
	private JFormattedTextField txtNoProveedor;
	
	private JComboBox txtSucursalProv;
	private JComboBox cbxConcepto;
	private JComboBox cbxRazonSocial;
	private JComboBox cbxArea;
	private JComboBox cbxDestinatario;
	private JComboBox<String> cmbPlaza;
	
	private JDateChooser jDateChooserFechaRecepcion = null;
	private JDateChooser jDateChooserFechaFactura = null;
	
	private JButton btnCargarCarpeta=null;
	private JButton btnCargarArchivo=null;
	private JButton btnCargaMasiva=null;
	private JButton btnCambiarUrl=null;
	private JButton btnDescartar=null;
	private JButton btnAnteriorPag=null;
	private JButton btnSiguientePag=null;
	private JButton btnGuardar =null;
	private JButton btnAceptarCM=null;
	private JButton btnAceptarJDialogSop=null;
	
	private JRadioButton rdbtnTipoFactura=null;
	private JRadioButton rdbtnTipoSoporte =null;
	private JRadioButton rdbtnFactura48hrs=null;
	
	private ButtonGroup  rdBtnsTipoDocumento=null;
	
	private JDialog jDialogDocumentoSoporte = null;
	private JDialog jDialogStatusCargaMasiva = null;
	
	/** Create the frame. **/
	private JLabel lblNewLabel;
	private JLabel lblNoFact;
	private JLabel lblProveedor;
	private JLabel lblNombreProv;
	private JLabel lblSucursalProv;
	private JLabel lblMonto;
	private JLabel lblConcepto;
	private JLabel lblRazonSocial;
	private JLabel lblArea;
	private JLabel lblEnviarFact;
	private JLabel lblOrdenCompra;
	private JLabel lblNoSAF;
	private JLabel lblFechaRecepcion;
	private JLabel lblRfcProv;
	private JLabel lblFechaFact;
	private JLabel lblNewLabel_1;
	private JLabel lblMercado = null;
	private JLabel lblNmeroDeTiendas;
	private JLabel lblMontoMximo;
	private JLabel lblPlaza;
	
	private JProgressBar progressBar;
	public TGenericDocViewer visorManager = null;  //  @jve:decl-index=0:
	
	private CapturaGastosModel model;
	private CapturaGastosListener listener;
	private FocusListenerCaptura listenerCapturaFocus =new FocusListenerCaptura();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public CapturaGastosView(CapturaGastosModel model) {
		this.model = model; 
		this.visorManager = new TGenericDocViewer();
		this.initialize();
		this.setVisible(true);
		
	}
	
	/** Opens a document in the viewer.  The filename of the document is either passed in (originally
	 * coming from the program arguments) or is prompted for. */
	public void openDocument(String filename,boolean activate) {
		this.visorManager.openDocument(filename, activate);
	}
	
	
	public void cierraDocuments(){
		this.visorManager.getVisor().closeDocument();				
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
	
	public void addCapturaGastosListener(
			CapturaGastosListener listener) {
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
		this.rdbtnFactura48hrs.addActionListener(listener);
		this.rdbtnTipoSoporte.addActionListener(listener);
		this.cbxArea.addActionListener(listener);
		this.cbxConcepto.addActionListener(listener);
		this.cbxDestinatario.addActionListener(listener);
		this.cbxRazonSocial.addActionListener(listener);
		this.jTableFactura.addMouseListener(listener);
		this.txtNoProveedor.addFocusListener(listener);
		this.txtNoSAF.addFocusListener(listener);
		this.addInternalFrameListener(listener);
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
			jPanelPrincipal.add(this.getPanelBtns());
			jPanelPrincipal.add(this.getPanelPaginador());			
			//jPanelPrincipal.add( getJDialogContinuarCarga());
		}
		return jPanelPrincipal;
	}

	private JPanel getPanelVisor(){
		
		if(panelVisor==null){
			//panelVisor = new JPanel();
			//panelVisor = model.getPanelVisor();
			panelVisor = visorManager.getPanelVisor();
			
			panelVisor.setEnabled(false);
			panelVisor.setBounds(2, 37, 790, 599);
		}
		return panelVisor;
	}
	

	
	private JPanel getPnlCargarArchivos(){
		if(pnlCargarArchivos==null){
			pnlCargarArchivos = new JPanel();
			pnlCargarArchivos.setBounds(2, 2, 694, 32);
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
			txtUrl.setBounds(0, 2, 613, 28);
			txtUrl.setEnabled(false);
			txtUrl.setColumns(10);
		}
		return txtUrl;
	}
	private JButton getbtnCargarCarpeta(){
		if(btnCargarCarpeta==null){
			btnCargarCarpeta=model.getbtnCargarCarpeta();
			//btnCargarCarpeta = new JButton("");
			btnCargarCarpeta.setBounds(656, 2, 38, 28);
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
			btnCargarArchivo.setBounds(615, 2, 38, 28);
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
			panelDatosCaptura.setBackground(new Color(255, 255, 240));
			panelDatosCaptura.setBounds(795, 37, 385, 366);
			panelDatosCaptura.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panelDatosCaptura.setLayout(null);
			
			lblNoFact = new JLabel("No. Factura*");
			lblNoFact.setBounds(2, 2, 97, 14);
			panelDatosCaptura.add(lblNoFact);
			panelDatosCaptura.add(getTxtNoFact());
			
			lblProveedor = new JLabel("No. Proveedor*");
			lblProveedor.setBounds(137, 2, 108, 14);
			panelDatosCaptura.add(lblProveedor);
			panelDatosCaptura.add(getTxtNoProveedor());
			
			lblNombreProv = new JLabel("Proveedor");
			lblNombreProv.setBounds(2, 40, 130, 14);
			panelDatosCaptura.add(lblNombreProv);
			panelDatosCaptura.add(getTxtNombreProv());
			
			lblRfcProv = new JLabel("RFC Proveedor");
			lblRfcProv.setBounds(272, 40, 113, 14);
			//lblRfcProv.setBounds(2, 76, 113, 14);
			panelDatosCaptura.add(lblRfcProv);
			panelDatosCaptura.add(getTxtRfcProveedor());
			
			lblFechaFact = new JLabel("Fecha Factura*");
			lblFechaFact.setBounds(272, 76, 112, 14);
			panelDatosCaptura.add(lblFechaFact);
			panelDatosCaptura.add(getJDateChooserFechaFactura());
			
			lblPlaza = new JLabel("Plaza*");
			//lblPlaza.setBounds(2, 184, 46, 14);
			lblPlaza.setBounds(2, 76, 113, 14);
			panelDatosCaptura.add(lblPlaza);
			panelDatosCaptura.add(getCbxPlaza());
			
			/*lblSucursalProv = new JLabel("Plaza");
			lblSucursalProv.setBounds(2, 76, 183, 14);
			panelDatosCaptura.add(lblSucursalProv);*/
			//panelDatosCaptura.add(getTxtSucursalPrv());
			
			lblMonto = new JLabel("Monto*");
			lblMonto.setBounds(2, 112, 46, 14);			
			panelDatosCaptura.add(lblMonto);
			panelDatosCaptura.add(getTxtMonto());
			
			lblMercado = new JLabel("Mercado");
			lblMercado.setBounds(new Rectangle(272, 112, 93, 14));
			panelDatosCaptura.add(lblMercado);
			panelDatosCaptura.add(getJTextFieldMercado(), null);
			
			lblConcepto = new JLabel("Concepto*");
			lblConcepto.setBounds(2, 256, 85, 14);
			panelDatosCaptura.add(lblConcepto);
			panelDatosCaptura.add(getCbxConcepto());
			
			lblRazonSocial = new JLabel("Raz\u00F3n Social*");
			lblRazonSocial.setBounds(2, 148, 86, 14);
			panelDatosCaptura.add(lblRazonSocial);
			panelDatosCaptura.add(getCbxRazonSocial());
						
			lblArea = new JLabel("Area*");
			lblArea.setBounds(2, 184, 46, 14);
			panelDatosCaptura.add(lblArea);
			panelDatosCaptura.add(getCbxArea());
			
			lblEnviarFact = new JLabel("Enviar Factura a:");
			lblEnviarFact.setBounds(2, 220, 112, 14);
			panelDatosCaptura.add(lblEnviarFact);
			panelDatosCaptura.add(getCbxEnviarFactura());
			
			lblOrdenCompra = new JLabel("Orden de Compra");
			lblOrdenCompra.setBounds(2, 292, 123, 14);
			panelDatosCaptura.add(lblOrdenCompra);
			panelDatosCaptura.add(getTxtOrdenCompra());
			
			lblNoSAF = new JLabel("Número de SAF");
			lblNoSAF.setBounds(167, 292, 112, 14);
			panelDatosCaptura.add(lblNoSAF);
			panelDatosCaptura.add(getTxtNoSAF());
			
			lblFechaRecepcion = new JLabel("Fecha Recepcion*");
			lblFechaRecepcion.setBounds(272, 2, 112, 14);
			panelDatosCaptura.add(lblFechaRecepcion);
			panelDatosCaptura.add(getJDateChooserFechaRecepcion());
			
			lblNewLabel = new JLabel("Moneda*");
			lblNewLabel.setBounds(135, 112, 65, 14);
			panelDatosCaptura.add(lblNewLabel);
			panelDatosCaptura.add(getTxtMoneda());
			
			lblNmeroDeTiendas = new JLabel("N\u00FAmero de tiendas");
			lblNmeroDeTiendas.setBounds(2, 328, 130, 14);
			panelDatosCaptura.add(lblNmeroDeTiendas);
			panelDatosCaptura.add(getTxtNumTiendas());
			
			lblMontoMximo = new JLabel("Monto M\u00E1ximo");
			lblMontoMximo.setBounds(167, 328, 123, 14);
			panelDatosCaptura.add(lblMontoMximo);
			panelDatosCaptura.add(getTxtMontoMaximo());
			
		}
		
		return panelDatosCaptura;
	}
	

	
	private JScrollPane getScrollPaneTablaFacturas(){
		if(scrollPaneTablaFacturas==null){
			 scrollPaneTablaFacturas = new JScrollPane();
			 scrollPaneTablaFacturas.setBounds(795, 400, 385, 197);
			scrollPaneTablaFacturas.setBorder(new LineBorder(Color.LIGHT_GRAY));
			scrollPaneTablaFacturas.setViewportView(getJTableDatos());
		}
		
		return scrollPaneTablaFacturas;
	}
	
	private JScrollPane getScrollPaneTablaFacturasSoporte(){
		if(scrollPaneTablaFacturasSoporte==null){
			scrollPaneTablaFacturasSoporte = new JScrollPane();
			scrollPaneTablaFacturasSoporte.setBounds(10, 56, 445,183 );
			scrollPaneTablaFacturasSoporte.setBorder(new LineBorder(Color.LIGHT_GRAY));
			scrollPaneTablaFacturasSoporte.setViewportView(getJTableFacturaSoporte());
		}
		
		return scrollPaneTablaFacturasSoporte;
	}
	
	private JTable getJTableDatos() {
		if (jTableFactura == null) {
			//jTableFactura = new JTable(); // Habilita para modificar la vista
			jTableFactura = model.getJTableDatos(); //Deshabilita para modificar la vista
			jTableFactura.setEnabled(false);
			jTableFactura.setRowHeight(20);
			jTableFactura.setName("jTableFactura");
			jTableFactura.setFocusable(false);
			
		}
		return jTableFactura;
	}
	
	private JTable getJTableFacturaSoporte() {
		if (jTableFacturaSoporte == null) {
			//jTableDatos = new JTable(modelo); // Habilita para modificar la vista
			jTableFacturaSoporte = model.getJTableDatosFacturaSoporte(); //Deshabilita para modificar la vista
			jTableFacturaSoporte.setRowHeight(20);
			jTableFacturaSoporte.setName("jTableFacturaSoporte");
			
		}
		return jTableFacturaSoporte;
	}
	
		
	private JPanel getPanelPaginador(){
		if(panelPaginador==null){
			panelPaginador = new JPanel();
			panelPaginador.setBounds(795, 608, 386, 32);
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
	
	private JButton getbtnGuardar(){
		if(btnGuardar==null){
			//btnGuardar = new JButton("Guardar");
			btnGuardar=model.getbtnGuardar();
			btnGuardar.setBounds(256, 1, 127, 26);
			btnGuardar.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_GUARDAR)));
			btnGuardar.setActionCommand(Constants.CMD_GUARDAR_FACTURA);
			
			btnGuardar.setMnemonic(KeyEvent.VK_ENTER);
			btnGuardar.registerKeyboardAction(
					btnGuardar.getActionForKeyStroke(
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
	private JPanel getPanelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new JPanel();
			panelOpciones.setBackground(new Color(245, 222, 179));
			panelOpciones.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panelOpciones.setBounds(699, 2, 380, 32);
			panelOpciones.setLayout(null);
			this.getBtnGroupTipoDucumento();
			panelOpciones.add(getRdnBtnFact());
			panelOpciones.add(getRdnBtnFact48hrs());
			panelOpciones.add(getRdnBtnSoporte());
			rdBtnsTipoDocumento.add(rdbtnTipoFactura);
			rdBtnsTipoDocumento.add(rdbtnFactura48hrs);
			rdBtnsTipoDocumento.add(rdbtnTipoSoporte);
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
			//rdbtnTipoFactura = new JRadioButton();
			rdbtnTipoFactura=model.getRdnBtnFact();
			rdbtnTipoFactura.setBounds(2, 2, 80, 28);
			rdbtnTipoFactura.setActionCommand(Constants.CMD_TIPO_DOCUMENTO_FACTURA);
			rdbtnTipoFactura.setText("Factura");
			rdbtnTipoFactura.setEnabled(false);
		}
		return rdbtnTipoFactura;
	}
	private JRadioButton getRdnBtnFact48hrs() {
		if (rdbtnFactura48hrs == null) {
			//rdbtnFactura48hrs = new JRadioButton();
			rdbtnFactura48hrs=model.getRdnBtnFact48hrs();
			rdbtnFactura48hrs.setBounds(117, 2, 129, 28);
			rdbtnFactura48hrs.setActionCommand(Constants.CMD_TIPO_DOCUMENTO_FACTURA48hrs);
			rdbtnFactura48hrs.setText("Factura 48Hrs");
			rdbtnFactura48hrs.setEnabled(false);
		}
		return rdbtnFactura48hrs;
	}

	private JRadioButton getRdnBtnSoporte() {
		if (rdbtnTipoSoporte == null) {
			//rdbtnTipoSoporte = new JRadioButton("Soporte");
			rdbtnTipoSoporte= model.getRdnBtnSoporte();
			rdbtnTipoSoporte.setBounds(250, 2, 80, 28);
			rdbtnTipoSoporte.setActionCommand(Constants.CMD_TIPO_DOCUMENTO_SOPORTE);
			rdbtnTipoSoporte.setText("Soporte");
			rdbtnTipoSoporte.setEnabled(false);
		}
		return rdbtnTipoSoporte;
	}

	private JTextField getTxtNoFact() {
		if (txtNoFact == null) {
			//txtNoFact = new JTextField();
			txtNoFact=model.getTxtNoFact();
			txtNoFact.setBounds(2, 18, 130, 20);
			//txtNoFact.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
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
			txtNoProveedor.addFocusListener(listenerCapturaFocus);
			txtNoProveedor.setBounds(137, 18, 130, 20);
			txtNoProveedor.setColumns(10);
			txtNoProveedor.setEnabled(false);
			txtNoProveedor.setName("noProv");
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
			txtNombreProv.setEnabled(true);
			txtNombreProv.setEditable(false);

		}
		return txtNombreProv;
	}
	
	
	private JComboBox getTxtSucursalProv() {
		if (txtSucursalProv == null) {
			//txtSucursalProv = new JTextField();
			txtSucursalProv = model.getTxtSucursalProv();
			txtSucursalProv.setBounds(2, 90, 265, 20);
			txtSucursalProv.addFocusListener(listenerCapturaFocus);
			txtSucursalProv.setEnabled(false);
		}
		return txtSucursalProv;
	}

	private JTextField getTxtMonto() {
		if (txtMonto == null) {
			//txtMonto = new JTextField();
			txtMonto=model.getTxtMonto();
			txtMonto.setBounds(2, 126, 128, 20);
			txtMonto.setColumns(10);
			txtMonto.addFocusListener(listenerCapturaFocus);
			txtMonto.setEnabled(false);
		}
		return txtMonto;
	}

	
	private JComboBox getCbxConcepto() {
		if (cbxConcepto == null) {
			//cbxConcepto = new JComboBox();
			cbxConcepto=model.getCbxConcepto();
			cbxConcepto.setBounds(2, 270, 381, 20);
			cbxConcepto.setActionCommand(Constants.CMD_CONCEPTO);
			cbxConcepto.addFocusListener(listenerCapturaFocus);
			cbxConcepto.setEnabled(false);
		
		}
		return cbxConcepto;
	}
	//	JComboBox cbxConcepto = new JComboBox();
	//cbxConcepto.setBounds(120, 133, 340, 20);
	

	private JComboBox getCbxRazonSocial() {
		if (cbxRazonSocial == null) {
			//cbxRazonSocial = new JComboBox();
			cbxRazonSocial=model.getCbxRazonSocial();
			cbxRazonSocial.setBounds(2, 162, 381, 20);
			cbxRazonSocial.setActionCommand(Constants.CMD_RAZON_SOCIAL);
			cbxRazonSocial.addFocusListener(listenerCapturaFocus);
			cbxRazonSocial.setEnabled(false);
		}
		return cbxRazonSocial;
	}

	private JComboBox<String> getCbxPlaza() {
		if (cmbPlaza == null) {
			//txtPlaza = new JTextField();
			cmbPlaza = model.getCbxPlaza();
			//cmbPlaza.setBounds(2, 162, 381, 20);
			cmbPlaza.setBounds(2, 90, 265, 20);
			//cmbPlaza.setBounds(2, 198, 381, 20);
			//cmbPlaza.setActionCommand(Constants.CMD_PLAZA);
			cmbPlaza.setEnabled(false);
			//cmbPlaza.setVisible(false);
		}
		
		return cmbPlaza;
	}

	private JComboBox getCbxArea() {
		if (cbxArea == null) {
			//cbxArea = new JComboBox();
			cbxArea=model.getCbxArea();
			cbxArea.setBounds(2,198, 381, 20);
			cbxArea.setActionCommand(Constants.CMD_AREA);
			cbxArea.addFocusListener(listenerCapturaFocus);
			cbxArea.setEnabled(false);
		}
		return cbxArea;
	}

	private JComboBox getCbxEnviarFactura() {
		if (cbxDestinatario == null) {
			//cbxDestinatario = new JComboBox();
			cbxDestinatario=model.getCbxDestinatario();
			cbxDestinatario.setBounds(2, 234, 381, 20);
			cbxDestinatario.addFocusListener(listenerCapturaFocus);
			cbxDestinatario.setActionCommand(Constants.CMD_ENVIAR_FACTURA);
			cbxDestinatario.setEnabled(false);
		}
		return cbxDestinatario;
	}

	private JTextField getTxtOrdenCompra() {
		if (txtordenCompra == null) {
			//txtordenCompra = new JTextField();
			txtordenCompra=model.getTxtOrdenCompra();
			txtordenCompra.setBounds(2, 306, 160, 20);
			txtordenCompra.setColumns(10);
			txtordenCompra.addFocusListener(listenerCapturaFocus);
			txtordenCompra.setEnabled(false);
		}
		return txtordenCompra;
	}

	private JTextField getTxtNoSAF() {
		if (txtNoSAF == null) {
			//txtNoSAF = new JTextField();
			txtNoSAF=model.getTxtNoSAF();
			txtNoSAF.setBounds(167, 306, 216, 20);
			txtNoSAF.setColumns(10);
			txtNoSAF.setEnabled(false);
			txtNoSAF.addFocusListener(listenerCapturaFocus);
			txtNoSAF.setName("noSaf");
			
		}
		return txtNoSAF;
	}
	
	private JTextField getTxtNumTiendas() {
		if (txtNumTiendas == null) {
			//txtNumTiendas = new JTextField();
			txtNumTiendas=model.getTxtNumTiendas();
			txtNumTiendas.setBounds(2, 342, 160, 20);
			txtNumTiendas.setColumns(10);
			txtNumTiendas.addFocusListener(listenerCapturaFocus);
			txtNumTiendas.setEnabled(false);
		}
		return txtNumTiendas;
	}
	
	private JTextField getTxtMontoMaximo() {
		if (txtMontoMaximo == null) {
			//txtMontoMaximo = new JTextField();
			txtMontoMaximo=model.getTxtMontoMaximo();
			txtMontoMaximo.setBounds(167, 342, 216, 20);
			txtMontoMaximo.setColumns(10);
			txtMontoMaximo.addFocusListener(listenerCapturaFocus);
			txtMontoMaximo.setEnabled(false);
		}
		return txtMontoMaximo;
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
			jDateChooserFechaRecepcion.addFocusListener(listenerCapturaFocus);
			jDateChooserFechaRecepcion.setBounds(new Rectangle(272,18, 110,20));
			//jDateChooserFechaRecepcion.setDateFormatString(Utilities.nowDate());
			//jDateChooserFechaRecepcion.setDateFormatString(null);
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
			//jDateChooserFechaFactura = new JDateChooser(new Date(12,12,2012));
			//TODO Deshabilita para modificar la vista
			jDateChooserFechaFactura = model.getJDateChooserFechaFactura(); 
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			jDateChooserFechaFactura.setCalendar(gregorianCalendar);
			jDateChooserFechaFactura.setDateFormatString("dd-MM-yyyy");
			jDateChooserFechaFactura.setBounds(new Rectangle(272, 90, 110, 20));
			jDateChooserFechaFactura.addFocusListener(listenerCapturaFocus);
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
			lblNewLabel = new JLabel("Moneda*");
			lblNewLabel.setBounds(135, 112, 65, 14);
		}
		return lblNewLabel;
	}
	
	private JTextField getTxtMoneda() {
		if (txtMoneda == null) {
			//txtMoneda = new JTextField();
			txtMoneda=model.getTxtMoneda();
			txtMoneda.setBounds(135, 126, 60, 20);
			txtMoneda.addFocusListener(listenerCapturaFocus);
			txtMoneda.setEnabled(false);
		}
		return txtMoneda;
	}
	/**
	 * This method initializes jTextFieldMercado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMercado() {
		if (jTextFieldMercado == null) {
			//jTextFieldMercado = new JTextField();
			jTextFieldMercado =model.getJTextFieldMercado();
			jTextFieldMercado.setBounds(272, 126, 110, 20);	
			jTextFieldMercado.addFocusListener(listenerCapturaFocus);
			jTextFieldMercado.setEnabled(false);
		}
		return jTextFieldMercado;
	}

	private JButton getBtnAnteriorPag() {
		if (btnAnteriorPag == null) {
			//btnAnteriorPag = new JButton("");
			btnAnteriorPag =model.getBtnAnteriorPag();
			btnAnteriorPag.setBounds(2, 1, 26, 26);
			btnAnteriorPag.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_BACKPAGE)));
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
			btnAceptarJDialogSop = new JButton("Aceptar");
			btnAceptarJDialogSop = model.getBtnAceptarDocumentoSoporte();
			btnAceptarJDialogSop.setBounds(366, 250, 89, 23);
			btnAceptarJDialogSop.setActionCommand("AsignarFactDocumento");
		}
		return btnAceptarJDialogSop;
	}
	
	public JDialog getJDialogFacturasoporte(){
		if(jDialogDocumentoSoporte == null){
			jDialogDocumentoSoporte = model.getJDialogDocumentoSoporte();	
			jDialogDocumentoSoporte = new JDialog();
			jDialogDocumentoSoporte.setBounds(0, 0, 481, 322);
			JLabel lbl= new JLabel("Seleccione el n\u00FAmero de F\u00E1ctura a la Que se asociara el Documento Soporte:");
			lbl.setBounds(10, 11, 445,35 );
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel = new JLabel("Nombre Documento");
			lblNewLabel.setBounds(10, 250, 114, 14);
			jDialogDocumentoSoporte.getContentPane().setLayout(null);
			jDialogDocumentoSoporte.setLocationRelativeTo(null);
			jDialogDocumentoSoporte.setModal(true);
			jDialogDocumentoSoporte.setResizable(false);
			jDialogDocumentoSoporte.setTitle("Facturas");
			jDialogDocumentoSoporte.setName("jDialogDocSoporte");
			jDialogDocumentoSoporte.getContentPane().add(lbl);
			jDialogDocumentoSoporte.getContentPane().add(lblNewLabel);
			jDialogDocumentoSoporte.getContentPane().add(this.getScrollPaneTablaFacturasSoporte());
			jDialogDocumentoSoporte.getContentPane().add(getBtnAceptarDocumentoSoporte());	
			jDialogDocumentoSoporte.getContentPane().add(getTxtNombreDocumento());
			jDialogDocumentoSoporte.setName("cerrarVentanaSoporte");
			jDialogDocumentoSoporte.addWindowListener(this.listener);
			
			this.jDialogSoporteAddActionListener(jDialogDocumentoSoporte);
			
		}
		//jDialogDocumentoSoporte.setVisible(true);
		return jDialogDocumentoSoporte ;
	}
	public JDialog jDialogCargaMasiva(){
		if(jDialogStatusCargaMasiva==null){
			
			jDialogStatusCargaMasiva = model.getJDialogStatusCM();	
			//jDialogStatusCargaMasiva = new JDialog();
			
			jDialogStatusCargaMasiva.setBounds(500, 200, 420, 218);
			
			jDialogStatusCargaMasiva.getContentPane().setLayout(null);
			jDialogStatusCargaMasiva.setLocationRelativeTo(null);
			jDialogStatusCargaMasiva.setModal(false);
			jDialogStatusCargaMasiva.setResizable(false);
			
			JLabel lblNewLabel = new JLabel("Total De Documentos en Lote:");
			lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 11, 174, 14);
			
			
			JLabel lblTotalDeDocumentos = new JLabel("Total de Documentos a Procesar:");
			lblTotalDeDocumentos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos.setBounds(10, 36, 191, 14);
			
			
			JLabel lblTotalDeDocumentos_1 = new JLabel("Total de Documentos Importados:");
			lblTotalDeDocumentos_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos_1.setBounds(10, 61, 191, 14);
		
			
			JLabel lblTotalDeDocumentos_2 = new JLabel("Total de Documentos no Importados:");
			lblTotalDeDocumentos_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos_2.setBounds(10, 86, 206, 14);
			
			jDialogStatusCargaMasiva.getContentPane().add(lblNewLabel);
			jDialogStatusCargaMasiva.getContentPane().add(lblTotalDeDocumentos);
			jDialogStatusCargaMasiva.getContentPane().add(lblTotalDeDocumentos_1);
			jDialogStatusCargaMasiva.getContentPane().add(lblTotalDeDocumentos_2);
			jDialogStatusCargaMasiva.getContentPane().add(getProgressBar());
			jDialogStatusCargaMasiva.getContentPane().add(getBtnAceptarStatusCM());
			jDialogStatusCargaMasiva.getContentPane().add(getTxtDocsLote());
			jDialogStatusCargaMasiva.getContentPane().add(getTxtDocsAProcesar());
			jDialogStatusCargaMasiva.getContentPane().add(getTxtDocsNoImportados());
			jDialogStatusCargaMasiva.getContentPane().add(getTxtNombreDocumento());
			
			
			
		}
		jDialogStatusCargaMasiva.setVisible(true);
		this.jDiaStatusCMAddActionListener(jDialogStatusCargaMasiva);
		return jDialogStatusCargaMasiva;
	}
	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar(0,100);
			progressBar= model.getProgressBar();
			progressBar.setBounds(10, 121, 384, 23);
			
			//progressBar.setPreferredSize(new Dimension(300, 20));
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}
	
	private JButton getBtnAceptarStatusCM() {
		if (btnAceptarCM == null) {
			//btnAceptarCM = new JButton("Aceptar");
			btnAceptarCM =model.getBtnAceptarStatusCM();
			btnAceptarCM.setActionCommand(Constants.CMD_ACEPTAR_STATUS_CM);
			btnAceptarCM.setBounds(305, 146, 89, 23);
			btnAceptarCM.setVisible(false);
		}
		return btnAceptarCM;
	}
	
	public JTextField getTxtDocsLote(){
		if(txtDocsLote==null){
			//txtDocsLote = new JTextField();
			txtDocsLote=model.getTxtDocsLote();
			txtDocsLote.setBounds(215, 8, 179, 20);
			txtDocsLote.setColumns(10);
			txtDocsLote.setEnabled(false);
		}
		return txtDocsLote;
	}
	
	public JTextField getTxtDocsAProcesar(){
		if(txtDocsAProcesar==null){
			//txtDocsAProcesar = new JTextField();
			txtDocsAProcesar=model.getTxtDocsAProcesar();
			txtDocsAProcesar.setBounds(215, 33, 179, 20);
			txtDocsAProcesar.setColumns(10);
			txtDocsAProcesar.setEnabled(false);
		}
		return txtDocsAProcesar;
	}
	
	public JTextField getTxtDocsNoImportados(){
		if(txtDocsNoImportados==null){
			//txtDocsNoImportados = new JTextField();
			txtDocsNoImportados=model.getTxtDocsNoImportados();
			txtDocsNoImportados.setBounds(215, 58, 179, 20);
			txtDocsNoImportados.setColumns(10);
			txtDocsNoImportados.setEnabled(false);
			
		}
		return txtDocsNoImportados;
	}
	
	public JTextField getTxtDocsAImportados(){
		if(txtDocsImportados==null){
		//	txtDocsImportados = new JTextField();
			txtDocsLote=model.getTxtDocsAImportados();
			txtDocsImportados.setBounds(215, 83, 179, 20);
			txtDocsImportados.setColumns(10);
			txtDocsImportados.setEnabled(false);
		}
		return txtDocsImportados;
	}
	
	public JTextField getTxtNombreDocumento(){
		if(txtNombreDoc==null){
			//txtNombreDoc = new JTextField();
			txtNombreDoc=model.getTxtNombreDoc();
			txtNombreDoc.setBounds(140, 250, 171, 20);
			txtNombreDoc.addFocusListener(listenerCapturaFocus);
			txtNombreDoc.setColumns(10);
			
		}
		return txtNombreDoc;
	}
	public void cerrarJDialogSoporte(){
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		this.jDialogDocumentoSoporte.setVisible(false);
		this.jDialogDocumentoSoporte=null;
	}
	public void cerrarJDialogEnviarACM(){
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		jDialogStatusCargaMasiva.setVisible(false);
		System.out.println(nombreMetodo + ": Se cerro ventana de Status CM.");
	}
	
	private boolean jDialogSoporteAddActionListener(JDialog ventanaDialogo){
		boolean seAgregoListener = false;
		
		if(ventanaDialogo != null){
			Component[] componentes = ventanaDialogo.getContentPane().getComponents();
			for(int i=0; i< componentes.length; i++){
				Component comp = componentes[i];
				if(comp instanceof JButton){
					((JButton) comp).addActionListener(this.listener);
				}else if(comp instanceof JTable){
					((JTable)comp).addMouseListener(this.listener);
				}
			}
			seAgregoListener = true;
		}
		
		return seAgregoListener;
	}
	private boolean jDiaStatusCMAddActionListener(JDialog ventanaDialogo){
		boolean seAgregoListener = false;
		
		if(ventanaDialogo != null){
			Component[] componentes = ventanaDialogo.getContentPane().getComponents();
			for(int i=0; i< componentes.length; i++){
				Component comp = componentes[i];
				if(comp instanceof JButton){
					((JButton) comp).addActionListener(this.listener);
				}
			}
			seAgregoListener = true;
		}
		
		return seAgregoListener;
	}
	
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this, mensaje);
	}
	
	public int  showMessageConfirmacion(Mensaje mensaje){
		int resultado= Utilities.showMensajeConfirmacion(this, mensaje);
		return resultado;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
