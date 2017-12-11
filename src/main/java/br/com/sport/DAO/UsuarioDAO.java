package br.com.sport.DAO;

import br.com.sport.model.Usuario;
import br.com.sport.util.JPAUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UsuarioDAO extends GenericDAO<Usuario> {


    public UsuarioDAO() {
        super(Usuario.class);
    }
    @Inject
    private EntityManager manager;
    public Usuario getUsuarioByUsuarioAndSenha(String usuario, String senha) {
        try {
            manager = new JPAUtil().getEntity();
            Usuario usu = manager.createQuery("select u from Usuario u where u.usuario = :usuario and u.senha = :senha", Usuario.class)
                    .setParameter("usuario", usuario)
                    .setParameter("senha", senha).getSingleResult();
            manager.close();
            return usu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
