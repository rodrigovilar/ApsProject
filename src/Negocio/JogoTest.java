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

}
