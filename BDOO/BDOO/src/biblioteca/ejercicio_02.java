/*Realiza  un  programa  Java  que  utilice  NeoDatis  para  ejecutar  las  siguientes  operaciones  de 
gestión de objetos de la base de datos biblioteca:*/

package biblioteca;

import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ejercicio_02 {

	public static void main(String[] args) {

		// SI NO EXISTE LA BASE DE DATOS biblioteca.test SE EJECUTA LA SIGUIENTE LINEA
		// DE
		// MODO QUE SE GENERE LA BASE DE DATOS CON LOS REGISTROS CORRESPONDIENTES

		// Metodos.iniciar_biblioteca();

		ODB odb = ODBFactory.open("biblioteca.test");// ABRIMOS O CREAMOS LA BASE DE DATOS
		Scanner entrada = new Scanner(System.in);

		// E) VISUALIZAR TODOS LOS LIBROS
		Metodos.imprimir_libros(odb);

		// F) VISUALIZAR TODOS LOS AUTORES
		Metodos.imprimir_autores(odb);

		// A) INSERTAR UN LIBRO SIN ESPECIFICAR NINGUN AUTOR
		System.out.println("A) INSERTAR UN LIBRO SIN ESPECIFICAR NINGUN AUTOR");
		Metodos.insertar_libro_sinAutor(odb, entrada);

		// B) INSERTAR UN AUTOR SIN ESPECIFICAR NINGUN LIBRO
		System.out.println("B) INSERTAR UN AUTOR SIN ESPECIFICAR NINGUN LIBRO");
		Metodos.inserta_autor_sinLibro(odb, entrada);

		// C) MODIFICAR UN LIBRO PARA AÑADIRLE UN AUTOR
		System.out.println("C) MODIFICAR UN LIBRO PARA AÑADIRLE UN AUTOR");
		Metodos.anadir_autor(odb, entrada);

		// D) MODIFICAR UN AUTOR PARA AÑADIRLE UN LIBRO
		System.out.println("D) MODIFICAR UN AUTOR PARA AÑADIRLE UN LIBRO");
		Metodos.anadir_libro(odb, entrada);

		// E) VISUALIZAR TODOS LOS LIBROS
		System.out.println("E) VISUALIZAR TODOS LOS LIBROS");
		Metodos.imprimir_libros(odb);

		// F) VISUALIZAR TODOS LOS AUTORES
		System.out.println("E) VISUALIZAR TODOS LOS AUTORES");
		Metodos.imprimir_autores(odb);

		odb.close();
		entrada.close();

	}

}
