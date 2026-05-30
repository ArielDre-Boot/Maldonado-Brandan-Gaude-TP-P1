package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class ImagenFondo  {
		double x;
		double y;
		private Image imagen;
		
		public ImagenFondo(double x, double y) {
			this.x=x;
			this.y=y;
			try {
				this.imagen= javax.imageio.ImageIO.read(new java.io.File("juego/fondo.jpg"));
			}
			catch (Exception e){
				System.out.println("No se puede cargar la imagen "+e.getMessage());	
			}
		}
		
		public void dibujarImagen(Entorno entorno) {
			entorno.dibujarImagen(imagen, x, y, 0, 0.5);
		}
		
		
		
	}


