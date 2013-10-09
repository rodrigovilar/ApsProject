package Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Professor;

public class ProfessorDAO {
	private static File fileProfessor ;
	
	public ProfessorDAO(){
		fileProfessor = new File("ProfessorPersistência.ser");
	}
	public void insert(ArrayList<Professor> professores) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileProfessor);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(professores);
		objOut.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Professor> selectAll() throws IOException, Exception{
		ArrayList<Professor> listaAux;
		FileInputStream fileIn = new FileInputStream(fileProfessor);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		listaAux = (ArrayList<Professor>) objIn.readObject();
		objIn.close();
		return listaAux;
	}
	
	public static void deletarFileProfessor(){
		fileProfessor.delete();
	}
}
