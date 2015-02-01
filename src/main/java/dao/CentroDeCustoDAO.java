package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.CentroDeCusto;
import util.jpa.Transactional;

public class CentroDeCustoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(CentroDeCusto centroDeCusto) {
        em.merge(centroDeCusto);
    }
    
	public CentroDeCusto porId(Long codigo) {
		return em.find(CentroDeCusto.class, codigo);
	}
	
	@Transactional
	public void excluir(CentroDeCusto centroDeCusto) {
		centroDeCusto = porId(centroDeCusto.getCodigo());
		em.remove(centroDeCusto);
	}
    
    @SuppressWarnings("unchecked")
    public List<CentroDeCusto> buscarTodos() {
        return em.createQuery("from CentroDeCusto").getResultList();
    }
}