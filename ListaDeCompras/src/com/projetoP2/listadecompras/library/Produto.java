package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
public class Produto implements Serializable {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
       
        String nome, local;
        Date data;
        double valor;
        int quantidade;
        ArrayList<EventoDePreco> eventosDePreco = new ArrayList<EventoDePreco>();
 
        public Produto(String nome, String local, double valor) {
                this.nome = nome;
                this.local = local;
                this.valor = valor;
        }
 
        public Produto onCreate(String nome, String local, double valor){
                return new Produto(nome, local, valor);
        }
        
        public Produto read(){
                return this;
        }
        
        public void update(String local, double valor){
                this.local = local;
                this.valor = valor;
        }
        
        public void onDelete(){
                this.local = null;
                this.valor = 0.0;
        }
		
        public String getLocal() {
			return local;
		}
        public double getValor() {
            return valor;
        }
        public double getQtd() {
            return quantidade;
        }
 
		public String getNome() {
			return nome;
		}

		

		public void setLocal(String local) {
			this.local = local;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}
		
		public void setQtd(int novaquantidade) {
			this.quantidade = novaquantidade;
		}
		
		public void addUnidade() {
			this.quantidade += 1;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public void addEventoDePreco(double valor, Date data){
        	eventosDePreco.add(new EventoDePreco(new Date(), this.local, this.valor));
        }


}