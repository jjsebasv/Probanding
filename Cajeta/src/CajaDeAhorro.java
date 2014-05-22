


public class CajaDeAhorro extends Cuenta {

	private final double IMP_AL_CHEQUE = 0.12;
	
	public CajaDeAhorro() {
		super();
	}
	
	public void cobrarImpuestoCheque( double monto){
		this.setSaldoActual(getSaldoActual()-monto*IMP_AL_CHEQUE);
	}
	
	
	public String toString(){
		int cuenta = (int) (this.getNumeroCuenta() / 10);
		int dv = (int) (this.getNumeroCuenta() % 10);
		return "Caja de Ahorro: "+cuenta+"/"+dv;
	}

		// TRANSFERENCIA //
	
	public void transferir(double monto, Cuenta cuentaDestino) {	
		Banco.recuperarMiBanco().transferencia(monto, this, cuentaDestino);
		Movimiento mov = new Movimiento("TRANSFENCIA A "+cuentaDestino.toString(), monto*(-1), null);
		this.movimientos.push(mov);
	}
	
	public void transferirPorCbu(double monto, long CBUdestino) {
		Banco.recuperarMiBanco().transferenciaPorCbu(monto, this, CBUdestino);
		Movimiento mov = new Movimiento("TRANSFENCIA A "+CBUdestino, monto*(-1), null);
		this.movimientos.push(mov);
	}
	
	
		// EXTRACCION //

	public void extraccion(double monto) {
		Banco.recuperarMiBanco().extraccion(this, monto);
		Movimiento mov = new Movimiento("EXTRACCION", monto, null);
		this.movimientos.push(mov);
	}

	
	// DEPOSITO //
	
	public void depositar(double monto) {
		Banco.recuperarMiBanco().deposito(this, monto);
		Movimiento mov = new Movimiento("DEPOSITO EFECTIVO", monto, null);
		this.movimientos.push(mov);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(IMP_AL_CHEQUE);
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
		CajaDeAhorro other = (CajaDeAhorro) obj;
		if (Double.doubleToLongBits(IMP_AL_CHEQUE) != Double
				.doubleToLongBits(other.IMP_AL_CHEQUE))
			return false;
		return true;
	}

	public double getIMP_AL_CHEQUE() {
		return IMP_AL_CHEQUE;
	}

	public void depositar(Cheque cheque) {
		Banco.recuperarMiBanco().deposito(cheque, this);
		this.cobrarImpuestoCheque(cheque.getMonto());
		Movimiento mov = new Movimiento("DEPOSITO CHEQUE", cheque.getMonto(), null);
		this.movimientos.push(mov);
	}

	
	public void cobrarConsumo(Consumo consumo) {
		// TODO Auto-generated method stub
		
	}
	
	


}
