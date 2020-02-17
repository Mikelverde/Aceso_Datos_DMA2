import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Main {

	public static void main(String[] args) {

		int opcion = 1;//NOS PERMITE ENTRAR EN EL WHILE

		ODB odb = ODBFactory.open("neodatis.test");// ABRIMOS O GENERAMOS LA BASE DE DATOS

		Scanner entrada = new Scanner(System.in);

		while (opcion != 0) {// HASTA QUE EL USUARIO INTRODUZACA LA OPCION SALIR=0 SE REPITE EL BUCLE
			System.out.println("-----------------");
			System.out.println("Elija una Opcion?");
			System.out.println("-----------------");
			System.out.println("1- Imprimar datos");
			System.out.println("2- Introducir datos");
			System.out.println("3- Hacer una consulta");
			System.out.println("0- Salir");

			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				Metodos.imprimir_datos(odb);
				break;
			case 2:
				Metodos.introducir_datos(odb, entrada);
				break;
			case 3:
				Metodos.consulta(odb, entrada);
				break;

			}// FIN SWITCH

		} // FIN WHILE

		entrada.close();
		odb.close();

	}// FIN MAIN

}
