
public class TarjetaDebito extends Tarjeta {

	private double limiteExtraccion;
	private TarjetaDeCoordenadas tarjetaCoordVinculada;
	private final static double LIMITE_COMPRA_DEBITO = 10000.00;
	private final static double LIMITE_EXTRACCION_DEBITO = 5000.00;
	
	

	public TarjetaDebito(long numeroTarjeta) {
		
		super(numeroTarjeta, LIMITE_COMPRA_DEBITO );
		this.limiteExtraccion = LIMITE_EXTRACCION_DEBITO;
		
	}
	
	public void habilitarTarjeta() {
		this.setStatus(true);
		this.setLimiteCompra(10000);
		this.setLimiteExtraccion(5000);
		
	}

	
	// --------------------------- GETTERS Y SETTERS  -----------------------------------


	public double getLimiteExtraccion() {
		return limiteExtraccion;
	}

	public void setLimiteExtraccion(double limiteExtraccion) {
		this.limiteExtraccion = limiteExtraccion;
	}

	public TarjetaDeCoordenadas getTarjetaCoordVinculada() {
		return tarjetaCoordVinculada;
	}

	public void setTarjetaCoordVinculada(TarjetaDeCoordenadas tarjetaCoordVinculada) {
		this.tarjetaCoordVinculada = tarjetaCoordVinculada;
	}

	
	// --------------------------- HASHCODE, EQUALS -----------------------------------

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(limiteExtraccion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((tarjetaCoordVinculada == null) ? 0 : tarjetaCoordVinculada
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarjetaDebito other = (TarjetaDebito) obj;
		if (Double.doubleToLongBits(limiteExtraccion) != Double
				.doubleToLongBits(other.limiteExtraccion))
			return false;
		if (tarjetaCoordVinculada == null) {
			if (other.tarjetaCoordVinculada != null)
				return false;
		} else if (!tarjetaCoordVinculada.equals(other.tarjetaCoordVinculada))
			return false;
		return true;
	}


	
}
