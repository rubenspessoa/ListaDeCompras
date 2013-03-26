package com.projetoP2.listadecompras;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaDeCompras implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3746827195217723129L;
	String nome; 
	
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	double valorDaListaDeProdutos;

	public ListaDeCompras(String nome){
		this.nome = nome;
	}
	
	private double setValorDaListaDeProdutos(){}
	
	public void adicionarProduto(){}
	
	public void removerProduto(){}
	
	public double getValorDaLista(){}
	
	public String getNome(){}
	
	public void setNome(String novoNome){}
	
	@Override
	public String toString(){}

}
