package com.projetoP2.listadecompras;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.projetoP2.listadecompras.library.GerenciarListas;
import com.projetoP2.listadecompras.library.ListaDeCompras;
import com.projetoP2.listadecompras.library.Produto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
//Lista de compras atual
public class ListaActivity extends Activity {
	//Nomes dos produtos que compõem a lista.
	Documento doc = Documento.getInstance(this);
	ListaDeCompras listaCompra;
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
		String nomeLista = intent.getExtras().getString("nome");
		
		
		for (int i = 0; i < MainActivity.gerencia.getListasDeCompras().size(); i++) {
			
			if (MainActivity.gerencia.getListasDeCompras().get(i).getNome().equals(nomeLista)){
				listaCompra = MainActivity.gerencia.getListasDeCompras().get(i);
				if (listaCompra.getNomeProdutos().length == 0){
					addProdutos();
				}
			}
			
		}
		
		setTitle(nomeLista);
		
		/*List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (int i = 0;i < listaCompra.getNomeProdutos().length;i++) {
		    Map<String, String> datum = new HashMap<String, String>(2);
		    datum.put("nome", listaCompra.getNomeProdutos()[i]);
		    datum.put("valor", String.format("%.2f",listaCompra.getValorProdutos()[i]));
		    data.add(datum);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"nome", "valor"},
                new int[] {android.R.id.text1,
                           android.R.id.text2})*/
		ListView lista = (ListView) findViewById(R.activity_lista.listProdutos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1){
			 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Recupera o produto selecionado de acordo com a sua posição no ListView
				String nomeProduto = listaCompra.getNomeProdutos()[position];
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
	            TextView txt2 = (TextView) v.findViewById(R.item_produto.txtpreco);
	            txt2.setText(valorProduto);
	 
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      	case R.id.excluir:
	      		/*
	      		 * Exclui a lista de compras
	      		 */
	      	case R.id.addNaLista:
	      		addProdutos();
	      		break;
		} 
	    	
		return super.onOptionsItemSelected(item);
	}
	
	public void addProdutos(){
		setContentView(R.layout.selecionar_produto);
		ListView list = (ListView) findViewById(R.selecionar_produto.listItens);
		
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
		                	for (int i = 0;i < MainActivity.gerencia.getListaDeProdutos().size();i++){
		                		if(MainActivity.gerencia.getListaDeProdutos().get(i).getNome().equals(produto)){
		                			listaCompra.add(MainActivity.gerencia.getListaDeProdutos().get(i));
		                			Toast.makeText(getApplicationContext(), produto +" adicionado", Toast.LENGTH_SHORT).show();
		                		}
		                	}
		                	
		                } else {
		                	for (int i = 0;i < MainActivity.gerencia.getListaDeProdutos().size();i++){
		                		if(MainActivity.gerencia.getListaDeProdutos().get(i).getNome().equals(produto)){
		                			listaCompra.removerProduto(i);
		                			Toast.makeText(getApplicationContext(), produto +" removido", Toast.LENGTH_SHORT).show();
		                		}
		                	}
		                }
		            }
	            });
	            
	            Button adicionar = (Button) findViewById(R.selecionar_produto.buttonAdd);
	            adicionar.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						try {
							setContentView(R.layout.activity_lista);
							doc.salvarConjunto(MainActivity.gerencia);
							Toast.makeText(getApplicationContext(), "Lista atualizada!", Toast.LENGTH_SHORT).show();
						} catch (IOException e) {
							Log.d("Erro", e.getMessage());
						}
					}
				});
	            
	            
	            TextView txt = (TextView) v.findViewById(R.item_produto.txtproduto);
	            txt.setText(nomeProduto);
	            TextView txt2 = (TextView) v.findViewById(R.item_produto.txtpreco);
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
}
