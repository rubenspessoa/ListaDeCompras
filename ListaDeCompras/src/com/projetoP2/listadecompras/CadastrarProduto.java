package com.projetoP2.listadecompras;

import java.io.IOException;

import com.projetoP2.listadecompras.library.Produto;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class CadastrarProduto extends Activity {
	EditText nome, preco,local;
	Documento doc = Documento.getInstance(this);
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
					String nomeProduto = nome.getText().toString();
					preco = (EditText) findViewById(R.activity_cadastrar_produto.edtxPreco);
					double precoProduto = Double.parseDouble(preco.getText().toString());
					local = (EditText) findViewById(R.activity_cadastrar_produto.edtxLocal);
					String localVenda = local.getText().toString();
					
					
					Produto produto = new Produto(nomeProduto,localVenda,precoProduto);
					MainActivity.gerencia.add(produto);
					
					try {
						doc.salvarConjunto(MainActivity.gerencia);
					} catch (IOException e) {
						Log.d("Bosta", e.getMessage());
					}
					
					/*
					 * Cria um produto com as informa��es coletadas da activity e adiciona o produto a lista geral, depois salva
					 * o estado da lista de produtos no arquivo txt.
					 */
				} catch(NumberFormatException e){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarProduto.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("� necessario todas informa��es.");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
				
			}
		});
		
	}

}
