package biblioteca;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ejercicio_04 {

	public static void main(String[] args) {

		ODB odb = ODBFactory.open("biblioteca.test");// ABRIMOS O CREAMOS LA BASE DE DATOS
		//A) OBTENER LOS LIBROS CON AS DE 30 PAGINAS
		System.out.println("A) OBTENER LOS LIBROS CON MAS DE 30 PAGINAS");
		Metodos.buscar_libros_paginas(odb);
		System.out.println();
		//B) OBTENER AUTORES ESPAÑOLES
		System.out.println("B) OBTENER AUTORES ESPAÑOLES");
		Metodos.buscar_autores_nacionalidad(odb);
		System.out.println();
		//C) OBTENER LIBROS DE AUTORES CON APELLIDOS CON LETRA S
		System.out.println("C) OBTENER LIBROS DE AUTORES CON APELLIDOS CON LETRA S");
		Metodos.buscar_letra_apellido(odb);

		odb.close();

	}

}
