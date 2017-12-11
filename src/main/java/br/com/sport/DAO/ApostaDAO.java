package br.com.sport.DAO;

import br.com.sport.model.Aposta;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ApostaDAO extends GenericDAO<Aposta> {

    public ApostaDAO()
    {
        super(Aposta.class);
    }
    @Inject
    private EntityManager manager;
    public List<Aposta> apostasByCupom(long cupomID) {

        List<Aposta> all = manager.createQuery("select a from Aposta a where a.cupom.id = :cupomid", Aposta.class)
                .setParameter("cupomid", cupomID).getResultList();

        return all;
    }
}
