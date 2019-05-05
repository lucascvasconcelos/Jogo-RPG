package modelo;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
	public static List<Item> itensVendedor = new ArrayList<Item>();
	public String nome;
	
	public Vendedor(String nome) {
		this.nome = nome;
	}
	
	public Vendedor(String nome, List<Item> itens) {
		this.nome = nome;
		itensVendedor = itens;
	}
	
	public static List<Item> getItensVendedor() {
		return itensVendedor;
	}
	
	public void adicionarItem (Item i) {
		itensVendedor.add(i);
	}
	
	public void removerItem (Item i) {
		itensVendedor.remove(i);
	}
	
}
