package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Cliente;
import modelo.Endereco;
import modelo.Status;
import modelo.TipoPessoa;
import servico.ClienteService;
import servico.NegocioException;
import util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	
	@Inject
	private FacesMessages message;
	
	private Cliente cliente;
	private Endereco endereco;
	private List<Status> statuss;
	private List<TipoPessoa> tipo;

	public CadastroClienteBean() {
		limpar();
	}

	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.statuss = Arrays.asList(Status.values());
		this.tipo = Arrays.asList(TipoPessoa.values());
	}

	public void salvar() {
		try {
			this.clienteService.salvar(this.cliente);
			limpar();
			message.info("Cliente salvo com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
		this.limpar();
	}

	public void limpar() {
		this.cliente = new Cliente();
		this.cliente.setEndereco(new Endereco());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<TipoPessoa> getTipo() {
		return tipo;
	}

	public List<Status> getStatuss() {
		return statuss;
	}
	
}
