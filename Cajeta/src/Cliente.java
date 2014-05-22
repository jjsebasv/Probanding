
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;


public class Cliente {

	private int claveHomeBanking;
	private int clavePin;
	private long CBU;
	private final long numeroCliente;
	private String usuario;
	private TarjetaDebito tajetaDeDebito;
	private Celular celular;
	
	
	public Celular getCelular() {
		return celular;
	}

	public void setCelular(Celular celular) {
		this.celular = celular;
	}

	private Map<Long,CuentaCredito> cuentasCredito;
	private Map<Long, Cuenta> cuentasMonetarias;
	private Set<Movimiento> movimientos;
	private Set<Seguro> seguros;
	private Map<Long,Tarjeta> tarjetasCredito;
	private Map<Long,Servicio> listaServicios;
	
	
	public Cliente ( long numeroCliente ){
		this.numeroCliente = numeroCliente;
		cuentasCredito = new HashMap<Long,CuentaCredito>();
		cuentasMonetarias = new HashMap<Long,Cuenta>();
		seguros = new HashSet<Seguro>();
		tarjetasCredito = new HashMap<Long,Tarjeta>();
		movimientos = new HashSet<Movimiento>();
		listaServicios = new HashMap<Long,Servicio>();

	}
	
	// --------------------------- HASH CODE AND EQUALS, TO STRING   -----------------------------------

	
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
	
		
		public String toString(){
			return "Numero cliente: "+this.getNumeroCliente();
		}

	
	
	// --------------------------- GETTERS Y SETTERS   -----------------------------------


	public Map<Long, Tarjeta> getTarjetasCredito() {
		return tarjetasCredito;
	}	
		
		
	public int getClaveHomeBanking() {
		return claveHomeBanking;
	}

	public Map<Long, Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(Map<Long, Servicio> listaServicios) {
		this.listaServicios = listaServicios;
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






	public Map<Long, CuentaCredito> getCuentasCredito() {
		return cuentasCredito;
	}

	public void setCuentasCredito(Map<Long, CuentaCredito> cuentasCredito) {
		this.cuentasCredito = cuentasCredito;
	}

	public void setTarjetasCredito(Map<Long, Tarjeta> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}

	public Map<Long, Cuenta> getCuentasMonetarias() {
		return cuentasMonetarias;
	}

	public void setCuentasMonetarias(Map<Long, Cuenta> cuentasMonetarias) {
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




	public long getNumeroCliente() {
		return numeroCliente;
	}



	public TarjetaDebito getTajetaDeDebito() {
		return tajetaDeDebito;
	}



	public void setTajetaDeDebito(TarjetaDebito tajetaDeDebito) {
		this.tajetaDeDebito = tajetaDeDebito;
	}
	
	
	// --------------------------- SERVICIOS  -----------------------------------
	
	
		
	public void pagarServicio(String tipo, long nroCuenta){
		
		if( this.listaServicios.isEmpty() ){
			throw new NoHayServicios();
		}
		else if( !this.getCuentasMonetarias().containsKey(nroCuenta) ){
			throw new NoExisteLaCuentaExcepcion();
		}
		else {
			double impuesto = 0.0;
			double monto = this.getListaServicios().get(tipo).getMonto();
			LocalDate fecha = new LocalDate();
			if( this.getListaServicios().get(tipo).getFechaVencimiento().isBefore(fecha)){
				impuesto = this.getListaServicios().get(tipo).getImpuesto();
			}
			// diferencias ca y cc
			if ( monto <= this.getCuentasMonetarias().get(nroCuenta).getSaldoActual() )
			{
				this.cuentasMonetarias.get(nroCuenta).setSaldoActual(this.getCuentasMonetarias().get(nroCuenta).getSaldoActual()-monto-impuesto);
				this.listaServicios.get(tipo).setPago(true);
			} 
			else{
				throw new NoPoseeSaldoExcepcion();
			}
		}
	}
	
	public void agregarServicio(String tipo, double monto, LocalDate fechaPago, LocalDate fechaVencimiento, long numeroPagoElectronico){
		Servicio servicioNuevo = new Servicio( tipo, monto, fechaPago, fechaVencimiento, numeroPagoElectronico);
		this.listaServicios.put(numeroPagoElectronico, servicioNuevo);
	}
	
	public double disponibleDebitoExtraccion(){
		double aux = 0;
		if ( this.tajetaDeDebito != null ){
			for (Cuenta cuenta : this.getCuentasMonetarias().values()) {
				aux += cuenta.montoExtHoy();
			}
			aux = this.tajetaDeDebito.getLimiteExtraccionDebito() - aux;
		}
		return aux;
	}
	
	public double disponibleDebitoCompra(){
		double aux = 0;
		if ( this.tajetaDeDebito != null ){
			for (Cuenta cuenta : this.getCuentasMonetarias().values()) {
				aux += cuenta.montoCompHoy();
			}
			aux = this.tajetaDeDebito.getLimiteCompra() - aux;
		}
		return aux;
	}
	
	
	
}

	



