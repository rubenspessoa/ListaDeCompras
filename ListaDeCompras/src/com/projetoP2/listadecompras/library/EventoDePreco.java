package com.projetoP2.listadecompras.library;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Date;
=======
import java.util.Calendar;

>>>>>>> Evento de Preço
public class EventoDePreco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469973788231849455L;
	
<<<<<<< HEAD
	String estabelecimento; //Generico (Implementar mais cuidadosamente depois :P)
=======
	Calendar data;
	String estabelecimento;
>>>>>>> Evento de Preço
	double valor;
	Date data;

<<<<<<< HEAD
	public EventoDePreco(Date data, String estabelecimento, double valor) {
		this.data = data;
=======
	public EventoDePreco(double valor, String estabelecimento) {
		data = Calendar.getInstance();
>>>>>>> Evento de Preço
		this.estabelecimento = estabelecimento;
		this.valor = valor;
	}


<<<<<<< HEAD
	public Date getData() {
		return this.data;
	}


	public void setData(Date data) {
		this.data = data;
	}


=======
	public Calendar getData() {
		return data;
	}

>>>>>>> Evento de Preço
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


	public int compareTo(Calendar anotherCalendar) {
		return data.compareTo(anotherCalendar);
	}
	
}
