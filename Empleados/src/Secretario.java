

public class Secretario extends Empleado{
	int incremento_anual=5;
	String despacho;
	int fax;
	
	//constructor
	public Secretario(String n, String a, int dni, String direc, int tel, double sal, int anios_ant, String des, int fx) {
		super(n,a,dni,direc,tel,sal);
		anios_antiguedad=anios_ant;
		despacho=des;
		fax=fx;
	}
	
	//imprimir
	@Override
	public void imprimir() {
		super.imprimir();
		System.out.println("Despacho: "+despacho);
		System.out.println("Fax: "+fax);
		System.out.println("Puesto en la empresa: Secretario");
	}
	
	@Override
	public void incrementar_salario(double x) {
		salario=((incremento_anual*salario)/100)+salario;
	}
	
}
