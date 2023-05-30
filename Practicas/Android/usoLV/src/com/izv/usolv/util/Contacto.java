package com.izv.usolv.util;

	import java.io.Serializable;

public class Contacto implements Serializable, Comparable <Contacto> {
	
	private static final long serialVersionUID = 14112012L;
	private String nombre, telefono, correo, imagen;
	
	public Contacto(){
		
	}

	public Contacto(String nombre, String telefono, String correo) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
	}

	public Contacto(String nombre, String telefono, String correo, String imagen) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", telefono=" + telefono
				+ ", correo=" + correo + ", imagen=" + imagen + "]";
	}

	@Override
	//Codigo pseudo aleatorio generado a partir de un objeto
	//El hashCode de dos objetos iguales debe ser el mismo.
	//Puede haber 2 objetos con el mismo hashCode que no sean iguales.
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		//result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	//Declara cuando 2 objetos son iguales o no (True o False)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	//Comparar Elementos
	public int compareTo(Contacto c) {
		int a=this.nombre.compareTo(c.nombre);
		if(a==0)
			return this.telefono.compareTo(c.telefono);
		return 0;
	}
	
}