package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class ReporteCargaListener implements ActionListener,
		InternalFrameListener {

	private ReporteCargaController controller = null;

	public ReporteCargaListener(ReporteCargaController controller) {
		this.controller = controller;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("Buscar")) {

			controller.mostrarReporte();

		} else if (action.equals("Limpiar")) {

			controller.limpiarResultados();
		} else if (action.equals("Excel")) {

			controller.mostrarExcel();

		}

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
		controller.cerrarVentanaReporteCarga();

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

}
