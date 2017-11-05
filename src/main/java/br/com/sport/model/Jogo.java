package br.com.sport.model;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Jogo {
    @Id
    @SequenceGenerator(name = "jogo_seq",
            sequenceName = "jogo_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "jogo_seq")
    private int id;

	@Temporal(TemporalType.TIMESTAMP)
    private Calendar dataJogo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    private Time timeCasa;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    private Time timeVisitante;
   
    private String resultado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDataJogo() {
		return dataJogo;
	}
	public void setDataJogo(Calendar dataJogo) {
		this.dataJogo = dataJogo;
	}

    public Time getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(Time timeCasa) {
        this.timeCasa = timeCasa;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;

        Jogo jogo = (Jogo) o;

        if (id != jogo.id) return false;
        if (dataJogo != null ? !dataJogo.equals(jogo.dataJogo) : jogo.dataJogo != null) return false;
        if (timeCasa != null ? !timeCasa.equals(jogo.timeCasa) : jogo.timeCasa != null) return false;
        if (timeVisitante != null ? !timeVisitante.equals(jogo.timeVisitante) : jogo.timeVisitante != null)
            return false;
        return resultado != null ? resultado.equals(jogo.resultado) : jogo.resultado == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataJogo != null ? dataJogo.hashCode() : 0);
        result = 31 * result + (timeCasa != null ? timeCasa.hashCode() : 0);
        result = 31 * result + (timeVisitante != null ? timeVisitante.hashCode() : 0);
        result = 31 * result + (resultado != null ? resultado.hashCode() : 0);
        return result;
    }
}
