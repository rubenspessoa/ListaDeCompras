package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

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
	private static final long msecsDia = 86400000;
	
	public ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	public ArrayList<ListaDeCompras> listasDeCompras = new ArrayList<ListaDeCompras>();

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
    			nomes[i] = listaDeProdutos.get(i).getNome();
    		}
    	
    	return nomes;
    }
	
	public String[] nomesProdutosInvertida(){
		
	Collections.sort(listaDeProdutos, new ProdutoComparator());
	Collections.reverse(listaDeProdutos);
		
    	String[] nomes = new String[listaDeProdutos.size()];
    	
    		for (int i = 0;i<listaDeProdutos.size();i++ ){
    			nomes[i] = listaDeProdutos.get(i).getNome();
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
    			nomes[i] = listasDeCompras.get(i).getNome();
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
			ArrayList<Produto> copyListaDeProdutos = listaDeProdutos;
			Collections.sort(copyListaDeProdutos, new ProdutoComparatorQuantasVezesComprado());
			Collections.reverse(copyListaDeProdutos);
			
			int maiorNumeroDeCompras = maiorNumeroDeCompras();
				
			for (Produto produto : listaDeProdutos) {
				if (produto.quantasVezesFoiComprado() >= maiorNumeroDeCompras/2) {
					sugerida.add(produto);
				}
			}
		}
		
		return sugerida;
		
	}
	
	/**
	 * Retorna uma lista sugerida de produtos automática. Utiliza os habitos compra de do usuário
	 * (eventos de preço para cada produto) para gerar esta lista.
	 * 
	 * @return LinkedList<Produto> listaSugeridaFinal
	 */
	
	public ListaDeCompras getListaSugeridaDeProdutos(String nome, int tamanho){
		 ArrayList<Produto> listaOrdenadaPreliminar = new ArrayList<Produto>(this.listaDeProdutos);
		/*	Primeira ordenação dos produtos. Aqui todos os produtos cadastrados são ordenados
		*decrescentemente* por quantidades de eventos.
		* 										*testar
		*/
		Collections.sort(listaOrdenadaPreliminar, new Comparator<Produto>() {
		    public int compare(Produto a, Produto b) {
		        return b.getAmostraEventosDePreco().size() - a.getAmostraEventosDePreco().size();
		    }
		});
		/*	Aqui a lista ordenada de todos os produtos obtida anteriormente
		 * é cortada numa lista menor de comprimento @param tamanho.
		 */
		LinkedList<Produto> listaSugeridaFinal = new LinkedList<Produto>();
		if(tamanho< listaOrdenadaPreliminar.size()){
			tamanho = listaOrdenadaPreliminar.size();
		}
		for(int i = 0 ; i < tamanho ; i++){
			listaSugeridaFinal.add(listaOrdenadaPreliminar.get(i));
		}
		
		//	Segunda ordenação de produtos.
		Collections.sort(listaSugeridaFinal, new Comparator<Produto>() {
		    public int compare(Produto a, Produto b) {
		        return a.getTendenciaDeCompra() - b.getTendenciaDeCompra();
		    }
		});
		// Cria a ListaDeCompras e adiciona os produtos sugeridos
		ListaDeCompras listaComprasSugerida = new ListaDeCompras(nome);
		for(Produto produto : listaSugeridaFinal){
			listaComprasSugerida.add(produto, 1);
		}
		return listaComprasSugerida;
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
