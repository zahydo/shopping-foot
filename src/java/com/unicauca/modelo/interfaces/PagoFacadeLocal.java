/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.interfaces;


/**
 *
 * @author sahydo
 */
public interface PagoFacadeLocal {
    void configurarAPIdePago();
    boolean validarMetodoPago();
    boolean cancelarPago(Long idPago);
}
