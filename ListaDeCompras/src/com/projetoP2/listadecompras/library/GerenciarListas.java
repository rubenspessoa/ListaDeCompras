package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
	
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	ArrayList<ListaDeCompras> listasDeCompras = new ArrayList<ListaDeCompras>();

	public GerenciarListas(){
		listaDeProdutos.add(new ProdutoEmUnidade("Arroz Generico 1kg", "Supermercado Generico", 3.50));
		listaDeProdutos.add(new ProdutoEmUnidade("Feijao Generico 1kg", "Supermercado Generico", 4.50));
		listaDeProdutos.add(new ProdutoEmUnidade("Macarrao Generico 500g", "Supermercado Generico", 2.20));
		listaDeProdutos.add(new ProdutoEmUnidade("Cafe Generico 500g", "Supermercado Generico", 4.00));
		listaDeProdutos.add(new ProdutoEmUnidade("Manteiga Generica 300g", "Supermercado Generico", 2.00));
		listaDeProdutos.add(new ProdutoEmUnidade("Acucar Generico 1kg", "Supermercado Generico", 1.50));
		listaDeProdutos.add(new ProdutoEmUnidade("Refrigerante Coca-Cola 2L", "Supermercado Generico", 5.00));
		listaDeProdutos.add(new ProdutoEmKg("Pao Frances Generico 1kg", "Supermercado Generico", 5.00));
		listaDeProdutos.add(new ProdutoEmKg("Presunto Generico 1kg", "Supermercado Generico", 15.00));
		listaDeProdutos.add(new ProdutoEmKg("Queijo Mussarela Generica 1kg", "Supermercado Generico", 22.00));
		listaDeProdutos.add(new ProdutoEmKg("Carne Generica 1kg", "Supermercado Generico", 35.00));
	}
	
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
	
	Collections.sort(listaDeProdutos, new ProdutoComparator());
	
    	String[] nomes = new String[listaDeProdutos.size()];
    	
    		for (int i = 0;i<listaDeProdutos.size();i++ ){
    			nomes[i] = listaDeProdutos.get(i).nome;
    		}
    	
    	return nomes;
    }
	
	public String[] nomesProdutosInvertida(){
		
	Collections.sort(listaDeProdutos, new ProdutoComparator());
	Collections.reverse(listaDeProdutos);
		
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
	
	public ListaDeCompras sugereListaDeCompras(String nomeDaLista) {
		
		ListaDeCompras sugerida = new ListaDeCompras(nomeDaLista);
		
		if (listasDeCompras.size() == 0) {
			for (Produto produto : listaDeProdutos) {
				sugerida.add(produto);
			}
		} else {
			int maiorNumeroDeCompras = maiorNumeroDeCompras();
			
			for (Produto produto : listaDeProdutos) {
				if (produto.quantasVezesFoiComprado() >= maiorNumeroDeCompras/2) {
					sugerida.add(produto);
				}
			}
			
		}
		
		return sugerida;
		
	}
	
	private int maiorNumeroDeCompras(){
		int maiorNumeroDeCompras = 0;
		
		for (Produto produto : listaDeProdutos) {
			if (produto.quantasVezesFoiComprado() > maiorNumeroDeCompras) {
				maiorNumeroDeCompras = produto.quantasVezesFoiComprado();
			}
		}
		
		return maiorNumeroDeCompras;
	}
}
