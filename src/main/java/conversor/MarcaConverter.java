package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Marca;
import util.cdi.CDIServiceLocator;
import dao.MarcaDAO;

@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter {
    
	private MarcaDAO marcaDAO;

    public MarcaConverter() {
        this.marcaDAO = CDIServiceLocator.getBean(MarcaDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        Marca retorno = null;
        if (value != null) {
            retorno = this.marcaDAO.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            Long codigo = ((Marca) value).getCodigo();
            return codigo == null ? null : codigo.toString();
        }
        return "";
    }
}
