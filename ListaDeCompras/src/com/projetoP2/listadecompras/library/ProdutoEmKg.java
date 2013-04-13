package com.projetoP2.listadecompras.library;

import java.io.Serializable;

public class ProdutoEmKg extends Produto implements Medida, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094665976608781148L;
	
	double ValorDaUnidadeDeMedida;
	
	public ProdutoEmKg(String nome, String estabelecimento, double qtdComprada, double preco) {
		super(nome, estabelecimento, preco);
		addEventoDePreco(calculaValorDaUnidadeDeMedida(qtdComprada, preco), estabelecimento);
	}

	@Override
	public double calculaValor(double pesoDesejado, double precoDoKg) {
		return pesoDesejado * precoDoKg;
	}

	@Override
	public double calculaValorDaUnidadeDeMedida(double quantidade, double preco) {
		return preco/quantidade;
	}

	@Override
	public void addEventoDePreco(double valor, String estabelecimento) {
		this.eventosDePreco.add(new EventoDePreco(valor, estabelecimento));		
	}

}
