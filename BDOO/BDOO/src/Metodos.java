
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Metodos {

	public static void introducir_datos(ODB odb, Scanner entrada) {

		String nombre, deporte, ciudad, continuar, nombre_pais;
		int edad;
		Pais pais;
		continuar = "Y";
		Jugadores j;

		while (continuar.equalsIgnoreCase("Y")) {// PEDIMOS LOS DATOS NECESARIOS HASTA QUE EL USUARIO TECLEE N

			System.out.println("introduce un nombre");
			nombre = entrada.nextLine();
			System.out.println("introduce un deporte");
			deporte = entrada.nextLine();
			System.out.println("introduce una ciudad");
			ciudad = entrada.nextLine();
			System.out.println("introduce el pais");
			nombre_pais = entrada.nextLine();
			System.out.println("introduce la edad");
			edad = entrada.nextInt();
			entrada.nextLine();// ESTA LINEA EVITA PROBLEMAS TRAS LA LECTURA DE UN INT
			System.out.println("-------------------------");

			pais = new Pais(nombre_pais);
			j = new Jugadores(nombre, deporte, ciudad, edad, pais);

			odb.store(j);// ALMAZENAMOS JUGADOR
			odb.commit();// VALIDA LOS CAMBIOS EN LA BASE DE DATOS

			System.out.println("Desea introducir mas datos (Y/N)");
			System.out.println("");
			continuar = entrada.nextLine();

		} // FIN WHILE

	}// FIN METODO INTRODUCIR_DATOS

	public static void imprimir_datos(ODB odb) {

		Objects<Jugadores> jugadores = odb.getObjects(Jugadores.class);// RECUPERAMOS LOS DATOS EN UNA COLECCION DE
																		// OBJETOS

		int i = 1;// PARA NUMERAR LOS RESULTADOS

		while (jugadores.hasNext()) {

			Jugadores jugador = jugadores.next();
			System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
					jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());

		} // FIN WHILE

	}// FIN CLASE IMPRIMIR_DATOS

	public static void consulta(ODB odb, Scanner entrada) {

		String nombre;
		int edad, i = 1;
		int opcion;

		System.out.println("Con que criterio quiere realizar la consulta?");
		System.out.println("1- Nombre del jugador");
		System.out.println("2- Nombre del deporte");
		System.out.println("3- Ciudad");
		System.out.println("4- Edad del jugador");
		System.out.println("5- Pais del jugador");

		opcion = entrada.nextInt();
		entrada.nextLine();

		switch (opcion) {
		case 1:
			System.out.println("Nombre?");
			nombre = entrada.nextLine();
			//REALIZAMOS UNA CONSULTA CON UN CRITERIO NOMBRE Y RECOGE LOS RESULTADOS EN JUGADORES
			IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("nombre", nombre));
			Objects<Jugadores> jugadores = odb.getObjects(query);

			if (jugadores.size() == 0) {// SI SIZE DE JUGADORES ES 0 ESQUER NO EXISTE NINGUN ELEMENTO QUE CUMPLA LA
										// CONSULTA
				System.out.println("No existen jugadores con esa caracteristica");
			}

			while (jugadores.hasNext()) {//DEVUELVE TRUE HASTA QUE LLEGA AL ULTIMO ELEMENTO DE JUGADORES

				Jugadores jugador = jugadores.next();//CARGA UN ELEMENTO DE LA LISTA Y PASA AL SIGUIENTE
				System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
						jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());
			} // FIN WHILE

			break;

		case 2:
			System.out.println("Deporte?");
			nombre = entrada.nextLine();
			IQuery query02 = new CriteriaQuery(Jugadores.class, Where.equal("deporte", nombre));
			Objects<Jugadores> jugadores02 = odb.getObjects(query02);

			if (jugadores02.size() == 0) {
				System.out.println("No existen jugadores con esa caracteristica");
			}

			while (jugadores02.hasNext()) {

				Jugadores jugador = jugadores02.next();
				System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
						jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());
			} // FIN WHILE

			break;

		case 3:
			System.out.println("Ciudad?");
			nombre = entrada.nextLine();
			IQuery query03 = new CriteriaQuery(Jugadores.class, Where.equal("ciudad", nombre));
			Objects<Jugadores> jugadores03 = odb.getObjects(query03);

			if (jugadores03.size() == 0) {
				System.out.println("No existen jugadores con esa caracteristica");
			}

			while (jugadores03.hasNext()) {

				Jugadores jugador = jugadores03.next();
				System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
						jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());
			} // FIN WHILE

			break;

		case 4:
			System.out.println("Edad?");
			edad = entrada.nextInt();
			IQuery query04 = new CriteriaQuery(Jugadores.class, Where.equal("edad", edad));
			Objects<Jugadores> jugadores04 = odb.getObjects(query04);

			if (jugadores04.size() == 0) {
				System.out.println("No existen jugadores con esa caracteristica");
			}

			while (jugadores04.hasNext()) {

				Jugadores jugador = jugadores04.next();
				System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
						jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());
			} // FIN WHILE

			break;

		case 5:
			System.out.println("Pais?");
			nombre = entrada.nextLine();
			IQuery query05 = new CriteriaQuery(Jugadores.class, Where.equal("pais.nombre", nombre));
			Objects<Jugadores> jugadores05 = odb.getObjects(query05);

			if (jugadores05.size() == 0) {
				System.out.println("No existen jugadores con esa caracteristica");
			}

			while (jugadores05.hasNext()) {

				Jugadores jugador = jugadores05.next();
				System.out.printf("%d: %s, %s, %s, %s, %d %n", i++, jugador.getNombre(), jugador.getDeporte(),
						jugador.getCiudad(), jugador.getPais().getNombre(), jugador.getEdad());
			} // FIN WHILE

			break;

		}// FIN SWITCH

	}// FIN METODO CONSULTA

}// FIN CLASE METODOS
