package com.app.persistencia;

import java.util.List;

import com.app.logica.Jugador;
import com.app.logica.Equipo;

public class ControladoraPersistencia {
	// Artista: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	JugadorJpaController jugadorJpa = new JugadorJpaController();

	// Cancion: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	// INSTANCIAR OBJETO DE CANCIONJPACONTROLER AL IGUAL QUE SE HIZO CON ARTISTA
	EquipoJpaController equipoJpa = new EquipoJpaController();
	// Este método es el que se usa desde otras capas para crear un ARTISTA
	public void crearJugador(Jugador jugador) {
		// Delegamos la tarea al controlador especializado
		jugadorJpa.crear(jugador);
	}
	
	
// AL IGUAL QUE CON ARTISTA, GENERAR EL MÉTODO “crearCancion()”
	public void crearEquipo(Equipo equipo) {
		// Delegamos la tarea al controlador especializado
		equipoJpa.crear(equipo);
	}
	
	// BUSCAR UN ARTISTA
	public Jugador buscarJugador(int id) {
	return jugadorJpa.buscarUno(id);
	}
	// BUSCAR TODOS LOS ARTISTAS
	public List<Jugador> buscarTodosJugadores() {
	return jugadorJpa.buscarTodos();
	}
	
	public List<Jugador> buscarJugadoresPorPosicion(String posicion) {
	    return jugadorJpa.buscarPorPosicion(posicion);
	}
	
	// BUSCAR UNA CANCION
	public Equipo buscarEquipo(int id) {
	return equipoJpa.buscarUno(id);
	}
	// BUSCAR TODOS LAS CANCION
	public List<Equipo> buscarTodosEquipos() {
	return equipoJpa.buscarTodos();
	}
	// ----- ARTISTA -----
	public void editarJugador(Jugador jugador) throws Exception {
		jugadorJpa.editar(jugador);
	}

	public void eliminarJugador(int id) throws Exception {
		jugadorJpa.eliminar(id);
	}

	// ----- CANCIÓN -----
	public void editarEquipo(Equipo equipo) throws Exception {
		equipoJpa.editar(equipo);
	}

	public void eliminarEquipo(int id) throws Exception {
		equipoJpa.eliminar(id);
	}
}


