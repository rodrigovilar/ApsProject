package Negocio;
import java.io.IOException;
import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Professor;
import Persistencia.ProfessorDAO;


public class GerenciadorProfessor {
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	private ProfessorDAO professorDAO = new ProfessorDAO();
	
	public boolean loginProfessor(Professor professor) throws Exception{
		return buscarProfessor(professor);
	}
}
