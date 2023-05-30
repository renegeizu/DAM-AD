package com.izv.basedatos.db;

	import android.provider.BaseColumns;

public class Contrato {
	
	private Contrato(){
	}

	public static abstract class TablaAgenda implements BaseColumns{
				
		public static final String TABLA = "coches";
		//public static final String ID = "id";
		public static final String MARCA = "marca";
		public static final String MODELO = "modelo";
		public static final String MATRICULA="matricula";
		public static final int ANNO=0;
		public static final float POTENCIA=0;
		public static final String COLOR="color";
		
	}
	
}