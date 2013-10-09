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
}
