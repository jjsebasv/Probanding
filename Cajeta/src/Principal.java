import java.io.IOException;

import org.joda.time.LocalDate;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Banco bancoFrances = Banco.recuperarMiBanco();

		
		// CLIENTE 1: NOELIA
		bancoFrances.nuevoCliente(1L, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", new LocalDate());
		Cliente nowi = Banco.recuperarMiBanco().verCliente(1L);
		nowi.setClavePin(709);
		bancoFrances.altaCajaAhorro(1L);
		bancoFrances.altaCajaAhorro(1L);
		bancoFrances.altaCajaAhorro(1L);
		bancoFrances.altaCuentaCredito(1L,"Visa", 14000.00);
		nowi.getCuentasMonetarias().get(1L).depositar(1500.00);
		Consumo consumo1 = new Consumo("Compra", 100.00, bancoFrances.verCliente(1L).getTarjetasCredito().get(1L), "Starbucks", "Bouchard");
		Consumo consumo2 = new Consumo("Compra", 50.00, bancoFrances.verCliente(1L).getTarjetasCredito().get(1L), "Mc donalds", "Corrientes");
		nowi.getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo1);
		nowi.getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo2);
		nowi.getCuentasMonetarias().get(1L).depositar(1000);
		System.out.println("size"+nowi.getCuentasMonetarias().get(1L).getUltimosMovimientos().size());
		nowi.getCuentasMonetarias().get(1L).imprimirUltimosMovimientos();
		
		// CLIENTE 2: NANCY
		bancoFrances.nuevoCliente(16599403L, "Fontana", "Matheu 234", "Nancy", "6666-8888", new LocalDate());
		bancoFrances.altaCuentaCorriente(16599403L);
		bancoFrances.verCliente(16599403L).setClavePin(2011);
		
		// CLIENTE 3: CARLOS
		bancoFrances.altaCuentaCredito(14908728L, "Lopez", "Mastercard", "LN Alem", "Carlos", "2342-2342", new LocalDate(),  30000.00);
		bancoFrances.verCliente(14908728).setClavePin(2810);
	
		
		bancoFrances.cierreDeTarjeta();

		//	---------------------------------------------------------------------------------------------------- //
		
		InicioJFrame inicio = new InicioJFrame();
		inicio.setVisible(true);
		
	}

}
