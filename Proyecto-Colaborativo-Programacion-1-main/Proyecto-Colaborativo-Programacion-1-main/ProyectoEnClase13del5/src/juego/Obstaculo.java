package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import entorno.Entorno;

public class Obstaculo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	/*private boolean enPantalla;*/
	
	public Obstaculo(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		/*this.enPantalla=false;*/
	}

	
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.gray);
	}
	
	/*public boolean getEnPantalla() {
		return enPantalla;
	}
	public void setEnPantalla(boolean b) {
		this.enPantalla=b;
	}*/
	public int bordeDerecho() {
		
		return this.x+this.ancho/2;
	}
	
	public int bordeIzquierdo() {
		return this.x-this.ancho/2;
	}
	
	public int bordeInferior() {
		return this.y+this.alto/2;
	}
	
	public int bordeSuperior() {
		return this.y-this.alto/2;
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int setBordeDerecho(int a) {
		return (this.x+this.ancho/2)+a;
	}
	public int setBordeIzquierdo(int a) {
		return (this.x+this.ancho/2)+a;
	}
	
	
}
