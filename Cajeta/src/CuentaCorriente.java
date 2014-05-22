import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

public class CuentaCorriente extends Cuenta {

	private final double IMP_DEBITOS_Y_CREDITOS  = 0.006;
	
	private Set<Cheque> chequesEmitidos;
	private Set<Cheque> chequesRechazados;
	private double giroEnDescubierto;
	


	public CuentaCorriente(double giroEnDescubierto) {
		super();
		chequesEmitidos = new HashSet<Cheque>();
		chequesRechazados = new HashSet<Cheque>();
		this.giroEnDescubierto = giroEnDescubierto;
	} 

	
	// --------------------------- OTROS  -----------------------------------

	


	public double getIMP_DEBITOS_Y_CREDITOS() {
		return IMP_DEBITOS_Y_CREDITOS;
	}

	
	public void cobrarImpuesto (double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_DEBITOS_Y_CREDITOS);
	}
	
	public void cobrarCheque ( Cheque cheque ){
		Banco.recuperarMiBanco().cobrarCheque(cheque, this);
		this.cobrarImpuesto(cheque.getMonto());
		Movimiento mov = new Movimiento("COBRO DE CHEQUE: "+cheque.getNumeroCheque(), cheque.getMonto()*(-1), null);
		this.movimientos.push(mov);
	}
	
	public void emitirCheque(Cliente destinatario, double monto, LocalDate fechaCobro){
		Cheque cheque = new Cheque(destinatario, monto, fechaCobro, this);
		this.chequesEmitidos.add(cheque);
	}
	
	// --------------------------- DEPOSITAR, EXTRAER, TRANSFERIR  -----------------------------------

		// EXTRACCION //
	
	public void extraccion(double monto) {
		Banco.recuperarMiBanco().extraccion(this, monto);
		this.cobrarImpuesto(monto);
		Movimiento mov = new Movimiento("EXTRACCION", monto*(-1), null);
		this.movimientos.push(mov);
	}
	
		// DEPOSITO //

	public void depositar(double monto) {
		Banco.recuperarMiBanco().deposito(this, monto);
		this.cobrarImpuesto(monto);
		Movimiento mov = new Movimiento("DEPOSITO EFECTIVO", monto, null);
		this.movimientos.push(mov);
	}

	public void depositar(Cheque cheque) {
		
		this.cobrarImpuesto(cheque.getMonto());
		Movimiento mov = new Movimiento("DEPOSITO CHEQUE", cheque.getMonto(), null);
		this.movimientos.push(mov);
	}
	
	
			// TRANSFERENCIA //
	
	public void transferir(double monto, Cuenta cuentaDestino) {
		Banco.recuperarMiBanco().transferencia(monto, this, cuentaDestino);
		this.cobrarImpuesto(monto);
		Movimiento mov = new Movimiento("TRANSFENCIA A "+cuentaDestino.getNumeroCuenta(), monto*(-1), null);
		this.movimientos.push(mov);
	}
	
	public void transferir(double monto, long CBUdestino) {
		Banco.recuperarMiBanco().transferenciaPorCbu(monto, this, CBUdestino);
		this.cobrarImpuesto(monto);
	}
	

	// --------------------------- GETTERS Y SETTERS  -----------------------------------


	public Set<Cheque> getChequesEmitidos() {
		return chequesEmitidos;
	}

	public void setChequesEmitidos(Set<Cheque> chequesEmitidos) {
		this.chequesEmitidos = chequesEmitidos;
	}

	public Set<Cheque> getChequesRechazados() {
		return chequesRechazados;
	}

	public void setChequesRechazados(Set<Cheque> chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}


	public double getGiroEnDescubierto() {
		return giroEnDescubierto;
	}

	public void setGiroEnDescubierto(double giroEnDescubierto) {
		this.giroEnDescubierto = giroEnDescubierto;
	}
	
	public String toString(){
			int cuenta = (int) (this.getNumeroCuenta() / 10);
			int dv = (int) (this.getNumeroCuenta() % 10);
			return "Cuenta Corriente: "+cuenta+"/"+dv;
		}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(IMP_DEBITOS_Y_CREDITOS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((chequesEmitidos == null) ? 0 : chequesEmitidos.hashCode());
		result = prime
				* result
				+ ((chequesRechazados == null) ? 0 : chequesRechazados
						.hashCode());
		temp = Double.doubleToLongBits(giroEnDescubierto);
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
		CuentaCorriente other = (CuentaCorriente) obj;
		if (Double.doubleToLongBits(IMP_DEBITOS_Y_CREDITOS) != Double
				.doubleToLongBits(other.IMP_DEBITOS_Y_CREDITOS))
			return false;
		if (chequesEmitidos == null) {
			if (other.chequesEmitidos != null)
				return false;
		} else if (!chequesEmitidos.equals(other.chequesEmitidos))
			return false;
		if (chequesRechazados == null) {
			if (other.chequesRechazados != null)
				return false;
		} else if (!chequesRechazados.equals(other.chequesRechazados))
			return false;
		if (Double.doubleToLongBits(giroEnDescubierto) != Double
				.doubleToLongBits(other.giroEnDescubierto))
			return false;
		return true;
	}


	@Override
	public void cobrarConsumo(Consumo consumo) {
		// TODO Auto-generated method stub
		
	}

}
