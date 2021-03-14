package com.adinfi.sevCaptura.entities;

import java.util.ArrayList;

public class Usuario {

	private String idUsuario=null;
	private int  tipoUsuario;
	private String nombre;
	private String apellido;
	private String pass;
	private String correo;
	private String idJefe;	
	private Puesto puesto;
	private Area area;
	private ArrayList<Aplicativo> accesoAplicativos;
	private ArrayList<Modulo> accesoModulos;

	public Usuario(){}

	/**
	 * 
	 * @param idUsuario
	 * @param password
	 */
	public Usuario(String idUsuario, String password){
		this.idUsuario = idUsuario;
		this.pass = password;
	}
      
	/**
	 * 
	 * @param tipoUsuario
	 * @param nombre
	 * @param pass
	 * @param idUsuario
	 */
	public Usuario(int tipoUsuario, String nombre, String pass, String idUsuario){
		this.setTipoUsuario(tipoUsuario);
		this.setNombre(nombre);
		this.setPassword(pass);
		this.setIdUsuario(idUsuario);
	}
	
	public String getNombre() {
		return this.nombre;
	}
   
	public String getPassword() {
		return this.pass;
	}
	
	public int getTipoUsuario() {
		return this.tipoUsuario;
	}
	
	public void setNombre(String nombre) {
			this.nombre = nombre;
	}
	
	public void setPassword(String pass) {
		this.pass = pass;
	}
	   
	public void setTipoUsuario(int tipoUsuario){
		this.tipoUsuario= tipoUsuario;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the puesto
	 */
	public Puesto getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getMonto(){
		if(this.puesto != null){
			return this.puesto.getMonto();
		}
		return (double)0;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the idJefe
	 */
	public String getIdJefe() {
		return idJefe;
	}

	/**
	 * @param idJefe the idJefe to set
	 */
	public void setIdJefe(String idJefe) {
		this.idJefe = idJefe;
	}

	/**
	 * @return the accesoAplicativos
	 */
	public ArrayList<Aplicativo> getAccesoAplicativos() {
		return accesoAplicativos;
	}

	/**
	 * @param accesoAplicativos the accesoAplicativos to set
	 */
	public void setAccesoAplicativos(ArrayList<Aplicativo> accesoAplicativos) {
		this.accesoAplicativos = accesoAplicativos;
	}

	/**
	 * @return the accesoModulos
	 */
	public ArrayList<Modulo> getAccesoModulos() {
		return accesoModulos;
	}

	/**
	 * @param accesoModulos the accesoModulos to set
	 */
	public void setAccesoModulos(ArrayList<Modulo> accesoModulos) {
		this.accesoModulos = accesoModulos;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNombreCompleto(){
		StringBuffer sb = new StringBuffer();
		
		if(this.nombre != null){
			sb.append(this.nombre);
		}
		
		if(this.apellido != null){
			sb.append(" ");
			sb.append(this.apellido);
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param idAplicaivo
	 * @return
	 */
	public boolean tieneAccesoAplicativo(int idAplicaivo){
		boolean tieneAcceso = false;
		
		for (Aplicativo app : this.accesoAplicativos) {
			if(app.getIdAplicativo() == idAplicaivo){
				tieneAcceso = true;
				break;
			}
		}
		
		return tieneAcceso;
	}
   
}
