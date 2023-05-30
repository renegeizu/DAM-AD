package com.izv.basedatos.db;

	import android.content.Context;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	
public class Ayudante extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "Coches.db";
	public static final int DATABASE_VERSION = 1;
	
	public Ayudante(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql;
		sql="CREATE TABLE "+Contrato.TablaAgenda.TABLA+" ("
			               +Contrato.TablaAgenda._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			               +Contrato.TablaAgenda.MARCA+" TEXT, "
			               +Contrato.TablaAgenda.MODELO+" TEXT, "
			               +Contrato.TablaAgenda.MATRICULA+" TEXT"
			               +Contrato.TablaAgenda.ANNO+" INTEGER"
			               +Contrato.TablaAgenda.POTENCIA+" REAL"
			               +Contrato.TablaAgenda.COLOR+" TEXT)";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
	}
}