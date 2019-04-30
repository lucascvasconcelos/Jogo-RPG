package fachada;

import java.util.List;

import dao.DAO;
import dao.DAOConta;
import dao.DAOPersonagem;
import modelo.*;

public class Fachada {
	private static DAOPersonagem daopersonagem = new DAOPersonagem();
	private static DAOConta daoconta = new DAOConta();
	
	public static void inicializar(){
		DAO.open();
	}

	public static void finalizar(){
		DAO.close();
	}
	
	public static Personagem criarPersonagem(String nome, int nivel, double vida, double ataque, double defesa) throws Exception{
		DAO.begin();
		Personagem personagem = daopersonagem.read(nome);
		if(personagem != null){
			throw new Exception("Personagem já cadastrado: " + personagem);
		}
		personagem = new Personagem(nome, nivel, vida, ataque, defesa);
		daopersonagem.create(personagem);
		DAO.commit();
		
		return personagem;
	}
	
	public static void removerPersonagem(Personagem personagem) throws Exception{
		DAO.begin();
		Personagem p = daopersonagem.read(personagem.getNome());
		if(p==null) {
			throw new Exception("Personagem não existe!!");
		}
		daopersonagem.delete(personagem);		
		DAO.commit();
		
	}
	
	public static String listarPersonagens() {
		String lista = "\nLista de Personagens: ";
		List<Personagem> personagens = daopersonagem.readAll();
		for(Personagem personagem : personagens) {
			lista += "\n" + personagem;
		}
		return lista;
	}
	
	public static Conta criarConta(String usuario, String senha, String email) throws Exception{
		DAO.begin();
		
		Conta conta = daoconta.read(usuario);
		if(conta != null) {
			throw new Exception("Usuário já existe: " + usuario);
		}
		conta = new Conta(usuario, senha, email);
		daoconta.create(conta);
		DAO.commit();
		return conta;
	}
	
	
	
	
}
