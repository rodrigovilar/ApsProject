package Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Problema;

public class ProblemaDAO {
	
	private static File fileProblema  = new File("ProblemaPersistência.ser");;
	
	public void insert(ArrayList<Problema> problemas) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileProblema);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(problemas);
		objOut.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Problema> selectAll() throws IOException, Exception{
		ArrayList<Problema> listaAux;
		FileInputStream fileIn = new FileInputStream(fileProblema);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		listaAux = (ArrayList<Problema>) objIn.readObject();
		objIn.close();
		return listaAux;
	}
	
	public static void deletarFileProblema(){
		fileProblema.delete();
	}

}
