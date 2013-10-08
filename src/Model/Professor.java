package Model;

import java.io.Serializable;

//@SuppressWarnings("serial")
public class Professor extends Usuario  implements Serializable { //
	
	public Professor(){
		int id = super.getId();
		id++;
		super.setId(id);
		super.setTipo("professor");
	}

}
