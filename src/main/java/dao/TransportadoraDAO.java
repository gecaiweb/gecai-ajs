package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import modelo.Transportadora;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import servico.NegocioException;
import util.jpa.Transactional;
import dao.filtro.TransportadoraFilter;

public class TransportadoraDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Transportadora salvar(Transportadora transportadora){
		return em.merge(transportadora);
	}

	public Transportadora buscarPeloCodigo(Long codigo) {
		return em.find(Transportadora.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Transportadora> buscarTodos() {
		return em.createQuery("from Transportadora").getResultList();
	}

	@Transactional
	public void excluir(Transportadora transportadora) throws NegocioException {
		transportadora = buscarPeloCodigo(transportadora.getCodigo());
		try {
			em.remove(transportadora);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Transportadora não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Transportadora> filtrados(TransportadoraFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Transportadora.class);
		
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
