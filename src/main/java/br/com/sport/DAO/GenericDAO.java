package br.com.sport.DAO;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import br.com.sport.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.internal.SessionFactoryImpl;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<E> {


	@Inject private EntityManager manager;

	private Class<E> entity;
    @Inject
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

		manager.getTransaction().begin();
		manager.persist(entity);
		manager.flush();
		manager.getTransaction().commit();


	}

	@Transactional
	public void merge(E entity) {

		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();


	}

	/**
	 * remove do banco o dados passados pelo objeto. a coluna especifica.
	 *
	 * @param entity
	 */
	@Transactional
	public void remove(E entity) {

		manager.getTransaction().begin();
		entity = manager.merge(entity);
		manager.remove(entity);
		manager.getTransaction().commit();

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

		E e = manager.find(entity, id);

		return e;
	}

	/**
	 * retorna todos os resultados de uma tabela, baseado na entidade informada.
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> findAll() {

		CriteriaQuery criteria = manager.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		List<E> all = manager.createQuery(criteria).getResultList();
		manager.close();
		return all;
	}
}