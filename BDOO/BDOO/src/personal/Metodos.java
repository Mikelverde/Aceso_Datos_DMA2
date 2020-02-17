package personal;

import java.util.Date;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Metodos {

	public static void introducir_departamento(ODB odb, Scanner entrada) {

		String nombre, localidad;
		int id_dep;

		System.out.println("Introduce un nombre");
		nombre = entrada.nextLine();
		System.out.println("Introduce la Localidad");
		localidad = entrada.nextLine();

		System.out.println("Introdice el identificador");
		id_dep = entrada.nextInt();
		entrada.nextLine();
		System.out.println("-------------------------");

		// CON LOS DATOS OBTENEDIS DEL USUARIO CREAMOS UN OBJETO DEPARTAMENTO
		Departamento departamento = new Departamento(id_dep, nombre, localidad);

		odb.store(departamento);// ALMACENAMOS EL DEPARTAMENTO
		odb.commit();// VALIDA LOS CAMBIOS EN LA BASE DE DATOS

	}// FIN INTRODUCIR_DEPARTAMENTO

	public static void introducir_empleado(ODB odb, Scanner entrada) {

		String apellido, oficio, fecha;
		int id_jefe, id_emp, id_dep;
		Date fecha_alta;
		float salario, comision;
		Empleado jefe;
		Departamento departamento;

		System.out.println("Introduzca el numero de id del empleado");
		id_emp = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Introduce el apellido");
		apellido = entrada.nextLine();
		System.out.println("Introduce el oficio");
		oficio = entrada.nextLine();
		System.out.println("Introduce la fecha de alta dd/MM/yyyy");
		fecha = entrada.nextLine();
		fecha_alta = Metodos.convertir_string_date(fecha);
		System.out.println("Intrudece el salario");
		salario = entrada.nextFloat();
		entrada.nextLine();
		System.out.println("Intrudece la comision");
		comision = entrada.nextFloat();
		entrada.nextLine();
		System.out.println("Introduzca el numero de id de su jefe");
		id_jefe = entrada.nextInt();
		entrada.nextLine();
		// BUSCAMOS EL JEFE CON EL ID_EMP INTRODUCIDO
		IQuery query = new CriteriaQuery(Empleado.class, Where.equal("id_emp", id_jefe));
		Objects<Empleado> empleados = odb.getObjects(query);

		if (empleados.size() == 0) {// SI NO EXISTE UN EMPLEADO CON ESE ID_EMP
			System.out.println("No existe empleado con ese id");
		}

		// CARGAMOS COMO JEFE EL EMPLEADO CON EL ID_EMP INTRODUCIDO
		jefe = empleados.getFirst();

		System.out.println("Introduzca el numero de id del departamento");
		id_dep = entrada.nextInt();
		entrada.nextLine();

		// PROCESO IDENTICO AL DE LA BUSQUEDA DE JEFE
		IQuery query02 = new CriteriaQuery(Departamento.class, Where.equal("id_dep", id_dep));
		Objects<Departamento> dep = odb.getObjects(query02);

		if (dep.size() == 0) {
			System.out.println("No existen departamento con ese id");
		}
		// CARGAMOS EL DEPARTAMENTO
		departamento = dep.getFirst();

		System.out.println("-------------------------");

		// GENERAMOS EL EMPLEADO CON LOS DATOS CORRESPONDIENTES
		Empleado empleado = new Empleado(id_emp, apellido, oficio, fecha_alta, salario, comision, jefe, departamento);

		odb.store(empleado);// ALMAZENAMOS EL EMPLEADO
		odb.commit();// VALIDA LOS CAMBIOS EN LA BASE DE DATOS

	}// FIN INTRODUCIR EMPLEADO

	public static Date convertir_string_date(String fecha) {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}// FIN CONVERTIR_STRING_DATE

	public static String convertir_date_string(Date fecha) {

		Date date = fecha;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		return strDate;

	}// FIN CONVERTIR_DATE_STRING

	public static void imprimir_empleados(ODB odb) {

		Objects<Empleado> empleados = odb.getObjects(Empleado.class);// RECUPERAMOS LOS DATOS EN UNA COLECCION DE
																		// OBJETOS
		System.out.println(
				"EMPLEADOS-------------------------------------------------------------------------");

		while (empleados.hasNext()) {

			Empleado empleado = empleados.next();
			
			//SI EL EMPLEADO NO TIENE JEFE NO IMPRIMIMOS LOS DATOS DE JEFE
			if (empleado.getJefe() == null) {

				System.out.printf(
						"EMPLEADO ID %d--> Apellido: %s, Oficio: %s, Fecha de alta: %s, Salario: %.2f€, Comisión: %.2f, Departamento: %s%n",
						empleado.getId_emp(), empleado.getApellido(), empleado.getOficio(),
						Metodos.convertir_date_string(empleado.getFecha_alta()), empleado.getSalario(),
						empleado.getComision(), empleado.getDepartamento().getNombre());

			} else {
				//SI EL EMPLEADO TIENE JEFE IMPRIMIMOS TODOS LOS DATOS

				System.out.printf(
						"EMPLEADO ID %d--> Apellido: %s, Oficio: %s,Fecha de alta: %s,Salario: %.2f€,Comisión: %.2f, Jefe ID: %d, "
								+ "Jefe apellido: %s, Departamento: %s%n",
						empleado.getId_emp(), empleado.getApellido(), empleado.getOficio(),
						Metodos.convertir_date_string(empleado.getFecha_alta()), empleado.getSalario(),
						empleado.getComision(), empleado.getJefe().getId_emp(), empleado.getJefe().getApellido(),
						empleado.getDepartamento().getNombre());
			}

		} // FIN WHILE

	}//FIN IMPRIMIR_EMPLEADOS

	public static void imprimir_departamentos(ODB odb) {

		Objects<Departamento> departamentos = odb.getObjects(Departamento.class);// RECUPERAMOS LOS DATOS EN UNA
																					// COLECCION DE OBJETOS
		System.out.println(
				"DEPARTAMENTOS---------------------------------------------------------------------------------");

		while (departamentos.hasNext()) {

			Departamento departamento = departamentos.next();
			System.out.printf("ID %d: %s, %s%n", departamento.getId_dp(), departamento.getLocalidad(),
					departamento.getNombre());
		} // FIN WHILE

	}// FIN METODO IMPRIMIR DEPARTAMENTOS

	public static void iniciar_base_datos() {

		ODB odb = ODBFactory.open("personal.test");// ABRIMOS O GENERAMOS LA BASE DE DATOS

		Departamento direccion = new Departamento(1, "Direccion", "Zaragoza");
		Departamento rrhh = new Departamento(2, "RRHH", "Zaragoza");
		Departamento logistica = new Departamento(3, "Logistica", "Zaragoza");
		Departamento produccion = new Departamento(4, "Produccion", "Huesca");
		Departamento produccion_zaragoza = new Departamento(10, "Produccion", "Zaragoza");
		Departamento ventas = new Departamento(5, "Ventas", "Zaragoza");

		Empleado ceo = new Empleado(1, "Fernández", "CEO", Metodos.convertir_string_date("30/12/2000"), 6500, 5,
				direccion);// ESTE EMPLEADO NO TIENE JEFE, SE UTILIZA EL CONSTRUCTOR SIN JEFE
		Empleado jefe_rrhh = new Empleado(2, "Anadon", "Jefe departamento", Metodos.convertir_string_date("30/12/2000"),
				4000, 4, ceo, rrhh);
		Empleado jefe_logistica = new Empleado(3, "Fernandez", "Jefe departamento",
				Metodos.convertir_string_date("30/12/2000"), 4000, 4, ceo, logistica);
		Empleado jefe_produccion = new Empleado(4, "Poblador", "Jefe departamento",
				Metodos.convertir_string_date("30/12/2015"), 4000, 4, ceo, produccion);
		Empleado jefe_produccion_zaragoza = new Empleado(5, "Lopez", "Jefe departamento",
				Metodos.convertir_string_date("30/12/2015"), 4000, 4, ceo, produccion_zaragoza);
		Empleado tornero_zaragoza = new Empleado(6, "Agudo", "Tornero", Metodos.convertir_string_date("30/12/2015"),
				2000, 2, jefe_produccion_zaragoza, produccion_zaragoza);
		Empleado jefe_ventas = new Empleado(7, "Lopez", "Jefe departamento",
				Metodos.convertir_string_date("30/12/2016"), 4000, 4, ceo, ventas);
		Empleado contable_zaragoza = new Empleado(8, "Agudo", "Contable", Metodos.convertir_string_date("30/12/2016"),
				2000, 2, jefe_ventas, ventas);

		odb.store(ceo);// ALMACENAMOS EL CEO
		odb.store(jefe_logistica);
		odb.store(jefe_produccion);
		odb.store(jefe_rrhh);
		odb.store(direccion);// ALMACENAMOS DIRECCION
		odb.store(rrhh);
		odb.store(logistica);
		odb.store(produccion);
		odb.store(produccion_zaragoza);
		odb.store(jefe_produccion_zaragoza);
		odb.store(tornero_zaragoza);
		odb.store(ventas);
		odb.store(jefe_ventas);
		odb.store(contable_zaragoza);

		odb.commit();// VALIDA LOS CAMBIOS EN LA BASE DE DATOS
		odb.close();// CERRAMOS LA BASE DE DATOS
	}// FIN METODO INICIAR_BASE _DATOS

	public static void buscar_empleados_salario(ODB odb) {

		ICriterion salario = Where.gt("salario", 1000);// SALARIO>=1000
		IQuery query = new CriteriaQuery(Empleado.class, salario);
		Objects<Empleado> empleados = odb.getObjects(query);

		for (Empleado empleado : empleados) {
			System.out.println("Empleado nº " + empleado.getId_emp() + "--> Apellido: " + empleado.getApellido()
					+ " Salario: " + empleado.getSalario());
		} // FIN FOR

	}// FIN METODO BUSCAR_EMPLEADO_SALARIO

	public static void buscar_empleados_ano(ODB odb) {

		Date uno_enero, treintauno_diciembre;
		uno_enero = Metodos.convertir_string_date("01/01/2015");
		treintauno_diciembre = Metodos.convertir_string_date("30/12/2015");
		// COMPROBAMOS SI EL EMPLEADO HA SIDO DADO DE ALTA ENTTRE EL 01/01 Y EL 31/12 DE
		// 2015
		ICriterion ano01 = Where.ge("fecha_alta", uno_enero);
		ICriterion ano02 = Where.le("fecha_alta", treintauno_diciembre);
		ICriterion consulta = new And().add(ano01).add(ano02);

		IQuery query = new CriteriaQuery(Empleado.class, consulta);
		Objects<Empleado> empleados = odb.getObjects(query);

		for (Empleado empleado : empleados) {
			System.out.println("Empleado nº " + empleado.getId_emp() + "--> Apellido: " + empleado.getApellido()
					+ " Fecha de Alta: " + Metodos.convertir_date_string(empleado.getFecha_alta()));
		}

	}// FIN METODOS BUSCAR_EMPLEADOS

	public static void buscar_empleados_departamento(ODB odb) {

		ICriterion departamento_10 = Where.equal("departamento.id_dep", 10);
		IQuery consulta = new CriteriaQuery(Empleado.class, departamento_10);
		Objects<Empleado> empleados = odb.getObjects(consulta);

		for (Empleado empleado : empleados) {
			System.out.println("Empleado nº " + empleado.getId_emp() + "--> Apellido: " + empleado.getApellido()
					+ " Departamento: " + empleado.getDepartamento().getId_dp());
		}//FIN FOR

	}//FIN METODO BUSCAR_EMPLEADO_DEPARTAMENTO

	public static void buscar_empleados_jefe(ODB odb) {
		
		ICriterion jefe_lopez = Where.equal("jefe.apellido", "Lopez");
		IQuery consulta = new CriteriaQuery(Empleado.class, jefe_lopez);
		Objects<Empleado> empleados = odb.getObjects(consulta);

		for (Empleado empleado : empleados) {
			System.out.println("Empleado nº " + empleado.getId_emp() + "--> Apellido: " + empleado.getApellido()
					+ " Jefe: " + empleado.getJefe().getApellido());
		}//FIN FOR

	}//FIN METODO BUSCAR_EMPLEADOS_JEFE

	public static void buscar_empleados_ventas(ODB odb) {

		ICriterion departamento_ventas = Where.equal("departamento.nombre", "Ventas");
		IQuery consulta = new CriteriaQuery(Empleado.class, departamento_ventas);
		Objects<Empleado> empleados = odb.getObjects(consulta);

		System.out.println("En el departamento de Ventas trabajan " + empleados.size() + " trabajadores:");
		for (Empleado empleado : empleados) {
			System.out.println("Empleado nº " + empleado.getId_emp() + "--> Apellido: " + empleado.getApellido()
					+ " Departamento: " + empleado.getDepartamento().getNombre());
		}//FIN FOR

	}//FIN METODO BUSCAR_EMPLEADOS_VENTAS

	public static void empleados_por_departamento(ODB odb) {
		
		//OBTENEMOS TODOS LOS DEPARTAMENTOS EXISTENTES
		Objects<Departamento> departamentos = odb.getObjects(Departamento.class);
		//RECORREMOS LOS DEPARTAMENTOS PREGUNTANDO QUIEN TRABAJA EN EL 
		for (Departamento departamento : departamentos) {
			int id_dep=departamento.getId_dp();
			ICriterion departamento_nº = Where.equal("departamento.id_dep", id_dep);
			IQuery consulta = new CriteriaQuery(Empleado.class, departamento_nº);
			Objects<Empleado> empleados = odb.getObjects(consulta);
			if (empleados.size() != 0) {//SI TRABAJA ALGUIEN SIZE DISTINTO DE 0 E IMPRIMIMOS LOS DATOS
				System.out.println("En " + empleados.getFirst().getDepartamento().getNombre() + " hay "
						+ empleados.size() + " empleados");
			}//FIN IF
			
		}

	}//FIN METODO EMPLEADOS_POR_DEPARTAMENTO

}// FIN CLASE METODOS
