package br.com.sport.DAO;

import br.com.sport.model.Cupom;

import java.util.List;

public class CupomDAO extends GenericDAO<Cupom> {

    public CupomDAO() {
        super(Cupom.class);
    }

    public List<Cupom> getAllCuponsOnResultado() {
        return entityManager.createQuery("select c from Cupom c where resultado = false",Cupom.class).getResultList();
    }
}
