package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.adinfi.sevCaptura.resources.Constants;

public class MenuListener implements ActionListener, WindowListener,
		InternalFrameListener {
	
	private MenuController controller;
	
	public MenuListener(MenuController controller){
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		String action = e.getActionCommand();
		
		
		
		if(action.equals("facturaGastos")){
			controller.abrirVentana(Constants.VENTANA_CAPTURA_GASTOS, null);
			
		}else if(action.equals("facturaMercancia")){
			controller.abrirVentana(Constants.VENTANA_CAPTURA_MERCANCIAS, null);
			
		}else if(action.equals("ventanaLotes")){
			controller.abrirVentana(Constants.VENTANA_LOTES, null);
		}else if(action.equals("ventanaReporteCarga")){
			controller.abrirVentana(Constants.VENTANA_REPORTE_CARGA, null);
		}else if(action.equals("ventanaLotesMercancia")){
			controller.abrirVentana(Constants.VENTANA_LOTES_MERCANCIA, null);
		}

	}
	@Override
	public void windowClosing(WindowEvent arg0) {
	controller.confirmarSalirUsuario();
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



}
