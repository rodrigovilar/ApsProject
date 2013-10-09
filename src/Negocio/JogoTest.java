package Negocio;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Excecao.BalasEsgotadasException;
import Excecao.FaseNaoDisponivelException;
import Excecao.JogadorNaoLogadoException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Canhao;
import Model.Fase;
import Model.Jogo;
import Model.Municao;
import Model.Problema;
import Model.Professor;

@SuppressWarnings("deprecation")
public class JogoTest {
	
	private Jogo jogo;
	
	@Before
	public void iniciarTeste() throws IOException{
		jogo = new Jogo();
		jogo.iniciandoDAO();
	}
	

}
