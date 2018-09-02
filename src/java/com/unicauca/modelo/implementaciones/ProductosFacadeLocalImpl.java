/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.modelo.ejbs.productos.TblProductoFacade;
import com.unicauca.accesodatos.entidades.Producto;
import com.unicauca.modelo.ejbs.productos.TblCategoriaFacade;
import com.unicauca.modelo.ejbs.productos.TblIngredienteFacade;
import com.unicauca.modelo.ejbs.productos.TblIngredienteProductoFacade;
import com.unicauca.modelo.interfaces.ProductosFacadeLocal;
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
    @EJB
    private TblIngredienteFacade ingredienteFacade;
    @EJB
    private TblIngredienteProductoFacade ingredienteProductoFacade;
    @EJB
    private TblCategoriaFacade categoriaFacade;

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
