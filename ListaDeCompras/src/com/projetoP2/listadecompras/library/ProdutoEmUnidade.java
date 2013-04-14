package com.projetoP2.listadecompras.library;

import java.io.Serializable;

public class ProdutoEmUnidade extends Produto implements Medida, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -580427383168426924L;

	public ProdutoEmUnidade(String nome, String estabelecimento, double valor) {
		super(nome, estabelecimento, valor);
		int quantidade = 1;
		addEventoDePreco(calculaValorDaUnidadeDeMedida(quantidade, valor), estabelecimento);
	}
	
	public ProdutoEmUnidade(String nome, String estabelecimento, int quantidade, double valor) {
		super(nome, estabelecimento, valor);
		addEventoDePreco(calculaValorDaUnidadeDeMedida(quantidade, valor), estabelecimento);
	}

	@Override
	public double calculaValor(double quantidade, double preco) {
		return quantidade * preco;
	}

	@Override
	public double calculaValorDaUnidadeDeMedida(double quantidade, double preco) {
		return preco/quantidade;
	}

	@Override
	public void addEventoDePreco(double valor, String estabelecimento) {
		// TODO Auto-generated method stub
		
	}

}
