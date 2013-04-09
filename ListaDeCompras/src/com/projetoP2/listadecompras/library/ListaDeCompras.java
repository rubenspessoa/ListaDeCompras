package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Date;

public class ListaDeCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4851367961472402839L;
	
	String nome;
	Date data;
	LinkedHashMap<Produto, Integer> mapaDeProdutos = new LinkedHashMap<Produto, Integer>();
	double valorDaListaDeProdutos;

	public ListaDeCompras (String nome) {
		this.nome = nome;
		this.data = new Date();
	}
	
	public void add(Produto produto){
		mapaDeProdutos.put(produto, 1);
		updateValorDaLista();
	}
	
	public void removerProduto(int indice){
		mapaDeProdutos.remove(indice);
		updateValorDaLista();
	}
	
	private void updateValorDaLista(){
		double valorTotal = 0;
		
		for (Produto produto : mapaDeProdutos.keySet()) {
			valorTotal += produto.getValor() * mapaDeProdutos.get(produto); // valor total eh igual ao valor do produto vezes a quantidade de produtos na lista.
		}
		
		this.valorDaListaDeProdutos = valorTotal;
	}
	
	public double getValorDaLista(){
		return valorDaListaDeProdutos;
	}
	
	public void setValorEmProduto(Produto produto, double valor, String estabelecimento){
		produto.addEventoDePreco(valor, estabelecimento);
	}

	public LinkedHashMap<Produto, Integer> getMapaDeProdutos() {
		return mapaDeProdutos;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String[] getNomeProdutos(){
		
		String[] nomeProdutos = new String[mapaDeProdutos.size()];
		Object[] teste = mapaDeProdutos.keySet().toArray();
		Produto[] produtos = new Produto[mapaDeProdutos.keySet().size()]; 
				
		for(int i = 0; i < mapaDeProdutos.keySet().size(); i++){
			produtos[i] = (Produto) teste[i];
		}
		
		for (int i = 0; i < nomeProdutos.length; i++) {
			nomeProdutos[i] = produtos[i].getNome();
		}
		
		return nomeProdutos ;
	}
	
	public double[] getValorProdutos(){
		
		double[] valorProdutos = new double[mapaDeProdutos.size()];
		Object[] teste = mapaDeProdutos.keySet().toArray();
		Produto[] produtos = new Produto[mapaDeProdutos.values().size()]; 
				
		for(int i = 0; i < mapaDeProdutos.values().size(); i++){
			produtos[i] = (Produto) teste[i];
		}
				
		for (int i = 0; i < valorProdutos.length; i++) {
			valorProdutos[i] = produtos[i].getValor();
		}
		
		return valorProdutos;
	}
	
}
