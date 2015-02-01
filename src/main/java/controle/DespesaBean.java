package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.CentroDeCusto;
import modelo.Despesa;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.DespesaService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.CentroDeCustoDAO;
import dao.DespesaDAO;

@Named
@ViewScoped
public class DespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DespesaDAO despesaDAO;

	@Inject
    private DespesaService despesaService;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CentroDeCustoDAO centroDeCustoDAO;

    private Despesa despesa = new Despesa();
    private List<Despesa> despesasFiltradas;
    private List<Status> statuss;
    private Despesa despesaSelecionada;
    private List<CentroDeCusto> centrosDeCusto;

    public DespesaBean() {
    	despesasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.despesaService.salvar(despesa);
			consultar();
			messages.info("Despesa salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmDespesa:messages", "frmDespesa:tblDespesa"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	despesa = new Despesa();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    	this.centrosDeCusto = centroDeCustoDAO.buscarTodos();
    }
    
    public void consultar() {
    	despesasFiltradas = despesaDAO.buscarTodos();
    }
 
    public void excluir() {
        despesaDAO.excluir(despesaSelecionada);
        despesaSelecionada = null;

        consultar();

        messages.info("Despesa excluída com sucesso!");
    }
    
	public Despesa getDespesa() {
		return despesa;
	}
	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getDespesasFiltradas() {
		return despesasFiltradas;
	}
	public void setDespesasFiltradas(List<Despesa> despesasFiltradas) {
		this.despesasFiltradas = despesasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}
	
	public Despesa getDespesaSelecionada() {
		return despesaSelecionada;
	}
	public void setDespesaSelecionada(Despesa despesaSelecionada) {
		this.despesaSelecionada = despesaSelecionada;
	}

	public List<CentroDeCusto> getCentrosDeCusto() {
		return centrosDeCusto;
	}
	
}