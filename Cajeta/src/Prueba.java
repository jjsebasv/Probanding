
public class Prueba {

	public static void main(String[] args) {
		
		Banco bancoFrances = new Banco( "Banco Frances", 17);
		bancoFrances.nuevoCliente(37905934, "Lopez", "Pedro Goyena", "Noelia", "4444-4444", "07-09-1003");
		bancoFrances.nuevoCliente(16599403L, "Fontana", "Matheu 234", "Nancy", "6666-8888", "20-11-1961");
		
		//	REVISAR CUENTAS CREDITO
		//	bancoFrances.altaCuentaCredito(37905934L, "VISA", 14000.00,"05-07-2014", "05-07-2015");
		bancoFrances.altaCuentaCredito(14908654L, "Lopez", "Mastercard", "LN Alem", "Carlos", "2342-2342", "ayer", 30000.00, "hoy", "ma��ana");
		 
		bancoFrances.altaCajaAhorro(37905934L, "04-04-2001");
		bancoFrances.altaCuentaCorriente(16599403L, "hoy");
		
		System.out.println(bancoFrances.verCliente(37905934L));
		System.out.println(bancoFrances.verUsuario(37905934L));
		
		//AGRGEAR AL TO STRING SI ES CA O CC
		System.out.println("MIS CUENTAS MONETARIAS: "+bancoFrances.verCliente(37905934L).getCuentasMonetarias());
		System.out.println("CANTIDAD DE TARJETAS:" +bancoFrances.getListaTarjetas().size());
		System.out.println("MI TARJETA DE DEBITO:" + bancoFrances.verCliente(37905934L).getTajetaDeDebito());

	
	}
	
	

}
