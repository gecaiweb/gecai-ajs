package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.CentroDeCusto;
import util.jpa.Transactional;
import dao.CentroDeCustoDAO;

public class CentroDeCustoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private CentroDeCustoDAO centroDeCustoDAO;

    @Transactional
    public void salvar(CentroDeCusto centroDeCusto) throws NegocioException {
        this.centroDeCustoDAO.salvar(centroDeCusto);
    }
}
