package juego;


import java.awt.Color;
import java.util.LinkedList;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;
	private LinkedList<Obstaculo> obstaculos;
	Obstaculo seMueve;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(400,300,20,50);
		
		Obstaculo o = new Obstaculo(200,500,100,10);
		Obstaculo o2 = new Obstaculo(350,450,100,10);
		Obstaculo o3 = new Obstaculo(500,400,100,10);
		Obstaculo o4 = new Obstaculo(650,350,100,10);
		Obstaculo o5 = new Obstaculo(500,250,100,10);
		obstaculos=new LinkedList<Obstaculo>();
		obstaculos.add(o);
		obstaculos.add(o2);
		obstaculos.add(o3);
		obstaculos.add(o4);
		obstaculos.add(o5);
		
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
    p.dibujar(entorno);

    boolean bloqueaIzquierda = false;
    boolean bloqueaDerecha = false;
    boolean bloqueaAbajo = false;
    boolean bloqueaArriba = false;

    for (Obstaculo obstaculo : obstaculos) {

        obstaculo.dibujar(entorno);
     
        if (p.colisionaPorIzquierda(obstaculo)) {
            bloqueaIzquierda = true;
        }

        if (p.colisionaPorDerecha(obstaculo)) {
            bloqueaDerecha = true;
        }

        if (p.colisionaPorDebajo(obstaculo)) {
            bloqueaAbajo = true;
        }

        if (p.colisionaPorArriba(obstaculo)) {
            bloqueaArriba = true;
        }      
    }
    //Repetición de los niveles
    ////Me faltó ver como hacer para que cuando salen por derecha, cada nivel tenga una ubicación random
    for(Obstaculo a: obstaculos) {
    	if(p.getEnMovimiento()) {
    		int cuantoRetrocede=a.getX()-2;
    		a.setX(cuantoRetrocede);
    		}
    	if(a.bordeIzquierdo()<0) {
    		a.setX(entorno.ancho());
    		}
    	if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
    		p.setEnMovimiento(true);
    	}
    	else {
    		p.setEnMovimiento(false);
    	}
  
    }
    
    if(p.getX()>entorno.ancho()/2){
		p.setX((entorno.ancho()/2)+10);
	}


    controlMovimientoJugador(
        entorno,
        p,
        bloqueaIzquierda,
        bloqueaDerecha,
        bloqueaAbajo,
        bloqueaArriba
    );
    controlDelSalto(p,bloqueaArriba);

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
		//colision del lado derecho
		if (p.getDisparo()!=null && p.getDisparo().getX()>entorno.ancho()) {
			p.setDisparo(null);
			System.out.println("eliminado");
		}
			//colision por la parte superior
		if (p.getDisparo()!=null && p.getDisparo().getY()<0) {
			p.setDisparo(null);
			System.out.println("eliminado");
		}
			//colision por la parte inferior
		if (p.getDisparo()!=null && p.getDisparo().getY()>entorno.alto()) {
			p.setDisparo(null);
			System.out.println("eliminado");
		}
			
	}

	// funcion que controla las colisiones dado un entorno personaje o obstaculo
	public static void controlMovimientoJugador(
    Entorno entorno,
    Personaje p,
    boolean bloqueaIzquierda,
    boolean bloqueaDerecha,
    boolean bloqueaAbajo,
    boolean bloqueaArriba){
	if(entorno.sePresiono(entorno.TECLA_ARRIBA)
        && !bloqueaArriba
        && (bloqueaAbajo || p.getY()+p.getAlto()/2 >= entorno.alto())) {

        p.setEstaSaltando(true);
        p.setSaltoY(p.getY()-200);
    }

    if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)
        && p.getX()-p.getAncho()/2>0
        && !bloqueaIzquierda) {

        p.moverIzquierda();
    }

    if(entorno.estaPresionada(entorno.TECLA_DERECHA)
        && p.getX()+p.getAncho()/2<entorno.ancho()+2
        && !bloqueaDerecha) {

        p.moverDerecha();
    }

    if(p.getTieneGravedad()
        && p.getY()+p.getAlto()/2<=entorno.alto()-2
        && !bloqueaAbajo) {

        p.moverAbajo();
    }

    if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)
        && p.getDisparo()==null) {

        p.disparo(entorno);
    }
}
	public static void controlDelSalto(Personaje p , boolean bloqueaArriba) {
		if(p.isEstaSaltando()) {
			if(!bloqueaArriba) {
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
