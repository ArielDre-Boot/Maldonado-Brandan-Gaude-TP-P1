package juego;

import javax.swing.*;

import entorno.Entorno;

import java.awt.*;

public class MostradorDeVida {
	private int x;
	private int y;
	private Image imagen;
	
	public MostradorDeVida(int x, int y) {
		this.x = x;
		this.y = y;
		ImageIcon icono = new ImageIcon(getClass().getResource("Corazon.png"));
        this.imagen = icono.getImage();
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0,0.08);
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
	
}
