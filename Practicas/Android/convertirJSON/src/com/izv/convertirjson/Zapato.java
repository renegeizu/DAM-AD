package com.izv.convertirjson;

import org.json.JSONException;
import org.json.JSONObject;

public class Zapato {
	
	private String descripcion;
	private String urlImagen;
	
	public Zapato() {
		super();
	}

	public Zapato(String descripcion, String urlImagen) {
		super();
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
	}
	
	 public Zapato(JSONObject json) throws JSONException{
	    	this(json.getString("descr"), json.getString("url"));
	 }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

}