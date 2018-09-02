/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.accesodatos.entidades.Usuario;
import com.unicauca.modelo.ejbs.usuarios.TblPermisoFacade;
import com.unicauca.modelo.ejbs.usuarios.TblPermisoUsuarioFacade;
import com.unicauca.modelo.ejbs.usuarios.TblRolFacade;
import com.unicauca.modelo.ejbs.usuarios.TblUsuarioFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.unicauca.modelo.interfaces.UsuariosFacadeLocal;

/**
 *
 * @author sahydo
 */
@Stateless
public class UsuariosFacadeLocalImpl implements UsuariosFacadeLocal {

    @EJB
    private TblUsuarioFacade usuarioFacade;
    @EJB
    private TblPermisoFacade permisoFacade;
    @EJB
    private TblRolFacade rolFacade;
    @EJB
    private TblPermisoUsuarioFacade permisoUsuarioFacade;

    @Override
    public Usuario inicarSesion(Usuario user) {
        return usuarioFacade.inicarSesion(user);
    }

}
