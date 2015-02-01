package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Funcionario;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import servico.NegocioException;
import util.jpa.Transactional;
import dao.filtro.FuncionarioFilter;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Funcionario salvar(Funcionario funcionario){
		return em.merge(funcionario);
	}

	public Funcionario buscarPeloCodigo(Long codigo) {
		return em.find(Funcionario.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos() {
		return em.createQuery("from Funcionario").getResultList();
	}

	@Transactional
	public void excluir(Funcionario funcionario) throws NegocioException {
		funcionario = buscarPeloCodigo(funcionario.getCodigo());
		try {
			em.remove(funcionario);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionário não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrados(FuncionarioFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Funcionario.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getCpf())) {
			criteria.add(Restrictions.eq("documentoReceitaFederal", filtro.getCpf()));
		}
		if (StringUtils.isNotBlank(filtro.getRg())) {
			criteria.add(Restrictions.eq("registroEstadual", filtro.getRg()));
		}
		if (filtro.getStatusFuncionarios() != null && filtro.getStatusFuncionarios().length > 0) {
			criteria.add(Restrictions.in("statusFuncionario", filtro.getStatusFuncionarios()));
		}
		
		return criteria.addOrder(Order.asc("codigo")).list();
	}

}
