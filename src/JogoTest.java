import org.junit.Assert;
import org.junit.Test;


public class JogoTest {
	
	@Test
	public void criarJogo(){
		Assert.assertTrue("Menu iniciado",jogo.iniciarMenu());
	}

}
