package br.com.sport.DAO;

import br.com.sport.model.Time;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TimeDAO extends GenericDAO<Time>{

	public TimeDAO() {
		super(Time.class);
	}
	@Inject
	private EntityManager manager;
	public List<Time> getAllTime(){
		manager = new JPAUtil().getEntity();
		List<Time> times = manager.createQuery("from Time order by nome asc",Time.class).getResultList();

		return times;
	}
	     
}
