package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Funcionario;
import modelo.TipoPessoa;
import util.jpa.Transactional;
import dao.FuncionarioDAO;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) throws NegocioException {
		if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
			throw new NegocioException("O nome do funcionário é obrigatório.");
		}
		
		if (funcionario.getTipoPessoa() == null ){
			funcionario.setTipoPessoa(TipoPessoa.F);
		}
		
		funcionario = this.funcionarioDAO.salvar(funcionario);

		return funcionario;
	}

}
