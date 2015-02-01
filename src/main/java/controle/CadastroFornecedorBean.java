package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Fornecedor;
import modelo.Endereco;
import modelo.Status;
import modelo.TipoPessoa;
import servico.FornecedorService;
import servico.NegocioException;
import util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private FacesMessages message;
	
	private Fornecedor fornecedor;
	private Endereco endereco;
	private List<Status> statuss;
	private List<TipoPessoa> tipo;

	public CadastroFornecedorBean() {
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
			this.fornecedorService.salvar(this.fornecedor);
			limpar();
			message.info("Fornecedor salvo com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
		this.limpar();
	}

	public void limpar() {
		this.fornecedor = new Fornecedor();
		this.fornecedor.setEndereco(new Endereco());
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
