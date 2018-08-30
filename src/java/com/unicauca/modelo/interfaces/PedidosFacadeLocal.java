/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.interfaces;

import com.unicauca.accesodatos.entidades.Cliente;
import com.unicauca.accesodatos.entidades.Pedido;
import com.unicauca.accesodatos.entidades.Usuario;
import java.util.List;

/**
 *
 * @author sahydo
 */
public interface PedidosFacadeLocal {
    List<Pedido> buscarTodosLosPedidos();
    Pedido buscarPedido(Long id);
    Pedido editarPedido(Pedido pedido, Usuario usuario);
    Pedido guardarPedido(Pedido pedido, Usuario usuario);
    boolean eliminarPedido(Pedido pedido, Usuario usuario);
    
    public Pedido asignarPedido(Long idUsuario, Long idPedido);
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuario);
    List<Pedido> obtenerPedidosPorCliente(Cliente cliente);
    void generarFactura(Pedido pedido, Usuario usuario, Cliente cliente);
}
