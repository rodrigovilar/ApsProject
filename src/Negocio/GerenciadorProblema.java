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

	private boolean autenticarProfessor(){
		for(Professor p: professoresCadastrados){
			if(professor.equals(p)){
				return true;
			}
		}
		return false;
	}
	
	public void removerProblema(Problema problema) throws ObjetoInexistenteException{
		if(!buscarProblema(problema)){
			throw new ObjetoInexistenteException("Esse problema no existe");
		}
		problemas.remove(problema);
	}
	private boolean buscarProblema(Problema problema) {
		for(Problema p:problemas){
			if(p.getQuestao().equals(problema.getQuestao())){
				return true;
			}
		}
		return false;
	}
	public int getQuantidadeDeProblemasCadastrados(){
		return problemas.size();
	}
	public ArrayList<Problema> listarProblemas(){
		return problemas;
	}
	public boolean verificarSeRespostaCorretaEmBalao(int resposta){
		for(Balao b: baloes){
			if(b.getResposta() == resposta){
				return true;
			}
		}
		return false;
	}
	public void gerarBaloes(Problema problema){
		
		Random random = new Random();
		int posicao = random.nextInt(9);
		for(int i = 0; i< 10; i++){
			Balao b = new Balao();
			if(i == posicao){
				b.setResposta(problema.getResposta());
				baloes.add(b);
			}
			else{
				
				b.setResposta(i);
				baloes.add(b);
			}
		}
	}
}
