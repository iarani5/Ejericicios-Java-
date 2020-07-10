import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Supermercado{

	ArrayList <Producto> un_super;
	
	public Supermercado() throws IOException, FileNotFoundException {
		String v[];
		un_super = new ArrayList<Producto>();
		
		FileReader f = new FileReader("src/archivo.txt");//Me trae el archivo
		BufferedReader bf= new BufferedReader(f);//Crea el lector de archivo
		
		String linea = bf.readLine();
		while(linea!=null){
			v=linea.split(";"); //separa el String
			Producto p = new Producto(v);// v la guardo en producto
			un_super.add(p);// se guarda en chino
			linea=bf.readLine();// pasa a leer la siguiente linea
			// vuelve a empezar
		}
		bf.close();
	}
	
	public void leer_un_super(){
		for(int i=0;i<un_super.size();i++){
			System.out.print(un_super.get(i));
		}
	}

}
