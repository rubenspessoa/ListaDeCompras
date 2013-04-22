package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
       
        public String nome, estabelecimento;
        double valor;
        public LinkedList<EventoDePreco> eventosDePreco = new LinkedList<EventoDePreco>();
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
		
        /**
		 * Retorna uma lista de eventos de preço referentes à um intervalo de tempo terminando na presente data. 
		 * O tamanho do intervalo é medido em dias e a ordem da lista retornada é: eventos mais recentes primeiro.
		 * 
		 * @return LinkedList<EventoDePreco> amostraEventosDePreco
		 */
		
		public LinkedList<EventoDePreco> getAmostraEventosDePreco(){
			int tamanhoIntervalo = 90; //Tamanho do intervalo em dias. Importante: esse valor é um parâmetro suscetível a mudanças para calibrar a sugestão.
			Date dataFim = new Date();
			long dataInicioArredondada = (((dataFim.getTime()/86400000) - tamanhoIntervalo)*86400000);
			LinkedList<EventoDePreco> amostraEventosDePreco = new LinkedList<EventoDePreco>();
			for(int i = this.eventosDePreco.size() ; i >= 0; i--){
				if (this.eventosDePreco.get(i).getData().getTime()>dataInicioArredondada){
					amostraEventosDePreco.addFirst(this.eventosDePreco.get(i));
				}else{
					break;
				}
			}
			return amostraEventosDePreco;
		}
		
		/**
		 * Gera uma array de intervalos de compras - em dias. É usado em getTendenciaIntervalo
		 * @return int [] ArrayIntervalos
		 */
		
		private int[] getIntervalosDeCompra(){
			int[] ArrayIntervalos;
			if (getAmostraEventosDePreco().size() < 2){
				ArrayIntervalos = new int[1];
				ArrayIntervalos[1] = 15; //Valor arbitrário para o caso de  não haver eventos de compra suficientes.
				return ArrayIntervalos;
			}
			ArrayIntervalos = new int[getAmostraEventosDePreco().size() - 1];
			for(int i = 0 ; i < getAmostraEventosDePreco().size() -1 ; i++){
				int evento_corrente = (int) (getAmostraEventosDePreco().get(i).getData().getTime()/86400000);
				int evento_proximo = (int)(getAmostraEventosDePreco().get(i+1).getData().getTime()/86400000);
				ArrayIntervalos[i] = evento_proximo - evento_corrente;
			}
			return ArrayIntervalos;
		}
		
		/**
		 * 	Retorna o intervalo de compras mais frequente - em dias - para 
		 * este produto a partir de uma medida de tendência central ( Atualmente utilizando a moda.)
		 * Chama o método getIntervalosDeCompra para iterar sobre
		 * 
		 * @return int moda
		 */
		
		private int getTendenciaDeIntervalo(){
			int moda = 0;
			int[] a = getIntervalosDeCompra();
			for (int i = 0; i < a.length; ++i) {
		        int count = 0;
		        for (int j = 0; j < a.length; ++j) {
		            if (a[j] == a[i]) ++count;
		        }
		        if (count > moda) {
		            moda = count;
		        }
		    }
			return moda;
		}
		/**
		 * Método que retorna um índice usado como tendência de compra. Este índice é o número de dias que faltam
		 * para a data provável para a próxima compra do produto, de acordo com os eventos de preço dele.
		 * Quanto mais próximo de 0, é mais provável que esse produto tenha que ser comprado na data atual.
		 */
		
		public int getTendenciaDeCompra(){
			Date dataHoje = new Date();
			int diaHoje = (int)dataHoje.getTime()/86400000;
			int tendencia = diaHoje - ((int)(getAmostraEventosDePreco().getLast().getData().getTime()/86400000) + getTendenciaDeIntervalo()); 
			if (tendencia < 0){
				return -tendencia;
			}
			return tendencia;
		}
		
		public EventoDePreco melhorEventoDePreco(){
			EventoDePreco melhorEvento = eventosDePreco.getFirst();
			double melhorPreco = eventosDePreco.getFirst().valorPago;
			for (EventoDePreco evento : eventosDePreco) {
				if (evento.valorPago < melhorPreco){
					melhorEvento = evento;
					melhorPreco = evento.valorPago;
				}
			}
			
			return melhorEvento;
		}
}