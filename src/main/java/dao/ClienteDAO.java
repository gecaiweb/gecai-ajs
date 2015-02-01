package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Cliente;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import servico.NegocioException;
import util.jpa.Transactional;
import dao.filtro.ClienteFilter;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Cliente salvar(Cliente cliente){
		return em.merge(cliente);
	}

	public Cliente buscarPeloCodigo(Long codigo) {
		return em.find(Cliente.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Transactional
	public void excluir(Cliente cliente) throws NegocioException {
		cliente = buscarPeloCodigo(cliente.getCodigo());
		try {
			em.remove(cliente);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.eq("documentoReceitaFederal", filtro.getDocumentoReceitaFederal()));
		}
		if (StringUtils.isNotBlank(filtro.getRegistroGeral())) {
			criteria.add(Restrictions.eq("registroEstadual", filtro.getDocumentoReceitaFederal()));
		}
		if (filtro.getStatus() != null && filtro.getStatus().length > 0) {
			criteria.add(Restrictions.in("status", filtro.getStatus()));
		}
		
		return criteria.addOrder(Order.asc("codigo")).list();
	}

}
