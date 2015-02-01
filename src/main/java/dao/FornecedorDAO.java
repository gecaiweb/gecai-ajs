package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Fornecedor;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import servico.NegocioException;
import util.jpa.Transactional;
import dao.filtro.FornecedorFilter;

public class FornecedorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Fornecedor salvar(Fornecedor fornecedor){
		return em.merge(fornecedor);
	}

	public Fornecedor buscarPeloCodigo(Long codigo) {
		return em.find(Fornecedor.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscarTodos() {
		return em.createQuery("from Fornecedor").getResultList();
	}

	@Transactional
	public void excluir(Fornecedor fornecedor) throws NegocioException {
		fornecedor = buscarPeloCodigo(fornecedor.getCodigo());
		try {
			em.remove(fornecedor);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Fornecedor não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> filtrados(FornecedorFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.eq("documentoReceitaFederal", filtro.getDocumentoReceitaFederal()));
		}
		if (StringUtils.isNotBlank(filtro.getRegistroGeral())) {
			criteria.add(Restrictions.eq("registroGeral", filtro.getDocumentoReceitaFederal()));
		}
		if (filtro.getStatus() != null && filtro.getStatus().length > 0) {
			criteria.add(Restrictions.in("status", filtro.getStatus()));
		}
		
		return criteria.addOrder(Order.asc("codigo")).list();
	}

}
