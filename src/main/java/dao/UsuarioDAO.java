package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Usuario;
import util.jpa.Transactional;

public class UsuarioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager em;

    public void salvar(Usuario usuario) {
        em.merge(usuario);
    }
    
	public Usuario porId(Long codigo) {
		return em.find(Usuario.class, codigo);
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		usuario = porId(usuario.getCodigo());
		em.remove(usuario);
	}
    
    @SuppressWarnings("unchecked")
    public List<Usuario> buscarTodos() {
        return em.createQuery("from Usuario").getResultList();
    }
}