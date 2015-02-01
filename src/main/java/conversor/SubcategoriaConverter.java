package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Subcategoria;
import util.cdi.CDIServiceLocator;
import dao.SubcategoriaDAO;

@FacesConverter(forClass = Subcategoria.class)
public class SubcategoriaConverter implements Converter {
    
	private SubcategoriaDAO subcategoriaDAO;

    public SubcategoriaConverter() {
        this.subcategoriaDAO = CDIServiceLocator.getBean(SubcategoriaDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        Subcategoria retorno = null;
        if (value != null) {
            retorno = this.subcategoriaDAO.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            Long codigo = ((Subcategoria) value).getCodigo();
            return codigo == null ? null : codigo.toString();
        }
        return "";
    }
}
