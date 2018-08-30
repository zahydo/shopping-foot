/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.accesodatos.entidades.Usuario;
import com.unicauca.modelo.ejbs.usuarios.TblUsuarioFacade;
import com.unicauca.modelo.interfaces.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sahydo
 */
@Stateless
public class UsurioFacadeLocalImpl implements UsuarioFacadeLocal {

    @EJB
    private TblUsuarioFacade usuarioFacade;

    @Override
    public Usuario inicarSesion(Usuario user) {
        return usuarioFacade.inicarSesion(user);
    }

}
