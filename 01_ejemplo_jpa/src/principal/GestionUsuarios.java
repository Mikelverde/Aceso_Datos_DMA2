package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.text.StyledEditorKit.BoldAction;

import model.Usuario;


public class GestionUsuarios {
	
	// método que permite obtener el objeto EntityManager
		private EntityManager getEntityManager() {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("01_ejemplo_1");
			return factory.createEntityManager();
		}
	
	public boolean autenticar(String usuario, String pwd) {
		
		EntityManager em= getEntityManager();
		boolean res=false;
		TypedQuery<Usuario> qr=em.createNamedQuery("Usuario.findByUserAndPwd", Usuario.class);
		qr.setParameter(1, usuario);
		qr.setParameter(2, pwd);
		
		try {
			qr.getSingleResult();
		res=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
		
	}//FIN METODO AUNTENTICAR

}//FIN GESTIONUSUARIOS
