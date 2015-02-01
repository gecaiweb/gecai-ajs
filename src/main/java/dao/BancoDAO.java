package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Banco;
import util.jpa.Transactional;

public class BancoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Banco banco) {
        em.merge(banco);
    }
    
	public Banco porId(Long codigo) {
		return em.find(Banco.class, codigo);
	}
	
	@Transactional
	public void excluir(Banco banco) {
		banco = porId(banco.getCodigo());
		em.remove(banco);
	}
    
    @SuppressWarnings("unchecked")
    public List<Banco> buscarTodos() {
        return em.createQuery("from Banco").getResultList();
    }
}