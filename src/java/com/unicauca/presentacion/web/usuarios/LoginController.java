package com.unicauca.presentacion.web.usuarios;

import com.unicauca.accesodatos.entidades.Usuario;
import com.unicauca.accesodatos.entidades.util.CodigosUtil;
import com.unicauca.presentacion.util.EncrypterUtil;
import com.unicauca.presentacion.util.SessionUtils;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.unicauca.modelo.interfaces.UsuariosFacadeLocal;

@Named("loginController")
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UsuariosFacadeLocal ejbUsuario;
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
            usuario.setContrasena(EncrypterUtil.encriptarMD5(usuario.getContrasena()));
            user = ejbUsuario.inicarSesion(usuario);
            if (user != null) {
                //Almacenar en la sesión de JSF
                usuario = user;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
                redireccion = "/protegido/principal";
                usuario = null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "No se puede iniciar sesión con esas credenciales"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de validación", "No se puede iniciar sesión con esas credenciales"));
        }
        return redireccion;
    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
        String redireccion = "/login";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cierre de sesión", "Sesión cerrada satisfactoriamente"));
        return redireccion;
    }
    
    public boolean isCajero(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_CAJERO;
    }

    public boolean isMesero(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_MESERO;
    }

    public boolean isCocinero(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_COCINERO;
    }

    public boolean isDespachador(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_DESPACHADOR;
    }

    public boolean isAdministrador(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_ADMINISTRADOR;
    }

    public boolean isConfigurador(){
        return SessionUtils.getUsuarioSession().getIdRol().getIdRol() == CodigosUtil.ROL_CONFIGURADOR;
    }
}
