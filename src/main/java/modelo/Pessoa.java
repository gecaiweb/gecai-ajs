package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "fl_pessoa", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("pessoa")
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private String nomeFantasia;
	private TipoPessoa tipoPessoa;
	private String documentoReceitaFederal;
	private String registroEstadual;
	private Date dataCadastro;
	private Status status; // = Status.ATIVO;
	private Endereco endereco;
	private String inscricaoSocial;
	private String inscricaoSuframa;
	private Boolean isentoIcms;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull(message="obrigatório preenchimento")
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="nome_fantasia", length=80)
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	@NotNull(message="obrigatório preenchimento")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "tipo_pessoa")
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@NotNull
	@Column(length = 15, name = "documento_receita_federal")
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}
	
	@Column(length = 15, name = "registro_geral")
	public String getRegistroEstadual() {
		return registroEstadual;
	}
	public void setRegistroEstadual(
			String registroEstadual) {
		this.registroEstadual = registroEstadual;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@NotNull(message="obrigatório preenchimento")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="codigo_endereco")
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Column(name="inscricao_social", length=30)
	public String getInscricaoSocial() {
		return inscricaoSocial;
	}
	public void setInscricaoSocial(String inscricaoSocial) {
		this.inscricaoSocial = inscricaoSocial;
	}
	
	@Column(name="inscricao_suframa", length=30)
	public String getInscricaoSuframa() {
		return inscricaoSuframa;
	}
	public void setInscricaoSuframa(String inscricaoSuframa) {
		this.inscricaoSuframa = inscricaoSuframa;
	}
	
	@Column(name="isento_icms")
	public Boolean getIsentoIcms() {
		return isentoIcms;
	}
	public void setIsentoIcms(Boolean isentoIcms) {
		this.isentoIcms = isentoIcms;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
