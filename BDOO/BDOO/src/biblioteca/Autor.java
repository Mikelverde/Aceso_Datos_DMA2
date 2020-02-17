package biblioteca;

import java.util.List;

public class Autor {

	private String nombre;
	private String apellido;
	private String nacionalidad;
	private int edad;
	private List<Libro> libros;

	// CONSTRUCTOR

	public Autor(String nombre, String apellido, String nacionalidad, int edad, List<Libro> libros) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.libros = libros;
	}

	public Autor(String nombre, String apellido, String nacionalidad, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
	}

	// METOSDOS GET Y SET
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	//METODO PARA IMPRIMIR LOS LIBROS DE UN AUTOR
	public void imprimir_libros() {

		for (Libro l : libros) {
			System.out.print("Libro: " + l.getTitulo() + ", ");
		}

	}// FIN IMPRIMIR LIBROS

	//METODO PARA AÑADIR UN LIBRO A LA LISTA DE LIBROS DE UN AUTOR
	public void anadir_libro(Libro libro) {

		libros.add(libro);
	}//FIN AÑADIR_LIBRO
}
