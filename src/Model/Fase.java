package Model;

import Jogador;


public class Fase {

	private Jogador jogador;
	private String nome;
	private int nivel;
	private boolean liberado = false;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public boolean isLiberado() {
		return liberado;
	}
	public void setLiberado(boolean liberado) {
		this.liberado = liberado;
	}
	public Jogador getJogador() {
		return jogador;
	}
	
}
