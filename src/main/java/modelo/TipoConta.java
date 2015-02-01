package modelo;

public enum TipoConta {

	CC("Conta-Corrente"), 
	CP("Conta-Poupança") ;

	private String descricao;

	TipoConta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
