package com.adinfi.sevCaptura.controller;

import java.util.Observable;

import com.adinfi.sevCaptura.model.LotesMercanciaModel;
import com.adinfi.sevCaptura.view.LotesMercanciaView;

public class LotesMercanciaController extends Observable{
	private LotesMercanciaModel model=null;
	private LotesMercanciaView view=null;
	
	public LotesMercanciaController(LotesMercanciaModel model,LotesMercanciaView view){
		this.model=model;
		this.view=view;
		this.view.addLotesListener(new LotesMercanciaListener(this));
		this.model.procesoCargaLotesPendiente();
	}

	/**
	 * verifica si hay facturas pendiente
	 * de capturar de lote seleccionado
	 */
	public void consultarLote() {
		model.mostrarLoteSeleccionado();
		
	}
	
	/**
	 * abre la ventana de captura Mercancía
	 * y muestras las facturas de lote
	 * seleccionado.
	 */
	public void abrirLote() {
		String lote = model.getLote();
		if(lote != null){
			setChanged();
			notifyObservers("AbrirLoteMercancia_"+lote);
		}
	}
	
	/**
	 * método que le notifica al la clase
	 * menu controller de cerrar la ventana 
	 * de lotes
	 */
	public void cerrarVentanLotes() {
		setChanged();
		notifyObservers("CerrarVentanaLotesMercancia");
	}		
}
