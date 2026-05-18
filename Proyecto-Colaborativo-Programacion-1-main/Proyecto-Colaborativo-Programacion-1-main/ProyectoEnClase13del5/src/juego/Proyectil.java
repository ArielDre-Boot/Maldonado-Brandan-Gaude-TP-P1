package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private double x;
	private double y;
	private double radio;
	private double dx;
	private double dy;
	
	
	public Proyectil(double x, double y, double radio, int xMouse, int yMouse) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		
		this.dx=xMouse - this.x;
		this.dy=yMouse - this.y;
		
		double distancia = Math.sqrt(dx*dx + dy*dy);
		this.dx=this.dx / (distancia + 4);
		this.dy=this.dy / (distancia + 4);
	}
	
	public void dibujar (Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.blue);
	}
	
	public void mover() {
		this.x+=this.dx;
		this.y+=this.dy;
	}

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
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}
}
