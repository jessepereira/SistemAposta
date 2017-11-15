package br.com.sport.DAO;

import br.com.sport.model.Time;

import java.util.List;

public class TimeDAO extends GenericDAO<Time>{

	public TimeDAO() {
		super(Time.class);
	}

	public List<Time> getAllTime(){
		return entityManager.createQuery("from Time order by nome asc",Time.class).getResultList();
	}
	     
}
