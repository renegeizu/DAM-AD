package com.rngds.pojo;

	import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id; 
	private String fechaHora;
	private long idMesa;
	private int cerrado;
	
	public Pedido(){
	}
	
	public Pedido(String fechaHora, long idMesa, int cerrado) {
		super();
		this.fechaHora = fechaHora;
		this.idMesa = idMesa;
		this.cerrado = cerrado;
	}

	public Pedido(long id, String fechaHora, long idMesa, int cerrado) {
		super();
		this.id = id;
		this.fechaHora = fechaHora;
		this.idMesa = idMesa;
		this.cerrado = cerrado;
	}
	
	public Pedido(String[] registro) {
        set(registro);
    }
	
	public Pedido(JSONObject json) throws JSONException{
    	this(json.getLong("id"), json.getString("fechaHora"), json.getLong("idMesa"), json.getInt("cerrado"));
    }
    
	public final void set(String[] registro){
		if(registro!=null){
			this.id = Long.parseLong(registro[0]);
            this.fechaHora=registro[1];
            this.idMesa=Long.parseLong(registro[2]);
            this.cerrado=Integer.parseInt(registro[3]);
        }
    }

    public long getId() {
    	return id;
    }

    public void setId(long id) {
    	this.id = id;
    }

    public String getFechaHora() {
    	return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
	this.fechaHora = fechaHora;
    }

    public long getIdMesa() {
	return idMesa;
    }

    public void setIdMesa(long idMesa) {
	this.idMesa = idMesa;
    }

    public int getCerrado() {
	return cerrado;
    }

    public void setCerrado(int cerrado) {
    	this.cerrado = cerrado;
    }

    @Override
    public String toString() {
    	return "Pedido{" + "id=" + id + ", fechaHora=" + fechaHora + ", idMesa=" + idMesa + ", cerrado=" + cerrado + '}';
    }
	
}