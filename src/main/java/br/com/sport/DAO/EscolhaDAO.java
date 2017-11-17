package br.com.sport.DAO;

import br.com.sport.model.Escolha;
import br.com.sport.util.JPAUtil;

import java.util.ArrayList;
import java.util.List;

public class EscolhaDAO extends GenericDAO<Escolha> {

    public EscolhaDAO() {
        super(Escolha.class);
    }

    public List<Escolha> getEscolhasByAposta(int apostaID) {
        entityManager = new JPAUtil().getEntity();
        List<Escolha> escolhas = (List<Escolha>) entityManager.createQuery("select a.escolhas from Aposta a where a.id = :apostaid")
                .setParameter("apostaid", apostaID).getResultList();
        return escolhas;
    }
}
