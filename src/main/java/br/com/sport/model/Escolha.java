package br.com.sport.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Escolha implements Serializable {

    private static final long serialVersionUID = 3462578845242L;

    @Id
    @SequenceGenerator(name = "escolha_seq",
            sequenceName = "escolha_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "escolha_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "jogo_id", referencedColumnName = "id")
    private Jogo jogo;

    private String resposta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Escolha)) return false;

        Escolha escolha = (Escolha) o;

        if (id != null ? !id.equals(escolha.id) : escolha.id != null) return false;
        if (jogo != null ? !jogo.equals(escolha.jogo) : escolha.jogo != null) return false;
        return resposta != null ? resposta.equals(escolha.resposta) : escolha.resposta == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jogo != null ? jogo.hashCode() : 0);
        result = 31 * result + (resposta != null ? resposta.hashCode() : 0);
        return result;
    }
}
