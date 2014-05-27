package banco;
import org.joda.time.LocalDate;
 
public class Cheque {

	

	private final long numeroCheque;
	private final CuentaCorriente emisora;
	private final Cliente destinatario;
	private final LocalDate fechaEmision;
	private final LocalDate fechaCobro;
	private final double monto;
	private boolean cobrado;

	public Cheque (Cliente destinatario, double monto, LocalDate fechaCobro, CuentaCorriente emisora){
		this.emisora = emisora;
		this.numeroCheque = this.hashCode();
		this.destinatario = destinatario;
		this.monto = monto;
		this.fechaEmision = new LocalDate();
		this.fechaCobro = fechaCobro;
		this.cobrado = false;
	}
	
	public String toString(){
		return "Cheque: "+this.hashCode();
	}

	public boolean getCobrado() {
		return cobrado;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	public long getNumeroCheque() {
		return numeroCheque;
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

	public CuentaCorriente getEmisora() {
		return emisora;
	}
	

	
}
