/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs;

import com.unicauca.entidades.TblUsuario;

/**
 *
 * @author sahydo
 */
public interface LoginDao {
    TblUsuario validate(String user, String password);
}
