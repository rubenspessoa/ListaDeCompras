package com.projetoP2.listadecompras;

import java.io.IOException;

import com.projetoP2.listadecompras.library.Produto;
import com.projetoP2.listadecompras.library.ProdutoEmKg;
import com.projetoP2.listadecompras.library.ProdutoEmUnidade;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
/**
 * (Activity)Tela de cadastro de novos produtos.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class CadastrarProduto extends Activity {
	EditText nome, preco,local,chave;
	Spinner tipo;
	Documento doc = Documento.getInstance(this);
	private  static final String[] tipos = { "Kg" ,"Und."};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_produto);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		tipo = (Spinner) findViewById(R.activity_cadastrar.spinnerTipo);
		
		ArrayAdapter<String> medidas = new ArrayAdapter<String>( this ,android.R.layout.simple_spinner_dropdown_item , tipos);
		tipo.setAdapter(medidas);
		Button confirmar = (Button) findViewById(R.activity_cadastrar_produto.btConfirmar);
		confirmar.setOnClickListener(new OnClickListener() {
			
			
			Produto produto;
			@Override
			public void onClick(View v) {
				try {
					nome = (EditText) findViewById(R.activity_cadastrar_produto.edtxNome);
					String nomeProduto = nome.getText().toString();
					preco = (EditText) findViewById(R.activity_cadastrar_produto.edtxPreco);
					double precoProduto = Double.parseDouble(preco.getText().toString());
					local = (EditText) findViewById(R.activity_cadastrar_produto.edtxLocal);
					String localVenda = local.getText().toString();
					chave = (EditText) findViewById(R.activity_cadastrar_produto.edtxChave);
					switch(tipo.getSelectedItemPosition()){
						case 0:
							produto = new ProdutoEmKg(nomeProduto, localVenda, precoProduto);
							produto.addPalavrasChave(chave.getText().toString());
							break;
						case 1:
							produto = new ProdutoEmUnidade(nomeProduto,localVenda,precoProduto);
							produto.addPalavrasChave(chave.getText().toString());
							break;
					}
					
					
					MainActivity.gerencia.add(produto);
					
					try {
						doc.salvarConjunto(MainActivity.gerencia);
						Toast.makeText(getApplicationContext(), produto.getNome() + " adicionado!", Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						Log.d("Erro", e.getMessage());
					}
					
					Intent intent = new Intent(CadastrarProduto.this, MainActivity.class);
					startActivity(intent);
					CadastrarProduto.this.finish();
				
				} catch(NumberFormatException e){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarProduto.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("É necessario todas informações.");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				} catch(IllegalArgumentException e2){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarProduto.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("Já existe um produto com este nome.");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
				
			}
		});
		
	}

}
