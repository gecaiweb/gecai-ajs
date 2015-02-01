package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Lotacao;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.LotacaoService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.LotacaoDAO;

@Named
@ViewScoped
public class LotacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LotacaoDAO lotacaoDAO;

	@Inject
    private LotacaoService lotacaoService;
	
	@Inject
	private FacesMessages messages;

    private Lotacao lotacao = new Lotacao();
    private List<Lotacao> lotacoesFiltradas;
    private List<Status> statuss;
    private Lotacao lotacaoSelecionada;

    public LotacaoBean() {
    	lotacoesFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.lotacaoService.salvar(lotacao);
			consultar();
			messages.info("Lotacao salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmLotacao:messages", "frmLotacao:tblLotacao"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	lotacao = new Lotacao();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	lotacoesFiltradas = lotacaoDAO.buscarTodos();
    }
 
    public void excluir() {
        lotacaoDAO.excluir(lotacaoSelecionada);
        lotacaoSelecionada = null;

        consultar();

        messages.info("Lotacao excluída com sucesso!");
    }
    
	public Lotacao getLotacao() {
		return lotacao;
	}
	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public List<Lotacao> getLotacoesFiltradas() {
		return lotacoesFiltradas;
	}
	public void setLotacoesFiltradas(List<Lotacao> lotacoesFiltradas) {
		this.lotacoesFiltradas = lotacoesFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Lotacao getLotacaoSelecionada() {
		return lotacaoSelecionada;
	}
	public void setLotacaoSelecionada(Lotacao lotacaoSelecionada) {
		this.lotacaoSelecionada = lotacaoSelecionada;
	}
	
}