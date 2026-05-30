package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class ImagenFondo  {
		private Image imagen;
		double x;
		double y;
		
		public ImagenFondo(double x, double y) {
			this.imagen=Herramientas.cargarImagen("C:\\Users\\estudiante\\Pictures\\Saved Pictures");
			this.x=x;
			this.y=y;
		}
		
		public void dibujarImagen(Entorno entorno) {
			entorno.dibujarImagen(imagen, x, y, 0, 0.5);
		}
		
		
		
	}


