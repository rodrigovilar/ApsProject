package Persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
