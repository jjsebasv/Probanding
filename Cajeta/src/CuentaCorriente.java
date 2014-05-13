import java.util.HashSet;
import java.util.Set;


public class CuentaCorriente extends Cuenta {

	private final double impDebitosYCredios = 0.006;
	
	private Set<Cheque> chequesEmitidos;
	private Set<Cheque> chequesRechazados;
	private Set<Cheque> chequera;
	private double giroEnDescubierto;
	

	public CuentaCorriente(long CBU, long numeroCuenta, double giroEnDescubierto) {
		super(CBU, numeroCuenta);
		chequesEmitidos = new HashSet<Cheque>();
		chequesRechazados = new HashSet<Cheque>();
		chequera = new HashSet<Cheque>();
		this.giroEnDescubierto = giroEnDescubierto;
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

	public Set<Cheque> getChequera() {
		return chequera;
	}

	public void setChequera(Set<Cheque> chequera) {
		this.chequera = chequera;
	}

	public double getGiroEnDescubierto() {
		return giroEnDescubierto;
	}

	public void setGiroEnDescubierto(double giroEnDescubierto) {
		this.giroEnDescubierto = giroEnDescubierto;
	}

	public double getImpDebitosYCredios() {
		return impDebitosYCredios;
	}
	
}
