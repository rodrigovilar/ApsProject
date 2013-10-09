package Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Negocio.Jogador;

public class JogadorDAO {

private static File fileJogador = new File("JogadorPersistência.ser");
	
	public void insert(ArrayList<Jogador> jogadores) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileJogador);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(jogadores);
		objOut.close();
		}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Jogador> selectAll() throws IOException, Exception{
		ArrayList<Jogador> listaAux;
		FileInputStream fileIn = new FileInputStream(fileJogador);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		listaAux = (ArrayList<Jogador>) objIn.readObject();
		objIn.close();
		return listaAux;
	}
	
	public static void deletarFileJogador(){
		fileJogador.delete();
	}
}
