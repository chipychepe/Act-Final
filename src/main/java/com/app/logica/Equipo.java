
package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Equipo implements Serializable {

   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


	@Basic
	 private String nombre;
    private String ciudad;
    private String estadio;
    private int titulos;

    public Equipo() { }
    
    

    public Equipo(int id, String nombre, String ciudad, String estadio, int titulos) {
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.estadio = estadio;
		this.titulos = titulos;
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



	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public String getEstadio() {
		return estadio;
	}



	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}



	public int getTitulos() {
		return titulos;
	}



	public void setTitulos(int titulos) {
		this.titulos = titulos;
	}
	
    @Override
    public String toString() {
        return "EQUIPO: \n" +"ID: "+ getId() + "\nNombre: " +  getNombre() +"\nCiudad: " + getCiudad()
        		+ "\nEstadio: " +  getEstadio() + "\nTitulos: " + getTitulos();
    }

}
