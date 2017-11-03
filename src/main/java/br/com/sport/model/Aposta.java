package br.com.sport.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aposta {

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
}
