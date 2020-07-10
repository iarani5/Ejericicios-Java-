
public class Libro {
	int id;
	String titulo;
	String autor;
	int cant_paginas;
	int ISBN;
	int anio;
	String editorial;
	String genero;

	public Libro (String id, String t, String a, String cP, String i, String anio, String e, String g){
		 this.id=Integer.parseInt(id);
		 this.titulo = t;
		 this.autor = a;
		 this.cant_paginas = Integer.parseInt(cP);
		 this.ISBN = Integer.parseInt(i);
		 this.anio = Integer.parseInt(anio);
		 this.editorial = e;
		 this.genero = g;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", cant_paginas=" + cant_paginas + ", ISBN=" + ISBN
				+ ", anio=" + anio + ", editorial=" + editorial + ", genero=" + genero + "]";
	}
	
}
