package com.projetoP2.listadecompras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/**
 * (Activity)Tela de lista de produtos, exibe todos os produtos existentes no sistema.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class ListaDeProdutos extends Activity {
	String[] nomesDosProdutos = MainActivity.gerencia.nomesProdutos();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_de_produtos);
	
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
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
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ListaDeProdutos.this,ProdutoActivity.class);
				
				for (int i = 0; i< nomesDosProdutos.length;i++){
					if(nomesDosProdutos[arg2].equals(MainActivity.gerencia.getListaDeProdutos().get(i).getNome())){
						intent.putExtra("index", i);
						break;
					}
				}
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
