import org.joda.time.LocalDate;



public class Prueba {

	public static void main(String[] args) {
		
		
		Banco bancoFrances = new Banco( "Banco Frances", 17);
		bancoFrances.nuevoCliente(37905934L, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", new LocalDate());
		bancoFrances.nuevoCliente(16599403L, "Fontana", "Matheu 234", "Nancy", "6666-8888", new LocalDate());
		
		bancoFrances.altaCuentaCredito(37905934L,"VISA", 14000.00);
		bancoFrances.altaCuentaCredito(14908654L, "Lopez", "Mastercard", "LN Alem", "Carlos", "2342-2342", new LocalDate(),  30000.00);
		 
		bancoFrances.altaCajaAhorro(37905934L);
		bancoFrances.altaCuentaCorriente(16599403L);
		
		System.out.println(bancoFrances.verCliente(37905934L));
		System.out.println(bancoFrances.verUsuario(37905934L));
		
		//AGRGEAR AL TO STRING SI ES CA O CC
		System.out.println("CUENTAS MONETARIAS NOELIA: "+bancoFrances.verCliente(37905934L).getCuentasMonetarias());
		System.out.println("CANTIDAD DE TARJETAS NOELIA:" +bancoFrances.getListaTarjetas().size());
		System.out.println("TARJETA DE DEBITO NOELIA:" + bancoFrances.verCliente(37905934L).getTajetaDeDebito());
		System.out.println("CUENTAS CREDITO NOELIA: "+ bancoFrances.verCliente(37905934L).getCuentasCredito());
			
		
		Consumo consumo1 = new Consumo("Compra", 100.00, bancoFrances.verCliente(37905934L).getTarjetasCredito().get(1L), "Starbucks", "Bouchard");
		Consumo consumo2 = new Consumo("Compra", 50.00, bancoFrances.verCliente(37905934L).getTarjetasCredito().get(1L), "Mc donalds", "Corrientes");
		bancoFrances.verCliente(37905934L).getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo1);
		bancoFrances.verCliente(37905934L).getCuentasCredito().get(1L).getConsumosDelPeriodo().add(consumo2);
		bancoFrances.cierreDeTarjeta();
		//	bancoFrances.verCliente(37905934L).getCuentasCredito().get(1L).getResumenes().get(1L).impresionResumen();
		
		
	
	}
	
	

}
