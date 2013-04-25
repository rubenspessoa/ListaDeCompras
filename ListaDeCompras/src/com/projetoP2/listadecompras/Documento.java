package com.projetoP2.listadecompras;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.projetoP2.listadecompras.library.GerenciarListas;

import android.content.Context;
/**
 * Criar, salva e edita um arquivo onde é armazenado(Persistencia de dados) das informações do aplicativo. Salva o estado
 * de todos os objetos do aplicativo.
 * @author Arthur Felipe, Joao Paulo Ribeiro, Rubens Pessoa, Victor Souto
 *
 */
public class Documento {
	public static Documento documento = null;
	private ObjectInputStream object;
	String FILENAME = "conjunto.txt";
	private ObjectOutputStream obj;
	Context context;
	
	private Documento(Context context) {
		this.context = context;
	}
	
	public static Documento getInstance(Context context){
		
		if (documento == null){
			documento = new Documento(context);
		}
		return documento;
		
	}
	/**
	 * Carrega as informações do documento e retorna o Objeto com seu estado recuperado.
	 * @return GerenciarListas, o Objeto com seu estado recuperado.
	 * @throws StreamCorruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public GerenciarListas carregarDocumento() throws StreamCorruptedException, IOException, ClassNotFoundException{
		FileInputStream file = context.openFileInput(FILENAME);
		object = new ObjectInputStream(file);
		
		Object con = object.readObject();
		GerenciarListas conjunto = (GerenciarListas) con;
		
		return conjunto;
		
	}
	/**
	 * Salva o estado dos objetos do aplicativo.
	 * @param gerencia Obejeto do tipo GerenciarListas 
	 * @throws IOException
	 */
	public void salvarConjunto(GerenciarListas gerencia) throws IOException{
		FileOutputStream file = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		obj = new ObjectOutputStream(file);
		obj.writeObject(gerencia);		
		
	}
	
	
}
