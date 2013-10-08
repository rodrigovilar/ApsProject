package Negocio;
import java.io.IOException;
import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Professor;


public class GerenciadorProfessor {
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	
	
	public boolean loginProfessor(Professor professor){
		return buscarProfessor(professor);
	}
	
	public GerenciadorProblema getGerenciadorProblema(){
		return gerenciadorProblema;
	}
	
	public void cadastrarProfessor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		if(buscarProfessor(professor)){
			throw new ObjetoJaExistenteException("Esse professor já existe");
		}
		if(professor.getSenha() == null || professor.getSenha().length() < 4){
			throw new ObjetoInexistenteException("Senha invï¿½lida");
		}
		professores.add(professor);
		//Persistencia.escreverNoArquivo(professor);
	}
	
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException{
		if(!buscarProfessor(professor)){
			throw new ObjetoInexistenteException("Esse professor nï¿½o existe");
		}
		professores.remove(professor);
	}
	
	private boolean buscarProfessor(Professor professor) {
		for(Professor p:professores){
			if(p.getNome().equals(professor.getNome())){
				return true;
			}
		}
		return false;
	}
	
	public int getQuantidadeDeProfessoresCadastrados(){
		return professores.size();
	}
	
	public ArrayList<Professor> listarProfessores(){
		return professores;
	}
}
