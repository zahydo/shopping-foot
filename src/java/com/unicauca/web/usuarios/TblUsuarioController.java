package com.unicauca.web.usuarios;

import com.unicauca.entidades.TblUsuario;
import com.unicauca.web.util.JsfUtil;
import com.unicauca.web.util.JsfUtil.PersistAction;
import com.unicauca.ejbs.usuarios.TblUsuarioFacade;

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

@Named("tblUsuarioController")
@ApplicationScoped
public class TblUsuarioController implements Serializable {

    @EJB
    private com.unicauca.ejbs.usuarios.TblUsuarioFacade ejbFacade;
    private List<TblUsuario> items = null;
    private TblUsuario selected;

    public TblUsuarioController() {
    }

    public TblUsuario getSelected() {
        return selected;
    }

    public void setSelected(TblUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public TblUsuario prepareCreate() {
        selected = new TblUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TblUsuario> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleUsuarios").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleUsuarios").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TblUsuario getTblUsuario(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<TblUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TblUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TblUsuario.class)
    public static class TblUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblUsuarioController controller = (TblUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblUsuarioController");
            return controller.getTblUsuario(getKey(value));
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
            if (object instanceof TblUsuario) {
                TblUsuario o = (TblUsuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TblUsuario.class.getName()});
                return null;
            }
        }

    }

}
