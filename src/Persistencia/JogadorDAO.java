package Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Negocio.Jogador;

public class JogadorDAO {
	
	private static final String NOME_DO_ARQUIVO = "JogadorPersistência.ser";
	
	public void insert(ArrayList<Jogador> jogadores) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(NOME_DO_ARQUIVO);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(jogadores);
		objOut.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Jogador> selectAll() throws IOException, Exception{
		ArrayList<Jogador> listaAux;
		FileInputStream fileIn = new FileInputStream(NOME_DO_ARQUIVO);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		listaAux = (ArrayList<Jogador>) objIn.readObject();
		objIn.close();
		return listaAux;
	}

}
