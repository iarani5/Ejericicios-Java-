
public class Cuadrado extends Rect {
    double lado;
	public Cuadrado(String nombre, Punto p, double l) {
		super(nombre, p, l, l);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", lado: " + Double.toString(lado);
	}
}
