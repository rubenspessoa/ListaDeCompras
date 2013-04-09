package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;
/**
 * Gerencia a lista de todos os produtos cadastrados no aplicativo e as listas criadas pelo usuario.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
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
	
	/**
	 * Adiciona um produto a lista de todos os produtos cadastrados.
	 * @param p produto
	 * @throws IllegalArgumentException O produto ja existe
	 */
	public void add(Produto p) throws IllegalArgumentException {
		if (listaDeProdutos.size() > 0){
			for (int i = 0; i < listaDeProdutos.size(); i++) {
				if (listaDeProdutos.get(i).getNome().equals(p.getNome())){
					throw new IllegalArgumentException("O produto ja existe.");
				}
			}
		} 
		this.listaDeProdutos.add(p);
		
	}
	
	/**
	 * Deleta um produto da lista de todos os produtos cadastrados.
	 * @param index posicao do produto na lista de produtos.
	 */
	public void deleteProduto(int index){
		this.listaDeProdutos.remove(index);
	}
	/**
	 * 
	 * @return Os nomes de todos os produtos cadastrados.
	 */
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
	/**
	 * Adiciona um lista de compras a lista de todos as listas de compras.
	 * @param lista de compras
	 * @throws IllegalArgumentException Uma lista com este mesmo nome ja existe.
	 */
	public void add(ListaDeCompras lista) throws IllegalArgumentException {
		if (listasDeCompras.size() > 0){
			for (int i = 0; i < listasDeCompras.size(); i++) {
				if (listasDeCompras.get(i).getNome().equals(lista.getNome())){
					throw new IllegalArgumentException("Uma lista com este mesmo nome ja existe.");
				}
			}
		}
		this.listasDeCompras.add(lista);

		
	}
	/**
	 * Deleta uma lista da lista de todas as listas de compras.
	 * @param index posicao da lista na lista de todas as listas de compras.
	 */
	public void deleteLista(int index){
		this.listasDeCompras.remove(index);
	}
	
	/**
	 * Retorna um Array de Strings com o nome de todas as Listas de Compras.
	 * @return nome de todas as listas de compras.
	 */
	public String[] nomesDasListas(){
	
	String[] nomes = new String[listasDeCompras.size()];
    	
	for (int i = 0;i < listasDeCompras.size();i++ ) {
    		nomes[i] = listasDeCompras.get(i).nome;
    	}
    	
    	return nomes;
	}

	public ArrayList<ListaDeCompras> getListasDeCompras() {
		return listasDeCompras;
	}
	
}
