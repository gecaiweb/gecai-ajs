package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Grupo;
import util.jpa.Transactional;

public class GrupoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;
	
    public void salvar(Grupo grupo) {
        em.merge(grupo);
    }
    
	public Grupo porId(Long codigo) {
		return em.find(Grupo.class, codigo);
	}
	
	@Transactional
	public void excluir(Grupo grupo) {
		grupo = porId(grupo.getCodigo());
		em.remove(grupo);
	}
	
    @SuppressWarnings("unchecked")
    public List<Grupo> buscarTodos() {
        return em.createQuery("from Grupo").getResultList();
    }
}