package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Funcionario;
import modelo.StatusFuncionario;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.FuncionarioDAO;
import dao.filtro.FuncionarioFilter;

@Named
@ViewScoped
public class ConsultaFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Inject 
	private FacesMessages message;
	
	private FuncionarioFilter filtro;
	private List<Funcionario> funcionariosFiltrados;
	private List<StatusFuncionario> statussFuncionario;
	private Funcionario funcionarioSelecionado;
	
	public ConsultaFuncionarioBean() {
		filtro = new FuncionarioFilter();
	}
	
	public void consultar() {
		funcionariosFiltrados = funcionarioDAO.filtrados(filtro);
	}

	@PostConstruct
	public void init() {
		this.statussFuncionario = Arrays.asList(StatusFuncionario.values());
	}
	
	public void excluir() {
		try {
			funcionarioDAO.excluir(funcionarioSelecionado);
			this.funcionariosFiltrados.remove(funcionarioSelecionado);
			message.info("Funcionário " + funcionarioSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
	}
	
	public FuncionarioFilter getFiltro() {
		return filtro;
	}
	public void setFiltro(FuncionarioFilter filtro) {
		this.filtro = filtro;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}
	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public List<StatusFuncionario> getStatussFuncionario() {
		return statussFuncionario;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

}
