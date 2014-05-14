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
	
}
