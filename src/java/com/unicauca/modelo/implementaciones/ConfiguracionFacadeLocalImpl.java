/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.implementaciones;

import com.unicauca.modelo.ejbs.configuracion.TblEstadoFacade;
import com.unicauca.modelo.ejbs.configuracion.TblTerminalFacade;
import com.unicauca.modelo.interfaces.ConfiguracionFacadeLocal;
import javax.ejb.EJB;

/**
 *
 * @author sahydo
 */
public class ConfiguracionFacadeLocalImpl implements ConfiguracionFacadeLocal{
    @EJB
    private TblEstadoFacade estadoFacade;
    @EJB
    private TblTerminalFacade terminalFacade;
}
