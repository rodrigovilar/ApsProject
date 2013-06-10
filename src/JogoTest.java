import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;


public class JogoTest {
	
	private Jogo jogo;
	@Before
	public void iniciandoTest(){
		jogo = new Jogo();
	}
	
	@Test
	public void criarJogo(){
		Assert.assertTrue("Menu iniciado",jogo.iniciarMenu());
	}

}
