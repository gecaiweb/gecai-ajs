package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Empresa;
import modelo.Endereco;
import modelo.Status;
import modelo.TipoPessoa;

import org.primefaces.context.RequestContext;

import servico.EmpresaService;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.EmpresaDAO;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDAO empresaDAO;

	@Inject
    private EmpresaService empresaService;
	
	@Inject
	private FacesMessages messages;

    private Empresa empresa = new Empresa();
    private List<Empresa> empresasFiltradas;
    private List<Status> statuss;
    private List<TipoPessoa> tiposPessoa;
    private Empresa empresaSelecionada;

    public EmpresaBean() {
    	empresasFiltradas = new ArrayList<>();
    }
      
	public void salvar() {
		try {
			this.empresaService.salvar(empresa);
			consultar();
			messages.info("Empresa salva com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("frmEmpresa:messages", "frmEmpresa:tblEmpresa"));

		} catch (NegocioException e) {
			messages.error(e.getMessage());
		}
	}
	
    public void prepararNovoCadastro() {
    	empresa = new Empresa();
    	empresa.setEndereco(new Endereco());
    }
    
    @PostConstruct
    public void init() {
    	statuss = Arrays.asList(Status.values());
    	tiposPessoa = Arrays.asList(TipoPessoa.values());
    }
    
    public void consultar() {
    	empresasFiltradas = empresaDAO.buscarTodas();
    }
 
    public void excluir() {
        try {
			empresaDAO.excluir(empresaSelecionada);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        empresaSelecionada = null;

        consultar();

        messages.info("Empresa excluída com sucesso!");
    }
    
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresasFiltradas() {
		return empresasFiltradas;
	}
	public void setEmpresasFiltradas(List<Empresa> empresasFiltradas) {
		this.empresasFiltradas = empresasFiltradas;
	}

	public List<Status> getStatuss() {
		return statuss;
	}
	
	public List<TipoPessoa> getTiposPessoa() {
		return tiposPessoa;
	}

	public void setTiposPessoa(List<TipoPessoa> tiposPessoa) {
		this.tiposPessoa = tiposPessoa;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}
	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
}