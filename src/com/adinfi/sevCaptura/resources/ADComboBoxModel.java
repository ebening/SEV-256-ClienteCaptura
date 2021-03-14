package com.adinfi.sevCaptura.resources;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ADComboBoxModel extends AbstractListModel implements ComboBoxModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -151563883817327745L;
	private String [] items = null;//cadena q recibira los datos a mostrar.
	private String itemSelecionado=null;
	
	public ADComboBoxModel(String[] tipoVendedores) {
		// TODO Auto-generated constructor stub
	//	System.out.println("Elegir tipo de Vendedor1");
		this.items=tipoVendedores;
	}

	public ADComboBoxModel() {
		// TODO Auto-generated constructor stub
	}

	public void remplaceItems(String[] Vendedores) { 
		this.items=Vendedores;
	    return;
	  }
	
	@Override
	public Object getSelectedItem() { //metodo implementado por la interface  JComboBoxModel
		//System.out.println("Elegir tipo de Vendedor2");
	    return itemSelecionado;
	  }

	@Override
	public void setSelectedItem(Object item) {
		//System.out.println("Elegir tipo de Vendedor3");
	    itemSelecionado = (String) item;
		fireContentsChanged(this, -1, -1);
	  }

	@Override
	public Object getElementAt(int index) {
		//System.out.println("Elegir tipo de Vendedor4");
	    return items[index];
	  }

	@Override
	 public int getSize() {
		//System.out.println("Elegir tipo de Vendedor5");
	    return items.length;
	  }
}
