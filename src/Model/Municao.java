package Model;

import Excecao.BalasEsgotadasException;

public class Municao {
	
	
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
