package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private double x;
	private double y;
	private double diametro;
	private double dx;
	private double dy;
	
	
	public Proyectil(double x, double y, double diametro, int xMouse, int yMouse) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		
		this.dx=xMouse - this.x;
		this.dy=yMouse - this.y;
		
		double distancia = Math.sqrt(dx*dx + dy*dy);
		this.dx=this.dx / (distancia)*4;
		this.dy=this.dy / (distancia)*4;
	}
	
	public void dibujar (Entorno e) {
		e.dibujarCirculo(x, y, diametro, Color.blue);
	}
	
	public void mover() {
		this.x+=this.dx ;
		this.y+=this.dy;
		
	}
	public boolean colisionaDisparoConEnemigo(Enemigo e) {

	    if (
	        bordeDerecho() >= e.bordeIzquierdo() &&
	        bordeIzquierdo() <= e.bordeDerecho() &&
	        bordeInferior() >= e.bordeSuperior() &&
	        bordeSuperior() <= e.bordeInferior()
	    ) {
	      
	        return true;
	    }

	    return false;
	}
	

//	public boolean colisionaPorDerecha(Obstaculo o) {
//		if(bordeDerecho()>= o.bordeIzquierdo()&& bordeIzquierdo()<= o.getX()) {
//			if(bordeInferior()>=o.bordeSuperior()+1 && bordeSuperior()<=o.bordeInferior()-1) {
//				rebote(-5);
//				System.out.println("-5");
//				return true;	
//			}
//
//		}
//		
//		  
//		
//		
//		return false;
//	}
//	
//	// aqui se agregaron mas coliciones, especificamente si coliciona por debajo o por arriba
//	public boolean colisionaPorArriba(Obstaculo o) {
//		if(bordeSuperior()<= o.bordeInferior()&& bordeInferior()>= o.bordeSuperior()+1) {
//			if(bordeDerecho()>=o.bordeIzquierdo()+1 && bordeIzquierdo()<=o.bordeDerecho()-1) {
//				return true;				
//		}
//		
//	}
//		return false;
//	}
//			
//	public boolean colisionaPorDebajo(Obstaculo o) {
//	  if(bordeInferior()>= o.bordeSuperior()&& bordeSuperior()<= o.bordeInferior()-1) {
//			if(bordeIzquierdo()<=o.bordeDerecho()-1 && bordeDerecho()>=o.bordeIzquierdo()+1) {
//				return true;				
//		}
//	  }
//
//	return false;
//	
//	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return diametro;
	}

	public void setRadio(double diametro) {
		this.diametro = diametro;
	}
	public double bordeDerecho() {
	    return this.x + this.diametro / 2;
	}

	public double bordeIzquierdo() {
	    return this.x - this.diametro / 2;
	}

	public double bordeInferior() {
	    return this.y + this.diametro / 2;
	}

	public double bordeSuperior() {
	    return this.y - this.diametro / 2;
	}
}
