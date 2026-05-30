package juego;

import java.util.Random;
import java.awt.Color;
import java.util.LinkedList;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	private Personaje p;
	private LinkedList<Obstaculo> obstaculos;
	private LinkedList<Obstaculo> obstaculos1;
	private int limiteEnemigos=10;
	private int enemigosVivos=0;
	private Castillo castillo;
    private Enemigo[] enemigos= new Enemigo[10];
	/*private Obstaculo nuevoObstaculo;*/

	private Escritor t;

	private MostradorDeVida[] vidas = new MostradorDeVida[10];

	// Variables y métodos propios de cada grupo
	// ..
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
	

	
	
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(140,300,20,50);
		
		castillo= new Castillo(400, 500,100, 100);
	

		Obstaculo o = new Obstaculo(150,500,200,20); //obstaculo inferior izquierdo 
		Obstaculo o2 = new Obstaculo(150,400,200,20); //obstaculo superior izquierdo
		Obstaculo o3 = new Obstaculo(450,400,200,20); //obstaculo superior del medio
		Obstaculo o4 = new Obstaculo(600,500,300,20);//obstaculo inferior derecho
		Obstaculo o5= new Obstaculo(650,400, 120,20); //obstaculo superior derecho
		Obstaculo o7 = new Obstaculo(170,595,340,40);
		Obstaculo o8 = new Obstaculo(660,595,400,40);
		
		
		

		obstaculos=new LinkedList<Obstaculo>();
		obstaculos.add(o);
		obstaculos.add(o2);
		obstaculos.add(o3);
		obstaculos.add(o4);
		obstaculos.add(o5);
		
		obstaculos1=new LinkedList<Obstaculo>();
		obstaculos1.add(o7);
		obstaculos1.add(o8);



		//se crean las vidas
		for(int j = 0; j < p.getVida(); j++) {
			MostradorDeVida v = new MostradorDeVida(j*50+50, 50);
			vidas[j]=v;
		}

		//crea el texto
		t = new Escritor(325, 350, "Winer", "Loser");

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
		


	for (MostradorDeVida vida: this.vidas) {
		if (vida != null) {
			vida.dibujar(entorno);
		}
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
  //Generacion de los enemigos de forma aleatoria
      for (int i=0;i<enemigos.length;i++) {
    	  if(enemigos[i]==null && enemigosVivos<limiteEnemigos && !p.isGano()) {
    			Random randomEnemigos=new Random();
    			   int numeroAleatorio=randomEnemigos.nextInt(0,2);
    			   int posAleatoria=randomEnemigos.nextInt(550-300+1)+300;
    			   int posAleatoria2=randomEnemigos.nextInt(200- 100+1)+100;
    			   if(numeroAleatorio==0) {
    				   Enemigo e=new Enemigo(entorno.ancho()+posAleatoria2, posAleatoria, 40, 40,"izquierda");
    					
    				   enemigos[i]=e;

    			   }else {
    				   Enemigo e= new Enemigo(0-posAleatoria2, posAleatoria, 40, 40,"derecha");
    					
    				   enemigos[i]=e;
    			   }
    			   enemigosVivos++;
    	  }
      }
//    while(enemigosVivos<limiteEnemigos) {

//	
//		}
    // Dibujo de los enemigos y control de colisiones entre los obstacuos y el jugador
	for (int i=0;i<enemigos.length;i++) {
			Enemigo enemigo=enemigos[i];
		
		
			if(enemigo!=null) {
				
				if(p.getDisparo()!=null) {
					if(p.getDisparo().colisionaDisparoConEnemigo(enemigo)) {
						enemigo=null;
						enemigos[i]=enemigo;
						p.setDisparo(null);
						  enemigosVivos--;
						break;
					}
					
					
				}
		
				for(Obstaculo o :obstaculos) {
					if(enemigo.colisionaConObstaculo(o)) {
						enemigo=null;
						enemigos[i]=enemigo;
						enemigosVivos--;
						break;
					}
		
					
				}
				if(enemigo!=null) {
					enemigo.dibujar(entorno);
					if (enemigo.colisionaConElJugador(p)) {
						enemigo=null;
						enemigos[i]=enemigo;
			        	enemigosVivos--;
						vidas = convertirANUllLaVida(entorno, vidas);
			            
			        }else {
			        	if(enemigo.getDireccion().equals("izquierda")) {
							enemigo.moverIzquierda();
							if(enemigo.esDestruiblePorIzquierda(entorno)) {
								enemigo=null;
								enemigos[i]=enemigo;
								enemigosVivos--;
							
							}
						
						}
						else {
							enemigo.moverDerecha();		
							if(enemigo.esDestruibleDerecha(entorno)) {
								enemigo=null;
								enemigos[i]=enemigo;
								enemigosVivos--;
							}
							
						
						}
			        }
				}
			
				
			
				
		
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
    		a.setX(entorno.ancho()+r);
    		
    	}
    		
    }
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
    			b.setX(entorno.ancho());
	    	}	
    		
    }
	   
   castillo.dibujar(entorno);
   if(p.isGano()) {
   	
		t.dibujar('W', entorno);
	};
    //posicion del personaje en mitad de pantalla
    if(p.getX()>entorno.ancho()/2){

		p.setX((entorno.ancho()/2));
	
		

	}
 
    if(!p.isGano()) {
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
   
	

    	

    			   p.colisionaCastillo(castillo);
    			
  

	if (p.siElPersonajeTocaElBordeInferiorDeLaPantalla())//aqui puse que el personaje se teletransporde caundo se cae
	{
		vidas = convertirANUllLaVida(entorno, vidas);
	}
    }

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
	
	public static MostradorDeVida[] convertirANUllLaVida(Entorno e, MostradorDeVida[] v) {
		boolean yaSeQuito = false;
		for (int i = v.length-1; i > -1; i = i -1) {
			if (v[i]!=null && yaSeQuito == false) {
				yaSeQuito = true;
				v[i] = null;
			}
		}
		return v;
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
