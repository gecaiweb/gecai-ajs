package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long prazoPagamento;

	@Column(name="prazo_pagamento")
	public Long getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(Long prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}

}