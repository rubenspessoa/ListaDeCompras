package com.projetoP2.listadecompras;

import java.util.ArrayList;


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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
/**
 * (Activity)Tela de lista de produtos, exibe todos os produtos existentes no sistema.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class ListaDeProdutos extends Activity {
	String[] nomesDosProdutos;
	ListView lista;
	ArrayAdapter<String> adapter;
	EditText busca;
	ImageView btnBusca;
	boolean ordemInversa = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_de_produtos);
	
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if (!ordemInversa){
			nomesDosProdutos = MainActivity.gerencia.nomesProdutos();
		} else {
			nomesDosProdutos = MainActivity.gerencia.nomesProdutosInvertida();
		}
		adapter = new ArrayAdapter<String>( this ,android.R.layout. simple_list_item_1 , nomesDosProdutos);
		lista = (ListView) findViewById(R.activity_lista_de_produtos.listProdutos);
		lista.setAdapter(adapter);
		
		busca = (EditText) findViewById(R.activity_lista_de_produtos.ediBusca);
		btnBusca = (ImageView) findViewById(R.activity_lista_de_produtos.btnBusca);
		btnBusca.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String[] encontrado = search(busca.getText().toString());
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ListaDeProdutos.this, android.R.layout.simple_list_item_1, encontrado);
				lista.setAdapter(adapter1);
			}
		});
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
		
		ImageButton ordenado = (ImageButton) findViewById(R.activity_lista_de_produtos.ButtonOrdenado);
		ordenado.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!ordemInversa){
					ordemInversa = true;
					onStart();
					Toast.makeText(getApplicationContext(), "Clicado pra inverter", Toast.LENGTH_SHORT).show();
				} else {
					ordemInversa = false;
					onStart();
					Toast.makeText(getApplicationContext(), "Clicado pra ordenar", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_de_produtos, menu);
		return true;
	}
	
	public String[] search(String busca){
		ArrayList<String> searchArr = new ArrayList<String>();
		for (int i = 0; i < MainActivity.gerencia.getListaDeProdutos().size(); i++) {
			for (int j = 0; j < MainActivity.gerencia.getListaDeProdutos().get(i).palavrasChave.size(); j++) {
				if (MainActivity.gerencia.listaDeProdutos.get(i).palavrasChave.get(j).toLowerCase().contains(busca.toLowerCase())){
					searchArr.add(MainActivity.gerencia.listaDeProdutos.get(i).getNome());
					break;
				}
			}
			
		}
		String[] search = new String[searchArr.size()];
		for (int i = 0; i < search.length; i++) {
			search[i] = searchArr.get(i);
		}
		
		return search;
		
	}

}
