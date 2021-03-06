/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.modelo.ejbs.usuarios;

import com.unicauca.modelo.ejbs.AbstractFacade;
import com.unicauca.accesodatos.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.unicauca.modelo.interfaces.UsuariosFacadeLocal;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblUsuarioFacade extends AbstractFacade<Usuario>{

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblUsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario inicarSesion(Usuario user) {
        Usuario usuario = null;
        String consulta;
        try {
            Usuario usuarioTemporal = null;
            consulta = "SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1 and u.contrasena = ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, user.getNombreUsuario());
            query.setParameter(2, user.getContrasena());
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuarioTemporal = lista.get(0);
            }
            if (usuarioTemporal != null) {
                usuario = em.find(Usuario.class, usuarioTemporal.getIdUsuario());
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
}
