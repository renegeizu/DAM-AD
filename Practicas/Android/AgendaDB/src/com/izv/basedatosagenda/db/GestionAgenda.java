package com.izv.basedatosagenda.db;

	import java.util.ArrayList;
	import java.util.List;
	import com.izv.basedatosagenda.pojo.Agenda;
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;

public class GestionAgenda {

	private Ayudante abd;
	private SQLiteDatabase bd;
	
	public void close() {
		abd.close();
	}
	
	public int delete(Agenda ag) {
		return delete(ag.getId());
	}

	/*
	public int delete(Agenda ag) {
		String condicion = Contrato.TablaAgenda._ID + " = ?";
		String[] argumentos = { ag.getId() + "" };
		int cuenta = bd.delete(Contrato.TablaAgenda.TABLA, condicion, argumentos);
		return cuenta;
	}
	*/
	
	//preparedStatement - Sentencia preparada
	public int delete(long id) {
		//_ID=? -> Significa que es una preparedStatement. Es igual que Where=?
		//Se pueden encadenar varios con un and
		String condicion = Contrato.TablaAgenda._ID + " = ?";
		String[] argumentos = { id + "" };
		int cuenta = bd.delete(Contrato.TablaAgenda.TABLA, condicion, argumentos);
		return cuenta;
	}
	
	public int delete(String nombre, String telefono, String correo){
		String condicion = Contrato.TablaAgenda.NOMBRE + " = '"+nombre+"' and" 
						   + Contrato.TablaAgenda.TELEFONO + " = '"+telefono+"' and"
						   + Contrato.TablaAgenda.CORREO + " = '"+correo+"' ";
		String[] argumentos = { nombre, telefono };
		int cuenta = bd.delete(Contrato.TablaAgenda.TABLA, condicion, argumentos);
		return cuenta;
	}
	public int delete(String nombre, String telefono){
		String condicion = Contrato.TablaAgenda.NOMBRE + " = '"+nombre+"' and" 
						   + Contrato.TablaAgenda.TELEFONO + " = '"+telefono+"'";
		String[] argumentos = { nombre, telefono };
		int cuenta = bd.delete(Contrato.TablaAgenda.TABLA, condicion, argumentos);
		return cuenta;
	}

	public GestionAgenda(Context c) {
		abd = new Ayudante(c);
	}
	
	public Cursor getCursor(){
		return getCursor(null, null);
	}
	
	public Cursor getCursor(String condicion, String [] parametrosPrepared){
		//SELECT * FROM TABLA WHERE CONDICION=PARAMETROS ORDER BY Nombre, Telefono, Correo
		Cursor cursor = bd.query(Contrato.TablaAgenda.TABLA, null, condicion, parametrosPrepared, null, null, 
				Contrato.TablaAgenda.NOMBRE+", "+Contrato.TablaAgenda.TELEFONO+", "+ Contrato.TablaAgenda.CORREO);
		return cursor;
	}
	
	public Agenda getRow(Cursor c) {
		try{
			Agenda ag = new Agenda();
			ag.setId(c.getLong(0));
			ag.setNombre(c.getString(1));
			ag.setTelefono(c.getString(2));
			ag.setCorreo(c.getString(3));
			return ag;
		} catch(Exception e){
			return null;
		}
	}

	public Agenda getRow(Long id) {
		String[] proyeccion = { 
			Contrato.TablaAgenda._ID , 
			Contrato.TablaAgenda.NOMBRE,
			Contrato.TablaAgenda.TELEFONO,
			Contrato.TablaAgenda.CORREO 
		};
		String where = Contrato.TablaAgenda._ID + " = ?";
		String[] parametros = new String[] { id+"" };
		String groupby = null; String having = null;
		String orderby = Contrato.TablaAgenda.NOMBRE + " ASC";
		Cursor c = bd.query(Contrato.TablaAgenda.TABLA, proyeccion,
		where, parametros, groupby , having, orderby);
		c.moveToFirst();
		Agenda  ag = getRow(c);
		c.close();
		return ag;
	}

	public Agenda getRow(String nombre) {
		String[] parametros = new String[] { nombre };
		Cursor c = bd.rawQuery(
			"select * from " + Contrato.TablaAgenda.TABLA
			+ " where " + Contrato.TablaAgenda.NOMBRE
			+ " = ?", parametros
		);
		c.moveToFirst();
		Agenda  ag = getRow(c);
		c.close();
		return ag;
	}

	//ContentValues se usa para Insertar Datos en SQLite
	public long insert(Agenda ag) {
		ContentValues valores = new ContentValues();
		valores.put(Contrato.TablaAgenda.CORREO, ag.getCorreo());
		valores.put(Contrato.TablaAgenda.NOMBRE, ag.getNombre());
		valores.put(Contrato.TablaAgenda.TELEFONO, ag.getTelefono());
		//bd.insert() devuelve -1 si no se ha insertado
		long id = bd.insert(Contrato.TablaAgenda.TABLA, null, valores);
		return id;
	}

	public void open() {
		bd = abd.getWritableDatabase();
	}
	
	public void openRead() {
		bd = abd.getReadableDatabase();
	}
	
	public List<Agenda> select(String condicion) {
		List<Agenda> la = new ArrayList<Agenda>();
		Cursor cursor = bd.query(Contrato.TablaAgenda.TABLA, null, condicion, null, null, null, null);
		cursor.moveToFirst();
		Agenda ag;
		while (!cursor.isAfterLast()) {
			ag = getRow(cursor);
			la.add(ag);
			cursor.moveToNext();
		}
		cursor.close();
		return la;
	}

	public int update(Agenda ag) {
		ContentValues valores = new ContentValues();
		valores.put(Contrato.TablaAgenda.CORREO, ag.getCorreo());
		valores.put(Contrato.TablaAgenda.NOMBRE, ag.getNombre());
		valores.put(Contrato.TablaAgenda.TELEFONO, ag.getTelefono());
		String condicion = Contrato.TablaAgenda._ID + " = ?";
		String[] argumentos = { ag.getId() + "" };
		int cuenta = bd.update(Contrato.TablaAgenda.TABLA, valores, condicion, argumentos);
		return cuenta;
	}
	
}