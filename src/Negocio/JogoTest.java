package Negocio;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class JogoTest {
	private Jogo jogo;
	
	@Before
	public void iniciarTest(){//Inicia a fachada Jogo
		jogo = new Jogo();
	}
	
	@Test
	public void iniciarJogo(){//Veririca que o jogo nao terminou
		Assert.assertFalse("O jogo iniciou acabado", jogo.jogoAcabou());
	}
	
	@Test
	public void verificarListaInicialDeProfessores(){
		Assert.assertEquals("Esse teste espera que a lista inicial de professores seja igual a zero", 0, jogo.getQuantidadeDeProfessoresCadastrados());
	}
	
	@Test
	public void cadastrarProfessor() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor_1");
		professor.setSenha("12344");
		jogo.cadastrarProfesssor(professor);
		ArrayList<Professor> professoresCadastrados = jogo.listarProfessores();
		Assert.assertEquals("Esse teste espera que possua apenas um jogador cadastrado", 1, jogo.getQuantidadeDeProfessoresCadastrados());
		Professor professorSalvo = professoresCadastrados.get(0);
		Assert.assertEquals("Esse teste espera que o jogador cadastrado seja igual ao jogador salvo na lista",professor, professorSalvo);
	}
	
	@Test(expected=ObjetoJaExistenteException.class)
	public void cadastrarProfessorJaCadastrado() throws ObjetoJaExistenteException, ObjetoInexistenteException{

		Professor professor_1 = new Professor();
		professor_1.setNome("professor_1");
		professor_1.setSenha("22244");
		jogo.cadastrarProfesssor(professor_1);
		
		Professor professor_2 = new Professor();
		professor_2.setNome("professor_1");
		jogo.cadastrarProfesssor(professor_2);
	}

	@Test
	public void removerProfessor() throws ObjetoInexistenteException, ObjetoJaExistenteException {
		Professor professor = new Professor();
		professor.setNome("professor_1");
		professor.setSenha("33344");
		jogo.cadastrarProfesssor(professor);
		Assert.assertEquals("Esse teste espera que possua apenas um jogador cadastrado", 1, jogo.getQuantidadeDeProfessoresCadastrados());
		jogo.removerProfessor(professor);
		Assert.assertEquals("Esse teste espera que a quantidade de jogadores seja igual a 0", 0, jogo.getQuantidadeDeProfessoresCadastrados());
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void removerMesmoProfessorDuasVezes() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor_1");
		professor.setSenha("44444");
		jogo.cadastrarProfesssor(professor);
		jogo.removerProfessor(professor);
		jogo.removerProfessor(professor);
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void removerProfessorInexistente() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor_1");
		professor.setSenha("5555");
		jogo.cadastrarProfesssor(professor);
		
		Professor professor_2 = new Professor();
		professor_2.setNome("professor_2");
		professor_2.setSenha("66666");
		
		jogo.removerProfessor(professor_2);
	}
	
	@Test
	public void verificarListaInicialDeJogadores(){//Verifica se a lista inicial de jogadores est vazia
		Assert.assertEquals("Esse teste espera que a lista inicial de jogadores seja igual a zero" ,0, jogo.getQuantidadeDeJogadoresCadastrados());
	}
	
	@Test
	public void cadastrarJogador() throws ObjetoJaExistenteException{//Cadastra e verifica se o jogador  cadastrado
		Jogador jogador = new Jogador();
		jogador.setNome("jogador_1");
		jogo.cadastrarJogador(jogador);
		ArrayList<Jogador> jogadoresCadastrados = jogo.listarJogadores();
		Assert.assertEquals("Esse teste espera que possua apenas um jogador cadastrado", 1, jogadoresCadastrados.size());
		Jogador jogadorSalvo = jogadoresCadastrados.get(0);
		Assert.assertEquals("Esse teste espera que o jogador cadastrado seja igual ao jogador salvo na lista",jogador, jogadorSalvo);
	}
	
	@Test(expected=ObjetoJaExistenteException.class)
	public void cadastrarJogadorJaCadastrado() throws ObjetoJaExistenteException{//Cadastra duas vezes o mesmo jogador e lana uma exceo
		Jogador jogador_1 = new Jogador();
		jogador_1.setNome("jogador_1");
		jogo.cadastrarJogador(jogador_1);
		
		Jogador jogador_2 = new Jogador();
		jogador_2.setNome("jogador_1");
		jogo.cadastrarJogador(jogador_2);
	}
	
	@Test
	public void removerJogadorCadastrado() throws ObjetoJaExistenteException, ObjetoInexistenteException{//Remove jogador cadastrado
		Jogador jogador_1 = new Jogador();
		jogador_1.setNome("jogador_1");
		jogo.cadastrarJogador(jogador_1);
		Assert.assertEquals("Esse teste espera que a quantidade de jogadores seja igual a 0", 1, jogo.getQuantidadeDeJogadoresCadastrados());
		jogo.removerJogador(jogador_1);
		Assert.assertEquals("Esse teste espera que a quantidade de jogadores seja igual a 0", 0, jogo.getQuantidadeDeJogadoresCadastrados());
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void removerMesmoJogadorDuasVezes() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Jogador jogador_1 = new Jogador();
		jogador_1.setNome("jogador_1");
		jogo.cadastrarJogador(jogador_1);
		jogo.removerJogador(jogador_1);
		jogo.removerJogador(jogador_1);
	}
	
	@Test(expected=ObjetoInexistenteException.class)//Tenta remover jogador inexistente e lana um execeo
	public void removerJogadorInexistente() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Jogador jogador_1 = new Jogador();
		jogador_1.setNome("jogador_1");
	
		jogo.cadastrarJogador(jogador_1);
		
		Jogador jogador_2 = new Jogador();
		jogador_2.setNome("jogador_2");
		
		
		jogo.removerJogador(jogador_2);
	}
	
	@Test
	public void cadastrarProblema() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("12345");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		
		Problema problema = new Problema();
		problema.setQuestao("questao");
		problema.setResposta(5);
		jogo.cadastrarProblema(problema);

		ArrayList<Problema> problemasCadastrados = jogo.listarProblemas();
		Assert.assertEquals("Esse teste espera que possua apenas um problema cadastrado", 1, problemasCadastrados.size());
		Problema problemaSalvo = problemasCadastrados.get(0);
		Assert.assertEquals("Esse teste espera que o problema cadastrado seja igual ao problema salvo na lista",problema, problemaSalvo);
	}
	
	@Test(expected=ObjetoInexistenteException.class)
	public void verificarCadastroDeProblemaComLoginInvalido() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("123");
		jogo.cadastrarProfesssor(professor);
		
		jogo.loginProfessor(professor);
		
		Problema problema = new Problema();
		problema.setQuestao("questao");
		problema.setResposta(5);
		jogo.cadastrarProblema(problema);
	}
	
	@Test(expected=ObjetoJaExistenteException.class)//Tenta cadastrar problema j cadastrado, da  lanado a exceo
	public void cadastrarProblemaJaCadastrado() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("12345");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		
		Problema problema_1 = new Problema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(4);
		jogo.cadastrarProblema(problema_1);
		
		Problema problema_2 = new Problema();
		problema_2.setQuestao("problema_1");
		jogo.cadastrarProblema(problema_2);
	}
	
	@Test
	public void removerProblemaCadastrado() throws ObjetoJaExistenteException, ObjetoInexistenteException{//Remove jogador cadastrado
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("12345");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		
		Problema problema_1 = new Problema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(4);
		jogo.cadastrarProblema(problema_1);
		Assert.assertEquals("Esse teste espera que a quantidade de problemas seja igual a 1", 1, jogo.getQuantidadeDeProblemasCadastrados());
		jogo.removerProblema(problema_1);
		Assert.assertEquals("Esse teste espera que a quantidade de jogadores seja igual a 0", 0, jogo.getQuantidadeDeProblemasCadastrados());
	}
	
	@Test(expected=ObjetoInexistenteException.class)//Tenta remover o mesmo problema duas vezes, com isso lanando duas vezes a exceco
	public void removerMesmoProblemaDuasVezes() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("12345");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		
		Problema problema_1 = new Problema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(8);
		jogo.cadastrarProblema(problema_1);
		jogo.removerProblema(problema_1);
		jogo.removerProblema(problema_1);
	}
	
	@Test(expected=ObjetoInexistenteException.class)//Tenta remover jogador inexistente e lana um execeo
	public void removerProblemaInexistente() throws ObjetoJaExistenteException, ObjetoInexistenteException{
		Professor professor = new Professor();
		professor.setNome("professor");
		professor.setSenha("12345");
		jogo.cadastrarProfesssor(professor);
		jogo.loginProfessor(professor);
		
		Problema problema_1 = new Problema();
		problema_1.setQuestao("problema_1");
		problema_1.setResposta(9);
		jogo.cadastrarProblema(problema_1);
		
		Problema problema_2 = new Problema();
		problema_2.setQuestao("problema_2");
		problema_2.setResposta(115);		
		jogo.removerProblema(problema_2);
	}
	
	@Test
	public void verificarSePrimeiraFaseEstaDisponivel(){
		jogo.gerarTodasAsFases();
		Assert.assertEquals(true, jogo.listarFases().get(0).isLiberado());
	}
	
	@Test
	public void verificarFasesBloqueadas(){
		jogo.gerarTodasAsFases();
		Assert.assertEquals(false, jogo.listarFases().get(1).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(2).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(3).isLiberado());
		Assert.assertEquals(false, jogo.listarFases().get(4).isLiberado());
	}
	
}
