
import org.joda.time.LocalDate;



public abstract class Tarjeta {

	private final long numeroTarjeta;
	private final LocalDate fechaEmision;
	private final LocalDate fechaVencimiento;
	private boolean status; // ver si esta habilitada o no.
	private double limiteCompra;
	private final int codigoDeSeguridad;

	

	public Tarjeta(long numeroTarjeta, double limiteCompra) {
		
		this.numeroTarjeta = numeroTarjeta;
		this.fechaEmision = new LocalDate();
		this.fechaVencimiento = this.fechaEmision.plusYears(4);
		this.status = false;
		this.limiteCompra = limiteCompra;
		this.codigoDeSeguridad =  (int)Math.floor( Math.random() *(1-11)+998);
	}

	

	// ---------------------------  TOSTRING -----------------------------------
	

	public String toString(){
		return "XXXX-XXXX-XXXX-"+this.getNumeroTarjeta()%10000;
	}
	
	public abstract void habilitarTarjeta();
	
	// --------------------------- GETTERS -Y SETTERS ----------------------------------

	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public LocalDate getFechaEmision() {
		return fechaEmision;
	}


	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public boolean isStatus() {
		return status;
	}


	public double getLimiteCompra() {
		return limiteCompra;
	}


	public int getCodigoDeSeguridad() {
		return codigoDeSeguridad;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public void setLimiteCompra(double limiteCompra) {
		this.limiteCompra = limiteCompra;
	}


	// --------------------------- HASHCODE, EQUALS  -----------------------------------

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoDeSeguridad;
		result = prime * result
				+ ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime
				* result
				+ ((fechaVencimiento == null) ? 0 : fechaVencimiento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(limiteCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ (int) (numeroTarjeta ^ (numeroTarjeta >>> 32));
		result = prime * result + (status ? 1231 : 1237);
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
		Tarjeta other = (Tarjeta) obj;
		if (codigoDeSeguridad != other.codigoDeSeguridad)
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (fechaVencimiento == null) {
			if (other.fechaVencimiento != null)
				return false;
		} else if (!fechaVencimiento.equals(other.fechaVencimiento))
			return false;
		if (Double.doubleToLongBits(limiteCompra) != Double
				.doubleToLongBits(other.limiteCompra))
			return false;
		if (numeroTarjeta != other.numeroTarjeta)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	
}
