package com.izv.basedatosagenda.db;

	import android.provider.BaseColumns;

public class Contrato {

	private Contrato (){
		
	}
	
	public static abstract class TablaAgenda implements BaseColumns{
		
		/* 
		 * Todas las tablas en Android tienen un primer campo que 
		 * se debe llamar _ID y que es de tipo auto incremental y
		 * va a ser la clave primaria.
		 */
		
		public static final String TABLA = "agenda";
		public static final String NOMBRE = "nombre";
		public static final String TELEFONO = "telefono";
		public static final String CORREO = "correo";
	}
	
}