package com.rngds.pojo;

	import java.io.Serializable;
	import org.json.JSONException;
	import org.json.JSONObject;

public class Carta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private float precio;
	
	public Carta() {
		this(0, "Carta", 0);
	}
	
	public Carta(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Carta(String nombre, float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public Carta(long id, String nombre, float precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Carta(JSONObject json) throws JSONException{
    	this(json.getLong("id"), json.getString("nombre"), Float.parseFloat(json.getString("precio")));
    }

	public Carta(String[] registro) {
        set(registro, 0);
    }
    
    public Carta(String[] registro, int inicial) {
        set(registro, inicial);
    }

    public final void set(String[] registro){
        set(registro, 0);
    }

    public final void set(String[] registro, int Inicial){
        if(registro!=null){
            this.id = Long.parseLong(registro[0+Inicial]);
            this.nombre = registro[1+Inicial];
	    this.precio = Float.parseFloat(registro[2+Inicial]);
        }
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
        this.nombre = nombre;
    }

    public float getPrecio() {
	return precio;
    }

    public void setPrecio(float precio) {
	this.precio = precio;
    }

    @Override
    public String toString() {
	return "Carta{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
	
}