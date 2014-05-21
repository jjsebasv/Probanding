import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;


public abstract class Cuenta {

	private final long CBU;
	private final LocalDate fechaAlta;
	private double saldoActual;
	private final long numeroCuenta;
	protected LinkedList<Movimiento> movimientos; 
	// que sean 10, el key es la fecha. 
	
	public Cuenta (long numeroCuenta){
		this.saldoActual = 0;
		this.CBU = Math.abs(this.hashCode()*10000000)+this.numeroCuenta;
		this.numeroCuenta = numeroCuenta;
		this.fechaAlta = new LocalDate();
		movimientos = new LinkedList<Movimiento>();
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

				int j =( this.movimientos.size() >  20 )? 20 : this.movimientos.size() ;
				for (int i = 0; i < j ; i++) {
					Movimiento mov = this.movimientos.get(i);
					bw.write(mov.toString());
					bw.newLine();
				}
				bw.close();
				w.close();
				
			} catch(IOException e){
				System.out.println("ERROR: "+e.getMessage());
			};
			
	}

	public LinkedList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
}
