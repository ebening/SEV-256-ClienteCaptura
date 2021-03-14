package com.adinfi.sevCaptura.entities;


import com.adinfi.sevCaptura.controller.CargaMasivaMercanciaController;
import com.adinfi.sevCaptura.model.CargaMasivaMercanciaModel;
import com.adinfi.sevCaptura.view.CargaMasivaMercancia;


public class VentanaCargaMasivaMercancia {
	
	private CargaMasivaMercanciaModel model;
	private CargaMasivaMercanciaController controller;
	private CargaMasivaMercancia view;
	
	public VentanaCargaMasivaMercancia(CargaMasivaMercanciaModel model,CargaMasivaMercanciaController controller,CargaMasivaMercancia view){
		this.setModel(model);
		this.setView(view);
		this.setController(controller);
	}

	public CargaMasivaMercanciaModel getModel() {
		return model;
	}

	public void setModel(CargaMasivaMercanciaModel model) {
		this.model = model;
	}

	public CargaMasivaMercanciaController getController() {
		return controller;
	}

	public void setController(CargaMasivaMercanciaController controller) {
		this.controller = controller;
	}

	public CargaMasivaMercancia getView() {
		return view;
	}

	public void setView(CargaMasivaMercancia view) {
		this.view = view;
	}

}
