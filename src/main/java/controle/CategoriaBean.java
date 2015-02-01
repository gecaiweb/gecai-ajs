package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Categoria;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.CategoriaService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.CategoriaDAO;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
    private CategoriaService categoriaService;
	
	@Inject
	private FacesMessages messages;

    private Categoria categoria = new Categoria();
    private List<Categoria> categoriasFiltradas;
    private List<Status> statuss;
    private Categoria categoriaSelecionada;

    public CategoriaBean() {
    	categoriasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.categoriaService.salvar(categoria);
			consultar();
			messages.info("Categoria salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmCategoria:messages", "frmCategoria:tblCategoria"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	categoria = new Categoria();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	categoriasFiltradas = categoriaDAO.buscarTodos();
    }
 
    public void excluir() {
        categoriaDAO.excluir(categoriaSelecionada);
        categoriaSelecionada = null;

        consultar();

        messages.info("Categoria excluído com sucesso!");
    }
    
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}
	public void setCategoriasFiltradas(List<Categoria> categoriasFiltradas) {
		this.categoriasFiltradas = categoriasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}
	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}
	
}