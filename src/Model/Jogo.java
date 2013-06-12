package Model;

public class Jogo {
	private GerenciadorJogador gerenciadorJogador = new GerenciadorJogador();
	private GerenciadorProfessor gerenciadorProfessor = new GerenciadorProfessor();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	
	public boolean jogoAcabou() {
		return false;
	}
	
	public int getQuantidadeDeJogadoresCadastrados() {
		return gerenciadorJogador.getQuantidadeDeJogadoresCadastrados();
	}
	
	public void cadastrarJogador(Jogador jogador) throws ObjetoJaExistenteException {
		gerenciadorJogador.cadastrarJogador(jogador);
	}
	
	public void removerJogador(Jogador jogador) throws ObjetoInexistenteException{
		gerenciadorJogador.removerJogador(jogador);
	}
	
	public ArrayList<Jogador> listarJogadores(){
		return gerenciadorJogador.listarJogadores();
	}
	
	public int getQuantidadeDeProfessoresCadastrados() {
		return gerenciadorProfessor.getQuantidadeDeProfessoresCadastrados();
	}
	
	public void cadastrarProfesssor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		gerenciadorProfessor.cadastrarProfessor(professor);
	}
	
	public ArrayList<Professor> listarProfessores() {
		return gerenciadorProfessor.listarProfessores();
	}
	
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException {
		gerenciadorProfessor.removerProfessor(professor);
	}
	
	public void cadastrarProblema(Problema problema) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		gerenciadorProblema.cadastrarProblema(problema);
	}
	
	public int getQuantidadeDeProblemasCadastrados() {
		return gerenciadorProblema.getQuantidadeDeProblemasCadastrados();
	}
	
	public ArrayList<Problema> listarProblemas() {
		return gerenciadorProblema.listarProblemas();
	}
	
	public void removerProblema(Problema problema_1) throws ObjetoInexistenteException {
		gerenciadorProblema.removerProblema(problema_1);
		
	}
	
	public void loginProfessor(Professor professor) {
		gerenciadorProblema.loginProfessor(professor, gerenciadorProfessor.listarProfessores());
		
	}
	
	public void gerarTodasAsFases() {
		gerenciadorFase.gerarTodasAsFases();
		
	}
	
	public ArrayList<Fase> listarFases() {
		return gerenciadorFase.getFases();
	}
	
	public void inserirJogadorNaFase(Jogador jogador){
		gerenciadorFase.inserirJogadorNaFaseDisponivel(jogador);
	}
	
	public boolean isVerificarJogadorNaFase(Jogador jogador){
		return gerenciadorFase.isVerificarJogadorNaFaseDisponivel(jogador);
	}






}
