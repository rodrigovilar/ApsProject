import org.junit.Assert;
import org.junit.Test;


public class JogoTest {
	
	@Test
	public void criarJogo(){
		Object jogo;
		Assert.assertTrue("Menu iniciado",jogo.iniciarMenu());
	}

}
