package fachada;

import java.util.List;

import dao.DAO;
import dao.DAOPersonagem;
import modelo.*;

public class Fachada {
	private static DAOPersonagem daopersonagem = new DAOPersonagem();
	
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
	
	public static String listarPersonagens() {
		String lista = "\nLista de Personagens: ";
		List<Personagem> personagens = daopersonagem.readAll();
		for(Personagem personagem : personagens) {
			lista += "\n" + personagem;
		}
		return lista;
	}
}
