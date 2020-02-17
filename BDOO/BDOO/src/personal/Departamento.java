package personal;

public class Departamento {

	private int id_dep;
	private String nombre, localidad;
	
	//CONSTRUCTOR
	public Departamento(int id_dep, String nombre, String localidad) {
		super();
		this.id_dep = id_dep;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	
	//METODOS GET Y SET
	public int getId_dp() {
		return id_dep;
	}

	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}//FIN DEPARTAMENTO
