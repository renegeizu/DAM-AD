package com.izv.basedatos.pojo;

public class Coche {
	
	private String Marca, Modelo, Matricula, Color;
	private long Id;
	private Integer Anno;
	private float Potencia;
	
	public Coche() {
		
	}

	public Coche(long id, String marca, String modelo, String matricula, String color,
			Integer anno, float potencia) {
		super();
		Marca = marca;
		Modelo = modelo;
		Matricula = matricula;
		Color = color;
		Id = id;
		Anno = anno;
		Potencia = potencia;
	}
	
	
	
}
