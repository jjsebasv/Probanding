package banco;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

import exception.NoPoseeSaldoExcepcion;
import exception.NoSePuedeDepositarChequeExcepcion;

public class CuentaCorriente extends Cuenta {

	private final double IMP_DEBITOS_Y_CREDITOS  = 0.006;
	
	private Map<Long,Cheque> chequesEmitidos;
	private double giroEnDescubierto;
	


	public CuentaCorriente(double giroEnDescubierto) {
		super();
		chequesEmitidos = new HashMap<Long,Cheque>();
		this.giroEnDescubierto = giroEnDescubierto;
	} 

	
	// --------------------------- OTROS  -----------------------------------

	


	public double getIMP_DEBITOS_Y_CREDITOS() {
		return IMP_DEBITOS_Y_CREDITOS;
	}

	
	public void cobrarImpuesto (double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_DEBITOS_Y_CREDITOS);
		Movimiento mov = new Movimiento("LEY IMPUESTO DEBITOS Y CREDITOS", monto*IMP_DEBITOS_Y_CREDITOS*(-1),null);
		this.movimientos.push(mov);
	}
	
	public void cobrarCheque ( Cheque cheque ){
		Banco.recuperarMiBanco().cobrarCheque(cheque, this);
		Movimiento mov = new Movimiento("COBRO DE CHEQUE: "+cheque.getNumeroCheque(), cheque.getMonto()*(-1), null);
		this.movimientos.push(mov);
		this.cobrarImpuesto(cheque.getMonto());
	}
	
	public void emitirCheque(Cliente destinatario, double monto, LocalDate fechaCobro){
		Cheque cheque = new Cheque(destinatario, monto, fechaCobro, this);
		this.chequesEmitidos.put(cheque.getNumeroCheque(), cheque);
	}
	
	public void cobrarRecargaCelular(double monto) throws NoPoseeSaldoExcepcion{
		if ( this.getSaldoActual() + this.getGiroEnDescubierto() >= monto){
			this.setSaldoActual(this.getSaldoActual()-monto);
			this.cobrarImpuesto(monto);
			Movimiento mov = new Movimiento("RECARGA DE CELULAR: ",monto*(-1), null);
			this.movimientos.push(mov);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	// --------------------------- DEPOSITAR, EXTRAER, TRANSFERIR  -----------------------------------

		// EXTRACCION //
	
	public void extraccion(double monto) throws NoPoseeSaldoExcepcion {
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

	public void depositar(Cheque cheque) throws NoSePuedeDepositarChequeExcepcion, NoPoseeSaldoExcepcion {
		Banco.recuperarMiBanco().deposito(cheque,this);
		this.cobrarImpuesto(cheque.getMonto());
		Movimiento mov = new Movimiento("DEPOSITO CHEQUE", cheque.getMonto(), null);
		this.movimientos.push(mov);
	}
	
	
			// TRANSFERENCIA //
	
	public void transferir(double monto, Cuenta cuentaDestino) throws NoPoseeSaldoExcepcion {
		Banco.recuperarMiBanco().transferencia(monto, this, cuentaDestino);
		this.cobrarImpuesto(monto);
		Movimiento mov = new Movimiento("TRANSFENCIA A "+cuentaDestino.getNumeroCuenta(), monto*(-1), null);
		this.movimientos.push(mov);
	}
	
	public void transferir(double monto, long CBUdestino) throws NoPoseeSaldoExcepcion {
		Banco.recuperarMiBanco().transferenciaPorCbu(monto, this, CBUdestino);
		this.cobrarImpuesto(monto);
	}
	

	// --------------------------- GETTERS Y SETTERS  -----------------------------------





	public double getGiroEnDescubierto() {
		return giroEnDescubierto;
	}

	public Map<Long, Cheque> getChequesEmitidos() {
		return chequesEmitidos;
	}


	public void setChequesEmitidos(Map<Long, Cheque> chequesEmitidos) {
		this.chequesEmitidos = chequesEmitidos;
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
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(IMP_DEBITOS_Y_CREDITOS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((chequesEmitidos == null) ? 0 : chequesEmitidos.hashCode());
		temp = Double.doubleToLongBits(giroEnDescubierto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		CuentaCorriente other = (CuentaCorriente) obj;
		if (Double.doubleToLongBits(IMP_DEBITOS_Y_CREDITOS) != Double
				.doubleToLongBits(other.IMP_DEBITOS_Y_CREDITOS))
			return false;
		if (chequesEmitidos == null) {
			if (other.chequesEmitidos != null)
				return false;
		} else if (!chequesEmitidos.equals(other.chequesEmitidos))
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
