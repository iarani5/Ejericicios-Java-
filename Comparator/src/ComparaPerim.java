import java.util.Comparator;


public class ComparaPerim implements Comparator<Figura>{

	public int compare(Figura a, Figura b) {
		double s = a.perim();
		double t = b.perim();
		return (int)(s-t);
	}

}
