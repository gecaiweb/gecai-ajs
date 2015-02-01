package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import util.jpa.Transactional;
import modelo.Lotacao;

public class LotacaoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Lotacao lotacao) {
        em.merge(lotacao);
    }
    
	public Lotacao porId(Long codigo) {
		return em.find(Lotacao.class, codigo);
	}
	
	@Transactional
	public void excluir(Lotacao lotacao) {
		lotacao = porId(lotacao.getCodigo());
		em.remove(lotacao);
	}
    
    @SuppressWarnings("unchecked")
    public List<Lotacao> buscarTodos() {
        return em.createQuery("from Lotacao").getResultList();
    }
}