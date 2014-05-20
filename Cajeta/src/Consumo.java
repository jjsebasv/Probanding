
public class Consumo extends Movimiento {
	
	private final String comercio;
	private final String domicilio;
	

	public Consumo(String tipo, double monto, Tarjeta tarjeta,String comercio, String domicilio) {
		super(tipo, monto, tarjeta);
		this.comercio = comercio;
		this.domicilio = domicilio;
	}
 

	// --- ------------------------ HASHCODE, EQUALS, TOSTRING -----------------------------------

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((comercio == null) ? 0 : comercio.hashCode());
		result = prime * result
				+ ((domicilio == null) ? 0 : domicilio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumo other = (Consumo) obj;
		if (comercio == null) {
			if (other.comercio != null)
				return false;
		} else if (!comercio.equals(other.comercio))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Consumo [comercio=" + comercio + ", domicilio=" + domicilio
				+ "]";
	}
	
	
	// --------------------------- GETTERS  -----------------------------------

	public String getComercio() {
		return comercio;
	}


	public String getDomicilio() {
		return domicilio;
	}
	
	
}
