

public class CajaDeAhorro extends Cuenta {

	private final double IMP_AL_CHEQUE = 0.12;
	
	public CajaDeAhorro(long CBU, long numeroCuenta) {
		super(CBU, numeroCuenta);
	}
	
	public void cobrarImpuestoCheque( double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_AL_CHEQUE);
	}

	public void transferir(double monto, Cuenta cuentaDestino) {
		this.setSaldoActual(getSaldoActual()-monto);
		cuentaDestino.depositar(monto);
	}

	public void extraccion(double monto) {
		this.setSaldoActual(getSaldoActual()-monto);
	}

	public void depositar(double monto) {
		this.setSaldoActual(getSaldoActual()+monto);
	}

	public void depositar(Cheque cheque) {
		this.setSaldoActual(this.getSaldoActual()+cheque.getMonto());
		this.cobrarImpuestoCheque(cheque.getMonto());
	}

}
