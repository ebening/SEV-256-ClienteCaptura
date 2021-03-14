package com.adinfi.sevCaptura.entities;

public class Usuario2 {

	private int  tipoUsuario;
	private String nombre;
	private String pass;
	private String noEmpleado;
	private String correo;
	private int id;
	
   public Usuario2(){
	   
  }
   
   public Usuario2(String noEmpleado,String pass){
	   this(0,"",pass,noEmpleado);
   }
   
	public Usuario2(int tipoUsuario, String nombre, String pass, String noEmpleado){
		this.setTipoUsuario(tipoUsuario);
		this.setNombre(nombre);
		this.setPassword(pass);
		this.setNoEmpleado(noEmpleado);
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
	   
	   public String getNoEmpleado() {
			return this.noEmpleado;
		}
	   public int getId() {
			return this.id;
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
	public void setId(int id){
		this.id= id;
		
	}
	public void setNoEmpleado(String noEmpleado){
		this.noEmpleado= noEmpleado;
		
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
   
}
