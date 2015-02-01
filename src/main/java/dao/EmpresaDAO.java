package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Empresa;
import servico.NegocioException;
import util.jpa.Transactional;

public class EmpresaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Empresa salvar(Empresa empresa){
		return em.merge(empresa);
	}

	public Empresa buscarPeloCodigo(Long codigo) {
		return em.find(Empresa.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> buscarTodas() {
		return em.createQuery("from Empresa").getResultList();
	}

	@Transactional
	public void excluir(Empresa empresa) throws NegocioException {
		empresa = buscarPeloCodigo(empresa.getCodigo());
		try {
			em.remove(empresa);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Empresa não pode ser excluída.");
		}
	}
}
