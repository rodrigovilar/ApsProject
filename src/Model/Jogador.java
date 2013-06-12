package Model;


import Excecao.BalasEsgotadasException;

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
	public int getId() {
		return id;
	}
	public static void incrementarScore(){
		score++;
	}
	public static void decrementarScore(){
		score--;
	}
	public int getScore() {
		return score;
	}
	public Canhao getCanhao() {
		return canhao;
	}
	public void setCanhao(Canhao canhao){
		this.canhao = canhao;
	}
	public void atirar() throws BalasEsgotadasException{
		this.canhao.getTiro().diminuirQuantidadeDeBalas();
	}
}
