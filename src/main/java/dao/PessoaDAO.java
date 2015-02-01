package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Pessoa;
import servico.NegocioException;
import util.jpa.Transactional;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Pessoa salvar(Pessoa pessoa){
		return em.merge(pessoa);
	}

	public Pessoa buscarPeloCodigo(Long codigo) {
		return em.find(Pessoa.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarTodos() {
		return em.createQuery("from Pessoa").getResultList();
	}

	@Transactional
	public void excluir(Pessoa pessoa) throws NegocioException {
		pessoa = buscarPeloCodigo(pessoa.getCodigo());
		try {
			em.remove(pessoa);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Pessoa não pode ser excluída.");
		}
	}

}
