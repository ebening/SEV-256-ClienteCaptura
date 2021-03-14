package com.adinfi.sevCaptura.entities;


import com.adinfi.sevCaptura.controller.CapturaMercanciaController;
import com.adinfi.sevCaptura.model.CapturaMercanciaModel;
import com.adinfi.sevCaptura.view.CapturaMercanciaView;

public class VentanaCapturaMercancia {
	
	private CapturaMercanciaModel model;
	private CapturaMercanciaView view;
	private CapturaMercanciaController controller;
	
	public VentanaCapturaMercancia (CapturaMercanciaModel model, CapturaMercanciaView view,
			CapturaMercanciaController controller){
		this.model=model;
		this.view=view;
		this.controller=controller;
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public CapturaMercanciaModel getModel() {
		return model;
	}
	/**
	 * 
	 * @param model
	 */
	public void setModel(CapturaMercanciaModel model) {
		this.model = model;
	}
	
	public CapturaMercanciaView getView() {
		return view;
	}
	public void setView(CapturaMercanciaView view) {
		this.view = view;
	}
	public CapturaMercanciaController getController() {
		return controller;
	}
	public void setController(CapturaMercanciaController controller) {
		this.controller = controller;
	}
	
	
	
	

}
