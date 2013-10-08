package Negocio;
import java.io.IOException;
import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Professor;


public class GerenciadorProfessor {
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	//private Persistencia persistencia;
	
	public boolean loginProfessor(Professor professor){
		return buscarProfessor(professor);
	}
	
	public GerenciadorProblema getGerenciadorProblema(){
		return gerenciadorProblema;
	}
	
	
}
