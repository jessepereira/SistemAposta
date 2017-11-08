package br.com.sport.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resultado {

    @Id
    @SequenceGenerator(name = "resultado_seq",
            sequenceName = "resultado_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "resultado_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Cupom cupom;

    @OneToMany(targetEntity = Escolha.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Escolha> escolhas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof Resultado)) return false;

        Resultado resultado = (Resultado) o;

        if (id != null ? !id.equals(resultado.id) : resultado.id != null) return false;
        if (cupom != null ? !cupom.equals(resultado.cupom) : resultado.cupom != null) return false;
        return escolhas != null ? escolhas.equals(resultado.escolhas) : resultado.escolhas == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cupom != null ? cupom.hashCode() : 0);
        result = 31 * result + (escolhas != null ? escolhas.hashCode() : 0);
        return result;
    }
}
