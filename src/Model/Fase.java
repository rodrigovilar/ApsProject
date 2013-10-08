package Model;

import Negocio.Jogador;

public class Fase {

	private Jogador jogador;
	private String nome;
	private int nivel;
	private boolean liberado = false;
	
	
	public boolean isLiberado() {
		return liberado;
	}
	public void setLiberado(boolean liberado) {
		this.liberado = liberado;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void criarJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
}
