/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.interfaces;

import com.unicauca.entidades.Cliente;
import com.unicauca.entidades.Pedido;
import com.unicauca.entidades.Usuario;
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
    Pedido asignarPedido(Usuario usuario, Pedido pedido);
    List<Pedido> obtenerPedidosPorUsuario(Usuario usuario);
    List<Pedido> obtenerPedidosPorCliente(Cliente cliente);
    void generarFactura(Pedido pedido, Usuario usuario, Cliente cliente);
    
    
    
}
