package br.com.sport.bean;


import br.com.sport.model.Jogo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class JogoBean {

    private Jogo jogo1;
    private Jogo jogo2;
    private Jogo jogo3;
    private Jogo jogo4;
    private Jogo jogo5;
    private Jogo jogo6;
    private Jogo jogo7;
    private Jogo jogo8;

    @PostConstruct
    public void init(){}

}
