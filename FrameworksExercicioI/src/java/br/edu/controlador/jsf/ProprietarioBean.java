package br.edu.controlador.jsf;

import br.edu.controlador.repositorio.ProprietarioRepositorio;
import br.edu.infraestrutura.JpaUtil;
import br.edu.infraestrutura.dao.CarroDao;
import br.edu.infraestrutura.dao.ProprietarioDao;
import br.edu.model.entidade.Carro;
import br.edu.model.entidade.Proprietario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
public class ProprietarioBean {
        
    private Proprietario proprietarioCorrente;
    private ProprietarioRepositorio repositorio;
    private List<Proprietario> proprietarios;
    
    public ProprietarioBean() {
        proprietarioCorrente = new Proprietario();
        repositorio = new ProprietarioRepositorio() {

            @Override
            public List<Proprietario> getTodos() {
                /** A primeira vez que é chamado invoca todos do banco, a partir
                     dai pega o que tá em memória. */
                proprietarios = getTodosProprietarios();
                repositorio = new ProprietarioRepositorio() {

                    @Override
                    public List<Proprietario> getTodos() {
                        return proprietarios;
                    }
                };
                        
                return proprietarios;
            }
        };
    }        

    public Proprietario getProprietarioCorrente() {
        return proprietarioCorrente;
    }

    public void setProprietarioCorrente(Proprietario proprietarioCorrente) {
        this.proprietarioCorrente = proprietarioCorrente;
    }

    private List<Proprietario> getTodosProprietarios() {
        ProprietarioDao dao = new ProprietarioDao(JpaUtil.getEntityManager());
        return dao.get();
    };
    
    public List<Proprietario> getProprietarios() {
        return repositorio.getTodos();
    }
    
    public void salvar(CarroBean carroBean) {
        ProprietarioDao dao = new ProprietarioDao(JpaUtil.getEntityManager());
        dao.salvar(proprietarioCorrente);
        proprietarioCorrente = new Proprietario();
        recarregarProprietarios();
        carroBean.recarregarCarros();
    }
    
    public void apagar(Proprietario proprietario) {
CarroDao carroDao = new CarroDao(JpaUtil.getEntityManager());
        List<Carro> carrosDesteProprietario = carroDao.get(proprietario);
        
        if (!carrosDesteProprietario.isEmpty()) {
            FacesMessage message = 
                    new FacesMessage("Proprietário não pode ser "+
                                     "incluído porque possui carros");
            throw new ValidatorException(message);
        }        
        
        ProprietarioDao dao = new ProprietarioDao(JpaUtil.getEntityManager());
        dao.apagar(proprietario);
        proprietarioCorrente = new Proprietario();
        recarregarProprietarios();        
    }
    
    private void recarregarProprietarios() {
        proprietarios = getTodosProprietarios();
    }    
}
