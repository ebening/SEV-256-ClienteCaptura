package com.adinfi.sevCaptura.entities;

import java.sql.Timestamp;

/**
 * Clase que abstraer los atributos de una Approbacion
 * para registrarlos en la tabla "HISTORIAL_APROBACIONES"
 * en la base de datos de DB2.
 * @author Adanm.
 * @version 1.0.
 */
public class Aprobacion {

	private int idFactura;
	private Timestamp fecha;
	private Usuario usuarioOrigen;
	private Usuario usuarioDestino;
	/**
	 * @return the idFactura
	 */
	public int getIdFactura() {
		return idFactura;
	}
	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuarioOrigen
	 */
	public Usuario getUsuarioOrigen() {
		return usuarioOrigen;
	}
	/**
	 * @param usuarioOrigen the usuarioOrigen to set
	 */
	public void setUsuarioOrigen(Usuario usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	
	/**
	 * @return
	 */
	public String getIdUsuarioOrigen(){
		if(usuarioOrigen != null){
			return usuarioOrigen.getIdUsuario();
		}
		return null;
	}
	
	/**
	 * @param id
	 */
	public void setIdUsuarioOrigen(String id){
		if(usuarioOrigen == null){
			usuarioOrigen = new Usuario();
		}
		usuarioOrigen.setIdUsuario(id);
	}
	
	/**
	 * @return the usuarioDestino
	 */
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}
	
	/**
	 * @param usuarioDestino the usuarioDestino to set
	 */
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	
	/**
	 * @return
	 */
	public String getIdUsuarioDestino(){
		if(usuarioDestino != null){
			return usuarioDestino.getIdUsuario();
		}
		return null;
	}
	
	/**
	 * @param id
	 */
	public void setIdUsuarioDestino(String id){
		if(usuarioDestino == null){
			usuarioDestino = new Usuario();
		}
		usuarioDestino.setIdUsuario(id);
	}
	
	
}
