package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Banco;
import util.jpa.Transactional;
import dao.BancoDAO;

public class BancoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private BancoDAO bancoDAO;

    @Transactional
    public void salvar(Banco banco) throws NegocioException {
        this.bancoDAO.salvar(banco);
    }
}
