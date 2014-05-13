 import java.util.HashSet;
import java.util.Set;


public class Cliente {

	private int claveHomeBanking;
	private int clavePin;
	private long CBU;
	private final long numeroCliente;
	private String usuario;
	private TarjetaDebito tajetaDeDebito;
	
	private Set<CuentaCredito> cuentasCredito;
	private Set<Cuenta> cuentasMonetarias;
	private Set<Movimiento> movimientos;
	private Set<Seguro> seguros;
	private Set<Tarjeta> tarjetasCredito;
	
	public Cliente ( long numeroCliente ){
		this.numeroCliente = numeroCliente;
		cuentasCredito = new HashSet<CuentaCredito>();
		cuentasMonetarias = new HashSet<Cuenta>();
		seguros = new HashSet<Seguro>();
		tarjetasCredito = new HashSet<Tarjeta>();
		movimientos = new HashSet<Movimiento>();

	}
	
	// --------------------------- HASH CODE AND EQUALS, TO STRING   -----------------------------------

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (CBU ^ (CBU >>> 32));
		result = prime
				* result
				+ ((cuentasMonetarias == null) ? 0 : cuentasMonetarias
						.hashCode());
		result = prime * result
				+ ((movimientos == null) ? 0 : movimientos.hashCode());
		result = prime * result
				+ (int) (numeroCliente ^ (numeroCliente >>> 32));
		result = prime * result + ((seguros == null) ? 0 : seguros.hashCode());
		result = prime * result
				+ ((tajetaDeDebito == null) ? 0 : tajetaDeDebito.hashCode());
		result = prime * result
				+ ((tarjetasCredito == null) ? 0 : tarjetasCredito.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Cliente other = (Cliente) obj;
		if (CBU != other.CBU)
			return false;
		if (cuentasMonetarias == null) {
			if (other.cuentasMonetarias != null)
				return false;
		} else if (!cuentasMonetarias.equals(other.cuentasMonetarias))
			return false;
		if (movimientos == null) {
			if (other.movimientos != null)
				return false;
		} else if (!movimientos.equals(other.movimientos))
			return false;
		if (numeroCliente != other.numeroCliente)
			return false;
		if (seguros == null) {
			if (other.seguros != null)
				return false;
		} else if (!seguros.equals(other.seguros))
			return false;
		if (tajetaDeDebito == null) {
			if (other.tajetaDeDebito != null)
				return false;
		} else if (!tajetaDeDebito.equals(other.tajetaDeDebito))
			return false;
		if (tarjetasCredito == null) {
			if (other.tarjetasCredito != null)
				return false;
		} else if (!tarjetasCredito.equals(other.tarjetasCredito))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
		// long a String??
		public String toString(){
			return "Numero cliente: "+this.getNumeroCliente();
		}

	
	
	// --------------------------- GETTERS Y SETTERS   -----------------------------------


	
	public int getClaveHomeBanking() {
		return claveHomeBanking;
	}




	public void setClaveHomeBanking(int claveHomeBanking) {
		this.claveHomeBanking = claveHomeBanking;
	}



	public int getClavePin() {
		return clavePin;
	}



	public void setClavePin(int clavePin) {
		this.clavePin = clavePin;
	}



	public long getCBU() {
		return CBU;
	}



	public void setCBU(long cBU) {
		CBU = cBU;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public Set<CuentaCredito> getCuentasCredito() {
		return cuentasCredito;
	}



	public void setCuentasCredito(Set<CuentaCredito> cuentasCredito) {
		this.cuentasCredito = cuentasCredito;
	}



	public Set<Cuenta> getCuentasMonetarias() {
		return cuentasMonetarias;
	}



	public void setCuentasMonetarias(Set<Cuenta> cuentasMonetarias) {
		this.cuentasMonetarias = cuentasMonetarias;
	}



	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}



	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}



	public Set<Seguro> getSeguros() {
		return seguros;
	}



	public void setSeguros(Set<Seguro> seguros) {
		this.seguros = seguros;
	}



	public Set<Tarjeta> getTarjetasCredito() {
		return tarjetasCredito;
	}



	public void setTarjetasCredito(Set<Tarjeta> tarjetas) {
		this.tarjetasCredito = tarjetas;
	}



	public long getNumeroCliente() {
		return numeroCliente;
	}



	public TarjetaDebito getTajetaDeDebito() {
		return tajetaDeDebito;
	}



	public void setTajetaDeDebito(TarjetaDebito tajetaDeDebito) {
		this.tajetaDeDebito = tajetaDeDebito;
	}



	public void generacionClavePIN( int clavePIN){
		this.clavePin = clavePIN;
	}
	
	public void generacionClaveHomeBanking( int claveHomeBanking){
		this.claveHomeBanking = claveHomeBanking;
	}
	
	public void generacionDeUsuario( String usuario ){
		this.usuario = usuario;
	}

	
	

}
