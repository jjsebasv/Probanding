import org.joda.time.LocalDate;

public class Resumen {

	private final double PORCENTAJE = 0.3;
	private final long numeroResumen;
	private final double monto;
	private final double pagoMinimo;
	private final LocalDate fechaCierre;
	private final LocalDate fechaVencimiento;
	private double montoAbonado;
	
	public Resumen ( long numeroResumen, double monto ){
		this.numeroResumen = numeroResumen;
		this.monto = monto;
		this.pagoMinimo = monto * PORCENTAJE;
		this.fechaCierre = new LocalDate();
		this.fechaVencimiento = this.fechaCierre.plusWeeks(2);
		
	}

	public double getMontoAbonado() {
		return montoAbonado;
	}

	public void setMontoAbonado(double montoAbonado) {
		this.montoAbonado += montoAbonado;
	}

	public long getNumeroResumen() {
		return numeroResumen;
	}

	public double getMonto() {
		return monto;
	}

	public double getPagoMinimo() {
		return pagoMinimo;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	
	
	
	
}
