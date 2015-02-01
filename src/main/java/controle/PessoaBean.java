package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Pessoa;
import modelo.Status;
import modelo.TipoPessoa;

import org.primefaces.context.RequestContext;

import servico.NegocioException;
import servico.PessoaService;
import util.jsf.FacesMessages;
import dao.PessoaDAO;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDAO pessoaDAO;

	@Inject
    private PessoaService pessoaService;
	
	@Inject
	private FacesMessages messages;

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoasFiltradas;
    private List<Status> statuss;
    private List<TipoPessoa> tiposPessoa;
    private Pessoa pessoaSelecionada;

    public PessoaBean() {
    	pessoasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.pessoaService.salvar(pessoa);
			consultar();
			messages.info("Pessoa salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmPessoa:messages", "frmPessoa:tblPessoa"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	pessoa = new Pessoa();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    	tiposPessoa = Arrays.asList(TipoPessoa.values());
    }
    
    public void consultar() {
    	pessoasFiltradas = pessoaDAO.buscarTodos();
    }
 
    public void excluir() {
        try {
			pessoaDAO.excluir(pessoaSelecionada);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pessoaSelecionada = null;

        consultar();

        messages.info("Pessoa excluída com sucesso!");
    }
    
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoasFiltradas() {
		return pessoasFiltradas;
	}
	public void setPessoasFiltradas(List<Pessoa> pessoasFiltradas) {
		this.pessoasFiltradas = pessoasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public List<TipoPessoa> getTiposPessoa() {
		return tiposPessoa;
	}
	
}