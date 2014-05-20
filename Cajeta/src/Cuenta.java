import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;


public abstract class Cuenta {

	private final long CBU;
	private final LocalDate fechaAlta;
	private double saldoActual;
	private final long numeroCuenta;
	protected Map<LocalDate,Movimiento> movimientos; 
	// que sean 10, el key es la fecha. 
	
	public Cuenta (long numeroCuenta){
		this.saldoActual = 0;
		this.CBU = Math.abs(this.hashCode()*10000000)+this.numeroCuenta;
		this.numeroCuenta = numeroCuenta;
		this.fechaAlta = new LocalDate();
		movimientos = new TreeMap<LocalDate,Movimiento>();
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
	
	public Map<LocalDate, Movimiento> getUltimosMovimientos() {
		return movimientos;
	}

	public void setUltimosMovimientos(Map<LocalDate, Movimiento> ultimosMovimientos) {
		this.movimientos = ultimosMovimientos;
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

	public void imprimirUltimosMovimientos(){
			File c;
			FileWriter w;
			
			try {
				long time = new Date().getTime();
				
				c = new File(this.getNumeroCuenta() + time + ".txt");
				w = new FileWriter(c);
				BufferedWriter bw = new BufferedWriter(w);
				bw.write("***************************"+" RESUMEN "+"******************************");
				bw.newLine();
				for (LocalDate fecha : this.movimientos.keySet()) {
					bw.write(this.movimientos.get(fecha).toString());
					bw.newLine();
				}
				bw.close();
				w.close();
				
			} catch(IOException e){
				System.out.println("ERROR: "+e.getMessage());
			};
			
	}
	
	
}
