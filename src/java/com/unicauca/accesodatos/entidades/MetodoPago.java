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
public class MetodoPago {

    private Integer id;
    private String numeroUnico;
    private Integer saldo;
    private String nombreTitular;

    public MetodoPago() {

    }

    public MetodoPago(Integer id, String numeroUnico, Integer saldo, String nombreTitular) {
        this.id = id;
        this.numeroUnico = numeroUnico;
        this.saldo = saldo;
        this.nombreTitular = nombreTitular;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
