package com.unicauca.web.configuracion;

import com.unicauca.entidades.TblTerminal;
import com.unicauca.ejbs.configuracion.TblTerminalFacade;
import com.unicauca.web.util.JsfUtil;
import com.unicauca.web.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tblTerminalController")
@ApplicationScoped
public class TblTerminalController implements Serializable {

    @EJB
    private com.unicauca.ejbs.configuracion.TblTerminalFacade ejbFacade;
    private List<TblTerminal> items = null;
    private TblTerminal selected;

    public TblTerminalController() {
    }

    public TblTerminal getSelected() {
        return selected;
    }

    public void setSelected(TblTerminal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblTerminalFacade getFacade() {
        return ejbFacade;
    }

    public TblTerminal prepareCreate() {
        selected = new TblTerminal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTerminalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTerminalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTerminalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TblTerminal> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleRecursos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleRecursos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TblTerminal getTblTerminal(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<TblTerminal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TblTerminal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TblTerminal.class)
    public static class TblTerminalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblTerminalController controller = (TblTerminalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblTerminalController");
            return controller.getTblTerminal(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TblTerminal) {
                TblTerminal o = (TblTerminal) object;
                return getStringKey(o.getIdTerminal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TblTerminal.class.getName()});
                return null;
            }
        }

    }

}
