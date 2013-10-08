package Negocio;

import java.util.ArrayList;
import Excecao.FaseNaoDisponivelException;
import Excecao.JogadorNaoLogadoException;
import Model.Fase;

public class GerenciadorFase {
	

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
	
	public void atualizarFase(int indice) throws FaseNaoDisponivelException{
		if(indice <= 0 || indice > 5){
			throw new FaseNaoDisponivelException("Fase inexistente");
		}
		fases.get(indice).setLiberado(true);
		fases.add(indice, fases.get(indice));
	}
	
	public ArrayList<Fase> getFases(){
		return fases;
	}
	
	public void gerarFase() throws JogadorNaoLogadoException{
		int numeroMaximoDeFases = 5;
		Fase fase0 = new Fase();
		fase0.setLiberado(true);
		fases.add(fase0);
		for(int i = 1; i < numeroMaximoDeFases; i++){
			Fase fase = new Fase();
			fase.setNome("Fase: "+i);
			fase.setNivel(i);
			fase.setLiberado(false);
			fases.add(fase);
		}
		
	}

	public void passarDeFase() throws FaseNaoDisponivelException {
		int nivelAux = 0;
		for(Fase f:fases){
			if(f.isLiberado()){
				nivelAux = f.getNivel();
			}
		}
		if((nivelAux) > fases.size()){
			throw new FaseNaoDisponivelException("Fase inexistente!");
		}
		fases.get(nivelAux+1).setLiberado(true);
		
		
		
	}
}
