package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Cliente;
import modelo.TipoPessoa;
import util.jpa.Transactional;
import dao.ClienteDAO;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;
	
	@Transactional
	public Cliente salvar(Cliente cliente) throws NegocioException {
		if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
			throw new NegocioException("O nome do cliente é obrigatório.");
		}
		
		if (cliente.getTipoPessoa() == null ){
			cliente.setTipoPessoa(TipoPessoa.F);
		}

		cliente = this.clienteDAO.salvar(cliente);

		return cliente;
	}

}
