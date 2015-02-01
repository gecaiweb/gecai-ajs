package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.TipoPessoa;
import modelo.Transportadora;
import util.jpa.Transactional;
import dao.TransportadoraDAO;

public class TransportadoraService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TransportadoraDAO transportadoraDAO;
	
	@Transactional
	public Transportadora salvar(Transportadora transportadora) throws NegocioException {
		if (transportadora.getNome() == null || transportadora.getNome().trim().equals("")) {
			throw new NegocioException("O nome do transportadora é obrigatório.");
		}
		
		if (transportadora.getTipoPessoa() == null ){
			transportadora.setTipoPessoa(TipoPessoa.F);
		}

		transportadora = this.transportadoraDAO.salvar(transportadora);

		return transportadora;
	}

}
