
public abstract class Empleado {
	String nombre;
	String apellido;
	int DNI;
	String direccion;
	int anios_antiguedad;
	int telefono;
	double salario;
	Empleado supervisor;
	
	
	//constructor
	public Empleado(String n, String a, int dni, String direc, int tel, double sal) {
		nombre=n;
		apellido=a;
		DNI=dni;
		direccion=direc;
		telefono=tel;
		salario=sal;
	}
	
	//imprimir 
	public void imprimir() {
		System.out.println(
			"\nNombre: "+nombre+
			"\nApellido: "+apellido+
			"\nDNI: "+DNI+
			"\nDireccion: "+direccion+
			"\nAnios Antiguedad: "+anios_antiguedad+
			"\nTelefono: "+telefono+
			"\nSalario: $"+salario+
			"\nSupervisor: "+supervisor.nombre+" "+supervisor.apellido
		);
	}
	
	//cambiar supervisor
	public void cambiar_supervisor(Empleado sup){
		supervisor=sup;
	}
	
	//incrementar salario
	public abstract void incrementar_salario(double x);
	
	
}
