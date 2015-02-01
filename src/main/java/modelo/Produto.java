package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@Entity
//@Table(name="produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String ean;
	private String eanUnidadeTributavel;
	private String tipi;
	private String genero;
	private String ncm;
	private Long unidadeComercial;
	private BigDecimal valorUnidadeComercial;
	private Long unidadeTributaria;
	private BigDecimal quantidadeTributavel;
	private BigDecimal valorUnidadeTributavel;
	//private TipoTributacao tipoTributacao;
	//private TipoIpi tipoIpi;
	
	private Marca marca;
	private Unidade unidade;
	private Categoria categoria;
	private Subcategoria subcategoria;
	
	private Date dataCadastro;
	private Date Atualizacao;
	

}
