package banco;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;


public class Resumen implements Serializable{

	private Cliente duenio;
	private final double PORCENTAJE = 0.3;
	private final long numeroResumen;
	private final double monto;
	private final double pagoMinimo;
	private final LocalDate fechaCierre;
	private final LocalDate fechaVencimiento;
	private double montoAbonado;
	private Set<Consumo> consumos;
	
	public Resumen ( long numeroResumen, double monto, Cliente duenio, Set<Consumo> consumos ){
		this.numeroResumen = numeroResumen;
		this.monto = monto;
		this.pagoMinimo = monto * PORCENTAJE;
		this.fechaCierre = new LocalDate();
		this.fechaVencimiento = this.fechaCierre.plusWeeks(2);
		this.duenio = duenio;
		this.consumos = consumos;
		
	}

	public void impresionResumen() throws IOException{
		File c;
		FileWriter w;
		
		try {
			long time = new Date().getTime();
			
			c = new File(this.duenio.getNumeroCliente() + time + ".txt");
			w = new FileWriter(c);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write("***************************"+" RESUMEN "+"******************************");
			bw.newLine();
			bw.append("******************************************************************");
			bw.newLine();
			bw.newLine();
			bw.append("FECHA CIERRE: "+this.fechaCierre);
			bw.newLine();
			bw.append("FECHA VENCIMIENTO: "+this.fechaVencimiento);
			bw.newLine();
			bw.append("PAGO MINIMO: "+this.pagoMinimo);
			bw.newLine();
			bw.append("MONTO: "+this.monto);
			bw.newLine();
			bw.newLine();
			for (Consumo consumo : consumos) {
				bw.append(consumo.toString());
				bw.newLine();
			}
			bw.newLine();
			bw.append("******************************************************************");
			bw.newLine();
			bw.append("******************************************************************");
			bw.close();
			w.close();
			
		} catch(IOException e){
			System.out.println("ERROR: "+e.getMessage());
		};
		
	}
	
	public String toString(){
		return String.valueOf(numeroResumen);
	}
	
	
	public Cliente getDuenio() {
		return duenio;
	}

	public void setDuenio(Cliente duenio) {
		this.duenio = duenio;
	}

	public double getPORCENTAJE() {
		return PORCENTAJE;
	}

	public double getMontoAbonado() {
		return montoAbonado;
	}

	public void setMontoAbonado(double montoAbonado) {
		this.montoAbonado += montoAbonado;
	}

	public long getNumeroResumen() {
		return numeroResumen;
	}

	public double getMonto() {
		return monto;
	}

	public double getPagoMinimo() {
		return pagoMinimo;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	
	
	
	
	
}
