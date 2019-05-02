package fachada;
import modelo.*;
import java.util.List;

import dao.DAO;
import dao.DAOConta;
import dao.DAOPersonagem;
import dao.DAOTipoPersonagem;
import modelo.*;

public class Fachada {
	private static DAOPersonagem daopersonagem = new DAOPersonagem();
	private static DAOConta daoconta = new DAOConta();
	private static DAOTipoPersonagem daotipopersonagem = new DAOTipoPersonagem();

	private static Conta contaAtual = null;
	
	public static void inicializar(){
		DAO.open();
	}

	public static void finalizar(){
		DAO.close();
	}
	
	public static void login(String usuario, String senha)throws Exception{
		if(contaAtual!=null) {
			throw new Exception("Você já está logado!!");
		}
		List<Conta> contas = daoconta.readAll();
		for(Conta c:contas) {
			if(c.getUsuario().equals(usuario) && c.getSenha().equals(senha)) {
				contaAtual = c;
			}
		}
		if(contaAtual==null) {
			throw new Exception("Usuário e senha inválidos!!");
		}
		
	}
	
	public static TipoPersonagem criarTipoPersonagem(String descricao) throws Exception {
		DAO.begin();
		TipoPersonagem tipo = daotipopersonagem.read(descricao);
		if(tipo!=null) {
			throw new Exception("Tipo já existe!!");
		}
		tipo = new TipoPersonagem(descricao);
		daotipopersonagem.create(tipo);
		DAO.commit();
		return tipo;
	}
	
	public static Personagem criarPersonagem(String nome, String descricao) throws Exception{
		DAO.begin();
		
		TipoPersonagem tipo = daotipopersonagem.read(descricao);
		if(tipo==null) {
			throw new Exception("Tipo nao cadastrado!!");
		}
		
		
		if(contaAtual==null) {
			throw new Exception("Você precisa está logado");
		}
		
		Personagem personagem = daopersonagem.read(nome);
		if(personagem != null){
			throw new Exception("Personagem já cadastrado: " + personagem);
		}
		personagem = new Personagem(nome, tipo);
		daopersonagem.create(personagem);
		contaAtual.addPersonagem(personagem);
		daoconta.update(contaAtual);
		DAO.commit(); 
		return personagem;
	}
	
	public static void removerPersonagem(String personagem) throws Exception{
		DAO.begin();
		Personagem p = daopersonagem.read(personagem);
		if(p==null) {
			throw new Exception("Personagem não existe!!");
		}
		daopersonagem.delete(p);		
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
