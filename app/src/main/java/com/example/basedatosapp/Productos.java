package com.example.basedatosapp;

public class Productos {

	int Id;
	String nombre, Tipo;
	//int Stock;
	//Double Precio;
	
	public Productos(int id, String nombre, String tipo) {
		
		Id = id;
		this.nombre = nombre;
		Tipo = tipo;
		//Stock = stock;
		//Precio = precio;
	}

	
	public Productos() {
		Id = 0;
		this.nombre = null;
		Tipo = null;
		//Stock = 0;
		//Precio = 0.00;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}

}
