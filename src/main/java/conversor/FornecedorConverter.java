package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Fornecedor;
import util.cdi.CDIServiceLocator;
import dao.FornecedorDAO;

@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

	private FornecedorDAO fornecedorDAO;

	public FornecedorConverter() {
		fornecedorDAO = CDIServiceLocator.getBean(FornecedorDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fornecedor retorno = null;
		if (value != null) {
			retorno = this.fornecedorDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Fornecedor) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}
