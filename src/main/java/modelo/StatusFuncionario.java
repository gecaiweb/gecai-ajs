package modelo;

public enum StatusFuncionario {

	ATIVO("Ativo"), DEMITIDO("Demitido"), FERIAS("Férias"), AFASTADO("Afastado"), EM_AFASTAMENTO("Em Afastamento");
	
	private String descricao;
	
	StatusFuncionario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
