package br.com.sport.bean;

import br.com.sport.DAO.CupomDAO;
import br.com.sport.DAO.JogoDAO;
import br.com.sport.DAO.ResultadoDAO;
import br.com.sport.model.Cupom;
import br.com.sport.model.Escolha;
import br.com.sport.model.Resultado;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ResultadoBean implements Serializable {

    private static final long serialVersionUID = -12314981471098L;

    private Cupom cupom;
    private Escolha escolha;
    private Resultado result;

    private CupomDAO cupomDAO;
    private ResultadoDAO resultadoDAO;

    private boolean optionSelect = true;

    private List<Cupom> cupons;
    private List<Escolha> escolhas;

    private String resultado;
    private Map<String, String> item;

    @PostConstruct
    public void init() {
        cupom = new Cupom();
        escolha = new Escolha();
        result = new Resultado();
        escolhas = new ArrayList<>();

        cupomDAO = new CupomDAO();
        resultadoDAO = new ResultadoDAO();
        item = new LinkedHashMap<String, String>();
        item.put("CASA", "C");
        item.put("FORA", "F");

        cupons = cupomDAO.getAllCuponsOnResultado();
        if (cupons.size() == 1) {
            cupom = cupons.get(0);
            optionSelect = false;
        }
    }

    public void salvarAposta() {
        try {
            cupom.setResultado(true);
            result.setCupom(cupom);
            result.setEscolhas(escolhas);

            resultadoDAO.persist(result);

            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onEscolhaSelect(String jogoId) {
        if (resultado.equals("")) {
            for (Escolha escolha : escolhas) {
                if (String.valueOf(escolha.getJogo().getId()).equals(jogoId)) {
                    escolhas.remove(escolha);

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

                        return;
                    }
                }
                escolha.setResposta(resultado);
                escolha.setJogo(new JogoDAO().findById(Integer.parseInt(jogoId)));
                escolhas.add(escolha);
                escolha = new Escolha();

            } else {
                escolha.setResposta(resultado);
                escolha.setJogo(new JogoDAO().findById(Integer.parseInt(jogoId)));
                escolhas.add(escolha);
                escolha = new Escolha();

            }
        }
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public boolean isOptionSelect() {
        return optionSelect;
    }

    public void setOptionSelect(boolean optionSelect) {
        this.optionSelect = optionSelect;
    }

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }
}
