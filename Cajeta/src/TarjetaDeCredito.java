
public class TarjetaDeCredito extends Tarjeta {

	private double disponibleUnPago;
	private double disponibleCuotas;
	private double porcentajeLimite;
	
	public TarjetaDeCredito(long numeroTarjeta, double limiteCompra, double porcentajeLimite) {
		super(numeroTarjeta, limiteCompra);
		this.porcentajeLimite = porcentajeLimite;
	}
	
	// TIRAR UNA EXCEPCION SI YA ESTA HABILITADA Y NO HACER NADA.
	public void habilitarTarjeta (){
		this.setStatus(true);
		this.setDisponibleCuotas(this.getLimiteCompra()*this.porcentajeLimite);
		this.setDisponibleUnPago(this.getLimiteCompra()*this.porcentajeLimite);
	}

	
	// --------------------------- GETTER Y SETTERS  -----------------------------------


	public double getDisponibleUnPago() {
		return disponibleUnPago;
	}

	public void setDisponibleUnPago(double disponibleUnPago) {
		this.disponibleUnPago = disponibleUnPago;
	}

	public double getDisponibleCuotas() {
		return disponibleCuotas;
	}

	public void setDisponibleCuotas(double disponibleCuotas) {
		this.disponibleCuotas = disponibleCuotas;
	}

	
}
