package br.edu.controlador.jsf;

import br.edu.controlador.repositorio.CarroRepositorio;
import br.edu.infraestrutura.JpaUtil;
import br.edu.infraestrutura.dao.CarroDao;
import br.edu.infraestrutura.dao.ProprietarioDao;
import br.edu.model.entidade.Carro;
import br.edu.model.entidade.Proprietario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarroBean {

    private Carro carroCorrente;
    private CarroRepositorio repositorio;
    private List<Carro> carros;
    private Long codigoProprietario;
    
    public CarroBean() {
        carroCorrente = new Carro();
        repositorio = new CarroRepositorio() {

            @Override
            public List<Carro> getTodos() {
                /** A primeira vez que é chamado invoca todos do banco, a partir
                     dai pega o que tá em memória. */
                carros = getTodosCarros();
                repositorio = new CarroRepositorio() {

                    @Override
                    public List<Carro> getTodos() {
                        return carros;
                    }
                };
                        
                return carros;
            }
        };
    }        

    public Long getCodigoProprietario() {
        return codigoProprietario;
    }

    public void setCodigoProprietario(Long codigoProprietario) {
        this.codigoProprietario = codigoProprietario;
    }
    

    public Carro getCarroCorrente() {
        return carroCorrente;
    }

    public void setCarroCorrente(Carro carroCorrente) {
        this.carroCorrente = carroCorrente;
    }

    private List<Carro> getTodosCarros() {
        CarroDao dao = new CarroDao(JpaUtil.getEntityManager());
        return dao.get();
    };
    
    public List<Carro> getCarros() {
        return repositorio.getTodos();
    }
    
    public void salvar() {
        ProprietarioDao daoProprietario = 
                new ProprietarioDao(JpaUtil.getEntityManager());
        CarroDao dao = new CarroDao(JpaUtil.getEntityManager());
        Proprietario proprietario = daoProprietario.get(codigoProprietario);
        carroCorrente.setProprietario(proprietario);
        dao.salvar(carroCorrente);
        carroCorrente = new Carro();
        recarregarCarros();
    }
    
    public void apagar(Carro carro) {
        CarroDao dao = new CarroDao(JpaUtil.getEntityManager());
        dao.apagar(carro);
        carroCorrente = new Carro();
        recarregarCarros();
    }
    
    public void recarregarCarros() {
        carros = getTodosCarros();
    }
    
}
