import java.util.HashSet;
import java.util.Set;

public class CuentaCorriente extends Cuenta {

	private final double IMP_DEBITOS_Y_CREDITOS  = 0.006;
	
	private Set<Cheque> chequesEmitidos;
	private Set<Cheque> chequesRechazados;
	private double giroEnDescubierto;
	

	public CuentaCorriente(long CBU, long numeroCuenta, double giroEnDescubierto) {
		super(CBU, numeroCuenta);
		chequesEmitidos = new HashSet<Cheque>();
		chequesRechazados = new HashSet<Cheque>();
		this.giroEnDescubierto = giroEnDescubierto;
	} 

	
	// --------------------------- OTROS  -----------------------------------

	
	public void cobrarImpuesto (double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_DEBITOS_Y_CREDITOS);
	}
	
	public void cobrarCheque ( Cheque cheque ){
		this.setSaldoActual(getSaldoActual()-cheque.getMonto());
		this.cobrarImpuesto(cheque.getMonto());
		cheque.setCobrado(true);
	}
	
	// --------------------------- DEPOSITAR, EXTRAER, TRANSFERIR  -----------------------------------

	public void extraccion(double monto) {
		this.setSaldoActual(getSaldoActual()-monto);
		this.cobrarImpuesto(monto);
	}

	public void depositar(double monto) {
		this.setSaldoActual(getSaldoActual()+monto);
		this.cobrarImpuesto(monto);
	}

	public void depositar(Cheque cheque) {
		this.setSaldoActual(getSaldoActual()+cheque.getMonto());
		this.cobrarImpuesto(cheque.getMonto());
	}
	
	public void transferir(double monto, Cuenta cuentaDestino) {
		this.setSaldoActual(getSaldoActual()-monto);
		this.cobrarImpuesto(monto);
		cuentaDestino.depositar(monto);
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
			return "CUENTA CORRIENTE: "+cuenta+"/"+dv;
		}

}
