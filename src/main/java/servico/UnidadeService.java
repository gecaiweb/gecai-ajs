package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Unidade;
import util.jpa.Transactional;
import dao.UnidadeDAO;

public class UnidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private UnidadeDAO unidadeDAO;

    @Transactional
    public void salvar(Unidade unidade) throws NegocioException {
        this.unidadeDAO.salvar(unidade);
    }
}
