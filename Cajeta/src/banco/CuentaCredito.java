package banco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;


public class CuentaCredito implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final Cliente titular;
		private final long nroCuenta;
		private final LocalDate fechaAlta;
		private final String marca;
		private double limiteFinanciacion;
		private boolean debitoAutomatico;
		private final double TEM = 1.0407;
		
		private Set<TarjetaDeCredito> adicionales;
		private Tarjeta tarTitular;
		private Map<Long,Resumen> resumenes;
		private ArrayList<Consumo> consumosDelPeriodo;
				
		public CuentaCredito ( Cliente titular, long nroCuenta, String marca, Tarjeta tarTitular, double limiteFinanciacion ){
			this.titular = titular;
			this.nroCuenta = nroCuenta;
			this.fechaAlta = new LocalDate();
			this.marca = marca;
			this.tarTitular = tarTitular;
			this.limiteFinanciacion = limiteFinanciacion;
			adicionales = new HashSet<TarjetaDeCredito>();
			resumenes = new HashMap<Long,Resumen>();
			consumosDelPeriodo = new ArrayList<Consumo>();
			
		}
		
		public void removerConsumos(){
			for (Consumo consumo : this.consumosDelPeriodo) {
				this.consumosDelPeriodo.remove(consumo);
			}
		}
		
		public double montoConsumosCuotas(){
			double cuotas = 0;
			for (Consumo consumo : this.consumosDelPeriodo) {
				if ( consumo.getCuotas() ){
					cuotas += consumo.getMonto();
				}
			}
			return cuotas;
		}
		
		public double montoConsumosUnPago(){
			double cuotas = 0;
			for (Consumo consumo : this.consumosDelPeriodo) {
				if ( !consumo.getCuotas() ){
					cuotas += consumo.getMonto();
				}
			}
			return cuotas;
		}
		
		public void cierreLiquidacion ( long nroResumen ){
			Set<Consumo> consumos = new HashSet<Consumo>();
			double monto = 0;
			double saldoAnterior = 0;
			
			if ( this.resumenes.size() > 0 )
				saldoAnterior = this.resumenes.get(this.resumenes.size()).getMonto() - this.resumenes.get(this.resumenes.size()).getMontoAbonado();
			
			for (Consumo consumo : consumosDelPeriodo) {
				monto += consumo.getMonto();
				consumos.add(consumo);
			}
			if ( saldoAnterior >= 0 ){
				monto += saldoAnterior * TEM; // debia plata
			}
			else
				monto += saldoAnterior; // tenia saldo a favor
			

			Resumen resumen = new Resumen(nroResumen , monto, this.titular, consumos);
			this.resumenes.put(this.resumenes.size()+1L, resumen);
			removerConsumos();
		}
		
		public void abonarResumen ( double monto ){
			this.resumenes.get(this.resumenes.size()).setMontoAbonado(monto);
		}
		
		// --------------------------- GETTERS Y SETTERS  -----------------------------------


		public Set<TarjetaDeCredito> getAdicionales() {
			return adicionales;
		}

		public void setAdicionales(Set<TarjetaDeCredito> adicionales) {
			this.adicionales = adicionales;
		}

		public Tarjeta getTarTitular() {
			return tarTitular;
		}

		public void setTarTitular(Tarjeta tarTitular) {
			this.tarTitular = tarTitular;
		}

		public Map<Long, Resumen> getResumenes() {
			return resumenes;
		}

		public void setResumenes(Map<Long, Resumen> resumenes) {
			this.resumenes = resumenes;
		}

		public ArrayList<Consumo> getConsumosDelPeriodo() {
			return consumosDelPeriodo;
		}

		public void setConsumosDelPeriodo(ArrayList<Consumo> consumosDelPeriodo) {
			this.consumosDelPeriodo = consumosDelPeriodo;
		}

		public double getLimiteFinanciacion() {
			return limiteFinanciacion;
		}

		public void setLimiteFinanciacion(double limiteFinanciacion) {
			this.limiteFinanciacion = limiteFinanciacion;
		}

		public boolean isDebitoAutomatico() {
			return debitoAutomatico;
		}

		public void setDebitoAutomatico(boolean debitoAutomatico) {
			this.debitoAutomatico = debitoAutomatico;
		}

		public Cliente getTitular() {
			return titular;
		}

		public long getNroCuenta() {
			return nroCuenta;
		}

		public LocalDate getFechaAlta() {
			return fechaAlta;
		}

		public String getMarca() {
			return marca;
		}

		public double getTEM() {
			return TEM;
		}


		
		// --------------------------- HASH CODE, EQUALS, TO STRING -----------------------------------

		
		public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adicionales == null) ? 0 : adicionales.hashCode());
		result = prime
				* result
				+ ((consumosDelPeriodo == null) ? 0 : consumosDelPeriodo
						.hashCode());
		result = prime * result + (debitoAutomatico ? 1231 : 1237);
		result = prime * result
				+ ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(limiteFinanciacion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = (int) (prime * result + nroCuenta);
		result = prime * result
				+ ((resumenes == null) ? 0 : resumenes.hashCode());
		result = prime * result
				+ ((tarTitular == null) ? 0 : tarTitular.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaCredito other = (CuentaCredito) obj;
		if (adicionales == null) {
			if (other.adicionales != null)
				return false;
		} else if (!adicionales.equals(other.adicionales))
			return false;
		if (consumosDelPeriodo == null) {
			if (other.consumosDelPeriodo != null)
				return false;
		} else if (!consumosDelPeriodo.equals(other.consumosDelPeriodo))
			return false;
		if (debitoAutomatico != other.debitoAutomatico)
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (Double.doubleToLongBits(limiteFinanciacion) != Double
				.doubleToLongBits(other.limiteFinanciacion))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nroCuenta != other.nroCuenta)
			return false;
		if (resumenes == null) {
			if (other.resumenes != null)
				return false;
		} else if (!resumenes.equals(other.resumenes))
			return false;
		if (tarTitular == null) {
			if (other.tarTitular != null)
				return false;
		} else if (!tarTitular.equals(other.tarTitular))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}
	
	public String toString(){
		return this.marca +" "+this.nroCuenta ;
	}
	
	public double disponibleUnPago() {
		return this.getLimiteFinanciacion()-this.montoConsumosUnPago();
	}


	public double disponibleCuotas() {
		return this.getLimiteFinanciacion()-this.montoConsumosUnPago();
	}


}
