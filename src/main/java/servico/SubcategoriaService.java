package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Subcategoria;
import util.jpa.Transactional;
import dao.SubcategoriaDAO;

public class SubcategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private SubcategoriaDAO subcategoriaDAO;

    @Transactional
    public void salvar(Subcategoria subcategoria) throws NegocioException {
        this.subcategoriaDAO.salvar(subcategoria);
    }
}
