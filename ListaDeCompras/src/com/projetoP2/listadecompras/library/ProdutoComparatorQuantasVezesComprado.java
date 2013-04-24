package com.projetoP2.listadecompras.library;

import java.util.Comparator;

public class ProdutoComparatorQuantasVezesComprado implements Comparator<Produto> {

	@Override
	public int compare(Produto lhs, Produto rhs) {
		return (lhs.compareTo(rhs));
	}


}
