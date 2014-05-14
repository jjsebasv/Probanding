import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;


public abstract class Cuenta {

	private final long CBU;
	private final LocalDate fechaAlta;
	private double saldoActual;
	private final long numeroCuenta;
	private Map<String,Movimiento> ultimosMovimientos; 
	// que sean 10, el key es la fecha. 
	
	public Cuenta ( long CBU, long numeroCuenta){
		this.saldoActual = 0;
		this.CBU = CBU;
		this.numeroCuenta = numeroCuenta;
		this.fechaAlta = new LocalDate();
		ultimosMovimientos = new HashMap<String,Movimiento>();
	}
	
	public abstract void transferir( double monto, Cuenta cuentaDestino);
	public abstract void extraccion( double monto );
	public abstract void depositar ( double monto );
	public abstract void depositar ( Cheque cheque );

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual; 
	}
	
	
	public Map<String, Movimiento> getUltimosMovimientos() {
		return ultimosMovimientos;
	}

	public void setUltimosMovimientos(Map<String, Movimiento> ultimosMovimientos) {
		this.ultimosMovimientos = ultimosMovimientos;
	}

	public long getCBU() {
		return CBU;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public long getNumeroCuenta() {
		return numeroCuenta;
	}

	// IMPRIME EJ: CUENTA: 123/4. 
	public String toString(){
		int cuenta = (int) (this.numeroCuenta / 10);
		int dv = (int) (this.numeroCuenta % 10);
		return "CUENTA: "+cuenta+"/"+dv;
	}
	
	
	
}
