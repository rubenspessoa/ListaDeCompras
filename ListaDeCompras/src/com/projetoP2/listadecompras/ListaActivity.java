package com.projetoP2.listadecompras;
import java.io.IOException;
import com.projetoP2.listadecompras.library.ListaDeCompras;
import com.projetoP2.listadecompras.library.Produto;

import android.os.Bundle;
import android.app.*;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * (Activity)Lista de compras sendo utilizada no momento
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class ListaActivity extends Activity {
	//Nomes dos produtos que compõem a lista.
	Documento doc = Documento.getInstance(this);
	ListaDeCompras listaCompra;
	String nomeLista;
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		try {
		
		Intent intent = getIntent();
		nomeLista = intent.getExtras().getString("nome");
		
		
		for (int i = 0; i < MainActivity.gerencia.getListasDeCompras().size(); i++) {
			
			if (MainActivity.gerencia.getListasDeCompras().get(i).getNome().equals(nomeLista)){
				listaCompra = MainActivity.gerencia.getListasDeCompras().get(i);
				if (listaCompra.getNomeProdutos().length == 0){
					addProdutos();
				}
			}
			
		}
		
		setTitle(nomeLista);
		
		
		ListView lista = (ListView) findViewById(R.activity_lista.listProdutos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1){
			String nomeProduto;
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Recupera o produto selecionado de acordo com a sua posição no ListView
				nomeProduto = listaCompra.getNomeProdutos()[position];
				Produto p;
				for (int i = 0; i < listaCompra.getMapaDeProdutos().size(); i++) {
					if (listaCompra.getNomeProdutos()[i].equals(nomeProduto)){
					//	p = listaCompra.getMapaDeProdutos();
					}	
				}
				
				String valorProduto = String.format("%.2f",listaCompra.getValorProdutos()[position]);
				// Se o ConvertView for diferente de null o layout ja foi "inflado"
				View v = convertView;
					if(v==null) {
					// "Inflando" o layout do item caso o memso ainda nao tenha sido feito
					LayoutInflater inflater = getLayoutInflater();
					v = (View) inflater.inflate(R.layout.item_produto, null);
				}
				
				// Recuperando o checkbox
	            CheckBox chc = (CheckBox) v.findViewById(R.item_produto.chcproduto);
	 
	            // Definindo um "valor" para o checkbox
	            chc.setTag(nomeProduto);
	 
	            /*
	             *  Definindo uma ação ao clicar no checkbox. Aqui poderiamos inserior o metodo para alterar
	             * o valor do produto.
	             */
	            chc.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		                CheckBox chk = (CheckBox) v;
		                String produto = (String) chk.getTag();
		                if(chk.isChecked()) {
		                	Toast.makeText(getApplicationContext(), produto + " coletado!", Toast.LENGTH_SHORT).show();
		                } else {
		                    Toast.makeText(getApplicationContext(), produto + " removido!", Toast.LENGTH_SHORT).show();
		                }
		            }
	            });
	            
	            
	            TextView txt = (TextView) v.findViewById(R.item_produto.txtproduto);
	            txt.setText(nomeProduto);
	            txt.setTag(nomeProduto);
	            TextView txt2 = (TextView) v.findViewById(R.item_produto.txtpreco);
	            txt2.setText(valorProduto);
	            
	            txt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						TextView tx =(TextView) v;
						String nome = (String) tx.getTag();
						dialogAtualizar(nome);
						Toast.makeText(getApplicationContext(), nome + " selecionado", Toast.LENGTH_SHORT).show();
					}
				});
	            
	            return v;
            }
 
            @Override
            public long getItemId(int position) {
                return position;
            }
 
            @Override
            public int getCount() {
                return listaCompra.getNomeProdutos().length;
            }
        };// Fim ArrayAdapter
        
        lista.setAdapter(adapter);
        
		} catch(Exception e){
			
		}
	}
	
	// Atualiza preço
	private void dialogAtualizar(String nome) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_atualizar_preco);
		dialog.setTitle("Atualizar preço");
		
		for (int i = 0; i < MainActivity.gerencia.nomesProdutos().length; i++) {
			if (MainActivity.gerencia.nomesProdutos()[i].equals(nome)){
				index = i;
			}	
		}
		
		Button btn = (Button) dialog.findViewById(R.dialog_atualizar_preco.btn_Confirmar);
		btn.setOnClickListener(new OnClickListener() {
			
			EditText preco = (EditText) dialog.findViewById(R.dialog_atualizar_preco.preco);
			EditText local = (EditText) dialog.findViewById(R.dialog_atualizar_preco.localDeVenda);
			
			@Override
			public void onClick(View arg0) {
				try {
					double precoAtual = Double.parseDouble(preco.getText().toString());
					String estabelecimento = local.getText().toString();
					MainActivity.gerencia.getListaDeProdutos().get(index).addEventoDePreco(precoAtual, estabelecimento);
					try {	
						doc.salvarConjunto(MainActivity.gerencia);
						Toast.makeText(getApplicationContext(), "Produto atualizado!", Toast.LENGTH_SHORT).show();
						setContentView(R.layout.activity_lista);
						onStart();
					} catch (IOException e) {
						Log.d("Erro", e.getMessage());
					}
					
					dialog.dismiss();
				} catch(IllegalArgumentException e){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(ListaActivity.this);
					dialogo.setTitle("Ops!");
					dialogo.setMessage("Necessario entrar com as informações");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
			}
		});
		
		final Button cancelar = (Button) dialog.findViewById(R.dialog_atualizar_preco.btn_Cancelar);
		cancelar.setOnClickListener(new OnClickListener() {
			
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
		getMenuInflater().inflate(R.menu.lista, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      	case R.id.excluirLista:
	      		for (int i = 0; i < MainActivity.gerencia.getListasDeCompras().size(); i++) {
	    			if (MainActivity.gerencia.getListasDeCompras().get(i).getNome().equals(nomeLista)){
	    				MainActivity.gerencia.deleteLista(i);
	    				try {
							doc.salvarConjunto(MainActivity.gerencia);
							Toast.makeText(getApplicationContext(), "Lista excluida", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(ListaActivity.this, MainActivity.class);
		    				startActivity(intent);
						} catch (IOException e) {
							Log.d("Erro", e.getMessage());
						}
	    				
	    			}
	    			
	    		}
	      	case R.id.addNaLista:
	      		addProdutos();
	      		break;
		} 
	    	
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 *  Adiciona Produto
	 */
	public void addProdutos(){
		setContentView(R.layout.selecionar_produto);
		ListView list = (ListView) findViewById(R.selecionar_produto.listItens);
		setTitle("Adicionar itens a lista");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1){
			 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Recupera o produto selecionado de acordo com a sua posição no ListView
				String nomeProduto = MainActivity.gerencia.nomesProdutos()[position];
				// Se o ConvertView for diferente de null o layout ja foi "inflado"
				View v = convertView;
					if(v==null) {
					// "Inflando" o layout do item caso o memso ainda nao tenha sido feito
					LayoutInflater inflater = getLayoutInflater();
					v = (View) inflater.inflate(R.layout.produto_add, null);
				}
				
				Button chc = (Button) v.findViewById(R.produto_add.btAdd);
	 
	            // Definindo um "valor" para o checkbox
	            chc.setTag(nomeProduto);
	 
	            /*
	             *  Definindo uma ação ao clicar no checkbox. Aqui poderiamos inserior o metodo para alterar
	             * o valor do produto.
	             */
	            chc.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		               	for (int i = 0;i < MainActivity.gerencia.getListaDeProdutos().size();i++){
		               		Button chk = (Button) v;
			                String produto = (String) chk.getTag();
		                	Produto pAdicionado = MainActivity.gerencia.getListaDeProdutos().get(i);
		                	if(pAdicionado.getNome().equals(produto)){
		                		EditText quantidade = (EditText) v.findViewById(R.produto_add.quantidade);
		                		double quant = Double.parseDouble(quantidade.getText().toString()); 
		                		listaCompra.add(pAdicionado,quant);
		                		listaCompra.getMapaDeProdutos().get(pAdicionado);
		                		Toast.makeText(getApplicationContext(), produto +" adicionado", Toast.LENGTH_SHORT).show();
		                		try {	
									doc.salvarConjunto(MainActivity.gerencia);
									Toast.makeText(getApplicationContext(), "Lista atualizada!", Toast.LENGTH_SHORT).show();
									setContentView(R.layout.activity_lista);
									onStart();
								} catch (IOException e) {
									Log.d("Erro", e.getMessage());
								}
		                	}
		                }
		            }
	            });
	            
	            TextView txt = (TextView) v.findViewById(R.produto_add.txtproduto);
	            txt.setText(nomeProduto);
	            TextView txt2 = (TextView) v.findViewById(R.produto_add.txtpreco);
	            txt2.setText(" ");
	            return v;
			}
			 @Override
	            public long getItemId(int position) {
	                return position;
	            }
	 
	            @Override
	            public int getCount() {
	                return MainActivity.gerencia.getListaDeProdutos().size();
	            }
		};
		
		list.setAdapter(adapter);
	}
	/*
	 * Dialogo de quantidade de produtos que devem ser adicionados ao carrinho
	 */
}
