package Model;


public class Jogador {
	private String nome;
	private int id = 0;
	private static int score = 10;
	private Canhao canhao;
	
	public Jogador(){
		score = 10;
		id++;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
