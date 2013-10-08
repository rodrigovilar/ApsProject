package Model;

import java.io.Serializable;

//@SuppressWarnings("serial")
public class Professor extends Usuario  implements Serializable { //
	/*private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	private int ID;*/
	public Professor(){
		int id = super.getId();
		id++;
		super.setId(id);
		super.setTipo("professor");
	}

}
