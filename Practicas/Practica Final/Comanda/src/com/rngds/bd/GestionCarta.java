package com.rngds.bd;

	import com.rngds.pojo.Carta;
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;

public class GestionCarta {

	private Ayudante abd;
	private SQLiteDatabase bd;
	
	public void close() {
		abd.close();
	}
	
	public int delete(){
		String condicion = Contrato.Carta.IDCARTA + " > -1";
		int cuenta = bd.delete(Contrato.Carta.TABLA, condicion, null);
		return cuenta;
	}

	public GestionCarta(Context c) {
		abd = new Ayudante(c);
	}
	
	public Cursor getCursor(){
		return getCursor(null, null);
	}
	
	public Cursor getCursor(String condicion, String [] parametrosPrepared){
		Cursor cursor = bd.query(Contrato.Carta.TABLA, null, condicion, parametrosPrepared, 
				null, null, Contrato.Carta.NOMBRE);
		return cursor;
	}

	public long insert(Carta obj) {
		ContentValues valores = new ContentValues();
		valores.put(Contrato.Carta.IDCARTA, obj.getId());
		valores.put(Contrato.Carta.NOMBRE, obj.getNombre());
		valores.put(Contrato.Carta.PRECIO, obj.getPrecio());
		long id = bd.insert(Contrato.Carta.TABLA, null, valores);
		return id;
	}

	public void open() {
		bd = abd.getWritableDatabase();
	}
	
	public void openRead() {
		bd = abd.getReadableDatabase();
	}

	
}