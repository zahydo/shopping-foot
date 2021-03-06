/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.ejbs.tienda;

import com.unicauca.modelo.ejbs.AbstractFacade;
import com.unicauca.accesodatos.entidades.Tienda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblTiendaFacade extends AbstractFacade<Tienda> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblTiendaFacade() {
        super(Tienda.class);
    }
    
}
