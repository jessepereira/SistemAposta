package br.com.sport.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aposta {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String apostador;

    @OneToMany(targetEntity = Jogo.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jogo> jogos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApostador() {
        return apostador;
    }

    public void setApostador(String apostador) {
        this.apostador = apostador;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
}
