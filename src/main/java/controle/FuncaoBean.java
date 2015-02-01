package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Funcao;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.FuncaoService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.FuncaoDAO;

@Named
@ViewScoped
public class FuncaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncaoDAO funcaoDAO;

	@Inject
    private FuncaoService funcaoService;
	
	@Inject
	private FacesMessages messages;

    private Funcao funcao = new Funcao();
    private List<Funcao> funcoesFiltradas;
    private List<Status> statuss;
    private Funcao funcaoSelecionada;

    public FuncaoBean() {
    	funcoesFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.funcaoService.salvar(funcao);
			consultar();
			messages.info("Funcao salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmFuncao:messages", "frmFuncao:tblFuncao"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	funcao = new Funcao();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	funcoesFiltradas = funcaoDAO.buscarTodas();
    }
 
    public void excluir() {
        funcaoDAO.excluir(funcaoSelecionada);
        funcaoSelecionada = null;

        consultar();

        messages.info("Funcao excluída com sucesso!");
    }
    
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public List<Funcao> getFuncoesFiltradas() {
		return funcoesFiltradas;
	}
	public void setFuncoesFiltradas(List<Funcao> funcoesFiltradas) {
		this.funcoesFiltradas = funcoesFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Funcao getFuncaoSelecionada() {
		return funcaoSelecionada;
	}
	public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
		this.funcaoSelecionada = funcaoSelecionada;
	}
	
}