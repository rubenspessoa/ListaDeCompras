package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;

public class GerenciarListas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8857382174325365137L;
	
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>(); // Avaliar LinkedHashSet
	ArrayList<ListaDeCompras> listasDeCompras = new ArrayList<ListaDeCompras>();

	/*
	 * PRODUTOS
	 */
	
	public void add(Produto p) throws IllegalArgumentException {
		for (Produto produto : listaDeProdutos) {
			if (produto.getNome().equals(p.getNome())){
				throw new IllegalArgumentException("O produto ja existe.");
			} else {
				listaDeProdutos.add(p);
			}
		}
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
	
	public void add(ListaDeCompras lista) throws IllegalArgumentException {
		for (ListaDeCompras forEachLista : listasDeCompras) {
			if (forEachLista.getNome().equals(lista.getNome())){
				throw new IllegalArgumentException("Uma lista com este mesmo nome ja existe.");
			} else {
				listasDeCompras.add(lista);
			}
		}
	}
	
	public void deleteLista(int index){
		this.listasDeCompras.remove(index);
	}
	
	// Retorna um Array de Strings com o nome de todas as Listas de Compras.
	public String[] nomesDasListas(){
	
	String[] nomes = new String[listasDeCompras.size()];
    	
	for (int i = 0;i < listasDeCompras.size();i++ ) {
    		nomes[i] = listasDeCompras.get(i).getNome();
    	}
    	
    	return nomes;
	}

	public ArrayList<ListaDeCompras> getListasDeCompras() {
		return listasDeCompras;
	}
	
}
