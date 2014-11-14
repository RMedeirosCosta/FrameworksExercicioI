package br.edu.infraestrutura.dao;

import javax.persistence.EntityManager;

public abstract class Dao {
    
    protected EntityManager em;
    
    protected Dao(EntityManager em) {
        this.em = em;
    }
    
}
