package banco;

import java.io.IOException;
import java.util.Date;
       
import org.joda.time.LocalDate;

import view.InicioJFrame;

public class Principal {

	
	// ver error pin car ---> FUNCIONA
	public static void main(String[] args) throws IOException {
		
		Banco bancoFrances = Banco.recuperarMiBanco();

		// CLIENTE 1: NANCY
		bancoFrances.nuevoCliente(2L, "Fontana", "Matheu 234", "Nancy", "6666-8888", new LocalDate());
		bancoFrances.verCliente(2L).setClavePin(2);
		Cliente nan = bancoFrances.verCliente(2L);
		
			// ALTAS
			bancoFrances.altaCuentaCorriente(2L,0);
			bancoFrances.altaCajaAhorro(2L);
			bancoFrances.altaCuentaCredito(2L, "Visa", 15000.00);

			// DEPOSITOS
			nan.getCuentasMonetarias().get(1L).depositar(1000.00);
			System.out.println("saldo depo 1000pe:"+nan.getCuentasMonetarias().get(1L).getSaldoActual());
			nan.getCuentasMonetarias().get(2L).depositar(5000);
		
		// CLIENTE 2: NOELIA
		bancoFrances.nuevoCliente(1L, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", new LocalDate());
		Cliente nowi = Banco.recuperarMiBanco().verCliente(1L);
		nowi.setClavePin(709);
		
			// ALTAS
				bancoFrances.altaCajaAhorro(1L);
				bancoFrances.altaCajaAhorro(1L);
				bancoFrances.altaCuentaCorriente(1L,0);

				bancoFrances.altaCuentaCredito(1L,"Visa", 14000.00);
				bancoFrances.altaCuentaCredito(1L,"Master", 18000.00);		
				

				
			// CONSUMOS
			// ver fecha del consumo
				Consumo consumo1 = new Consumo("COMPRA", 100.00, bancoFrances.verCliente(1L).getTarjetasCredito().get(1L), "Starbucks", "Bouchard", false);
				Consumo consumo2 = new Consumo("COMPRA", 50.00, bancoFrances.verCliente(1L).getTarjetasCredito().get(1L), "Mc donalds", "Corrientes", false);
				Consumo consumo3 = new Consumo("COMPRA", 25.00, nowi.getTajetaDeDebito(), "Kiosko", "Castelar", false);
				Consumo consumo4 = new Consumo("COMPRA", 1500.00, nowi.getTajetaDeDebito(), "Super", "Caba", false);
				nowi.getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo1);
				nowi.getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo2);
				nowi.getCuentasMonetarias().get(3L).movimientos.push(consumo3);
				nowi.getCuentasMonetarias().get(3L).movimientos.push(consumo4);
				
				
			// DEPOSITOS
				nowi.getCuentasMonetarias().get(3L).depositar(3000);
				nowi.getCuentasMonetarias().get(3L).extraccion(200);
				nowi.getCuentasMonetarias().get(3L).extraccion(50);
				nowi.getCuentasMonetarias().get(3L).transferir(2500, 2L);
				nowi.getCuentasMonetarias().get(3L).transferir(30, nan.getCuentasMonetarias().get(1L));
				nowi.getCuentasMonetarias().get(5L).depositar(1000);
				
		// CLIENTE 3: CARLOS
			
			// ALTAS
				bancoFrances.altaCuentaCredito(3L, "Lopez", "Mastercard", "LN Alem", "Carlos", "2342-2342", new LocalDate(),  30000.00);
				Cliente car = bancoFrances.verCliente(3L);
				
				car.setClavePin(300);
		
		bancoFrances.cierreDeTarjeta();

		//	---------------------------------------------------------------------------------------------------- //
		
		System.out.println("DE NAN");
		System.out.println("cuentas monetarias: "+nan.getCuentasMonetarias());
		System.out.println("cuentas credito: "+nan.getCuentasCredito());
		System.out.println("tarjeta debito: " +nan.getTajetaDeDebito());
		System.out.println("tarjeta credito: "+nan.getTarjetasCredito());
		System.out.println("CBU: "+nan.getCBU());
		System.out.println();
		
		System.out.println("DE NOE");
		System.out.println("cuentas monetarias: "+nowi.getCuentasMonetarias());
		System.out.println("cuentas credito: "+nowi.getCuentasCredito());
		System.out.println("tarjeta debito: " +nowi.getTajetaDeDebito());
		System.out.println("tarjeta credito: "+nowi.getTarjetasCredito());
		System.out.println();

		System.out.println("DE CAR");
		System.out.println("cuentas monetarias: "+car.getCuentasMonetarias());
		System.out.println("cuentas credito: "+car.getCuentasCredito());
		System.out.println("tarjeta debito: " +car.getTajetaDeDebito());
		System.out.println("tarjeta credito: "+car.getTarjetasCredito());
		System.out.println();
		System.out.println();

		CuentaCorriente cuenta = (CuentaCorriente) nowi.getCuentasMonetarias().get(5L);
		cuenta.emitirCheque(nan, 100, new LocalDate());
		
		System.out.println("SALDO CUENTA CORRIENTE EMISORA:"+cuenta.getSaldoActual());
		System.out.println("CHEQUE EMITIDO: "+cuenta.getChequesEmitidos());
		
		InicioJFrame inicio = InicioJFrame.recuperarInicio();
		inicio.setVisible(true);
	
		bancoFrances.save();
		
	}

}
