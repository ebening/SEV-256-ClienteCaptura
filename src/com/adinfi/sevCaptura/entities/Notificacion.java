package com.adinfi.sevCaptura.entities;

import java.sql.Timestamp;

/**
 * 
 * 
 * @author 
 * @version 1.0 - 10/11/2012.
 */

public class Notificacion {
	
	/**
	 * 
	 * 
	 * 
	 */
	
	private int idfactura;
	private int idEstado;
	private String idUsuarioOrigen;
	private String idUsuarioDestino;
	private Timestamp fecha;
	private boolean automatica;
	private boolean Escalamiento;
	private int Dias;
	
	/**
	 * @return the idfactura
	 */
	public int getIdfactura() {
		return idfactura;
	}
	
	/**
	 * @param idfactura the idfactura to set
	 */
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}

	/**
	 * @return the idEstado
	 */
	public int getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the idUsuarioOrigen
	 */
	public String getIdUsuarioOrigen() {
		return idUsuarioOrigen;
	}

	/**
	 * @param idUsuarioOrigen the idUsuarioOrigen to set
	 */
	public void setIdUsuarioOrigen(String idUsuarioOrigen) {
		this.idUsuarioOrigen = idUsuarioOrigen;
	}

	/**
	 * @return the idUsuarioDestino
	 */
	public String getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	/**
	 * @param idUsuarioDestino the idUsuarioDestino to set
	 */
	public void setIdUsuarioDestino(String idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
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
	 * @return the automatica
	 */
	public boolean isAutomatica() {
		return automatica;
	}

	/**
	 * @param automatica the automatica to set
	 */
	public void setAutomatica(boolean automatica) {
		this.automatica = automatica;
	}

	/**
	 * @return the escalamiento
	 */
	public boolean isEscalamiento() {
		return Escalamiento;
	}

	/**
	 * @param escalamiento the escalamiento to set
	 */
	public void setEscalamiento(boolean escalamiento) {
		Escalamiento = escalamiento;
	}

	/**
	 * @return the dias
	 */
	public int getDias() {
		return Dias;
	}

	/**
	 * @param dias the dias to set
	 */
	public void setDias(int dias) {
		Dias = dias;
	}
	

}
