package br.com.sport.DAO;

import br.com.sport.model.Resultado;
import br.com.sport.util.JPAUtil;

public class ResultadoDAO extends GenericDAO<Resultado> {
    public ResultadoDAO() {
        super(Resultado.class);
    }

    public Resultado getResultadoByCupom(Long cupomID) {
        try {
            entityManager = new JPAUtil().getEntity();
            Resultado resultado = entityManager.createQuery("select r from Resultado r where r.cupom.id = :cupomid", Resultado.class)
                    .setParameter("cupomid", cupomID).getSingleResult();
            entityManager.close();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
