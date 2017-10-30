package br.com.sport.model;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Jogo {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataJogo;
}
