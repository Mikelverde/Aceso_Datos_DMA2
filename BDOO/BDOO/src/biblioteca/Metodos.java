package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import org.neodatis.odb.core.query.criteria.And;

public class Metodos {

	public static void insertar_libro_sinAutor(ODB odb, Scanner entrada) {

		String titulo, editorial;
		int ano, paginas;

		System.out.println("Introduce el titulo");
		titulo = entrada.nextLine();
		System.out.println("Introduce la editorial");
		editorial = entrada.nextLine();
		System.out.println("Introduce el año");
		ano = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Introduce el numero de paginas");
		paginas = entrada.nextInt();
		entrada.nextLine();
		System.out.println("-------------------------");

		Libro libro = new Libro(titulo, ano, editorial, paginas);

		odb.store(libro);// ALMAZENAMOS EL DEPARTAMENTO
		odb.commit();// VALIDA LOS CAMBIOS EN LA BASE DE DATOS

	}//FIN METODO INSERTAR_LIBRO_SINAUTOR

	public static void imprimir_libros(ODB odb) {

		Objects<Libro> libros = odb.getObjects(Libro.class);// RECUPERAMOS LOS DATOS EN UNA COLECCION DE
															// OBJETOS
		System.out.println(
				"LIBROS---------------------------------------------------------------------------------------------------");

		while (libros.hasNext()) {

			Libro libro = libros.next();
			//SEGUN SI EL LIBRO TIENE ASIGNADOS AUTORES O NO IMPRIMIMOS UNOS DATOS U OTROS
			if (libro.getAutores() == null) {

				System.out.printf("LIBRO --> Titulo: %s, Editorial: %s, Año: %d, Paginas: %d%n", libro.getTitulo(),
						libro.getEditorial(), libro.getAño(), libro.getPaginas());

			} else {

				System.out.print("LIBRO -->");
				//METODO IMPRIMIR_AUTORES DE LA CLASE LIBRO PARA IMPRIMIR LOS AUTORES DE LA LISTA<AUTORES>
				libro.imprimir_autores();
				System.out.printf(" Titulo: %s, Editorial: %s, Año: %d, Paginas: %d%n", libro.getTitulo(),
						libro.getEditorial(), libro.getAño(), libro.getPaginas());
			}

		} // FIN WHILE

	}//FIN IMPRIMIR_LIBROS

	public static void inserta_autor_sinLibro(ODB odb, Scanner entrada) {

		String nombre;
		String apellido;
		String nacionalidad;
		int edad;

		System.out.println("Introduce el Nombre");
		nombre = entrada.nextLine();
		System.out.println("Introduce apellido");
		apellido = entrada.nextLine();
		System.out.println("Introduce la nacionalidad");
		nacionalidad = entrada.nextLine();
		System.out.println("Introduce la edad");
		edad = entrada.nextInt();
		entrada.nextLine();
		System.out.println("-------------------------");

		//CONSTRUCTOS SIN PARAMETRO <LIBROS>
		Autor autor = new Autor(nombre, apellido, nacionalidad, edad);
		odb.store(autor);
		odb.commit();

	}//FIN INSERTAR_AUTOR_SIN LIBRO

	public static void imprimir_autores(ODB odb) {
		
		Objects<Autor> autores = odb.getObjects(Autor.class);// RECUPERAMOS LOS DATOS EN UNA COLECCION DE
																// OBJETOS
		System.out.println(
				"AUTORES--------------------------------------------------------------------------------------------------------------------------------------------------");

		while (autores.hasNext()) {

			Autor autor = autores.next();
			//SEGUN SI EL AUTOR TIENE ASIGNADOS LIBROS O NO IMPRIMIMOS UNOS DATOS U OTROS
			if (autor.getLibros() == null) {

				System.out.printf("AUTOR --> Nombre: %s, Apellido: %s, Edad: %d, Nacionalidad: %s%n", autor.getNombre(),
						autor.getApellido(), autor.getEdad(), autor.getNacionalidad());

			} else {

				System.out.print("AUTOR -->");
				//METODO IMPRIMIR_LIBROS DE LA CLASE AUTORES PARA IMPRIMIR LOS LIBROS DE LA LISTA<LIBROS>
				autor.imprimir_libros();
				System.out.printf(", Nombre: %s, Apellido: %s, Edad: %d, Nacionalidad: %s%n", autor.getNombre(),
						autor.getApellido(), autor.getEdad(), autor.getNacionalidad());
			}

		} // FIN WHILE

	}//FIN IMPRIMIR_AUTORES

	public static Libro buscar_libro(String titulo, ODB odb) {

		IQuery query = new CriteriaQuery(Libro.class, Where.equal("titulo", titulo));
		Objects<Libro> libros = odb.getObjects(query);
		return libros.getFirst();

	}//FIN BUSCAR LIBRO

	public static Autor buscar_autor(ODB odb, Scanner entrada) {
		
		//COMO PUEDN EXISTIR AYTORES CON EL MISMO APELLIDO UTILIZAMOS ADEMAS EL PARAMETRO NOMBRE
		System.out.println("Nombre del autor?");
		String nombre_autor = entrada.nextLine();
		System.out.println("Apellido del autor?");
		String apellido_autor = entrada.nextLine();

		ICriterion por_nombre = Where.equal("nombre", nombre_autor);
		ICriterion por_apellido = Where.equal("apellido", apellido_autor);
		ICriterion crit = new And().add(por_apellido).add(por_nombre);
		IQuery query = new CriteriaQuery(Autor.class, crit);
		Objects<Autor> autores = odb.getObjects(query);

		return autores.getFirst();

	}//FIN BUSCAR_AUTOR

	public static void anadir_autor(ODB odb, Scanner entrada) {

		String titulo, nombre_autor, apellido_autor;
		Libro libro;
		Objects<Autor> autores;
		ArrayList<Autor> lista_autores = new ArrayList<Autor>();

		System.out.println("Que libro quiere modificar?");
		titulo = entrada.nextLine();
		libro = Metodos.buscar_libro(titulo, odb);

		System.out.println("Quien ha escrito el " + libro.getTitulo());

		Metodos.imprimir_autores(odb);

		System.out.println("Nombre del autor?");
		nombre_autor = entrada.nextLine();
		System.out.println("Apellido del autor?");
		apellido_autor = entrada.nextLine();

		ICriterion por_nombre = Where.equal("nombre", nombre_autor);
		ICriterion por_apellido = Where.equal("apellido", apellido_autor);
		ICriterion crit = new And().add(por_apellido).add(por_nombre);

		IQuery query = new CriteriaQuery(Autor.class, crit);
		autores = odb.getObjects(query);

		for (Autor au : autores) {

			lista_autores.add(au);
		}

		libro.setAutores(lista_autores);

		odb.store(libro);
		odb.commit();

	}//FIN ANADIR_AUTOR

	public static void anadir_libro(ODB odb, Scanner entrada) {

		String titulo;
		Libro libro;
		List<Libro> libros = new ArrayList<Libro>();

		Metodos.imprimir_autores(odb);
		Autor autor = Metodos.buscar_autor(odb, entrada);

		Metodos.imprimir_libros(odb);
		System.out.println("A que libro quiere añadir el autor?");
		titulo = entrada.nextLine();
		libro = Metodos.buscar_libro(titulo, odb);
		libros.add(libro);

		autor.setLibros(libros);

		odb.store(autor);
		odb.commit();

	}//FIN ANADIR_LIBRO

	public static void iniciar_biblioteca() {

		ODB odb = ODBFactory.open("biblioteca.test");// ABRIMOS O CREAMOS LA BASE DE DATOS
		Libro libro01 = new Libro("Ojo por Ojo", 1942, "EDB", 15);
		Libro libro02 = new Libro("El Pez Amarillo", 2012, "Altamira", 100);
		Libro libro03 = new Libro("Manolito", 2020, "EDB", 130);

		Autor autor01 = new Autor("Mariano", "Sanchez", "España", 104);
		Autor autor02 = new Autor("Sofia", "Martin", "España", 37);
		Autor autor03 = new Autor("Juan", "Sanchez", "Francia", 18);

		odb.store(libro01);
		odb.store(libro02);
		odb.store(libro03);
		odb.store(autor01);
		odb.store(autor02);
		odb.store(autor03);
		odb.commit();
		odb.close();

	}//FIN INICIAR BIBLIOTECA

	public static void buscar_libros_paginas(ODB odb) {

		ICriterion paginas = Where.ge("paginas", 30);// PAGINAS > 30
		IQuery query = new CriteriaQuery(Libro.class, paginas);
		Objects<Libro> libros = odb.getObjects(query);

		for (Libro libro : libros) {
			System.out.println("Titulo: " + libro.getTitulo() + "--> nº paginas " + libro.getPaginas());
		}//FIN FOR

	}//FIN BUSCAR_LIBROS_PAGINAS

	public static void buscar_autores_nacionalidad(ODB odb) {

		ICriterion espana = Where.equal("nacionalidad", "España");
		IQuery query = new CriteriaQuery(Autor.class, espana);
		Objects<Autor> autores = odb.getObjects(query);

		for (Autor autor : autores) {
			System.out.println(
					"Nombre: " + autor.getNombre() + " " + autor.getApellido() + "es de " + autor.getNacionalidad());
		}//FIN FOR

	}//FIN BUSCAR_AUTORES_NACIONALIDAD

	public static void buscar_letra_apellido(ODB odb) {

		ICriterion letra = Where.like("apellido", "S%");
		IQuery consulta = new CriteriaQuery(Autor.class, letra);
		Objects<Autor> autores = odb.getObjects(consulta);

		for (Autor autor : autores) {
 
			if (autor.getLibros() != null) {
				List<Libro> libros = autor.getLibros();
				for (Libro libro : libros) {
					
					System.out.println("Titulo: " + libro.getTitulo() + " escrito en " + libro.getAño() + " por "
							+ autor.getApellido());
				}//FIN FOR

			}//FIN IF

		}//FIN FOR

	}//FIN BUSCAR_LETRA_APELLIDO

}//FIN CLASE BIBLIOTECA

