/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.usuarios;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.entidades.TblPermisoUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblPermisoUsuarioFacade extends AbstractFacade<TblPermisoUsuario> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblPermisoUsuarioFacade() {
        super(TblPermisoUsuario.class);
    }
    
}
