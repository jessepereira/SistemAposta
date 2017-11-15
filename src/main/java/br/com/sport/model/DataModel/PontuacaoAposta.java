package br.com.sport.model.DataModel;

import java.io.Serializable;

public class PontuacaoAposta implements Serializable {

    private static final long serialVersionUID = -127492937529694564L;

    private String nomeApostador;

    private int acertos;

    private int pontuacao;


    public String getNomeApostador() {
        return nomeApostador;
    }

    public void setNomeApostador(String nomeApostador) {
        this.nomeApostador = nomeApostador;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontuacaoAposta)) return false;

        PontuacaoAposta that = (PontuacaoAposta) o;

        if (acertos != that.acertos) return false;
        if (pontuacao != that.pontuacao) return false;
        return nomeApostador != null ? nomeApostador.equals(that.nomeApostador) : that.nomeApostador == null;
    }

    @Override
    public int hashCode() {
        int result = nomeApostador != null ? nomeApostador.hashCode() : 0;
        result = 31 * result + acertos;
        result = 31 * result + pontuacao;
        return result;
    }
}
