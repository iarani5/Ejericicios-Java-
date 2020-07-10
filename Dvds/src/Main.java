
public class Main {

	public static void main(String[] args) {
		Catalogo catalogo = new Catalogo();
		catalogo.generar_listado_cds();
		catalogo.generar_listado_dvds();
		catalogo.listadoCompleto();
		catalogo.cantidadDiscos();
		catalogo.listadoDisponibles();
		catalogo.listadoFiltrarPorTiempo();
		catalogo.listadoOrdenado();
	}

}
