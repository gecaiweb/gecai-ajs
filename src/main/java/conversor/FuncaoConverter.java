package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Funcao;
import util.cdi.CDIServiceLocator;
import dao.FuncaoDAO;

@FacesConverter(forClass = Funcao.class)
public class FuncaoConverter implements Converter {

	private FuncaoDAO funcaoDAO;
	
	public FuncaoConverter() {
		funcaoDAO = CDIServiceLocator.getBean(FuncaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcao retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = funcaoDAO.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Funcao) value).getCodigo().toString();
		}
		
		return "";
	}

}
