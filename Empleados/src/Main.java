
public class Main {

	public static void main(String[] args) {
		
		/* SECRETARIO */
		Secretario susana = new Secretario("Susana", "Martinez", 40449955, "Superi 2233", 43206059, 123.400, 12,"Milano 112",45432281);
		susana.incrementar_salario(0);

		/* VENDEDOR */
		Vendedor el_pelado = new Vendedor("El pelado", "Cobra", 40449911, "Melian 2233", 43202259, 123.400,new Coche(1111,"Fiat","600"),1530392948,300.30,17.30);
		Vendedor pablito = new Vendedor("Pablito", "Martinez", 40449911, "Melian 2233", 43202259, 123.400,new Coche(1111,"Fiat","600"),1530392948,300.30,17.30);
		pablito.cambiar_supervisor(susana);
		pablito.incrementar_salario(34.2);
		el_pelado.cambiar_supervisor(susana);
		pablito.incrementar_salario(20);
		
		pablito.nuevo_cliente("Marcelo","Casas");
		pablito.nuevo_cliente("Daniel","Wok");
		pablito.nuevo_cliente("Braulio","Carlo");
		pablito.baja_cliente("Daniel","Wok");
		
		/* JEFE DE ZONA */
		Jefe_Zona el_jefecito = new Jefe_Zona("EL rey","Goma",110022030,"Echandia 112", 483729034, 123.400,"Cochabamba 1012");
		el_jefecito.cambiar_secretario(susana);
		el_jefecito.cambiar_supervisor(el_jefecito); //no tiene supervisor porque es el fucking jefe
		susana.cambiar_supervisor(el_jefecito);
		el_jefecito.incrementar_salario(0);

		el_jefecito.cambiar_coche(new Coche(1111,"Fiat","600"));
		el_jefecito.nuevo_vendedor(pablito);
		el_jefecito.nuevo_vendedor(el_pelado);
		el_jefecito.baja_vendedor(pablito);

		System.out.println("\n-----------------SECRETARIOS");
		susana.imprimir();
		System.out.println("\n-----------------VENDEDORES");
		pablito.imprimir();
		el_pelado.imprimir();
		System.out.println("\n-----------------JEFES DE ZONA");
		el_jefecito.imprimir();
	

	}

}
