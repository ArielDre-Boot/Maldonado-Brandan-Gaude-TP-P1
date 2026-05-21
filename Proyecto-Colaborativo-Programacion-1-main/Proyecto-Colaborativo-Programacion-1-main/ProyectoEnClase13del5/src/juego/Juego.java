package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;
	private Obstaculo o;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(400,300,20,50);
		o = new Obstaculo(200,500,100,10);

		// Inicia el juego!
		this.entorno.iniciar();
	
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
	
		//Dibujar objetros
		p.dibujar(entorno);
		o.dibujar(entorno);
		
		controlDeColisionesYMovimientoDelJugador(entorno, p, o);
		controlDelSalto(p, o);
		controlDelProyectil(p, entorno);
			
		
	
	}

	public static void controlDelProyectil(Personaje p, Entorno entorno) {
		if(p.getDisparo()!=null) {
			p.getDisparo().dibujar(entorno);
		}
		
		
		
		//movimiento del proyectil
		if (p.getDisparo()!=null) {
			p.getDisparo().mover();
		}
		
		//colicsiones del proyectil
		if (p.getDisparo()!=null && p.getDisparo().getX() < 0) {
			p.setDisparo(null);
			System.out.println("eliminado");
		}
	}// funcion que controla las colisiones dado un entorno personaje o obstaculo
	public static void controlDeColisionesYMovimientoDelJugador(Entorno entorno, Personaje p, Obstaculo o) {
	
		if(entorno.sePresiono(entorno.TECLA_ARRIBA) && p.getY()-p.getAlto()/2>=0 ) {
			if(!p.colisionaPorArriba(o) && (p.colisionaPorDebajo(o) || p.getY()+p.getAlto()/2==entorno.alto())) {
				p.setEstaSaltando(true);
				p.setSaltoY(p.getY()-250);
				
			}
		}
	if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && p.getX()-p.getAncho()/2>0) {
		if(!p.colisionaPorIzquierda(o)) {
			p.moverIzquierda();							
		}
	}
	
	if(entorno.estaPresionada(entorno.TECLA_DERECHA) && p.getX()+p.getAncho()/2<entorno.ancho()+2) {
		if(!p.colisionaPorDerecha(o)) {
			p.moverDerecha();							
		}
	}
	
	
	if(p.getTieneGravedad() && p.getY()+p.getAlto()/2<=entorno.alto()-2) {
		if(!p.colisionaPorDebajo(o)) {
			p.moverAbajo();
		}
	}
	
	if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && p.getDisparo()==null) {
		p.disparo(entorno);
	}
}
	public static void controlDelSalto(Personaje p ,Obstaculo o) {
		if(p.isEstaSaltando()) {
			if(!p.colisionaPorArriba(o)) {
				p.setTieneGravedad(false);
				p.saltar();
				if(p.getY()<=p.getSaltoY()) {
					p.setTieneGravedad(true);
					p.setEstaSaltando(false);
				}
			}else {
				p.setTieneGravedad(true);
				p.setEstaSaltando(false);
			}
			
		}
	}


	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
