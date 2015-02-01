package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Pessoa;
import modelo.TipoPessoa;
import util.jpa.Transactional;
import dao.PessoaDAO;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDAO pessoaDAO;
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) throws NegocioException {
		if (pessoa.getNome() == null || pessoa.getNome().trim().equals("")) {
			throw new NegocioException("O nome do funcionário é obrigatório.");
		}
		if (pessoa.getTipoPessoa() == null ){
			pessoa.setTipoPessoa(TipoPessoa.F);
		}
		pessoa = this.pessoaDAO.salvar(pessoa);
		return pessoa;
	}
}
