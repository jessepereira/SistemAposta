package br.com.sport.model;


import javax.persistence.*;

@Entity

public class Time {

    @Id
    @SequenceGenerator(name = "time_seq",
            sequenceName = "time_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "time_seq")
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(unique = false, nullable = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;

        Time time = (Time) o;

        if (id != null ? !id.equals(time.id) : time.id != null) return false;
        if (nome != null ? !nome.equals(time.nome) : time.nome != null) return false;
        return sigla != null ? sigla.equals(time.sigla) : time.sigla == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        return result;
    }
}
