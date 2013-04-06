package com.projetoP2.listadecompras.library;


import java.io.Serializable;
import java.util.ArrayList;

public class GerenciarListas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8857382174325365137L;
	ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
	
	public void add(Produto p){
		this.listaDeProdutos.add(p);
	}
	
	public void delete(int index){
		this.listaDeProdutos.remove(index);
	}
	
	public String[] nomesProdutos(){
    	String[] nomes = new String[listaDeProdutos.size()];
    	for (int i = 0;i<listaDeProdutos.size();i++ ){
    		nomes[i] = listaDeProdutos.get(i).nome;
    	}
    	
    	return nomes;
    }
	/*ArrayList<ListaDeCompras> listasDeCompra = new ArrayList<ListaDeCompras>();
	
	public void add(ListaDeCompras lista){}
	
	public void remove(ListaDeCompras lista){}
	
	public void procura(ListaDeCompras lista){}
	
	public String[] formataListasDeCompra(){} // Retorna um Array de Strings com as informacoes de todas as Listas de Compras.*/

}
