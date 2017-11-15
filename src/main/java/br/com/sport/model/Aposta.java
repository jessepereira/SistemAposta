package br.com.sport.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Aposta implements Serializable {

    public static final long serialVersionUID = -4907638723412L;

    @Id
    @SequenceGenerator(name = "aposta_seq",
            sequenceName = "aposta_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "aposta_seq")
    private int id;

    @Column
    private String apostador;

    @Column
    @GeneratedValue
    private int apostadorCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn
    private Cupom cupom;

    @OneToMany(targetEntity = Escolha.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Escolha> escolhas;

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

    public int getApostadorCode() {
        return apostadorCode;
    }

    public void setApostadorCode(int apostadorCode) {
        this.apostadorCode = apostadorCode;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public List<Escolha> getEscolhas() {
        return escolhas;
    }

    public void setEscolhas(List<Escolha> escolhas) {
        this.escolhas = escolhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aposta)) return false;

        Aposta aposta = (Aposta) o;

        if (id != aposta.id) return false;
        if (apostadorCode != aposta.apostadorCode) return false;
        if (apostador != null ? !apostador.equals(aposta.apostador) : aposta.apostador != null) return false;
        if (cupom != null ? !cupom.equals(aposta.cupom) : aposta.cupom != null) return false;
        return escolhas != null ? escolhas.equals(aposta.escolhas) : aposta.escolhas == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (apostador != null ? apostador.hashCode() : 0);
        result = 31 * result + apostadorCode;
        result = 31 * result + (cupom != null ? cupom.hashCode() : 0);
        result = 31 * result + (escolhas != null ? escolhas.hashCode() : 0);
        return result;
    }
}
