package aplicacao;

import com.db4o.ObjectContainer;

import fachada.Fachada;

public class Teste {
	
	protected static ObjectContainer manager;
	public Teste(){
		Fachada.inicializar();
		listarPersonagens();
		cadastrar();
		Fachada.finalizar();
	}
	
	public void cadastrar(){
		System.out.println("Cadastrando...");

		try {
			Fachada.criarPersonagem("aksjdha", 1, 2, 3, 4);
			
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
