package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Marca;
import util.jpa.Transactional;
import dao.MarcaDAO;

public class MarcaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private MarcaDAO marcaDAO;

    @Transactional
    public void salvar(Marca marca) throws NegocioException {
        this.marcaDAO.salvar(marca);
    }
}
