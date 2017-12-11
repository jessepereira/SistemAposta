package br.com.sport.DAO;

import br.com.sport.model.Cupom;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CupomDAO extends GenericDAO<Cupom> {

    public CupomDAO() {
        super(Cupom.class);
    }
    @Inject
    private EntityManager manager;
    public List<Cupom> getAllCuponsOnResultado() {

        List<Cupom> all = manager.createQuery("select c from Cupom c where resultado = false", Cupom.class).getResultList();

        return all;
    }

    public List<Cupom> getAllCuponsWithResultado() {
        manager = new JPAUtil().getEntity();
        List<Cupom> all = manager.createQuery("select c from Cupom c where resultado = true", Cupom.class).getResultList();

        return all;
    }
}
