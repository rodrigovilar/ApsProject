package Persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
