package Negocio;

public class GerenciadorProblema {
	private ArrayList<Problema> problemas = new ArrayList<Problema>();
	private Professor professor;
	ArrayList<Professor> professoresCadastrados;
	private ArrayList<Balao> baloes= new ArrayList<Balao>();
	
	public void loginProfessor(Professor professor, ArrayList<Professor> professoresCadastrados){
		this.professor = professor;
		this.professoresCadastrados = professoresCadastrados;
		
	}
	public void cadastrarProblema(Problema problema) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		if(!autenticarProfessor()){
			throw new ObjetoInexistenteException("Para cadastrar o problema, o professor deve est logado");
		}
		if(buscarProblema(problema)){
			throw new ObjetoJaExistenteException("Esse problema j existe");
		}
		problemas.add(problema);
	}
	

}
