package Model;

import java.io.IOException;
import java.util.ArrayList;

import Excecao.FaseNaoDisponivelException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Negocio.GerenciadorJogador;
import Negocio.GerenciadorProfessor;
import Negocio.Jogador;
import Persistencia.JogadorDAO;
import Persistencia.ProblemaDAO;
import Persistencia.ProfessorDAO;


public class Jogo {
	private GerenciadorJogador gerenciadorJogador = new GerenciadorJogador();
	private GerenciadorProfessor gerenciadorProfessor;
	private boolean isJogoAcabou = false;
	
	public Jogo() throws IOException{
		gerenciadorProfessor = new GerenciadorProfessor();
	}
	
	public void loginJogador(Jogador jogador) throws IOException, Exception{
		if(gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().getIsFaseLiberada()){
			throw new LoginInexistenteException("Login não permitido, jogador logado!");
		}
		if(gerenciadorJogador.loginJogador(jogador)){
			gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().gerarFase();
		}
		
	}
	
	public void loginProfessor(Professor professor) throws Exception{
		if(gerenciadorProfessor.getGerenciadorProblema().getIsLogado()){
			throw new LoginInexistenteException("Login não permitido, professor logado!");
		}
		if(gerenciadorProfessor.loginProfessor(professor)){
			gerenciadorProfessor.getGerenciadorProblema().setIsProfessorLogado(true);
		}
	}
	
	public boolean jogoAcabou() {
		return isJogoAcabou;
	}
	
	public void fimDeJogo(Jogador jogador) throws IOException, Exception{
		for(Jogador j: gerenciadorJogador.listarJogadores()){
			if(j.getNome().equals(jogador.getNome()) && (jogador.getCanhao().getMunicao().getQuantidadeDeBalas() == 0)){
				isJogoAcabou = true;
			}
		}
	}
	
	public int getQuantidadeDeJogadoresCadastrados() throws IOException, Exception {
		return gerenciadorJogador.getQuantidadeDeJogadoresCadastrados();
	}
	
	public void cadastrarJogador(Jogador jogador) throws IOException, Exception {
		gerenciadorJogador.cadastrarJogador(jogador);
	}
	
	public void removerJogador(Jogador jogador) throws Exception{
		gerenciadorJogador.removerJogador(jogador);
	}
	
	public ArrayList<Jogador> listarJogadores() throws IOException, Exception{
		return gerenciadorJogador.listarJogadores();
	}
	
	public int getQuantidadeDeProfessoresCadastrados() throws Exception {
		return gerenciadorProfessor.getQuantidadeDeProfessoresCadastrados();
	}
	
	public void cadastrarProfesssor(Professor professor) throws Exception {
		gerenciadorProfessor.cadastrarProfessor(professor);
	}
	
	public ArrayList<Professor> listarProfessores() throws Exception {
		return gerenciadorProfessor.listarProfessores();
	}
	
	public void removerProfessor(Professor professor) throws Exception {
		gerenciadorProfessor.removerProfessor(professor);
	}
	
	public void cadastrarProblema(Problema problema) throws Exception {
		gerenciadorProfessor.getGerenciadorProblema().cadastrarProblema(problema);
	}
	
	public int getQuantidadeDeProblemasCadastrados() throws IOException, Exception {
		return gerenciadorProfessor.getGerenciadorProblema().getQuantidadeDeProblemasCadastrados();
	}
	
	public ArrayList<Problema> listarProblemas() throws IOException, Exception {
		return gerenciadorProfessor.getGerenciadorProblema().listarProblemas();
	}
	
	public void removerProblema(Problema problema_1) throws IOException, Exception {
		gerenciadorProfessor.getGerenciadorProblema().removerProblema(problema_1);
		
	}
	
	public void verificarExistenciaDeFase(int fase) throws FaseNaoDisponivelException{
		gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().atualizarFase(fase);
	}
	
	public ArrayList<Fase> listarFases() {
		return gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().getFases();
	}
	
	public void inserirJogadorNaFase(Jogador jogador, Fase fase) throws FaseNaoDisponivelException{
		gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().inserirJogadorNaFase(jogador, fase);
	}
	
	public boolean isVerificarJogadorNaFase(Jogador jogador){
		return gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().isVerificarJogadorNaFaseDisponivel(jogador);
	}
	
	public void gerarBalao(Problema problema){
		gerenciadorProfessor.getGerenciadorProblema().gerarBaloes(problema);
	}
	
	public boolean verificarSeRespostaEstaEmBaloes(int resposta){
		return gerenciadorProfessor.getGerenciadorProblema().verificarSeRespostaCorretaEmBalao(resposta);
	}
	
	public int verificarQuantidadeDeBaloesGerados() throws ObjetoInexistenteException {
		return gerenciadorProfessor.getGerenciadorProblema().getQuantidadeDeBaloesGerados();
	}
	
	public void estourarBalao(int resposta) throws ObjetoInexistenteException, FaseNaoDisponivelException {
		gerenciadorProfessor.getGerenciadorProblema().estourarBalao(resposta);
		
	}

	public void iniciandoDAO() throws IOException {
		ProfessorDAO profDAO = new ProfessorDAO();
		ArrayList<Professor> lista = new ArrayList<Professor>();
		profDAO.insert(lista);
		
		JogadorDAO jogDAO = new JogadorDAO();
		ArrayList<Jogador> listajog = new ArrayList<Jogador>();
		jogDAO.insert(listajog);
		
		
	}
	public void destruirArquivo() {
		JogadorDAO.deletarFileJogador();
		ProfessorDAO.deletarFileProfessor();
		ProblemaDAO.deletarFileProblema();
		
	}
}
