package br.com.sport.DAO;

import br.com.sport.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {


    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario getUsuarioByUsuarioAndSenha(String usuario, String senha) {
        return entityManager.createQuery("select u from Usuario u where u.usuario = :usuario and u.senha = :senha", Usuario.class)
                .setParameter("usuario", usuario)
                .setParameter("senha", senha).getResultList().get(0);
    }
}
