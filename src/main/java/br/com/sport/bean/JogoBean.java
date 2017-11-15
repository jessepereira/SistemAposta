package br.com.sport.bean;


import br.com.sport.DAO.CupomDAO;
import br.com.sport.DAO.JogoDAO;
import br.com.sport.DAO.TimeDAO;
import br.com.sport.model.Cupom;
import br.com.sport.model.Jogo;
import br.com.sport.model.Time;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class JogoBean implements Serializable {

    private static final long serialVersionUID = -12312415167642L;

    private CupomDAO cupomDAO;
    private Cupom cupom;

    private JogoDAO jogoDAO;
    private Jogo jogo1;
    private Jogo jogo2;
    private Jogo jogo3;
    private Jogo jogo4;
    private Jogo jogo5;
    private Jogo jogo6;
    private Jogo jogo7;
    private Jogo jogo8;
    private Jogo jogo9;
    private Jogo jogo10;
    private Jogo jogo11;
    private Jogo jogo12;
    private Jogo jogo13;
    private Jogo jogo14;
    private Jogo jogo15;
    private Jogo jogo16;

    private TimeDAO timeDAO;
    private List<Time> times;

    private String nomeCupom;

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        cupom = (Cupom) session.getAttribute("currentCupom");
        if (cupom != null) {
            nomeCupom = cupom.getNomeCupom();
        }
        jogo1 = new Jogo();
        jogo2 = new Jogo();
        jogo3 = new Jogo();
        jogo4 = new Jogo();
        jogo5 = new Jogo();
        jogo6 = new Jogo();
        jogo7 = new Jogo();
        jogo8 = new Jogo();
        jogo9 = new Jogo();
        jogo10 = new Jogo();
        jogo11 = new Jogo();
        jogo12 = new Jogo();
        jogo13 = new Jogo();
        jogo14 = new Jogo();
        jogo15 = new Jogo();
        jogo16 = new Jogo();
        cupomDAO = new CupomDAO();
        timeDAO = new TimeDAO();
        jogoDAO = new JogoDAO();
        times = timeDAO.getAllTime();
    }

    public void criarJogo() {
        try {
            if (cupom == null) {
                cupom = new Cupom();
            }
            cupom.setNomeCupom(nomeCupom);
            cupom.getJogos().add(jogo1);
            cupom.getJogos().add(jogo2);
            cupom.getJogos().add(jogo3);
            cupom.getJogos().add(jogo4);
            cupom.getJogos().add(jogo5);
            cupom.getJogos().add(jogo6);
            cupom.getJogos().add(jogo7);
            cupom.getJogos().add(jogo8);
            cupom.getJogos().add(jogo9);
            cupom.getJogos().add(jogo10);
            cupom.getJogos().add(jogo11);
            cupom.getJogos().add(jogo12);
            cupom.getJogos().add(jogo13);
            cupom.getJogos().add(jogo14);
            cupom.getJogos().add(jogo15);
            cupom.getJogos().add(jogo16);

            cupomDAO.persist(cupom);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarJogos.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String nomeCupom) {
        this.nomeCupom = nomeCupom;
    }

    public List<Time> getTimes() {
        return times;
    }

    public Jogo getJogo1() {
        return jogo1;
    }

    public void setJogo1(Jogo jogo1) {
        this.jogo1 = jogo1;
    }

    public Jogo getJogo2() {
        return jogo2;
    }

    public void setJogo2(Jogo jogo2) {
        this.jogo2 = jogo2;
    }

    public Jogo getJogo3() {
        return jogo3;
    }

    public void setJogo3(Jogo jogo3) {
        this.jogo3 = jogo3;
    }

    public Jogo getJogo4() {
        return jogo4;
    }

    public void setJogo4(Jogo jogo4) {
        this.jogo4 = jogo4;
    }

    public Jogo getJogo5() {
        return jogo5;
    }

    public void setJogo5(Jogo jogo5) {
        this.jogo5 = jogo5;
    }

    public Jogo getJogo6() {
        return jogo6;
    }

    public void setJogo6(Jogo jogo6) {
        this.jogo6 = jogo6;
    }

    public Jogo getJogo7() {
        return jogo7;
    }

    public void setJogo7(Jogo jogo7) {
        this.jogo7 = jogo7;
    }

    public Jogo getJogo8() {
        return jogo8;
    }

    public void setJogo8(Jogo jogo8) {
        this.jogo8 = jogo8;
    }

    public Jogo getJogo9() {
        return jogo9;
    }

    public void setJogo9(Jogo jogo9) {
        this.jogo9 = jogo9;
    }

    public Jogo getJogo10() {
        return jogo10;
    }

    public void setJogo10(Jogo jogo10) {
        this.jogo10 = jogo10;
    }

    public Jogo getJogo11() {
        return jogo11;
    }

    public void setJogo11(Jogo jogo11) {
        this.jogo11 = jogo11;
    }

    public Jogo getJogo12() {
        return jogo12;
    }

    public void setJogo12(Jogo jogo12) {
        this.jogo12 = jogo12;
    }

    public Jogo getJogo13() {
        return jogo13;
    }

    public void setJogo13(Jogo jogo13) {
        this.jogo13 = jogo13;
    }

    public Jogo getJogo14() {
        return jogo14;
    }

    public void setJogo14(Jogo jogo14) {
        this.jogo14 = jogo14;
    }

    public Jogo getJogo15() {
        return jogo15;
    }

    public void setJogo15(Jogo jogo15) {
        this.jogo15 = jogo15;
    }

    public Jogo getJogo16() {
        return jogo16;
    }

    public void setJogo16(Jogo jogo16) {
        this.jogo16 = jogo16;
    }
}
