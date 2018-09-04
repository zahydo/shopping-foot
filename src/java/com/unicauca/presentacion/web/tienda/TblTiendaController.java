package com.unicauca.presentacion.web.tienda;

import com.unicauca.accesodatos.entidades.Tienda;
import com.unicauca.presentacion.util.JsfUtil;
import com.unicauca.presentacion.util.JsfUtil.PersistAction;
import com.unicauca.modelo.ejbs.tienda.TblTiendaFacade;

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

@Named("tblTiendaController")
@ApplicationScoped
public class TblTiendaController implements Serializable {

    @EJB
    private com.unicauca.modelo.ejbs.tienda.TblTiendaFacade ejbFacade;
    private List<Tienda> items = null;
    private Tienda selected;

    public TblTiendaController() {
    }

    public Tienda getSelected() {
        return selected;
    }

    public void setSelected(Tienda selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblTiendaFacade getFacade() {
        return ejbFacade;
    }

    public Tienda prepareCreate() {
        selected = new Tienda();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTiendaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTiendaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleRecursos").getString("TblTiendaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tienda> getItems() {
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

    public Tienda getTblTienda(Long id) {
        return getFacade().find(id);
    }

    public List<Tienda> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tienda> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tienda.class)
    public static class TblTiendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblTiendaController controller = (TblTiendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblTiendaController");
            return controller.getTblTienda(getKey(value));
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
            if (object instanceof Tienda) {
                Tienda o = (Tienda) object;
                return getStringKey(o.getIdTienda());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tienda.class.getName()});
                return null;
            }
        }

    }

}
