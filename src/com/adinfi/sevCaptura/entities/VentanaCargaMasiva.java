package com.adinfi.sevCaptura.entities;

import com.adinfi.sevCaptura.controller.CargaMasivaController;
import com.adinfi.sevCaptura.model.CargaMasivaModel;
import com.adinfi.sevCaptura.view.CargaMasivaVista;

public class VentanaCargaMasiva {
	
	private CargaMasivaModel model;
	private CargaMasivaController controller;
	private CargaMasivaVista view;
	
	
	
	
	public VentanaCargaMasiva (CargaMasivaModel model,CargaMasivaController controller,CargaMasivaVista view) {
		this.model=model;
		this.controller=controller;
		this.view=view;
		
	}
	
	public CargaMasivaModel getModel(){
		return model;
		
	}
	
	/**
	 * 
	 * @param model
	 */
	public void setModel(CargaMasivaModel model) {
		this.model = model;
	}
	/**
	 * 
	 * @return
	 */
	public CargaMasivaController getController() {
		return controller;
	}
	/**
	 * 
	 * @param controller
	 */
	public void setController(CargaMasivaController controller) {
		this.controller = controller;
	}
	/**
	 * 
	 * @return
	 */
	public CargaMasivaVista getView() {
		return view;
	}
	/**
	 * 
	 * @param view
	 */
	public void setView(CargaMasivaVista view) {
		this.view = view;
	}
	
	

}
