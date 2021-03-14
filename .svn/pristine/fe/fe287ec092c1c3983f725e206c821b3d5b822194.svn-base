package com.adinfi.sevCaptura.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.adinfi.sevCaptura.resources.Constants;

public class CargaMasivaListener implements ActionListener {

	CargaMasivaController controller=null;
	
	public CargaMasivaListener(CargaMasivaController controller){
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
