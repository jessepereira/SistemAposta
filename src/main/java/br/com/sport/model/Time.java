package br.com.sport.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Time {
	
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = true)
	private String nome;
    @Column(unique = true, nullable = true)
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getId() {
		return id;
	}
	
	
}
