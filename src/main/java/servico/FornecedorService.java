package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Fornecedor;
import modelo.TipoPessoa;
import util.jpa.Transactional;
import dao.FornecedorDAO;

public class FornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) throws NegocioException {
		if (fornecedor.getNome() == null || fornecedor.getNome().trim().equals("")) {
			throw new NegocioException("O nome do fornecedor é obrigatório.");
		}
		
		if (fornecedor.getTipoPessoa() == null ){
			fornecedor.setTipoPessoa(TipoPessoa.J);
		}

		fornecedor = this.fornecedorDAO.salvar(fornecedor);

		return fornecedor;
	}

}
