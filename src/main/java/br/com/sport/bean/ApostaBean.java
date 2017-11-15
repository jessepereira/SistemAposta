package br.com.sport.bean;

import br.com.sport.DAO.ApostaDAO;
import br.com.sport.DAO.CupomDAO;
import br.com.sport.DAO.JogoDAO;
import br.com.sport.model.Aposta;
import br.com.sport.model.Cupom;
import br.com.sport.model.Escolha;
import br.com.sport.model.Jogo;
import com.sun.faces.renderkit.SelectItemsIterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ApostaBean implements Serializable {

    private static final long serialVersionUID = -67854645846842L;

    private Cupom cupom;
    private Escolha escolha;
    private Aposta aposta;

    private CupomDAO cupomDAO;
    private ApostaDAO apostaDAO;

    private List<Cupom> cupons;

    private List<Escolha> escolhas;

    private boolean option = true;
    private String resultado;
    private long cupomId;
    private int contador;
    private Map<String, String> item;

    @PostConstruct
    public void init() {
        cupomDAO = new CupomDAO();
        apostaDAO = new ApostaDAO();
        escolhas = new ArrayList<>();
        escolha = new Escolha();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        aposta = (Aposta) session.getAttribute("currentAposta");
        session.removeAttribute("currentAposta");

        if (aposta == null) {
            aposta = new Aposta();
            cupons = cupomDAO.getAllCuponsOnResultado();
            if (cupons.size() != 0 && cupom == null) {
                cupom = cupons.get(cupons.size() - 1);
            }
            aposta.setEscolhas(null);
        } else {
            cupom = aposta.getCupom();
            option = false;
            apostaDAO.remove(aposta);
        }
        item = new LinkedHashMap<String, String>();
        item.put("CASA", "C");
        item.put("FORA", "F");
    }

    public void onEscolhaSelect(String jogoId) {
        if (contador == 8) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "8 escolhas realizadas, por gentileza desmarque alguma escolha anterior, ou finalize a aposta"));
            return;
        }
        if (resultado.equals("")) {
            for (Escolha escolha : escolhas) {
                if (String.valueOf(escolha.getJogo().getId()).equals(jogoId)) {
                    escolhas.remove(escolha);
                    contador--;
                }
            }
        } else {
            if (escolhas.size() > 0) {
                for (Escolha escolha : escolhas) {
                    if (!escolha.getResposta().equals(resultado) && jogoId.equals(String.valueOf(escolha.getJogo().getId()))) {
                        escolhas.remove(escolha);
                        escolha.setResposta(resultado);
                        escolha.setJogo(new JogoDAO().findById(Integer.parseInt(jogoId)));
                        escolhas.add(escolha);
                        contador++;
                        return;
                    }
                }
                escolha.setResposta(resultado);
                escolha.setJogo(new JogoDAO().findById(Integer.parseInt(jogoId)));
                escolhas.add(escolha);
                escolha = new Escolha();
                contador++;
            } else {
                escolha.setResposta(resultado);
                escolha.setJogo(new JogoDAO().findById(Integer.parseInt(jogoId)));
                escolhas.add(escolha);
                escolha = new Escolha();
                contador++;
            }
        }
    }

    public void salvarAposta() {
        try {
            String nomeApostador = aposta.getApostador();
            aposta = new Aposta();
            aposta.setApostador(nomeApostador);
            aposta.setCupom(cupom);
            aposta.setEscolhas(escolhas);
            apostaDAO.persist(aposta);
            aposta = new Aposta();
            FacesContext.getCurrentInstance().getExternalContext().redirect("CriarAposta.xhtml");
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

    public Escolha getEscolha() {
        return escolha;
    }

    public void setEscolha(Escolha escolha) {
        this.escolha = escolha;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }

    public long getCupomId() {
        return cupomId;
    }

    public void setCupomId(long cupomId) {
        this.cupomId = cupomId;
    }
}
