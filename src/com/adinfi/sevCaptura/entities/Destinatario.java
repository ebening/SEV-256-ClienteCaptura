package com.adinfi.sevCaptura.entities;

public class Destinatario {
	private String idUsuario;
	private String nombres;
	private String apellidos;
	private String correo;
	private String idPuesto;
	private int idArea;
	private String idJefe;
	private int idTipoUsuario;
	
	public Destinatario(String idUsuario,String nombres,String apellidos,String correo,
			String idPuesto, int idArea,String idJefe, int idTipousuario){
		
		this.idUsuario=idUsuario;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.correo=correo;
		this.idPuesto=idPuesto;
		this.idArea=idArea;
		this.idJefe=idJefe;
		this.idTipoUsuario=idTipousuario;
		
	}
	public Destinatario(){
		
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombre) {
		this.nombres = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidoPat) {
		this.apellidos = apellidoPat;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getIdJefe() {
		return idJefe;
	}
	public void setIdJefe(String idJefe) {
		this.idJefe = idJefe;
	}
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	

}
