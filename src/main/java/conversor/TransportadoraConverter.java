package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Transportadora;
import util.cdi.CDIServiceLocator;
import dao.TransportadoraDAO;

@FacesConverter("transportadoraConverter")
public class TransportadoraConverter implements Converter {

	private TransportadoraDAO transportadoraDAO;

	public TransportadoraConverter() {
		transportadoraDAO = CDIServiceLocator.getBean(TransportadoraDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Transportadora retorno = null;
		if (value != null) {
			retorno = this.transportadoraDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Transportadora) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}
