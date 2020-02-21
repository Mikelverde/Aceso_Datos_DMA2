package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Contacto;

public class GestionContactos {
	// método que permite obtener el objeto EntityManager
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("02_ejercicio_practico_1");
		return factory.createEntityManager();
	}

	public void altaContacto(String nombre, String email, int telefono) {
		// CREAMOS UN NUEVO CONSTRUCTOR EN LA CLASE CONTACTO
		Contacto c = new Contacto(email, nombre, telefono);
		EntityManager em = getEntityManager();
		// la operación la incluimos en una transacción
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c);
		tx.commit();
	}

	public void altaContacto(Contacto c) {
		EntityManager em = getEntityManager();
		// la operación la incluimos en una transacción
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c);
		tx.commit();
	}

	public List<Contacto> recuperarContactos() {
		EntityManager em = getEntityManager();
		/*
		 * Query qr=em.createQuery("Select c from Contacto c"); return
		 * (List<Contacto>)qr.getResultList();
		 */
		TypedQuery<Contacto> qr = em.createQuery("Select c from Contacto c", Contacto.class);
		return qr.getResultList();
	}

	public Contacto buscarContacto(String email) {
		EntityManager em = getEntityManager();
		String jpql = "Select c from Contacto c where c.email=?1";
		TypedQuery<Contacto> qr = em.createQuery(jpql, Contacto.class);
		qr.setParameter(1, email);
		// return qr.getSingleResult();
		return qr.getResultList().get(0);
	}

	public void eliminarContacto(int idContacto) {
		EntityManager em = getEntityManager();
		Contacto c = em.find(Contacto.class, idContacto);
		EntityTransaction tx = em.getTransaction();
		// si el contacto existe lo eliminamos
		tx.begin();
		if (c != null) {
			em.remove(c);
		}
		tx.commit();
	}
	/*
	 * public void eliminarContactoPorEmail(String email){ EntityManager
	 * em=getEntityManager(); Query qr
	 * =em.CreateNamedQuery("Contacto.deleteByEmail"); EntityTransaction tx =
	 * em.getTransaction(); tx.begin(); qr.executeUpdate();
	 * 
	 * }
	 */

}
