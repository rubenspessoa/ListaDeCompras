package com.projetoP2.listadecompras.library;

import java.io.Serializable;

public class ProdutoEmUnidade extends Produto implements Medida, Serializable {

	public ProdutoEmUnidade(String nome, String estabelecimento, double valor) {
		super(nome, estabelecimento, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculaValor(double quantidade, double preco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculaValorDaUnidadeDeMedida(double quantidade, double preco) {
		// TODO Auto-generated method stub
		return 0;
	}

}
