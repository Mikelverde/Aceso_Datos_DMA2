
public class Pais {
	
	private String nombre;
	private int id;
	
	//CONSTRUCTOR
	
	
	
	
	public Pais(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	public Pais(String nombre) {
		super();
		this.nombre = nombre;
	}

	//METODOS

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	

}
