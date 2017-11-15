package br.com.sport.bean;

import br.com.sport.DAO.ApostaDAO;
import br.com.sport.DAO.CupomDAO;
import br.com.sport.model.Aposta;
import br.com.sport.model.Cupom;
import br.com.sport.model.DataModel.ApostaDataModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListagemApostaBean implements Serializable{

    private static final long serialVersionUID = -7378752361471098L;

    private Cupom cupom;
    private Aposta aposta;

    private List<Aposta> apostaList;
    private List<Cupom> cupons;
    private CupomDAO cupomDAO;
    private ApostaDAO apostaDAO;
    private ApostaDataModel apostaDataModel;

    @PostConstruct
    public void init() {
        cupomDAO = new CupomDAO();
        apostaDAO = new ApostaDAO();
        aposta = new Aposta();

        if (cupom == null) {
            cupom = new Cupom();
            cupons = cupomDAO.findAll();
            if (cupons.size() > 0) {
                cupom = cupons.get(cupons.size() - 1);
                apostaList = apostaDAO.apostasByCupom(cupom.getId());
                apostaDataModel = new ApostaDataModel(apostaList);
            }
        } else {
            apostaList = apostaDAO.apostasByCupom(cupom.getId());
        }
    }

    public void onApostaSelect() {
        if (cupom != null) {
            try {
                apostaList = apostaDAO.apostasByCupom(cupom.getId());
                FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarAposta.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editAposta(Aposta aposta) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentAposta", aposta);
            FacesContext.getCurrentInstance().getExternalContext().redirect("CriarAposta.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelarAposta() {
        try {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("OK", "Aposta Cancelada Com Sucesso"));
            apostaDAO.remove(aposta);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarAposta.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    public List<Aposta> getApostaList() {
        return apostaList;
    }

    public void setApostaList(List<Aposta> apostaList) {
        this.apostaList = apostaList;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
}
