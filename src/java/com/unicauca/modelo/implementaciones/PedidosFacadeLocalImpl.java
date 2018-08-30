/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.modelo.ejbs.configuracion.TblEstadoFacade;
import com.unicauca.modelo.ejbs.configuracion.TblTerminalFacade;
import com.unicauca.modelo.ejbs.pedidos.TblPedidoFacade;
import com.unicauca.modelo.ejbs.pedidos.TblPedidoUsuarioFacade;
import com.unicauca.accesodatos.entidades.Cliente;
import com.unicauca.accesodatos.entidades.Estado;
import com.unicauca.accesodatos.entidades.Pedido;
import com.unicauca.accesodatos.entidades.PedidoUsuario;
import com.unicauca.accesodatos.entidades.Producto;
import com.unicauca.accesodatos.entidades.Terminal;
import com.unicauca.accesodatos.entidades.Usuario;
import com.unicauca.accesodatos.entidades.util.CodigosUtil;
import com.unicauca.modelo.interfaces.PedidosFacadeLocal;
import com.unicauca.modelo.interfaces.ProductosFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    @EJB
    private TblTerminalFacade terminalFacade;
    @EJB
    private TblEstadoFacade estadoFacade;

    @Override
    public Pedido asignarPedido(Long idUsuario, Long idPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuario) {
        return pedidoUsuarioFacade.obtenerPedidosPorUsuario(idUsuario);
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
    public Pedido buscarPedido(Long id) {
        return pedidoFacade.find(id);
    }

    @Override
    public Pedido editarPedido(Pedido pedido, Usuario usuario) {
        pedidoFacade.edit(pedido);
        return pedido;
    }

    @Override
    public boolean eliminarPedido(Pedido pedido, Usuario usuario) {
        try {
            pedidoFacade.remove(pedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Pedido guardarPedido(Pedido pedido, Usuario usuario) {
        pedido.setValorTotal(pedido.getCantidad() * pedido.getIdProducto().getValor());
        pedido.setDomicilio("En restaurante");
        Producto producto = productoFacade.buscarProducto(pedido.getIdProducto().getIdProducto());
        PedidoUsuario pedUsuario = new PedidoUsuario();
        pedUsuario.setIdPedido(pedido);
        pedUsuario.setIdUsuario(usuario);
        Terminal terminal;
        Estado estado;
        switch(usuario.getIdRol().getIdRol().intValue()){
            case (int)CodigosUtil.ROL_COCINERO:
                terminal = terminalFacade.find(CodigosUtil.TERMINAL_COCINA);
                estado = estadoFacade.find(CodigosUtil.ESTADO_EN_PROCESO);
                break;
            case (int)CodigosUtil.ROL_DESPACHADOR:
                terminal = terminalFacade.find(CodigosUtil.TERMINAL_DESPACHO);
                estado = estadoFacade.find(CodigosUtil.ESTADO_DESPACHADO);
                break;
            default:
                terminal = terminalFacade.find(CodigosUtil.TERMINAL_CAJA);
                estado = estadoFacade.find(CodigosUtil.ESTADO_EN_COLA);
                break;                
        }
        pedUsuario.setIdTerminal(terminal);
        List<PedidoUsuario> relacionConUsuario = new ArrayList<>();
        pedido.setPedidoUsuarioList(relacionConUsuario);
        pedido.setIdEstado(estado);
        if (producto.getDisponibles() > pedido.getCantidad()) {
            producto.setDisponibles(producto.getDisponibles() - pedido.getCantidad());
            productoFacade.editarProducto(producto);
            pedido.setIdProducto(producto);
            pedidoFacade.edit(pedido);
        } else {
            return null;
        }
        return pedido;
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
}
