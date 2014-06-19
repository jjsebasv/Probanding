package banco;

public class TarjetaDebito extends Tarjeta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TarjetaDeCoordenadas tarjetaCoordVinculada;
	private final static double LIMITE_COMPRA_DEBITO = 10000.00;
	private final static double LIMITE_EXTRACCION_DEBITO = 5000.00;
	
	

	public TarjetaDebito() {
		super(LIMITE_COMPRA_DEBITO ,"Visa Electron");		
	}
	
	public void habilitarTarjeta() {
		this.setStatus(true);
		
	}

	
	// --------------------------- GETTERS Y SETTERS  -----------------------------------


	public TarjetaDeCoordenadas getTarjetaCoordVinculada() {
		return tarjetaCoordVinculada;
	}

	public void setTarjetaCoordVinculada(TarjetaDeCoordenadas tarjetaCoordVinculada) {
		this.tarjetaCoordVinculada = tarjetaCoordVinculada;
	}

	
	// --------------------------- HASHCODE, EQUALS -----------------------------------

	
	public static double getLimiteExtraccionDebito() {
		return LIMITE_EXTRACCION_DEBITO;
	}


	
}
