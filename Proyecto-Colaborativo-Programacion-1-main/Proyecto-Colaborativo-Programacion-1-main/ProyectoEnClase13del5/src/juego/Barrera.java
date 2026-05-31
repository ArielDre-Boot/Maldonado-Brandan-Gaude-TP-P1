package juego;

import java.awt.Color;

import entorno.Entorno;

public class Barrera {
	private double x;
	private double y;
	private double ancho;
	private double distancia;
	private double tiempoActual;
	private double tiempoDeDesaparicion;
	
	public Barrera(double x, double y, double ancho, double distancia, int tiempo) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.distancia = distancia;
		this.tiempoActual = tiempo;
		this.tiempoDeDesaparicion = tiempo + 1000;
	}
	
	public void dibujar(Entorno e) {
		actualizarTiempoDeAparicion(e, tiempoActual);
		e.dibujarRectangulo(x + 20, y, ancho, distancia, 0, Color.RED);
	}
	
	public void actualizarTiempoDeAparicion(Entorno e, double tiempo) {
	    this.tiempoActual = tiempo;
	}
	
	public void movimiento(int xDePersonaje, int yDePersonaje) {
		this.x = xDePersonaje;
		this.y = yDePersonaje;
	}
	
	
	public double bordeDerecho() {
		return this.x + this.ancho/2;
	}
	public double bordeIzquierdo() {
		return this.x - this.ancho/2;
	}
	public double bordeInferior() {
		return this.y + this.distancia/2;
	}
	public double bordeSuperior() {
		return this.y - this.distancia/2;
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
	public double getAncho() {
		return ancho;
	}
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getTiempoActual() {
		return tiempoActual;
	}

	public void setTiempoActual(double tiempoActual) {
		this.tiempoActual = tiempoActual;
	}

	public double getTiempoDeDesaparicion() {
		return tiempoDeDesaparicion;
	}

	public void setTiempoDeDesaparicion(double tiempoDeDesaparicion) {
		this.tiempoDeDesaparicion = tiempoDeDesaparicion;
	}
	
}
