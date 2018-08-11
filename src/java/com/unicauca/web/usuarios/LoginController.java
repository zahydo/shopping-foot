package com.unicauca.web.usuarios;

import com.unicauca.interfaces.UsuarioFacadeLocal;
import com.unicauca.entidades.Usuario;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginController")
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacadeLocal ejbUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        Usuario user;
        String redireccion = null;
        try {
            user = ejbUsuario.inicarSesion(usuario);
            if (user != null) {
                //Almacenar en la sesión de JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
                redireccion = "/protegido/principal";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "No se puede iniciar sesión con esas credenciales"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de validación", "No se puede iniciar sesión con esas credenciales"));
        }
        return redireccion;
    }
}
