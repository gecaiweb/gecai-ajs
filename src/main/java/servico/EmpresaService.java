package servico;

import java.io.Serializable;

import javax.inject.Inject;

import modelo.Empresa;
import util.jpa.Transactional;
import dao.EmpresaDAO;

public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private EmpresaDAO empresaDAO;

    @Transactional
    public void salvar(Empresa empresa) throws NegocioException {
        this.empresaDAO.salvar(empresa);
    }
}
