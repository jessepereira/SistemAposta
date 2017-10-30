package br.com.sport.bean;


import br.com.sport.DAO.UsuarioDAO;
import br.com.sport.model.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class LoginBean {

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();
    }

    public void login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario user = usuarioDAO.getUsuarioByUsuarioAndSenha(this.usuario.getUsuario(), this.usuario.getSenha());
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                "Erro no Login!"));

                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", user);
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Realizado com Sucesso", "Login Bem Sucedido"));
                fc.getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
