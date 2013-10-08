package Model;

import java.io.IOException;
import java.util.ArrayList;

import Excecao.FaseNaoDisponivelException;
import Excecao.JogadorNaoLogadoException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Negocio.GerenciadorJogador;
import Negocio.GerenciadorProfessor;
import Negocio.Jogador;


public class Jogo {
	
	
	public void loginJogador(Jogador jogador) throws JogadorNaoLogadoException, LoginInexistenteException{
		if(gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().getIsFaseLiberada()){
			throw new LoginInexistenteException("Login n�o permitido, jogador logado!");
		}
		if(gerenciadorJogador.loginJogador(jogador)){
			gerenciadorProfessor.getGerenciadorProblema().getGerenciadorFase().gerarFase();
		}
		
	}
	
	public void loginProfessor(Professor professor) throws LoginInexistenteException{
		if(gerenciadorProfessor.getGerenciadorProblema().getIsLogado()){
			throw new LoginInexistenteException("Login n�o permitido, professor logado!");
		}
		if(gerenciadorProfessor.loginProfessor(professor)){
			gerenciadorProfessor.getGerenciadorProblema().setIsProfessorLogado(true);
		}
	}
	
	public boolean jogoAcabou() {
		return isJogoAcabou;
	}
	
	public void fimDeJogo(Jogador jogador){
		for(Jogador j: gerenciadorJogador.listarJogadores()){
			if(j.equals(jogador) && (j.getCanhao().getMunicao().getQuantidadeDeBalas() == 0)){
				isJogoAcabou = true;
			}
		}
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
	
	public void cadastrarProfesssor(Professor professor) throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		gerenciadorProfessor.cadastrarProfessor(professor);
	}
	
	public ArrayList<Professor> listarProfessores() {
		return gerenciadorProfessor.listarProfessores();
	}
	
	public void removerProfessor(Professor professor) throws ObjetoInexistenteException {
		gerenciadorProfessor.removerProfessor(professor);
	}
	
	public void cadastrarProblema(Problema problema) throws ObjetoJaExistenteException, ObjetoInexistenteException, LoginInexistenteException {
		gerenciadorProfessor.getGerenciadorProblema().cadastrarProblema(problema);
	}
	
	public int getQuantidadeDeProblemasCadastrados() {
		return gerenciadorProfessor.getGerenciadorProblema().getQuantidadeDeProblemasCadastrados();
	}
	
	public ArrayList<Problema> listarProblemas() {
		return gerenciadorProfessor.getGerenciadorProblema().listarProblemas();
	}
	
	public void removerProblema(Problema problema_1) throws ObjetoInexistenteException, LoginInexistenteException {
		gerenciadorProfessor.getGerenciadorProblema().removerProblema(problema_1);
		
	}
	
	
}
