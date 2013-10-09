package Negocio;

import java.util.ArrayList;
import Excecao.FaseNaoDisponivelException;
import Excecao.JogadorNaoLogadoException;
import Model.Fase;

public class GerenciadorFase {

private ArrayList<Fase> fases = new ArrayList<Fase>();
	
	public void inserirJogadorNaFase(Jogador jogador, Fase fase) throws FaseNaoDisponivelException{
		boolean faseLiberada = false;
		for(Fase f:fases){
			if(f.isLiberado() && (f.getNivel() == fase.getNivel())){
				f.criarJogador(jogador);
				faseLiberada = true;
				
			}
		}
		if(faseLiberada == false){
			throw new FaseNaoDisponivelException("Fase ainda não liberada"); 
		}
		
	}
	
	public boolean getIsFaseLiberada(){
		if(fases.size() > 0){
			return true;
		}
		return false;
	}
	
	public boolean isVerificarJogadorNaFaseDisponivel(Jogador jogador){
		for(Fase f: fases){
			if(f.getJogador().equals(jogador))
				return true;
		}
		return false;
	}
}