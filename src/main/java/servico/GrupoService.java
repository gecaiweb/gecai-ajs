package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Grupo;
import util.jpa.Transactional;
import dao.GrupoDAO;

public class GrupoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private GrupoDAO grupoDAO;

    @Transactional
    public void salvar(Grupo grupo) throws NegocioException {
        this.grupoDAO.salvar(grupo);
    }
}
