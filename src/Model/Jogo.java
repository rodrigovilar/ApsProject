package Model;

public class Jogo {
	private GerenciadorJogador gerenciadorJogador = new GerenciadorJogador();
	private GerenciadorProfessor gerenciadorProfessor = new GerenciadorProfessor();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	
	public boolean jogoAcabou() {
		return false;
	}

}
