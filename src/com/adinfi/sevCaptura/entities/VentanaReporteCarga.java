package com.adinfi.sevCaptura.entities;

import com.adinfi.sevCaptura.controller.ReporteCargaController;
import com.adinfi.sevCaptura.model.ReporteCargaModel;
import com.adinfi.sevCaptura.view.ReporteCargaView;

public class VentanaReporteCarga {
	
	private ReporteCargaModel model;
	private ReporteCargaController controller;
	private ReporteCargaView view;
	
	
	public VentanaReporteCarga (ReporteCargaModel model,ReporteCargaView view,
			 ReporteCargaController controller){
		
		this.setModel(model);
		this.setView(view);
		this.setController(controller);
	}


	public ReporteCargaModel getModel() {
		return model;
	}


	public void setModel(ReporteCargaModel model) {
		this.model = model;
	}


	public ReporteCargaController getController() {
		return controller;
	}


	public void setController(ReporteCargaController controller) {
		this.controller = controller;
	}


	public ReporteCargaView getView() {
		return view;
	}


	public void setView(ReporteCargaView view) {
		this.view = view;
	}
	

}
