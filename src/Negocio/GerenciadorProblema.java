package Negocio;



import java.util.ArrayList;
import java.util.Random;

import Excecao.FaseNaoDisponivelException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Balao;
import Model.Problema;

public class GerenciadorProblema {
	private ArrayList<Problema> problemas = new ArrayList<Problema>();
	private boolean isProfessorLogado;
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	private ArrayList<Balao> baloes= new ArrayList<Balao>();
	
	public void cadastrarProblema(Problema problema) throws ObjetoJaExistenteException, ObjetoInexistenteException, LoginInexistenteException {
		if(buscarProblema(problema)){
			throw new ObjetoJaExistenteException("Esse problema já existe");
		}
		if(!isProfessorLogado){
			throw new LoginInexistenteException("Você não tem permissão para cadastrar o problema!");
		}
		problemas.add(problema);
		
	}
	
	public GerenciadorFase getGerenciadorFase(){
		return gerenciadorFase;
	}
	
	public boolean getIsLogado(){
		return isProfessorLogado;
	}
	
	public void setIsProfessorLogado(boolean status){
		isProfessorLogado = status;
	}

	public void removerProblema(Problema problema) throws ObjetoInexistenteException, LoginInexistenteException{
		if(!buscarProblema(problema)){
			throw new ObjetoInexistenteException("Esse problema não existe");
		}
		if(!isProfessorLogado){
			throw new LoginInexistenteException("Você não tem permissão para cadastrar o problema!");
		}
		problemas.remove(problema);
	}
	private boolean buscarProblema(Problema problema) {
		for(Problema p:problemas){
			if(p.getQuestao().equals(problema.getQuestao())){
				return true;
			}
		}
		return false;
	}
	public int getQuantidadeDeProblemasCadastrados(){
		return problemas.size();
	}
	public ArrayList<Problema> listarProblemas(){
		return problemas;
	}
	public boolean verificarSeRespostaCorretaEmBalao(int resposta){
		for(Balao b: baloes){
			if(b.getResposta() == resposta){
				return true;
			}
		}
		return false;
	}
	public void gerarBaloes(Problema problema){
		
		Random random = new Random();
		int posicao = random.nextInt(10);
		for(int i = 0; i< 10; i++){
			Balao b = new Balao();
			if(i == posicao){
				b.setResposta(problema.getResposta());
				baloes.add(b);
			}
			else{
				
				b.setResposta(i);
				baloes.add(b);
			}
		}
	}
	public int getQuantidadeDeBaloesGerados() throws ObjetoInexistenteException{
		if(this.baloes.size() == 0){
			throw new ObjetoInexistenteException("Nao existe nenhum balao");
		}
		else{
			return this.baloes.size();
		}
	}
	
	public void estourarBalao(int resposta) throws ObjetoInexistenteException, FaseNaoDisponivelException{
		boolean encontrou = false;
		int cont = 0;
		for(int i = 0; i < baloes.size(); i++){
			
			if(baloes.get(i).getResposta() == resposta && cont == 0){
				System.out.println("Contador" + cont);
				cont++;
				baloes.remove(baloes.get(i));
				Jogador.incrementarScore();
				encontrou = true;
			}
		}
		if(encontrou){
			for(int i = 0; i < baloes.size(); i++){
					baloes.remove(baloes.get(i));
			}
		}
		verificarPassagemDeFase();
		if(encontrou == false){
			Jogador.decrementarScore();
		}
	}
	
	private void verificarPassagemDeFase() throws FaseNaoDisponivelException{
		if(Jogador.getScore() == 15){
			gerenciadorFase.passarDeFase();
			Jogador.setScoreInicial();
		}
		System.out.println(Jogador.getScore());
	}
}
