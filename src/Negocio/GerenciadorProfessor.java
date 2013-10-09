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
	
	public GerenciadorProblema getGerenciadorProblema(){
		return gerenciadorProblema;
	}
	
	public void cadastrarProfessor(Professor professor) throws Exception {
		if(buscarProfessor(professor)){
			throw new ObjetoJaExistenteException("Não é possível cadastrar o mesmo professor duas vezes");
		}
		if(professor.getSenha().length() <= 4){
			throw new ObjetoInexistenteException("A senha tem que ter mais do que 4 caracteres!");
		}
		professores.add(professor);
		professorDAO.insert(professores);
	}
	
	public void removerProfessor(Professor professor) throws Exception{
		if(!buscarProfessor(professor)){
			throw new ObjetoInexistenteException("Esse professor não existe");
		}
		for(Professor p:professorDAO.selectAll()){
			if(p.getNome().equals(professor.getNome())){
				professores.remove(professor);
				professorDAO.insert(professores);
			}
		}
	}
}
