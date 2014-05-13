
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//pusheando banco!

public class Banco {

	private final int CUOTA_MENSUAL_SEGURO = 5;
	private final int numeroEntidad;
	private Map<Usuario,Cliente> listaClientes;
	private Set<CajaDeAhorro> listaCajasDeAhorro;
	private Set<CuentaCorriente> listaCuentasCorriente;
	private Set<CuentaCredito> listaCuentasCredito;
	private Set<Tarjeta> listaTarjetas;
	private Set<Seguro> listaSeguros;
	private final String nombre;
		  
	public Banco ( String nombre, int numeroEntidad ){
		this.nombre = nombre;
		this.numeroEntidad = numeroEntidad;
		listaClientes = new HashMap<Usuario,Cliente>();
		listaCajasDeAhorro = new HashSet<CajaDeAhorro>();
		listaCuentasCorriente = new HashSet<CuentaCorriente>();
		listaTarjetas = new HashSet<Tarjeta>();
		listaSeguros = new HashSet<Seguro>();
	}

	public void nuevoCliente ( long dni, String apellido, String domicilio, String nombre, String telefono, String fechaNacimiento ){
		Usuario usuario = new Usuario(dni,apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
	}
	
	
	public Cliente verCliente ( long DNI ){
		for (Usuario usuario : this.listaClientes.keySet()) {
			if ( usuario.getDni() == DNI ){
				return this.listaClientes.get(usuario);
			}
		}
		return null;
	}
	
	public Usuario verUsuario( long DNI ){
		for (Usuario usuario : this.listaClientes.keySet()) {
			if ( usuario.getDni() == DNI ){
				return usuario;
			}
		}
		return null;
	}
	
	// --------------------------- ALTA CUENTAS MONETARIAS -----------------------------------
	// 1. VALIDAR QUE EL DOCUMENTO EXISTA O NO. VER EXCEPCIONES.
	// 2. COMO HAGO LA FECHA DE HOY?
	

	
	// EL CLIENTE EXISTE. Tirar excepcion si el cliente buscado no existe. Si no tiene tarjeta de debito crear, sino,no
	public void altaCajaAhorro ( long DNI, String fechaAlta ){
		CajaDeAhorro cajaDeAhorro = new CajaDeAhorro(this.getNumeroEntidad()*10000+this.listaCajasDeAhorro.size()+1L, this.listaCajasDeAhorro.size()+1L, fechaAlta);
		this.listaCajasDeAhorro.add(cajaDeAhorro);
		verCliente(DNI).getCuentasMonetarias().add(cajaDeAhorro);
		if ( verCliente(DNI).getTajetaDeDebito() == null ){
			TarjetaDebito tarjetaDebito = nuevaTarjetaDebito(fechaAlta, fechaAlta);
			verCliente(DNI).setTajetaDeDebito(tarjetaDebito);
		} 
	}
	
	// EL CLIENTE ES NUEVO. Si existe lo pisa no? Si no tiene tarjeta de debito crear, sino, no. EXCEPCIONES. 
	public void altaCajaDeAhorro ( long dni, String apellido, String domicilio, String nombre, String telefono, String fechaNacimiento, String fechaAlta, String fechaVencimiento){
		Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
		altaCajaAhorro(dni, fechaAlta);
		TarjetaDebito tarjetaDebito = nuevaTarjetaDebito(fechaAlta, fechaVencimiento);
		cliente.setTajetaDeDebito(tarjetaDebito);
		this.listaTarjetas.add(tarjetaDebito);
		
	}
	
	// EL CLIENTE EXISTE. Tirar excepcion si el cliente buscado no existe.
	public void altaCuentaCorriente ( long DNI, String fechaAlta){
		CuentaCorriente cuentaCorriente = new CuentaCorriente(this.getNumeroEntidad()*10000+this.listaCajasDeAhorro.size()+1L, this.listaCajasDeAhorro.size()+1L, fechaAlta, 0 );
		this.listaCuentasCorriente.add(cuentaCorriente);
		Cliente cliente = verCliente(DNI);
		cliente.getCuentasMonetarias().add(cuentaCorriente);
	}
	
	// EL CLIENTE ES NUEVO
	public void altaCuentaCorriente ( long dni, String apellido, String domicilio, String nombre, String telefono, String fechaNacimiento, String fechaAlta, String fechaEmision, String fechaVencimiento){
		Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
		altaCuentaCorriente(dni,fechaAlta);
		TarjetaDebito tarjetaDebito = nuevaTarjetaDebito(fechaEmision, fechaVencimiento);
		cliente.setTajetaDeDebito(tarjetaDebito);	
	}
	
	// EL CLIENTE EXISTE Tirar excepcion si el cliente buscado no existe.
	public void altaCuentaCredito ( long DNI, String marca, double limiteCompra, String fechaEmision, String fechaVencimiento){
		TarjetaDeCredito tarjetaNueva = nuevaTarjetaDeCredito(limiteCompra, 1, fechaEmision, fechaVencimiento);
		CuentaCredito cuentaCredito = new CuentaCredito( verCliente(DNI), this.listaCuentasCredito.size()+1L, fechaEmision, marca, tarjetaNueva, limiteCompra );
		this.listaCuentasCredito.add(cuentaCredito);
	}


	// EL CLIENTE ES NUEVO
	public void altaCuentaCredito ( long dni, String apellido, String marca, String domicilio, String nombre, String telefono, String fechaNacimiento, double limiteDeCompra, String fechaEmision, String fechaVencimiento){
		Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
		altaCuentaCredito(dni, marca, limiteDeCompra, fechaEmision, fechaVencimiento );
	}
	
	// --------------------------- ALTA TARJETAS  -----------------------------------
	
	public TarjetaDebito nuevaTarjetaDebito ( String fechaEmision, String fechaVencimiento ){
		
		int codigoDeSeguridad = (int)Math.floor( Math.random() *(1-11)+1000);
		TarjetaDebito tarjetaDebito = new TarjetaDebito(this.listaTarjetas.size()+1L, fechaEmision,
				fechaVencimiento, 0, codigoDeSeguridad, 0);
		this.listaTarjetas.add(tarjetaDebito);
		return tarjetaDebito;
	}
	
	public TarjetaDeCredito nuevaTarjetaDeCredito( double limiteCompra, double porcentajeLimite, String fechaEmision, String fechaVencimiento){
		int codigoDeSeguridad = (int)Math.floor( Math.random() *(1-11)+1000);
		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(this.listaTarjetas.size()+1L,fechaEmision,fechaVencimiento,limiteCompra, codigoDeSeguridad, 0, 0, porcentajeLimite );
		this.listaTarjetas.add(tarjetaDeCredito);
		return tarjetaDeCredito;
	}
	
	public TarjetaDeCoordenadas nuevaTarjetaCoord (){
		return new TarjetaDeCoordenadas(this.listaTarjetas.size());
	}
	
	// --------------------------- ALTA Y BAJA SEGUROS -----------------------------------

	// EL CLIENTE EXISTE
	public void altaSeguro ( long DNI, String tipo ){
		Seguro seguro = new Seguro( tipo, this.listaSeguros.size()+1L, CUOTA_MENSUAL_SEGURO);
		Cliente cliente = this.verCliente(DNI);
		cliente.getSeguros().add(seguro);
		
	}

	// EL CLIENTE ES NUEVO
	public void altaSeguro (  String tipo, long dni, String apellido, String domicilio, String nombre, String telefono, String fechaNacimiento){
		Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
		altaSeguro(dni,tipo);
	}
	
	// --------------------------- GETTERS -----------------------------------
	
	public int getNumeroEntidad() {
		return numeroEntidad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Map<Usuario, Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(Map<Usuario, Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Set<CajaDeAhorro> getListaCajasDeAhorro() {
		return listaCajasDeAhorro;
	}

	public void setListaCajasDeAhorro(Set<CajaDeAhorro> listaCajasDeAhorro) {
		this.listaCajasDeAhorro = listaCajasDeAhorro;
	}

	public Set<CuentaCorriente> getListaCuentasCorriente() {
		return listaCuentasCorriente;
	}

	public void setListaCuentasCorriente(Set<CuentaCorriente> listaCuentasCorriente) {
		this.listaCuentasCorriente = listaCuentasCorriente;
	}

	public Set<CuentaCredito> getListaCuentasCredito() {
		return listaCuentasCredito;
	}

	public void setListaCuentasCredito(Set<CuentaCredito> listaCuentasCredito) {
		this.listaCuentasCredito = listaCuentasCredito;
	}

	public Set<Tarjeta> getListaTarjetas() {
		return listaTarjetas;
	}

	public void setListaTarjetas(Set<Tarjeta> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}

	public Set<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(Set<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public int getCUOTA_MENSUAL_SEGURO() {
		return CUOTA_MENSUAL_SEGURO;
	}
	
}
