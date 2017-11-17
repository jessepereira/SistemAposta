package br.com.sport.DAO;

import br.com.sport.model.Usuario;
import br.com.sport.util.JPAUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {


    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario getUsuarioByUsuarioAndSenha(String usuario, String senha) {
        try {
            entityManager = new JPAUtil().getEntity();
            Usuario usu = entityManager.createQuery("select u from Usuario u where u.usuario = :usuario and u.senha = :senha", Usuario.class)
                    .setParameter("usuario", usuario)
                    .setParameter("senha", senha).getSingleResult();
            entityManager.close();
            return usu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
