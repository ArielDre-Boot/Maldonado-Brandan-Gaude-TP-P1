package juego;

import java.awt.Color;

import entorno.Entorno;

public class Enemigo2 {
	private int x;
	private int y;
	private int ancho;
	private int alto;
    private int velocidadX=2;
    private int velocidadY=3;
    private String direccion;
    private double aceleracion = 0.5;
	private  Proyectil disparo;
	private boolean disparoTocoJugador=false;

	public Enemigo2(int x, int y, int ancho, int alto,String direccion) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
	    this.alto = alto;
		this.direccion=direccion;
		this.disparo=null;
	
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

	public void moverAbajo() {
		   velocidadY += aceleracion;
		    y += velocidadY;
	}
	public boolean cambiarDireccionDerecha(Entorno e) {
		if(bordeDerecho()>=e.ancho() ) {
		
			return true;
		}
	
		return false;	
	}
	
	public boolean cambiarDireccionIzquierda(Entorno e) {
		if(bordeIzquierdo()<=0) {
		
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

	public boolean colisionaConBarrera(Personaje p) {
		if (p.getBarrera() != null) {
			if (bordeIzquierdo() <= p.getBarrera().bordeDerecho() +20 && bordeDerecho() >= p.getBarrera().bordeDerecho()) {
				if (bordeInferior() >= p.getBarrera().bordeSuperior() && bordeSuperior() <= p.getBarrera().bordeInferior()) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean disparoColisionaJugador(Personaje p) {
		
		if(this.disparo!=null && 	!disparoTocoJugador) {
			if(this.disparo.bordeDerecho()>=p.bordeIzquierdo() && this.disparo.bordeSuperior()<=p.bordeInferior() && this.disparo.bordeInferior()>=p.bordeSuperior()&& this.disparo.bordeIzquierdo()<=p.bordeDerecho()) {
				this.disparoTocoJugador=true;
				return true;
			}
		}
		return false;
	}

	public boolean isDisparoTocoJugador() {
		return disparoTocoJugador;
	}

	public void setDisparoTocoJugador(boolean disparoTocoJugador) {
		this.disparoTocoJugador = disparoTocoJugador;
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
	public void disparo(Personaje p) {
		int xMouse = p.getX();
		int yMouse = p.getY();
		
		this.disparo = new Proyectil(this.x , this.y , 20, xMouse, yMouse);
	
	}
	public Proyectil getDisparo() {
		return this.disparo;
	}
	public void setDisparo(Proyectil disparo) {
		this.disparo = disparo;
	}
}
