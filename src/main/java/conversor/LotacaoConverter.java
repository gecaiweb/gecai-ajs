package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Lotacao;
import util.cdi.CDIServiceLocator;
import dao.LotacaoDAO;

@FacesConverter(forClass = Lotacao.class)
public class LotacaoConverter implements Converter {

	private LotacaoDAO lotacaoDAO;
	
	public LotacaoConverter() {
		lotacaoDAO = CDIServiceLocator.getBean(LotacaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lotacao retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = lotacaoDAO.porId(id);
		}
		
		return retorno;
	}

	  @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if (value != null) {
	            Long codigo = ((Lotacao) value).getCodigo();
	            return codigo == null ? null : codigo.toString();
	        }
	        return "";
	    }
}
