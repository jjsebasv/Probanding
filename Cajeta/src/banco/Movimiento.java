package banco;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import org.joda.time.LocalDate;

public class Movimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String tipo;
	private final LocalDate fecha;
	private final double monto;
	private final int codigoDeTransaccion = this.hashCode();
	private Tarjeta tarjeta;
 
	public Movimiento ( String tipo, double monto, Tarjeta tarjeta){
		this.tipo = tipo;
		this.tarjeta = tarjeta;
		this.fecha = new LocalDate();
		this.monto = monto;
	}
	
	 
	public void impresionComprobante () throws IOException{
		File c = new File(""); 
		
		try {
			FileWriter w = new FileWriter(c);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write("*****************************************************************");
			bw.append("*****************************************************************");
			bw.append(" FECHA: "+this.fecha);
			bw.append(" TIPO: "+tipo);
			bw.append("MONTO: "+monto);
			bw.append("NUMERO DE TRANSACCION: "+ codigoDeTransaccion);
			bw.append("*****************************************************************");
			bw.append("*****************************************************************");
			w.close();
			bw.close();
			
		} catch(IOException e){};
		
		
	}
	
	// --------------------------- HASHCODE, EQUALS, TOSTRING -----------------------------------

	
	public String toString() {
		return "Movimiento [tipo=" + tipo + ", fecha=" + fecha + ", monto="
				+ monto + "]";
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoDeTransaccion;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		long temp;
		temp = Double.doubleToLongBits(monto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}


	
	// --------------------------- GETTERS  -----------------------------------
	


	public String getTipo() {
		return tipo;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public double getMonto() {
		return monto;
	}




	public int getCodigoDeTransaccion() {
		return codigoDeTransaccion;
	}
	
	
}
