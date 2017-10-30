package br.com.sport.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogo {
		
	@GeneratedValue
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataJogo; 
    private int timeCasa;
    private int timeFora;
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
	public int getTimeCasa() {
		return timeCasa;
	}
	public void setTimeCasa(int timeCasa) {
		this.timeCasa = timeCasa;
	}
	public int getTimeFora() {
		return timeFora;
	}
	public void setTimeFora(int timeFora) {
		this.timeFora = timeFora;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	} 
    
    
}
