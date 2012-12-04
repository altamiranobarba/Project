package test.Droidlogin.layout;

public class MostrarCine {
	
	private String nombre;
	private String coordx;
	private String coordy;
	
	
	public MostrarCine(String nombre, String coordx, String coordy) {
		this.nombre = nombre;
		this.coordx=coordx;
		this.coordy=coordy;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCoordx() {
		return coordx;
	}

	public String getCoordy() {
		return coordy;
	}

}
