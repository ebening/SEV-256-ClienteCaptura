package com.adinfi.sevCaptura.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.adinfi.sevCaptura.dao.LotesDAO;
import com.adinfi.sevCaptura.dao.LotesDAOInterface;
import com.adinfi.sevCaptura.entities.Mensaje;
import com.adinfi.sevCaptura.resources.BundleManager;
import com.adinfi.sevCaptura.resources.Constants;
import com.adinfi.sevCaptura.resources.Utilities;

public class LotesMercanciaModel {

	public static Logger logger = Logger.getLogger(LotesMercanciaModel.class);
	private ArrayList<String> lotesPendientes=null;
	private LotesDAOInterface DAO = null;//mismo DAO 
	private JTextField textFieldLote = null;
	private JTextField textFieldDocsPendientes = null;
	private JTable jTableLotes = null;
	private DefaultTableModel defaultTableModelLotes = null;
	private String lote = null;
	private String idUsuario=null;
	private Mensaje message;
	private JInternalFrame jFrameMensajes = null;
	private String titulo = "Sistema de Captura";
	
	public LotesMercanciaModel(){
		this.DAO=new LotesDAO();
	}
	

	public JTextField getTxtFieldLote(){
		if(textFieldLote == null){
			textFieldLote = new JTextField();
		}
		return textFieldLote;
	}
	
	public JTextField getTxtFieldDocsPendientes(){
		if(textFieldDocsPendientes == null){
			textFieldDocsPendientes = new JTextField();
		}
		return textFieldDocsPendientes;
	}
	
	public JTable getJTableDatos() {
		if (jTableLotes == null) {
			jTableLotes = new JTable(getTableModelDatos()){ 
				public boolean isCellEditable ( int rowIndex, int colIndex ) 
				{return false;}
			};
			jTableLotes.setRowHeight(20);
			jTableLotes.setEnabled(true);
			jTableLotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableLotes.getTableHeader().setReorderingAllowed(false);
			
			TableColumn columna = jTableLotes.getColumnModel().getColumn(0);
			columna.setPreferredWidth(30);
			columna.setResizable(false);
			
		}
		return jTableLotes;
	}
	
	private DefaultTableModel getTableModelDatos(){
		if(defaultTableModelLotes == null){
			String[] lista = {"Lote"};
			defaultTableModelLotes = new DefaultTableModel(lista, 10);
		}
		return defaultTableModelLotes;
	}
	
	public String getLote(){
		return lote;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Muestra los lotes
	 * que tiene facturas pendientes
	 * de concluir su captura
	 * @return true si el proceso
	 * fue exitoso
	 */
	public boolean procesoCargaLotesPendiente(){
		
		boolean exito =false;
		
		if(this.establecerConexionDB2()){
			exito=true;
			
			try{
				lotesPendientes=DAO.getLotesPendientes(this.idUsuario,Constants.TIPO_FACTURA_MERCANCIA);
				
				int filas = this.defaultTableModelLotes.getRowCount();
				int regSoporte = this.lotesPendientes.size();
				
				if(filas < regSoporte){
					this.defaultTableModelLotes.setRowCount(regSoporte);
				}
				
				if(lotesPendientes.size()!=0){
					for(int i=0;i<lotesPendientes.size();i++ ){
						defaultTableModelLotes.setValueAt(lotesPendientes.get(i), i, 0);
					}
				}
			} catch(SQLException e) {
				
			}
			
			this.cerrarConexionDB2();
		}else{
			//mostrar mensaje
		}
		
		return exito;
	}

	/**
	 * 
	 */
	public void cerrarConexionDB2(){
		try {
			
			DAO.cerrarConexionDB2();
			
		} catch (SQLException e) {
			System.out.println("Error al intentar cerrar " +
					"la conexion con DB2."+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean establecerConexionDB2(){
		boolean conexionAbierta = false;
		boolean seGeneroExcepcion = false;
		
		try {
			
			conexionAbierta = DAO.establecerConexionDB2();
			
		} catch (SQLException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;
			
		} catch (InstantiationException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;
			
		} catch (IllegalAccessException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;
			
		} catch (ClassNotFoundException e) {
			logger.error(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), e);
			seGeneroExcepcion = true;
		}
		
		if(seGeneroExcepcion){
			message = new Mensaje(BundleManager.getValue(Constants.MESSAGE_BUNDLE, "MSG002"), titulo, 1);
			this.showMessage(message);
		}
		
		return conexionAbierta;
		
	}
	
	/**
	 * Muestra el número
	 * de facturas del 
	 * lote seleccionado
	 */
	public void mostrarLoteSeleccionado() {
		String seleccion = (String) jTableLotes.getValueAt(jTableLotes.getSelectedRow(), 0);
		
		if(seleccion != null && !seleccion.isEmpty()){
			lote = seleccion;
		}else{
			lote = null;
		}
		
		if(lote != null){
			
			if(this.establecerConexionDB2()){
				
				try{
					int docsPendientes = DAO.getNumFacturasPendientesPorLote(lote,this.idUsuario,Constants.TIPO_FACTURA_MERCANCIA);
					this.textFieldLote.setText(lote);
					this.textFieldDocsPendientes.setText(String.valueOf(docsPendientes));
					
				}catch (SQLException e){
					e.printStackTrace();
				}

				this.cerrarConexionDB2();
			}
			
		}else{
			this.textFieldLote.setText("");
			this.textFieldDocsPendientes.setText("");
		}
		
	}


	/**
	 * Muestra un mensaje JOptionPane.showMessageDialog
	 * 
	 * @param mensaje
	 */
	public void showMessage(Mensaje mensaje) {
		Utilities.showMensaje(this.jFrameMensajes, mensaje);
	}



}
