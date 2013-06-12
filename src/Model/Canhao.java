package Model;

public class Canhao {
	
	//Atributos 
	private Tiro tiro; //Referencia do tipo Tiro(referente a classe Tiro)
	private int posicaoX = 250;//Posição X do canhao em relação a tela
	private int posicaoY = 500;//Posição Y do canhao em relação a tela
	
	public int getPosicaoX() {//Método que recupera atributo privado posicaoX
		return posicaoX;
	}
	
	public void setPosicaoX(int posicaoX) {//Método que muda o valor do atributo posicaoX
		this.posicaoX = posicaoX;
	}
	
	public int getPosicaoY() {//Método que recupera atributo privado posicaoY
		return posicaoY;
	}
	
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public Tiro getTiro() {
		return tiro;
	}
	
	public void setTiro(Tiro tiro) {
		this.tiro = tiro;
	}

}
