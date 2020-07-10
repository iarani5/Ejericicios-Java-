import java.util.ArrayList;

public class Jefe_Zona extends Empleado{
	String despacho;
	Secretario secretario;
	ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	Coche auto;
	int incremento_anual=20;
	
	
	//contructor
		public Jefe_Zona(String n, String a, int dni, String direc, int tel, double sal, String desp)  {
			super(n,a,dni,direc,tel,sal);
			despacho=desp;
		}
		
	//imprimir 
	@Override
	public void imprimir(){
		super.imprimir();
		System.out.println("Puesto en la empresa: Jefe de Zona");
		System.out.println("Vendedores: ");
		for(int i=0;i<vendedores.size();i++) {
			System.out.println(vendedores.get(i).nombre);
		}

	}
	
	//cambiar de secretario
	public void cambiar_secretario(Secretario secr){
		secretario = secr;
	}

	//cambiar de coche
	public void cambiar_coche(Coche aut){
		auto=aut;
	}
	
	//dar de alta nuevo  vendedor
	public void nuevo_vendedor(Vendedor vend) {
		vendedores.add(vend);
	}
		
	//dar de baja un  vendedor
	public void baja_vendedor(Vendedor vend) {
		for(int i=0;i<vendedores.size();i++) {
			if(vendedores.get(i).equals(vend)) {
				vendedores.remove(vend);
			}
		}
	}
	
	@Override
	public void incrementar_salario(double x) {
		salario=((incremento_anual*salario)/100)+salario;
	}
	
}
