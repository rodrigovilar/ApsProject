package Negocio;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Excecao.FaseNaoDisponivelException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Balao;
import Model.Problema;
import Persistencia.ProblemaDAO;

public class GerenciadorProblema {
	private ArrayList<Problema> problemas = new ArrayList<Problema>();
	private boolean isProfessorLogado;
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	private ArrayList<Balao> baloes= new ArrayList<Balao>();
	private ProblemaDAO problemaDAO = new ProblemaDAO();
	
	public void cadastrarProblema(Problema problema) throws Exception {
		if(!isProfessorLogado){
			throw new LoginInexistenteException("Você não tem permissão para cadastrar o problema!");
		}
		problemas.add(problema);
		problemaDAO.insert(problemas);
		
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
	
	public void removerProblema(Problema problema) throws IOException, Exception{
		if(!buscarProblema(problema)){
			throw new ObjetoInexistenteException("Esse problema não existe");
		}
		if(!isProfessorLogado){
			throw new LoginInexistenteException("Você não tem permissão para cadastrar o problema!");
		}
		for(Problema p:problemaDAO.selectAll()){
			if(p.getQuestao().equals(problema.getQuestao())){
				problemas.remove(problema);
				problemaDAO.insert(problemas);
			}
		}
		
	}
	
	private boolean buscarProblema(Problema problema) throws IOException, Exception {
		for(Problema p:problemaDAO.selectAll()){
			if(p.getQuestao().equals(problema.getQuestao())){
				return true;
			}
		}
		return false;
	}
	
	public int getQuantidadeDeProblemasCadastrados() throws IOException, Exception{
		return problemaDAO.selectAll().size();
	}
	
	public ArrayList<Problema> listarProblemas() throws IOException, Exception{
		return problemaDAO.selectAll();
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
			throw new ObjetoInexistenteException("Não existe nenhum balão!");
		}
		else{
			return this.baloes.size();
		}
	}
	
	public void estourarBalao(int resposta) throws ObjetoInexistenteException, FaseNaoDisponivelException{
		boolean encontrou = false;
		int cont = 0;
		System.out.println("Quantidade de baloes: "+baloes.size());
		for(int i = 0; i < baloes.size(); i++){
			if(baloes.get(i).getResposta() == resposta && cont == 0){
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
}
