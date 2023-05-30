package com.rngds.bd;

	import android.content.Context;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;

public class Ayudante extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "Comanda.db";
	public static final int DATABASE_VERSION = 1;
	
	public Ayudante(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="CREATE TABLE IF NOT EXISTS "+Contrato.Mesa.TABLA+" ("
			               +Contrato.Mesa._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			               +Contrato.Mesa.IDMESA+" INTEGER NOT NULL UNIQUE, "
			               +Contrato.Mesa.NOMBRE+" VARCHAR(20) NOT NULL UNIQUE)";
		db.execSQL(sql);
		sql="CREATE TABLE IF NOT EXISTS "+Contrato.Carta.TABLA+" ("
	               +Contrato.Carta._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
	               +Contrato.Carta.IDCARTA+" INTEGER NOT NULL UNIQUE, "
	               +Contrato.Carta.NOMBRE+" VARCHAR(80) NOT NULL UNIQUE, "
	               +Contrato.Carta.PRECIO+" DECIMAL(6,2) NOT NULL)";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
		onCreate(db);
	}
	
}