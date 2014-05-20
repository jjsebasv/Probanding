import org.joda.time.LocalDate;

public class CajaDeAhorro extends Cuenta {

	private final double IMP_AL_CHEQUE = 0.12;
	
	public CajaDeAhorro(long numeroCuenta) {
		super(numeroCuenta);
	}
	
	public void cobrarImpuestoCheque( double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_AL_CHEQUE);
	}

	public void transferir(double monto, Cuenta cuentaDestino) {		
		this.setSaldoActual(getSaldoActual()-monto);
		cuentaDestino.depositar(monto);
		Movimiento mov = new Movimiento("TRANSFENCIA A "+cuentaDestino.getNumeroCuenta(), monto, null);
		this.movimientos.put(new LocalDate(), mov);
	}
	
	public void transferir(double monto, long CBUdestino) {
		this.setSaldoActual(getSaldoActual()-monto);
	}

	public void extraccion(double monto) {
		this.setSaldoActual(getSaldoActual()-monto);
		Movimiento mov = new Movimiento("EXTRACCION", monto, null);
		this.movimientos.put(new LocalDate(), mov);
	}

	public void depositar(double monto) {
		this.setSaldoActual(getSaldoActual()+monto);
		Movimiento mov = new Movimiento("DEPOSITO EFECTIVO", monto, null);
		this.movimientos.put(new LocalDate(), mov);
	}

	public void depositar(Cheque cheque) {
		this.setSaldoActual(this.getSaldoActual()+cheque.getMonto());
		this.cobrarImpuestoCheque(cheque.getMonto());
		Movimiento mov = new Movimiento("DEPOSITO CHEQUE", cheque.getMonto(), null);
		this.movimientos.put(new LocalDate(), mov);
	}
	
	public String toString(){
		int cuenta = (int) (this.getNumeroCuenta() / 10);
		int dv = (int) (this.getNumeroCuenta() % 10);
		return "Caja de Ahorro: "+cuenta+"/"+dv;
	}

}
