package juego;

import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;
	private Obstaculo[] obstaculos;
	private Obstaculo[] obstaculos1;
	private Image imagenFondo;
	private Obstaculo obsExtra;
	/*private Obstaculo [] arregloDeObstaculos;
	private Obstaculo nuevoObstaculo;*/

	private LinkedList<MostradorDeVida> vidas;

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(140,300,20,50);
		
		Obstaculo o = new Obstaculo(150,500,200,20); //obstaculo inferior izquierdo 
		Obstaculo o2 = new Obstaculo(150,400,200,20); //obstaculo superior izquierdo
		Obstaculo o3 = new Obstaculo(450,400,200,20); //obstaculo superior del medio
		Obstaculo o4 = new Obstaculo(600,500,300,20);//obstaculo inferior derecho
		Obstaculo o5= new Obstaculo(650,400, 120,20); //obstaculo superior derecho
		Obstaculo o7 = new Obstaculo(170,595,340,40); //obstaculo base izquierdo
		Obstaculo o8 = new Obstaculo(660,595,400,40); //obstaculo base derecho
		
		obstaculos= new Obstaculo[5];
		obstaculos[0]=o;
		obstaculos[1]=o2;
		obstaculos[2]=o3;
		obstaculos[3]=o4;
		obstaculos[4]=o5;
		
		obstaculos1=new Obstaculo[2];
		obstaculos1[0]=o7;
		obstaculos1[1]=o8;



		//Creara la lista para mostrar la vida
		 vidas = new LinkedList<MostradorDeVida>();
		//se crean las vidas
		for(int j = 0; j < p.getVida(); j++) {
			MostradorDeVida v = new MostradorDeVida(j*50+50, 50);
			vidas.add(v);
		}

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

	for (MostradorDeVida vida:vidas) {
		vida.dibujar(entorno);
	}

	
    p.dibujar(entorno);
    
    ///Limites de colisión de los niveles(obstaculos) superiores
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
    ///Limites de colisión de los niveles (obstaculos) de la base 
    for (Obstaculo o: obstaculos1) {
    	
    	o.dibujar(entorno);
    	
    	if(p.colisionaPorIzquierda(o)){
    		bloqueaIzquierda=true;
    	}
    	
    	if(p.colisionaPorDerecha(o)) {
    		bloqueaDerecha=true;
    	}
    	
    	if(p.colisionaPorArriba(o)) {
    		bloqueaArriba=true;
    	}
    	
    	if(p.colisionaPorDebajo(o)) {
    		bloqueaAbajo=true;
    	}
    }
  
    //Repetición de los niveles superiores
    detectaElMovimiento(entorno,p);
    	for(Obstaculo a: obstaculos) {
    		if(p.getEnMovimiento()) {
    			a.setX(a.getX()-2);

    		}
    		if(a.bordeDerecho()<0) {
    			Random ran=new Random();
    			int r= ran.nextInt(entorno.alto());
    			a.setX(entorno.ancho()+r/2);
    	}
    		
    }
 
    //Repetición de los niveles base
    detectaElMovimiento(entorno,p);
    for(Obstaculo b: obstaculos1) {
    		if(p.getEnMovimiento()) {
    			b.setX(b.getX()-2);
    			/*b.setEnPantalla(true);*/
    		}
    		/*if(!b.getEnPantalla()){
    			obsAuxiliar(nuevoObstaculo);}*/	
    		if(b.bordeDerecho()<0) {
    			Random ran=new Random();
        		int r= ran.nextInt(entorno.alto());
    			b.setX(entorno.ancho()+r/2);
	    	}	
    		
    }
 
    //posicion del personaje en mitad de pantalla
    if(p.getX()>entorno.ancho()/2){

		p.setX((entorno.ancho()/2));
	
		

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

	p.siElPersonajeTocaElBordeInferiorDeLaPantalla(); //aqui puse que el personaje se teletransporde caundo se cae
}	
	
	
	
	public static void detectaElMovimiento(Entorno a, Personaje b) {
    	if (a.estaPresionada(a.TECLA_DERECHA) && b.getX()>=400) { //se agrego "b.getX()>=400" para que solo se mueba si esta a mitad de pantalla
    		b.setEnMovimiento(true);
    	}
    	else {
    		b.setEnMovimiento(false);
    	}
    	if(a.estaPresionada(a.TECLA_DERECHA) && a.estaPresionada(a.TECLA_IZQUIERDA)) {
    		b.setEnMovimiento(false);
    		}
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
        && (bloqueaAbajo || p.bordeInferior() >= entorno.alto())) {

        p.setEstaSaltando(true);
        p.setSaltoY(p.getY()-200);
    }

    if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)
        && p.bordeIzquierdo()>0
        && !bloqueaIzquierda) {

        p.moverIzquierda();
    }

    if(entorno.estaPresionada(entorno.TECLA_DERECHA)
        && p.bordeDerecho()<entorno.ancho()+2
        && !bloqueaDerecha) {

        p.moverDerecha();
    }

    if(p.getTieneGravedad()
        && p.bordeInferior()<=entorno.alto()-2
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
  
	/*public void obsAuxiliar(Obstaculo o) {
			o= new Obstaculo (660,595,300,40);
			o.dibujar(entorno);
			if(p.getEnMovimiento()) {
				o.setX(o.getX()-2);	
			}
				}*/
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
