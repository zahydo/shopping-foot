/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.productos;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.entidades.TblIngrediente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblIngredienteFacade extends AbstractFacade<TblIngrediente> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblIngredienteFacade() {
        super(TblIngrediente.class);
    }
    
}
