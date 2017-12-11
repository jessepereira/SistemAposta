package br.com.sport.DAO;

import br.com.sport.model.Resultado;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class ResultadoDAO extends GenericDAO<Resultado> {
    public ResultadoDAO() {
        super(Resultado.class);
    }
    @Inject
    private EntityManager manager;
    public Resultado getResultadoByCupom(Long cupomID) {
        try {
            manager = new JPAUtil().getEntity();
            Resultado resultado = manager.createQuery("select r from Resultado r where r.cupom.id = :cupomid", Resultado.class)
                    .setParameter("cupomid", cupomID).getSingleResult();
            manager.close();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
