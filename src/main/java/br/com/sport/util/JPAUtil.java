package br.com.sport.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

  private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("sport");

  public EntityManager getEntity() {
	return factory.createEntityManager();  
  }

}
