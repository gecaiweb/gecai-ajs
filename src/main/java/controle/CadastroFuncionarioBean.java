package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Endereco;
import modelo.Funcao;
import modelo.Funcionario;
import modelo.Lotacao;
import modelo.Nacionalidade;
import modelo.Status;
import modelo.StatusFuncionario;
import modelo.TipoPessoa;
import servico.FuncionarioService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.FuncaoDAO;
import dao.LotacaoDAO;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private FuncaoDAO funcaoDAO;
	
	@Inject
	private LotacaoDAO lotacaoDAO;
	
	@Inject
	private FacesMessages message;
	
	private Funcionario funcionario;
	private Endereco endereco;
	private List<Status> statuss;
	private List<StatusFuncionario> statussFuncionario;
	private List<Funcao> funcoes;
	private List<TipoPessoa> tipo;
	private List<Lotacao> lotacoes;
	private List<Nacionalidade> nacionalidades;

	public CadastroFuncionarioBean() {
		limpar();
	}

	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.statussFuncionario = Arrays.asList(StatusFuncionario.values());
		this.statuss = Arrays.asList(Status.values());
		this.funcoes = funcaoDAO.buscarTodas();
		this.tipo = Arrays.asList(TipoPessoa.values());
		this.lotacoes = lotacaoDAO.buscarTodos();
		this.nacionalidades = Arrays.asList(Nacionalidade.values());
	}

	public void salvar() {
		try {
			this.funcionarioService.salvar(this.funcionario);
			limpar();
			message.info("Funcionário salvo com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
		this.limpar();
	}

	public void limpar() {
		this.funcionario = new Funcionario();
		this.funcionario.setEndereco(new Endereco());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<StatusFuncionario> getStatussFuncionario() {
		return statussFuncionario;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public List<TipoPessoa> getTipo() {
		return tipo;
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public List<Nacionalidade> getNacionalidades() {
		return nacionalidades;
	}
	
}
