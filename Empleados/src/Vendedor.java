import java.util.ArrayList;

public class Vendedor extends Empleado{
	Coche auto;
	int movil;
	double area_venta;
	double porcentaje_por_venta;
	int incremento_anual=10;
	ArrayList<String> clientes = new ArrayList<String>();
	
	//contructor
	public Vendedor(String n, String a, int dni, String direc, int tel, double sal, Coche coche, int cel, double av, double ppv)  {
		super(n,a,dni,direc,tel,sal);
		auto = coche;
		movil = cel;
		area_venta = av;
		porcentaje_por_venta = ppv;
	}
	
	//imprimir
	@Override
	public void imprimir() {
		super.imprimir();
		System.out.println("Puesto en la empresa: Vendedor");
		System.out.println("Clientes: "+clientes);

	}
	
	//dar de alta nuevo cliente
	public void nuevo_cliente(String nombre, String apellido) {
		clientes.add(nombre+" "+apellido);
	}
	
	//dar de baja un cliente
	public void baja_cliente(String nombre, String apellido) {
		for(int i=0;i<clientes.size();i++) {
			if(clientes.get(i).equals(nombre+" "+apellido)) {
				clientes.remove(nombre+" "+apellido);
			}
		}
	}
	
	//cambiar coche
	public void cambiar_coche(Coche coche) {
		auto=coche;
	}
	
	@Override
	public void incrementar_salario(double comisiones) {
		salario=((incremento_anual*salario)/100)+salario+comisiones;
	}
}
