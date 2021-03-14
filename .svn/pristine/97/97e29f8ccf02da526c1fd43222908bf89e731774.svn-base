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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.adinfi.sevCaptura.resources.Constants;

public class CapturaMercanciaListener implements PropertyChangeListener, FocusListener, ActionListener, ItemListener, MouseListener, WindowListener, InternalFrameListener{

	CapturaMercanciaController controller= null;
	
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
			
		}else if(command.equals(Constants.CMD_TIPO_DOCUMENTO_FACTURA)){
				
				//int tipoDocumento=1;
			//controller.validaTipoDocumento(tipoDocumento);
		}else if(command.equals(Constants.CMD_PLAZA)){
			controller.actualizaDatosProveedor();
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName().equals("jTableFactura")){
			controller.validarFilaSeleccionada();
		}
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		controller.actualizaDatosProveedor();
	}
	
	
	public CapturaMercanciaListener(CapturaMercanciaController controller) {
		this.controller=controller;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
	
		controller.cerrarVentanaCapturaMercancia();
		
	}



	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
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
	public void windowClosing(WindowEvent arg0) {
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





	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		controller.actualizaDatosProveedor();
	}



	
}
