package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.ArrayList;
 
public class Produto implements Serializable {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
        
        String nome, local, data;
        double valor;
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
        
        public double getValor() {
                return valor;
        }
 
 
        public void addEventoDePreco(double valor, String data){
                this.valor = valor;
                this.data = data;
        }

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}
        
        
}