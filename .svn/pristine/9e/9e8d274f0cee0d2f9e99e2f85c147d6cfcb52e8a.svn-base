package com.adinfi.sevCaptura.controller;

import java.util.Observable;


import com.adinfi.sevCaptura.model.CargaMasivaMercanciaModel;

import com.adinfi.sevCaptura.view.CargaMasivaMercancia;


public class CargaMasivaMercanciaController extends Observable {
	private CargaMasivaMercanciaModel model=null;
	private CargaMasivaMercancia view =null;

	private String Idusuario;

	
	public CargaMasivaMercanciaController(CargaMasivaMercanciaModel model,CargaMasivaMercancia view,String idusuario){
		this.model=model;
		this.view=view;
		this.view.addListenerCargaMasiva(new CargaMasivaMercanciaListener(this));
		this.Idusuario=idusuario;
		
		if(this.model.getLote() != null){
						if(this.model.muestraFacturasPorLote(Idusuario)){
							this.model.execute();
						}	
		}else{
			
		}
			
	}


	
	public void setUsuario(String IdUsuario){
		this.Idusuario = IdUsuario;
	}

	public void finCargaMasiva() {
			setChanged();
			notifyObservers("terminoCargaMasivaMercancia");
	}
	

}
