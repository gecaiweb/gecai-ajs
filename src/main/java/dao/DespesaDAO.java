package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Despesa;
import util.jpa.Transactional;

public class DespesaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Despesa despesa) {
        em.merge(despesa);
    }
    
	public Despesa porId(Long codigo) {
		return em.find(Despesa.class, codigo);
	}
	
	@Transactional
	public void excluir(Despesa despesa) {
		despesa = porId(despesa.getCodigo());
		em.remove(despesa);
	}
    
    @SuppressWarnings("unchecked")
    public List<Despesa> buscarTodos() {
        return em.createQuery("from Despesa").getResultList();
    }
}