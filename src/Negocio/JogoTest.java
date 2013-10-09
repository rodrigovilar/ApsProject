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
	
	@Test(expected = ObjetoInexistenteException.class)
	public void verificarQuantidadeInicialDeBaloesGerados()throws ObjetoJaExistenteException, ObjetoInexistenteException {
		jogo.verificarQuantidadeDeBaloesGerados();
	}
	
	@Test
	public void verificarQuantidadeDeBaloesGerados()throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("2+4?");
		pro1.setResposta(6);
		novoJogo.cadastrarProblema(pro1);
		ArrayList<Problema> listaProblemas = novoJogo.listarProblemas();
		Jogo maisUmJogo = new Jogo();
		maisUmJogo.gerarBalao(listaProblemas.get(0));
		Assert.assertEquals("Espera que a quantidade de balões gerados seja igual a 10", 10, maisUmJogo.verificarQuantidadeDeBaloesGerados());
	}
	
	@Test
	public void verificarSeBalaoEstoura() throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("4x4?");
		pro1.setResposta(16);
		novoJogo.cadastrarProblema(pro1);
		ArrayList<Problema> listaProblemas = novoJogo.listarProblemas();
		novoJogo.gerarBalao(listaProblemas.get(0));
		Jogador j1 = instanciarObjetoJogador();
		j1.setNome("Jonas");
		j1.setSenha("81011032");
		novoJogo.cadastrarJogador(j1);
		novoJogo.loginJogador(j1);
		Canhao c = new Canhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(0);
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		novoJogo.estourarBalao(16);
		Assert.assertFalse(novoJogo.verificarSeRespostaEstaEmBaloes(16));
	}
	
	@Test
	public void verificarSeJogadorMarcaScoreAoAcertarQuestao()throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(p1);
		Jogo novoJogo = new Jogo();
		novoJogo.loginProfessor(p1);
		Problema pro1 = instanciarObjetoProblema();
		pro1.setQuestao("2+5?");
		pro1.setResposta(7);
		novoJogo.cadastrarProblema(pro1);
		ArrayList<Problema> listaProblemas = novoJogo.listarProblemas();
		novoJogo.gerarBalao(listaProblemas.get(0));
		Jogador j1 = instanciarObjetoJogador();
		j1.setNome("kvasak");
		j1.setSenha("812383");
		novoJogo.cadastrarJogador(j1);
		novoJogo.loginJogador(j1);
		Canhao c = new Canhao();
		Municao m = new Municao();
		c.setMunicao(m);
		ArrayList<Jogador> listaJogadores = novoJogo.listarJogadores();
		listaJogadores.get(0).setCanhao(c);
		Fase f1 = instanciarObjetoFase();
		f1.setNivel(0);
		novoJogo.inserirJogadorNaFase(listaJogadores.get(0), f1);
		novoJogo.estourarBalao(7);
		Assert.assertEquals(11, Jogador.getScore());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void verificarSeJogadorPerdeScoreAoErrarQuestao()
			throws Exception {
		Professor professor = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = instanciarObjetoProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);

		Jogador jogador = instanciarObjetoJogador();

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);

		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Fase fase = instanciarObjetoFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogador, fase);

		jogo.estourarBalao(52);
		Assert.assertEquals(9, jogador.getScore());
	}

	@Test
	public void verificarSeJogadorPassaDeFaseAoFazer15Pontos()throws Exception {
		Professor p1 = instanciarObjetoProfessor();
		p1.setNome("rodrigo");
		jogo.cadastrarProfesssor(p1);
		jogo.loginProfessor(p1);

		Problema problema_1 = this.instanciarObjetoProblema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(1);
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 =instanciarObjetoProblema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(2);
		jogo.cadastrarProblema(problema_2);

		Problema problema_3 =instanciarObjetoProblema();
		problema_3.setQuestao("problema_3");
		problema_3.setResposta(3);
		jogo.cadastrarProblema(problema_3);

		Problema problema_4 = instanciarObjetoProblema();
		problema_4.setQuestao("problema_4");
		problema_4.setResposta(4);
		jogo.cadastrarProblema(problema_4);

		Problema problema_5 = instanciarObjetoProblema();
		problema_5.setQuestao("problema_5");
		problema_5.setResposta(5);
		jogo.cadastrarProblema(problema_5);

		Jogador jogador = instanciarObjetoJogador();
		jogador.setNome("toinho");

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);

		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		jogo.gerarBalao(problema_5);
		jogo.estourarBalao(5);
		jogo.gerarBalao(problema_4);
		jogo.estourarBalao(4);
		jogo.gerarBalao(problema_3);
		jogo.estourarBalao(3);
		jogo.gerarBalao(problema_2);
		jogo.estourarBalao(2);
		jogo.gerarBalao(problema_1);
		jogo.estourarBalao(1);
		Assert.assertTrue(jogo.listarFases().get(0).isLiberado());
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerProblemaInexistente() throws Exception {
		Professor professor = instanciarObjetoProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarObjetoProblema();
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 = this.instanciarObjetoProblema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(115);
		jogo.removerProblema(problema_2);
	}
	
	@Test
	public void verificarSePrimeiraFaseEstaDisponivel()throws IOException, Exception {
		Jogador jogador = instanciarObjetoJogador();
		jogador.setNome("carlinhos");
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(true, jogo.listarFases().get(0).isLiberado());
	}
	
	@Test
	public void verificarQuantidadeDeFases() throws IOException, Exception {
		Jogador jogador = instanciarObjetoJogador();
		jogador.setNome("luiz");
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(5, jogo.listarFases().size());
	}
	
	@Test
	public void verificarFasesBloqueadas() throws IOException, Exception {
		Jogador jogador = instanciarObjetoJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(false, jogo.listarFases().get(1).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(2).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(3).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(4).isLiberado());
	}
	
	@Test(expected = FaseNaoDisponivelException.class)
	public void faseInexisteException() throws FaseNaoDisponivelException {
		jogo.verificarExistenciaDeFase(6);
	}
	
	@Test(expected = LoginInexistenteException.class)
	public void professorNaoLogado() throws IOException, Exception {
		Jogador jogador = instanciarObjetoJogador();
		jogo.cadastrarJogador(jogador);
		Problema problema = instanciarObjetoProblema();
		jogo.cadastrarProblema(problema);
	}
	
	@Test(expected = LoginInexistenteException.class)
	public void loginDuploDeProfessor() throws Exception {
		Professor professor = instanciarObjetoProfessor();
		professor.setNome("rafael");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Professor professor_2 = instanciarObjetoProfessor();
		professor_2.setNome("jonas");
		jogo.cadastrarProfesssor(professor_2);
		jogo.loginProfessor(professor_2);
	}
	
	@Test(expected = LoginInexistenteException.class)
	public void loginDuploJogador() throws IOException, Exception {
		Jogador jogador = instanciarObjetoJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Jogador jogador_2 = instanciarObjetoJogador();
		jogador_2.setNome("Ligeirinho");
		jogo.cadastrarJogador(jogador_2);
		jogo.loginJogador(jogador_2);

	}

	
	