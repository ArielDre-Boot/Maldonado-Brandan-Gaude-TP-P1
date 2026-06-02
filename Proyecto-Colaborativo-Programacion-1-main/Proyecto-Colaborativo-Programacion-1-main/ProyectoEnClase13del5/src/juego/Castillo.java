package juego;

import java.awt.Color;

import entorno.Entorno;

public class Castillo{
	private int x;
	private int y;
	private int ancho;
	private int alto;
	/*private boolean enPantalla;*/
	public Castillo(int x, int y, int ancho, int alto) {
	
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		/*this.enPantalla=false;*/
	}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.YELLOW);
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
	public int bordeDerecho() {
		return this.x+this.ancho/2;
	}
	public int bordeIzquierdo() {
		return this.x-this.ancho/2;
	}
	public int bordeSuperior() {
		return this.y-this.alto/2;
	}
	public int bordeInferior() {
		return this.y+this.alto/2;
	}
	/*public boolean getEnPantalla() {
		return this.enPantalla;
	}
	public void setEnPantalla(boolean a) {
		this.enPantalla=a;
	}*/
	
}