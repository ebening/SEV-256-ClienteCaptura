package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.adinfi.sevCaptura.resources.Constants;


public class CapturaGastosListener implements  FocusListener, ActionListener, ItemListener, MouseListener, WindowListener, InternalFrameListener {

	CapturaGastosController controller=null;
	
	public CapturaGastosListener(CapturaGastosController controller) {
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		if(command.equals(Constants.CMD_CARGAR_ARCHIVO)){
			
			controller.importaDocumento(Constants.IMPORTA_UN_DOCUMENTO);
			
		}else if(command.equals(Constants.CMD_CARGAR_CARPETA)){
			
			controller.importaDocumento(Constants.IMPORTA_CARPETA);
			
		}else if (command.equals(Constants.CMD_CARGA_MASIVA)){
			
			controller.cargaMasiva();
			
		}else if (command.equals(Constants.CMD_CAMBIAR_URL)){
			
			controller.cambiarUrl();
			
		}else if (command.equals(Constants.CMD_DESCARTAR)){
		
			controller.descartarDocumento();
			
		}else if (command.equals(Constants.CMD_GUARDAR_FACTURA)){
		
			controller.guardarFacturaDB2();
			
		}else if (command.equals(Constants.CMD_PAGINA_ANTERIOR)){
			
			controller.cambiarPagina(Constants.CMD_PAGINA_ANTERIOR);
			
		}else if (command.equals(Constants.CMD_PAGINA_SIGUIENTE)){
			
			controller.cambiarPagina(Constants.CMD_PAGINA_SIGUIENTE);
			
		}else if (command.equals(Constants.CMD_AREA)){
			controller.ActualizaCombos();	
		}else if(command.equals(Constants.CMD_TIPO_DOCUMENTO_FACTURA)){				
				int tipoDocumento=1;
				controller.validaTipoDocumento(tipoDocumento);
		}else if (command.equals(Constants.CMD_TIPO_DOCUMENTO_FACTURA48hrs)){
				int tipoDocumento=2;
			controller.validaTipoDocumento(tipoDocumento);
				
		}else if (command.equals(Constants.CMD_TIPO_DOCUMENTO_SOPORTE)){
				int tipoDocumento=3;
				controller.validaTipoDocumento(tipoDocumento);
			
		}else if (command.equals(Constants.CMD_ASIGNAR_FACTURA_DOCUMENTO)){
				controller.agregaDocuementoaFacturas();
			
	}
	
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getComponent().getName().equals("noProv")){
			controller.actualizaDatosProveedor();
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName().equals("jTableFactura")){			
			controller.validarFilaSeleccionada();
		}else if(e.getComponent().getName().equals("jTableFacturaSoporte")){
		
		}
		
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		controller.cerrarVentanaCaptura();
		
	}
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		if(e.getComponent().getName().equals("cerrarVentanaSoporte")){
			
			controller.cambiarATipoDocFactura();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {}


}
