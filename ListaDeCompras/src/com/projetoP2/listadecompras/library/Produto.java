package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Classe que cria e edita um produto.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */

public abstract class Produto implements Serializable, Calculavel, Comparable<Produto> {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
       
        String nome, estabelecimento;
        double valor;
        LinkedList<EventoDePreco> eventosDePreco = new LinkedList<EventoDePreco>();
        public ArrayList<String> palavrasChave = new ArrayList<String>();
 
        public Produto (String nome, String estabelecimento, double valor) {
                this.nome = nome;
                this.estabelecimento = estabelecimento;
                this.valor = valor;
                String[] criaPalavrasChave = nome.split(" ");
                for ( int i = 0; i < criaPalavrasChave.length; i++) {
                		if (!criaPalavrasChave.equals(""))
                			palavrasChave.add(criaPalavrasChave[i]);
                }
        }
        
        /**
         * Adiciona um evento de preco ao produto
         * @param valor do produto
         * @param estabelecimento local onde foi encontrado
         */
        
		public abstract void addEventoDePreco(double quantidade, double valor, String estabelecimento);
		
		public void addPalavrasChave(String palavra){
			String[] teste = palavra.trim().split(",");
			for (int i = 0; i < teste.length; i++) {
				addPalavraChave(teste[i]);
			}
		}
		
		private void addPalavraChave(String palavra) {
			palavrasChave.add(palavra);
		}
		
		public ArrayList<String> getPalavrasChave(){
			return palavrasChave;
		}
		
		public String getNome() {
			return nome;
		}
		
        public String getEstabelecimento() {
			return estabelecimento;
		}
		
        public double getValor() {
        		return valor;
        }
        
        public int quantasVezesFoiComprado(){
        		return eventosDePreco.size();
        }
			
}