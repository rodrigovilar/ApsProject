package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Problema implements Serializable{
	
	private int id;
	private String questao;
	private int resposta;
	
	public int getId() {
		return id;
	}
	
	public Problema() {
		id++;
	}
	
	public String getQuestao() {
		return questao;
	}
	
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	
	public int getResposta() {
		return resposta;
	}
	public void setResposta(int resposta) {
		this.resposta = resposta;
	}
}
