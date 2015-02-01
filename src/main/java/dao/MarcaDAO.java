package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Marca;
import util.jpa.Transactional;

public class MarcaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Marca marca) {
        em.merge(marca);
    }
    
	public Marca porId(Long codigo) {
		return em.find(Marca.class, codigo);
	}
	
	@Transactional
	public void excluir(Marca marca) {
		marca = porId(marca.getCodigo());
		em.remove(marca);
	}
    
    @SuppressWarnings("unchecked")
    public List<Marca> buscarTodos() {
        return em.createQuery("from Marca").getResultList();
    }
}