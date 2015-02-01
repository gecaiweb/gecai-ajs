package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Grupo;
import modelo.Status;

import org.primefaces.context.RequestContext;

import servico.GrupoService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.GrupoDAO;

@Named
@ViewScoped
public class GrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoDAO grupoDAO;

	@Inject
    private GrupoService grupoService;
	
	@Inject
	private FacesMessages messages;

    private Grupo grupo= new Grupo();
    private List<Grupo> gruposFiltrados;
    private List<Status> statuss;
    private Grupo grupoSelecionado;

    public GrupoBean() {
    	gruposFiltrados = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.grupoService.salvar(grupo);
			consultar();
			messages.info("Grupo salvo com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frm:messages", "frm:tblGrupo"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	grupo = new Grupo();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    }
    
    public void consultar() {
    	gruposFiltrados = grupoDAO.buscarTodos();
    }
 
    public void excluir() {
        grupoDAO.excluir(grupoSelecionado);
        grupoSelecionado = null;
        consultar();
        messages.info("Grupo excluída com sucesso!");
    }

	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGruposFiltrados() {
		return gruposFiltrados;
	}
	public void setGruposFiltrados(List<Grupo> gruposFiltrados) {
		this.gruposFiltrados = gruposFiltrados;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}
	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public List<Status> getStatuss() {
		return statuss;
	}
}