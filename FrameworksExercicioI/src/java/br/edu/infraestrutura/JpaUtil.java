package br.edu.infraestrutura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("default");
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
}
