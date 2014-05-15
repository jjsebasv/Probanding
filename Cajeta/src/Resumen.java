import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.LocalDate;


public class Resumen {

	private final double PORCENTAJE = 0.3;
	private final long numeroResumen;
	private final double monto;
	private final double pagoMinimo;
	private final LocalDate fechaCierre;
	private final LocalDate fechaVencimiento;
	private double montoAbonado;
	
	public Resumen ( long numeroResumen, double monto ){
		this.numeroResumen = numeroResumen;
		this.monto = monto;
		this.pagoMinimo = monto * PORCENTAJE;
		this.fechaCierre = new LocalDate();
		this.fechaVencimiento = this.fechaCierre.plusWeeks(2);
		
	}

	public void impresionResumen() throws IOException{
		File c;
		FileWriter w;
		
		try {
			c = new File("Resumen 2");
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
			bw.append("******************************************************************");
			bw.newLine();
			bw.append("******************************************************************");
			bw.close();
			w.close();
			
		} catch(IOException e){
			System.out.println("ERROR: "+e.getMessage());
		};
		
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
