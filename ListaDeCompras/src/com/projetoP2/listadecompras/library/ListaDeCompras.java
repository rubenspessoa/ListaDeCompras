package com.projetoP2.listadecompras.library;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ListaDeCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4851367961472402839L;
	
	String nome;
	Date data;
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	
	double valorDaListaDeProdutos;

	public ListaDeCompras(String nome){
		this.nome = nome;
		this.data = new Date(); // ATRIBUI A PRIMEIRA DATA.
	}
	
	public void adicionarProduto(Produto produto){
		if (listaDeProdutos.contains(produto));{
			for (int i=0; i< this.listaDeProdutos.size(); i++){
				if (listaDeProdutos.get(i) == produto);{
					listaDeProdutos.get(i).addUnidade();
					break;
				}
			}
		}else{
			listaDeProdutos.add(produto);
		}
		updateValorDaLista();
		updateDate();
	}
	public void removerProduto(int indice){
		listaDeProdutos.remove(indice);
		updateValorDaLista();
		updateDate();
	}
	
	private void updateValorDaLista(){
		double novovalor = 0;
		for (int i=0; i< this.listaDeProdutos.size(); i++){
			novovalor += (listaDeProdutos.get(i).getValor() * listaDeProdutos.get(i).getQtd());
		}
		this.valorDaListaDeProdutos = novovalor;
		// Aperfeiçoar posteriormente...
	}
	
	public double getValorDaLista(){
		return this.valorDaListaDeProdutos;
	} 
	
	public void setValorDaListaDeProdutos(double novovalor){
		this.valorDaListaDeProdutos = novovalor;
	} // Será que faz sentido um set para ValorDaListaDeProdutos, se a variável depende totalmetne do valor dos produtos ?
	
	
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
		//PEGA A DATA ATUAL E ATUALIZA A VARIAVEL DATA PARA A ULTIMA VEZ EM QUE A LISTA FOR ATUALIZADA.
	}
	@Override
	public String toString(){
		return ("Nome da lista: " + this.nome + "Modificada em: " + this.data.toString());
	}
}
