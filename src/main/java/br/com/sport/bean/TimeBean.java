package br.com.sport.bean;

import br.com.sport.DAO.TimeDAO;
import br.com.sport.model.Time;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
public class TimeBean {

    private String nome;
    private String sigla;
    private TimeDAO timeDAO;

    @PostConstruct
    public void init() {
        timeDAO = new TimeDAO();
        sigla = "";
        nome = "";
    }

    public void criarTime() {
        try {
            Time time = new Time();
            time.setNome(nome);
            time.setSigla(sigla);
            timeDAO.persist(time);

            FacesContext.getCurrentInstance().getExternalContext().redirect("CriarTime.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public List<Time> getTimes(){
        return timeDAO.findAll();
    }
}
