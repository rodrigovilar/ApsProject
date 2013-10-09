package Negocio;



import java.util.ArrayList;
import java.util.Random;

import Excecao.FaseNaoDisponivelException;
import Excecao.LoginInexistenteException;
import Excecao.ObjetoInexistenteException;
import Excecao.ObjetoJaExistenteException;
import Model.Balao;
import Model.Problema;
import Persistencia.ProblemaDAO;

public class GerenciadorProblema {
	private ArrayList<Problema> problemas = new ArrayList<Problema>();
	private boolean isProfessorLogado;
	private GerenciadorFase gerenciadorFase = new GerenciadorFase();
	private ArrayList<Balao> baloes= new ArrayList<Balao>();
	private ProblemaDAO problemaDAO = new ProblemaDAO();
	
	public void cadastrarProblema(Problema problema) throws Exception {
		if(!isProfessorLogado){
			throw new LoginInexistenteException("Você não tem permissão para cadastrar o problema!");
		}
		problemas.add(problema);
		problemaDAO.insert(problemas);
		
	}
	
	public GerenciadorFase getGerenciadorFase(){
		return gerenciadorFase;
	}
	
	public boolean getIsLogado(){
		return isProfessorLogado;
	}
	
	public void setIsProfessorLogado(boolean status){
		isProfessorLogado = status;
	}
}
