package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Grupo;
import util.cdi.CDIServiceLocator;
import dao.GrupoDAO;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {
    
	private GrupoDAO grupoDAO;

    public GrupoConverter() {
        this.grupoDAO = CDIServiceLocator.getBean(GrupoDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        Grupo retorno = null;
        if (value != null) {
            retorno = this.grupoDAO.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            Long codigo = ((Grupo) value).getCodigo();
            return codigo == null ? null : codigo.toString();
        }
        return "";
    }
}
