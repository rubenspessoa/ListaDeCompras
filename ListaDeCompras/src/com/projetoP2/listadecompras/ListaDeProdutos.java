package com.projetoP2.listadecompras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaDeProdutos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_de_produtos);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		String[] nomesDosProdutos = MainActivity.gerencia.nomesProdutos();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this ,android.R.layout. simple_list_item_1 , nomesDosProdutos);
		ListView lista = (ListView) findViewById(R.activity_lista_de_produtos.listProdutos);
		lista.setAdapter(adapter);
		
		Button adicionar = (Button) findViewById(R.activity_lista_de_produtos.addProduto);
		adicionar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListaDeProdutos.this,CadastrarProduto.class);
	        	startActivity(intent);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_de_produtos, menu);
		return true;
	}

}
