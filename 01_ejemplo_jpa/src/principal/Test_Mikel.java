package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Contacto;

public class Test_Mikel {

	public static void main(String[] args) {
		//CREAMOSUN ENTITYMANAGER
		EntityManagerFactory fac=Persistence.createEntityManagerFactory("01_ejemplo_jpa");
		EntityManager em=fac.createEntityManager();
		//INICIAMOS LA TRANSACCION
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		//PERSISTO, CREO UNA ENTIDAD
		Contacto contacto= new Contacto();
		contacto.setIdContactos(1);
		contacto.setNombre("Mikel");
		contacto.setEmail("mikelcanfranc@gmail.com");
		contacto.setTelefono(639737507);
		em.merge(contacto);
		//ACTUALIZAMOS UNA ENTIDAD
		contacto=em.find(Contacto.class, 1);
		contacto.setEmail("modificado2@gmail.com");
		em.merge(contacto);
		//ELIMINAMOS UN CONTACTO
		contacto=em.find(Contacto.class, 1);
		em.remove(contacto);
		System.out.println("OPERACIONES REALIZADAS");
		tx.commit();
		em.close();
	}//FIN MAIN

}//FIN CLASE TEST_MIKEL
