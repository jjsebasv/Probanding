
import java.util.Date;
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
	
	
	private Map<Long,CuentaCredito> cuentasCredito;
	private Map<Long, Cuenta> cuentasMonetarias;
	private Set<Movimiento> movimientos;
	private Set<Seguro> seguros;
	private Map<Long,Tarjeta> tarjetasCredito;
	private Set<Servicio> listaServicios;
	
	
	public Cliente ( long numeroCliente ){
		this.numeroCliente = numeroCliente;
		cuentasCredito = new HashMap<Long,CuentaCredito>();
		cuentasMonetarias = new HashMap<Long,Cuenta>();
		seguros = new HashSet<Seguro>();
		tarjetasCredito = new HashMap<Long,Tarjeta>();
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


	public Set<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(Set<Servicio> listaServicios) {
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



	public void generacionClavePIN( int clavePIN){
		this.clavePin = clavePIN;
	}
	
	public void generacionClaveHomeBanking( int claveHomeBanking){
		this.claveHomeBanking = claveHomeBanking;
	}
	
	public void generacionDeUsuario( String usuario ){
		this.usuario = usuario;
	}

	
	
	// ------------------ Metodos ---------------------------------
	
	
		// ------ Servicios -----
	public void pagarServicio(String tipo, long numeroCuenta){
		boolean eServicio = false;
		
		
		if(getListaServicios() == null){
			System.out.println("No hay servicios a pagar.");
			return;
		}
		if(this.cuentasMonetarias == null){
			System.out.println("No registra cuentas a su nombre.");
			return;
		}
		
		Iterator<Servicio> itS = this.listaServicios.iterator();
		while (itS.hasNext() && !eServicio ) {
			Servicio s = (Servicio) itS.next();
			if ( s.getTipo().equals(tipo) ) {
				if ( this.cuentasMonetarias.containsKey(numeroCuenta) ){
					double impuesto = 0.0;
					LocalDate fecha = new LocalDate();
					if( s.getFechaVencimiento().before(fecha.toDate())){
						impuesto = s.getImpuesto();
						System.out.println("Por pago fuera de termino, se aplicara un impuesto de " + impuesto);
					}
					double saldoViejo = this.cuentasMonetarias.get(numeroCuenta).getSaldoActual();
					this.cuentasMonetarias.get(numeroCuenta).setSaldoActual(saldoViejo - s.getMonto() - impuesto);
					s.setPago(true);
				} 
				else{ 
					System.out.println("No existe esa cuenta.");
					return;
				}
				eServicio = true;
			}
		}
		if (eServicio)
			System.out.println("No existe el servicio que quiere pagar.");
				
		  
	}
	
	public void agregarServicio(String tipo, double monto, Date fechaPago, Date fechaVencimiento, long numeroPagoElectronico){
		if( this.listaServicios == null ){
			Set<Servicio> listaServicio = new HashSet<>();
			this.listaServicios = listaServicio;
		}
		Servicio servicioNuevo = new Servicio( tipo, monto, fechaPago, fechaVencimiento, numeroPagoElectronico);
		
		this.listaServicios.add(servicioNuevo);
	}

	public void listaServicios(){
		if(this.listaServicios == null)
			System.out.println("No registra servicios a su nombre.");
		else{
			for (Servicio s : this.listaServicios) {
				System.out.println(s.getTipo()); 
				if (!s.isPago()) {
					System.out.println("SERVICIO NO PAGO");
					System.out.println("Vence " + s.getFechaVencimiento());
				}
				 
			}   
		}  
	} 

		// ----- Cuentas -------
	
	
}



