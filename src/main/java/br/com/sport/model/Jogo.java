package br.com.sport.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogo {
	@Id
	@GeneratedValue
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataJogo; 
    
    @OneToOne
    @JoinColumn(name="chave_do_time")
    private Time time;
   
    private String resultado;
    
    
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
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	} 
    
    
}
