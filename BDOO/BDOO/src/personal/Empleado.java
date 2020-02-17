package personal;

import java.util.Date;

public class Empleado {

	private int id_emp;
	private String apellido, oficio;
	private Date fecha_alta;
	private float salario, comision;
	private Empleado jefe;
	private Departamento departamento;

	// CONSTRUCTOR

	//ESTE CONSTRUCTOR PERMITE CREAR UN EMPLEADO SIN JEFE
	public Empleado(int id_emp, String apellido, String oficio, Date fecha_alta, float salario, float comision,
			Departamento departamento) {
		super();
		this.id_emp = id_emp;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
		this.departamento = departamento;
	}

	public Empleado(int id_emp, String apellido, String oficio, Date fecha_alta, float salario, float comision,
			Empleado jefe, Departamento departamento) {
		super();
		this.id_emp = id_emp;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
		this.jefe = jefe;
		this.departamento = departamento;
	}

	// METODOS GET Y SET
	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public Empleado getJefe() {
		return jefe;
	}

	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}//FIN CLASE EMPLEADO
