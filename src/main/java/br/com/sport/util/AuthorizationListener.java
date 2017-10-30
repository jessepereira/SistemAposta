package br.com.sport.util;

import br.com.sport.model.Usuario;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

    private FacesContext facesContext;
    private String currentPage;
    private boolean isLoginPage;
    private boolean isParticipacaoPage;


    public void afterPhase(PhaseEvent event) {
        if (facesContext != null) {
            facesContext = null;
        }

        this.facesContext = event.getFacesContext();
        this.currentPage = facesContext.getViewRoot().getViewId();
        this.isLoginPage = (currentPage.lastIndexOf("/login.xhtml") > -1);

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Usuario user = (Usuario) session.getAttribute("currentUser");

        if (!isLoginPage && user == null) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "LoginPage");
        }
    }

    public void beforePhase(PhaseEvent event) {

    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}

