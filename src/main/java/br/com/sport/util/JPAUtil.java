package br.com.sport.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {

  private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("sport");

  @Produces @RequestScoped
  public EntityManager getEntity() {
	return factory.createEntityManager();  
  }

  public void finaliza(@Disposes EntityManager manager){
      manager.close();
  }
}
