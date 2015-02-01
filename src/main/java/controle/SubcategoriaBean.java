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
import modelo.Subcategoria;

import org.primefaces.context.RequestContext;

import servico.NegocioException;
import servico.SubcategoriaService;
import util.jsf.FacesMessages;
import dao.CategoriaDAO;
import dao.SubcategoriaDAO;

@Named
@ViewScoped
public class SubcategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SubcategoriaDAO subcategoriaDAO;
	
	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
    private SubcategoriaService subcategoriaService;
	
	@Inject
	private FacesMessages messages;

    private Subcategoria subcategoria = new Subcategoria();
    private List<Subcategoria> subcategoriasFiltradas;
    private List<Status> statuss;
    private List<Categoria> categorias;
    private Subcategoria subcategoriaSelecionada;

    public SubcategoriaBean() {
    	subcategoriasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.subcategoriaService.salvar(subcategoria);
			consultar();
			messages.info("Subcategoria salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmSubcategoria:messages", "frmSubcategoria:tblSubcategoria"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	subcategoria = new Subcategoria();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    	categorias = categoriaDAO.buscarTodos();
    }
    
    public void consultar() {
    	subcategoriasFiltradas = subcategoriaDAO.buscarTodos();
    }
 
    public void excluir() {
        subcategoriaDAO.excluir(subcategoriaSelecionada);
        subcategoriaSelecionada = null;

        consultar();

        messages.info("Subcategoria excluída com sucesso!");
    }
    
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public List<Subcategoria> getSubcategoriasFiltradas() {
		return subcategoriasFiltradas;
	}
	public void setSubcategoriasFiltradas(List<Subcategoria> subcategoriasFiltradas) {
		this.subcategoriasFiltradas = subcategoriasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public Subcategoria getSubcategoriaSelecionada() {
		return subcategoriaSelecionada;
	}
	public void setSubcategoriaSelecionada(Subcategoria subcategoriaSelecionada) {
		this.subcategoriaSelecionada = subcategoriaSelecionada;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
}