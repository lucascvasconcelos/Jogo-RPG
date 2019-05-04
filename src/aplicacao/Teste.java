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
			Fachada.criarItem("ARMADURA", 1, 2, 2, 2, 5);
			Fachada.adicionarItemPersonagem("Lucas", "ARMADURA");
			Fachada.equiparItem("Lucas", "ARMADURA");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerPersonagem() {
		try {
			Fachada.removerPersonagem("Lkjhucas");
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
