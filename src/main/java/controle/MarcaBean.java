package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Marca;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.MarcaService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.MarcaDAO;

@Named
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MarcaDAO marcaDAO;

	@Inject
    private MarcaService marcaService;
	
	@Inject
	private FacesMessages messages;

    private Marca marca = new Marca();
    private List<Marca> marcasFiltradas;
    private List<Status> statuss;
    private Marca marcaSelecionada;

    public MarcaBean() {
    	marcasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.marcaService.salvar(marca);
			consultar();
			messages.info("Marca salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmMarca:messages", "frmMarca:tblMarca"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	marca = new Marca();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	marcasFiltradas = marcaDAO.buscarTodos();
    }
 
    public void excluir() {
        marcaDAO.excluir(marcaSelecionada);
        marcaSelecionada = null;

        consultar();

        messages.info("Marca excluída com sucesso!");
    }
    
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getMarcasFiltradas() {
		return marcasFiltradas;
	}
	public void setMarcasFiltradas(List<Marca> marcasFiltradas) {
		this.marcasFiltradas = marcasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Marca getMarcaSelecionada() {
		return marcaSelecionada;
	}
	public void setMarcaSelecionada(Marca marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}
	
}