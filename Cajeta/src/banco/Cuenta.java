package banco;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import org.joda.time.LocalDate;

import exception.NoPoseeSaldoExcepcion;
import exception.NoSePuedeDepositarChequeExcepcion;


public abstract class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long CBU;
	private final LocalDate fechaAlta;
	private double saldoActual;
	private final long numeroCuenta;
	protected LinkedList<Movimiento> movimientos; 
	
	public Cuenta (){
		long cantidadCuentasMonetarias = Banco.recuperarMiBanco().getListaCajasDeAhorro().size() + Banco.recuperarMiBanco().getListaCuentasCorriente().size();
		this.numeroCuenta = cantidadCuentasMonetarias+1L;
		this.saldoActual = 0;
		this.CBU = Math.abs(this.hashCode()*10000000)+this.numeroCuenta;
		this.fechaAlta = new LocalDate();
		movimientos = new LinkedList<Movimiento>();
	}
	
	public abstract void transferir( double monto, Cuenta cuentaDestino) throws NoPoseeSaldoExcepcion;
	public abstract void extraccion( double monto ) throws NoPoseeSaldoExcepcion;
	public abstract void depositar ( double monto );
	public abstract void depositar ( Cheque cheque ) throws NoSePuedeDepositarChequeExcepcion, NoPoseeSaldoExcepcion;
	public abstract void cobrarConsumo ( Consumo consumo);
	public abstract void cobrarRecargaCelular( double monto ) throws NoPoseeSaldoExcepcion;
	
	public double montoExtHoy(){
		LocalDate hoy = new LocalDate();
		double monto = 0;
		for (Movimiento mov : this.movimientos) {
			if (mov.getFecha().isBefore(hoy.plusDays(1)) && mov.getFecha().isAfter(hoy.minusDays(1)) && mov.getTipo().equals("EXTRACCION") ){
				monto +=  mov.getMonto();
			}
		}
		return monto;
	}
	
	public double montoCompHoy(){
		LocalDate hoy = new LocalDate();
		double monto = 0;
		for (Movimiento mov : this.movimientos) {
			if (mov.getFecha().isBefore(hoy.plusDays(1)) && mov.getFecha().isAfter(hoy.minusDays(1)) && mov.getTipo().equals("COMPRA") ){
				monto += mov.getMonto();
			}
		}
		return monto;
	}

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
	
	public void transferir(double monto, long nroCuenta) throws NoPoseeSaldoExcepcion{
		if ( Banco.recuperarMiBanco().getListaCajasDeAhorro().containsKey(nroCuenta))
			this.transferir(monto, Banco.recuperarMiBanco().getListaCajasDeAhorro().get(nroCuenta));
		else
			this.transferir(monto, Banco.recuperarMiBanco().getListaCuentasCorriente().get(nroCuenta));
	}
	
	public void transferenciaRecibida ( Cuenta emisora, double monto){
		this.setSaldoActual(this.getSaldoActual()+monto);
		Movimiento mov = new Movimiento("TRANFERENCIA RECIBIDA DE:"+emisora,monto,null);
		this.movimientos.push(mov);
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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (CBU ^ (CBU >>> 32));
		result = prime * result
				+ ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result
				+ ((movimientos == null) ? 0 : movimientos.hashCode());
		result = prime * result + (int) (numeroCuenta ^ (numeroCuenta >>> 32));
		long temp;
		temp = Double.doubleToLongBits(saldoActual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Cuenta other = (Cuenta) obj;
		if (CBU != other.CBU)
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (movimientos == null) {
			if (other.movimientos != null)
				return false;
		} else if (!movimientos.equals(other.movimientos))
			return false;
		if (numeroCuenta != other.numeroCuenta)
			return false;
		if (Double.doubleToLongBits(saldoActual) != Double
				.doubleToLongBits(other.saldoActual))
			return false;
		return true;
	}
	
	
}
