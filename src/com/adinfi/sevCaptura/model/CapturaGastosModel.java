package com.adinfi.sevCaptura.model;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import com.adinfi.sevCaptura.dao.CapturaGastosDAO;
import com.adinfi.sevCaptura.dao.CapturaGastosDAOInterface;
import com.adinfi.sevCaptura.dao.CapturaGastosDAOQuery;
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
import com.adinfi.sevCaptura.resources.JTextFieldLimit;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;

public class CapturaGastosModel {
	private ADComboBoxModel jComboModel = null;
	public static Logger logger = Logger.getLogger(CapturaGastosModel.class);
	private JTable jTableDatosFact = null;
	private JPanel panelVisor = null;
	private JTable jTableFacturaSoporte = null;
	private DefaultTableModel defaultTableModelDatos = null;
	private DefaultTableModel defaultTableModelDatosSoporte = null;
	private JRadioButton rdbtnTipoFactura = null;
	private JRadioButton rdbtnTipoSoporte = null;
	private JRadioButton rdbtnFactura48hrs = null;
	private ButtonGroup rdBtnsTipoDocumento = null;
	private JTextField txtNoFact = null;
	private JFormattedTextField txtNoProveedor = null;
	private JTextField txtNombreProv = null;
	private JComboBox txtSucursalProv = null;
	private JTextField txtMonto = null;
	private JTextField txtMercado = null;
	private JTextField txtNombreDoc = null;
	private DefaultComboBoxModel defaultComboConcepto = null;
	private JComboBox cbxConcepto = null;
	private DefaultComboBoxModel defaultComboArea = null;
	private JComboBox cbxRazonSocial = null;
	private DefaultComboBoxModel defaultComboEmpresa = null;
	private JComboBox cbxArea = null;
	private DefaultComboBoxModel defaultComboDestinatario = null;
	private JComboBox cbxEnviarFactura = null;
	private JTextField txtordenCompra = null;
	private JTextField txtNoSAF = null;
	private JTextField txtMontoMaximo=null;
	private JTextField txtNumTiendas=null;
	private JTextField txtRfcProveedor = null;
	private JTextField txtMoneda = null;
	private JDateChooser jDateChooserFechaRecepcion = null;
	private JDateChooser jDateChooserFechaFactura = null;
	private JTextField txtUrl = null;
	private JTextField txtDocsLote = null;
	private JTextField txtDocsAProcesar = null;
	private JTextField txtDocsImportados = null;
	private JTextField txtDocsNoImportados = null;
	private JProgressBar progressBar;
	private JButton btnCargarCarpeta = null;
	private JButton btnCargarArchivo = null;
	public JButton btnCargaMasiva = null;
	private JButton btnCambiarURL = null;
	private JButton btnDescartar = null;
	private JButton btnAnteriorPag = null;
	private JButton btnSiguientePag = null;
	private JButton btnAceptarJDialogSop = null;
	private JButton btnAceptarCM = null;
	private JTextField lblActualPag = null;
	private JTextField lblTotalPag = null;
	private JButton btnGuardar = null;
	private JTextArea txtAreaVisor = null;
	private CapturaGastosDAOInterface DAO = null;
	private int tipoDocumento = 1;
	private ArrayList<Factura> listaFacturasSoporte = new ArrayList<Factura>();
	private ArrayList<Factura> listaFacturasCapturadas = new ArrayList<Factura>();
	public ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
	private ArrayList<Area> listaAreas = null;
	private ArrayList<Empresa> listaEmpresas = null;
	private ArrayList<Concepto> listaConceptos = null;
	private ArrayList<Destinatario> listaDestinatarios = null;
	private JInternalFrame jFrameMensajes = null;
	private String titulo = "Sistema de Captura";
	private JDialog jDialogDocumentoSoporte = null;
	private JDialog jDialogStatusCM = null;
	private Mensaje message;
	private int fila = 0;
	boolean checkSeleccionado = false;
	private int regPorPagina = 0;
	private int totalRegistros= 0;
	private int totalPaginas = 0;
	private int paginaActual = 0;
	private int indiceInf = 0;
	private int indiceSup = 0;
	boolean primeraCaptura = true;
	private int activaCargaMasiva = 0;
	private boolean ultimaCaptura = false;
	private String idUsuario = null;
	private boolean activarprimerRegistro = false;
	private Date fechaRecepcion = null;
	
	private JComboBox<String> cmbPlaza = null; //TODO

	/** ATRIBUTOS PARA EL CONTROL DE LA CLASE **/
	private int filaSeleccionada = 0;

	public CapturaGastosModel() {
		this.DAO = new CapturaGastosDAO();
		this.regPorPagina = Integer.valueOf(BundleManager.getValue(Constants.CONFIG_BUNDLE, "reg.por.pagina"));

	}

	public int getPaginaActual() {
		return this.paginaActual;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setPaginaActual(int numPagina) {
		this.paginaActual = numPagina;
	}

	public void setPrimerCaptura(boolean b) {
		this.primeraCaptura = b;
	}

	public Mensaje getMensaje() {
		return this.message;
	}

	public String lote = null;
	public String plaza = null;

	public String getPlaza() {
		return plaza;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getLote() {
		return lote;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public JPanel getPanelVisor() {
		if (panelVisor == null) {
			panelVisor = new JPanel();

		}
		return panelVisor;
	}

	public JTable getJTableDatos() {
		if (jTableDatosFact == null) {
			jTableDatosFact = new JTable(getTableModelDatos()) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			};
			jTableDatosFact.setRowHeight(20);
			jTableDatosFact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableDatosFact.getTableHeader().setReorderingAllowed(false);

			int columnas = defaultTableModelDatos.getColumnCount();

			TableColumn columna = null;
			for (int i = 0; i < columnas; i++) {
				columna = jTableDatosFact.getColumnModel().getColumn(i);

				if (i == 0) {
					columna.setPreferredWidth(20);
				} else if (i == 1) {
					columna.setPreferredWidth(70);
				} else if (i == 2) {
					columna.setPreferredWidth(70);
				} else if (i == 3) {
					columna.setPreferredWidth(20);
					columna.setCellRenderer(new ImageRenderer());
				}

				columna.setResizable(false);
			}

		}
		return jTableDatosFact;
	}

	public DefaultTableModel getTableModelDatos() {
		if (defaultTableModelDatos == null) {
			String[] lista = { "id", "No.Proveedor", "Factura", "Estado" };
			defaultTableModelDatos = new DefaultTableModel(lista, 9);
		}
		return defaultTableModelDatos;
	}

	public JTable getJTableDatosFacturaSoporte() {
		if (jTableFacturaSoporte == null) {
			jTableFacturaSoporte = new JTable(getTableModelDatosFacturaSoporte()) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			};
			jTableFacturaSoporte.setRowHeight(20);
			jTableFacturaSoporte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableFacturaSoporte.getTableHeader().setReorderingAllowed(false);

			int columnas = defaultTableModelDatosSoporte.getColumnCount();

			TableColumn columna = null;
			for (int i = 0; i < columnas; i++) {
				columna = jTableFacturaSoporte.getColumnModel().getColumn(i);
				if (i == 0) {
					columna.setPreferredWidth(160);
				} else if (i == 1) {
					columna.setPreferredWidth(160);
				} else if (i == 2) {
					columna.setPreferredWidth(90);
				}
				columna.setResizable(false);
			}

		}
		return jTableFacturaSoporte;
	}

	public DefaultTableModel getTableModelDatosFacturaSoporte() {
		if (defaultTableModelDatosSoporte == null) {
			String[] lista = { "Número Factura", "Proveedor", "Monto" };
			defaultTableModelDatosSoporte = new DefaultTableModel(lista, 100);
		}
		return defaultTableModelDatosSoporte;
	}

	public JButton getbtnCargarCarpeta() {
		if (btnCargarCarpeta == null) {
			btnCargarCarpeta = new JButton("");
		}
		return btnCargarCarpeta;
	}

	public JButton getbtnCargarArchivo() {
		if (btnCargarArchivo == null) {
			btnCargarArchivo = new JButton("");
		}
		return btnCargarArchivo;
	}

	public JButton getBtnCargaMasiva() {
		if (btnCargaMasiva == null) {
			btnCargaMasiva = new JButton("");
		}
		return btnCargaMasiva;
	}

	public JButton getBtnCambiarURL() {
		if (btnCambiarURL == null) {
			btnCambiarURL = new JButton("");
		}
		return btnCambiarURL;
	}

	public JButton getBtnDescartar() {
		if (btnDescartar == null) {
			btnDescartar = new JButton("");
		}
		return btnDescartar;
	}

	public JTextField getTxtNoFact() {
		if (txtNoFact == null) {
			//int maxLength = Integer.valueOf(BundleManager.getValue(Constants.CONFIG_BUNDLE, "long.noFactura"));
			
			txtNoFact = new JTextField();
			//txtNoFact.setDocument(new JTextFieldLimit(maxLength));
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
		if (jComboModel == null) {
			jComboModel = new ADComboBoxModel(new String[] { "" });
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

	public DefaultComboBoxModel getCbxModelArea() {
		if (defaultComboArea == null) {
			defaultComboArea = new DefaultComboBoxModel();
			defaultComboArea.addElement("<Seleccione una opción>");
			this.llenarComboArea();
			if (listaAreas != null) {
				for (int i = 0; i < listaAreas.size(); i++) {
					defaultComboArea.addElement(listaAreas.get(i).getNombre());

				}
			}
		}
		return defaultComboArea;
	}

	public JTextField gettxtUrl() {
		if (txtUrl == null) {
			txtUrl = new JTextField();
		}
		return txtUrl;
	}

	public DefaultComboBoxModel getCbxModelEmpresa() {
		if (defaultComboEmpresa == null) {
			defaultComboEmpresa = new DefaultComboBoxModel();
			defaultComboEmpresa.addElement("<Seleccione una opción>");
			this.llenarComboEmpresa();
			if (listaEmpresas != null) {
				for (int i = 0; i < listaEmpresas.size(); i++) {
					defaultComboEmpresa.addElement(listaEmpresas.get(i).getNombre());

				}
			}
		}
		return defaultComboEmpresa;
	}

	public JComboBox getCbxArea() {
		if (cbxArea == null) {
			cbxArea = new JComboBox(getCbxModelArea());

		}
		return cbxArea;
	}

	public JComboBox<String> getCbxPlaza() {
		if (cmbPlaza == null) {
			/**
			 * @author Adan
			 * Se cambia por combobox.
			 */
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

	public JComboBox getCbxConcepto() {
		if (cbxConcepto == null) {
			cbxConcepto = new JComboBox(getCbxModelConcepto());
		}
		return cbxConcepto;
	}

	public DefaultComboBoxModel getCbxModelConcepto() {
		if (defaultComboConcepto == null) {
			defaultComboConcepto = new DefaultComboBoxModel();
			defaultComboConcepto.addElement("<Seleccione una opción>");
		}
		return defaultComboConcepto;
	}

	public DefaultComboBoxModel getCbxModelDestinatario() {
		if (defaultComboDestinatario == null) {
			defaultComboDestinatario = new DefaultComboBoxModel();
			defaultComboDestinatario.addElement("<Seleccione una opción>");
		}
		return defaultComboDestinatario;
	}

	public void procesoLlenarCbxDestinatario() {
		defaultComboDestinatario.removeAllElements();
		defaultComboDestinatario.addElement("<Seleccione una opción>");

		int area = defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem());

		if (area > 0) {

			this.llenarComboDestinatario();
			if (listaDestinatarios != null) {
				for (int i = 0; i < listaDestinatarios.size(); i++) {
					defaultComboDestinatario.addElement(listaDestinatarios.get(i).getNombres()+" "+listaDestinatarios.get(i).getApellidos());
				}
			}
		}
	}

	public void procesoLlenarCbxConcepto() {
		defaultComboConcepto.removeAllElements();
		defaultComboConcepto.addElement("<Seleccione una opción>");
		int area = defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem());

		if (area > 0) {

			this.llenarComboConcepto();
			if (listaAreas != null) {
				for (int i = 0; i < listaConceptos.size(); i++) {
					defaultComboConcepto.addElement(listaConceptos.get(i).getDescripcion());
				}
			}
		}
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
	
	public JTextField getTxtMontoMaximo() {
		if (txtMontoMaximo == null) {
			txtMontoMaximo = new JTextField();
			txtMontoMaximo.setColumns(10);
		}
		return txtMontoMaximo;
	}

	public JTextField getTxtNumTiendas() {
		if (txtNumTiendas == null) {
			txtNumTiendas = new JTextField();
			txtNumTiendas.setColumns(10);
		}
		return txtNumTiendas;
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

	public JTextField getTxtMoneda() {
		if (txtMoneda == null) {
			txtMoneda = new JTextField(3);
			txtMoneda.setColumns(10);
		}
		return txtMoneda;
	}

	public JTextArea getTxtAreaVisor() {
		if (txtAreaVisor == null) {
			txtAreaVisor = new JTextArea();
		}
		return txtAreaVisor;
	}

	public JDateChooser getJDateChooserFechaRecepcion() {
		if (jDateChooserFechaRecepcion == null) {
			jDateChooserFechaRecepcion = new JDateChooser();
		}
		return jDateChooserFechaRecepcion;
	}

	public JDateChooser getJDateChooserFechaFactura() {
		if (jDateChooserFechaFactura == null) {
			jDateChooserFechaFactura = new JDateChooser();
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

	public ButtonGroup getBtnGroupTipoDucumento() {
		if (this.rdBtnsTipoDocumento == null) {
			rdBtnsTipoDocumento = new ButtonGroup();
		}
		return rdBtnsTipoDocumento;
	}

	public JRadioButton getRdnBtnFact() {
		if (rdbtnTipoFactura == null) {
			rdbtnTipoFactura = new JRadioButton("Factura");
		}
		return rdbtnTipoFactura;
	}

	public JRadioButton getRdnBtnFact48hrs() {
		if (rdbtnFactura48hrs == null) {
			rdbtnFactura48hrs = new JRadioButton("Factura 48hrs");
		}
		return rdbtnFactura48hrs;
	}

	public JRadioButton getRdnBtnSoporte() {
		if (rdbtnTipoSoporte == null) {
			rdbtnTipoSoporte = new JRadioButton("Soporte");
		}
		return rdbtnTipoSoporte;
	}

	public JButton getbtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton();
		}
		return btnGuardar;
	}

	public JDialog getJDialogDocumentoSoporte() {
		if (jDialogDocumentoSoporte == null) {
			jDialogDocumentoSoporte = new JDialog();
		}
		return jDialogDocumentoSoporte;
	}

	public JDialog getJDialogStatusCM() {
		if (jDialogStatusCM == null) {
			jDialogStatusCM = new JDialog();
		}
		return jDialogStatusCM;
	}

	public JButton getBtnAceptarDocumentoSoporte() {
		if (btnAceptarJDialogSop == null) {
			btnAceptarJDialogSop = new JButton("Aceptar");
		}
		return btnAceptarJDialogSop;
	}

	public JButton getBtnAceptarStatusCM() {
		if (btnAceptarCM == null) {
			btnAceptarCM = new JButton("Aceptar");
		}
		return btnAceptarCM;
	}

	public JTextField getTxtDocsLote() {
		if (txtDocsLote == null) {
			txtDocsLote = new JTextField();
		}
		return txtDocsLote;
	}

	public JTextField getTxtDocsAProcesar() {
		if (txtDocsAProcesar == null) {
			txtDocsAProcesar = new JTextField();
		}
		return txtDocsAProcesar;
	}

	public JTextField getTxtDocsNoImportados() {
		if (txtDocsNoImportados == null) {
			txtDocsNoImportados = new JTextField();
		}
		return txtDocsNoImportados;
	}

	public JTextField getTxtDocsAImportados() {
		if (txtDocsImportados == null) {
			txtDocsImportados = new JTextField();
		}
		return txtDocsImportados;
	}

	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar(0, 100);
		}
		return progressBar;
	}

	/**
	 * Este metodo implementa un JFileChooser para que el usuario pueda
	 * seleccionar un documento o ubicacion de estos. VAR: tipoImportacion.
	 * Descripcion: Representa el tipo de importacion de documento.
	 * OPCIONES:valTipoArchivo 1 = Seleccion de 1 documento. 2 = Seleccion de
	 * todos los documentos de una carpeta.
	 * 
	 * @param frame
	 *            - InternalFrame donde se desplegara la busqueda.
	 * @return File - Ubicacion seleccionada por el usuario.
	 */
	public File getImportDocumentURL(JInternalFrame frame, int tipoImportacion, int nombreDialog) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		File file = null;
		JFileChooser fileChooser = new JFileChooser();

		if (tipoImportacion == Constants.IMPORTA_UN_DOCUMENTO) {
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			fileChooser.setFileFilter(new FileFilter() {

				String[] extension = { "doc", "DOC", "xml", "XML", "PDF", "pdf", "JPG", "jpg", "BMP", "bmp", "gif", "GIF" };

				@Override
				public boolean accept(File f) {
					String[] strPath = f.getAbsolutePath().split("\\.");
					int position = (strPath.length - 1);
					return (strPath[position].equals(extension[0]) || strPath[position].equals(extension[1]) || strPath[position].equals(extension[2]) || strPath[position].equals(extension[3])
							|| strPath[position].equals(extension[4]) || strPath[position].equals(extension[5]) || strPath[position].equals(extension[6]) || strPath[position].equals(extension[7])
							|| strPath[position].equals(extension[8]) || strPath[position].equals(extension[9]) || strPath[position].equals(extension[10]) || strPath[position].equals(extension[11]))
							|| f.isDirectory();
				}

				@Override
				public String getDescription() {
					return null;
				}
			});

		} else if (tipoImportacion == Constants.IMPORTA_CARPETA) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}

		if (nombreDialog == 1) {
			int var = fileChooser.showDialog(frame, "Importar");
			if (var == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		} else if (nombreDialog == 2) {
			int var = fileChooser.showDialog(frame, "Cambiar");
			if (var == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		}

		return file;
	}

	private void calcularTotalPaginas() {
		int residuo = (getTotalRegistros() % regPorPagina);

		if (residuo == 0) {
			this.totalPaginas = (getTotalRegistros() / regPorPagina);
		} else {
			this.totalPaginas = (getTotalRegistros() / regPorPagina) + 1;
		}

		if (this.totalPaginas > 1) {
			this.habilitaBotonesPaginador(true);
		}
		else
			this.habilitaBotonesPaginador(false);
	}

	/**
	 * actualiza los datos del paginador
	 */
	private void actualizarIndicesPaginador() {
		int dif = regPorPagina - 1;
		this.indiceInf = (paginaActual * regPorPagina) - dif;
		this.indiceSup = indiceInf + dif;
	}

	public boolean procesoCambiarPagina(String accion) {
		/* falta agregar q muestra primer reg */

		boolean cambio = false;
		if (accion.equals(Constants.CMD_PAGINA_SIGUIENTE)) {
			this.calcularTotalPaginas();
			if (paginaActual < totalPaginas) {
				paginaActual++;
			}
			this.actualizarIndicesPaginador();
			this.muestraFacturasPorLote();
			this.procesoActualizarDatosPginado();
			cambio = true;

		} else if (accion.equals(Constants.CMD_PAGINA_ANTERIOR)) {
			this.calcularTotalPaginas();
			if (paginaActual != 1) {
				paginaActual--;
			}
			this.actualizarIndicesPaginador();
			this.muestraFacturasPorLote();
			this.procesoActualizarDatosPginado();
			cambio = true;
		}

		return cambio;

	}

	private void procesoActualizarDatosPginado() {
		if (paginaActual != 0 && totalPaginas != 0) {
			this.lblActualPag.setText(paginaActual + "");
			this.lblTotalPag.setText(totalPaginas + "");
		}
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * Este metodo se encarga de leer el archivo o carpeta ingresados y los
	 * agrega a la base de datos en DB2, despues los muestra en la tabla de
	 * datos. VAR: tipoImportacion. Descripcion: Representa el tipo de
	 * importacion de documento. OPCIONES: 1 = Seleccion de 1 documento. 2 =
	 * Seleccion de todos los documentos de una carpeta.
	 * 
	 * @param URL
	 *            - Direccion o nombre del documento
	 * @param tipo
	 *            - Tipo de importacion del documento
	 */
	public boolean agregarDocumento(File file, int tipo, Usuario usuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		this.txtUrl.setText("");
		String urlDocumento = null;
		Factura nuevoRegistro = null;
		Estado status = null;
		int id_fact = 0;

		boolean procesoExitoso = false;

		/*
		 * 1.- GenerarLote
		 */

		if (this.lote == null) {
			this.lote = Utilities.getLoteActual();

		}

		if (file != null) {
			if (valExisteDireccion(file)) {
				if (tipo == Constants.IMPORTA_UN_DOCUMENTO) {
					logger.info("Importando Documento: "+file.getAbsolutePath());
					urlDocumento = file.toString();
					if (this.valTipoArchivo(urlDocumento)) {
						if (!file.isDirectory()) {
							nuevoRegistro = new Factura(urlDocumento);
							status = new Estado(Constants.STATUS_PENDIENTE_CAPTURA);
							status.setDescripcion(Constants.STATUS_DES_PENDIENTE_CAPTURA);
							nuevoRegistro.setEstado(status);
							nuevoRegistro.setUsuario(usuario);
							Destinatario destinatario = new Destinatario();
							destinatario.setIdUsuario(usuario.getIdUsuario());
							nuevoRegistro.setDestinatario(destinatario);
							nuevoRegistro.setLote(this.lote);
							nuevoRegistro.setSoporte(0);
							nuevoRegistro.setTipoDocumento(Constants.DOCUMENTO_TIPO_FACTURA);
							if (this.establecerConexionDB2()) {
								id_fact = insertarDocumento(nuevoRegistro);
								if (id_fact == 0) {
									logger.error("No se inserto la factura en FACTURACAPTURA");
								} else {
									nuevoRegistro.setId(id_fact);
									if (insertarEstadoDocumento(nuevoRegistro)) {
										fila++; // fila
										nuevoRegistro.setFila(fila);
										if (listaFacturas.size() <= this.regPorPagina) {
											this.agregarDocumentoArray(fila, nuevoRegistro);
										}
										setTotalRegistros(getTotalRegistros()+1);
										this.actualizaContadoresPagina(getTotalRegistros());
										this.muestraFacturasPorLote();
										
										/*verificar si fecha captura ya tiene datos*/
										this.insertarInicipoFechaCaptura();
										procesoExitoso = true;
									} else {
										logger.error("No se inserto en FACTURASTATUSCAPTURA con el id " + id_fact);
									}
								}
								cerrarConexionDB2();
							}
						}
					}
				} else if (tipo == Constants.IMPORTA_CARPETA) {
					logger.info("Importando doc de la Carpeta");
					if (this.establecerConexionDB2()) {
						String[] listaArchivosOrigen = file.list();
						this.txtUrl.setText(file.toString());
						if (listaArchivosOrigen.length > 0) {
							for (String nombreArchivo : listaArchivosOrigen) {
								File inFile = new File(file.getAbsolutePath()+File.separator+nombreArchivo);
								if (!inFile.isDirectory()) {
									urlDocumento = inFile.toString();
									if (valTipoArchivo(nombreArchivo)) {
										status = new Estado(Constants.STATUS_PENDIENTE_CAPTURA);
										status.setDescripcion(Constants.STATUS_DES_PENDIENTE_CAPTURA);
										nuevoRegistro = new Factura(urlDocumento);
										nuevoRegistro.setEstado(status);
										nuevoRegistro.setUsuario(usuario);
										nuevoRegistro.setLote(this.lote);
										nuevoRegistro.setSoporte(0);
										String tipoFactura = Constants.DOCUMENTO_TIPO_FACTURA;
										nuevoRegistro.setTipoDocumento(tipoFactura);
										id_fact = insertarDocumento(nuevoRegistro);
										if (id_fact == 0) {
											logger.info("No se inserto el documento como factura.");
										} else {
											nuevoRegistro.setId(id_fact);
											if (insertarEstadoDocumento(nuevoRegistro)) {
												fila++; // fila
												nuevoRegistro.setFila(fila);
												if (listaFacturas.size() <= this.regPorPagina) {
													this.agregarDocumentoArray(fila, nuevoRegistro);
												}
												setTotalRegistros(getTotalRegistros()+1);
												this.calcularTotalPaginas();
												if (this.primeraCaptura) {
													this.paginaActual++;
													this.actualizarIndicesPaginador();
													this.procesoActualizarDatosPginado();
													this.activarprimerRegistro = true;
													this.primeraCaptura = false;
												}
												/*verificar si fecha captura ya tiene datos*/
												this.insertarInicipoFechaCaptura();
												procesoExitoso = true;
											} else {
												logger.error("No se inserto el status de la factura en FACTURASTATUSCAPTURA  con el ID " + id_fact);
											}
										}
									}
								}
							}
							this.actualizarIndicesPaginador();
							this.procesoActualizarDatosPginado();
							this.muestraFacturasPorLote();

						} else {

							message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG029"), titulo, 1);
							this.showMessage(message);
						}

						this.cerrarConexionDB2();
					}
				}
			} else {
				message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG0024"), titulo, 1);
				this.showMessage(message);
			}
		}

		return procesoExitoso;
	}// cierre Metodo

	

	/**
	 * Inserta la fecha de inicio de
	 * Captura en la la tabla ReporteCarga
	 */
	private void insertarInicipoFechaCaptura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		Timestamp fechaInicioCap=null;
		if(this.establecerConexionDB2()){
		try {
			String tipoCaptura=Constants.REPORTECARGA_FACTURA_GASTO;
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
	 * Inserta la fecha de la ultima factura capturada
	 */
	public void insertarFechaFinCaptura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		Timestamp fechaFinCap=null;
		
		if(this.establecerConexionDB2()){
			try {
					String tipoCaptura=Constants.REPORTECARGA_FACTURA_GASTO;
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

	public void actualizaContadoresPagina(int totalRegistros2) {		
		this.calcularTotalPaginas();
		if (this.primeraCaptura) {
			this.paginaActual++;
			this.actualizarIndicesPaginador();
			this.procesoActualizarDatosPginado();
			this.primeraCaptura = false;
			this.activarprimerRegistro = true;
		}
		this.actualizarIndicesPaginador();
		this.procesoActualizarDatosPginado();
		
	}

	private boolean valTipoArchivo(String url) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		boolean resultado = false;

		int dot = url.lastIndexOf('.');
		String extencion = (dot == -1) ? "" : url.substring(dot + 1);
		if (extencion.equals("pdf") || extencion.equals("PDF") || extencion.equals("BMP") || extencion.equals("bmp") || extencion.equals("TIFF") || extencion.equals("tiff") || extencion.equals("XML")
				|| extencion.equals("xml") || extencion.equals("JPG") || extencion.equals("jpg") || extencion.equals("gif") || extencion.equals("gif"))

		{
			resultado = true;

		}
		return resultado;
	}

	private void agregarDocumentoArray(int fila, Factura nuevoRegistro) {

		if ((fila != 0) && (listaFacturas.size() < 10)) {

			listaFacturas.add(nuevoRegistro);
		}
	}

	/**
	 * Cambia la Url actual por la url del File
	 * 
	 * @param file
	 */
	public void procesoCambiarUrl(File file) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		String NewUrlDocumento = file.toString();
		String nombreNuevo = file.getName();
		if (valTipoArchivo(nombreNuevo)) {
			if (this.establecerConexionDB2()) {
				try {
					int idFact = this.listaFacturas.get(filaSeleccionada).getId();
					int actualizo = DAO.actualizaURL(idFact, NewUrlDocumento);
					if (actualizo != 0) {
						this.listaFacturas.get(filaSeleccionada).setUrl(NewUrlDocumento);
					}
				} catch (SQLException e) {
					if (this.validarConexionDB2()) {
						logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
						this.showMessage(message);
					}
				}
				this.cerrarConexionDB2();
				}
		}
	}

	public boolean procesoDescartarDocumento(Usuario usuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		boolean procesoExitoso = false;
		if (this.establecerConexionDB2()) {
			try {
				this.listaFacturas.get(filaSeleccionada).setUsuario(usuario);
				Destinatario destinatario = new Destinatario();
				destinatario.setIdUsuario(listaFacturas.get(filaSeleccionada).getUsuario().getIdUsuario());
				listaFacturas.get(filaSeleccionada).setDestinatario(destinatario);
				
				DAO.eliminarRegistro(this.listaFacturas.get(filaSeleccionada));
				DAO.borrarStatusCaptura(this.listaFacturas.get(filaSeleccionada).getId());
			
				procesoExitoso=true;
				this.totalRegistros--;
				int residuo = (getTotalRegistros() % regPorPagina);
			
				if (residuo == 0 && paginaActual != 1) {
					this.paginaActual--;
				} 
			} catch (SQLException e) {
				logger.info("procesoDescartarDocumento" + e.getMessage());

				if (this.validarConexionDB2()) {
					logger.error("Error en el procesoDescartarDocumento()", e);

					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				}

			}
			this.cerrarConexionDB2();
		}
		return procesoExitoso;
	}

	/**
	 * valida la direccion seleccionada
	 * 
	 * @param file
	 * @return true si la direccion existe
	 */
	public boolean valExisteDireccion(File file) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean resultado = false;

		if (file.exists()) {
			resultado = true;
		} else {
			logger.info(nombreMetodo + "   " + "la ruta del archivo:"+ file.getAbsolutePath() +" no existe.");
		}

		return resultado;
	}

	



	public boolean establecerConexionDB2() {
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;

		try {

			conexionAbierta = DAO.establecerConexionDB2();

		} catch (SQLException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;

		} catch (InstantiationException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;

		} catch (IllegalAccessException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;

		} catch (ClassNotFoundException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;
		}

		if (seGeneroExcepcion) {

			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), titulo, 1);
			this.showMessage(message);
		}
		return conexionAbierta;
	}

	public void cerrarConexionDB2() {
		try {

			DAO.cerrarConexionDB2();

		} catch (SQLException e) {
			logger.info("cerrarConexionDB2" + e.getMessage());
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
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
		}

		return conexionValida;
	}

	/**
	 * inserta los datos del nuevo documento a la tabla "Factura Captura"
	 * 
	 * @param factura
	 * @return
	 */
	private int insertarDocumento(Factura factura) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		int idFact = 0;

		try {
			idFact = DAO.insertarDocumento(factura);
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

		return idFact;
	}

	/**
	 * agrega a la tabla "FacturastatusCaptura" el status del nuevo documento
	 * 
	 * @param factura
	 * @return
	 */
	private boolean insertarEstadoDocumento(Factura factura) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		boolean inserto = false;
		try {
			inserto = DAO.insertarStatusFactura(factura);
			inserto = true;
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
		return inserto;
	}

	public void muestraPrimerRegistro() {
		filaSeleccionada = 0;
		this.seleccionaFila(filaSeleccionada);
	}

	/**
	 * cambia de registro en la tabjTableDatosFactjTableDatosFact
	 * 
	 * @param fila
	 */
	public void seleccionaFila(int fila) {
		jTableDatosFact.changeSelection(fila, 0, false, false);
	}

	/**
	 * habilita la seleccion de la tabla
	 * 
	 * @param b
	 */
	public void habilitaTablaDatos(boolean b) {
		this.jTableDatosFact.setEnabled(b);

	}

	/**
	 * habilita la seleccion de la tabla
	 * 
	 * @param b
	 */
	public void habilitaTablaDatosFactSoporte(boolean b) {
		this.jTableFacturaSoporte.setEnabled(b);
	}

	/**
	 * habilita botones de las funciones o deshabilita segun el caso
	 * 
	 * @param b
	 */
	public void habilitaBotonesFunciones(boolean b) {

		this.btnCambiarURL.setEnabled(b);
		this.btnDescartar.setEnabled(b);
		// this.btnEditar.setEnabled(b);
		this.btnGuardar.setEnabled(b);

		if (this.activaCargaMasiva == 1) {
			this.btnCargaMasiva.setEnabled(false);
		} else {
			this.btnCargaMasiva.setEnabled(true);
		}

	}

	/**
	 * habilta botones del paginador
	 * 
	 * @param b
	 */
	public void habilitaBotonesPaginador(boolean b) {
		this.btnAnteriorPag.setEnabled(b);
		this.btnSiguientePag.setEnabled(b);
	}

	/**
	 * habilita los botones de importacion archivo y carpeta
	 * 
	 * @param b
	 */
	public void habilitaBotonesImportacion(boolean b) {
		this.btnCargarArchivo.setEnabled(b);
		this.btnCargarCarpeta.setEnabled(b);
	}

	/**
	 * habilita los campos de captura
	 * 
	 * @param b
	 */
	public void habilitaCampoCaptura(boolean b) {
		this.txtMoneda.setEnabled(b);
		this.txtMonto.setEnabled(b);
		this.txtMercado.setEnabled(b);
		this.txtNoFact.setEnabled(b);
		this.txtNoProveedor.setEnabled(b);
		this.txtNoSAF.setEnabled(b);
		this.txtordenCompra.setEnabled(b);
		//this.txtSucursalProv.setEnabled(b);
		this.jDateChooserFechaFactura.setEnabled(b);
		this.jDateChooserFechaRecepcion.setEnabled(b);
		this.cbxArea.setEnabled(b);
		this.cbxConcepto.setEnabled(b);
		this.cbxEnviarFactura.setEnabled(b);
		this.cbxRazonSocial.setEnabled(b);
		this.txtNoFact.setFocusable(b);
		this.txtNumTiendas.setEnabled(b);
		this.txtMontoMaximo.setEnabled(b);
		
		/**
		 * @author Adan
		 * Habilitar cmbo de plazas.
		 */
		this.cmbPlaza.setEnabled(b);
	}

	public void habilitaRdBtnsTipoDocumento(boolean b) {
		this.rdbtnTipoFactura.setEnabled(b);
		this.rdbtnFactura48hrs.setEnabled(b);
		this.rdbtnTipoSoporte.setEnabled(b);
		rdbtnTipoFactura.setSelected(true);
	}

	

	public void llenarComboArea() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		listaAreas = new ArrayList<Area>();
		if (establecerConexionDB2()) {
			try {
				listaAreas = DAO.getListaAreas();

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

	public void llenarComboEmpresa() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		listaEmpresas = new ArrayList<Empresa>();
		if (establecerConexionDB2()) {
			try {

				listaEmpresas = DAO.getListaEmpresas();
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

	public void llenarComboDestinatario() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		int area = defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem());
		int areaId = listaAreas.get(area - 1).getId();
		logger.info("Area Seleccionada  " + areaId);

		this.listaDestinatarios = new ArrayList<Destinatario>();
		if (establecerConexionDB2()) {
			try {
				listaDestinatarios = DAO.getListaDestinatarios(areaId);
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

	public void llenarComboConcepto() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		int area = defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem());
		int areaId = listaAreas.get(area - 1).getId();

		listaConceptos = new ArrayList<Concepto>();
		if (establecerConexionDB2()) {
			try {

				listaConceptos = DAO.getListaConceptos(areaId);
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

	/**
	 * valida si el campo txtModeda solo tiene 3 digitos
	 * 
	 * @return
	 */
	private boolean esMonedaValida() {

		boolean valida = false;
		String MONEDA_PATTERN = "^[A-Za-z]{3}$";
		Pattern pattern;
		Matcher matcher;

		String monedaTextField = this.txtMoneda.getText();

		if (!monedaTextField.isEmpty()) {

			pattern = Pattern.compile(MONEDA_PATTERN);
			matcher = pattern.matcher(monedaTextField);
			valida = matcher.matches();
		}
		return valida;
	}

	public boolean procesoValidarcamposCaptura(Usuario usuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		String IdUsuario = usuario.getIdUsuario();
		boolean capturaCompleta = true;
		int status = 0;

		if (this.filaSeleccionada >= 0 && filaSeleccionada <= this.listaFacturas.size()) {
			Proveedor proveedor = new Proveedor();
			Area area = new Area();
			Destinatario destinatario = new Destinatario();
			Empresa empresa = new Empresa();
			Concepto concepto = new Concepto();

			this.listaFacturas.get(filaSeleccionada).setProveedor(proveedor);
			this.listaFacturas.get(filaSeleccionada).setArea(area);
			this.listaFacturas.get(filaSeleccionada).setDestinatario(destinatario);
			this.listaFacturas.get(filaSeleccionada).setUsuario(usuario);
			this.listaFacturas.get(filaSeleccionada).setEmpresa(empresa);
			this.listaFacturas.get(filaSeleccionada).setConcepto(concepto);

			if (this.txtMonto.getText().isEmpty()) {
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).setMonto("0");
			} else {
				String monto = this.txtMonto.getText();
				String montoReal = monto.replaceAll(",", "");
				this.listaFacturas.get(filaSeleccionada).setMonto(montoReal);
			}

			if (this.txtMoneda.getText().isEmpty()) {
				capturaCompleta = false;
			} else {
				this.listaFacturas.get(filaSeleccionada).setMoneda(this.txtMoneda.getText());
			}

			if (this.txtNoFact.getText().isEmpty()) {
				capturaCompleta = false;
			} else {
				this.listaFacturas.get(filaSeleccionada).setNoFact(this.txtNoFact.getText());
			}

			if (this.txtNombreProv.getText().isEmpty()) {
				capturaCompleta = false;
			} else {
				this.listaFacturas.get(filaSeleccionada).getProveedor().setNombre(this.txtNombreProv.getText());
			}

			if (this.txtNoProveedor.getText().isEmpty()) {
				capturaCompleta = false;
			} else {
				String numero = this.txtNoProveedor.getText();
				numero = numero.replaceAll(" ", "");
				this.listaFacturas.get(filaSeleccionada).getProveedor().setNumero(numero);
			}
		
			this.listaFacturas.get(filaSeleccionada).setNoSaf(this.txtNoSAF.getText());
			
			if(txtNumTiendas.getText().isEmpty()){
				this.listaFacturas.get(filaSeleccionada).setNumTiendas(0);
			}else{
				this.listaFacturas.get(filaSeleccionada).setNumTiendas(Integer.parseInt(txtNumTiendas.getText()));
			}
			
			if(txtMontoMaximo.getText().isEmpty()){
				this.listaFacturas.get(filaSeleccionada).setMontoMaximo("0");
			}else{
				this.listaFacturas.get(filaSeleccionada).setMontoMaximo(txtMontoMaximo.getText());
			}

			this.listaFacturas.get(filaSeleccionada).setOrdenCompra(this.txtordenCompra.getText());
			// }
			if (this.txtRfcProveedor.getText().isEmpty()) {
				capturaCompleta = false;
			} else {
				this.listaFacturas.get(filaSeleccionada).getProveedor().setRfc(this.txtRfcProveedor.getText());
			}
			
			/*if(this.txtSucursalProv != null 
					&& this.txtSucursalProv.getSelectedItem() != null
					&& !this.txtSucursalProv.getSelectedItem().toString().isEmpty()){
				
				if (this.txtSucursalProv.getSelectedItem().toString().isEmpty()) {
					capturaCompleta = false;
				} else {
					this.listaFacturas.get(filaSeleccionada).getProveedor().setSucursal(this.txtSucursalProv.getSelectedItem().toString());
				}
			}*/
			
			if (this.jDateChooserFechaFactura.getDate() == null) {
				this.listaFacturas.get(filaSeleccionada).setFechaFactura(null);
				this.listaFacturas.get(filaSeleccionada).setFechaContable(null);
				this.listaFacturas.get(filaSeleccionada).setFechaCambio(null);
				capturaCompleta = false;
			} else {
				Date date = jDateChooserFechaFactura.getDate();
				this.listaFacturas.get(filaSeleccionada).setFechaFactura(date);
				this.listaFacturas.get(filaSeleccionada).setFechaCambio(date);
			}

			if (this.jDateChooserFechaRecepcion.getDate() == null) {
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).setFechaRecepcion(null);
			} else {
				Date date = jDateChooserFechaRecepcion.getDate();
				this.listaFacturas.get(filaSeleccionada).setFechaRecepcion(date);
				/*
				 * agrego la fecha recepcion en una var por si hay un sig
				 * registro lo muestre
				 */
				this.fechaRecepcion = null;
				this.fechaRecepcion = jDateChooserFechaRecepcion.getDate();
			}
			if (defaultComboEmpresa.getIndexOf(defaultComboEmpresa.getSelectedItem()) == 0) {
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).getEmpresa().setNombre(null);
			} else {
				int index = defaultComboEmpresa.getIndexOf(defaultComboEmpresa.getSelectedItem());
				String empresaId = listaEmpresas.get(index - 1).getNombre();
				this.listaFacturas.get(filaSeleccionada).getEmpresa().setNombre(empresaId);
			}

			if (defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem()) == 0) {
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).getArea().setId(0);
			} else {
				int index = defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem());
				int areaId = listaAreas.get(index - 1).getId();
				this.listaFacturas.get(filaSeleccionada).getArea().setId(areaId);
			}

			if (this.defaultComboDestinatario.getIndexOf(defaultComboDestinatario.getSelectedItem()) == 0) {
				this.listaFacturas.get(filaSeleccionada).getDestinatario().setIdUsuario(null);
			} else {
				int index = defaultComboDestinatario.getIndexOf(defaultComboDestinatario.getSelectedItem());
				String usuarioDestId = listaDestinatarios.get(index - 1).getIdUsuario();
				this.listaFacturas.get(filaSeleccionada).getDestinatario().setIdUsuario(usuarioDestId);
			}

			if (this.defaultComboConcepto.getIndexOf(defaultComboConcepto.getSelectedItem()) == 0) {
				capturaCompleta = false;
				this.listaFacturas.get(filaSeleccionada).getConcepto().setDescripcion(null);
				this.listaFacturas.get(filaSeleccionada).getConcepto().setCuenta(null);
			} else {
				int index = this.defaultComboConcepto.getIndexOf(defaultComboConcepto.getSelectedItem());
				String cuenta = listaConceptos.get(index - 1).getCuenta();
				String descripcion = listaConceptos.get(index - 1).getDescripcion();
				this.listaFacturas.get(filaSeleccionada).getConcepto().setCuenta(cuenta);
				this.listaFacturas.get(filaSeleccionada).getConcepto().setDescripcion(descripcion);
			}
			
			if (this.tipoDocumento == 1) {

				this.listaFacturas.get(filaSeleccionada).setTipoDocumento(Constants.DOCUMENTO_TIPO_FACTURA);
				this.listaFacturas.get(filaSeleccionada).setSoporte(0);
				this.listaFacturas.get(filaSeleccionada).setNombreSoporte(null);

			} else if (this.tipoDocumento == 2) {
				this.listaFacturas.get(filaSeleccionada).setTipoDocumento(Constants.DOCUMENTO_TIPO_FACTURA48hrs);
				this.listaFacturas.get(filaSeleccionada).setSoporte(0);
				this.listaFacturas.get(filaSeleccionada).setNombreSoporte(null);

			} else if (this.tipoDocumento == 3) {
				this.listaFacturas.get(filaSeleccionada).setSoporte(1);
				this.listaFacturas.get(filaSeleccionada).setTipoDocumento(Constants.DOCUMENTO_TIPO_SOPORTE);
				this.listaFacturas.get(filaSeleccionada).setNombreSoporte(this.tipoDoc);
				capturaCompleta=true;
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
			
			//this.listaFacturas.get(filaSeleccionada).setPlaza(cmbPlaza.getSelectedItem().toString());
			this.listaFacturas.get(filaSeleccionada).setMercado(txtMercado.getText());
		}

		if (capturaCompleta) {
			status = Constants.STATUS_CAPTURA_COMPLETA;
		} else {
			status = Constants.STATUS_PENDIENTE_CAPTURA;
		}

		return this.procesoActualizarFactura(status, IdUsuario);
	}

	/**
	 * actualiza los datos de la factura dependiendo de los campos que se haya
	 * capturado
	 * 
	 * @param status
	 * @param idUsuario
	 */
	private boolean procesoActualizarFactura(int status, String idUsuario) {

		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		boolean procesoExitoso = false;

		if (this.establecerConexionDB2()) {

			try {
				int actualizo = DAO.actualizarFacturaDB2(this.listaFacturas.get(filaSeleccionada));
				
				if (actualizo != 0) {
					int actualizaEstado = DAO.actualizaStatus(this.listaFacturas.get(filaSeleccionada), status);
					if (actualizaEstado != 0) {
						this.listaFacturas.get(filaSeleccionada).getEstado().setId(status);

						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG025"), titulo, 1);
						procesoExitoso = true;

						int factPendientes = 0;
						factPendientes = this.countFacturasPendientesCapturar(idUsuario);
						if (factPendientes == 0) {
							this.ultimaCaptura = true;
						}

						this.showMessage(message);
						muestraFacturasPorLote();

					} else {
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG022"), titulo, 1);
						this.showMessage(message);
					}
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG022"), titulo, 1);
					this.showMessage(message);
				}
			} catch (SQLException e) {
				logger.info("Error al intentar actualizar La Factura  " + this.listaFacturas.get(filaSeleccionada).getId());
				if (this.validarConexionDB2()) {
					logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				} else {
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG007" + e), titulo, 1);
					this.showMessage(message);
					System.exit(0);
				}
			}
			this.cerrarConexionDB2();
		}
		return procesoExitoso;
	}

	/**
	 * Valida si hay seleccionado una Area y no esta seleccionado un
	 * departamento le muestre un msj al usuario que lo debe seleccionar
	 */
	public boolean procesoValidarCbxArea() {
		boolean valido = true;
		if (defaultComboArea.getIndexOf(defaultComboArea.getSelectedItem()) != 0) {

			if (defaultComboDestinatario.getIndexOf(defaultComboDestinatario.getSelectedItem()) == 0) {

				valido = false;
			}
		}
		return valido;
	}

	/**
	 * Muestra un mensaje JOptionPane.showMessageDialog
	 * 
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje) {
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}

	/**
	 * consulta facturas del lote y muestra sus datos en el
	 * defaultTableModelDatos
	 */
	public void muestraFacturasPorLote() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		this.txtUrl.setText("");

		if (this.establecerConexionDB2()) {
			try {
				this.listaFacturas = new ArrayList<Factura>();

				this.listaFacturas = DAO.getListaFacturasPorLote(this.lote, this.indiceInf, this.indiceSup, this.idUsuario);

				this.limpiaJTableDatos();

			//	String estado = null;

				if (listaFacturas.size() != 0) {
					for (int i = 0; i < listaFacturas.size(); i++) {
						Factura fact = listaFacturas.get(i);
						/*if (fact.getEstado().getId() == 1) {
							estado = Constants.STATUS_DES_EN_CAPTURA;
						} else if (fact.getEstado().getId() == 2) {
							estado = Constants.STATUS_DES_CAPTURA_COMPLETA;
						} else if (fact.getEstado().getId() == 3) {
							estado = Constants.STATUS_DES_EN_IMPORTACION_CM;
						} else if (fact.getEstado().getId() == 5) {
							estado = Constants.STATUS_DES_ERROR_IMPORTADO;
						} else if (fact.getEstado().getId() == 13) {
							estado = Constants.STATUS_DES_CANCELADO;
						}*/
						int id = indiceInf + i;
						this.defaultTableModelDatos.setValueAt(id, i, 0);
						this.defaultTableModelDatos.setValueAt(fact.getProveedor().getNumero(), i, 1);
						this.defaultTableModelDatos.setValueAt(fact.getNoFact(), i, 2);
						//this.defaultTableModelDatos.setValueAt(estado, i, 3); // Para que aparezca texto
						this.defaultTableModelDatos.setValueAt(fact.getEstado().getId(), i, 3); // Para que aparezca icono

					}
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
			this.habilitaTablaDatos(true);
			this.cerrarConexionDB2();
		}
	}

	/**
	 * 
	 * @return url actual de la factura seleccionada
	 */
	public String getUrl() {
		String url = this.listaFacturas.get(filaSeleccionada).getUrl();
		logger.info("Url del doc a mostrar en el visor: " + url);
		return url;
	}

	/**
	 * @return total de facturas capturadas 
	 */
	public int getNumRegistros() {
		return this.listaFacturas.size();
	}

	/**
	 * muestra los datos de las facturas capturadas cuando el usuario selecciona
	 * el tipo de doc. soporte
	 */
	public void muestraFacturasSoportePorLote(String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		listaFacturasSoporte = new ArrayList<Factura>();

		int id = this.listaFacturas.get(filaSeleccionada).getId();
		System.out.println("id fila seleccionada" + id);

		if (this.establecerConexionDB2()) {
			try {
				listaFacturasSoporte = DAO.getListaFacturasSoportePorLote(this.lote, id, idUsuario);
				
				int filas = this.defaultTableModelDatosSoporte.getRowCount();
				int regSoporte = this.listaFacturasSoporte.size();
				
				if(filas < regSoporte){
					this.defaultTableModelDatosSoporte.setRowCount(regSoporte);
				}
				
				if (listaFacturasSoporte.size() != 0) {
					for (int i = 0; i < listaFacturasSoporte.size(); i++) {
						Factura fact = listaFacturasSoporte.get(i);
						this.defaultTableModelDatosSoporte.setValueAt(fact.getNoFact(), i, 0);
						this.defaultTableModelDatosSoporte.setValueAt(fact.getProveedor().getNombre(), i, 1);
						this.defaultTableModelDatosSoporte.setValueAt(fact.getMonto(), i, 2);
					}
				}
			} catch (SQLException e) {
					logger.info("Error al mostrar al mostrar las facturas " + e);
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

	/**
	 * 
	 * @return true si el doc. seleccionado es de tipo Soporte
	 */
	public boolean isSoporte() {
		int sop = this.listaFacturas.get(filaSeleccionada).getSoporte();
		boolean isSoporte = false;
		if (sop == 1) {
			isSoporte = true;
		}

		return isSoporte;
	}

	public void seleccionaFacturaDelSoporte() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		int idFact = listaFacturas.get(filaSeleccionada).getId();
		String noFactura = listaFacturas.get(filaSeleccionada).getNoFact();
		String noProv = listaFacturas.get(filaSeleccionada).getProveedor().getNumero();

		if (this.establecerConexionDB2()) {

			try {
				String nombreSoporte = DAO.getNombreSoporte(idFact);
				this.txtNombreDoc.setText(nombreSoporte);
			} catch (SQLException e) {
				logger.error("Error al intentar traer el nombre del documento-Soporte", e);
			}

			for (int i = 0; i < listaFacturasSoporte.size(); i++) {
				Factura factura = listaFacturasSoporte.get(i);

				if (factura.getNoFact().equals(noFactura) && factura.getProveedor().getNumero().equals(noProv)) {
					jTableFacturaSoporte.changeSelection(i, 0, false, false);
				}
			}

			this.cerrarConexionDB2();
		}
	}

	public void limpiaJTableDatos() {
		int filas = defaultTableModelDatos.getRowCount();
		for (int i = 0; i < filas; i++) {
			this.defaultTableModelDatos.setValueAt("", i, 0);
			this.defaultTableModelDatos.setValueAt("", i, 1);
			this.defaultTableModelDatos.setValueAt("", i, 2);
			this.defaultTableModelDatos.setValueAt("", i, 3);
		}

	}

	public void limpiaJTableDatosFactSoporte() {
		int filas = defaultTableModelDatosSoporte.getRowCount();
		for (int i = 0; i < filas; i++) {
			this.defaultTableModelDatosSoporte.setValueAt("", i, 0);
			this.defaultTableModelDatosSoporte.setValueAt("", i, 1);
			this.defaultTableModelDatosSoporte.setValueAt("", i, 2);
		}

	}

	/**
	 * Meodo que valida el estado de la fila seleccionada
	 */

	public void consultarFilaSeleccionada() {
		int status = 0;
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		tipoDoc=null;
		logger.info(nombreMetodo);
		
		if (filaSeleccionada < this.listaFacturas.size()) {
			status = consultaEstadoFactura();
			logger.info("IDFACTURA seleccionada " + listaFacturas.get(filaSeleccionada).getId());
			
			if (status != 0 && status == Constants.STATUS_PENDIENTE_CAPTURA) {
				/* Pendientes de Capturar. */
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);
				this.procesoMuestraDatosCapturados();
				
				this.ponerFechaAnteriorEnRecepcion();
				this.ponerPlazaAnterior();
				
			} else if (status != 0 && status == Constants.STATUS_CAPTURA_COMPLETA) {
				/* Captura completa */
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);
				this.procesoMuestraDatosCapturados();
				//this.cmbPlaza.setSelectedItem(this.getPlaza());
				
			} else if (status != 0 && status == Constants.STATUS_IMPORTANDO_CM) {
				/*
				 * Captura completa
				 * Habilitar todas las opciones															
				 */															
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(false);
				this.habilitaBotonesFunciones(false);
				this.habilitaRdBtnsTipoDocumento(false);
				this.procesoMuestraDatosCapturados();
				
			} else if (status != 0 && status == Constants.STATUS_ERROR_IMPORTADO) {
				/*
				 * Captura completa
				 * Habilitar todas las opciones															
				 */		
				this.limpiarCamposCaptura();
				this.habilitaCampoCaptura(true);
				this.habilitaBotonesFunciones(true);
				this.habilitaRdBtnsTipoDocumento(true);
				this.procesoMuestraDatosCapturados();
				
			} else if (status != 0 && status == Constants.STATUS_CANCELADO) {
				this.limpiarCamposCaptura();
				this.txtUrl.setText(listaFacturas.get(filaSeleccionada).getUrl());
				this.habilitaCampoCaptura(false);
				this.habilitaBotonesFunciones(false);
				this.btnCargaMasiva.setEnabled(false);
				this.habilitaRdBtnsTipoDocumento(false);
			}
		} else {
			this.limpiarCamposCaptura();
			this.habilitaCampoCaptura(false);
			this.habilitaBotonesFunciones(false);
			this.habilitaRdBtnsTipoDocumento(false);
			plaza = null;
		}
	}

	/**
	 * pone la fecha de recepcion del anterior registro en al actual
	 */
	private void ponerFechaAnteriorEnRecepcion() {
		if (this.jDateChooserFechaRecepcion.getDate() == null) {
			this.jDateChooserFechaRecepcion.setDate(fechaRecepcion);
		}

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
	 * 
	 * @param date
	 */
	public void setFechaRecepcion(Date date) {
		this.fechaRecepcion = date;
	}

	/**
	 * limpia los campos de captura
	 */
	public void limpiarCamposCaptura() {
		this.txtMoneda.setText("");
		this.txtMonto.setText("");
		this.txtMercado.setText("");
		this.txtNoFact.setText("");
		this.txtNoProveedor.setText("");
		this.txtNoProveedor.setValue("");
		this.txtNombreProv.setText("");
		this.txtRfcProveedor.setText("");
		/*jComboModel = new ADComboBoxModel(new String[] { "" });
		this.txtSucursalProv.setModel(jComboModel);
		this.txtSucursalProv.setSelectedIndex(0);*/
		this.txtNoSAF.setText("");
		this.txtordenCompra.setText("");
		this.jDateChooserFechaFactura.setDate(null);
		this.jDateChooserFechaRecepcion.setDate(null);
		this.cbxArea.setSelectedIndex(0);
		this.cbxConcepto.setSelectedIndex(0);
		this.cbxEnviarFactura.setSelectedIndex(0);
		this.cbxRazonSocial.setSelectedIndex(0);
		this.txtUrl.setText("");
		this.txtNumTiendas.setText("");
		this.txtMontoMaximo.setText("");
		
		/**
		 * @author Adan
		 * Se limpia el combo de plaza
		 */
		this.cmbPlaza.setSelectedIndex(0);
	}

	/**
	 * toma el valor de plaza.nombre del archivo conexiones.properties y lo
	 * muestra en el txtPlaza
	 * 
	 * */

	public void setPlaza(String plazaDesktop) {
		this.plaza = plazaDesktop;

	}

	/**
	 * consulta los datos de la factura seleccionada y los muestra en los campos
	 * de captura
	 */
	private String tipoDoc=null;
	private void procesoMuestraDatosCapturados() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		
		if (this.establecerConexionDB2()) {
			try {
				Factura facturaNueva = new Factura();
				facturaNueva = DAO.getAllFactura(listaFacturas.get(filaSeleccionada));

				if (facturaNueva.getSoporte() == 0) {

					this.txtNoFact.setText(facturaNueva.getNoFact());

					String numero = facturaNueva.getProveedor().getNumero();
					if (numero != null) {
						numero = numero.replaceAll(" ", "");
					}
					
					this.txtNoProveedor.setValue(numero);
					this.txtNombreProv.setText(facturaNueva.getProveedor().getNombre());
					/*jComboModel = new ADComboBoxModel(new String[] { facturaNueva.getProveedor().getSucursal() });
					this.txtSucursalProv.setModel(jComboModel);
					this.txtSucursalProv.setSelectedIndex(0);*/
					this.txtRfcProveedor.setText(facturaNueva.getProveedor().getRfc());
					this.jDateChooserFechaRecepcion.setDate(facturaNueva.getFechaRecepcion());
					this.jDateChooserFechaFactura.setDate(facturaNueva.getFechaFactura());
					this.txtMonto.setText(facturaNueva.getMonto());
					this.txtMercado.setText(facturaNueva.getMercado());
						
					int indexArea = 0;
					
					if (facturaNueva.getArea().getId() != 0) {
						for (int i = 0; i <= this.listaAreas.size(); i++) {
							if (facturaNueva.getArea().getId() == this.listaAreas.get(i).getId()) {
								indexArea = (i + 1);
								break;
							}
						}
					}

					this.cbxArea.setSelectedIndex(indexArea);

					int indexDestinatario = 0;

					if (facturaNueva.getDestinatario().getIdUsuario() != null) {

						if (this.listaDestinatarios.size() > 0) {
							for (int i = 0; i <= this.listaDestinatarios.size(); i++) {

								if (facturaNueva.getDestinatario().getIdUsuario().equals(listaDestinatarios.get(i).getIdUsuario())) {
									indexDestinatario = (i + 1);
									break;
								}
							}
						}
					}

					this.cbxEnviarFactura.setSelectedIndex(indexDestinatario);

					int inedxConcepto = 0;
					if (facturaNueva.getConcepto().getDescripcion() != null) {

						for (int i = 0; i <= this.listaConceptos.size(); i++) {

							if (facturaNueva.getConcepto().getDescripcion().equals(listaConceptos.get(i).getDescripcion())) {
								inedxConcepto = (i + 1);
								break;
							}
						}

					}

					cbxConcepto.setSelectedIndex(inedxConcepto);

					int indexEmpresa = 0;
					if (facturaNueva.getEmpresa().getNombre() != null) {
						for (int i = 0; i <= this.listaEmpresas.size(); i++) {

							if (facturaNueva.getEmpresa().getNombre().equals(listaEmpresas.get(i).getNombre())) {
								indexEmpresa = (i + 1);
								break;
							}
						}
					}
					this.cbxRazonSocial.setSelectedIndex(indexEmpresa);

					this.txtordenCompra.setText(facturaNueva.getOrdenCompra());
					this.txtNoSAF.setText(facturaNueva.getNoSAF());
					
					if(facturaNueva.getNumTiendas()==0){
						this.txtNumTiendas.setText("");
					}else{
						this.txtNumTiendas.setText(String.valueOf(facturaNueva.getNumTiendas()));
					}
					
					if(facturaNueva.getMontoMaximo().equals("0.0000")){
						this.txtMontoMaximo.setText("");
					}else{
						this.txtMontoMaximo.setText(facturaNueva.getMontoMaximo());
					}
				

					if (facturaNueva.getModeda() != null) {
						this.txtMoneda.setText(facturaNueva.getModeda());
					} else {
						this.txtMoneda.setText("MXN");
					}

					if (facturaNueva.getTipoDoc() != null) {
						if (facturaNueva.getTipoDoc().equals(Constants.DOCUMENTO_TIPO_FACTURA)) {
							this.rdbtnTipoFactura.setSelected(true);
							this.setTipoDocumento(1);
						} else if (facturaNueva.getTipoDoc().equals(Constants.DOCUMENTO_TIPO_FACTURA48hrs)) {
							this.setTipoDocumento(2);
							this.rdbtnFactura48hrs.setSelected(true);
						}
					} else {
						this.rdbtnTipoFactura.setSelected(true);
					}

					this.txtUrl.setText(facturaNueva.getUrl());
					
					if(facturaNueva.getPlaza() != null && !facturaNueva.getPlaza().isEmpty()){
						this.cmbPlaza.setSelectedItem(facturaNueva.getPlaza());
					}else{
						this.cmbPlaza.setSelectedIndex(0);
					}
					
				} else if (facturaNueva.getSoporte() == 1) {
					this.setTipoDocumento(3);
					this.txtNoFact.setText(facturaNueva.getNoFact());
					String numero = facturaNueva.getProveedor().getNumero();
					if (numero != null) {
						numero = numero.replaceAll(" ", "");
					}
					this.txtNoProveedor.setValue(numero);
					this.txtNombreProv.setText(facturaNueva.getProveedor().getNombre());
					/*jComboModel = new ADComboBoxModel(new String[] { facturaNueva.getProveedor().getSucursal() });
					this.txtSucursalProv.setModel(jComboModel);
					this.txtSucursalProv.setSelectedIndex(0);*/
					this.txtRfcProveedor.setText(facturaNueva.getProveedor().getRfc());
					this.habilitaCampoCaptura(false);
					this.habilitaBotonesFunciones(true);
					this.rdbtnTipoSoporte.setSelected(true);
					this.cbxEnviarFactura.setSelectedIndex(0);
					this.txtUrl.setText(facturaNueva.getUrl());
					this.btnCargaMasiva.setEnabled(false);
					this.tipoDoc=facturaNueva.getNombreSoporte();
					
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

			} catch (SQLException e) {
				logger.info("Error al traer los datos Capturados " + e.getMessage());
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

	/**
	 * consulta el status actual de la fila seleccionada
	 * 
	 * @return
	 */
	public int consultaEstadoFactura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		int status = 0;

		int idFactura = listaFacturas.get(filaSeleccionada).getId();
		if (this.establecerConexionDB2()) {
			try {
				status = DAO.getStatusFactura(idFactura);
				if (status != 0) {

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

		return status;
	}

	/**
	 * regresa en index de la fila seleccionada
	 * 
	 * @return
	 */
	public int getFilaSeleccionada() {
		filaSeleccionada = this.jTableDatosFact.getSelectedRow();
		return filaSeleccionada;
	}

	/**
	 * 
	 * @param fila
	 */
	public void setFilaSeleccioanda(int fila) {
		this.filaSeleccionada = fila;
	}

	/**
	 * aumenta si se hizo alguna funncion en un registros cambia a 0 si se
	 * cambia de pagina
	 * 
	 * @param param
	 */
	public void aumentarFila() {
		this.filaSeleccionada++;
		this.seleccionaFila(this.filaSeleccionada);
	}

	/**
	 * regressa el indes de la fila seleccionada
	 * 
	 * @return
	 */
	public int getFilaSeleccionadaFactSoporte() {
		int fila = this.jTableFacturaSoporte.getSelectedRow();
		return fila;
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
						// this.pagina++
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
	 * Inserta el lote y el Id_usuario 
	 * en la la base de datos tabla 
	 * reporte Carga
	 * @throws SQLException 
	 */
	private void insertarLoteReporteCarga(String lote) throws SQLException {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		
		/*verificar si el lote ya existe en la tabla Reporte-Carga*/
		int numReport=DAO.ConsultaExisteLoteReporteCarga(idUsuario,lote,Constants.REPORTECARGA_FACTURA_GASTO); 
		if(numReport==0){
			
			String fechaCaptura=Utilities.nowDate();
			ReporteCarga report= new ReporteCarga();
			report.setIdUsuario(this.idUsuario);
			report.setLote(lote);
			report.setFechaCaptura(fechaCaptura);
			report.setTipoCaptura(Constants.REPORTECARGA_FACTURA_GASTO);
			report.setDocumentosProcesados("0");
		
			if(DAO.insertarLoteReporteCarga(report)){
				logger.info("Se ha insertado el Lote" +lote+" Usuario "+idUsuario+" En ReportesCarga");
			}else{
				logger.info(" el Lote " +lote+" ,  Usuario "+idUsuario+" y Tipo Captura "+Constants.REPORTECARGA_FACTURA_GASTO+" Ya existen en reporte Carga");
			}	
			
		}else{
			logger.info(" el Lote " +lote+" y  Usuario "+idUsuario+" Ya existen en reporte Carga");
		}
		
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

	/**
	 * consulta datos del proveedor en base al numero de proveedor que el
	 * usuario capturo
	 */
	public void procesoActualizaDatosProveedor() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		if (this.txtNoProveedor.getText().isEmpty()) {
			this.txtNombreProv.setText("");
			this.txtRfcProveedor.setText("");
			/*jComboModel = new ADComboBoxModel(new String[] { "" });
			this.txtSucursalProv.setModel(jComboModel);
			this.txtSucursalProv.setSelectedIndex(0);*/

		} else {
			if (this.establecerConexionDB2()) {
				String numero = txtNoProveedor.getText();
				try {
					ArrayList<Proveedor> proveedorArray = DAO.consultaDatosProveedor(numero);
					if (proveedorArray.size() == 0) {
						this.txtNombreProv.setText("");
						this.txtRfcProveedor.setText("");
						/*jComboModel = new ADComboBoxModel(new String[] { "" });
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);*/
						this.txtNoProveedor.setValue(numero);
					} else {
						//String[] proveedoresLista = new String[proveedorArray.size()];
						for (Proveedor pov : proveedorArray) {
							if (pov.getNombre() == null) {
								this.txtNombreProv.setText("");
								this.txtRfcProveedor.setText("");
								// this.txtNoProveedor.setText("");
							} else {
								this.txtNombreProv.setText(pov.getNombre());
								this.txtRfcProveedor.setText(pov.getRfc());
								//proveedoresLista[proveedorArray.indexOf(pov)] = pov.getSucursal();
								//this.txtNoProveedor.setValue(pov.getId_vendor());
							}
						}
						/*jComboModel = new ADComboBoxModel(proveedoresLista);
						this.txtSucursalProv.setModel(jComboModel);
						this.txtSucursalProv.setSelectedIndex(0);*/
						this.txtNoProveedor.setValue(numero);
					}
				} catch (SQLException e) {
					logger.info("Error al intentar traer los datos del Proveedor" + e);
					message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
					this.showMessage(message);
				}
				this.cerrarConexionDB2();

			}

		}
	}

	public void limpiaComponentesJDialogSoporte() {
		this.limpiaJTableDatosFactSoporte();
		jDialogDocumentoSoporte = null;
		txtNombreDoc.setText("");
		jTableFacturaSoporte.clearSelection();
	}

	public int getSizeJTablaFactSoporte() {
		return this.listaFacturasSoporte.size();
	}

	/**
	 * actualiza los datos de la factura-soporte seleccionada con numero de
	 * factura,proveedor. de la factura seleccionada
	 */
	public boolean procesoAsignarDocumentoAFactura() {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		boolean procesoExitoso = false;
		if (this.establecerConexionDB2()) {
			int index = this.getFilaSeleccionadaFactSoporte();

			String noFact = this.listaFacturasSoporte.get(index).getNoFact();

			String nomProv = this.listaFacturasSoporte.get(index).getProveedor().getNumero();

			this.listaFacturas.get(filaSeleccionada).setNoFact(noFact);
			this.listaFacturas.get(filaSeleccionada).getProveedor().setNumero(nomProv);
			this.listaFacturas.get(filaSeleccionada).setSoporte(1);
			this.listaFacturas.get(filaSeleccionada).setNombreSoporte(this.txtNombreDoc.getText());

			String tipoFactura = Constants.DOCUMENTO_TIPO_SOPORTE;

			int Soporte = 1;

			String nombreDoc = this.txtNombreDoc.getText();
			int idFact = this.listaFacturas.get(filaSeleccionada).getId();

			// this.listaFacturas.get(filaSeleccionada).setDestinatario(destinatario);
			int actualizo = 0;
			try {
				actualizo = DAO.signarDocumentoAFactura(noFact, nomProv, Soporte, nombreDoc, idFact, tipoFactura);
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
			if (actualizo != 0) {

				procesoExitoso = true;
				int status = Constants.STATUS_CAPTURA_COMPLETA;
				try {

					int actualizoStatus = DAO.actualizaStatusSoporte(idFact, status);
					if (actualizoStatus != 0) {
						// actualizar datos paginador
						this.muestraFacturasPorLote();
						this.consultarFilaSeleccionada();
					}
					
					int factPendientes = 0;
					factPendientes = this.countFacturasPendientesCapturar(idUsuario);
					if (factPendientes == 0) {

						this.ultimaCaptura = true;
					}

				} catch (SQLException e) {
					logger.info("Error al intentar asignarle el documento a la Factura seleccionada" + e.getMessage());
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

			this.cerrarConexionDB2();
		}

		return procesoExitoso;

	}

	public JTextField getTxtNombreDoc() {
		if (txtNombreDoc == null) {
			txtNombreDoc = new JTextField();
		}
		return txtNombreDoc;
	}

	/**
	 * cambia el radioButton de soporte a Factura
	 */
	public void setTipoFactura() {
		this.rdbtnTipoFactura.setSelected(true);
	}

	/**
	 * regresa true si el txtNombreDocumento esta vacio
	 * 
	 * @return
	 */

	public boolean nombreDocEmpty() {
		String nombreDoc = this.txtNombreDoc.getText();
		if (!nombreDoc.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * cambia el status de las facturas que estan listas para enviarce a CM
	 * 
	 * @param idUsuario
	 * @return
	 */
	public int procesoActualizarStatusEnvindoaCM(String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);
		int numFacXlote = 0;
		if (this.establecerConexionDB2()) {

			try {
				numFacXlote = DAO.consultaAllFacturasListasCM(this.lote, idUsuario);
			} catch (SQLException e) {
				logger.info("Error al intentar traer las facturas para cmabiar su status a " + Constants.STATUS_IMPORTANDO_CM);
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
			System.out.println("numero d facturas capturadas" + numFacXlote);
			if (numFacXlote > 0) {

				try {
					listaFacturasCapturadas = DAO.getFacturasLoteUsuario(this.lote, idUsuario);

					for (int i = 0; i < listaFacturasCapturadas.size(); i++) {
						Factura facturaCap = listaFacturasCapturadas.get(i);
						int status = Constants.STATUS_IMPORTANDO_CM;
						int actualizo = DAO.actualizaStatus(facturaCap, status);
						if (actualizo > 0) {
							logger.info("Se actualizaron las facturas con el status: " + Constants.STATUS_IMPORTANDO_CM);
						}
					}
					
				} catch (SQLException e) {
					logger.info("Error al intentar traer las facturas para caMbiar su status a " + Constants.STATUS_IMPORTANDO_CM);
					if (this.validarConexionDB2()) {
						logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), e);

						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "ERR001"), titulo, 1);
						this.showMessage(message);
					} else {
						message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG007" + e), titulo, 1);
						this.showMessage(message);
						System.exit(0);
					}

				}

			}

			this.cerrarConexionDB2();
			this.muestraFacturasPorLote();
			// actualizar paginador

		}

		return numFacXlote;
	}

	public int getActivaCargaMasiva() {
		return activaCargaMasiva;
	}

	public void setActivaCargaMasiva(int activaCargaMasiva) {
		this.activaCargaMasiva = activaCargaMasiva;
	}

	public boolean getUltimaCaptura() {
		return ultimaCaptura;
	}

	public void setUltimaFilaCaptura(boolean ultimaFilaCaptura) {
		this.ultimaCaptura = ultimaFilaCaptura;
	}
	
	public boolean isFacturaValida(Factura factura)
	{
		boolean isTrue = false;
		if (this.establecerConexionDB2()) {
			try {
				/**
				 * @author Eliudc
				 * Se extrae info de numero de factura y numero de proveedor.
				 * Control de cambio 011
				 */
				factura.setNoFact(this.txtNoFact.getText());
				factura.getProveedor().setNumero(this.txtNoProveedor.getText());
				
				logger.info(CapturaGastosDAOQuery.SELECT_FACTURA_GASTOS + " " + factura.getId() + " " + factura.getNoFact()+ " "+ factura.getProveedor().getNumero() + " tipo doc : " +this.tipoDocumento);
				
				if(this.tipoDocumento != 3)
					isTrue = DAO.selectFacturaByNumFact(factura);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.cerrarConexionDB2();
		
		return isTrue;
	}
	

	/**
	 * consulta si hay factuas pendiente s de captura cuando el usuario
	 * seleccioan salir d ela pantalla de captura
	 */
	public int countFacturasPendientesCapturar(String idUsuario) {
		String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(nombreMetodo);

		int factPendientes = 0;
		if(this.establecerConexionDB2()){
			try {
				factPendientes = DAO.consultaFacturasPendientesCaptura(this.lote, idUsuario);
			} catch (SQLException e) {
				logger.error("Error al intentar consultar si hay Facturas pendientes de capturar ",e);
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
		return factPendientes;
	}

	/**
	 * valida el tipo de Moneda
	 * 
	 * @return
	 */
	public boolean isValidoMoneda() {
		boolean valida = false;
		if (this.txtMoneda.getText().isEmpty()) {
			valida = true;
		} else {
			if (this.esMonedaValida()) {
				valida = true;
			} else {
				this.txtMoneda.setText("");
			}
		}

		return valida;
	}

	/**
	 * valida el campo Monto
	 * 
	 * @return true si es valido
	 */
	public boolean isMontoValido() {
		boolean valido = false;

		String cadenaInvalida = "$,";
		String monto = this.txtMonto.getText();
		String montoMaximo = this.txtMontoMaximo.getText();
		String montoSinCarac = Utilities.EliminaCaracteres(monto, cadenaInvalida);
		String montoSinCarac2 = Utilities.EliminaCaracteres(montoMaximo, cadenaInvalida);

		if ((montoSinCarac.matches("[0-9]+.[0-9]*") || montoSinCarac.matches("[0-9]") || monto.isEmpty())
			&& (montoSinCarac2.matches("[0-9]+.[0-9]*") || montoSinCarac2.matches("[0-9]") || montoSinCarac2.isEmpty()) ) {
			valido = true;
		}
		
		return valido;
	}

	/**
	 * valida Numero de proveedor
	 * 
	 * @return
	 */
	public boolean getNoProveedor() {
		String neNumber = txtNoProveedor.getText();
		boolean valido = false;
		if (neNumber.matches("[0-9]*")) {
			valido = true;
		}
		return valido;
	}
	
	

	/**
	 * valida Numero Tiendas
	 * 
	 * @return
	 */
	public boolean isNumTiendaValido() {
		String neNumber = txtNumTiendas.getText();
		boolean valido = false;
		if (neNumber.matches("[0-9]*") || neNumber.isEmpty()) {
			valido = true;
		}
		return valido;
	}

	

	public boolean getActivarprimerRegistro() {
		return activarprimerRegistro;
	}

	public void setActivarprimerRegistro(boolean activarprimerRefistro) {
		this.activarprimerRegistro = activarprimerRefistro;
	}

	public JTextField getJTextFieldMercado() {
		if (txtMercado == null) {
			txtMercado = new JTextField();

		}
		return txtMercado;
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

}// cierre clase Principal

