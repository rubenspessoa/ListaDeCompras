package com.projetoP2.listadecompras.library;

import java.io.Serializable;

public class ProdutoEmKg extends Produto implements Medida, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094665976608781148L;
	
	double quantidadeComprada;
	
	public ProdutoEmKg(String nome, String estabelecimento, double preco) {
		super(nome, estabelecimento, preco);
		this.quantidadeComprada = 1;
		addEventoDePreco(quantidadeComprada, preco, estabelecimento);
	}

	@Override
	public double calculaValor(double pesoDesejado) {
		return pesoDesejado * super.valor;
	}

	@Override
	public double calculaValorDaUnidadeDeMedida(double quantidade, double preco) {
		return preco/quantidade;
	}

	@Override
	public void addEventoDePreco(double quantidade, double valor, String estabelecimento) {
		this.quantidadeComprada = quantidade;
		super.estabelecimento = estabelecimento;
		super.valor = calculaValorDaUnidadeDeMedida(quantidade, valor);
		super.eventosDePreco.add(new EventoDePreco(calculaValorDaUnidadeDeMedida(quantidade, valor), estabelecimento));		
	}

}
