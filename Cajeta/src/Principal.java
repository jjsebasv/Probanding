import java.io.IOException;

import org.joda.time.LocalDate;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Banco bancoFrances = Banco.recuperarMiBanco();

		
		// CLIENTE 1: NOELIA
		bancoFrances.nuevoCliente(37905934L, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", new LocalDate());
		bancoFrances.altaCuentaCredito(37905934L,"Visa", 14000.00);
		bancoFrances.verCliente(37905934L).setClavePin(709);
		bancoFrances.altaCajaAhorro(37905934L);
		bancoFrances.altaCajaAhorro(37905934L);
		bancoFrances.altaCajaAhorro(37905934L);
		Consumo consumo1 = new Consumo("Compra", 100.00, bancoFrances.verCliente(37905934L).getTarjetasCredito().get(1L), "Starbucks", "Bouchard");
		Consumo consumo2 = new Consumo("Compra", 50.00, bancoFrances.verCliente(37905934L).getTarjetasCredito().get(1L), "Mc donalds", "Corrientes");
		bancoFrances.verCliente(37905934L).getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo1);
		bancoFrances.verCliente(37905934L).getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo2);
		bancoFrances.deposito(1000, bancoFrances.verCliente(37905934L).getCuentasMonetarias().get(1L));
		
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
