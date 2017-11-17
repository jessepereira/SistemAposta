package br.com.sport.bean;

import br.com.sport.DAO.ApostaDAO;
import br.com.sport.DAO.CupomDAO;
import br.com.sport.model.Aposta;
import br.com.sport.model.Cupom;

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
public class GerenciarCupomBean implements Serializable {

    private static final long serialVersionUID = -91572185643681L;

    private Cupom cupom;
    private List<Cupom> cupons;
    private CupomDAO cupomDAO;

    @PostConstruct
    public void init() {
        cupom = new Cupom();
        cupomDAO = new CupomDAO();
        cupons = cupomDAO.findAll();
    }

    public void editCupom(Cupom cupom) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentCupom", cupom);
            FacesContext.getCurrentInstance().getExternalContext().redirect("CriarJogo.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelarCupom(Cupom cupom) {
        try {
            List<Aposta> apostas = new ApostaDAO().apostasByCupom(cupom.getId());
            if (apostas != null && apostas.size() > 0) {
                for (Aposta aposta : apostas) {
                    new ApostaDAO().remove(aposta);
                }
            }
            cupomDAO.remove(cupom);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("OK", "Cupom Cancelado Com Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarJogos.xhtml");
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

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
}
