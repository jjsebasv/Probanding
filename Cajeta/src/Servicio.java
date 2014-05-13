 import java.util.Date;


public class Servicio extends Movimiento {
	
	private final Date fechaVencimiento;
	private final Date fechaPago;
	private final long numeroPagoElectronico;
	
	public Servicio ( String tipo, double monto, Date fechaPago, Date fechaVencimiento, long numeroPagoElectronico ){
		super(tipo, fechaPago, monto);
		this.fechaPago = fechaPago;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroPagoElectronico = numeroPagoElectronico;
	}


	

}
