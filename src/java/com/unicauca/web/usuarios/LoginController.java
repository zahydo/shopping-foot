package com.unicauca.web.usuarios;

import com.unicauca.entidades.TblUsuario;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable{

    @EJB
    private com.unicauca.ejbs.usuarios.LoginFacade loginFacade;
    private String pwd;
    private String msg;
    private String user;
    
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public LoginController() {
    }

    public void validateUsernamePassword(){
        TblUsuario usuario = loginFacade.validate(user, pwd);
    }
}
