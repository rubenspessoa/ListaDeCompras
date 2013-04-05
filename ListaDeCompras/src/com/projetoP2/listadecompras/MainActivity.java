package com.projetoP2.listadecompras;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		String[] nomesDasListas = new String[]{"Lista A","Lista B","Lista C"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this ,android.R.layout. simple_list_item_1 , nomesDasListas); 
		
		ListView lista = (ListView) findViewById(R.activity_main.listListasDeCompras);
		lista.setAdapter(adapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(MainActivity.this,ListaActivity.class);
				startActivity(intent);
			}
			
		});
		
		Button adicionar = (Button) findViewById(R.activity_main.buttonAdicionar);
		adicionar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ExibeDialog();
				
			}
		});

	}
	
	private void ExibeDialog() {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_add_lista);
	
		dialog.setTitle("Adicionar nota");
		
		final EditText nomeLista = (EditText) dialog.findViewById(R.dialog_add_lista.nomeLista);

		
		final Button confirmar = (Button) dialog.findViewById(R.dialog_add_lista.btn_Confirmar);
		confirmar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					/*
					 * Adicionar lista ao conjunto de listas
					 */
					onStart();
					dialog.dismiss();
				} catch(IllegalArgumentException e){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("Necessario entrar com o nome da lista");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
			}
		});
		
		final Button cancelar = (Button) dialog.findViewById(R.dialog_add_lista.btn_Cancelar);
		cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//finaliza o dialog
				dialog.dismiss();
			}
		});
		//exibe o dialog	
		dialog.show();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      	case R.id.adicionar_produto:
	        	Intent intent = new Intent(MainActivity.this,CadastrarProduto.class);
	        	startActivity(intent);
	        	break;
	      	case R.id.lista_de_produto:
	      		Intent intent2 = new Intent(MainActivity.this,ListaDeProdutos.class);
	        	startActivity(intent2);
	        	break;
		} 
	    	
		return super.onOptionsItemSelected(item);
	}

}
