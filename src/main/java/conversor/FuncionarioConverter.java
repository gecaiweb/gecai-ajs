package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Funcionario;
import util.cdi.CDIServiceLocator;
import dao.FuncionarioDAO;

@FacesConverter("funcionarioConverter")
public class FuncionarioConverter implements Converter {

	private FuncionarioDAO funcionarioDAO;

	public FuncionarioConverter() {
		funcionarioDAO = CDIServiceLocator.getBean(FuncionarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;
		if (value != null) {
			retorno = this.funcionarioDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Funcionario) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}
