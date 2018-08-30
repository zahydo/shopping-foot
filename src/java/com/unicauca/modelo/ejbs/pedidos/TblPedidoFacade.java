/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.ejbs.pedidos;

import com.unicauca.modelo.ejbs.AbstractFacade;
import com.unicauca.accesodatos.entidades.Pedido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblPedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TblPedidoFacade() {
        super(Pedido.class);
    }
    
}
