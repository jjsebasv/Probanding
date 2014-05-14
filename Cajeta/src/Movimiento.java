import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Movimiento {

	private final String tipo;
	private final String fecha;
	private final double monto;
	private final Tarjeta tarjeta;
	private final int codigoDeTransaccion = this.hashCode();
 
	public Movimiento ( String tipo, String fecha, double monto, Tarjeta tarjeta){
		this.tipo = tipo;
		this.fecha = fecha;
		this.monto = monto;
		this.tarjeta = tarjeta;  
	}
	
	 
	public void impresionComprobante () throws IOException{
		File c = new File(""); 
		
		try {
			FileWriter w = new FileWriter(c);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write("*****************************************************************");
			bw.append("*****************************************************************");
			bw.append(" FECHA: "+fecha.toString());
			bw.append(" TIPO: "+tipo);
			bw.append("MONTO: "+monto);
			bw.append("NUMERO DE TARJETA: "+ tarjeta);
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
		result = prime * result + ((tarjeta == null) ? 0 : tarjeta.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Movimiento other = (Movimiento) obj;
		if (codigoDeTransaccion != other.codigoDeTransaccion)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (Double.doubleToLongBits(monto) != Double
				.doubleToLongBits(other.monto))
			return false;
		if (tarjeta == null) {
			if (other.tarjeta != null)
				return false;
		} else if (!tarjeta.equals(other.tarjeta))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	// --------------------------- GETTERS  -----------------------------------
	


	public String getTipo() {
		return tipo;
	}


	public String getFecha() {
		return fecha;
	}


	public double getMonto() {
		return monto;
	}


	public Tarjeta getTarjeta() {
		return tarjeta;
	}


	public int getCodigoDeTransaccion() {
		return codigoDeTransaccion;
	}
	
	
}
