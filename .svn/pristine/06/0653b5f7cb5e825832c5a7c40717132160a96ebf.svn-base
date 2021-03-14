package com.adinfi.sevCaptura.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.adinfi.sevCaptura.controller.MenuListener;
import com.adinfi.sevCaptura.model.MenuModel;


public class MenuView extends JFrame {
	private MenuModel menuModel = null;
	private MenuListener listener = null;  //  @jve:decl-index=0:
	private JDesktopPane jDesktopPanePrincipal = null;	
	private JMenuBar menuBar = null;
	private JMenu menuCaptura = null;
	private JMenu menuReportes = null;
	private JMenu menuLotes = null;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenuItem jMnuItmFactGts=null;
	private JMenuItem jMnuItmLotes = null;
	private JMenuItem jMnuItmLotesMercancia = null;
	private JMenuItem jMnuItmReportes = null;
	private JMenuItem jMnuItmFactMcia = null;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MenuView(MenuModel menuModel){
		this.menuModel  = menuModel;
		initialize();
	}
	
	private void initialize(){
		this.setSize(new Dimension(1200, 730));
		this.setResizable(false);
		this.setTitle("Sistema de Captura");
		this.setExtendedState(JFrame.MAXIMIZED_VERT);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setContentPane(getJDesktopPanePrincipal());
		this.setJMenuBar(getThisMenuBar());	
		this.setVisible(true);
	}

	public MenuView() {
		this.menuModel  = new  MenuModel();
		initialize();
	}
	
	private JDesktopPane getJDesktopPanePrincipal() {
		if (jDesktopPanePrincipal == null) {
			//jDesktopPanePrincipal = menuModel.getJDesktopPanePrincipal();
			jDesktopPanePrincipal = new JDesktopPane();
			jDesktopPanePrincipal.setBackground(new Color(245, 222, 179));
			jDesktopPanePrincipal.setSize(new Dimension(1200, 730));
			jDesktopPanePrincipal.add(new JMenuBar());
			jDesktopPanePrincipal.setVisible(true);
			
			//jDesktopPanePrincipal.add(getJInternalFrameReports(), null);
		}
		return jDesktopPanePrincipal ;
	}
	
	private JMenuBar getThisMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();			
			menuBar.setPreferredSize(new Dimension(122, 25));
			menuBar.add(getMenuCaptura());
			menuBar.add(getMenuLotes());
			menuBar.add(getMenuReportes());
		}
		return menuBar;
	}
	private JMenu getMenuCaptura() {
		if (menuCaptura == null) {
			menuCaptura = new JMenu("Captura");
			//menuCaptura.setIcon(new ImageIcon(getClass().getResource("/com/adinfi/SEV256/resources/images/DOC40C.png")));
			menuCaptura.setText("Captura   ");			
			menuCaptura.setToolTipText("Captura de Facturas.");
			menuCaptura.add(getMnuItmCapturaGts());	
			menuCaptura.add(getMnuItmCapturaMcia());	
		}
		return menuCaptura;
	}
	private JMenu getMenuLotes() {
		if (menuLotes == null) {
			menuLotes = new JMenu("Lotes");
			//menuLotes.setIcon(new ImageIcon(getClass().getResource("/com/adinfi/uanl/resources/images/CapturaDocument.png")));
			menuLotes.setText("Lotes   ");			
			menuLotes.setToolTipText("Lotes Pedientes.");
			menuLotes.add(getMnuItmlotes());	
			menuLotes.add(getMnuItmlotesmercancia());
		}
		return menuLotes;
	}
	private JMenu getMenuReportes() {
		if (menuReportes == null) {
			menuReportes = new JMenu("Reportes");
			//menuCaptura.setIcon(new ImageIcon(getClass().getResource("/com/adinfi/uanl/resources/images/CapturaDocument.png")));
			menuReportes.setText("Reportes   ");			
			menuReportes.setToolTipText("Ver Reportes.");
			menuReportes.add(getMnuItmReportes());	
		}
		return menuReportes;
	}
	
	private JMenuItem getMnuItmCapturaGts() {
		if(jMnuItmFactGts==null){
			jMnuItmFactGts= new JMenuItem("Factura de Gastos");
			jMnuItmFactGts.setActionCommand("facturaGastos");
		}
		return jMnuItmFactGts;
	}
	
	private JMenuItem getMnuItmCapturaMcia() {
		if(jMnuItmFactMcia==null){
			jMnuItmFactMcia= new JMenuItem("Factura de Mercancia");
			jMnuItmFactMcia.setActionCommand("facturaMercancia");
		}
		return jMnuItmFactMcia;
	}
	
	private JMenuItem getMnuItmlotes() {
		if(jMnuItmLotes==null){
			jMnuItmLotes= new JMenuItem("Lotes Factura Gastos");
			jMnuItmLotes.setActionCommand("ventanaLotes");
		}
		return jMnuItmLotes;
	}
	
	private JMenuItem getMnuItmlotesmercancia() {
		if(jMnuItmLotesMercancia==null){
			jMnuItmLotesMercancia= new JMenuItem("Lotes Factura Mercacía");
			jMnuItmLotesMercancia.setActionCommand("ventanaLotesMercancia");
		}
		return jMnuItmLotesMercancia;
	}
	
	
	private JMenuItem getMnuItmReportes() {
		if(jMnuItmReportes==null){
			jMnuItmReportes= new JMenuItem("Abrir Ventana de Reportes");
			jMnuItmReportes.setActionCommand("ventanaReporteCarga");
		}
		return jMnuItmReportes;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addMenuListen(MenuListener listener) {
		this.listener = listener;
		jMnuItmFactGts.addActionListener(this.listener);
		jMnuItmFactMcia.addActionListener(listener);
		jMnuItmLotes.addActionListener(listener);
		jMnuItmLotesMercancia.addActionListener(listener);
		jMnuItmReportes.addActionListener(listener);
		this.addWindowListener(listener);
		System.out.println("Se agregaron listeners");
	}

}
