package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.Date;

public class EventoDePreco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469973788231849455L;
	

	String estabelecimento; 
	double valor;
	Date data;

	public EventoDePreco(double valor, String estabelecimento) {
		data = new Date();
		this.estabelecimento = estabelecimento;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int compareTo(Date anotherDate) {
		return data.compareTo(anotherDate);
	}
	
}
