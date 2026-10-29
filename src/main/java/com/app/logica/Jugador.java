package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Jugador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Basic
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private String posicion;
	private int edad;
	private int Dorsal;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	public Jugador() {
	}

	public Jugador(int id, String nombre, String apellido, String nacionalidad, String posicion, int edad,
			int Dorsal, Date fechaNacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.edad = edad;
		this.Dorsal = Dorsal;
		this.fechaNacimiento = fechaNacimiento;
	}

	// getters/setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getDorsal() {
		return Dorsal;
	}

	public void setDorsal(int Dorsal) {
		this.Dorsal = Dorsal;
	}

	// NUEVOS: Getters y Setters para fechaNacimiento
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "JUGADOR: \n" + "ID: " + getId() + "\nNombre: " + getNombre() + "\nApellido: " + getApellido() 
				+ "\nEdad: " + getEdad() + "\nNacionalidad: " + getNacionalidad() + "\nPosicion: " + getPosicion()
				+ "\nDorsal: " + getDorsal();
	}
}
