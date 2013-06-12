package Model;

public class Professor {
	
	private String nome;
	private int id = 0;
	private String senha;
	
	public Professor(){
		id++;
	}
	
	public String getNome() {
		return nome;
	}

}
