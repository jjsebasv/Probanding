
public class Celular {

	private String compania;
	private String numeroCelular;
	
	public Celular ( String compania, String numeroCelular){
		this.compania = compania;
		this.numeroCelular = numeroCelular;
	}

	// ---------- ----------------- HASHCODE, EQUALS, TOSTRING -----------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((compania == null) ? 0 : compania.hashCode());
		result = prime * result
				+ ((numeroCelular == null) ? 0 : numeroCelular.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celular other = (Celular) obj;
		if (compania == null) {
			if (other.compania != null)
				return false;
		} else if (!compania.equals(other.compania))
			return false;
		if (numeroCelular == null) {
			if (other.numeroCelular != null)
				return false;
		} else if (!numeroCelular.equals(other.numeroCelular))
			return false;
		return true;
	}
	
	public String toString (){
		return this.getCompania()+" -- "+this.getNumeroCelular();
	}
	
	
	// --------------------------- GETTER Y SETTERS  -----------------------------------

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
	
	
}
