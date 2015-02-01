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
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.CentroDeCustoService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.CentroDeCustoDAO;

@Named
@ViewScoped
public class CentroDeCustoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CentroDeCustoDAO centroDeCustoDAO;

	@Inject
    private CentroDeCustoService centroDeCustoService;
	
	@Inject
	private FacesMessages messages;

    private CentroDeCusto centroDeCusto = new CentroDeCusto();
    private List<CentroDeCusto> centroDeCustosFiltrados;
    private List<Status> statuss;
    private CentroDeCusto centroDeCustoSelecionado;

    public CentroDeCustoBean() {
    	centroDeCustosFiltrados = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.centroDeCustoService.salvar(centroDeCusto);
			consultar();
			messages.info("CentroDeCusto salvo com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmCentroDeCusto:messages", "frmCentroDeCusto:tblCentroDeCusto"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	centroDeCusto = new CentroDeCusto();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	centroDeCustosFiltrados = centroDeCustoDAO.buscarTodos();
    }
 
    public void excluir() {
        centroDeCustoDAO.excluir(centroDeCustoSelecionado);
        centroDeCustoSelecionado = null;

        consultar();

        messages.info("CentroDeCusto excluído com sucesso!");
    }
    
	public CentroDeCusto getCentroDeCusto() {
		return centroDeCusto;
	}
	public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
		this.centroDeCusto = centroDeCusto;
	}

	public List<CentroDeCusto> getCentroDeCustosFiltrados() {
		return centroDeCustosFiltrados;
	}
	public void setCentroDeCustosFiltrados(List<CentroDeCusto> centroDeCustosFiltrados) {
		this.centroDeCustosFiltrados = centroDeCustosFiltrados;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public CentroDeCusto getCentroDeCustoSelecionado() {
		return centroDeCustoSelecionado;
	}
	public void setCentroDeCustoSelecionado(CentroDeCusto centroDeCustoSelecionado) {
		this.centroDeCustoSelecionado = centroDeCustoSelecionado;
	}
	
}