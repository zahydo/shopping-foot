/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.interfaces;

import com.unicauca.accesodatos.entidades.Usuario;

/**
 *
 * @author sahydo
 */
public interface UsuariosFacadeLocal {
    Usuario inicarSesion(Usuario user);
}
