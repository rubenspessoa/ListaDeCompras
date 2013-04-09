package com.projetoP2.listadecompras;

import com.projetoP2.listadecompras.library.Produto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.*;
/**
 * (Activity) Tela que mostra a situacao atual de um produto
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class ProdutoActivity extends Activity {
	TextView preco, local;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_produto);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = getIntent();
		Produto p = MainActivity.gerencia.getListaDeProdutos().get(intent.getExtras().getInt("index"));
		setTitle(p.getNome());
		preco = (TextView) findViewById(R.activity_produto.preco);
		preco.setText(String.format("%.2f", p.getValor()));
		local = (TextView) findViewById(R.activity_produto.local);
		local.setText(p.getEstabelecimento());
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.produto, menu);
		return true;
	}

}
