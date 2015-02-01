package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Banco;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.BancoService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.BancoDAO;

@Named
@ViewScoped
public class BancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BancoDAO bancoDAO;

	@Inject
    private BancoService bancoService;
	
	@Inject
	private FacesMessages messages;

    private Banco banco = new Banco();
    private List<Banco> bancosFiltrados;
    private List<Status> statuss;
    private Banco bancoSelecionado;

    public BancoBean() {
    	bancosFiltrados = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.bancoService.salvar(banco);
			consultar();
			messages.info("Banco salvo com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmBanco:messages", "frmBanco:tblBanco"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	banco = new Banco();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	bancosFiltrados = bancoDAO.buscarTodos();
    }
 
    public void excluir() {
        bancoDAO.excluir(bancoSelecionado);
        bancoSelecionado = null;

        consultar();

        messages.info("Banco excluído com sucesso!");
    }
    
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<Banco> getBancosFiltrados() {
		return bancosFiltrados;
	}
	public void setBancosFiltrados(List<Banco> bancosFiltrados) {
		this.bancosFiltrados = bancosFiltrados;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}
	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}
	
}