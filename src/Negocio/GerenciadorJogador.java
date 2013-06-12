package Negocio;

import java.util.ArrayList;
public class GerenciadorJogador {
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

	public void cadastrarJogador(Jogador jogador) throws ObjetoJaExistenteException {
		if(buscarJogador(jogador)){
			throw new ObjetoJaExistenteException("Esse jogador já existe");
		}
		jogadores.add(jogador);
	}
}
