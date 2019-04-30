package modelo;

public class Item {
	private String nome;
	private int nivel;
	private int ataque;
	private int defesa;
	private double vida;
	private float preco;
	
	public Item(String nome, int nivel, int ataque, int defesa, double vida, float preco) {
		super();
		this.nome = nome;
		this.nivel = nivel;
		this.ataque = ataque;
		this.defesa = defesa;
		this.vida = vida;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefesa() {
		return defesa;
	}

	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}

	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
	
}
