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
}
