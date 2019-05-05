package aplicacao;

import com.db4o.ObjectContainer;

import fachada.Fachada;

public class Teste {

	protected static ObjectContainer manager;
	public Teste(){
		Fachada.inicializar();
		cadastrar();
		listarPersonagens();
		removerPersonagem();
		listarPersonagens();
		Fachada.finalizar();
	}
	
	public void cadastrar(){
		System.out.println("Cadastrando...");

		try {
			Fachada.criarConta("usuario", "senha", "email");
			Fachada.login("usuario", "senha");
			Fachada.criarTipoPersonagem("guerreiro");
			Fachada.criarPersonagem("Lucas", "guerreiro");
			Fachada.criarItem("ARMADURA", 1, 2, 2, 2, 5, "ARMADURA");
			Fachada.criarItem("ARMADURA BLACK", 1, 2, 2, 2, 5, "ARMADURA");
			Fachada.selecionarPersonagem("Lucas");
			Fachada.adicionarItemPersonagem("ARMADURA");
			Fachada.adicionarItemPersonagem("ARMADURA BLACK");
			Fachada.equiparItem("ARMADURA");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerPersonagem() {
		try {
			//Fachada.removerPersonagem("Lkjhucas");
			Fachada.equiparItem("ARMADURA BLACK");
		} catch (Exception e) {
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
