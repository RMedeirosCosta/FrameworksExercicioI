package br.edu.model.entidade;

public abstract class Entidade {
    
    protected abstract Long getCodigo();
    
    public boolean isNew() {
        try {
            return (getCodigo()==0);
        } catch (NullPointerException ex) {
            return true;
        }
    }    
}
