package Model;

public class Canhao {
	
	
	
	public int getPosicaoX() {//MŽtodo que recupera atributo privado posicaoX
		return posicaoX;
	}
	
	public void setPosicaoX(int posicaoX) {//MŽtodo que muda o valor do atributo posicaoX
		this.posicaoX = posicaoX;
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
