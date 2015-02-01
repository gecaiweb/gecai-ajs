package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Lotacao;
import util.jpa.Transactional;
import dao.LotacaoDAO;

public class LotacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private LotacaoDAO lotacaoDAO;

    @Transactional
    public void salvar(Lotacao lotacao) throws NegocioException {
        this.lotacaoDAO.salvar(lotacao);
    }
}
