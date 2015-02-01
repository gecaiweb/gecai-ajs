package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Subcategoria;
import util.jpa.Transactional;

public class SubcategoriaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Subcategoria subcategoria) {
        em.merge(subcategoria);
    }
    
	public Subcategoria porId(Long codigo) {
		return em.find(Subcategoria.class, codigo);
	}
	
	@Transactional
	public void excluir(Subcategoria subcategoria) {
		subcategoria = porId(subcategoria.getCodigo());
		em.remove(subcategoria);
	}
    
    @SuppressWarnings("unchecked")
    public List<Subcategoria> buscarTodos() {
        return em.createQuery("from Subcategoria").getResultList();
    }
}