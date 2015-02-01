package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fornecedor")
public class Fornecedor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long prazoPagamento;
	private Long prazoEntrega;

	@Column(name="prazo_pagamento")
	public Long getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(Long prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}
	
	@Column(name="prazo_entrega")
	public Long getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(Long prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

}