/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.productos;

import com.unicauca.entidades.Producto;
import com.unicauca.interfaces.ProductosFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sahydo
 */
@Stateless
public class ProductosFacadeLocalImpl implements ProductosFacadeLocal{
    
    @EJB
    private TblProductoFacade productoFacade;

    @Override
    public boolean hayUnidadesDisponiblesDe(Long idProducto) {
        Producto producto = productoFacade.find(idProducto);
        return producto.getDisponibles() > 0;
    }

    @Override
    public Producto buscarProducto(Long idProducto) {
        return productoFacade.find(idProducto);
    }

    @Override
    public void editarProducto(Producto producto) {
        productoFacade.edit(producto);
    }

    
}
