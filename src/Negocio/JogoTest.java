package Negocio;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Excecao.BalasEsgotadasException;
import Excecao.FaseNaoDisponivelException;
import Excecao.JogadorNaoLogadoException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Canhao;
import Model.Fase;
import Model.Jogo;
import Model.Municao;
import Model.Problema;
import Model.Professor;

@SuppressWarnings("deprecation")
public class JogoTest {
	
	private Jogo jogo;
	
	@Before
	public void iniciarTeste() throws IOException{
		jogo = new Jogo();
		jogo.iniciandoDAO();
	}
	
	@After
	public void destruirArquivo(){
		jogo.destruirArquivo();
	}
	
	@Test
	public void iniciarJogo(){
		Assert.assertFalse("O jogo já iniciou acabado", jogo.jogoAcabou());
	}
	
	private Professor instanciarObjetoProfessor(){
		Professor p1 = new Professor();
		p1.setNome("Antônio");
		p1.setSenha("81011054");
		return p1;
	}
	
	private Jogador instanciarObjetoJogador(){
		Jogador j1 = new Jogador();
		j1.setNome("Rodrigo");
		j1.setSenha("alucard1800");
		return j1;
	}
	
	private Problema instanciarObjetoProblema(){
		Problema pro1 = new Problema();
		pro1.setQuestao("Qual o resultado da operação (3x3)²?");
		pro1.setResposta(81);
		return pro1;
	}
	
	private Fase instanciarObjetoFase(){
		Fase f1 = new Fase();
		return f1;
	}
	
	private Canhao instanciarObjetoCanhao(){
		Canhao c = new Canhao();
		return c;
	}
	
	@Test
	public void cadastrarProfessor() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		Assert.assertEquals("Esse teste espera que o valor do arquivo cadastrado atualize para 1", 1, novoJogo.getQuantidadeDeProfessoresCadastrados());
	}
	
	@Test
	public void removerProfessor() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.removerProfessor(p1);
		Assert.assertEquals("Esse teste remove um objeto professor do arquivo", 0, novoJogo.getQuantidadeDeProfessoresCadastrados());
		
	}
	
	
	

}
