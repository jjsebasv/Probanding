
public class Prueba {

	public static void main(String[] args) {
		
		Banco bancoFrances = new Banco( "Banco Frances", 17);
		bancoFrances.nuevoCliente(37905934L, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", "07-09-1003");
		bancoFrances.nuevoCliente(16599403L, "Fontana", "Matheu 234", "Nancy", "6666-8888", "20-11-1961");
		
		bancoFrances.altaCuentaCredito(37905934L,"05-07-2014", "VISA", 14000.00);
		bancoFrances.altaCuentaCredito(14908654L, "Lopez", "Mastercard", "LN Alem", "Carlos", "2342-2342", "ayer", "ayer", 30000.00);
		 
		bancoFrances.altaCajaAhorro(37905934L, "04-04-2001");
		bancoFrances.altaCuentaCorriente(16599403L, "hoy");
		
		System.out.println(bancoFrances.verCliente(37905934L));
		System.out.println(bancoFrances.verUsuario(37905934L));
		
		//AGRGEAR AL TO STRING SI ES CA O CC
		System.out.println("CUENTAS MONETARIAS NOELIA: "+bancoFrances.verCliente(37905934L).getCuentasMonetarias());
		System.out.println("CANTIDAD DE TARJETAS NOELIA:" +bancoFrances.getListaTarjetas().size());
		System.out.println("TARJETA DE DEBITO NOELIA:" + bancoFrances.verCliente(37905934L).getTajetaDeDebito());
		System.out.println("CUENTAS CREDITO NOELIA: "+ bancoFrances.verCliente(37905934L).getCuentasCredito());
		
	//Consumo consumo = new Consumo("Starbucks", 100.00, bancoFrances.verCliente(37905934L).getTarjetasCredito().
	
	}
	
	

}
