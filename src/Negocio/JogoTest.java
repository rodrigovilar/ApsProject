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
	public void inserirJogadorNaFase() throws ObjetoJaExistenteException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		ArrayList<Jogador> jogadores = jogo.listarJogadores();

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogadores.get(0), fase);
		ArrayList<Fase> fases = jogo.listarFases();
		Assert.assertEquals(jogador, fases.get(0).getJogador());
	}
	
	@Test(expected = FaseNaoDisponivelException.class)
	public void FaseIndisponivel() throws ObjetoJaExistenteException,
			FaseNaoDisponivelException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		ArrayList<Jogador> jogadores = jogo.listarJogadores();

		Fase fase = instanciarFase();
		fase.setNivel(1);
		jogo.inserirJogadorNaFase(jogadores.get(0), fase);
	}
	
	@Test
	public void verificarNumerosDeFasesGeradas()
			throws ObjetoJaExistenteException, FaseNaoDisponivelException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		ArrayList<Fase> fases = jogo.listarFases();
		Assert.assertEquals(5, fases.size());
	}
	
	@Test
	public void verificarPosicaoInicialDoCanhao()
			throws ObjetoJaExistenteException, FaseNaoDisponivelException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();

		Canhao canhao = new Canhao();
		jogador.setCanhao(canhao);
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogador, fase);
		ArrayList<Fase> fases = jogo.listarFases();

		Assert.assertEquals(250, fases.get(0).getJogador().getCanhao()
				.getPosicaoX());
		Assert.assertEquals(500, fases.get(0).getJogador().getCanhao()
				.getPosicaoY());
	}
	
	@Test
	public void verificarSeJogadorControlaPosicaoCanhao()
			throws ObjetoJaExistenteException, FaseNaoDisponivelException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();

		Canhao canhao = new Canhao();
		jogador.setCanhao(canhao);
		jogador.getCanhao().setPosicaoX(100);
		jogador.getCanhao().setPosicaoY(50);

		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogador, fase);
		ArrayList<Fase> fases = jogo.listarFases();

		Assert.assertEquals(100, fases.get(0).getJogador().getCanhao()
				.getPosicaoX());
		Assert.assertEquals(50, fases.get(0).getJogador().getCanhao()
				.getPosicaoY());
	}
	
	@Test
	public void verificarSeCanhaoDoJogadorQueNaoAtirouPossuiTodosOsTiros()
			throws ObjetoJaExistenteException, FaseNaoDisponivelException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);

		ArrayList<Jogador> jogadores = jogo.listarJogadores();

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogadores.get(0), fase);
		ArrayList<Fase> fases = jogo.listarFases();

		Assert.assertEquals(10, fases.get(0).getJogador().getCanhao()
				.getMunicao().getQuantidadeDeBalas());
	}
	
	@Test
	public void verificarSeJogadorAtiraComCanhao()
			throws ObjetoJaExistenteException, BalasEsgotadasException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogador, fase);
		ArrayList<Fase> fases = jogo.listarFases();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		Assert.assertEquals(7, fases.get(0).getJogador().getCanhao()
				.getMunicao().getQuantidadeDeBalas());
	}
	
	@Test
	public void verificarQuantidadeInicialDeMunicao()
			throws ObjetoJaExistenteException, JogadorNaoLogadoException,
			LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);

		ArrayList<Jogador> listaJogadores = jogo.listarJogadores();

		Assert.assertEquals(10, listaJogadores.get(0).getCanhao().getMunicao()
				.getQuantidadeDeBalas());
	}

	
	@Test(expected = BalasEsgotadasException.class)
	public void verificarSeQuantidadeDeMunicaoEsgostaram()
			throws ObjetoJaExistenteException, BalasEsgotadasException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException {// verifica se balas esgotaram
		Jogador jogador = this.instanciarJogador();

		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
	}
	
	@Test
	public void verificarSeJogoAcabou() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException, LoginInexistenteException,
			BalasEsgotadasException {
		Jogador jogador = this.instanciarJogador();
		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();
		jogador.atirar();

		jogo.fimDeJogo(jogador);
		Assert.assertTrue(jogo.jogoAcabou());

	}

	@Test
	public void verificarPossivelRespostaCorretaEmBaloes()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);
		Assert.assertEquals(true, jogo.verificarSeRespostaEstaEmBaloes(15));
	}


}
