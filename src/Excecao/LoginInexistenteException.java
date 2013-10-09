package Excecao;

@SuppressWarnings("serial")
public class LoginInexistenteException extends Exception {
	
	public LoginInexistenteException(String msg){
		super(msg);
	}

}
