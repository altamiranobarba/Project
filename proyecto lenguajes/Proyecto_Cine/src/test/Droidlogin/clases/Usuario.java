package test.Droidlogin.clases;

public class Usuario {
	
	
	private String nombre;
	private String apellido;
	private String saldo;
	
	public Usuario(String nombre,String apellido, String saldo){
		this.nombre = nombre;
		this.apellido = apellido;
		this.saldo = saldo;
	}
	
	public Usuario(){
		
		
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	
	
}
