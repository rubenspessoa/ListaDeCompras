package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.LinkedList;

public class Produto implements Serializable {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
       
        String nome, estabelecimento;
        double valor;
        LinkedList<EventoDePreco> eventosDePreco = new LinkedList<EventoDePreco>();
 
        public Produto(String nome, String estabelecimento, double valor) {
                this.nome = nome;
                this.estabelecimento = estabelecimento;
                this.valor = valor;
                addEventoDePreco(valor, estabelecimento);
        }
 
        public Produto onCreate(String nome, String local, double valor){
                return new Produto(nome, local, valor);
        }
        
        public void onDelete() {
                this.estabelecimento = null;
                this.valor = 0.0;
                eventosDePreco.removeAll(eventosDePreco);
        }
        
		public void addEventoDePreco(double valor, String estabelecimento){
			eventosDePreco.add(new EventoDePreco(this.valor, this.estabelecimento));
        }
		
		public String getNome() {
			return nome;
		}
		
        public String getEstabelecimento() {
			return eventosDePreco.getLast().getEstabelecimento();
		}
		
        public double getValor() {
        		return eventosDePreco.getLast().getValor();
        }

		public void setEstabelecimento(String estabelecimento) {
			eventosDePreco.getLast().setEstabelecimento(estabelecimento);
		}

		public void setValor(double valor) {
			eventosDePreco.getLast().setValor(valor);
		}
		
}