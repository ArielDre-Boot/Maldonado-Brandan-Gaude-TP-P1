package juego;

import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje p;

	private Obstaculo[] obstaculosSuperiores= new Obstaculo[8];
	private Obstaculo[] obstaculosInferiores=new Obstaculo[9];
	private Obstaculo[] obstaculosBase= new Obstaculo[11];



	private Castillo castillo;
    
	private Enemigo[] enemigos= new Enemigo[5];

	private int enemigosVivos=0;
    private Enemigo2[] enemigos2=new Enemigo2[1];

	private int enemigosVivos2=0;
	private Escritor t;
	private MostradorDeVida[] vidas = new MostradorDeVida[10];

	private double x;
	private double y;
	private double angulo;
	private double escala;
	private Image imagenFondo;

	private Image imagenFondoTermino = null;


	// Variables y métodos propios de cada grupo
	// ..
	Juego()
	{ 
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Super Elizabeth Sis", 800, 600);


	
	
		// Inicializar lo que haga falta para el juego
		// ...
		p = new Personaje(140,300,20,50);
		if(!p.isPerdio() && !p.isGano()) {
			Herramientas.play("juego/musica.wav");
		}


		
		castillo= new Castillo(400, 525,100, 100);
	
		imagenFondo=Herramientas.cargarImagen("juego/fondo1.jpg");
		



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


{ if (imagenFondoTermino == null) {
	
	entorno.dibujarImagen(imagenFondo,entorno.ancho()/2,entorno.alto()/2,0,1);
	for (MostradorDeVida vida: this.vidas) {
		if (vida!= null) {

			vida.dibujar(entorno);
		}
	}
	

	
	/*imagen.dibujarImagen(entorno);*/
    p.dibujar(entorno);
    
    ///Valores booleanos usados para los limites
    boolean bloqueaIzquierda = false;
    boolean bloqueaDerecha = false;
    boolean bloqueaAbajo = false;
    boolean bloqueaArriba = false;
	
  ///Limites de colisión de la linea de los niveles(obstaculos) superiores
    for (Obstaculo obstaculo : obstaculosSuperiores) {
    	if(obstaculo!=null) {
    		
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
    }
   
    ///Limites de colisión de la linea de niveles inferiores 
    for (Obstaculo o: obstaculosInferiores) {
    	if(o!=null) {
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
    }
    
    //Limites de las lineas de niveles de la base del juego
    for(Obstaculo obs: obstaculosBase) {
    	if(obs!=null) {
    		obs.dibujar(entorno);
    		if(p.colisionaPorArriba(obs)) {
    			bloqueaArriba=true;
    		}
    		if(p.colisionaPorDebajo(obs)) {
    			bloqueaAbajo=true;
    		}
    		if(p.colisionaPorDerecha(obs)) {
    			bloqueaDerecha=true;
    		}
    		if(p.colisionaPorIzquierda(obs)) {
    			bloqueaIzquierda=true;
    		}
    	}
    }  
    
    for(Enemigo2 enemigo2:enemigos2) {
    	
    	if(enemigo2!=null &&enemigo2.getDisparo()!=null) {
     	controlDelProyectilEnemigo(enemigo2,entorno);
    }
    }
  //Generacion de los enemigos de forma aleatoria
      for (int i=0;i<enemigos.length;i++) {
    	  if(enemigos[i]==null && enemigosVivos<enemigos.length && !p.isGano() && !p.isPerdio()) {
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
      for (int i=0;i<enemigos2.length;i++) {
    
    	  if(enemigos2[i]==null && enemigosVivos2<enemigos2.length  && !p.isGano() && !p.isPerdio()) {
    			Random randomEnemigos=new Random();
    				int direccion=randomEnemigos.nextInt(0,2);
    			   int posAleatoria=randomEnemigos.nextInt(1000-800+1)+800;
    			   int posAleatoria2=randomEnemigos.nextInt(entorno.ancho()- 0+1)+0;
    			   	if(direccion==0) {
    			   	 Enemigo2 e=new Enemigo2(posAleatoria2, 0-posAleatoria, 40, 40,"derecha");
    			     enemigos2[i]=e;
    			   	}else {
    			    	 Enemigo2 e=new Enemigo2(posAleatoria2, 0-posAleatoria, 40, 40,"izquierda");
    			    	   enemigos2[i]=e;
    			   	}
    				  
    					
    				
    				
    			
    			   enemigosVivos2++;
 
      }
      }
    	 
      for (int i = 0; i < enemigos2.length; i++) {

    	    Enemigo2 enemigo = enemigos2[i];

    	    if (enemigo != null && !p.isGano() && !p.isPerdio()) {
    	    
    	    	
    	        // Colisión con disparo
    	        if (p.getDisparo() != null &&
    	            p.getDisparo().colisionaDisparoConEnemigo2(enemigo)) {
    	        	Herramientas.play("juego/explocion.wav");
    	            enemigos2[i] = null;
    	            p.setDisparo(null);
    	            enemigosVivos2--;
    	            continue;
    	        }

    	        // Colisión con barrera
    	        if (enemigo.colisionaConBarrera(p)) {

    	            enemigos2[i] = null;
    	            enemigosVivos2--;
    	            Herramientas.play("juego/explocion.wav");
    	            continue;
    	        }

    	      
    	        // Dibujar enemigo
    	        enemigo.dibujar(entorno);

    	        // Colisión con jugador
    	        if (enemigo.colisionaConElJugador(p) ) {
    	        
    	            enemigos2[i] = null;
    	            enemigosVivos2--;
    	            vidas = convertirANUllLaVida(entorno, vidas, p);
    	       
    	            continue;
    	        }

    	        // Movimiento
    	        if (enemigo.getY() <= 50) {
    	        	
    	            enemigo.moverAbajo();
    	           
    	        }else {
    	       
    	        	if(enemigo.getDisparo()==null) {
    	        		Herramientas.play("juego/disparo.wav");
        	    		enemigo.disparo(p);
        	    		enemigo.setDisparoTocoJugador(false);
        	    
        	    	}
    	        	 if(enemigo.getDisparo()!=null) {
         	    		enemigo.getDisparo().dibujar(entorno);
         	    		enemigo.getDisparo().mover();
if(enemigo.disparoColisionaJugador(p)){
    Herramientas.play("juego/explocion.wav");
	vidas=convertirANUllLaVida(entorno, vidas, p);
}
         	    	}
    	        	if(enemigo.getDireccion().equals("derecha")) {
    	        		enemigo.moverDerecha();
    	        		if(enemigo.cambiarDireccionDerecha(entorno)) {
    	        			enemigo.setDireccion("izquierda");
    	        		}
    	        	}else {
    	        		
    	        		enemigo.moverIzquierda();
    	        		if(	enemigo.cambiarDireccionIzquierda(entorno)) {
    	        			enemigo.setDireccion("derecha");
    	        		}
    	        	
    	        	}
    	        }
    	    }
    	}
//    while(enemigosVivos<limiteEnemigos) {

//	
//		}
    // Dibujo de los enemigos y control de colisiones entre los obstaculos y el jugador
	for (int i=0;i<enemigos.length;i++) {
			Enemigo enemigo=enemigos[i];
		
		
			if(enemigo!=null) {
				
				if(p.getDisparo()!=null) {
					if(p.getDisparo().colisionaDisparoConEnemigo(enemigo)) {
						enemigo=null;
						enemigos[i]=enemigo;
						p.setDisparo(null);
						  enemigosVivos--;
						  Herramientas.play("juego/explocion.wav");
					}
					
					
				}
			
				for(Obstaculo o :obstaculosSuperiores) {
					if(enemigo!=null && o!=null && enemigo.colisionaConObstaculo(o)) {
						enemigo=null;
						enemigos[i]=enemigo;
						enemigosVivos--;
				
					}
				}
				for(Obstaculo o1 :obstaculosInferiores) {
					if(enemigo!=null &&o1!=null && enemigo.colisionaConObstaculo(o1)) {
						enemigo=null;
						enemigos[i]=enemigo;
						enemigosVivos--;
				
					}
				}

				if (enemigo != null && enemigo.colisionaConBarrera(p)) {
					enemigo=null;
					enemigos[i]=enemigo;
					enemigosVivos--;
					  Herramientas.play("juego/explocion.wav");
				}
				
				if(enemigo!=null) {
					enemigo.dibujar(entorno);
					if (enemigo.colisionaConElJugador(p)) {
						enemigo=null;
						enemigos[i]=enemigo;
			        	enemigosVivos--;
			        	  Herramientas.play("juego/explocion.wav");
						vidas = convertirANUllLaVida(entorno, vidas, p);
			            
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
	
    //Repetición de la linea superior de niveles
	if(!p.isPerdio()) {
		detectaElMovimiento(entorno,p);
		int valorY=400; 
		int anchoObstaculo= 120;
		int altoObstaculo=20;
		for(int i=0; i<obstaculosSuperiores.length; i++) {
			Random valoresRandoms= new Random(); 
			int numerosRan= valoresRandoms.nextInt(501-200+1)+200;
			int numerosRan1= valoresRandoms.nextInt(200-100+1)+100;
			if(obstaculosSuperiores[i]==null && !p.getEnMovimiento()) { 
				Obstaculo o= new Obstaculo (500*i+numerosRan, valorY, anchoObstaculo, altoObstaculo); 
				obstaculosSuperiores[i]=o; } 
			if (obstaculosSuperiores[i]!=null && p.getEnMovimiento()){ 
				obstaculosSuperiores[i].setX(obstaculosSuperiores[i].getX()-2); }
			obstaculosSuperiores[i].dibujar(entorno);
		}
	    
	    /*for(Obstaculo a: obstaculos) {
	    		if(p.getEnMovimiento()) {
	    			a.setX(a.getX()-2);}
	    		if(a.bordeDerecho()<0) {
	    			Random ran=new Random();
	    			int r= ran.nextInt(entorno.alto());
	    			a.setX(entorno.ancho()+r/2);
	    	}
	    		
	    }*/
	 
	    //Repetición de la linea inferior de niveles
	    detectaElMovimiento(entorno,p); 
	    int anchoObstaculo1= 120;
		for(int i=0; i<obstaculosInferiores.length; i++) {
			int valorY1=500;
			Random valoresRandoms= new Random(); 
			int numerosRan= valoresRandoms.nextInt(401-200+1)+200;
			int numerosRan1= valoresRandoms.nextInt(150-50+1)+50;
			if(obstaculosInferiores[i]==null &&  !p.getEnMovimiento()) { 
				Obstaculo o= new Obstaculo (400*i+numerosRan, valorY1, anchoObstaculo1, altoObstaculo); 
				obstaculosInferiores[i]=o; } 
			if (obstaculosInferiores[i]!=null && p.getEnMovimiento()){ 
				obstaculosInferiores[i].setX(obstaculosInferiores[i].getX()-2); 
				} 
			obstaculosInferiores[i].dibujar(entorno);
		}
		//Repeticion de la linea de las bases 
		detectaElMovimiento(entorno,p);
		for(int i=0; i<obstaculosBase.length; i++) {
			Random ran= new Random();
			int numerosRandoms= ran.nextInt(50-20+1)+20;
			if(obstaculosBase[i]==null && !p.getEnMovimiento()) {
				Obstaculo o= new Obstaculo(400*i,595,290,40);
				obstaculosBase[i]=o;
			}
			if(obstaculosBase[i]!=null && p.getEnMovimiento()) {
				obstaculosBase[i].setX(obstaculosBase[i].getX()-2);
			}
			if (i == obstaculosBase.length -1) {
				castillo.setX(obstaculosBase[i].getX());
				//castillo.setY(obstaculosBase[i].getY() + castillo.bordeInferior());
			}
			obstaculosBase[i].dibujarBase(entorno);

		}

		
    
   
    castillo.dibujar(entorno);
	if (p.isSeMueve() == true) {
		castillo.setX(castillo.getX() - 1);
	}
   if(p.isGano()) {
   	
		t.dibujar('W', entorno);
	};
 
    //posicion del personaje en mitad de pantalla
    if(p.getX()>entorno.ancho()/2){

		p.setX((entorno.ancho()/2));
	
		

	}

    if(!p.isGano() && !p.isPerdio()) {
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
   
	
				controlDelBarrera(entorno, p);
    	

    			p.colisionaCastillo(castillo);
    			
	}
 
	if (p.siElPersonajeTocaElBordeInferiorDeLaPantalla())//aqui puse que el personaje se teletransporde caundo se cae
	{
		vidas = convertirANUllLaVida(entorno, vidas, p);
	}
	}
	if (p.isGano() || p.isPerdio()) {
		Image icono= Herramientas.cargarImagen("juego/Corazon.jpg");
        this.imagenFondoTermino = icono;
	}
}
	
	else {
			entorno.dibujarImagen(imagenFondoTermino, 400, 300, 0, 0.08);
			if (p.isPerdio()) {
				t.dibujar('L', entorno);
			}
			if (p.isGano()) {

				t.dibujar('W', entorno);
			}
			if (entorno.sePresiono('r') || entorno.sePresiono('R')) {
				sumarVida(entorno, vidas, p, -1);
				enemigos = eliminarTodosLosEnemigos(entorno, enemigos);
				enemigosVivos -= enemigos.length;
				p.setX(140);
				p.setY(300);
				imagenFondoTermino = null;
				p.setGano(false);
				p.setPerdio(false);
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
    	/*if(c.getEnPantalla()==true){
    		for(Obstaculo obs: o) {
    			obs.setSeMueven(false);
    		}
    		for (Obstaculo e: obstaculos) {
    			e.setSeMueven(false);
    		}
    	}*/
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
	
		}
		//colision del lado derecho
		if (p.getDisparo()!=null && p.getDisparo().getX()>entorno.ancho()) {
			p.setDisparo(null);
	
		}
			//colision por la parte superior
		if (p.getDisparo()!=null && p.getDisparo().getY()<0) {
			p.setDisparo(null);
		
		}
			//colision por la parte inferior
		if (p.getDisparo()!=null && p.getDisparo().getY()>entorno.alto()) {
			p.setDisparo(null);
		
		}
			
	}
	public static void controlDelProyectilEnemigo(Enemigo2 e, Entorno entorno) {
		
		
		
		

		
		
		//colicsiones del proyectil
		if (e.getDisparo()!=null && e.getDisparo().getX() < 0) {
			e.setDisparo(null);
	
		}
		//colision del lado derecho
		if (e.getDisparo()!=null && e.getDisparo().getX()>entorno.ancho()) {
			e.setDisparo(null);
	
		}
			//colision por la parte superior
		if (e.getDisparo()!=null && e.getDisparo().getY()<0) {
			e.setDisparo(null);
		
		}
			//colision por la parte inferior
		if (e.getDisparo()!=null && e.getDisparo().getY()>entorno.alto()) {
			e.setDisparo(null);
		
		}
			
	}

	public static void controlDelBarrera(Entorno e, Personaje p) {
		if (p.getBarrera() != null) {
			p.getBarrera().dibujar(e);
			p.getBarrera().movimiento(p.getX(), p.getY());
			p.getBarrera().actualizarTiempoDeAparicion(e, e.tiempo());
		}
		if (p.getBarrera() != null && p.getBarrera().getTiempoActual() >= p.getBarrera().getTiempoDeDesaparicion()) {
			p.setBarrera(null);;
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
		Herramientas.play("juego/retroJump.wav");
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
    	Herramientas.play("juego/disparo.wav");
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

	public static MostradorDeVida[] convertirANUllLaVida(Entorno e, MostradorDeVida[] v, Personaje p) {
		boolean yaSeQuito = false;
		for (int i = v.length-1; i > -1; i = i -1) {
			if (v[i]!=null && yaSeQuito == false) {
				yaSeQuito = true;
				v[i] = null;
			}
			if (v[0] == null) {
				Herramientas.play("juego/lose.wav");
				p.setPerdio(true);
			}
		}
		return v;
	}

 	public static MostradorDeVida[] sumarVida(Entorno e, MostradorDeVida[] v, Personaje p, int vidaSumada) {
		boolean yaSeAgrego = false;
		if (vidaSumada == -1) {
			for (int i = 0; i < p.getVida(); i++) {
				MostradorDeVida vida = new MostradorDeVida(i * 50 + 50, 50);
				v[i] = vida;
			}
		}

		if (vidaSumada > 0) {
			for (int i = 0; i < p.getVida(); i++) {
				if (v[i] == null) {
					MostradorDeVida vida = new MostradorDeVida(i * 50 + 50, 50);
					v[i] = vida;
				}
			}
		}

		return v;
	}

	public static Enemigo[] eliminarTodosLosEnemigos(Entorno e, Enemigo[] enemigos) {
		for (int i = 0; i < enemigos.length; i++) {
			if (enemigos[i] != null) {
				enemigos[i] = null;
			}
		}
		return enemigos;
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

