package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Categoria;
import util.jpa.Transactional;

public class CategoriaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Categoria categoria) {
        em.merge(categoria);
    }
    
	public Categoria porId(Long codigo) {
		return em.find(Categoria.class, codigo);
	}
	
	@Transactional
	public void excluir(Categoria categoria) {
		categoria = porId(categoria.getCodigo());
		em.remove(categoria);
	}
    
    @SuppressWarnings("unchecked")
    public List<Categoria> buscarTodos() {
        return em.createQuery("from Categoria").getResultList();
    }
}