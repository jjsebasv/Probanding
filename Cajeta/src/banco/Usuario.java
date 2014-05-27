package banco;
import org.joda.time.LocalDate;


 
public class Usuario {

	private Celular celular;
	private final long dni;
	private final String apellido;
	private String domicilio;
	private final String nombre;
	private String telefono;
	private final LocalDate fechaDeNacimiento;	


	public Usuario ( long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento){
		this.dni = dni;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaDeNacimiento = fechaNacimiento;
	}

	// ---------------------------  HASHCODE, EQUALS, TO STRING -----------------------------------


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + (int) (dni ^ (dni >>> 32));
		result = prime * result
				+ ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime
				* result
				+ ((fechaDeNacimiento == null) ? 0 : fechaDeNacimiento
						.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (dni != other.dni)
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (fechaDeNacimiento == null) {
			if (other.fechaDeNacimiento != null)
				return false;
		} else if (!fechaDeNacimiento.equals(other.fechaDeNacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	public String toString(){
		return "DNI: "+this.dni+" - "+this.nombre+" "+ this.apellido;
	}


	// ---------------------------  GETTERS Y SETTERS -----------------------------------

	
	public Celular getCelular() {
		return celular;
	}


	public long getDni() {
		return dni;
	}


	public String getApellido() {
		return apellido;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public String getNombre() {
		return nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}


	public void setCelular(Celular celular) {
		this.celular = celular;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
