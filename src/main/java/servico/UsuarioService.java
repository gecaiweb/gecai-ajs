package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Usuario;
import util.jpa.Transactional;
import dao.UsuarioDAO;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private UsuarioDAO usuarioDAO;

    @Transactional
    public void salvar(Usuario usuario) throws NegocioException {
        this.usuarioDAO.salvar(usuario);
    }
}
