package juego;

import java.awt.Color;

import entorno.Entorno;


public class Personaje {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int saltoY;
	private  Proyectil disparo;
	private boolean tieneGravedad ;
	private boolean estaSaltando;
	private boolean seMueve;
    private int velocidadX=5;
    private int velocidadY=5;
	private int vida = 10;
	private boolean llegoAlCastillo;
	
	public Personaje(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.saltoY=0;
		this.ancho = ancho;
		this.alto = alto;
		this.disparo=null;
		this.tieneGravedad=true;
		this.estaSaltando=false;
		this.seMueve=false;
		this.llegoAlCastillo=false;
	}
	public boolean getEnMovimiento() {
		return seMueve;
	}
	public void setEnMovimiento(boolean a) {
		this.seMueve=a;
	}
	public boolean isEstaSaltando() {
		return estaSaltando;
	}

	public void setEstaSaltando(boolean estaSaltando) {
		this.estaSaltando = estaSaltando;
	}

	public boolean getTieneGravedad() {
		return tieneGravedad;
	}

	public void setTieneGravedad(boolean tieneGravedad) {
		this.tieneGravedad = tieneGravedad;
	}

	public int getSaltoY() {
		return saltoY;
	}

	public void setSaltoY(int saltoY) {
		this.saltoY = saltoY;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
	}
	
	public void moverIzquierda() {
		this.x = this.x -velocidadX;
	}
	
	public void moverDerecha() {
		this.x = this.x +velocidadX;
	}
	public void moverArriba() {
		this.y=this.y-velocidadY;
	}
	public void saltar() {
	
		this.y=this.y-velocidadY;
		
	}
	
	public void moverAbajo() {
		this.y=this.y+velocidadY;
	}
	
	
	public boolean colisionaPorIzquierda(Obstaculo o) {
		
		if(bordeIzquierdo()<= o.bordeDerecho() && bordeDerecho()>=(o.getX())) { 
			if(bordeInferior()>=o.bordeSuperior()+1 && bordeSuperior()<=o.bordeInferior()-1) {
				rebote(+5);
				System.out.println("+5");
				return true;
			}	
		}
		return false;
	}
	

	public boolean colisionaPorDerecha(Obstaculo o) {
		if(bordeDerecho()>= o.bordeIzquierdo()&& bordeIzquierdo()<= o.getX()) {
			if(bordeInferior()>=o.bordeSuperior()+1 && bordeSuperior()<=o.bordeInferior()-1) {
				rebote(-5);
				System.out.println("-5");
				return true;	
			}

		}
		
		  
		
		
		return false;
	}
	
	// aqui se agregaron mas coliciones, especificamente si coliciona por debajo o por arriba
	public boolean colisionaPorArriba(Obstaculo o) {
		if(bordeSuperior()<= o.bordeInferior()&& bordeInferior()>= o.bordeSuperior()+1) {
			if(bordeDerecho()>=o.bordeIzquierdo()+1 && bordeIzquierdo()<=o.bordeDerecho()-1) {
				return true;				
		}
		
	}
		return false;
	}
			
	public boolean colisionaPorDebajo(Obstaculo o) {
	  if(bordeInferior()>= o.bordeSuperior()&& bordeSuperior()<= o.bordeInferior()-1) {
			if(bordeIzquierdo()<=o.bordeDerecho()-1 && bordeDerecho()>=o.bordeIzquierdo()+1) {
				return true;				
		}
	  }

	return false;
	
	}
	

		
		
	//aqui se agrego el rebote de el personaje para que no se quede atrapado
	public void rebote(int a) {
		this.x += a;
	}
	
	//aqui se agrego para teletransportar en caso de que el personaje toque el borde inferior de la pantalla
	public void siElPersonajeTocaElBordeInferiorDeLaPantalla() {
		if (this.y >= 575) {
			this.y = 100;
			this.x = 300;
		}
	}
	
	//disparo
	public void disparo(Entorno e) {
		int xMouse = e.mouseX();
		int yMouse = e.mouseY();
		
		this.disparo = new Proyectil(this.x , this.y , 30, xMouse, yMouse);
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
	
	public Proyectil getDisparo() {
		return this.disparo;
	}
	
	public void setDisparo(Proyectil disparo) {
		this.disparo = disparo;
	}
	
	public int getVida() {
		return vida;
	}
	public boolean getLlegoCastillo() {
		return this.llegoAlCastillo;
	}
	public void setLlegoCastillo(boolean v) {
		this.llegoAlCastillo=v;
	}
}
