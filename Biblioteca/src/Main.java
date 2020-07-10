import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main (String[] args)  throws NullPointerException, IOException, FileNotFoundException, ClassNotFoundException, SQLException{
		Biblioteca biblioteca = new Biblioteca();
		biblioteca.cargar();
	}

}
