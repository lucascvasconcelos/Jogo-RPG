package fachada;
import modelo.*;
import java.util.List;

import dao.DAO;
import dao.DAOConta;
import dao.DAOItem;
import dao.DAOPersonagem;
import dao.DAOTipoPersonagem;
import dao.DAOVendedor;
import modelo.*;

public class Fachada {
	private static DAOPersonagem daopersonagem = new DAOPersonagem();
	private static DAOConta daoconta = new DAOConta();
	private static DAOItem daoitem = new DAOItem();
	private static DAOVendedor daovendedor = new DAOVendedor();
	private static DAOTipoPersonagem daotipopersonagem = new DAOTipoPersonagem();

	private static Conta contaAtual = null;
	private static Personagem personagemAtual = null;
	
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
		
		contaAtual = daoconta.readAll().get(0);
//		for(Conta c:contas) {
//			if(c.getUsuario().equals(usuario) && c.getSenha().equals(senha)) {
//				contaAtual = c;
//			}
//		}
//		if(contaAtual==null) {
//			throw new Exception("Usuário e senha inválidos!!");
//		}
		
	}
	
	public static void selecionarPersonagem (String nome)throws Exception {
		Personagem personagem = contaAtual.localizarPersonagem(nome);
		if(personagem == null) {
			throw new Exception("O personagem nao existe em sua conta!");
		}
		personagemAtual = personagem;
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
	
	public static List<Personagem> listaPersonagem(){
		System.out.println(contaAtual);
		return contaAtual.getPersonagens();
	}
	
	public static List<TipoPersonagem> listarTipo(){
		System.out.println("kjashkldja");

		List<TipoPersonagem> tipos = daotipopersonagem.readAll();
		
		return tipos;
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
	
	public static Item criarItem(String nome, int nivel, int ataque, int defesa, double vida, float preco, String tipo)throws Exception {
		DAO.begin();
		Item item = daoitem.read(nome);
		if (item!=null) {
			throw new Exception("Item existente!!");
		}
		item = new Item(nome, nivel, ataque, defesa, vida, preco, tipo);
		daoitem.create(item);
		DAO.commit();
		return item;
	}
	
	public static void adicionarItemPersonagem(String nomeItem)throws Exception {
		DAO.begin();
		Item item = daoitem.read(nomeItem);
		if (item==null) {
			throw new Exception("Esse item não existe!!");
		}
		if(personagemAtual == null) {
			throw new Exception ("Voce nao selecionou nenhum personagem!");
		}
		personagemAtual.addItem(item);
		daopersonagem.update(personagemAtual);
		DAO.commit();
	}
	
	public static void removerItemPersonagem (String nomeItem)throws Exception {
		DAO.begin();
		Item item = daoitem.read(nomeItem);
		if (item==null) {
			throw new Exception("Esse item não existe!!");
		}
		if(personagemAtual == null) {
			throw new Exception ("Voce nao selecionou nenhum personagem!");
		}
		personagemAtual.removerItem(item);
		daopersonagem.update(personagemAtual);
		DAO.commit();
		
	}
	
//	Quando se equipa o item, o persongaem tem alterações na vida, ataque e defesa
	public static void equiparItem(String nomeItem) throws Exception{
		DAO.begin();
		Item itemEquipar = null;
		if(personagemAtual == null) {
			throw new Exception("Voce nao selecionou nenhum personagem!");
		}
		for(Item item: personagemAtual.getItensPersonagem()){
			if(item.getNome().equals(nomeItem)) {
				itemEquipar = item;
			}
		}
		for (Item item2: personagemAtual.getItensEquipados()){
			if(item2.getTipo().equals(itemEquipar.getTipo())) {
				throw new Exception("Voce ja tem um item do mesmo tipo equipado.\nDesequipe ele primeiro. ("+item2.getNome()+")");
			}
		}
		
		if(itemEquipar==null) {
			throw new Exception("O seu personagem ainda não possui esse item!!");
		}
		personagemAtual.getItensEquipados().add(itemEquipar);
		personagemAtual.getItensPersonagem().remove(itemEquipar);
		double ataqueAtual = personagemAtual.getAtaque() + itemEquipar.getAtaque();
		double defesaAtual = personagemAtual.getDefesa() + itemEquipar.getDefesa();
		double vidaAtual = personagemAtual.getVida() + itemEquipar.getVida();
		personagemAtual.setAtaque(ataqueAtual);
		personagemAtual.setDefesa(defesaAtual);
		personagemAtual.setVida(vidaAtual);
		daopersonagem.update(personagemAtual);
		DAO.commit();
	}
	
	public static void desequiparItem(String nomeItem)throws Exception {
		DAO.begin();
		Item itemEquipado = null;
		if(personagemAtual == null) {
			throw new Exception("Voce nao selecionou nenhum personagem!");
		}
		for(Item item: personagemAtual.getItensEquipados()){
			if(item.getNome().equals(nomeItem)) {
				itemEquipado = item;
			}
		}
		if(itemEquipado==null) {
			throw new Exception("O seu personagem ainda não possui esse item!!");
		}
		personagemAtual.getItensEquipados().remove(itemEquipado);
		personagemAtual.getItensPersonagem().add(itemEquipado);
		double ataqueAtual = personagemAtual.getAtaque() - itemEquipado.getAtaque();
		double defesaAtual = personagemAtual.getDefesa() - itemEquipado.getDefesa();
		double vidaAtual = personagemAtual.getVida() - itemEquipado.getVida();
		personagemAtual.setAtaque(ataqueAtual);
		personagemAtual.setDefesa(defesaAtual);
		personagemAtual.setVida(vidaAtual);
		daopersonagem.update(personagemAtual);
		DAO.commit();
	}
	
	public static void criarVendedor(String nome)throws Exception {
		DAO.begin();
		Vendedor v = daovendedor.read(nome);
		if(v!=null) {
			throw new Exception("Ja existe um vendedor com este nome.");
		}
		v = new Vendedor(nome);
		daovendedor.create(v);
		DAO.commit();
	}
	
	public static void adicionarItemVendedor(String nome, String nomeItem) throws Exception{
		DAO.begin();
		Vendedor v = daovendedor.read(nome);
		if(v == null) {
			throw new Exception("Este vendedor nao existe");
		}
		Item i = daoitem.read(nomeItem);
		if(i==null) {
			throw new Exception("Este item nao existe.");
		}
		v.adicionarItem(i);
		daovendedor.update(v);
		DAO.commit();
		
	}
	
	public static void comprarItem(String item)throws Exception {
		DAO.begin();
		if(contaAtual == null) {
			throw new Exception ("Voce nao esta logado.");
		}
		if(personagemAtual == null) {
			throw new Exception("Voce nao selecionou nenhum personagem.");
		}
		Item i = daoitem.read(item);
		if(i == null) {
			throw new Exception("Este item nao existe");
		}
		personagemAtual.addItem(i);
		if(personagemAtual.getGold() - i.getPreco()<0) {
			throw new Exception("Voce nao possui gold suficiente.");
		}
		float decremento = personagemAtual.getGold() - i.getPreco();
		personagemAtual.setGold(decremento);
		
		daopersonagem.update(personagemAtual);
		DAO.commit();
	}
}
