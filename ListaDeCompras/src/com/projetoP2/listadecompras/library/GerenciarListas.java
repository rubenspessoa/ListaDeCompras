package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;

public class GerenciarListas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8857382174325365137L;
	
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	ArrayList<ListaDeCompras> listasDeCompras = new ArrayList<ListaDeCompras>();

	
	
	/*
	 * PRODUTOS
	 */
	public void add(Produto p){
		this.listaDeProdutos.add(p);
	}
	
	public void deleteProduto(int index){
		this.listaDeProdutos.remove(index);
	}
	
	public String[] nomesProdutos(){
		
    	String[] nomes = new String[listaDeProdutos.size()];
    	
    	for (int i = 0;i<listaDeProdutos.size();i++ ){
    		nomes[i] = listaDeProdutos.get(i).nome;
    	}
    	
    	return nomes;
    }

	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	
	/*
	 * LISTAS DE COMPRAS
	 */
	
	public void add(ListaDeCompras lista){
		this.listasDeCompras.add(lista);
	}
	
	public void deleteLista(int index){
		this.listasDeCompras.remove(index);
	}
	
	// Retorna um Array de Strings com as informacoes de todas as Listas de Compras.
	public String[] nomesDasListas(){
	
	String[] nomes = new String[listasDeCompras.size()];
    	
	for (int i = 0;i<listaDeProdutos.size();i++ ){
    		nomes[i] = listasDeCompras.get(i).nome;
    	}
    	
    	return nomes;
	}

	public ArrayList<ListaDeCompras> getListasDeCompras() {
		return listasDeCompras;
	}
	
}
