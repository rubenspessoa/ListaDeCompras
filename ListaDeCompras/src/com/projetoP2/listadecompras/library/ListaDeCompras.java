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
	
<<<<<<< HEAD
	public void adicionarProduto(Produto produto){
		if (listaDeProdutos.contains(produto)){
			for (int i=0; i< this.listaDeProdutos.size(); i++){
				if (listaDeProdutos.get(i) == produto){
					listaDeProdutos.get(i).addUnidade();
					break;
				}
			}
		}else{
			listaDeProdutos.add(produto);
		}
=======
	public void add(Produto produto){
		listaDeProdutos.add(produto);
>>>>>>> Atualizando Produto, ListaDeCompras, EventoDePreco.
		updateValorDaLista();
		updateDate();
	}
	
	public void removerProduto(int indice){
		listaDeProdutos.remove(indice);
		updateValorDaLista();
		updateDate();
	}
	
	private void updateValorDaLista(){
		double valorTotal = 0;
		
		for (Produto produto : listaDeProdutos) {
			valorTotal += produto.getValor();
		}
		
		this.valorDaListaDeProdutos = valorTotal;
		// Aperfeicoar posteriormente...
	}
	
	public double getValorDaLista(){
		return this.valorDaListaDeProdutos;
	} 
	
	public void setValorDaListaDeProdutos(double novovalor){
		this.valorDaListaDeProdutos = novovalor;
	} // Sera que faz sentido um set para ValorDaListaDeProdutos, se a variavel depende totalmetne do valor dos produtos ?
	
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String novoNome){
		this.nome = novoNome;
		updateDate();
	}
	public void setValorEmProduto(Produto produto, double valor){
		produto.addEventoDePreco(valor, new Date());
	}
	
	public void updateDate(){
		this.data = new Date();
		// PEGA A DATA ATUAL E ATUALIZA A VARIAVEL DATA PARA A ULTIMA VEZ EM QUE A LISTA FOR ATUALIZADA.
	}

	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	
}
