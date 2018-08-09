/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.ejbs.usuarios;

import com.unicauca.ejbs.AbstractFacade;
import com.unicauca.ejbs.LoginDao;
import com.unicauca.entidades.TblUsuario;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author sahydo
 */
@Stateless
public class LoginFacade implements LoginDao {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    public TblUsuario validate(String user, String password) {
        TypedQuery<TblUsuario> query = em.createNamedQuery(TblUsuario.VALIDATE_USER, TblUsuario.class);
        query.setParameter("username", user);
        query.setParameter("contrasena", password);
        TblUsuario usuario = query.getSingleResult();
        return usuario;
    }
}
