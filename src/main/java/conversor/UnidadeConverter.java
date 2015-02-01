package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Unidade;
import util.cdi.CDIServiceLocator;
import dao.UnidadeDAO;

@FacesConverter(forClass = Unidade.class)
public class UnidadeConverter implements Converter {
    
	private UnidadeDAO unidadeDAO;

    public UnidadeConverter() {
        this.unidadeDAO = CDIServiceLocator.getBean(UnidadeDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        Unidade retorno = null;
        if (value != null) {
            retorno = this.unidadeDAO.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            Long codigo = ((Unidade) value).getCodigo();
            return codigo == null ? null : codigo.toString();
        }
        return "";
    }
}
