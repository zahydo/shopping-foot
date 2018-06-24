package com.unicauca.web.pedidos;

import com.unicauca.entidades.TblCliente;
import com.unicauca.web.util.JsfUtil;
import com.unicauca.web.util.JsfUtil.PersistAction;
import com.unicauca.ejbs.pedidos.TblClienteFacade;

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

@Named("tblClienteController")
@ApplicationScoped
public class TblClienteController implements Serializable {

    @EJB
    private com.unicauca.ejbs.pedidos.TblClienteFacade ejbFacade;
    private List<TblCliente> items = null;
    private TblCliente selected;

    public TblClienteController() {
    }

    public TblCliente getSelected() {
        return selected;
    }

    public void setSelected(TblCliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblClienteFacade getFacade() {
        return ejbFacade;
    }

    public TblCliente prepareCreate() {
        selected = new TblCliente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePedidos").getString("TblClienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePedidos").getString("TblClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePedidos").getString("TblClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TblCliente> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePedidos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePedidos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TblCliente getTblCliente(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<TblCliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TblCliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TblCliente.class)
    public static class TblClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblClienteController controller = (TblClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblClienteController");
            return controller.getTblCliente(getKey(value));
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
            if (object instanceof TblCliente) {
                TblCliente o = (TblCliente) object;
                return getStringKey(o.getIdCliente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TblCliente.class.getName()});
                return null;
            }
        }

    }

}
