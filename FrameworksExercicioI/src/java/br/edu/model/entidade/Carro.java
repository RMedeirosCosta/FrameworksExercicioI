package br.edu.model.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Carro extends Entidade {
    @SequenceGenerator(name="carroGenerator",
                       sequenceName="carro_seq",
                       initialValue=-1,
                       allocationSize=1)
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
                    generator="carroGenerator")    
    @Column(name="cod_carro", 
            nullable=false, 
            unique=true)    
    private Long codigo;
    
    @Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_proprietario", nullable=false)
    private Proprietario proprietario;
    
    @Column(unique=true, nullable=false)
    private String placa;

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
}
