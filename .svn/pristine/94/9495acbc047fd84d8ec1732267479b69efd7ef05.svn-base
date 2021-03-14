package com.adinfi.sevCaptura.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class VistaTest extends JFrame {

	private JPanel contentPane;
	private JDialog jDialogDocumentoSoporte=null;
	private JProgressBar progressBar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTest frame = new VistaTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 420, 218);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Total De Documentos en Lote:");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 174, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTotalDeDocumentos = new JLabel("Total de Documentos a Procesar:");
		lblTotalDeDocumentos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblTotalDeDocumentos.setBounds(10, 36, 191, 14);
		contentPane.add(lblTotalDeDocumentos);
		
		JLabel lblTotalDeDocumentos_1 = new JLabel("Total de Documentos Importados:");
		lblTotalDeDocumentos_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblTotalDeDocumentos_1.setBounds(10, 61, 191, 14);
		contentPane.add(lblTotalDeDocumentos_1);
		
		JLabel lblTotalDeDocumentos_2 = new JLabel("Total de Documentos no Importados:");
		lblTotalDeDocumentos_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblTotalDeDocumentos_2.setBounds(10, 86, 206, 14);
		contentPane.add(lblTotalDeDocumentos_2);
		
		contentPane.add(getProgressBar());
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(305, 146, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(215, 8, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(215, 33, 179, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(215, 58, 179, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(215, 83, 179, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
	
	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar(0,100);
			progressBar.setBounds(10, 121, 384, 23);
			//progressBar.setPreferredSize(new Dimension(300, 20));
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}
}
