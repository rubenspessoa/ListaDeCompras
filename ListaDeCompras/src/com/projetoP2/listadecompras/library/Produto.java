package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe que cria e edita um produto.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */

public abstract class Produto implements Serializable, Medida {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
       
        String nome, estabelecimento;
        double valor;
        LinkedList<EventoDePreco> eventosDePreco = new LinkedList<EventoDePreco>();
 
        public Produto (String nome, String estabelecimento, double valor) {
                this.nome = nome;
                this.estabelecimento = estabelecimento;
                this.valor = valor;
        }
        
        /**
         * Adiciona um evento de preco ao produto
         * @param valor do produto
         * @param estabelecimento local onde foi encontrado
         */
        
		public abstract void addEventoDePreco(double quantidade, double valor, String estabelecimento);
		
		public String getNome() {
			return nome;
		}
		
        public String getEstabelecimento() {
			return estabelecimento;
		}
		
        public double getValor() {
        		return valor;
        }
			
}