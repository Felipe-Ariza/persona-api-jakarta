package co.edu.javeriana.as.payara.jakarta.personapp.web.controller;

import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Estudios;
import co.edu.javeriana.as.payara.jakarta.personapp.web.controller.util.JsfUtil;
import co.edu.javeriana.as.payara.jakarta.personapp.web.controller.util.PaginationHelper;
import co.edu.javeriana.as.payara.jakarta.personapp.ejb.bean.impl.EstudiosFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("estudiosController")
@SessionScoped
public class EstudiosController implements Serializable {

    private Estudios current;
    private DataModel items = null;
    @EJB
    private EstudiosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EstudiosController() {
    }

    public Estudios getSelected() {
        if (current == null) {
            current = new Estudios();
            current.setEstudiosPK(new co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private EstudiosFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Estudios) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Estudios();
        current.setEstudiosPK(new co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getEstudiosPK().setIdProf(current.getProfesion().getId());
            current.getEstudiosPK().setCcPer(current.getPersona().getCc());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EstudiosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Estudios) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getEstudiosPK().setIdProf(current.getProfesion().getId());
            current.getEstudiosPK().setCcPer(current.getPersona().getCc());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EstudiosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Estudios) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EstudiosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Estudios getEstudios(co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Estudios.class)
    public static class EstudiosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstudiosController controller = (EstudiosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estudiosController");
            return controller.getEstudios(getKey(value));
        }

        co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK getKey(String value) {
            co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK();
            key.setIdProf(Integer.parseInt(values[0]));
            key.setCcPer(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdProf());
            sb.append(SEPARATOR);
            sb.append(value.getCcPer());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estudios) {
                Estudios o = (Estudios) object;
                return getStringKey(o.getEstudiosPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Estudios.class.getName());
            }
        }

    }

}
