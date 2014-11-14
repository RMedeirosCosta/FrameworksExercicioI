package br.edu.infraestrutura.dao;

import br.edu.model.entidade.Proprietario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProprietarioDao extends Dao {

    public ProprietarioDao(EntityManager em) {
        super(em);
    }

    public List<Proprietario> get() {
        Query query = em.createQuery("select p from Proprietario p",
                Proprietario.class);
        List<Proprietario> proprietarios = query.getResultList();
        return proprietarios;
    }

    public void salvar(Proprietario proprietario) {
        em.getTransaction().begin();

        try {
            if (!proprietario.isNew())
                proprietario = em.merge(proprietario);
            
            em.persist(proprietario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public Proprietario get(Long codigo) {
        return em.find(Proprietario.class, codigo);
    }

    public void apagar(Proprietario proprietario) {
        em.getTransaction().begin();
        try {
            Proprietario managed = em.getReference(Proprietario.class,
                    proprietario.getCodigo());
            em.remove(managed);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
