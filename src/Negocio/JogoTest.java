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
}
