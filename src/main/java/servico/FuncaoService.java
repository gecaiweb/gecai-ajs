package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Funcao;
import util.jpa.Transactional;
import dao.FuncaoDAO;

public class FuncaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private FuncaoDAO funcaoDAO;

    @Transactional
    public void salvar(Funcao funcao) throws NegocioException {
        this.funcaoDAO.salvar(funcao);
    }
}
