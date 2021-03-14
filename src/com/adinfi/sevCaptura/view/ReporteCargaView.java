package com.adinfi.sevCaptura.view;


import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.adinfi.sevCaptura.controller.ReporteCargaListener;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.model.ReporteCargaModel;
import com.adinfi.sevCaptura.resources.Utilities;
import com.toedter.calendar.JDateChooser;

public class ReporteCargaView extends JInternalFrame {

	private ReporteCargaModel model = null;
	private ReporteCargaListener listener = null;

	private JTextField textFieldLote = null;
	private JComboBox comboTipoCaptura = null;
	private JButton btnExcel = null;
	private JButton btnBuscar = null;
	private JScrollPane scrollpaneReporteCarga = null;
	private JTable tablaReporteCarga = null;
	private JButton btnLimpiar = null;
	private JDateChooser dateChooserInicioCaptura;
	private JDateChooser dateChooserFechaFinCaptura;

	/**
	 * Create the frame.
	 */
	public ReporteCargaView(ReporteCargaModel model) {
		this.model = model;
		this.initialize();

	}

	private void initialize() {
		this.setBounds(160, 15, 942, 447);
		this.setClosable(true);
		this.setResizable(false);
		this.setMaximizable(false);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Reporte de Cargas Masivas");
		this.setVisible(true);
		this.setContentPane(this.getContentPaneReporteCarga());
	}

	private JPanel getContentPaneReporteCarga() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);

		JLabel lblTipoCaptura = new JLabel("Tipo de captura");
		lblTipoCaptura.setBounds(2, 5, 107, 14);
		contentPane.add(lblTipoCaptura);

		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(2, 44, 46, 14);
		contentPane.add(lblLote);

		JLabel lblExportarExcel = new JLabel("Exportar a XLS");
		lblExportarExcel.setBounds(2, 207, 99, 14);
		contentPane.add(lblExportarExcel);

		JLabel lblFechaInicioCaptura = new JLabel("Fecha Inicio de captura");
		lblFechaInicioCaptura.setBounds(2, 83, 152, 14);
		contentPane.add(lblFechaInicioCaptura);

		JLabel lblFechaFinalCaptura = new JLabel("Fecha de Final de captura");
		lblFechaFinalCaptura.setBounds(2, 122, 166, 14);
		contentPane.add(lblFechaFinalCaptura);

		contentPane.add(lblLote);
		contentPane.add(lblExportarExcel);
		contentPane.add(lblFechaInicioCaptura);
		contentPane.add(lblFechaFinalCaptura);
		contentPane.add(getJTextFieldLote());
		contentPane.add(getComboTipoCaptura());
		contentPane.add(getBtnExcel());
		contentPane.add(getBtnBuscar());
		contentPane.add(getBtnLimpiar());
		contentPane.add(getScrollpaneReporteCarga());
		contentPane.add(getJDateChooserInicioCaptura());
		contentPane.add(getJDateChooserFechaFinCaptura());

		return contentPane;
	}

	private JTextField getJTextFieldLote() {
		if (textFieldLote == null) {
			//textFieldLote  = new JTextField();
			textFieldLote = model.getJTextFieldLote();
			textFieldLote.setBounds(2, 58, 169, 20);
			textFieldLote.setText("");
			textFieldLote.setColumns(10);
		}
		return textFieldLote;
	}

	public JComboBox getComboTipoCaptura() {
		if (comboTipoCaptura == null) {
			//comboTipoCaptura = new JComboBox();
			comboTipoCaptura = model.getComboTipoCaptura();
			comboTipoCaptura.setBounds(2, 19, 169, 20);

		}

		return comboTipoCaptura;
	}

	public JButton getBtnExcel() {
		if (btnExcel == null) {
			//btnExcel =new JButton("");
			btnExcel = model.getBtnExcel();
			btnExcel.setBounds(2, 221, 80, 23);
			btnExcel.setActionCommand("Excel");
			btnExcel.setEnabled(false);
		}
		return btnExcel;
	}

	public JButton getBtnBuscar() {
		if (btnBuscar == null) {
			//btnBuscar= new JButton();
			btnBuscar = model.getBtnBuscar();
			btnBuscar.setBounds(2, 161, 80, 23);
			btnBuscar.setActionCommand("Buscar");
		}
		return btnBuscar;
	}

	public JButton getBtnLimpiar() {
		if (btnLimpiar == null) {
			//btnLimpiar = new JButton();
			btnLimpiar = model.getBtnLimpiar();
			btnLimpiar.setBounds(91, 161, 80, 23);
			btnLimpiar.setActionCommand("Limpiar");

		}
		return btnLimpiar;
	}

	private JScrollPane getScrollpaneReporteCarga() {
		scrollpaneReporteCarga = new JScrollPane();
		scrollpaneReporteCarga.setBounds(180, 5, 750, 406);
		scrollpaneReporteCarga.setViewportView(getJTableDatos());
		return scrollpaneReporteCarga;
	}

	private JTable getJTableDatos() {
		if (tablaReporteCarga == null) {
			tablaReporteCarga = model.getJTableDatos();
		}
		return tablaReporteCarga;
	}

	public void addReporteCargaListener(ReporteCargaListener listener) {
		this.listener = listener;
		this.btnBuscar.addActionListener(this.listener);
		this.btnLimpiar.addActionListener(listener);
		this.btnExcel.addActionListener(listener);
		this.addInternalFrameListener(listener);
	}

	private JDateChooser getJDateChooserInicioCaptura() {
		if (dateChooserInicioCaptura == null) {
			//dateChooserInicioCaptura=new JDateChooser();
			dateChooserInicioCaptura = model.getJDateChooserInicioCaptura();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			dateChooserInicioCaptura.setCalendar(gregorianCalendar);
			dateChooserInicioCaptura.setDateFormatString("yyyy-MM-dd");
			dateChooserInicioCaptura.setBounds(new Rectangle(2, 97, 169, 20));
			dateChooserInicioCaptura
					.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							Calendar calendar = Calendar.getInstance();
						}
					});
			dateChooserInicioCaptura.setInheritsPopupMenu(true);
			dateChooserInicioCaptura.setEnabled(true);

		}
		return dateChooserInicioCaptura;
	}

	private JDateChooser getJDateChooserFechaFinCaptura() {
		if (dateChooserFechaFinCaptura == null) {
			//dateChooserFechaFinCaptura =new JDateChooser();
			dateChooserFechaFinCaptura = model.getJDateChooserFechaFinCaptura();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			dateChooserFechaFinCaptura.setCalendar(gregorianCalendar);
			dateChooserFechaFinCaptura.setDateFormatString("yyyy-MM-dd");
			dateChooserFechaFinCaptura
					.setBounds(new Rectangle(2, 136, 169, 20));
			dateChooserFechaFinCaptura
					.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							Calendar calendar = Calendar.getInstance();
						}
					});
			dateChooserFechaFinCaptura.setInheritsPopupMenu(true);
			dateChooserFechaFinCaptura.setEnabled(true);

		}
		return dateChooserFechaFinCaptura;
	}

	public void showMessage(Mensaje mensaje) {
		Utilities.showMensaje(this, mensaje);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
