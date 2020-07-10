class Punto {
	int x,y;

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
public abstract class Figura {
    String nombre;
    Punto p;
	public Figura(String nombre, Punto p) {
		this.nombre = nombre;
		this.p = p;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Punto getP() {
		return p;
	}
	public void setP(Punto p) {
		this.p = p;
	}

	public String toString() {
		return nombre + p ;
	}
	public abstract double area ();
	public abstract double perim ();
	
 }