package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Funcionario;
import modelo.Grupo;
import modelo.Status;
import modelo.Usuario;

import org.primefaces.context.RequestContext;

import servico.NegocioException;
import servico.UsuarioService;
import util.jsf.FacesMessages;
import dao.FuncionarioDAO;
import dao.GrupoDAO;
import dao.UsuarioDAO;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Inject
    private UsuarioService usuarioService;
	
	@Inject
	private FacesMessages messages;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuariosFiltrados;
    private List<Status> statuss;
    private List<Grupo> grupos;
    private List<Funcionario> funcionarios;
    private Usuario usuarioSelecionado;

    public UsuarioBean() {
    	usuariosFiltrados = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.usuarioService.salvar(usuario);
			consultar();
			messages.info("Usuario salvo com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmUsuario:messages", "frmUsuario:tblUsuario"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	usuario = new Usuario();
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    	grupos = grupoDAO.buscarTodos();
    	funcionarios = funcionarioDAO.buscarTodos();
    }
    
    public void consultar() {
    	usuariosFiltrados = usuarioDAO.buscarTodos();
    }
 
    public void excluir() {
        usuarioDAO.excluir(usuarioSelecionado);
        usuarioSelecionado = null;

        consultar();

        messages.info("Usuario excluída com sucesso!");
    }

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}