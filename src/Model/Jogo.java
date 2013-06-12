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



}
