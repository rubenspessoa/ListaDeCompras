package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Date;
/**
 * Classe que cria e edita uma Lista de Compras
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class ListaDeCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4851367961472402839L;
	
	String nome;
	Date data;
	LinkedHashMap<Produto, Double> mapaDeProdutos = new LinkedHashMap<Produto, Double>();
	double valorDaListaDeProdutos;

	public ListaDeCompras (String nome) {
		this.nome = nome;
		this.data = new Date();
	}
	/**
	 * Adiciona um produto a lista de compras
	 * @param produto
	 */
	public void add(Produto produto){
		mapaDeProdutos.put(produto, 1.0);
		updateValorDaLista();
	}
	
	public void add(Produto produto, double qtd){
		mapaDeProdutos.put(produto, qtd);
		updateValorDaLista();
	}
	
	/**
	 * Remove um produto da lista de compras
	 * @param indice
	 */
	
	public void removerProduto(int indice){
		mapaDeProdutos.remove(indice);
		updateValorDaLista();
	}
	
	/**
	 * Retorna o valor total da lista de compras
	 */
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

	public LinkedHashMap<Produto, Double> getMapaDeProdutos() {
		return mapaDeProdutos;
	}
	
	public String getNome(){
		return this.nome;
	}
	/**
	 * 
	 * @return Array de string com os nomes de todos os produtos na lista de compras
	 */
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
	
	/**
	 * 
	 * @return um Array de double com todos os precos dos produtos da lista de compras.
	 */
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
