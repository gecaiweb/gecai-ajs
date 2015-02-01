package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Funcao;
import util.jpa.Transactional;

public class FuncaoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Funcao funcao) {
        em.merge(funcao);
    }
    
	public Funcao porId(Long codigo) {
		return em.find(Funcao.class, codigo);
	}
	
	@Transactional
	public void excluir(Funcao funcao) {
		funcao = porId(funcao.getCodigo());
		em.remove(funcao);
	}
    
    @SuppressWarnings("unchecked")
    public List<Funcao> buscarTodas() {
        return em.createQuery("from Funcao").getResultList();
    }
}