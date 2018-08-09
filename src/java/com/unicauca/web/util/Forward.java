/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.web.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author sahydo
 */
public class Forward {
    private String action;
    private String actionParam;

    public void navigate(PhaseEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outcome = action; // Do your thing?
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, outcome);
    }
}
