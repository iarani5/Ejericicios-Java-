import javax.swing.JOptionPane;
import java.util.*;


public class Catalogo {
	private ArrayList<DVD> listado_dvds = new ArrayList<DVD>();
	private ArrayList<CD> listado_cds = new ArrayList<CD>();
	private String sub_indice;
	
	public Catalogo(){
		
	}
	public String getSub_indice() {
		return sub_indice;
	}
	public void setSub_indice(String sub_indice) {
		this.sub_indice = sub_indice;
	}
	public ArrayList<DVD> getListado_dvds() {
		return listado_dvds;
	}
	public void setListado_dvds(ArrayList<DVD> listado_dvds) {
		this.listado_dvds = listado_dvds;
	}
	public ArrayList<CD> getListado_cds() {
		return listado_cds;
	}
	public void setListado_cds(ArrayList<CD> listado_cds) {
		this.listado_cds = listado_cds;
	}
	
	public void generar_listado_cds(){		
		CD cd = new CD();
		cd.setTitulo("ZZ Album 1");
		cd.setTiempo(2200);
		cd.setDisponible(true);
		cd.setInformacion("Aca un comentario del album 1");
		cd.setGenero("Cumbia Villera");
		cd.setInterprete("Iara");
		cd.setCantidad_temas(12);
		this.listado_cds.add(cd);
		
		CD cd2 = new CD();
		cd2.setTitulo("FF Album 2");
		cd2.setTiempo(1000);
		cd2.setDisponible(false);
		cd2.setInformacion("Aca un comentario del album 2");
		cd2.setGenero("Funk");
		cd2.setInterprete("Marta");
		cd2.setCantidad_temas(9);
		this.listado_cds.add(cd2);
		
		 int choice = 0;
		 choice = JOptionPane.showConfirmDialog(null, "Cargar CD?", "titulo", JOptionPane.YES_NO_OPTION);
		 if(choice == JOptionPane.YES_OPTION) {
			 do{
				listado_cds.add(new CD());
				listado_cds.get(listado_cds.size()-1).setTitulo(JOptionPane.showInputDialog("CD Titulo:"));
				listado_cds.get(listado_cds.size()-1).setTiempo(Integer.parseInt(JOptionPane.showInputDialog("CD Tiempo:")));
				listado_cds.get(listado_cds.size()-1).setDisponible(Boolean.parseBoolean(JOptionPane.showInputDialog("CD Disponible (true/false):")));
				listado_cds.get(listado_cds.size()-1).setInformacion(JOptionPane.showInputDialog("CD Informacion:"));
				listado_cds.get(listado_cds.size()-1).setGenero(JOptionPane.showInputDialog("CD Genero:"));
				listado_cds.get(listado_cds.size()-1).setInterprete(JOptionPane.showInputDialog("CD Interprete:"));
				listado_cds.get(listado_cds.size()-1).setCantidad_temas(Integer.parseInt(JOptionPane.showInputDialog("CD Cantidad de temas:")));
	
				choice = JOptionPane.showConfirmDialog(null, "Cargar otro CD?", "titulo", JOptionPane.YES_NO_OPTION);
			 } while(choice == JOptionPane.YES_OPTION);
		 }
	}

	public void generar_listado_dvds(){
		DVD dvd = new DVD();
		dvd.setTitulo("SS peli 1");
		dvd.setTiempo(2200);
		dvd.setDisponible(true);
		dvd.setInformacion("No asusta");
		dvd.setGenero("Terror");
		dvd.setDirector("Merluza");
		this.listado_dvds.add(dvd);
		
		DVD dvd2 = new DVD();
		dvd2.setTitulo("CC peli 2");
		dvd2.setTiempo(1000);
		dvd2.setDisponible(true);
		dvd2.setInformacion("Medio pedorra");
		dvd2.setGenero("Thriller");
		dvd2.setDirector("Hakuna");
		this.listado_dvds.add(dvd2);
		
		 int choice = 0;
		 choice = JOptionPane.showConfirmDialog(null, "Cargar DVD?", "titulo", JOptionPane.YES_NO_OPTION);
		 if(choice == JOptionPane.YES_OPTION) {
			 do{
				listado_dvds.add(new DVD());
				listado_dvds.get(listado_dvds.size()-1).setTitulo(JOptionPane.showInputDialog("DVD Titulo:"));
				listado_dvds.get(listado_dvds.size()-1).setTiempo(Integer.parseInt(JOptionPane.showInputDialog("DVD Tiempo:")));
				listado_dvds.get(listado_dvds.size()-1).setDisponible(Boolean.parseBoolean(JOptionPane.showInputDialog("DVD Disponible (true/false):")));
				listado_dvds.get(listado_dvds.size()-1).setInformacion(JOptionPane.showInputDialog("DVD Informacion:"));
				listado_dvds.get(listado_dvds.size()-1).setGenero(JOptionPane.showInputDialog("DVD Genero:"));
				listado_dvds.get(listado_dvds.size()-1).setDirector(JOptionPane.showInputDialog("DVD Director:"));

				choice = JOptionPane.showConfirmDialog(null, "Cargar otro DVD?", "titulo", JOptionPane.YES_NO_OPTION);
			 } while(choice == JOptionPane.YES_OPTION);
		 }
	}
	
	public void listadoCompleto(){
		System.out.print("\n\n-------------------- Listado Completo: --------------------");
		
			System.out.print("\n\n-------- CDS\n");
			for(int i=0;i<listado_cds.size();i++){
					System.out.print("\nTitulo: "+this.listado_cds.get(i).getTitulo());
					System.out.print("\nTiempo:"+this.listado_cds.get(i).getTiempo());
					System.out.print("\nDisponible: "+this.listado_cds.get(i).isDisponible());
					System.out.print("\nInformacion: "+this.listado_cds.get(i).getInformacion());
					System.out.print("\nGenero: "+this.listado_cds.get(i).getGenero());
					System.out.print("\nInterprete: "+this.listado_cds.get(i).getInterprete());
					System.out.print("\nCantidad de temas: "+this.listado_cds.get(i).getCantidad_temas());
					System.out.print("\n");
			}
			
			System.out.print("\n\n-------- DVDS\n");
			for(int i=0;i<listado_dvds.size();i++){
				System.out.print("\nTitulo: "+this.listado_dvds.get(i).getTitulo());
				System.out.print("\nTiempo:"+this.listado_dvds.get(i).getTiempo());
				System.out.print("\nDisponible: "+this.listado_dvds.get(i).isDisponible());
				System.out.print("\nInformacion: "+this.listado_dvds.get(i).getInformacion());
				System.out.print("\nGenero: "+this.listado_dvds.get(i).getGenero());
				System.out.print("\nDirector: "+this.listado_dvds.get(i).getDirector());
				System.out.print("\n");
			}
		System.out.print("\n\n------------------------------------------------------------");
	}
	
	public void cantidadDiscos(){
		String a_buscar= JOptionPane.showInputDialog("\nCantidad de (CDS/DVDS):").toString();
		System.out.print("\nListado Total de "+a_buscar+":");

		if(a_buscar.equals("CDS")){
			this.sub_indice = a_buscar;
			System.out.print("\n"+this.listado_cds.size());
		}
		else if(a_buscar.equals("DVDS")){ 
			this.sub_indice = a_buscar;
			System.out.print("\n"+this.listado_dvds.size());
		}
		else{
			System.out.print("\n"+"Opcion no disponible");
		}
	}
	
	public void listadoDisponibles(){
		System.out.print("\n\nListado de "+this.sub_indice+" Disponibles:");
		if(sub_indice.equals("CDS")){
			for(int i=0;i<listado_cds.size();i++){
				if(this.listado_cds.get(i).isDisponible()){
					System.out.print("\n"+this.listado_cds.get(i).getTitulo());
				}
			}
		}
		else if(sub_indice.equals("DVDS")){ 
			for(int i=0;i<listado_dvds.size();i++){
				if(this.listado_dvds.get(i).isDisponible()){
					System.out.print("\n"+this.listado_dvds.get(i).getTitulo());
				}
			}
			String a_buscar_director= JOptionPane.showInputDialog("\nDirector:").toString();
			System.out.print("\n\nListado de DVD dirigidos por "+a_buscar_director+":");

			for(int i=0;i<listado_dvds.size();i++){
				if(this.listado_dvds.get(i).getDirector().equals(a_buscar_director)){
					System.out.print("\n"+this.listado_dvds.get(i).getTitulo());
				}
			}
		}
	}
	
	public void listadoFiltrarPorTiempo(){
		int cantidad_tiempo= Integer.parseInt(JOptionPane.showInputDialog("\nFiltrar por duracion de "+sub_indice+"(min):"));
		System.out.print("\n\nListado de "+this.sub_indice+" Que duran menos de "+cantidad_tiempo+" mins:");
		if(sub_indice.equals("CDS")){
			for(int i=0;i<listado_cds.size();i++){
				if(this.listado_cds.get(i).getTiempo()<=cantidad_tiempo){
					System.out.print("\n"+this.listado_cds.get(i).getTitulo());
				}
			}
		}
		else if(sub_indice.equals("DVDS")){ 
			for(int i=0;i<listado_dvds.size();i++){
				if(this.listado_dvds.get(i).getTiempo()<=cantidad_tiempo){
					System.out.print("\n"+this.listado_dvds.get(i).getTitulo());
				}
			}
		}
	}
	
	public void listadoOrdenado(){
		System.out.print("\n\nListado ordenado:\n");
		ArrayList<String> listado_ordenado = new ArrayList<String>();
		if(sub_indice.equals("CDS")){ 
			for(int i=0;i<listado_cds.size();i++){
				listado_ordenado.add(listado_cds.get(i).getTitulo());
			}
		}
		else if(sub_indice.equals("DVDS")){ 
			for(int i=0;i<listado_dvds.size();i++){
				listado_ordenado.add(listado_dvds.get(i).getTitulo());
			}
		}
		Collections.sort(listado_ordenado);
		for(String titulo : listado_ordenado){
			System.out.println(titulo);
		}
	}
}



























