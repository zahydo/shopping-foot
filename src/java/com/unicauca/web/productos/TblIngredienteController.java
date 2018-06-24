package com.unicauca.web.productos;

import com.unicauca.entidades.TblIngrediente;
import com.unicauca.web.util.JsfUtil;
import com.unicauca.web.util.JsfUtil.PersistAction;
import com.unicauca.ejbs.productos.TblIngredienteFacade;

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

@Named("tblIngredienteController")
@ApplicationScoped
public class TblIngredienteController implements Serializable {

    @EJB
    private com.unicauca.ejbs.productos.TblIngredienteFacade ejbFacade;
    private List<TblIngrediente> items = null;
    private TblIngrediente selected;

    public TblIngredienteController() {
    }

    public TblIngrediente getSelected() {
        return selected;
    }

    public void setSelected(TblIngrediente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblIngredienteFacade getFacade() {
        return ejbFacade;
    }

    public TblIngrediente prepareCreate() {
        selected = new TblIngrediente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleIngredientesProductos").getString("TblIngredienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleIngredientesProductos").getString("TblIngredienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleIngredientesProductos").getString("TblIngredienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TblIngrediente> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleIngredientesProductos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleIngredientesProductos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TblIngrediente getTblIngrediente(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<TblIngrediente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TblIngrediente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TblIngrediente.class)
    public static class TblIngredienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblIngredienteController controller = (TblIngredienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblIngredienteController");
            return controller.getTblIngrediente(getKey(value));
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
            if (object instanceof TblIngrediente) {
                TblIngrediente o = (TblIngrediente) object;
                return getStringKey(o.getIdIngrediente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TblIngrediente.class.getName()});
                return null;
            }
        }

    }

}
