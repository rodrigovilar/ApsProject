package Negocio;
import java.util.ArrayList;

public class GerenciadorProfessor {
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	
	public void cadastrarProfessor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		if(buscarProfessor(professor)){
			throw new ObjetoJaExistenteException("Esse professor já existe");
		}
		if(professor.getSenha() == null || professor.getSenha().length() < 4){
			throw new ObjetoInexistenteException("Senha inválida");
		}
		professores.add(professor);
	}
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException{
		if(!buscarProfessor(professor)){
			throw new ObjetoInexistenteException("Esse professor não existe");
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
}
