package com.rngds.pojo;

	import java.io.Serializable;
	import org.json.JSONException;
	import org.json.JSONObject;

public class DetallePedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long cantidad; 
	private long id, idPedido, idCarta;
	private float precio;
	
	public DetallePedido(){
		this(0, 0, 0, 0, 0);
	}

	public DetallePedido(long idPedido, int idCarta, int cantidad, float precio) {
		super();
		this.idPedido = idPedido;
		this.idCarta = idCarta;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public DetallePedido(long id, long idPedido, long idCarta, long cantidad, float precio) {
		this.id = id;
		this.idPedido = idPedido;
		this.idCarta = idCarta;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public DetallePedido(String[] registro) {
        set(registro);
    }
	
	public DetallePedido(JSONObject json) throws JSONException{
    	this(json.getLong("id"), json.getLong("idPedido"), json.getLong("idCarta"),
    			json.getLong("cantidad"), Float.parseFloat(json.getString("precio")));
    }
    
    public final void set(String[] registro){
        if(registro!=null){
            this.id = Long.parseLong(registro[0]);
            this.idPedido = Long.parseLong(registro[1]);
	    this.idCarta = Long.parseLong(registro[2]);
	    this.cantidad = Long.parseLong(registro[3]);
	    this.precio = Float.parseFloat(registro[4]);
        }
    }

    public long getId() {
    	return id;
    }

    public void setId(long id) {
    	this.id = id;
    }

    public long getIdPedido() {
    	return idPedido;
    }

    public void setIdPedido(long idPedido) {
    	this.idPedido = idPedido;
    }

    public long getIdCarta() {
    	return idCarta;
    }

    public void setIdCarta(long idCarta) {
    	this.idCarta = idCarta;
    }

    public long getCantidad() {
    	return cantidad;
    }

    public void setCantidad(long cantidad) {
    	this.cantidad = cantidad;
    }

    public float getPrecio() {
    	return precio;
    }

    public void setPrecio(float precio) {
    	this.precio = precio;
    }

    @Override
    public String toString() {
    	return "Detalle{" + "id=" + id + ", idPedido=" + idPedido + ", idCarta=" + idCarta + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }

}