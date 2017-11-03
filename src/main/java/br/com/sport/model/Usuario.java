package br.com.sport.model;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuario_seq",
            sequenceName = "usuario_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "usuario_seq")
    private int id;

    @Column
    private String usuario;

    @Column
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
