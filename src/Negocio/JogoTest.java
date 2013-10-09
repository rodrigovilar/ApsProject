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
	
	@Test(expected=ObjetoJaExistenteException.class)
	public void cadastrarMesmoProfessorDuasVezes() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.cadastrarProfesssor(p1);
		
	}
	
	@Test
	public void verificaSeNomeDoProfessorEstaCorreto() throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		p1.setNome("Madalena");
		p1.setSenha("01411023");
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		ArrayList<Professor> listaProfessores = novoJogo.listarProfessores();
		Assert.assertEquals("Esse teste espera que o professor cadastrado seja igual ao professor salvo na lista", p1.getNome(), listaProfessores.get(0).getNome());
	}
	
	@Test(expected = ObjetoInexistenteException.class)
	public void removerProfessorInexistente() throws Exception {
		Professor p1= instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		Professor p2 = instanciarObjetoProfessor();
		p2.setNome("Joao");
		p2.setSenha("01013456");
		novoJogo.removerProfessor(p2);
	}
	
	@Test(expected = ObjetoInexistenteException.class)
	public void removerMesmoProfessorDuasVezes() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.removerProfessor(p1);
		Jogo maisUmJogo = new Jogo();
		maisUmJogo.removerProfessor(p1);
		
	}
	
	@Test
	public void cadastrarJogador() throws Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		Assert.assertEquals("Esse teste espera que a quantidade de jogadores cadastrados seja igual a 1", 1, novoJogo.getQuantidadeDeJogadoresCadastrados());
	}
	
	@Test
	public void verificarSeNomeDoJogadorEstaCorreto() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		Assert.assertEquals("Esse teste verifica se o nome do jogador foi cadastrado corretamente", j1.getNome(), listaJogadores.get(0).getNome());
	}
	
	@Test(expected=ObjetoJaExistenteException.class)
	public void cadastrarJogadorJaCadastrado() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.cadastrarJogador(j1);
	}
	
	@Test
	public void removerJogador() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.removerJogador(j1);
		Assert.assertEquals("Esse teste espera que o valor do arquivo de cadastro atualize para 1", 0, novoJogo.getQuantidadeDeJogadoresCadastrados());
		
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void removerMesmoJogadorDuasVezes() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.removerJogador(j1);
		Jogo maisUmJogo = new Jogo();
		maisUmJogo.removerJogador(j1);
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void removerJogadorInexistente() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogador j2 = instanciarObjetoJogador();
		j2.setNome("Wandemberg");
		j2.setSenha("atecubanos");
		Jogo novoJogo = new Jogo();
		novoJogo.removerJogador(j2);
	}
	
	@Test
	public void cadastrarProblema() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("Qual a metade de dois mais dois?");
		pro1.setResposta(3);
		novoJogo.cadastrarProblema(pro1);
		Jogo maisUmJogo = new Jogo();
		Assert.assertEquals("Esse teste espera que o valor da lista de problemas atualize para 1", 1, maisUmJogo.getQuantidadeDeProblemasCadastrados());
	}
	
	@Test(expected = ObjetoInexistenteException.class)
	public void senhaDeProfessorComMenosDeQuatroDigitosImpossibilidadeDeCadastro() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		p1.setSenha("ufpb");
		jogo.cadastrarProfesssor(p1);
	}
	
	@Test(expected = ObjetoInexistenteException.class)
	public void senhaDeJogadorComMenosDeQuatroDigitosImpossibilidadeDeCadastro() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		j1.setNome("Piu_Pesadelo");
		j1.setSenha("@$#%");
		jogo.cadastrarJogador(j1);
	}
	
	@Test
	public void removerProblema() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("Quanto vale um terço de nove?");
		pro1.setResposta(3);
		novoJogo.cadastrarProblema(pro1);
		Jogo maisUmJogo = new Jogo();
		maisUmJogo.loginProfessor(p1);
		maisUmJogo.removerProblema(pro1);
		Assert.assertEquals("Esse teste espera que a lista de problemas seja atualizada para 0", 0, maisUmJogo.getQuantidadeDeProblemasCadastrados());
	}
	
	@Test
	public void verificaQuestaoDoProblemaCadastradoSeCadastrouCorretamente() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("Qual o valor do quadrado de 1/2?");
		pro1.setResposta((int)0.25);
		novoJogo.cadastrarProblema(pro1);
		Jogo maisUmJogo = new Jogo();
		ArrayList<Problema> listaProblemas = maisUmJogo.listarProblemas();
		Assert.assertEquals("Esse teste espera que o valor da questão cadastrada seja a mesma que está no arquivo", pro1.getQuestao(), listaProblemas.get(0).getQuestao());
	}
	
	@Test
	public void verificaSeRespostaDaQuestaoDoProblemaFoiCadastradaCorretamente() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("Qual o valor da soma de x+2.x, considerando x = 2?");
		pro1.setResposta(8);
		novoJogo.cadastrarProblema(pro1);
		Jogo maisUmJogo = new Jogo();
		ArrayList<Problema> listaProblemas = maisUmJogo.listarProblemas();
		Assert.assertEquals("Esse teste espera que a resposta da questão cadastrada seja a mesma que está no arquivo", pro1.getResposta(), listaProblemas.get(0).getResposta());
	}
	
	@Test
	public void verificarScoreInicialDoJogador() {
		Assert.assertEquals("Esse teste espera que a quantidade de score inicial do jogador seja igual a 10", 10, Jogador.getScore());
	}
	
	@Test
	public void verificarIDdoJogadorCadastrado() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		Assert.assertEquals("Esse teste espera que o Id do jogador cadastrado seja igual a 1", 1, listaJogadores.get(0).getId());
	}
	
	@Test
	public void verificarIDdoProfessorCadastrado() throws Exception{
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		ArrayList<Professor> listaProfessores = novoJogo.listarProfessores();
		Assert.assertEquals("Esse teste espera que o Id do professsor cadastrado seja igual a 1", 1, listaProfessores.get(0).getId());
	}
	
	@Test
	public void inserirJogadorNaFase() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(0);
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		Assert.assertEquals("Esse teste espera que o jogador cadastrado seja inserido na fase", listaJogadores.get(0), listaFases.get(0).getJogador());
	}
	
	@Test(expected=FaseNaoDisponivelException.class)
	public void verificarFaseIndisponivel() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(1);
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
	}
	
	@Test
	public void verificarOsNiveisDasFasesGeradas() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		Assert.assertEquals("Esse teste espera que a quantidade de fases sejam igual a 5", 5, listaFases.size());
	}
	
	@Test
	public void verificarPosicaoInicialDoCanhao() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(0);
		ArrayList<Jogador> listarJogadores = novoJogo.listarJogadores();
		Canhao c = instanciarObjetoCanhao();
		listarJogadores.get(0).setCanhao(c);
		novoJogo.inserirJogadorNaFase(listarJogadores.get(0), f1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		Assert.assertEquals("Espera que a posição X inicial do canhão seja 250", 250, listaFases.get(0).getJogador().getCanhao().getPosicaoX());
		Assert.assertEquals("Espera que a posição Y inicial do canhão seja 500", 500, listaFases.get(0).getJogador().getCanhao().getPosicaoY());
	}
	
	@Test
	public void verificarSeJogadorControlaPosicaoCanhao() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(0);
		ArrayList<Jogador> listarJogadores = novoJogo.listarJogadores();
		Canhao c = instanciarObjetoCanhao();
		listarJogadores.get(0).setCanhao(c);
		listarJogadores.get(0).getCanhao().setPosicaoX(50);
		listarJogadores.get(0).getCanhao().setPosicaoY(30);;
		novoJogo.inserirJogadorNaFase(listarJogadores.get(0), f1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		Assert.assertEquals("Espera que a posição X atual do canhão seja 50", 50, listaFases.get(0).getJogador().getCanhao().getPosicaoX());
		Assert.assertEquals("Espera que a posição Y atual do canhão seja 30", 30, listaFases.get(0).getJogador().getCanhao().getPosicaoY());
	}
	
	@Test
	public void verificarSeCanhaoQueAindaNaoAtirouPossuiTodosOsTiros() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Canhao c = instanciarObjetoCanhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		Assert.assertEquals("Espera que a quantidade de tiros do canhão seja igual a 10", 10, listaFases.get(0).getJogador().getCanhao().getMunicao().getQuantidadeDeBalas());
	}
	
	@Test
	public void verificarSeJogadorAtiraComCanhao() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Canhao c = instanciarObjetoCanhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		ArrayList<Fase> listaFases = novoJogo.listarFases();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		Assert.assertEquals("Espera que a quantidade de tiros decremente para 7", 7, listaFases.get(0).getJogador().getCanhao().getMunicao().getQuantidadeDeBalas());
	}
	
	@Test(expected = BalasEsgotadasException.class)
	public void verificarSeQuantidadeDeBalasEsgotaram() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Canhao c = instanciarObjetoCanhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
	}
	
	@Test
	public void verificarSeJogoAcabou() throws IOException, Exception{
		Jogador j1 = instanciarObjetoJogador();
		jogo.cadastrarJogador(j1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginJogador(j1);
		Canhao c = instanciarObjetoCanhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		listaJogadores.get(0).atirar();
		novoJogo.fimDeJogo(listaJogadores.get(0));
		Assert.assertTrue(novoJogo.jogoAcabou());
	}
	
	@Test
	public void verificarPossivelRespostaCorretaEmBaloes()throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("2x3?");
		pro1.setResposta(6);
		Jogo maisUmNovoJogo = new Jogo();
		maisUmNovoJogo.loginProfessor(p1);
		maisUmNovoJogo.cadastrarProblema(pro1);
		ArrayList<Problema> listaProblemas = maisUmNovoJogo.listarProblemas();
		System.out.println(listaProblemas.get(0).getResposta());
		maisUmNovoJogo.gerarBalao(listaProblemas.get(0));
		Assert.assertTrue(maisUmNovoJogo.verificarSeRespostaEstaEmBaloes(6));
	}
	
	
	