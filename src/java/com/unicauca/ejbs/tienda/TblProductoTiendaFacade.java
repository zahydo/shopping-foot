/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.tienda;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.entidades.ProductoTienda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblProductoTiendaFacade extends AbstractFacade<ProductoTienda> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblProductoTiendaFacade() {
        super(ProductoTienda.class);
    }
    
}
