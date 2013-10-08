package Negocio;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

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

public class JogoTest {
	private Jogo jogo;

	@Before
	public void iniciarTest() throws JogadorNaoLogadoException {
		jogo = new Jogo();
	}

	@Test
	public void iniciarJogo() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException {
		Assert.assertFalse("O jogo iniciou acabado", jogo.jogoAcabou());
	}

	@Test
	public void verificarListaInicialDeProfessores() {
		Assert.assertEquals(
				"Esse teste espera que a lista inicial de professores seja igual a zero",
				0, jogo.getQuantidadeDeProfessoresCadastrados());
	}

	@Test
	public void cadastrarProfessor() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, IOException {
		Professor professor = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		ArrayList<Professor> professoresCadastrados = jogo.listarProfessores();
		Assert.assertEquals(
				"Esse teste espera que possua apenas um professor cadastrado",
				1, jogo.getQuantidadeDeProfessoresCadastrados());
		Professor professorSalvo = professoresCadastrados.get(0);
		Assert.assertEquals(
				"Esse teste espera que o professor cadastrado seja igual ao jogador salvo na lista",
				professor, professorSalvo);
	}

	@Test(expected = ObjetoJaExistenteException.class)
	public void cadastrarProfessorJaCadastrado()
			throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		Professor professor_1 = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor_1);

		Professor professor_2 = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor_2);
	}

	@Test
	public void removerProfessor() throws ObjetoInexistenteException,
			ObjetoJaExistenteException, IOException {
		Professor professor = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		Assert.assertEquals(
				"Esse teste espera que possua apenas um jogador cadastrado", 1,
				jogo.getQuantidadeDeProfessoresCadastrados());
		jogo.removerProfessor(professor);
		Assert.assertEquals(
				"Esse teste espera que a quantidade de jogadores seja igual a 0",
				0, jogo.getQuantidadeDeProfessoresCadastrados());
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerMesmoProfessorDuasVezes()
			throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		Professor professor = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.removerProfessor(professor);
		jogo.removerProfessor(professor);
	}

	@Test
	public void verificaSeNomeDoProfessorEstaCorreto()
			throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		professor.setNome("Madalena");
		jogo.cadastrarProfesssor(professor);
		ArrayList<Professor> professores = jogo.listarProfessores();
		Professor professorSalvo = professores.get(0);
		Assert.assertEquals(
				"Esse teste espera que o professor cadastrado seja igual ao professor salvo na lista",
				professor.getNome(), professorSalvo.getNome());
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerProfessorInexistente()
			throws ObjetoJaExistenteException, ObjetoInexistenteException {
		Professor professor_2 = this.instanciarProfessor();
		jogo.removerProfessor(professor_2);
	}

	@Test
	public void verificarListaInicialDeJogadores() {
		Assert.assertEquals(
				"Esse teste espera que a lista inicial de jogadores seja igual a zero",
				0, jogo.getQuantidadeDeJogadoresCadastrados());
	}

	@Test
	public void cadastrarJogador() throws ObjetoJaExistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		ArrayList<Jogador> jogadoresCadastrados = jogo.listarJogadores();
		Assert.assertEquals(
				"Esse teste espera que possua apenas um jogador cadastrado", 1,
				jogadoresCadastrados.size());
		Jogador jogadorSalvo = jogadoresCadastrados.get(0);
		Assert.assertEquals(
				"Esse teste espera que o jogador cadastrado seja igual ao jogador salvo na lista",
				jogador.getNome(), jogadorSalvo.getNome());
	}

	@Test
	public void verificaSeNomeDoJogadorEstaCorreto()
			throws ObjetoJaExistenteException {
		Jogador jogador = instanciarJogador();
		jogador.setNome("jogador_1");
		jogo.cadastrarJogador(jogador);
		ArrayList<Jogador> jogadoresCadastrados = jogo.listarJogadores();
		Jogador jogadorSalvo = jogadoresCadastrados.get(0);
		Assert.assertEquals(
				"Esse teste espera que o jogador cadastrado seja igual ao jogador salvo na lista",
				jogador.getNome(), jogadorSalvo.getNome());
	}

	@Test(expected = ObjetoJaExistenteException.class)
	public void cadastrarJogadorJaCadastrado()
			throws ObjetoJaExistenteException, JogadorNaoLogadoException,
			LoginInexistenteException {// Cadastra duas vezes o mesmo jogador e
										// lanÂ�a uma exceÂ�Â‹o
		Jogador jogador_1 = instanciarJogador();
		jogo.cadastrarJogador(jogador_1);
		jogo.loginJogador(jogador_1);

		Jogador jogador_2 = instanciarJogador();
		jogo.cadastrarJogador(jogador_2);
	}

	@Test
	public void removerJogadorCadastrado() throws ObjetoJaExistenteException,
			ObjetoInexistenteException {// Remove jogador cadastrado
		Jogador jogador_1 = this.instanciarJogador();
		jogo.cadastrarJogador(jogador_1);
		Assert.assertEquals(
				"Esse teste espera que a quantidade de jogadores seja igual a 0",
				1, jogo.getQuantidadeDeJogadoresCadastrados());
		jogo.removerJogador(jogador_1);
		Assert.assertEquals(
				"Esse teste espera que a quantidade de jogadores seja igual a 0",
				0, jogo.getQuantidadeDeJogadoresCadastrados());
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerMesmoJogadorDuasVezes()
			throws ObjetoJaExistenteException, ObjetoInexistenteException {
		Jogador jogador_1 = this.instanciarJogador();
		jogo.cadastrarJogador(jogador_1);
		jogo.removerJogador(jogador_1);
		jogo.removerJogador(jogador_1);
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerJogadorInexistente() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, JogadorNaoLogadoException,
			LoginInexistenteException {
		Jogador jogador_1 = instanciarJogador();
		jogo.loginJogador(jogador_1);

		Jogador jogador_2 = new Jogador();
		jogador_2.setNome("jogador_2");

		jogo.removerJogador(jogador_2);
	}

	@Test
	public void cadastrarProblema() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema = this.instanciarProblema();
		jogo.cadastrarProblema(problema);

		ArrayList<Problema> problemasCadastrados = jogo.listarProblemas();
		Assert.assertEquals(
				"Esse teste espera que possua apenas um problema cadastrado",
				1, problemasCadastrados.size());
		Problema problemaSalvo = problemasCadastrados.get(0);
		Assert.assertEquals(
				"Esse teste espera que o problema cadastrado seja igual ao problema salvo na lista",
				problema, problemaSalvo);
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void senhaComApenasUmDigitoImpossibilidadeDeAtribuicao()
			throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		Professor professor = this.instanciarProfessor();
		professor.setSenha("1");
		jogo.cadastrarProfesssor(professor);

	}

	@Test(expected = ObjetoJaExistenteException.class)
	public void cadastrarProblemaJaCadastrado()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_2);
	}

	@Test
	public void removerProblemaCadastrado() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);
		Assert.assertEquals(
				"Esse teste espera que a quantidade de problemas seja igual a 1",
				1, jogo.getQuantidadeDeProblemasCadastrados());
		jogo.removerProblema(problema_1);
		Assert.assertEquals(
				"Esse teste espera que a quantidade de jogadores seja igual a 0",
				0, jogo.getQuantidadeDeProblemasCadastrados());
	}

	@Test
	public void verificarListaInicialDeProblemas() {
		Assert.assertEquals(0, jogo.listarProblemas().size());
	}

	@Test
	public void verificarNomeDoProblemaCadastrado()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema = instanciarProblema();
		problema.setQuestao("Quanto eh a soma de (3!)?");
		jogo.cadastrarProblema(problema);
		ArrayList<Problema> listaProblemas = jogo.listarProblemas();
		Assert.assertEquals("Quanto eh a soma de (3!)?", listaProblemas.get(0)
				.getQuestao());
	}

	@Test
	public void verificarRespostaDoProblemaCadastrado()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema = instanciarProblema();
		problema.setQuestao("Sendo x = 5, quanto vale x+10?");
		problema.setResposta(15);
		jogo.cadastrarProblema(problema);
		ArrayList<Problema> listaProblemas = jogo.listarProblemas();
		Assert.assertEquals(15, listaProblemas.get(0).getResposta());
	}

	@Test(expected = ObjetoInexistenteException.class)
	public void removerMesmoProblemaDuasVezes()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);
		jogo.removerProblema(problema_1);
		jogo.removerProblema(problema_1);
	}

	@SuppressWarnings("static-access")
	@Test
	public void verificarScoreInicial() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(10, jogo.listarJogadores().get(0).getScore());
	}

	@Test
	public void verificarIdDoJogadorCadastrado()
			throws ObjetoJaExistenteException {
		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);

		ArrayList<Jogador> jogadores = jogo.listarJogadores();
		Assert.assertEquals(1, jogadores.get(0).getId());
	}

	@Test
	public void verificarIdDoProfessorCadastrado()
			throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException {
		Professor professor = this.instanciarProfessor();
		jogo.cadastrarProfesssor(professor);

		ArrayList<Professor> professores = jogo.listarProfessores();
		Assert.assertEquals(1, professores.get(0).getId());

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

		Assert.assertEquals(250, fases.get(0).getJogador().getCanhao().getPosicaoX());
		Assert.assertEquals(500, fases.get(0).getJogador().getCanhao().getPosicaoY());
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

	@Test(expected = ObjetoInexistenteException.class)
	public void verificarQuantidadeInicialDeBaloesGerados()
			throws ObjetoJaExistenteException, ObjetoInexistenteException {
		jogo.verificarQuantidadeDeBaloesGerados();
	}

	@Test
	public void verificarQuantidadeDeBaloesGerados()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);
		Assert.assertEquals(10, jogo.verificarQuantidadeDeBaloesGerados());
	}

	@Test
	public void verificarSeBalaoEstoura() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, FaseNaoDisponivelException,
			JogadorNaoLogadoException, LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);

		Jogador jogador = this.instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Canhao canhao = new Canhao();
		Municao municao = new Municao();
		canhao.setMunicao(municao);
		jogador.setCanhao(canhao);

		Fase fase = instanciarFase();
		fase.setNivel(0);
		jogo.inserirJogadorNaFase(jogador, fase);

		jogo.estourarBalao(15);
		Assert.assertFalse(jogo.verificarSeRespostaEstaEmBaloes(15));
	}

	
	@SuppressWarnings("static-access")
	@Test
	public void verificarSeJogadorPerdeScoreAoErrarQuestao()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);

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

		jogo.estourarBalao(52);
		Assert.assertEquals(9, jogador.getScore());
	}
	@SuppressWarnings("static-access")
	@Test
	public void verificarSeJogadorMarcaScoreAoAcertarQuestao()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		jogo.gerarBalao(problema_1);

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

		jogo.estourarBalao(15);
		Assert.assertEquals(11, jogador.getScore());
	}

	@Test
	public void verificarSeJogadorPassaDeFaseAoFazer15Pontos()
			throws ObjetoJaExistenteException, ObjetoInexistenteException,
			FaseNaoDisponivelException, JogadorNaoLogadoException,
			LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(1);
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 = this.instanciarProblema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(2);
		jogo.cadastrarProblema(problema_2);

		Problema problema_3 = this.instanciarProblema();
		problema_3.setQuestao("problema_3");
		problema_3.setResposta(3);
		jogo.cadastrarProblema(problema_3);

		Problema problema_4 = this.instanciarProblema();
		problema_4.setQuestao("problema_4");
		problema_4.setResposta(4);
		jogo.cadastrarProblema(problema_4);

		Problema problema_5 = this.instanciarProblema();
		problema_5.setQuestao("problema_5");
		problema_5.setResposta(5);
		jogo.cadastrarProblema(problema_5);

		Jogador jogador = this.instanciarJogador();

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
	public void removerProblemaInexistente() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 = this.instanciarProblema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(115);
		jogo.removerProblema(problema_2);
	}

	@Test
	public void verificarSePrimeiraFaseEstaDisponivel()
			throws ObjetoJaExistenteException, JogadorNaoLogadoException,
			LoginInexistenteException {
		Jogador jogador = instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(true, jogo.listarFases().get(0).isLiberado());
	}

	@Test
	public void verificarQuantidadeDeFases() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);
		Assert.assertEquals(5, jogo.listarFases().size());
	}

	@Test
	public void verificarFasesBloqueadas() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = instanciarJogador();
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
	public void professorNaoLogado() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, LoginInexistenteException {
		Jogador jogador = instanciarJogador();
		jogo.cadastrarJogador(jogador);
		Problema problema = instanciarProblema();
		jogo.cadastrarProblema(problema);
	}

	@Test(expected = LoginInexistenteException.class)
	public void loginDuploDeProfessor() throws ObjetoJaExistenteException,
			ObjetoInexistenteException, LoginInexistenteException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Professor professor_2 = instanciarProfessor();
		professor_2.setNome("jonas");
		jogo.cadastrarProfesssor(professor_2);
		jogo.loginProfessor(professor_2);
	}

	@Test(expected = LoginInexistenteException.class)
	public void loginDuploJogador() throws ObjetoJaExistenteException,
			JogadorNaoLogadoException, LoginInexistenteException {
		Jogador jogador = instanciarJogador();
		jogo.cadastrarJogador(jogador);
		jogo.loginJogador(jogador);

		Jogador jogador_2 = instanciarJogador();
		jogador_2.setNome("Ligeirinho");
		jogo.cadastrarJogador(jogador_2);
		jogo.loginJogador(jogador_2);

	}

	@Test
	public void verificarSeJogoFoiZerado() throws ObjetoInexistenteException,FaseNaoDisponivelException, LoginInexistenteException,	ObjetoJaExistenteException, JogadorNaoLogadoException, IOException {
		Professor professor = instanciarProfessor();
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);

		Problema problema_1 = this.instanciarProblema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(1);
		jogo.cadastrarProblema(problema_1);

		Problema problema_2 = this.instanciarProblema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(2);
		jogo.cadastrarProblema(problema_2);

		Problema problema_3 = this.instanciarProblema();
		problema_3.setQuestao("problema_3");
		problema_3.setResposta(3);
		jogo.cadastrarProblema(problema_3);

		Problema problema_4 = this.instanciarProblema();
		problema_4.setQuestao("problema_4");
		problema_4.setResposta(4);
		jogo.cadastrarProblema(problema_4);

		Problema problema_5 = this.instanciarProblema();
		problema_5.setQuestao("problema_5");
		problema_5.setResposta(5);
		jogo.cadastrarProblema(problema_5);
		
		Problema problema_6 = this.instanciarProblema();
		problema_6.setQuestao("problema_6");
		problema_6.setResposta(6);
		jogo.cadastrarProblema(problema_6);

		Problema problema_7 = this.instanciarProblema();
		problema_7.setQuestao("problema_7");
		problema_7.setResposta(7);
		jogo.cadastrarProblema(problema_7);

		Problema problema_8= this.instanciarProblema();
		problema_8.setQuestao("problema_8");
		problema_8.setResposta(8);
		jogo.cadastrarProblema(problema_8);

		Problema problema_9 = this.instanciarProblema();
		problema_9.setQuestao("problema_9");
		problema_9.setResposta(9);
		jogo.cadastrarProblema(problema_9);

		Problema problema_10 = this.instanciarProblema();
		problema_10.setQuestao("problema_10");
		problema_10.setResposta(10);
		jogo.cadastrarProblema(problema_10);
		
		Problema problema_11 = this.instanciarProblema();
		problema_11.setQuestao("problema_11");
		problema_11.setResposta(11);
		jogo.cadastrarProblema(problema_11);

		Problema problema_12 = this.instanciarProblema();
		problema_12.setQuestao("problema_12");
		problema_12.setResposta(12);
		jogo.cadastrarProblema(problema_12);

		Problema problema_13= this.instanciarProblema();
		problema_13.setQuestao("problema_13");
		problema_13.setResposta(13);
		jogo.cadastrarProblema(problema_13);

		Problema problema_14 = this.instanciarProblema();
		problema_14.setQuestao("problema_14");
		problema_14.setResposta(14);
		jogo.cadastrarProblema(problema_14);

		Problema problema_15 = this.instanciarProblema();
		problema_15.setQuestao("problema_15");
		problema_15.setResposta(15);
		jogo.cadastrarProblema(problema_15);
		
		Problema problema_16 = this.instanciarProblema();
		problema_16.setQuestao("problema_16");
		problema_16.setResposta(16);
		jogo.cadastrarProblema(problema_16);

		Problema problema_17 = this.instanciarProblema();
		problema_17.setQuestao("problema_17");
		problema_17.setResposta(17);
		jogo.cadastrarProblema(problema_17);

		Problema problema_18= this.instanciarProblema();
		problema_18.setQuestao("problema_18");
		problema_18.setResposta(18);
		jogo.cadastrarProblema(problema_18);

		Problema problema_19 = this.instanciarProblema();
		problema_19.setQuestao("problema_19");
		problema_19.setResposta(19);
		jogo.cadastrarProblema(problema_19);

		Problema problema_20 = this.instanciarProblema();
		problema_20.setQuestao("problema_20");
		problema_20.setResposta(20);
		jogo.cadastrarProblema(problema_20);
		
		Jogador jogador = this.instanciarJogador();

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
		
		jogo.gerarBalao(problema_10);
		jogo.estourarBalao(10);
		jogo.gerarBalao(problema_9);
		jogo.estourarBalao(9);
		jogo.gerarBalao(problema_8);
		jogo.estourarBalao(8);
		jogo.gerarBalao(problema_7);
		jogo.estourarBalao(7);
		jogo.gerarBalao(problema_6);
		jogo.estourarBalao(6);
		
		jogo.gerarBalao(problema_15);
		jogo.estourarBalao(15);
		jogo.gerarBalao(problema_14);
		jogo.estourarBalao(14);
		jogo.gerarBalao(problema_13);
		jogo.estourarBalao(13);
		jogo.gerarBalao(problema_12);
		jogo.estourarBalao(12);
		jogo.gerarBalao(problema_11);
		jogo.estourarBalao(11);
		
		jogo.gerarBalao(problema_20);
		jogo.estourarBalao(20);
		jogo.gerarBalao(problema_19);
		jogo.estourarBalao(19);
		jogo.gerarBalao(problema_18);
		jogo.estourarBalao(18);
		jogo.gerarBalao(problema_17);
		jogo.estourarBalao(17);
		jogo.gerarBalao(problema_16);
		jogo.estourarBalao(16);
		Assert.assertTrue(jogo.listarFases().get(4).isLiberado());
	}
	
	
	
	@Test
	public void cadastrarProfessorNoArquivo() throws ObjetoJaExistenteException, ObjetoInexistenteException, IOException, ClassNotFoundException{
		Professor professor = instanciarProfessor();
		professor.setNome("Jonnathann");
		jogo.cadastrarProfesssor(professor);
	}

	private Jogador instanciarJogador() {
		Jogador jogador = new Jogador();
		jogador.setNome("jonas");
		return jogador;
	}

	private Problema instanciarProblema() {
		Problema problema = new Problema();
		problema.setQuestao("questao 1");
		problema.setResposta(15);
		return problema;

	}

	private Fase instanciarFase() {
		Fase fase = new Fase();
		return fase;
	}

	private Professor instanciarProfessor() {
		Professor professor = new Professor();
		professor.setNome("professor_1");
		professor.setSenha("12344");
		return professor;
	}

}
