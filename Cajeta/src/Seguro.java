 
public class Seguro {

	// hacer algo para cobrar todos los meses.
	private final String tipo;
	private final long nroPoliza;
	private int cuotaMensual;
	
	public Seguro(String tipo, long nroPoliza, int cuotaMensual) {
		this.tipo = tipo;
		this.nroPoliza = nroPoliza;
		this.cuotaMensual = cuotaMensual;
	}

	public int getCuotaMensual() {
		return cuotaMensual;
	}

	public void setCuotaMensual(int cuotaMensual) {
		this.cuotaMensual = cuotaMensual;
	}

	public String getTipo() {
		return tipo;
	}

	public long getNroPoliza() {
		return nroPoliza;
	}
	
	
	
}
