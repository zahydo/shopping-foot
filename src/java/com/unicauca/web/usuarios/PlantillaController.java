/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.web.usuarios;

import com.unicauca.entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sahydo
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable {
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (usuario == null) {
                context.getExternalContext().redirect("permisos.xhtml");
            }
        } catch (IOException e) {
            //
        }
    }
}
