package com.projetoP2.listadecompras;

import java.io.IOException;

import com.projetoP2.listadecompras.library.ListaDeCompras;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class SugestaoLista extends Activity {
	ListaDeCompras listaSugerida;
	EditText nome;
	Documento doc = Documento.getInstance(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sugestao_lista);
	}
	
	@Override
	protected void onStart() {
		try {
			
			ListView lista = (ListView) findViewById(R.lista_sugerida.lista);
			Button aceitar = (Button) findViewById(R.lista_sugerida.btn_Confirmar);
			nome = (EditText) findViewById(R.lista_sugerida.nomeLista);
			listaSugerida = MainActivity.gerencia.sugereListaDeCompras("lista sugerida");
			//listaSugerida = MainActivity.gerencia.getListaSugeridaDeProdutos("nome", 8);
			
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1 , listaSugerida.getNomeProdutos());
			lista.setAdapter(adapter);
			aceitar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listaSugerida.setNome(nome.getText().toString());
					MainActivity.gerencia.add(listaSugerida);
					try {
						doc.salvarConjunto(MainActivity.gerencia);
						Toast.makeText(getApplicationContext(), nome.getText().toString() + " adicionada!", Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						Log.d("Erro", e.getMessage());
					}
					
					onBackPressed();
				}
			});
			
			Button cancelar = (Button) findViewById(R.lista_sugerida.btn_Cancelar);
			cancelar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});
		} catch (Exception e) {
				
		}
		super.onStart();
	}

}
