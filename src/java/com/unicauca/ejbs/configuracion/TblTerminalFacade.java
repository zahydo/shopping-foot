/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.configuracion;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.entidades.TblTerminal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblTerminalFacade extends AbstractFacade<TblTerminal> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblTerminalFacade() {
        super(TblTerminal.class);
    }
    
}
