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
	

}
