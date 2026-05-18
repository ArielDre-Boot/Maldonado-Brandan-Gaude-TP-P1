package juego;

import java.awt.Color;

import entorno.Entorno;

public class Personaje {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	public Personaje(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
	}
	
	public void moverIzquierda() {
		this.x = this.x -5;
	}
	
	public void moverDerecha() {
		this.x = this.x +5;
	}
	
	public void moverArriba() {
		this.y=this.y-5;
	}
	
	public void moverAbajo() {
		this.y=this.y+5;
	}
	
	
	public boolean colisionaPorIzquierda(Obstaculo o) {
		
		if(bordeIzquierdo()<= o.bordeDerecho() && bordeIzquierdo()>= o.bordeIzquierdo()) {
			if(bordeInferior()>=o.bordeSuperior() && bordeSuperior()<=o.bordeInferior()) {
				reboteDelPersonajeContraElObjetoEjeX(3);
				return true;				
			}
		}else {
			return false;
		}
		return false;
	}
	
public boolean colisionaPorDerecha(Obstaculo o) {
		
		if(bordeDerecho()>= o.bordeIzquierdo()&& bordeDerecho()<= o.bordeIzquierdo()) {
			if(bordeInferior()>=o.bordeSuperior() && bordeSuperior()<=o.bordeInferior()) {
				reboteDelPersonajeContraElObjetoEjeX(-3);
				return true;				
			}
		}else {
			return false;
		}
		return false;
	}
	
	// aqui se agregaron mas coliciones, especificamente si coliciona por debajo o por arriba
	public boolean colisionaPorArriba(Obstaculo o) {
		
		if(bordeSuperior()<= o.bordeInferior()&& bordeInferior()>= o.bordeSuperior()) {
			if(bordeIzquierdo()<=o.bordeDerecho() && bordeDerecho()>=o.bordeIzquierdo()) {
				reboteDelPersonajeContraElObjetoEjeY(3);
				return true;				
			}
		}else {
			return false;
		}
		return false;
	}
	
	public boolean colisionaPorDebajo(Obstaculo o) {
		if(bordeInferior()>= o.bordeSuperior()&& bordeSuperior()<= o.bordeInferior()) {
			if(bordeIzquierdo()<=o.bordeDerecho() && bordeDerecho()>=o.bordeIzquierdo()) {
				reboteDelPersonajeContraElObjetoEjeY(-3);
				return true;				
			}
		}else {
			return false;
		}
		return false;
	}
	
	//aqui se agrego el rebote de el personaje para que no se quede atrapado
	public void reboteDelPersonajeContraElObjetoEjeY(int a) {
		this.y = this.y + a;
	}
	
	public void reboteDelPersonajeContraElObjetoEjeX(int a) {
		this.x = this.x + a;
	}
	
	
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
	
	
	
	
	

}
