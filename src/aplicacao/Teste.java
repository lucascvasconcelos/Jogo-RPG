package aplicacao;

import com.db4o.ObjectContainer;

import fachada.Fachada;

public class Teste {
	
	protected static ObjectContainer manager;
	public Teste(){
		Fachada.inicializar();
		
		cadastrar();
		listarPersonagens();
		Fachada.finalizar();
	}
	
	public void cadastrar(){
		System.out.println("Cadastrando...");

		try {
			
			Fachada.criarPersonagem("aksjdha", "ksjhda");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarPersonagens(){
		System.out.println(Fachada.listarPersonagens());
	}
	
	//=================================================
		public static void main(String[] args) {
			new Teste();
		}

}
