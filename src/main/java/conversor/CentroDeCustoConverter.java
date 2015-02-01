package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.CentroDeCusto;
import util.cdi.CDIServiceLocator;
import dao.CentroDeCustoDAO;

@FacesConverter(forClass = CentroDeCusto.class)
public class CentroDeCustoConverter implements Converter {

	private CentroDeCustoDAO centroDeCustoDAO;
	
	public CentroDeCustoConverter() {
		centroDeCustoDAO = CDIServiceLocator.getBean(CentroDeCustoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CentroDeCusto retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = centroDeCustoDAO.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((CentroDeCusto) value).getCodigo().toString();
		}
		
		return "";
	}

}
