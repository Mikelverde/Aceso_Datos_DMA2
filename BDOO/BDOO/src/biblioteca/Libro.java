package biblioteca;

import java.util.List;

public class Libro {

	private String titulo;
	private int año;
	private String editorial;
	private int paginas;
	private List<Autor> autores;

	// CONSTRUCTOR
	public Libro(String titulo, int año, String editorial, int paginas, List<Autor> autores) {
		super();
		this.titulo = titulo;
		this.año = año;
		this.editorial = editorial;
		this.paginas = paginas;
		this.autores = autores;
	}

	//CONSTRUCTOR DE LIBRO SIN ASIGNAR AUTOR
	public Libro(String titulo, int año, String editorial, int paginas) {
		super();
		this.titulo = titulo;
		this.año = año;
		this.editorial = editorial;
		this.paginas = paginas;
	}

	// METODOS GET Y SET
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	//METODO PARA IMPRIMIR LOS AUTORES DEL LIBRO
	public void imprimir_autores() {

		for (Autor a : autores) {
			System.out.print("Autor: " + a.getNombre() + " " + a.getApellido() + ", ");
		}
	}// FIN IMPRIMIR AUTORES

}// FIN CLASE LIBRO
