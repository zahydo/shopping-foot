package com.unicauca.presentacion.web.pedidos;

import com.unicauca.accesodatos.entidades.Pedido;
import com.unicauca.presentacion.web.util.JsfUtil;
import com.unicauca.presentacion.web.util.JsfUtil.PersistAction;
import com.unicauca.presentacion.web.util.SessionUtils;

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
    private com.unicauca.modelo.interfaces.PedidosFacadeLocal ejbFacade;
    private List<Pedido> items = null;
    private Pedido selected;

    public TblPedidoController() {
    }

    public Pedido getSelected() {
        return selected;
    }

    public void setSelected(Pedido selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Pedido prepareCreate() {
        selected = new Pedido();
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

    public List<Pedido> getItems() {
        if (items == null) {
            items = ejbFacade.buscarTodosLosPedidos();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    switch(persistAction){
                            case CREATE:
                                if(ejbFacade.guardarPedido(selected, SessionUtils.getUsuarioSession()) == null)
                                    JsfUtil.addErrorMessage("No se pudo agregar el pedido");
                                else
                                    JsfUtil.addSuccessMessage(successMessage);
                                break;
                            case UPDATE:
                                if(ejbFacade.editarPedido(selected, SessionUtils.getUsuarioSession()) == null)
                                    JsfUtil.addErrorMessage("No se pudo agregar el pedido");
                                else
                                    JsfUtil.addSuccessMessage(successMessage);
                                break;
                            default:
                                if(ejbFacade.editarPedido(selected, SessionUtils.getUsuarioSession()) == null)
                                    JsfUtil.addErrorMessage("No se pudo agregar el pedido");
                                else
                                    JsfUtil.addSuccessMessage(successMessage);
                                break;
                    }
                } else {
                    if(ejbFacade.eliminarPedido(selected, SessionUtils.getUsuarioSession()))
                        JsfUtil.addSuccessMessage(successMessage);
                    else
                        JsfUtil.addErrorMessage("No se pudo agregar el pedido");
                }
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

    public Pedido getTblPedido(Long id) {
        return ejbFacade.buscarPedido(id);
    }

    public List<Pedido> getItemsAvailableSelectMany() {
        return ejbFacade.buscarTodosLosPedidos();
    }

    public List<Pedido> getItemsAvailableSelectOne() {
        return ejbFacade.buscarTodosLosPedidos();
    }

    @FacesConverter(forClass = Pedido.class)
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
            if (object instanceof Pedido) {
                Pedido o = (Pedido) object;
                return getStringKey(o.getIdPedido());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pedido.class.getName()});
                return null;
            }
        }

    }
    
    public List<Pedido> pedidosPorUsuario(Long idUsuario){
        return ejbFacade.obtenerPedidosPorUsuario(idUsuario);
    }
}
