package com.unicauca.presentacion.controlador.usuarios;

import com.unicauca.accesodatos.entidades.PermisoUsuario;
import com.unicauca.presentacion.util.JsfUtil;
import com.unicauca.presentacion.util.JsfUtil.PersistAction;
import com.unicauca.modelo.ejbs.usuarios.TblPermisoUsuarioFacade;

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

@Named("tblPermisoUsuarioController")
@ApplicationScoped
public class TblPermisoUsuarioController implements Serializable {

    @EJB
    private com.unicauca.modelo.ejbs.usuarios.TblPermisoUsuarioFacade ejbFacade;
    private List<PermisoUsuario> items = null;
    private PermisoUsuario selected;

    public TblPermisoUsuarioController() {
    }

    public PermisoUsuario getSelected() {
        return selected;
    }

    public void setSelected(PermisoUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TblPermisoUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public PermisoUsuario prepareCreate() {
        selected = new PermisoUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblPermisoUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblPermisoUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleUsuarios").getString("TblPermisoUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PermisoUsuario> getItems() {
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

    public PermisoUsuario getTblPermisoUsuario(Long id) {
        return getFacade().find(id);
    }

    public List<PermisoUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PermisoUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PermisoUsuario.class)
    public static class TblPermisoUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblPermisoUsuarioController controller = (TblPermisoUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblPermisoUsuarioController");
            return controller.getTblPermisoUsuario(getKey(value));
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
            if (object instanceof PermisoUsuario) {
                PermisoUsuario o = (PermisoUsuario) object;
                return getStringKey(o.getIdPermisoUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PermisoUsuario.class.getName()});
                return null;
            }
        }

    }

}
