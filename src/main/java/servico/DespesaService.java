package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Despesa;
import util.jpa.Transactional;
import dao.DespesaDAO;

public class DespesaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private DespesaDAO despesaDAO;

    @Transactional
    public void salvar(Despesa despesa) throws NegocioException {
        this.despesaDAO.salvar(despesa);
    }
}
