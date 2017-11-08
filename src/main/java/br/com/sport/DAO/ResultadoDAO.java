package br.com.sport.DAO;

import br.com.sport.model.Resultado;

public class ResultadoDAO extends GenericDAO<Resultado> {
    public ResultadoDAO() {
        super(Resultado.class);
    }

    public Resultado getResultadoByCupom(Long cupomID){
        return entityManager.createQuery("select r from Resultado r where r.cupom.id = :cupomid", Resultado.class)
                .setParameter("cupomid", cupomID).getSingleResult();
    }
}
