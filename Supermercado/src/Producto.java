
public class Producto {
	String tipo;
	int codigo;
	String desc;
	double precio;
	int cant;
	
	public Producto(String []v){
		tipo=v[0];
		codigo=Integer.parseInt(v[1]);
		desc=v[2];
		precio=Double.parseDouble(v[3]);
		cant=Integer.parseInt(v[4]);
	}
	
	public String toString(){
		return "\nTipo: "+tipo+"   Codigo: "+codigo+"   Descripcion: "+desc+"   Precio: $"+precio+"   Cantidad: "+cant+"\n";
	}

	public void sumar_cantidad(int n) {
		cant+=n;
	}
	public int restar_cantidad(int n) {
		return	cant-=n;
	}
	
	
}
