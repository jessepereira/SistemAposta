package br.com.sport.DAO;

import br.com.sport.model.Cupom;
import br.com.sport.util.JPAUtil;

import java.util.List;

public class CupomDAO extends GenericDAO<Cupom> {

    public CupomDAO() {
        super(Cupom.class);
    }

    public List<Cupom> getAllCuponsOnResultado() {
        entityManager = new JPAUtil().getEntity();
        List<Cupom> all = entityManager.createQuery("select c from Cupom c where resultado = false", Cupom.class).getResultList();
        entityManager.close();
        return all;
    }

    public List<Cupom> getAllCuponsWithResultado() {
        entityManager = new JPAUtil().getEntity();
        List<Cupom> all = entityManager.createQuery("select c from Cupom c where resultado = true", Cupom.class).getResultList();
        entityManager.close();
        return all;
    }
}
