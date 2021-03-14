package com.adinfi.sevCaptura.view;



import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import com.adinfi.sevCaptura.controller.CargaMasivaMercanciaListener;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.model.CargaMasivaMercanciaModel;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;

public class CargaMasivaMercancia extends JInternalFrame {
	public CargaMasivaMercancia() {
	}

	private JTextField txtDocsLote=null;
	private JTextField txtDocsAProcesar=null;
	private JTextField txtDocsImportados=null;
	private JTextField txtDocsNoImportados=null;
	private JButton btnAceptarCM=null;
	private JProgressBar progressBar;
	private CargaMasivaMercanciaModel model;
	private CargaMasivaMercanciaListener listener;
	private JPanel jPanelPrincipal;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargaMasivaVista frame = new CargaMasivaVista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public CargaMasivaMercancia(CargaMasivaMercanciaModel model) {
		this.model=model;
		this.initialize();
		
	}
	private void initialize(){
		this.setBounds(500, 250, 420, 218);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.setClosable(true);
		this.setResizable(false);
		this.setMaximizable(false);
		System.out.println(this.getSize().width);
		System.out.println(this.getSize().height);
		this.setContentPane(this.getJPanelPrincipal());
		this.setVisible(true);
	}
	
	public void addListenerCargaMasiva(
			CargaMasivaMercanciaListener listener) {
		this.listener=listener;
		this.btnAceptarCM.addActionListener(this.listener);

	}
	
	private JPanel getJPanelPrincipal() {
		if(jPanelPrincipal == null){
			jPanelPrincipal = new JPanel();
			jPanelPrincipal.setLayout(null);
			JLabel lblNewLabel = new JLabel("Total De Documentos en Lote:");
			//lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 11, 174, 14);
			
			
			JLabel lblTotalDeDocumentos = new JLabel("Total de Documentos a Procesar:");
			//lblTotalDeDocumentos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos.setBounds(10, 36, 191, 14);
			
			
			JLabel lblTotalDeDocumentos_1 = new JLabel("Total de Documentos Importados:");
			//lblTotalDeDocumentos_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos_1.setBounds(10, 61, 191, 14);
		
			
			JLabel lblTotalDeDocumentos_2 = new JLabel("Total de Documentos no Importados:");
			//lblTotalDeDocumentos_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			lblTotalDeDocumentos_2.setBounds(10, 86, 206, 14);
			
			jPanelPrincipal.add(lblNewLabel);
			jPanelPrincipal.add(lblTotalDeDocumentos);
			jPanelPrincipal.add(lblTotalDeDocumentos_1);
			jPanelPrincipal.add(lblTotalDeDocumentos_2);
			jPanelPrincipal.add(getTxtDocsLote());
			jPanelPrincipal.add(getTxtDocsAProcesar());
			jPanelPrincipal.add(getTxtDocsAImportados());
			jPanelPrincipal.add(getTxtDocsNoImportados());
			jPanelPrincipal.add(getProgressBar());
			jPanelPrincipal.add(getBtnAceptarStatusCM());
			
		}
		return jPanelPrincipal;
	}

	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			//progressBar = new JProgressBar(0,100);
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
			btnAceptarCM.setEnabled(false);
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
			txtDocsNoImportados.setBounds(215, 83, 179, 20);
			txtDocsNoImportados.setColumns(10);
			txtDocsNoImportados.setEnabled(false);
			
		}
		return txtDocsNoImportados;
	}
	
	public JTextField getTxtDocsAImportados(){
		if(txtDocsImportados==null){
			txtDocsImportados = new JTextField();
			txtDocsImportados=model.getTxtDocsAImportados();
			txtDocsImportados.setBounds(215, 58, 179, 20);
			txtDocsImportados.setColumns(10);
			txtDocsImportados.setEnabled(false);
		}
		return txtDocsImportados;
	}
	
	
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this, mensaje);
	}
	
	public int  showMessageConfirmacion(Mensaje mensaje){
		int resultado= Utilities.showMensajeConfirmacion(this, mensaje);
		return resultado;
	}

}
