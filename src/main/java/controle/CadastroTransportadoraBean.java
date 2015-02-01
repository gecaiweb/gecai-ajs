package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Endereco;
import modelo.Status;
import modelo.TipoPessoa;
import modelo.Transportadora;
import servico.NegocioException;
import servico.TransportadoraService;
import util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroTransportadoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TransportadoraService transportadoraService;
	
	@Inject
	private FacesMessages message;
	
	private Transportadora transportadora;
	private Endereco endereco;
	private List<Status> statuss;
	private List<TipoPessoa> tipo;

	public CadastroTransportadoraBean() {
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
			this.transportadoraService.salvar(this.transportadora);
			limpar();
			message.info("Transportadora salva com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
		this.limpar();
	}

	public void limpar() {
		this.transportadora = new Transportadora();
		this.transportadora.setEndereco(new Endereco());
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
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
