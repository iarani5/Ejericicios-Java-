import java.util.Comparator;


public class ComparaArea implements Comparator<Figura>{

	public int compare(Figura a, Figura b) {
		double s = a.area();
		double p = b.area();
		return Double.compare(s, p);
	}
	
}
