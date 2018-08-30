package com.unicauca.presentacion.web.tienda;

import com.unicauca.accesodatos.entidades.ProductoTienda;
import com.unicauca.presentacion.web.util.JsfUtil;
import com.unicauca.presentacion.web.util.JsfUtil.PersistAction;
import com.unicauca.modelo.ejbs.tienda.TblProductoTiendaFacade;

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

@Named("tblProductoTiendaController")
@ApplicationScoped
public class TblProductoTiendaController implements Serializable {

    @EJB
    private com.unicauca.modelo.ejbs.tienda.TblProductoTiendaFacade ejbFacade;
    private List<ProductoTienda> items = null;
    private ProductoTienda selected;

    public TblProductoTiendaController() {
    }

    public ProductoTienda getSelected() {
        return selected;
    }

    public void setSelected(ProductoTienda selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblProductoTiendaFacade getFacade() {
        return ejbFacade;
    }

    public ProductoTienda prepareCreate() {
        selected = new ProductoTienda();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTienda").getString("TblProductoTiendaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTienda").getString("TblProductoTiendaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTienda").getString("TblProductoTiendaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProductoTienda> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTienda").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTienda").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ProductoTienda getTblProductoTienda(Long id) {
        return getFacade().find(id);
    }

    public List<ProductoTienda> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProductoTienda> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ProductoTienda.class)
    public static class TblProductoTiendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblProductoTiendaController controller = (TblProductoTiendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblProductoTiendaController");
            return controller.getTblProductoTienda(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = new Long(value);
            return key;
        }

        String getStringKey(Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProductoTienda) {
                ProductoTienda o = (ProductoTienda) object;
                return getStringKey(o.getIdProductoTienda());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProductoTienda.class.getName()});
                return null;
            }
        }

    }

}
