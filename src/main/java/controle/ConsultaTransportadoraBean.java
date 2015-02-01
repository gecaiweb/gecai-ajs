package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Status;
import modelo.Transportadora;
import servico.NegocioException;
import util.jsf.FacesMessages;
import dao.TransportadoraDAO;
import dao.filtro.TransportadoraFilter;

@Named
@ViewScoped
public class ConsultaTransportadoraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TransportadoraDAO transportadoraDAO;
	
	@Inject 
	private FacesMessages message;
	
	private TransportadoraFilter filtro;
	private List<Transportadora> transportadorasFiltradas;
	private List<Status> statuss;
	private Transportadora transportadoraSelecionada;
	
	public ConsultaTransportadoraBean() {
		filtro = new TransportadoraFilter();
	}
	
	public void consultar() {
		transportadorasFiltradas = transportadoraDAO.filtrados(filtro);
	}

	@PostConstruct
	public void init() {
		this.statuss = Arrays.asList(Status.values());
	}
	
	public void excluir() {
		try {
			transportadoraDAO.excluir(transportadoraSelecionada);
			this.transportadorasFiltradas.remove(transportadoraSelecionada);
			message.info("Transportadora " + transportadoraSelecionada.getNome() + " excluída com sucesso.");
		} catch (NegocioException e) {
			message.error(e.getMessage());
		}
	}

	public TransportadoraFilter getFiltro() {
		return filtro;
	}
	public void setFiltro(TransportadoraFilter filtro) {
		this.filtro = filtro;
	}

	public List<Transportadora> getTransportadorasFiltradas() {
		return transportadorasFiltradas;
	}
	public void setTransportadorasFiltradas(
			List<Transportadora> transportadorasFiltradas) {
		this.transportadorasFiltradas = transportadorasFiltradas;
	}

	public Transportadora getTransportadoraSelecionada() {
		return transportadoraSelecionada;
	}
	public void setTransportadoraSelecionada(
			Transportadora transportadoraSelecionada) {
		this.transportadoraSelecionada = transportadoraSelecionada;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

}
