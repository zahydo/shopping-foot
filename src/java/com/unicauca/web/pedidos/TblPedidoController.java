package com.unicauca.web.pedidos;

import com.unicauca.entidades.TblPedido;
import com.unicauca.web.util.JsfUtil;
import com.unicauca.web.util.JsfUtil.PersistAction;
import com.unicauca.ejbs.pedidos.TblPedidoFacade;

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

@Named("tblPedidoController")
@ApplicationScoped
public class TblPedidoController implements Serializable {

    @EJB
    private com.unicauca.ejbs.pedidos.TblPedidoFacade ejbFacade;
    private List<TblPedido> items = null;
    private TblPedido selected;

    public TblPedidoController() {
    }

    public TblPedido getSelected() {
        return selected;
    }

    public void setSelected(TblPedido selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblPedidoFacade getFacade() {
        return ejbFacade;
    }

    public TblPedido prepareCreate() {
        selected = new TblPedido();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePedidos").getString("TblPedidoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePedidos").getString("TblPedidoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePedidos").getString("TblPedidoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TblPedido> getItems() {
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

    public TblPedido getTblPedido(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<TblPedido> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TblPedido> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TblPedido.class)
    public static class TblPedidoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblPedidoController controller = (TblPedidoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblPedidoController");
            return controller.getTblPedido(getKey(value));
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
            if (object instanceof TblPedido) {
                TblPedido o = (TblPedido) object;
                return getStringKey(o.getIdPedido());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TblPedido.class.getName()});
                return null;
            }
        }

    }

}
