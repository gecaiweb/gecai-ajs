package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Cliente;
import util.cdi.CDIServiceLocator;
import dao.ClienteDAO;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

	private ClienteDAO clienteDAO;

	public ClienteConverter() {
		clienteDAO = CDIServiceLocator.getBean(ClienteDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		if (value != null) {
			retorno = this.clienteDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Cliente) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}
