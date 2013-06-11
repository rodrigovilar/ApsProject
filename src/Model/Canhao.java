package Model;

public class Canhao {
	
	//Atributos 
	private Tiro tiro; //Referencia do tipo Tiro(referente a classe Tiro)
	private int posicaoX = 250;//Posição X do canhao em relação a tela
	private int posicaoY = 500;//Posição Y do canhao em relação a tela
	
	public int getPosicaoX() {//Método que recupera atributo privado posicaoX
		return posicaoX;
	}

}
