package Negocio;

import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
public class GerenciadorJogador {
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	public boolean loginJogador(Jogador jogador){
		return buscarJogador(jogador);
	}
	
	public void cadastrarJogador(Jogador jogador) throws ObjetoJaExistenteException {
		if(buscarJogador(jogador)){
			throw new ObjetoJaExistenteException("Esse jogador j� existe");
		}
		jogadores.add(jogador);
	}
	public void removerJogador(Jogador jogador) throws ObjetoInexistenteException{
		if(!buscarJogador(jogador)){
			throw new ObjetoInexistenteException("Esse jogador n�o existe");
		}
		jogadores.remove(jogador);
	}
	private boolean buscarJogador(Jogador jogador) {
		for(Jogador j:jogadores){
			if(j.getNome().equals(jogador.getNome()) ){
				return true;
			}
		}
		return false;
	}
	
	public int getQuantidadeDeJogadoresCadastrados(){
		return jogadores.size();
	}
	public ArrayList<Jogador> listarJogadores(){
		return jogadores;
	}
}
