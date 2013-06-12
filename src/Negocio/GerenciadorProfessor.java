package Negocio;
import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Professor;

public class GerenciadorProfessor {
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	
	public void cadastrarProfessor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		if(buscarProfessor(professor)){
			throw new ObjetoJaExistenteException("Esse professor j� existe");
		}
		if(professor.getSenha() == null || professor.getSenha().length() < 4){
			throw new ObjetoInexistenteException("Senha inv�lida");
		}
		professores.add(professor);
	}
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException{
		if(!buscarProfessor(professor)){
			throw new ObjetoInexistenteException("Esse professor n�o existe");
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
