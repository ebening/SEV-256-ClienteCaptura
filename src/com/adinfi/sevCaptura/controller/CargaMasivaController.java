package com.adinfi.sevCaptura.controller;

import java.util.Observable;


import com.adinfi.sevCaptura.model.CargaMasivaModel;
import com.adinfi.sevCaptura.view.CargaMasivaVista;

public class CargaMasivaController extends Observable {
	
	private CargaMasivaModel model=null;
	private CargaMasivaVista view =null;
	private String Idusuario;

	
	public CargaMasivaController(CargaMasivaModel model,CargaMasivaVista view,String idusuario){
		this.model=model;
		this.view=view;
		this.view.addListenerCargaMasiva(new CargaMasivaListener(this));
		this.Idusuario=idusuario;
		
		if(this.model.getLote() != null){
						if(this.model.muestraFacturasPorLote(Idusuario)){
								this.model.execute();
						}	
		}
			
	}
	
	public void setUsuario(String IdUsuario){
		this.Idusuario = IdUsuario;
	}


	public void finCargaMasiva() {
	
			setChanged();
			notifyObservers("terminoCargaMasiva");
		
	}
	

}
