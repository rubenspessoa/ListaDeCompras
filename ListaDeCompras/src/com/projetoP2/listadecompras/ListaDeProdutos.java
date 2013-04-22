package com.projetoP2.listadecompras;

import java.io.IOException;
import java.util.ArrayList;

import com.projetoP2.listadecompras.library.Produto;


import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
	EditText busca,nome,chaves;
	ImageView btnBusca;
	boolean ordemInversa = false;
	Documento doc = Documento.getInstance(this);
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
		registerForContextMenu(lista);
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
					if (lista.getItemAtPosition(arg2).equals(MainActivity.gerencia.getListaDeProdutos().get(i).getNome())) {
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

	public void dialogEditar(final int position){
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_editar_produto);
		Produto p = MainActivity.gerencia.getListaDeProdutos().get(position);
		Button confirmar, cancelar;
		
		nome = (EditText) dialog.findViewById(R.dialog_editar_produto.edtxNome);
		nome.setText(p.getNome());
		chaves = (EditText) dialog.findViewById(R.dialog_editar_produto.edtxChave);
		String palavras = "";
		for (int i = 0; i < p.palavrasChave.size(); i++) {
			if (p.palavrasChave.size() - 1 == i){
				palavras += p.palavrasChave.get(i);
			} else {
				palavras += p.palavrasChave.get(i)+",";
			}
		}
		chaves.setText(palavras);
		
		confirmar = (Button) dialog.findViewById(R.dialog_editar_produto.btn_Confirmar);
		confirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.gerencia.getListaDeProdutos().get(position).palavrasChave.clear();
				for (String palavra: chaves.getText().toString().split(",")){
					MainActivity.gerencia.getListaDeProdutos().get(position).addPalavrasChave(palavra);
				}
				MainActivity.gerencia.getListaDeProdutos().get(position).nome = nome.getText().toString();
				
				try {	
					doc.salvarConjunto(MainActivity.gerencia);
					Toast.makeText(getApplicationContext(), "Produto editado!", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
					onStart();
				} catch (IOException e) {
					Log.d("Erro", e.getMessage());
				}
			}
		});
		
		cancelar = (Button) dialog.findViewById(R.dialog_editar_produto.btn_Cancelar);
		cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu_produto, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		int itemPosition = contextMenuInfo.position;
		
		
		switch (item.getItemId()) {
		case R.id.excluir_produto:
			MainActivity.gerencia.listaDeProdutos.remove(MainActivity.gerencia.listaDeProdutos.get(itemPosition));
			try {
				Toast.makeText(getApplicationContext(), "Excluido!", Toast.LENGTH_SHORT).show();
				doc.salvarConjunto(MainActivity.gerencia);
			} catch (IOException e) {
				Log.d("Erro", e.getMessage());
			}
			onStart();
			break;

		case R.id.editar_produto:
			dialogEditar(itemPosition);
		}
		
		return super.onContextItemSelected(item);
	}
}
