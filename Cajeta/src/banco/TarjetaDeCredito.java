package banco;

public class TarjetaDeCredito extends Tarjeta {

	private double porcentajeLimite;
	private CuentaCredito cuentaCredito;
	
	public TarjetaDeCredito(double limiteCompra, double porcentajeLimite, String marca, CuentaCredito cuenta) {
		super(limiteCompra,marca);
		this.porcentajeLimite = porcentajeLimite;
		this.cuentaCredito = cuenta;
	}
	
	// TIRAR UNA EXCEPCION SI YA ESTA HABILITADA Y NO HACER NADA.
	public void habilitarTarjeta (){
		this.setStatus(true);
	}

	
	// --------------------------- GETTER Y SETTERS  -----------------------------------


	public double disponibleUnPago() {
		return this.cuentaCredito.disponibleUnPago()*this.porcentajeLimite;
	}


	public double disponibleCuotas() {
		return this.cuentaCredito.disponibleCuotas()*this.porcentajeLimite;
	}


}
