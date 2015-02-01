package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Categoria;
import util.jpa.Transactional;
import dao.CategoriaDAO;

public class CategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Transactional
	public void salvar(Categoria categoria) throws NegocioException {
		this.categoriaDAO.salvar(categoria);
	}
}
