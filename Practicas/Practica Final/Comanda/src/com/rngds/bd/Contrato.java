package com.rngds.bd;

	import android.provider.BaseColumns;

public class Contrato {

	private Contrato (){
	}
	
	public static abstract class Mesa implements BaseColumns{
		public static final String TABLA = "Mesa";
		public static final String IDMESA = "idMesa";
		public static final String NOMBRE = "Nombre";
	}
	
	public static abstract class Carta implements BaseColumns{
		public static final String TABLA = "Carta";
		public static final String IDCARTA = "idCarta";
		public static final String NOMBRE = "Nombre";
		public static final String PRECIO= "Precio";
	}
	
}