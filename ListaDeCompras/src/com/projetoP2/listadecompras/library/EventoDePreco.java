package com.projetoP2.listadecompras.library;

import java.io.Serializable;

public class EventoDePreco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469973788231849455L;
	
	String data, estabelecimento; //Generico (Implementar mais cuidadosamente depois :P)
	double valor;
	

	public EventoDePreco(String data, String estabelecimento, double valor) {
		this.data = data;
		this.estabelecimento = estabelecimento;
		this.valor = valor;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
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
	
	
}
