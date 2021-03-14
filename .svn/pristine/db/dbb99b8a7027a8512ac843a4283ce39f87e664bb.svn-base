package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class LotesListner implements ActionListener, MouseListener, InternalFrameListener{

	
	private LotesController  controller=null;

	public LotesListner(LotesController controller) {
		this.controller=controller;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("AbrirLote")){
			controller.abrirLote();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.consultarLote();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		controller.cerrarVentanLotes();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}


}
