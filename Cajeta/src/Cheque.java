import org.joda.time.LocalDate;
 
public class Cheque {

	

	private final long numeroCheque;
	private final Cliente emisor;
	private final Cliente destinatario;
	private final LocalDate fechaEmision;
	private final LocalDate fechaCobro;
	private final double monto;
	private boolean cobrado;

	public Cheque ( long numeroCheque, Cliente emisor, Cliente destinatario, double monto, LocalDate fechaCobro){
		this.numeroCheque = numeroCheque;
		this.emisor = emisor;
		this.destinatario = destinatario;
		this.monto = monto;
		this.fechaEmision = new LocalDate();
		this.fechaCobro = fechaCobro;
		this.cobrado = false;
	}

	public boolean isCobrado() {
		return cobrado;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	public long getNumeroCheque() {
		return numeroCheque;
	}

	public Cliente getEmisor() {
		return emisor;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public LocalDate getFechaCobro() {
		return fechaCobro;
	}

	public double getMonto() {
		return monto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cobrado ? 1231 : 1237);
		result = prime * result
				+ ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result + ((emisor == null) ? 0 : emisor.hashCode());
		result = prime * result
				+ ((fechaCobro == null) ? 0 : fechaCobro.hashCode());
		result = prime * result
				+ ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		long temp;
		temp = Double.doubleToLongBits(monto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (numeroCheque ^ (numeroCheque >>> 32));
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
		Cheque other = (Cheque) obj;
		if (cobrado != other.cobrado)
			return false;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (emisor == null) {
			if (other.emisor != null)
				return false;
		} else if (!emisor.equals(other.emisor))
			return false;
		if (fechaCobro == null) {
			if (other.fechaCobro != null)
				return false;
		} else if (!fechaCobro.equals(other.fechaCobro))
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (Double.doubleToLongBits(monto) != Double
				.doubleToLongBits(other.monto))
			return false;
		if (numeroCheque != other.numeroCheque)
			return false;
		return true;
	}
	
}
