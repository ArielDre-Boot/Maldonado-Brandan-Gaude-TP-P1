package juego;

import java.awt.Color;

import entorno.Entorno;

public class Escritor {
	private int x;
	private int y;
	private String textoGanador;
	private String textoPerdedor;
	public Escritor(int x, int y, String textoGanador, String testoPerdedor) {
		super();
		this.x = x;
		this.y = y;
		this.textoGanador = textoGanador;
		this.textoPerdedor = testoPerdedor;
		
		
	}
	
	public void dibujar(String a, Entorno e) {
		e.cambiarFont("Arial", 50, Color.cyan);
		if (a.equals("W")) {
			e.escribirTexto(textoGanador, x, y);		
		}
		if (a.equals("L")) {
			e.escribirTexto(textoPerdedor, x, y);
		}
	}
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getTextoGanador() {
		return textoGanador;
	}
	public void setTextoGanador(String textoGanador) {
		this.textoGanador = textoGanador;
	}
	public String getTestoPerdedor() {
		return textoPerdedor;
	}
	public void setTestoPerdedor(String testoPerdedor) {
		this.textoPerdedor = testoPerdedor;
	}
	
	
}
