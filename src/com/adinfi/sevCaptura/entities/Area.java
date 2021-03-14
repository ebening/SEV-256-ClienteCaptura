package com.adinfi.sevCaptura.entities;

public class Area {
	
	private int id;
	private String clave;
	private String nombre;
	
	
	public Area(){
	}
	public Area(int id,String clave,String nombre){
		this.id=id;
		this.clave=clave;
		this.nombre=nombre;
	}
	
	public int  getId(){
		return this.id;
	}
	
	public String  getClave(){
		return this.clave;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public  void setId(int id){
		this.id=id;
	}
	
	public  void setIdEmpresa(String clave){
		this.clave=clave;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}

}
