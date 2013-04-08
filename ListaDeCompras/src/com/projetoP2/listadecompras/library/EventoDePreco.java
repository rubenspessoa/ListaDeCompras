package com.projetoP2.listadecompras.library;

import java.io.Serializable;
import java.util.Date;
public class EventoDePreco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469973788231849455L;
	
	String estabelecimento; //Generico (Implementar mais cuidadosamente depois :P)
	double valor;
	Date data;

	public EventoDePreco(Date data, String estabelecimento, double valor) {
		this.data = data;
		this.estabelecimento = estabelecimento;
		this.valor = valor;
	}


	public Date getData() {
		return this.data;
	}


	public void setData(Date data) {
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
