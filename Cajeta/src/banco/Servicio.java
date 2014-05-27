package banco;
import org.joda.time.LocalDate;



public class Servicio{
	  
	private final String tipo;
	private final LocalDate fechaVencimiento;
	private final LocalDate fechaPago;
	private final long numeroPagoElectronico;
	private final double monto;
	private boolean pago = false;
	private final double impuesto = 50.0;
	
	public Servicio ( String tipo, double monto, LocalDate fechaPago, LocalDate fechaVencimiento, long numeroPagoElectronico ){
		this.monto = monto;
		this.tipo = tipo;
		this.fechaPago = fechaPago;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroPagoElectronico = numeroPagoElectronico;
	}

	// -------------- HashCode & Equals --------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fechaPago == null) ? 0 : fechaPago.hashCode());
		result = prime
				* result
				+ ((fechaVencimiento == null) ? 0 : fechaVencimiento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(monto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ (int) (numeroPagoElectronico ^ (numeroPagoElectronico >>> 32));
		result = prime * result + (pago ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Servicio other = (Servicio) obj;
		if (fechaPago == null) {
			if (other.fechaPago != null)
				return false;
		} else if (!fechaPago.equals(other.fechaPago))
			return false;
		if (fechaVencimiento == null) {
			if (other.fechaVencimiento != null)
				return false;
		} else if (!fechaVencimiento.equals(other.fechaVencimiento))
			return false;
		if (Double.doubleToLongBits(monto) != Double
				.doubleToLongBits(other.monto))
			return false;
		if (numeroPagoElectronico != other.numeroPagoElectronico)
			return false;
		if (pago != other.pago)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	// --------------- Getters  & Setters ----------------
	public double getImpuesto(){
		return this.impuesto;
	}
	
	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public String getTipo() {
		return tipo;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public long getNumeroPagoElectronico() {
		return numeroPagoElectronico;
	}

	public double getMonto() {
		return monto;
	}
	

	
		

	
}
