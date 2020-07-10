import java.util.Comparator;


public class ComparaNombre implements Comparator<Figura>{

	public int compare(Figura a, Figura b) {
		return a.getNombre().compareTo(b.getNombre());
	}

}
