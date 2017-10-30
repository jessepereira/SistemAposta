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
}
