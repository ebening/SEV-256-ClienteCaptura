package com.adinfi.sevCaptura.resources;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



public class ImageRenderer extends DefaultTableCellRenderer{
	
	 public Component getTableCellRendererComponent(JTable table, Object value,
			 boolean isSelected, boolean hasFocus, int row, int column) {
		 JLabel lbl = new JLabel("");
		 if(value instanceof String){
			 
		 }else{
			 if(value!=null){
				lbl = new JLabel("   ");
				int status = Integer.parseInt(value.toString());
				 if(status == Constants.STATUS_CAPTURA_COMPLETA){
					 lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_OK)));
					 lbl.setToolTipText("Captura completa");

				 }else if(status == Constants.STATUS_ERROR_IMPORTADO){
					 lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_NO_IMPORTADO)));
					 lbl.setToolTipText("Error al importar");

				 }else if(status == Constants.STATUS_IMPORTADO_A_CM){
					 lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_IMPORTADO)));
					 lbl.setToolTipText("Importación exitosa");
					 
				 }else if(status == Constants.STATUS_PENDIENTE_CAPTURA){
					lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_TRANSICION)));
					 lbl.setToolTipText("Pendiente de captura");
				 }else if(status == Constants.STATUS_IMPORTANDO_CM){
					lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_IMPORTANDO)));
					lbl.setToolTipText("En proceso de importación");

				 }else if(status == Constants.STATUS_CANCELADO){
						lbl.setIcon(new ImageIcon(getClass().getResource(Constants.ICONO_CANCELADO)));
						lbl.setToolTipText("Cancelado");
				 }
			 }
		 }
		 return lbl;
	 }
}

