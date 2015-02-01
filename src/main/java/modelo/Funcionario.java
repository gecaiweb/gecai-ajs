package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("funcionario")
public class Funcionario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataNascimento;
	private Date dataAdmissao;
	private Date dataDemissao;
	private Funcao funcao;
	private StatusFuncionario statusFuncionario;
	private BigDecimal salario;
	private Lotacao lotacao;
	private Nacionalidade nacionalidade;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_admissao")
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_demissao")
	public Date getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	@ManyToOne
	@JoinColumn(name = "id_funcao")
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="status_funcionario", length=20)
	public StatusFuncionario getStatusFuncionario() {
		return statusFuncionario;
	}
	public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	
	@Column(precision = 9, scale = 2)
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_lotacao")
	public Lotacao getLotacao() {
		return lotacao;
	}
	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
}