/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.interfaces;

import com.unicauca.entidades.TblIngrediente;
import com.unicauca.entidades.TblProducto;
import java.util.List;

/**
 *
 * @author sahydo
 */
public interface Productos {
    public abstract List<TblProducto> listarProductos();
    public abstract List<TblProducto> listarProductosPorEstatus(int idEstatus);
    public abstract List<TblProducto> listarProductosPorTerminal(int idTerminal);
    public abstract List<TblProducto> listarProductosPorTienda(int idTienda);
    public abstract List<TblProducto> listarProductosPorCategoria(int idCategoria);
    
    public abstract List<TblIngrediente> listarIngredientes();
    public abstract List<TblIngrediente> listarIngredientesPorProducto(int idProducto);
    
    public abstract boolean agregarProducto(TblProducto producto);
    public abstract boolean editarProducto(int idProductoAnterior, TblProducto producto);
    public abstract boolean eliminarProducto(int idProducto);
    
    public abstract boolean agregarIngrediente(TblIngrediente ingrediente);
    public abstract boolean editarIngrediente(int idIngredienteAnterior, TblIngrediente ingrediente);
    public abstract boolean eliminarIngrediente(int idIngrediente);
    
    public abstract boolean agregarIngrediente_a_producto(TblIngrediente ingrediente, TblProducto producto);
}
