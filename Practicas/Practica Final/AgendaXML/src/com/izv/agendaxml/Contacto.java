package com.izv.agendaxml;

	import java.io.Serializable;

public class Contacto implements Serializable, Comparable<Contacto>{

	private static final long serialVersionUID = 14112012L;
	private String Nombre, Telefono, email;

	public Contacto(String nombre, String telefono, String email) {
		super();
		Nombre = nombre;
		Telefono = telefono;
		this.email = email;
	}

	public Contacto() {
		this("nombre", "telefono", "email");
	}
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Contacto){
			Contacto c=(Contacto)o;
			if(this.Nombre.equals(c.Nombre)){
				if(this.Telefono.equals(c.Telefono)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Contacto another){
		int dif=this.Nombre.compareTo(another.Nombre);
		if(dif!=0)
			return dif;
		dif=this.Telefono.compareTo(another.Telefono);
		if(dif!=0)
			return dif;
		return 0;
	}

	@Override
	public String toString() {
		return "Contacto [Nombre=" + Nombre + ", Telefono=" + Telefono
				+ ", email=" + email + "]";
	}
	
}