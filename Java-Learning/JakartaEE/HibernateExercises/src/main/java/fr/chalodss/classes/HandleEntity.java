package fr.chalodss.classes;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Charles T.
 * 
 */
public final class HandleEntity {

  private EntityManagerFactory emf;
  private EntityManager        em;

  public HandleEntity(String pu) {
    this.emf = Persistence.createEntityManagerFactory(pu);
    this.em  = this.emf.createEntityManager();
  }

  public List<Player> listAll(String table) {
    return em.createQuery("SELECT p FROM " + table + " p", Player.class).getResultList();
  }

  public void insert(Object o) {
    em.getTransaction().begin();
    em.persist(o);
    em.getTransaction().commit();
  }

  public void close() {
    emf.close();
    em.close();
  }

}
