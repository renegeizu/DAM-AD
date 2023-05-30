package com.rngds.bd;

	import com.rngds.pojo.Mesa;
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;

public class GestionMesa {
	
	private Ayudante abd;
	private SQLiteDatabase bd;
	
	public void close() {
		abd.close();
	}
	
	public int delete(){
		String condicion = Contrato.Mesa.IDMESA + " > -1";
		int cuenta = bd.delete(Contrato.Mesa.TABLA, condicion, null);
		return cuenta;
	}

	public GestionMesa(Context c) {
		abd = new Ayudante(c);
	}
	
	public Cursor getCursor(){
		return getCursor(null, null);
	}
	
	public Cursor getCursor(String condicion, String [] parametrosPrepared){
		Cursor cursor = bd.query(Contrato.Mesa.TABLA, null, condicion, parametrosPrepared, null, 
				null, Contrato.Mesa.NOMBRE);
		return cursor;
	}
	
	public int getNumRow(){
		Cursor c=getCursor();
		return c.getCount();
	}
	
	public long insert(Mesa obj) {
		ContentValues valores = new ContentValues();
		valores.put(Contrato.Mesa.IDMESA, obj.getId());
		valores.put(Contrato.Mesa.NOMBRE, obj.getNombre());
		long id = bd.insert(Contrato.Mesa.TABLA, null, valores);
		return id;
	}

	public void open() {
		bd = abd.getWritableDatabase();
	}
	
	public void openRead() {
		bd = abd.getReadableDatabase();
	}

}