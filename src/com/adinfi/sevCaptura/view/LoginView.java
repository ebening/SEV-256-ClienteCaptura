package com.adinfi.sevCaptura.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import com.adinfi.sevCaptura.controller.LoginListener;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.model.LoginModel;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;
import javax.swing.border.LineBorder;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame {

	//private JPanel contentPane;
	//private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	public JPanel jpnllImag;//imagen
	public JPanel jpnlCampos;//campos
	public JPanel jpnlBtn;//btn
	private JLabel jLabUser = null;
	private JLabel jLabPass = null;
	private JTextField jTxtUser = null;
	private JPasswordField jPass = null;
	private JButton jBtnEntrar = null;
	private JButton jBtnCancelar = null;
	private LoginModel model;
	private LoginListener listener=null;  
	private JLabel labelLogo=null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginView(LoginModel model) {
		super();
		//setDefaultLookAndFeelDecorated(true);
		this.model=model;		
		initialize();
	}
	
	public LoginView() {
		super();
		this.model= new LoginModel();		
		initialize();
	}
	
	private void initialize() {
		this.setSize(692, 216);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(500, 400, 400, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Sistema Captura de Facturas");
		this.setResizable(false);		
		this.setLocationRelativeTo(null);
	}
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jLabUser = new JLabel();
			jLabUser.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			jLabUser.setBounds(431, 32, 108, 19);
			jLabUser.setText("Usuario");
			jLabPass = new JLabel();
			jLabPass.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
			jLabPass.setBounds(548, 32, 97, 19);
			jLabPass.setText("Contraseña");
			jContentPane.setLayout(null);
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(Color.ORANGE));
			panel.setBounds(10, 11, 666, 166);
			jContentPane.add(panel);
			labelLogo=new JLabel();
			labelLogo.setBounds(new Rectangle(2, 32, 423, 106));
			//labelLogo.setIcon(Constants.LOGON);
			labelLogo.setIcon(new ImageIcon(getClass().getResource(Constants.LOGON)));
			//labelLogo.setIcon(new ImageIcon(getClass().getResource("/com/adinfi/sevCaptura/resources/images/Logon.png")));
			
			panel.setLayout(null);
			panel.add(labelLogo);
			panel.add(jLabUser);
			panel.add(jLabPass);
			panel.add(getJTextFieldUser());
			panel.add(getJPasswordField());
			panel.add(getJButtonEntrar());
			panel.add(getJButtonCancelar());
	       
	        
		}
		return jContentPane;
	}
	
	private JTextField getJTextFieldUser() {
		if (jTxtUser == null) {
			//jTxtUser = new JTextField();
			jTxtUser = model.getJTextFieldUser();
			jTxtUser.setBounds(431, 48, 107, 25);				
		}
		return jTxtUser;
	}
	
	private JPasswordField getJPasswordField() {
		if (jPass == null) {
			jPass =model.getJPasswordField();
			//jPass=new JPasswordField();
			jPass.setBounds(549, 48, 107, 25);			
		}
		return jPass;
	}
	private JButton getJButtonEntrar() {
		if (jBtnEntrar == null) {
			//jBtnEntrar = new JButton();
			jBtnEntrar=model.getJButtonEntrar();
			jBtnEntrar.setBounds(431, 84, 107, 38);
			jBtnEntrar.setText("Entrar");
			jBtnEntrar.setFont(new Font("Dialog", Font.PLAIN, 12));		
			jBtnEntrar.setMnemonic(KeyEvent.VK_ENTER);
			jBtnEntrar.registerKeyboardAction(jBtnEntrar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			jBtnEntrar.registerKeyboardAction(jBtnEntrar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);

			jBtnEntrar.setActionCommand("entrar");			
		}
		return jBtnEntrar;
	}
	
	private JButton getJButtonCancelar() {
		if (jBtnCancelar == null) {
			jBtnCancelar = model.getJButtonCancelar();
			//jBtnCancelar =new JButton();
			jBtnCancelar.setBounds(549, 84, 107, 38);
			jBtnCancelar.setText("Cancelar");
			jBtnCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));			
			jBtnCancelar.setActionCommand("cancelar");
		}
		return jBtnCancelar;
	}
	
	public void addLoginListener(LoginListener loginListener){
		this.listener=loginListener;
		jBtnEntrar.addActionListener(listener);
		jBtnCancelar.addActionListener(listener);
		jTxtUser.addKeyListener(listener);
		jPass.addKeyListener(listener);
	}
	
	public void showMessage(Mensaje mensaje){
		Utilities.showMensaje(this, mensaje);
	}
}
