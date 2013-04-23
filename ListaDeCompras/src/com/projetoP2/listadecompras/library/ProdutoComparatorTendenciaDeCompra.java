package com.projetoP2.listadecompras.library;

import java.util.Comparator;

public class ProdutoComparatorTendenciaDeCompra implements Comparator<Produto> {
		@Override
		public int compare(Produto a, Produto b) {
	        return a.getTendenciaDeCompra() - b.getTendenciaDeCompra();
	    }
}
