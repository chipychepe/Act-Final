
package com.app.persistencia;

import com.app.logica.Jugador;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JugadorJpaController {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("actfinal");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Jugador jugador) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(jugador);

            em.getTransaction().commit();

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {
            em.close();
        }
    }

    public Jugador buscarUno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugador.class, id);
        } finally {
            em.close();
        }
    }

    public List<Jugador> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT j FROM Jugador j", Jugador.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Jugador> buscarPorPosicion(String posicion) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT j FROM Jugador j " +
                    "WHERE LOWER(j.posicion) = LOWER(:posicion)",
                    Jugador.class
            )
            .setParameter("posicion", posicion.trim())
            .getResultList();
        } finally {
            em.close();
        }
    }

    public void editar(Jugador jugador) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jugador);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Jugador jugador = em.find(Jugador.class, id);

            if (jugador != null) {
                em.remove(jugador);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
