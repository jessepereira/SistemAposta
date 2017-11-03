package br.com.sport.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cupom {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nomeCupom;

    @OneToMany(targetEntity = Jogo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Jogo> jogos = new ArrayList<>();

    @Column
    private boolean resultado = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cupom)) return false;

        Cupom cupom = (Cupom) o;

        if (resultado != cupom.resultado) return false;
        if (id != null ? !id.equals(cupom.id) : cupom.id != null) return false;
        if (nomeCupom != null ? !nomeCupom.equals(cupom.nomeCupom) : cupom.nomeCupom != null) return false;
        return jogos != null ? jogos.equals(cupom.jogos) : cupom.jogos == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nomeCupom != null ? nomeCupom.hashCode() : 0);
        result = 31 * result + (jogos != null ? jogos.hashCode() : 0);
        result = 31 * result + (resultado ? 1 : 0);
        return result;
    }
}
