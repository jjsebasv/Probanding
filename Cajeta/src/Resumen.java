 import java.util.Date;


public class Resumen {

	private final long numeroResumen;
	private final double monto;
	private final double pagoMinimo;
	private final String fechaCierre;
	private final String fechaVencimiento;
	private double montoAbonado;
	
	public Resumen ( long numeroResumen, double monto, double pagoMinimo, String fechaCierre,  String fechaVencimiento ){
		this.montoAbonado = 0;
		this.numeroResumen = numeroResumen;
		this.monto = monto;
		this.pagoMinimo = pagoMinimo;
		this.fechaCierre = fechaCierre;
		this.fechaVencimiento = fechaVencimiento;
		
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

	public String getFechaCierre() {
		return fechaCierre;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	
	
	
	
}
