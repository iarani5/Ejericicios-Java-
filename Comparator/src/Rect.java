
public class Rect extends Figura {
    double ancho, alto;
	public Rect(String nombre, Punto p, double a, double h) {
		super(nombre,p);
		ancho = a;
		alto = h;
	}
	@Override
	public String toString() {
		return super.toString() + ", ancho: " + Double.toString(ancho) + ", alto: " + Double.toString(alto);
	}
	
	@Override
	public double area() {
		return ancho*alto;
	}
	@Override
	public double perim() {
		return 2*ancho+2*alto;
	}

}
