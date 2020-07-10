public class Circulo extends Figura {
    double radio;
	public Circulo(String nombre, Punto p, double r) {
		super(nombre,p);
		radio = r;
	}
	@Override
	public String toString() {
		return super.toString() + ", radio: " + Double.toString(radio);
	}
	
	@Override
	public double area() {
		return Math.round(Math.PI * radio * radio);
	}
	@Override
	public double perim() {
		return Math.round(Math.PI * radio);
	}

}
