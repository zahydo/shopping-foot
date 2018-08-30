/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.ejbs.pedidos;

import com.unicauca.accesodatos.entidades.Pedido;
import com.unicauca.modelo.ejbs.AbstractFacade;
import com.unicauca.accesodatos.entidades.PedidoUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblPedidoUsuarioFacade extends AbstractFacade<PedidoUsuario> {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblPedidoUsuarioFacade() {
        super(PedidoUsuario.class);
    }
    
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuario){
        Query query = em.createNativeQuery("select * from TBL_PEDIDO ped INNER JOIN tbl_pedido_usuario pedUsu ON (ped.id_pedido = pedusu.id_pedido) WHERE pedusu.id_usuario = ?1",Pedido.class);
        query.setParameter(1, idUsuario);
        return query.getResultList();
    }
}
