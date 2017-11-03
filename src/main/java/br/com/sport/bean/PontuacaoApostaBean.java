package br.com.sport.bean;

import br.com.sport.DAO.ApostaDAO;
import br.com.sport.DAO.CupomDAO;
import br.com.sport.DAO.EscolhaDAO;
import br.com.sport.DAO.ResultadoDAO;
import br.com.sport.model.Aposta;
import br.com.sport.model.Cupom;
import br.com.sport.model.Escolha;
import br.com.sport.model.Resultado;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@ViewScoped
public class PontuacaoApostaBean {

    private Cupom cupom;
    private Resultado resultado;
    private Aposta aposta;

    private List<Aposta> apostas;
    private List<Cupom> cupons;

    private ApostaDAO apostaDAO;
    private CupomDAO cupomDAO;
    private ResultadoDAO resultadoDAO;
    private EscolhaDAO escolhaDAO;

    private int pontuacao;

    @PostConstruct
    public void init() {
        cupomDAO = new CupomDAO();
        apostaDAO = new ApostaDAO();
        resultadoDAO = new ResultadoDAO();
        escolhaDAO = new EscolhaDAO();
        aposta = new Aposta();

        if (cupom == null) {
            cupom = new Cupom();
            cupons = cupomDAO.getAllCuponsWithResultado();
            cupom = cupons.get(cupons.size() - 1);
            apostas = apostaDAO.apostasByCupom(cupom.getId());
        } else {
            apostas = apostaDAO.apostasByCupom(cupom.getId());
        }
    }

    public void onCupomSelect() {
        if (cupom != null) {
            try {
                apostas = apostaDAO.apostasByCupom(cupom.getId());
                FacesContext.getCurrentInstance().getExternalContext().redirect("Resultado.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int calcularResultado(int apostaID) {
        resultado = resultadoDAO.getResultadoByCupom(cupom.getId());
        List<Escolha> escolhas = escolhaDAO.getEscolhasByAposta(apostaID);
        for (Escolha escolha : escolhas) {
            for (int i = 0; i < resultado.getEscolhas().size(); i++) {
                if (escolha.getJogo().getId() == resultado.getEscolhas().get(i).getJogo().getId()) {
                    if (escolha.getResposta().equals(resultado.getEscolhas().get(i).getResposta())) {
                        pontuacao = pontuacao + 3;
                        resultado.getEscolhas().remove(i);
                    } else if (resultado.getEscolhas().get(i).getResposta().equals("E")) {
                        pontuacao = pontuacao + 1;
                    }
                }
            }
        }
        resultado = null;
        return pontuacao;
    }

    public int getAcertos(int apostaID) {
        resultado = resultadoDAO.getResultadoByCupom(cupom.getId());
        List<Escolha> escolhas = escolhaDAO.getEscolhasByAposta(apostaID);
        int acerto = 0;
        for (Escolha escolha : escolhas) {
            for (int i = 0; i < resultado.getEscolhas().size(); i++) {
                if (escolha.getJogo().getId() == resultado.getEscolhas().get(i).getJogo().getId()) {
                    if (escolha.getResposta().equals(resultado.getEscolhas().get(i).getResposta())) {
                        acerto++;
                    }
                }
            }
        }
        resultado = null;
        return acerto;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public List<Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(List<Aposta> apostas) {
        this.apostas = apostas;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
