package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe que cria e edita um produto.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */

public abstract class Produto implements Serializable {
    
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
        
        public void onDelete() {
                this.estabelecimento = null;
                this.valor = 0.0;
                eventosDePreco.removeAll(eventosDePreco);
        }
        
        /**
         * Adiciona um evento de preco ao produto
         * @param valor do produto
         * @param estabelecimento local onde foi encontrado
         */
        
		public abstract void addEventoDePreco(double valor, String estabelecimento);
		
		public String getNome() {
			return nome;
		}
		
        public String getEstabelecimento() {
			return eventosDePreco.getLast().getEstabelecimento();
		}
		
        public double getValor() {
        		return eventosDePreco.getLast().getValor();
        }
			
}