package com.izv.basedatosagenda.pojo;

public class Agenda {

	private long id; 
	private String nombre, telefono, correo;
	
	public Agenda(){
		this(0, "", "", "");
	}
	
	public Agenda(long id, String nombre, String telefono, String correo) {
		this.id = id;
		this.nombre = nombre.trim();
		this.telefono = telefono.trim();
		this.correo = correo.trim();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono.trim();
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo.trim();
	}
	
	@Override
	public String toString(){
		return "ID: "+id+"\n"+"Nombre: "+nombre+"\n"+"Telefono: "+telefono+"\n"+"Correo: "+correo;
	}
	
}
