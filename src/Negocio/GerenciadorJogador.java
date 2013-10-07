package Negocio;

import java.io.IOException;
import java.util.ArrayList;

import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Persistencia.JogadorDAO;
public class GerenciadorJogador {
	
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	private JogadorDAO jogadorDAO = new JogadorDAO();
	
	public boolean loginJogador(Jogador jogador) throws IOException, Exception{
		return buscarJogador(jogador);
	}
	
	public void cadastrarJogador(Jogador jogador) throws IOException, Exception {
		if(buscarJogador(jogador)){
			throw new ObjetoJaExistenteException("Não é permitido cadastrar o mesmo jogador duas vezes");
		}
		if(jogador.getSenha().length() <= 4){
			throw new ObjetoInexistenteException("A senha tem que ter mais do que 4 caracteres!");
		}
		jogadores.add(jogador);
		jogadorDAO.insert(jogadores);
	}
	
	public void removerJogador(Jogador jogador) throws Exception{
		if(!buscarJogador(jogador)){
			throw new ObjetoInexistenteException("Esse jogador não existe!");
		}
		for(Jogador j:jogadorDAO.selectAll()){
			if(j.getNome().equals(jogador.getNome())){
				jogadores.remove(jogador);
				jogadorDAO.insert(jogadores);
			}
		}
	}
	
	private boolean buscarJogador(Jogador jogador) throws IOException, Exception {
		for(Jogador j:jogadorDAO.selectAll()){
			if(j.getNome().equals(jogador.getNome()) ){
				return true;
			}
		}
		return false;
	}
	
	public int getQuantidadeDeJogadoresCadastrados() throws IOException, Exception{
		return jogadorDAO.selectAll().size();
	}
	
	public ArrayList<Jogador> listarJogadores() throws IOException, Exception{
		return jogadorDAO.selectAll();
	}

	public boolean isGameOver() {
		if(Jogador.getScore() == 0){
			return true;
		}
		return false;
	}
}
