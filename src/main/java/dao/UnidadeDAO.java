package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Unidade;
import util.jpa.Transactional;

public class UnidadeDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Unidade unidade) {
        em.merge(unidade);
    }
    
	public Unidade porId(Long codigo) {
		return em.find(Unidade.class, codigo);
	}
	
	@Transactional
	public void excluir(Unidade unidade) {
		unidade = porId(unidade.getCodigo());
		em.remove(unidade);
	}
    
    @SuppressWarnings("unchecked")
    public List<Unidade> buscarTodos() {
        return em.createQuery("from Unidade").getResultList();
    }
}