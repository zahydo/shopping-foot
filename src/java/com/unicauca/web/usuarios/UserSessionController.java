/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.web.usuarios;

import com.unicauca.ejbs.usuarios.TblUsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author sahydo
 */
public class UserSessionController implements Serializable {

    private String username;
    private String password;
    private boolean sesionIniciada = false;
    private boolean esAdmin = false;

    @EJB
    private TblUsuarioFacade facade;

    public void login() {
        FacesMessage message;
        RequestContext context = RequestContext.getCurrentInstance();

        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
            sesionIniciada = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            sesionIniciada = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", sesionIniciada);
        if (sesionIniciada) {
            context.addCallbackParam("view", "template.xhtml");
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        sesionIniciada = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public TblUsuarioFacade getFacade() {
        return facade;
    }

    public void setFacade(TblUsuarioFacade facade) {
        this.facade = facade;
    }

}
