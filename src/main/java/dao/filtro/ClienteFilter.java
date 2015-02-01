package dao.filtro;

import java.io.Serializable;

import modelo.Status;

public class ClienteFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String documentoReceitaFederal;
	private String registroGeral;
	private Status[] status;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}
	
	public String getRegistroGeral() {
		return registroGeral;
	}
	public void setRegistroGeral(String registroGeral) {
		this.registroGeral = registroGeral;
	}
	
	public Status[] getStatus() {
		return status;
	}
	public void setStatus(Status[] status) {
		this.status = status;
	}
	
}
