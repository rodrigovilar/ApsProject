package Model;

import java.util.ArrayList;

import Excecao.FaseInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Negocio.GerenciadorFase;
import Negocio.GerenciadorJogador;
import Negocio.GerenciadorProblema;
import Negocio.GerenciadorProfessor;
import Negocio.Jogador;

public class Jogo {
	private GerenciadorJogador gerenciadorJogador = new GerenciadorJogador();
	private GerenciadorProfessor gerenciadorProfessor = new GerenciadorProfessor();
	private GerenciadorProblema gerenciadorProblema = new GerenciadorProblema();
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	
	public boolean jogoAcabou() {
		return false;
	}
	
	public int getQuantidadeDeJogadoresCadastrados() {
		return gerenciadorJogador.getQuantidadeDeJogadoresCadastrados();
	}
	
	public void cadastrarJogador(Jogador jogador) throws ObjetoJaExistenteException {
		gerenciadorJogador.cadastrarJogador(jogador);
	}
	
	public void removerJogador(Jogador jogador) throws ObjetoInexistenteException{
		gerenciadorJogador.removerJogador(jogador);
	}
	
	public ArrayList<Jogador> listarJogadores(){
		return gerenciadorJogador.listarJogadores();
	}
	
	public int getQuantidadeDeProfessoresCadastrados() {
		return gerenciadorProfessor.getQuantidadeDeProfessoresCadastrados();
	}
	
	public void cadastrarProfesssor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		gerenciadorProfessor.cadastrarProfessor(professor);
	}
	
	public ArrayList<Professor> listarProfessores() {
		return gerenciadorProfessor.listarProfessores();
	}
	
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException {
		gerenciadorProfessor.removerProfessor(professor);
	}
	
	public void cadastrarProblema(Problema problema) throws ObjetoJaExistenteException, ObjetoInexistenteException {
		gerenciadorProblema.cadastrarProblema(problema);
	}
	
	public int getQuantidadeDeProblemasCadastrados() {
		return gerenciadorProblema.getQuantidadeDeProblemasCadastrados();
	}
	
	public ArrayList<Problema> listarProblemas() {
		return gerenciadorProblema.listarProblemas();
	}
	
	public void removerProblema(Problema problema_1) throws ObjetoInexistenteException {
		gerenciadorProblema.removerProblema(problema_1);
		
	}
	
	public void loginProfessor(Professor professor) {
		gerenciadorProblema.loginProfessor(professor, gerenciadorProfessor.listarProfessores());
		
	}
	
	public void gerarFase() {
		gerenciadorFase.gerarFase();
		
	}
	public void verificarExistenciaDeFase(int fase) throws FaseInexistenteException{
		gerenciadorFase.atualizarFase(fase);
	}
	
	public ArrayList<Fase> listarFases() {
		return gerenciadorFase.getFases();
	}
	
	public void inserirJogadorNaFase(Jogador jogador) throws FaseInexistenteException{
		gerenciadorFase.inserirJogadorNaFaseDisponivel(jogador);
	}
	
	public boolean isVerificarJogadorNaFase(Jogador jogador){
		return gerenciadorFase.isVerificarJogadorNaFaseDisponivel(jogador);
	}
	
	public void gerarBalao(Problema problema){
		gerenciadorProblema.gerarBaloes(problema);
	}
	
	public boolean verificarSeRespostaEstaEmBaloes(int resposta){
		return gerenciadorProblema.verificarSeRespostaCorretaEmBalao(resposta);
	}
	
	public int verificarQuantidadeDeBaloesGerados() throws ObjetoInexistenteException {
		return gerenciadorProblema.getQuantidadeDeBaloesGerados();
	}
	
	public void estourarBalao(int resposta) throws ObjetoInexistenteException {
		gerenciadorProblema.estourarBalao(resposta);
		
	}
}
