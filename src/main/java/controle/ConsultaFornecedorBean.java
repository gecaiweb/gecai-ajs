package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Fornecedor;
import modelo.Status;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.FornecedorDAO;
import dao.filtro.FornecedorFilter;

@Named
@ViewScoped
public class ConsultaFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@Inject 
	private FacesMessages message;
	
	private FornecedorFilter filtro;
	private List<Fornecedor> fornecedoresFiltrados;
	private List<Status> statuss;
	private Fornecedor fornecedorSelecionado;
	
	public ConsultaFornecedorBean() {
		filtro = new FornecedorFilter();
	}
	
	public void consultar() {
		fornecedoresFiltrados = fornecedorDAO.filtrados(filtro);
	}

	@PostConstruct
	public void init() {
		this.statuss = Arrays.asList(Status.values());
	}
	
	public void excluir() {
		try {
			fornecedorDAO.excluir(fornecedorSelecionado);
			this.fornecedoresFiltrados.remove(fornecedorSelecionado);
			message.info("Fornecedor " + fornecedorSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
	}
	
	public FornecedorFilter getFiltro() {
		return filtro;
	}
	public void setFiltro(FornecedorFilter filtro) {
		this.filtro = filtro;
	}

	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}
	public void setFornecedoresFiltrados(List<Fornecedor> fornecedoresFiltrados) {
		this.fornecedoresFiltrados = fornecedoresFiltrados;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}
	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

}
