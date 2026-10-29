package com.app.persistencia;

import java.util.List;

import com.app.logica.Jugador;
import com.app.logica.Equipo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EquipoJpaController {
	
	// "Fábrica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qué unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que está en persistence.xml.
	public EquipoJpaController() {
		emf = Persistence.createEntityManagerFactory("actfinal");
	}

	// Método que devuelve un nuevo EntityManager (una conexión lista para trabajar).
	// Podés pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para crear (persistir) una Canción en la base de datos.
	public void crear(Equipo eq) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (pedimos un empleado).
			em = getEntityManager();

			// Iniciamos una transacción: empieza la "promesa".
			em.getTransaction().begin();

			// Guardamos el objeto en la base (pero todavía no está confirmado).
			em.persist(eq);

			// Confirmamos la transacción: ahora sí el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
	}
	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transacción porque no modifica nada en la base.
	public Equipo buscarUno(int id) {
	EntityManager em = getEntityManager();
	try {
	// find busca por clave primaria. Si no existe, devuelve null.
	return em.find(Equipo.class, id);
	} finally {
	em.close();
	}
	}
	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Equipo> buscarTodos() {
	EntityManager em = getEntityManager();
	try {
	// Consulta JPQL: "traeme todos los objetos Artista"
	return em.createQuery("SELECT a FROM Equipo a", Equipo.class)
	.getResultList();
	} finally {
	em.close();
	}
	}
	// Método para editar/actualizar una Canción en la base de datos
	public void editar(Equipo eq) throws Exception {
	    EntityManager em = null;
	    try {
	        em = getEntityManager();
	        em.getTransaction().begin();
	        eq = em.merge(eq);
	        em.getTransaction().commit();
	    } catch (Exception ex) {
	        if (buscarUno(eq.getId()) == null) {
	            throw new Exception("El equipo con ID " + eq.getId() + " ya no existe.");
	        }
	        throw ex;
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}

	// Método para eliminar una Canción por su ID
	public void eliminar(int id) throws Exception {
	    EntityManager em = null;
	    try {
	        em = getEntityManager();
	        em.getTransaction().begin();
	        Equipo eq;
	        try {
	            eq = em.getReference(Equipo.class, id);
	            eq.getId();
	        } catch (jakarta.persistence.EntityNotFoundException enfe) {
	            throw new Exception("El equipo con ID " + id + " ya no existe.", enfe);
	        }
	        em.remove(eq);
	        em.getTransaction().commit();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
}
