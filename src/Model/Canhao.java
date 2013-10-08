package Model;

public class Canhao {
	
	//Atributos 
	private Municao municao; //Referencia do tipo Tiro(referente a classe Tiro)
	private int posicaoX = 250;//Posi�‹o X do canhao em rela�‹o a tela
	private int posicaoY = 500;//Posi�‹o Y do canhao em rela�‹o a tela
	
	public int getPosicaoX() {//MŽtodo que recupera atributo privado posicaoX
		return posicaoX;
	}
	
	
	public int getPosicaoY() {//MŽtodo que recupera atributo privado posicaoY
		return posicaoY;
	}
	
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public Municao getMunicao() {
		return municao;
	}
	
	public void setMunicao(Municao tiro) {
		this.municao = tiro;
	}

}
