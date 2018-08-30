/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.accesodatos.entidades;

/**
 *
 * @author sahydo
 */
public class Pago {
    private Long saldo;

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Pago(Long saldo) {
        this.saldo = saldo;
    }
    
    public Pago(){
        
    }
}
