package Negocio;

public class GerenciadorFase {
	
private ArrayList<Fase> fases = new ArrayList<Fase>();
	
	public void inserirJogadorNaFaseDisponivel(Jogador jogador){
		for(Fase f:fases){
			if(f.isLiberado()){
				f.criarJogador(jogador);
			}
		}
	}
	
	public boolean isVerificarJogadorNaFaseDisponivel(Jogador jogador){
		for(Fase f: fases){
			if(f.getJogador().equals(jogador))
				return true;
		}
		return false;
	}
	
	public void atualizarFase(int indice) throws FaseInexistenteException{
		if(indice <= 0 || indice > 5){
			throw new FaseInexistenteException("Fase inexistente");
		}
		fases.get(indice).setLiberado(true);
		fases.add(indice, fases.get(indice));
	}
	
	public ArrayList<Fase> getFases(){
		return fases;
	}
	
	public void gerarTodasAsFases(){
		int numeroMaximoDeFases = 5;
		Fase fase0 = new Fase();
		fase0.setLiberado(true);
		fases.add(fase0);
		for(int i = 1; i < numeroMaximoDeFases; i++){
			Fase fase = new Fase();
			fase.setNome("Fase: "+i);
			fase.setNvel(i);
			fase.setLiberado(false);
			fases.add(fase);
		}
	}
}
