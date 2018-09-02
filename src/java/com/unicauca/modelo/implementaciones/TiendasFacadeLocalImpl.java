/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.modelo.ejbs.tienda.TblProductoTiendaFacade;
import com.unicauca.modelo.ejbs.tienda.TblTiendaFacade;
import javax.ejb.EJB;
import com.unicauca.modelo.interfaces.TiendasFacadeLocal;

/**
 *
 * @author sahydo
 */
public class TiendasFacadeLocalImpl implements TiendasFacadeLocal{
    @EJB
    private TblTiendaFacade tiendaFacade;
    @EJB
    private TblProductoTiendaFacade productoTiendaFacade;
}
