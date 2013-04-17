package com.projetoP2.listadecompras.library;

import java.util.Comparator;

public class Compara implements Comparator {

	@Override
	public int compare(Object p1, Object p2) {
		return ((Produto)p1).getNome().compareToIgnoreCase(((Produto) p2).getNome());
	}
	
}
