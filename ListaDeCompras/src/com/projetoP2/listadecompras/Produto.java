package com.projetoP2.listadecompras;

import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017858648084823895L;
	
	String nome, palavrasChave;
	ArrayList<EventoDePreco> eventosDePreco = new ArrayList<EventoDePreco>();

	public Produto(String nome, String palavrasChave) {
		this.nome = nome;
		this.palavrasChave = palavrasChave;
	}

	public double getPreco(){}
	
	public void addEventoDePreco(double valor, String data){}
	
}
