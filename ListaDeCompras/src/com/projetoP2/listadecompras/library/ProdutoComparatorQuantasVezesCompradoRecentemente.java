package com.projetoP2.listadecompras.library;

import java.util.Comparator;

public class ProdutoComparatorQuantasVezesCompradoRecentemente implements Comparator<Produto> {
	@Override
	public int compare(Produto a, Produto b) {
		return ((a.getAmostraEventosDePreco().size()) - (b.getAmostraEventosDePreco().size()));
	}
}
