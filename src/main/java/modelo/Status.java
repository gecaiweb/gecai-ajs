package modelo;

public enum Status {
	
	ATIVO("Ativo"), BLOQUEADO("Bloqueado"), INATIVO("Inativo");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}