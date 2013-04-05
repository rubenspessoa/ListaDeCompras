package com.projetoP2.listadecompras;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class CadastrarProduto extends Activity {
	EditText nome, preco,local;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_produto);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Button confirmar = (Button) findViewById(R.activity_cadastrar_produto.btConfirmar);
		confirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					nome = (EditText) findViewById(R.activity_cadastrar_produto.edtxNome);
					preco = (EditText) findViewById(R.activity_cadastrar_produto.edtxPreco);
					local = (EditText) findViewById(R.activity_cadastrar_produto.edtxLocal);
					
					/*
					 * Cria um produto com as informações coletadas da activity e adiciona o produto a lista geral, depois salva
					 * o estado da lista de produtos no arquivo txt.
					 */
				} catch(NumberFormatException e){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarProduto.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("É necessario todas informações.");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
				
			}
		});
		
	}

}
