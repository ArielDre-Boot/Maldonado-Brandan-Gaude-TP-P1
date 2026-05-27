package juego;

import java.awt.Color;

import entorno.Entorno;

public class Enemigo {
	private int x;
	private int y;

	private int ancho;
	private int alto;
    private int velocidadX=4;
    private String direccion;
    
	public Enemigo(int x, int y, int ancho, int alto,String direccion) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.direccion=direccion;
		
	
	
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
	}
	public void moverIzquierda() {
		this.x = this.x -velocidadX;
	}
	
	public void moverDerecha() {
		this.x = this.x +velocidadX;
	}
	public boolean esDestruibleDerecha(Entorno e) {
		if(bordeIzquierdo()>=e.ancho() ) {
		
			return true;
			
		}
		return false;
		
	}
	
	public boolean esDestruiblePorIzquierda(Entorno e) {
		if(bordeDerecho()<=0) {
		
			return true;
			
		}
		return false;
		
	}
    public int bordeIzquierdo() {
    	return this.x-this.ancho/2;
    }
    public int bordeDerecho() {
    	return this.x+this.ancho/2;
    }
    public int bordeSuperior () {
    	return this.y-this.alto/2;
    }
    public int bordeInferior() {
    	return this.y+this.alto/2;
    }
	public boolean colisionaConElJugador(Personaje p) {
	
		if(bordeIzquierdo()<= p.bordeDerecho() && bordeDerecho()>=(p.getX()) && bordeSuperior()<=p.bordeInferior() && bordeInferior()>=p.bordeSuperior()) { 
			return true;
		}
		return false;
	}
	public boolean colisionaConObstaculo(Obstaculo o) {
		
		if(bordeInferior()>= o.bordeSuperior() && bordeSuperior()<=o.bordeInferior()
				) { 
			return true;
		}
		return false;
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
	public int getVelocidadX() {
		return velocidadX;
	}
	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

}
