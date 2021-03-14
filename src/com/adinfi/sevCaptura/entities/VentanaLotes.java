package com.adinfi.sevCaptura.entities;

import com.adinfi.sevCaptura.controller.LotesController;
import com.adinfi.sevCaptura.model.LotesModel;
import com.adinfi.sevCaptura.view.LotesView;

public class VentanaLotes {

	private LotesModel model;
	private LotesView view;
	private LotesController controller;
	
	public VentanaLotes(){};
	
	/**
	 * 
	 * @param model
	 * @param view
	 * @param controller
	 */
	public VentanaLotes(LotesModel model, LotesView view, LotesController controller){
		this.model = model;
		this.view = view;
		this.controller = controller;
	}
	
	/**
	 * @return the model
	 */
	public LotesModel getModel() {
		return model;
	}
	
	/**
	 * @param model the model to set
	 */
	public void setModel(LotesModel model) {
		this.model = model;
	}

	/**
	 * @return the view
	 */
	public LotesView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(LotesView view) {
		this.view = view;
	}

	/**
	 * @return the controller
	 */
	public LotesController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(LotesController controller) {
		this.controller = controller;
	}
	
}
