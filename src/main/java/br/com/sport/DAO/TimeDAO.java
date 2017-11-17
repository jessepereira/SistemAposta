package br.com.sport.DAO;

import br.com.sport.model.Time;
import br.com.sport.util.JPAUtil;

import java.util.List;

public class TimeDAO extends GenericDAO<Time>{

	public TimeDAO() {
		super(Time.class);
	}

	public List<Time> getAllTime(){
		entityManager = new JPAUtil().getEntity();
		List<Time> times = entityManager.createQuery("from Time order by nome asc",Time.class).getResultList();
		entityManager.close();
		return times;
	}
	     
}
