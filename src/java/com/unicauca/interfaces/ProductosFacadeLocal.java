/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.interfaces;

import com.unicauca.entidades.Producto;


/**
 *
 * @author sahydo
 */
public interface ProductosFacadeLocal {
    boolean hayUnidadesDisponiblesDe(Long idProducto);
    Producto buscarProducto(Long idProducto);
    void editarProducto(Producto producto);
}
