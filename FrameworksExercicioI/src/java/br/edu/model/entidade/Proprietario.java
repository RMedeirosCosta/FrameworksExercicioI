package br.edu.model.entidade;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Proprietario extends Entidade {
    @SequenceGenerator(name="proprietarioGenerator",
                       sequenceName="proprietario_seq",
                       initialValue=-1,
                       allocationSize=1)
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
                    generator="proprietarioGenerator")        
    @Column(name="cod_proprietario", 
            nullable=false, 
            unique=true)    
    private Long codigo;
    
    @Column(nullable=false)
    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

}
