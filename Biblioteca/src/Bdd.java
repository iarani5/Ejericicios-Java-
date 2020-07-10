
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bdd {

//****** A T R I B U T O S
	
    private Connection conexion;
    private Statement sentencia;
    private ResultSet datos;
    
//****** M E T O D O S

	// CONSTRUCTOR 
    public Bdd() throws SQLException, ClassNotFoundException{
    	crear();
    }

	// CREAR 
	public void crear() throws ClassNotFoundException, SQLException{
	    Class.forName("com.mysql.jdbc.Driver"); //si tira error para configurar el Driver => https://www.youtube.com/watch?v=VkwZci8JqlY 
	    conexion = DriverManager.getConnection ("jdbc:mysql://localhost/biblioteca","root","");
	    sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    sentencia.executeUpdate( "DROP TABLE IF EXISTS libro; " ); 
	    sentencia.executeUpdate(	
	    			" CREATE TABLE libro("  
	    		+		"id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+		"titulo VARCHAR(50) NOT NULL,"									 
				+		"autor VARCHAR(45) NOT NULL,"									 
				+		"cant_paginas INT(9) NOT NULL,"									 
				+		"ISBN INT(9) NOT NULL,"                                          
				+		"anio INT(9) NOT NULL,"                                          
				+		"editorial VARCHAR(45) NOT NULL,"								 
				+		"genero ENUM('ficcion','accion','suspenso','romantico','novela','fantasia') NOT NULL" 
				+	");"    
	    );
	}
	
	// CARGAR 
	public void cargar() throws ClassNotFoundException, SQLException{
	    sentencia.executeUpdate(
	    			"INSERT INTO libro (titulo,autor,cant_paginas,ISBN,anio,editorial,genero) "
	    		+   "VALUES ('Harry Potter 1', 'JK rowling', 420, 192038, 2004, 'Salamandra', 'fantasia'),"
	    		+   "       ('Harry Potter 2', 'JK rowling', 301, 100910, 2005, 'Interprete' , 'fantasia' ),"
	    		+   "       ('Crimen y castigo', 'Fiodor Dostoievski', 301, 182910, 1866, 'Interprete' , 'novela' ),"
	    		+   "       ('Un mundo feliz', 'Aldous Huxley', 230, 183928, 1932, 'Salamandra', 'novela'  ),"  
	    		+   "       ('Orgullo y prejuicio', 'Jane Austen', 324, 482832, 1813, 'Ateneo', 'novela'  )," 
	    		+   "       ('Matar un ruiseñor', 'Harper Lee', 230, 233928, 1956, 'Ateneo', 'novela'  );" 
	    );
	}
	
	// CONECTAR
	public void conectar() throws ClassNotFoundException, SQLException{
	    datos = sentencia.executeQuery("select * from libro");
	}
	
	// DESCONECTAR
    public void desconectar() throws SQLException{
    	datos.close();
    	sentencia.close();
    	conexion.close();
    }
    
    public ArrayList<Libro> getLibros() throws SQLException, ClassNotFoundException{
    	ArrayList<Libro> libros = new ArrayList<Libro>();
    	while (datos.next()){
    		libros.add( new Libro(datos.getString(1),datos.getString(2),datos.getString(3),datos.getString(4),datos.getString(5),datos.getString(6),datos.getString(7),datos.getString(8)));
    	}
    	return libros;
    }
	
    // ELIMINAR
    public void eliminar(String txt) throws ClassNotFoundException, SQLException{
	    sentencia.executeUpdate("DELETE FROM libro WHERE titulo='"+txt+"'; ");
	}
    
    //CREAR   
    public void crear(String titulo, String autor, String cant_paginas, String ISBN, String anio, String editorial, String genero) throws ClassNotFoundException, SQLException{
	    sentencia.executeUpdate(
	    			"INSERT INTO libro (titulo,autor,cant_paginas,ISBN,anio,editorial,genero) "
	    		+   "VALUES ('"+titulo+"','"+autor+"',"+cant_paginas+","+ISBN+","+anio+",'"+autor+"','"+genero+"'); "		
	    );
	}
    
    
    //MODIFICAR
    public void modificar(String id,String titulo, String autor, String cant_paginas, String ISBN, String anio, String editorial, String genero) throws ClassNotFoundException, SQLException{
	    sentencia.executeUpdate(
	    	"UPDATE libro SET titulo='"+titulo+"', autor='"+autor+"', cant_paginas="+cant_paginas+", ISBN="+ISBN+", anio="+anio+", editorial='"+editorial+"', genero='"+genero+"' WHERE id="+id+";"                            	
	    );
	}
}











