package com.app.logica;

import java.util.List;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	// Desde aquí la usamos como "puente" para guardar datos.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ----- JUGADOR -----
	// Método de la capa lógica para crear un jugador.
	// Antes de guardarlo en la BD, se hacen validaciones.
	public void crearJugador(Jugador jug) throws Exception {
		// Validación: el jugador debe tener un nombre
		if (jug.getNombre() == null || jug.getNombre().isEmpty()) {
			throw new Exception("El Jugador debe tener un nombre.");
		}
		// Validación: el jugador debe tener una posicion
		if (jug.getPosicion() == null || jug.getPosicion().isEmpty()) {
			throw new Exception("El jugador debe tener una posicion.");
		}
		// Validación: la edad debe ser positiva
		if (jug.getEdad() <= 0) {
			throw new Exception("La edad del jugador debe ser mayor a 0.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		// NUEVA VALIDACIÓN: Fecha de nacimiento no nula y en el pasado/presente
		if (jug.getFechaNacimiento() == null) {
			throw new Exception("El jugador debe tener una fecha de nacimiento.");
		}

		// Si pasa todas las validaciones, recién ahí se manda a la persistencia
		controlPersis.crearJugador(jug);
	}

	// ----- EQUIPO -----
	// Método de la capa lógica para crear un equipo.
	// Se valida que tenga datos coherentes antes de persistirla.
	public void crearEquipo(Equipo eq) throws Exception {
		// Validación: el nombre no puede estar vacío
		if (eq.getNombre() == null || eq.getNombre().isEmpty()) {
			throw new Exception("El equipo debe tener un nombre.");
		}
		// Validación: el equipo debe tener una ciudad
		if (eq.getCiudad() == null || eq.getCiudad().isEmpty()) {
			throw new Exception("El equipo debe tener una ciudad.");
		}
		// Validación: la edad debe ser positiva
		if (eq.getTitulos() <= 0) {
			throw new Exception("El equipo debe tener titulos mayor a 0.");
		}

		// Si pasa todas las validaciones, se guarda en la base de datos
		controlPersis.crearEquipo(eq);

	}

	// BUSCAR UN ARTISTA por su id
	public Jugador buscarUnJugador(int id) {
		return controlPersis.buscarJugador(id);
	}

	// LISTAR TODOS LOS ARTISTAS
	public List<Jugador> listarJugadores() {
		return controlPersis.buscarTodosJugadores();
	}

	public List<Jugador> buscarJugadoresPorPosicion(String posicion) {
	    if (posicion == null || posicion.trim().isEmpty()) {
	        throw new IllegalArgumentException("Ingresá una posición.");
	    }

	    return controlPersis.buscarJugadoresPorPosicion(posicion);
	}
	
	// BUSCAR UNA CANCION por su id
	public Equipo buscarUnEquipo(int id) {
		return controlPersis.buscarEquipo(id);
	}

	// LISTAR TODAS LAS CANCIONES
	public List<Equipo> listarEquipos() {
		return controlPersis.buscarTodosEquipos();
	}

	// ----- EDICIÓN Y BAJA DE ARTISTA -----
	public void editarJugador(Jugador jug) throws Exception {
		// Validación de existencia
		if (controlPersis.buscarJugador(jug.getId()) == null) {
			throw new Exception("El jugador a editar no existe en el sistema.");
		}
		// Mismas validaciones que en el alta
		if (jug.getNombre() == null || jug.getNombre().isEmpty()) {
			throw new Exception("El jugador debe tener un nombre.");
		}
		if (jug.getPosicion() == null || jug.getPosicion().isEmpty()) {
			throw new Exception("El jugador debe tener una posicion.");
		}
		if (jug.getEdad() <= 0) {
			throw new Exception("La edad del jugador debe ser mayor a 0.");
		}
		if (jug.getFechaNacimiento() == null) {
			throw new Exception("El jugador debe tener una fecha de nacimiento.");
		}

		controlPersis.editarJugador(jug);
	}

	public void eliminarJugador(int id) throws Exception {
		// Validación de existencia
		if (controlPersis.buscarJugador(id) == null) {
			throw new Exception("El jugador a eliminar no existe.");
		}
		controlPersis.eliminarJugador(id);
	}

	// ----- EDICIÓN Y BAJA DE CANCIÓN -----

public void editarEquipo(Equipo eq) throws Exception {

    // Validar que el equipo exista
    if (controlPersis.buscarEquipo(eq.getId()) == null) {
        throw new Exception("El equipo que intentás editar no existe en el sistema.");
    }

    // Validar nombre
    if (eq.getNombre() == null || eq.getNombre().trim().isEmpty()) {
        throw new Exception("El equipo debe tener un nombre.");
    }

    // Validar ciudad
    if (eq.getCiudad() == null || eq.getCiudad().trim().isEmpty()) {
        throw new Exception("El equipo debe tener una ciudad.");
    }

    // Validar estadio
    if (eq.getEstadio() == null || eq.getEstadio().trim().isEmpty()) {
        throw new Exception("El equipo debe tener un estadio.");
    }

    // Validar títulos: se permite tener cero títulos
    if (eq.getTitulos() < 0) {
        throw new Exception("La cantidad de títulos no puede ser negativa.");
    }

    // Guardar los cambios
    controlPersis.editarEquipo(eq);
}


	public void eliminarEquipo(int id) throws Exception {
		// Validación de existencia
		if (controlPersis.buscarEquipo(id) == null) {
			throw new Exception("La equipos a eliminar no existe.");
		}
		controlPersis.eliminarEquipo(id);
	}
}
