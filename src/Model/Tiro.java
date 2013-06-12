package Model;

import Excecao.BalasEsgotadasException;

public class Tiro {
	private int quantidadeDeBalas = 10;

	public int getQuantidadeDeBalas() {
		return quantidadeDeBalas;
	}
	
	public void setQuantidadeDeBalas(int quantidadeDeBalas) {
		this.quantidadeDeBalas = quantidadeDeBalas;
	}
	
	public void diminuirQuantidadeDeBalas() throws BalasEsgotadasException{
		if(quantidadeDeBalas < 1){
			throw new BalasEsgotadasException("Balas Esgotadas");
		}
		else{
			this.quantidadeDeBalas-- ;
		}
	}
	
}
