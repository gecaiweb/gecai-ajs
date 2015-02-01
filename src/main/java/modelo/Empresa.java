package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("empresa")
public class Empresa extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cnaeFiscal;
	private String inscricaoMunicipal;
	
	@Column(name="cnae_fiscal", length=20)
	public String getCnaeFiscal() {
		return cnaeFiscal;
	}
	public void setCnaeFiscal(String cnaeFiscal) {
		this.cnaeFiscal = cnaeFiscal;
	}
	
	@Column(name="inscricao_municipal", length=20)
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}	
	
}