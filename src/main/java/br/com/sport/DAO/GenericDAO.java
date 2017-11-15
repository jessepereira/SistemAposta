package br.com.sport.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import br.com.sport.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.internal.SessionFactoryImpl;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<E> {

	protected EntityManager entityManager;

	private Class<E> entity;

	public GenericDAO(Class<E> entity) {
		entityManager = new JPAUtil().getEntity();
		this.entity = entity;

	}

	/**
	 * metodo, que realiza a persistencia no banco de dados baseado na entitade
	 * informada
	 *
	 * @param entity
	 */
	@Transactional
	public void persist(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();

	}

	@Transactional
	public void merge(E entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();

	}

	/**
	 * remove do banco o dados passados pelo objeto. a coluna especifica.
	 *
	 * @param entity
	 */
	@Transactional
	public void remove(E entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Metodo Responsavel por encontrar no banco o resultado baseado no id da
	 * entidade informada. o id tera de ser um inteiro, tipo este declarado como
	 * inteiro,uma vez que no banco a coluna id e do tipo inteiro
	 *
	 * @param id
	 * @return
	 */
	public E findById(int id) {
		return entityManager.find(entity, id);
	}

	/**
	 * retorna todos os resultados de uma tabela, baseado na entidade informada.
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> findAll() {
		CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));

		return entityManager.createQuery(criteria).getResultList();
	}
}