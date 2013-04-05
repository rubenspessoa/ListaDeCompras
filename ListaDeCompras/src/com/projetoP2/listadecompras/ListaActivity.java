package com.projetoP2.listadecompras;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.*;

public class ListaActivity extends Activity {
	private String[] produtos = new String[] {"Produto A", "Produto B", "Produto C"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
	}

	@Override
	protected void onStart() {
		super.onStart();

		ListView lista = (ListView) findViewById(R.activity_lista.listProdutos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1){
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Recupera o produto selecionado de acordo com a sua posição no ListView
				String produto = produtos[position];
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
	            chc.setTag(produto);
	 
	            /** Definindo uma ação ao clicar no checkbox. Aqui poderiamos inserior o metodo para alterar
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
	            txt.setText(produto);
	 
	            return v;
            }
 
            @Override
            public long getItemId(int position) {
                return position;
            }
 
            @Override
            public int getCount() {
                return produtos.length;
            }
        };// Fim ArrayAdapter
        
        lista.setAdapter(adapter);
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
	      		/*
	      		 * Adiciona produto a lista atual
	      		 */
		} 
	    	
		return super.onOptionsItemSelected(item);
	}

}
