package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.Date;
/**
 * Evento de preco de um produto, determina e atualiza o local e preco que o produto foi comprado
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class EventoDePreco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469973788231849455L;
	

	String estabelecimento; 
	double valorPago;
	Date data;

	public EventoDePreco(double valor, String estabelecimento) {
		data = new Date();
		this.estabelecimento = estabelecimento;
		this.valorPago = valor;
	}	

	public Date getData() {
		return data;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public double getValor() {
		return valorPago;
	}

	public int compareTo(Date anotherDate) {
		return data.compareTo(anotherDate);
	}
	
}
