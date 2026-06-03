package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Image imagen;
	/*private boolean seMueven;
	private boolean enPantalla;*/
	
	public Obstaculo(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		Image icono = Herramientas.cargarImagen("juego/baseVerde1.png");
		this.imagen = icono;		
		/*this.seMueven=false;
		this.enPantalla=false;*/
	}

	
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 0.190);
	}
	public void dibujarBase(Entorno e) {
	
		e.dibujarImagen(imagen, x, y, 0, 0.5);
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
	/*public boolean getSeMueven() {
		return this.seMueven;
	}
	public void setSeMueven(boolean a) {
		this.seMueven=a;
	}*/
	
}
