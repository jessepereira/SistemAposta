package br.com.sport.bean;

import br.com.sport.DAO.ApostaDAO;
import br.com.sport.DAO.CupomDAO;
import br.com.sport.DAO.EscolhaDAO;
import br.com.sport.DAO.ResultadoDAO;
import br.com.sport.model.Aposta;
import br.com.sport.model.Cupom;
import br.com.sport.model.DataModel.PontuacaoAposta;
import br.com.sport.model.Escolha;
import br.com.sport.model.Resultado;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class PontuacaoApostaBean implements Serializable {

    private static final long serialVersionUID = -987535622342362L;

    private Cupom cupom;
    private Resultado resultado;
    private Aposta aposta;

    private List<Aposta> apostas;
    private List<Cupom> cupons;
    private List<PontuacaoAposta> pontuacaoApostas;


    private ApostaDAO apostaDAO;
    private CupomDAO cupomDAO;
    private ResultadoDAO resultadoDAO;
    private EscolhaDAO escolhaDAO;

    private int cupomId;
    private int pontuacao;
    private int acerto;

    @PostConstruct
    public void init() {
        cupomDAO = new CupomDAO();
        apostaDAO = new ApostaDAO();
        resultadoDAO = new ResultadoDAO();
        escolhaDAO = new EscolhaDAO();
        aposta = new Aposta();
        pontuacaoApostas = new ArrayList<>();

        if (cupom == null) {
            cupom = new Cupom();
            cupons = cupomDAO.getAllCuponsWithResultado();
            if (cupons.size() > 0) {
                cupom = cupons.get(cupons.size() - 1);
                apostas = apostaDAO.apostasByCupom(cupom.getId());
            }
        } else {
            apostas = apostaDAO.apostasByCupom(cupom.getId());
        }
        resultado = resultadoDAO.getResultadoByCupom(cupom.getId());
        if (resultado != null) {
            criarListagemPontuacao();
        }
    }

    public void onCupomSelect() {
        if (cupom != null) {
            apostas = apostaDAO.apostasByCupom(cupomId);
            resultado = resultadoDAO.getResultadoByCupom((long) cupomId);
            criarListagemPontuacao();
        }
    }

    private int calcularResultado(int apostaID) {
        int pontuacao = 0;
        List<Escolha> escolhas = escolhaDAO.getEscolhasByAposta(apostaID);
        for (Escolha escolha : escolhas) {
            for (int i = 0; i < resultado.getEscolhas().size(); i++) {
                if (escolha.getJogo().getId() == resultado.getEscolhas().get(i).getJogo().getId()) {
                    if (escolha.getResposta().equals(resultado.getEscolhas().get(i).getResposta())) {
                        pontuacao = pontuacao + 3;
                        break;
                    } else if (resultado.getEscolhas().get(i).getResposta().equals("E")) {
                        pontuacao = pontuacao + 1;
                    }
                }
            }
        }
        return pontuacao;
    }

    private int calcularAcerto(int apostaID) {
        int acerto = 0;
        List<Escolha> escolhas = escolhaDAO.getEscolhasByAposta(apostaID);
        for (Escolha escolha : escolhas) {
            for (int i = 0; i < resultado.getEscolhas().size(); i++) {
                if (escolha.getJogo().getId() == resultado.getEscolhas().get(i).getJogo().getId()) {
                    if (escolha.getResposta().equals(resultado.getEscolhas().get(i).getResposta())) {
                        acerto++;
                        break;
                    }
                }
            }
        }
        return acerto;
    }


    private void criarListagemPontuacao() {
        pontuacaoApostas = new ArrayList<>();
        for (Aposta aposta : apostas) {
            PontuacaoAposta pontuacaoAposta = new PontuacaoAposta();
            pontuacaoAposta.setAcertos(calcularAcerto(aposta.getId()));
            pontuacaoAposta.setPontuacao(calcularResultado(aposta.getId()));
            pontuacaoAposta.setNomeApostador(aposta.getApostador());
            pontuacaoApostas.add(pontuacaoAposta);
        }
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

    public int getCupomId() {
        return cupomId;
    }

    public void setCupomId(int cupomId) {
        this.cupomId = cupomId;
    }

    public List<PontuacaoAposta> getPontuacaoApostas() {
        return pontuacaoApostas;
    }

    public void setPontuacaoApostas(List<PontuacaoAposta> pontuacaoApostas) {
        this.pontuacaoApostas = pontuacaoApostas;
    }
}
