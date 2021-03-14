package com.adinfi.sevCaptura.controller;

import java.util.Observable;

import com.adinfi.sevCaptura.model.ReporteCargaModel;
import com.adinfi.sevCaptura.view.ReporteCargaView;

public class ReporteCargaController extends Observable {

	private ReporteCargaModel model;
	private ReporteCargaView view;
	private String idUsuario;

	public ReporteCargaController(ReporteCargaModel model,
			ReporteCargaView view, String idusuario) {
		this.model = model;
		this.view = view;
		this.idUsuario = idusuario;
		this.view.addReporteCargaListener(new ReporteCargaListener(this));
		this.model.setIdUsuario(this.idUsuario);

	}

	public void mostrarReporte() {
		model.mostrarReporte();

	}

	public void limpiarResultados() {
		model.limpiarResultados();
		model.limpiarCamposCaptura();
	}

	public void mostrarExcel() {
		model.mostrarExcel();
	}

	public void cerrarVentanaReporteCarga() {
		setChanged();
		notifyObservers("CerrarVentanaReporteCarga");
	}
}
