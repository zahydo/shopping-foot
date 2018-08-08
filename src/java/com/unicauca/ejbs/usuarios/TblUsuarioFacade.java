/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.usuarios;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.ejbs.LoginDao;
import com.unicauca.entidades.TblUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author sahydo
 */
@Stateless
public class TblUsuarioFacade extends AbstractFacade<TblUsuario> implements LoginDao {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblUsuarioFacade() {
        super(TblUsuario.class);
    }

    @Override
    public boolean validate(String user, String password) {
        TypedQuery<TblUsuario> query = em.createNamedQuery(TblUsuario.VALIDATE_USER, TblUsuario.class);
        query.setParameter("username", user);
        query.setParameter("contrasena", password);
        TblUsuario usuario = query.getSingleResult();
        return usuario != null;
    }
    
}
