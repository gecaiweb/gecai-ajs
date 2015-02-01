package dao.filtro;

import java.io.Serializable;

import modelo.StatusFuncionario;

public class FuncionarioFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cpf;
	private String rg;
	private StatusFuncionario[] statusFuncionarios;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public StatusFuncionario[] getStatusFuncionarios() {
		return statusFuncionarios;
	}
	public void setStatusFuncionarios(StatusFuncionario[] statusFuncionarios) {
		this.statusFuncionarios = statusFuncionarios;
	}
}
