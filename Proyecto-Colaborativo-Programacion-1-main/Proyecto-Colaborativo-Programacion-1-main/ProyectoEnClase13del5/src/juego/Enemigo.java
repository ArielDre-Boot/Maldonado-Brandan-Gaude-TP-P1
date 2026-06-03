package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
    private int velocidadX=3;
    private String direccion;
    /*private Image imagenParado;
    private boolean adelante;
    private Image imagenAdelante;
    private boolean atras;
    private Image imagenAtras;*/
    
	public Enemigo(int x, int y, int ancho, int alto,String direccion) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.direccion=direccion;
		/*Image icono= Herramientas.cargarImagen("juego/2.jpeg");
        this.imagenAdelante = icono;
        Image icono2= Herramientas.cargarImagen("juego/1.jpeg");
        this.imagenAtras = icono2;
        Image icono3= Herramientas.cargarImagen("juego/3.jpeg");
        this.imagenParado = icono3;*/
		
	
	
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
