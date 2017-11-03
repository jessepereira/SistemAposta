package br.com.sport.DAO;

import br.com.sport.model.Aposta;

import java.util.List;

public class ApostaDAO extends GenericDAO<Aposta> {

    public ApostaDAO() {
        super(Aposta.class);
    }

    public List<Aposta> apostasByCupom(long cupomID) {
        return entityManager.createQuery("select a from Aposta a where a.cupom.id = :cupomid", Aposta.class)
                .setParameter("cupomid", cupomID).getResultList();
    }
}
