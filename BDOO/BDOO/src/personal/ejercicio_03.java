/*Realiza un programa Java que utilice NeoDatis para ejecutar las siguientes consultas sobre la 
base de datos personal: */

package personal;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ejercicio_03 {

	public static void main(String[] args) {
		
		ODB odb = ODBFactory.open("personal.test");// ABRIMOS O GENERAMOS LA BASE DE DATOS
		
		//A) OBTENER LOS EMPLEADOS QUE TIENEN UN SALARIO SUPERIOR A 1000
		System.out.println("A) OBTENER LOS EMPLEADOS QUE TIENEN UN SALARIO SUPERIOR A 1000");
		Metodos.buscar_empleados_salario(odb);
		System.out.println();
		//B) OBTENER LOS EMPLEADOS NUEVOS DEL 2015
		System.out.println("B) OBTENER LOS EMPLEADOS NUEVOS DEL 2015");
		Metodos.buscar_empleados_ano(odb);
		System.out.println();
		//C) OBTENER LOS EMPLEADOS DEL DEPARTAMENTO 10
		System.out.println("C) OBTENER LOS EMPLEADOS DEL DEPARTAMENTO 10");
		Metodos.buscar_empleados_departamento(odb);
		System.out.println();
		//D) OBTENER LOS EMPLEADOS CUYO JEFE ES LOPEZ
		System.out.println("D) OBTENER LOS EMPLEADOS CUYO JEFE ES LOPEZ");
		Metodos.buscar_empleados_jefe(odb);
		System.out.println();
		//E) OBTENER EL NUMERO DE EMPLEADOS DEL DEPARTAMENTO VENTAS
		System.out.println("E) OBTENER EL NUMERO DE EMPLEADOS DEL DEPARTAMENTO VENTAS");
		Metodos.buscar_empleados_ventas(odb);
		System.out.println();
		//F) OBTENER EL NUMERO DE EMPLEADOS DE CADA DEPARTAMENTO
		System.out.println("F) OBTENER EL NUMERO DE EMPLEADOS DE CADA DEPARTAMENTO");
		Metodos.empleados_por_departamento(odb);
		System.out.println();
		
	}//FIN MAIN

}//FIN EJERCICIO_03
