package com.adinfi.sevCaptura.entities;

import com.adinfi.sevCaptura.controller.LotesMercanciaController;
import com.adinfi.sevCaptura.model.LotesMercanciaModel;
import com.adinfi.sevCaptura.view.LotesMercanciaView;

public class VentanaLotesMercancia {
	
	private LotesMercanciaModel model;
	private LotesMercanciaView view;
	private LotesMercanciaController controller;
	
	public VentanaLotesMercancia(LotesMercanciaModel model,LotesMercanciaView view,LotesMercanciaController controller){
		this.setModel(model);
		this.setView(view);
		this.setController(controller);
	}

	public LotesMercanciaModel getModel() {
		return model;
	}

	public void setModel(LotesMercanciaModel model) {
		this.model = model;
	}

	public LotesMercanciaView getView() {
		return view;
	}

	public void setView(LotesMercanciaView view) {
		this.view = view;
	}

	public LotesMercanciaController getController() {
		return controller;
	}

	public void setController(LotesMercanciaController controller) {
		this.controller = controller;
	}

}
