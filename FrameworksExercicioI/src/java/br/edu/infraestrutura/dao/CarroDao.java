package br.edu.infraestrutura.dao;

import br.edu.model.entidade.Carro;
import br.edu.model.entidade.Proprietario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CarroDao extends Dao {

    public CarroDao(EntityManager em) {
        super(em);
    }
    
    public List<Carro> get() {
        Query query = em.createQuery("select c from Carro c", Carro.class);
        List<Carro> carros = query.getResultList();
        return carros;
    }
    
    public void salvar(Carro carro) {
        em.getTransaction().begin();
        try {
            if (!carro.isNew())
                carro = em.merge(carro);
            
            em.persist(carro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    
    public List<Carro> get(Proprietario proprietario) {
        if (proprietario == null)        
            return new ArrayList<>();
        
        List<Carro> carros = get();
        
        List<Carro> carrosDesteProprietario = new ArrayList<>();
        
        for (Carro carro : carros) {
            if (carro.getProprietario().getCodigo()
                    .equals(proprietario.getCodigo()))
                carrosDesteProprietario.add(carro);            
        }
        
        return carrosDesteProprietario;
    }
    
    public void apagar(Carro carro) {
        em.getTransaction().begin();
        try {
            Carro managed = em.getReference(Carro.class,
                                                   carro.getCodigo());
            em.remove(managed);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }        
    
}
