package com.projetoP2.listadecompras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
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
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
