package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.ArrayList;
 
public class Produto implements Serializable {
    
        /**
         * 
         */
        private static final long serialVersionUID = -2017858648084823895L;
        
        String nome, descricao, data;
        double valor;
        ArrayList<EventoDePreco> eventosDePreco = new ArrayList<EventoDePreco>();
 
        public Produto(String nome, String descricao, double valor) {
                this.nome = nome;
                this.descricao = descricao;
                this.valor = valor;
        }
 
        public Produto onCreate(String nome, String descricao, double valor){
                return new Produto(nome, descricao, valor);
        }
        
        public Produto read(){
                return this;
        }
        
        public void update(String descricao, double valor){
                this.descricao = descricao;
                this.valor = valor;
        }
        
        public void onDelete(){
                this.descricao = null;
                this.valor = 0.0;
        }
        
        public double getValor() {
                return valor;
        }
 
 
        public void addEventoDePreco(double valor, String data){
                this.valor = valor;
                this.data = data;
        }
}