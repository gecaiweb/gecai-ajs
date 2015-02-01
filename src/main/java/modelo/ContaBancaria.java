package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private TipoConta tipoConta;
	private Banco banco;
	private String numeroConta;
	private String numeroAgencia;
	private String nomeTitular;
	private String documentoTitular;
	private Status status;
	private Pessoa pessoa;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = false, length = 2)
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@ManyToOne
	@JoinColumn(name = "id_banco")
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Column(name = "numero_conta", nullable = false, length = 20)
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Column(name = "numero_agencia", nullable = false, length = 20)
	public String getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	@Column(name = "nome_titular", nullable = false, length = 80)
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	@Column(name = "documento_titular", nullable = false, length = 15)
	public String getDocumentoTitular() {
		return documentoTitular;
	}
	public void setDocumentoTitular(String documentoTitular) {
		this.documentoTitular = documentoTitular;
	}

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable=false, length=15)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
