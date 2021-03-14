package com.adinfi.sevCaptura.controller;

import java.util.Observable;

import com.adinfi.sevCaptura.model.LotesModel;
import com.adinfi.sevCaptura.view.LotesView;

	public class LotesController extends Observable{
		private LotesModel model=null;
		private LotesView view=null;
		
		
		public LotesController(LotesModel model, LotesView view){
			this.model=model;
			this.view=view;
			this.view.addLotesListener(new LotesListner(this));
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
		 * abre la ventana de captura gastos 
		 * y muestras las facturas de lote
		 * seleccionado.
		 */
		public void abrirLote() {
			String lote = model.getLote();
			if(lote != null){
				setChanged();
				notifyObservers("AbrirLote_"+lote);
			}
			
		}
		
		/**
		 * método que le notifica al la clase
		 * menu controller de cerrar la ventana 
		 * de lotes
		 */
		public void cerrarVentanLotes() {
			setChanged();
			notifyObservers("CerrarVentanaLotes");
		}		
	}
