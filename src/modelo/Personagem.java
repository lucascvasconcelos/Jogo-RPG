package modelo;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
	private String nome;
	private int nivel;
	private double vida;
	private double ataque;
	private double defesa;
	private List<Item> itensPersonagem = new ArrayList<Item>();
	
	public Personagem(String nome, int nivel, double vida, double ataque, double defesa) {
		super();
		this.nome = nome;
		this.nivel = nivel;
		this.vida = vida;
		this.ataque = ataque;
		this.defesa = defesa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}

	public double getAtaque() {
		return ataque;
	}

	public void setAtaque(double ataque) {
		this.ataque = ataque;
	}

	public double getDefesa() {
		return defesa;
	}

	public void setDefesa(double defesa) {
		this.defesa = defesa;
	}

	public List<Item> getItensPersonagem() {
		return itensPersonagem;
	}

	public void setItensPersonagem(List<Item> itensPersonagem) {
		this.itensPersonagem = itensPersonagem;
	}

	@Override
	public String toString() {
		return "Personagem [nome=" + nome + ", nivel=" + nivel + ", vida=" + vida + ", ataque=" + ataque + ", defesa="
				+ defesa + ", itensPersonagem=" + itensPersonagem + "]";
	}
	
	
}
