package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Unidade;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.UnidadeService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.UnidadeDAO;

@Named
@ViewScoped
public class UnidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeDAO unidadeDAO;

	@Inject
    private UnidadeService unidadeService;
	
	@Inject
	private FacesMessages messages;

    private Unidade unidade = new Unidade();
    private List<Unidade> unidadesFiltradas;
    private List<Status> statuss;
    private Unidade unidadeSelecionada;

    public UnidadeBean() {
    	unidadesFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.unidadeService.salvar(unidade);
			consultar();
			messages.info("Unidade salvo com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmUnidade:messages", "frmUnidade:tblUnidade"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	unidade = new Unidade();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	unidadesFiltradas = unidadeDAO.buscarTodos();
    }
 
    public void excluir() {
        unidadeDAO.excluir(unidadeSelecionada);
        unidadeSelecionada = null;

        consultar();

        messages.info("Unidade excluída com sucesso!");
    }
    
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getUnidadesFiltradas() {
		return unidadesFiltradas;
	}
	public void setUnidadesFiltradas(List<Unidade> unidadesFiltradas) {
		this.unidadesFiltradas = unidadesFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Unidade getUnidadeSelecionada() {
		return unidadeSelecionada;
	}
	public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
		this.unidadeSelecionada = unidadeSelecionada;
	}
	
}