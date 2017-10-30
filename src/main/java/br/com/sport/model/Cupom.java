package br.com.sport.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cupom {
    
	@Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataJogo;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDataJogo() {
		return dataJogo;
	}
	public void setDataJogo(Calendar dataJogo) {
		this.dataJogo = dataJogo;
	} 
    
    
    
}
