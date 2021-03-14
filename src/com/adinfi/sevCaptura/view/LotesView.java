package com.adinfi.sevCaptura.view;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.adinfi.sevCaptura.controller.LotesListner;
import com.adinfi.sevCaptura.model.LotesModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LotesView extends JInternalFrame {
	private LotesModel model = null;
	private LotesListner listener = null;
	private JTextField textFieldLote = null;
	private JTextField textFieldDocsPendientes = null;
	private JButton btnAbrirLote = null;
	private JTable jTableLotes = null;
	
	public LotesView(LotesModel model) {
		this.model=model;
		this.initialize();
	}
		
	public void addLotesListener(LotesListner listener){
		this.listener=listener;
		this.btnAbrirLote.addActionListener(this.listener);
		jTableLotes.addMouseListener(listener);
		this.addInternalFrameListener(listener);
	}
	
	private void initialize() {
		this.setBounds(100, 100, 476, 288);
		this.setClosable(true);
		this.setResizable(false);
		this.setMaximizable(false);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Facturas  Gastos");
		this.setIconifiable(true);
		this.setVisible(true);
		this.setContentPane(this.getContenPaneLotes());
	}

	private JPanel getContenPaneLotes() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.add(this.getScrollPaneLotes());
		
		JLabel lblLote = new JLabel("Lote:");		
		lblLote.setBounds(160, 5, 46, 22);
		String texto = "<html>Documentos pendientes de <P>" +
				 "<html>enviar al flujo de autorización:<P>";
		JLabel lblDocPendientes = new JLabel(texto);
		lblDocPendientes.setBounds(160, 39, 191, 30);
		
		contentPane.add(lblLote);
		contentPane.add(lblDocPendientes);
		contentPane.add(getTxtFieldLote());
		contentPane.add(getTxtFieldDocsPendientes());
		contentPane.add(getJButtonAbrirLote());
		
		return contentPane;
	}

	private JTextField getTxtFieldLote(){
		if(textFieldLote == null){
			//textFieldLote = new JTextField();
			textFieldLote = model.getTxtFieldLote();			
			textFieldLote.setBounds(353, 5, 107, 22);
			textFieldLote.setColumns(10);
			textFieldLote.setEditable(false);
		}
		return textFieldLote;
	}
	
	private JTextField getTxtFieldDocsPendientes(){
		if(textFieldDocsPendientes == null){
			//textFieldDocsPendientes = new JTextField();
			textFieldDocsPendientes = model.getTxtFieldDocsPendientes();			
			textFieldDocsPendientes.setColumns(10);
			textFieldDocsPendientes.setBounds(353, 47, 107, 22);
			textFieldDocsPendientes.setEditable(false);
		}
		return textFieldDocsPendientes;
	}
	
	private JButton getJButtonAbrirLote(){
		if(btnAbrirLote == null){
			btnAbrirLote = new JButton("Abrir Lote");			
			btnAbrirLote.setBounds(353, 94, 107, 22);
			btnAbrirLote.setActionCommand("AbrirLote");
		}
		return btnAbrirLote;
	}
	
	private JScrollPane getScrollPaneLotes() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 150, 250);
		scrollPane.setViewportView(getJTableDatos());
		return scrollPane;
	}
	
	private JTable getJTableDatos() {
		if (jTableLotes == null) {
			jTableLotes = model.getJTableDatos(); 
			jTableLotes.setRowHeight(20);
		}
		return jTableLotes;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

