package Model;


public class Professor extends Usuario{ 
	
	public Professor(){
		int id = super.getId();
		id++;
		super.setId(id);
		super.setTipo("professor");
	}

}
