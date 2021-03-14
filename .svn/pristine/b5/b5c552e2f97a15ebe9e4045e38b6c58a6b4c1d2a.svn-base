package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.adinfi.sevCaptura.resources.Constants;

public class CargaMasivaMercanciaListener implements ActionListener {
	
	CargaMasivaMercanciaController controller=null;
	
	public  CargaMasivaMercanciaListener(CargaMasivaMercanciaController controller){
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals(Constants.CMD_ACEPTAR_STATUS_CM)){
			controller.finCargaMasiva();
		}

	}

}
