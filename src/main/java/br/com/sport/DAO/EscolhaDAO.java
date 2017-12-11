package br.com.sport.DAO;

import br.com.sport.model.Escolha;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EscolhaDAO extends GenericDAO<Escolha> {

    public EscolhaDAO() {
        super(Escolha.class);
    }
    @Inject
    private EntityManager manager;
    public List<Escolha> getEscolhasByAposta(int apostaID) {
        List<Escolha> escolhas = (List<Escolha>) manager.createQuery("select a.escolhas from Aposta a where a.id = :apostaid")
                .setParameter("apostaid", apostaID).getResultList();
        return escolhas;
    }
}
