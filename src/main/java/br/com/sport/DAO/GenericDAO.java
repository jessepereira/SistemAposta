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
		entityManager = new JPAUtil().getEntity();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Transactional
	public void merge(E entity) {
		entityManager = new JPAUtil().getEntity();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	/**
	 * remove do banco o dados passados pelo objeto. a coluna especifica.
	 *
	 * @param entity
	 */
	@Transactional
	public void remove(E entity) {
		entityManager = new JPAUtil().getEntity();
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
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
		entityManager = new JPAUtil().getEntity();
		E e = entityManager.find(entity, id);
		entityManager.close();
		return e;
	}

	/**
	 * retorna todos os resultados de uma tabela, baseado na entidade informada.
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> findAll() {
		entityManager = new JPAUtil().getEntity();
		CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		List<E> all = entityManager.createQuery(criteria).getResultList();
		entityManager.close();
		return all;
	}
}