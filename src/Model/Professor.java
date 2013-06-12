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
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}

}
