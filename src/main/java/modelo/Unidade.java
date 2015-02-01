package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="unidade")
public class Unidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String nomeReduzido;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotEmpty
	@Column(nullable=false, length=20)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty
	@Column(name="nome_reduzido", nullable=false, length=3)
	public String getNomeReduzido() {
		return nomeReduzido;
	}
	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
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
		Unidade other = (Unidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
