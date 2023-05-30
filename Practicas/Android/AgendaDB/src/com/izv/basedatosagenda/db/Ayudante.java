package com.izv.basedatosagenda.db;

	import android.content.Context;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "agenda.db";
	public static final int DATABASE_VERSION = 4;
	
	public Ayudante(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Tipos de datos de SQLite: Text, Integer, Float
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql;
		sql="CREATE TABLE "+Contrato.TablaAgenda.TABLA+" ("
			               +Contrato.TablaAgenda._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			               +Contrato.TablaAgenda.NOMBRE+" text, "
			               +Contrato.TablaAgenda.TELEFONO+" text, "
			               +Contrato.TablaAgenda.CORREO+" text)";
		Log.v("SQL", sql);
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
		//Para volcar datos de una tabla a otra
		//INSERT INTO TablaNueva SELECT * FROM TablaVieja
		//con oldVersion podemos comparar las versiones instaladas con la actual
		String sql="drop table if exists "+Contrato.TablaAgenda.TABLA;
		db.execSQL(sql);
		onCreate(db);
	}
}