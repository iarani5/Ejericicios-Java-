import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Biblioteca {
	ArrayList<Libro> libros = new ArrayList<Libro>();
	
	public Biblioteca(){
		
	}
	
	public void cargar() throws ClassNotFoundException, SQLException, FileNotFoundException, NullPointerException, IOException{
		new Modal();

	}
	
}
