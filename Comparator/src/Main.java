import java.util.Arrays;


public class Main {
     public static void main(String[] args) {
		Rect uno = new Rect("rectangulo 1: ",new Punto(1,1),10,20);
		Rect dos = new Rect("rectangulo 2: ",new Punto(2,2),30,40);
		Circulo tres = new Circulo("circulo 1: ",new Punto(0,0), 20);
		Circulo cuatro = new Circulo("circulo 2: ",new Punto(1,1), 10);
		Figura lista [] = { uno, dos, tres, cuatro};
//		Arrays.sort(lista, new ComparaArea());
//		for (int i = 0; i < lista.length; i++)
//		{
//			System.out.println(lista[i] + ", area: " + lista[i].area());
//		}
//		Arrays.sort(lista, new ComparaPerim());
//		for (int i = 0; i < lista.length; i++)
//		{
//			System.out.println(lista[i] + ", perimetro: " + lista[i].perim());
//		}
		
		Arrays.sort(lista, new ComparaNombre());
		
		for (int i = 0; i < lista.length; i++)
		{
			System.out.println(lista[i] + ", perimetro: " + lista[i].perim());
		}
	}
}
