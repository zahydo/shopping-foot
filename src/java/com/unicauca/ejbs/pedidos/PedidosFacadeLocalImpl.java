/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.pedidos;

import com.unicauca.ejbs.productos.TblProductoFacade;
import com.unicauca.entidades.Cliente;
import com.unicauca.entidades.Pedido;
import com.unicauca.entidades.Producto;
import com.unicauca.entidades.Usuario;
import com.unicauca.interfaces.PedidosFacadeLocal;
import com.unicauca.interfaces.ProductosFacadeLocal;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author sahydo
 */
@Stateless
public class PedidosFacadeLocalImpl implements PedidosFacadeLocal {
    @EJB
    private TblPedidoFacade pedidoFacade;
    @EJB
    private TblPedidoUsuarioFacade pedidoUsuarioFacade;
    @EJB
    private ProductosFacadeLocal productoFacade;
    
    @Override
    public Pedido guardarPedido(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido asignarPedido(Usuario usuario, Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Cliente cliente) {
        EntityManager em = pedidoFacade.getEntityManager();
        String consulta = "SELECT p FROM Pedido p WHERE p.idCliente = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, cliente.getIdCliente());
        List<Pedido> lista = query.getResultList();
        return lista;
    }

    @Override
    public void generarFactura(Pedido pedido, Usuario usuario, Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> buscarTodosLosPedidos() {
        return pedidoFacade.findAll();
    }

    @Override
    public Pedido buscarPedido(BigDecimal id) {
        return pedidoFacade.find(id);
    }

    @Override
    public void editarPedido(Pedido pedido) {
        if (pedido.getIdPedido() != null) {
            pedidoFacade.edit(pedido);
        } else {
            pedido.setValorTotal(pedido.getCantidad() * pedido.getIdProducto().getValor());
            pedido.setDomicilio("En restaurante");
            Producto producto = productoFacade.buscarProducto(pedido.getIdProducto().getIdProducto());
            if (producto.getDisponibles() > pedido.getCantidad()) {
                producto.setDisponibles(producto.getDisponibles()-pedido.getCantidad());
                productoFacade.editarProducto(producto);
                pedido.setIdProducto(producto);
                pedidoFacade.edit(pedido);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
            }
        }
    }

    @Override
    public void eliminarPedido(Pedido pedido) {
        pedidoFacade.remove(pedido);
    }
    
}
