package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Cliente;
import modelo.Status;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.ClienteDAO;
import dao.filtro.ClienteFilter;

@Named
@ViewScoped
public class ConsultaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject 
	private FacesMessages message;
	
	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	private List<Status> statuss;
	private Cliente clienteSelecionado;
	
	public ConsultaClienteBean() {
		filtro = new ClienteFilter();
	}
	
	public void consultar() {
		clientesFiltrados = clienteDAO.filtrados(filtro);
	}

	@PostConstruct
	public void init() {
		this.statuss = Arrays.asList(Status.values());
	}
	
	public void excluir() {
		try {
			clienteDAO.excluir(clienteSelecionado);
			this.clientesFiltrados.remove(clienteSelecionado);
			message.info("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
	}
	
	public ClienteFilter getFiltro() {
		return filtro;
	}
	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}
	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
