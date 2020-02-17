/*Realiza  un  programa  Java  que  utilice  NeoDatis  para  ejecutar  las  siguientes  operaciones  de 
gestión de objetos de la base de datos personal:*/

package personal;

import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ejercicio_01 {

	public static void main(String[] args) {

		// SI NO EXISTE LA BASE DE DATOS personal.test SE EJECUTA LA SIGUIENTE LINEA DE
		// MODO QUE SE GENERE LA BASE DE DATOS CON LOS REGISTROS CORRESPONDIENTES
		
		// Metodos.iniciar_base_datos();

		Scanner entrada = new Scanner(System.in);
		ODB odb = ODBFactory.open("personal.test");// ABRIMOS O GENERAMOS LA BASE DE DATOS

		int opcion = 1;

		while (opcion != 0) {
			System.out.println("-----------------");
			System.out.println("Elija una Opcion?");
			System.out.println("-----------------");
			System.out.println("1- Imprimir Departamentos");
			System.out.println("2- Imprimir personal");
			System.out.println("3- Insertar Personal");
			System.out.println("4- Insertar Departamento");
			System.out.println("0- Salir");

			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				Metodos.imprimir_departamentos(odb);
				break;
			case 2:
				Metodos.imprimir_empleados(odb);
				break;
			case 3:
				Metodos.introducir_empleado(odb, entrada);
				break;
			case 4:
				Metodos.introducir_departamento(odb, entrada);
				break;
			case 0:
				System.out.println("Sesion finalizada");
				break;

			}// FIN SWITCH

		}//FIN WHILE

		entrada.close();

	}//FIN MAIN
}//FIN EJERCICIO_01
