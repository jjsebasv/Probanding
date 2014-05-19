
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.joda.time.LocalDate;



public class Banco {
	
	private static Banco bancoFrances = null;

	private final int CUOTA_MENSUAL_SEGURO = 5;
	private final int numeroEntidad = 17;
	
	private Set<CajaDeAhorro> listaCajasDeAhorro;
	private Set<CuentaCorriente> listaCuentasCorriente;
	private Set<Tarjeta> listaTarjetas;
	private Set<TarjetaDeCoordenadas> listaTarjetasCoord;
	private Set<Seguro> listaSeguros;
	
	private Map<Long,CuentaCredito> listaCuentasCredito;
	private Map<Long,Resumen> listaResumenes;
	private Map<Usuario,Cliente> listaClientes;
	private Map<Long, Usuario> listaUsuarios;

	

	private final String nombre = "Banco Frances";

	private Banco (){
		listaClientes = new HashMap<Usuario,Cliente>();
		listaCajasDeAhorro = new HashSet<CajaDeAhorro>();
		listaCuentasCorriente = new HashSet<CuentaCorriente>();
		listaCuentasCredito = new HashMap<Long,CuentaCredito>();
		listaTarjetas = new HashSet<Tarjeta>();
		listaSeguros = new HashSet<Seguro>();
		listaResumenes = new HashMap<Long,Resumen>();
		listaTarjetasCoord = new HashSet<TarjetaDeCoordenadas>();
		listaUsuarios = new HashMap<Long, Usuario>();

	}

	public void nuevoCliente ( long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento ){
		Usuario usuario = new Usuario(dni,apellido, domicilio, nombre, telefono, fechaNacimiento);
		Cliente cliente = new Cliente(this.listaClientes.size()+1L);
		this.listaClientes.put(usuario, cliente);
		this.listaUsuarios.put(dni, usuario);
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
	
	// --------------------------- CLIENTE -----------------------------------
	
	public void generacionClavePin( int clavePin, long dni ){
		this.verCliente(dni).setClavePin(clavePin);
	}
	
	public void generacionClaveHome ( int claveHome, long dni){
		this.verCliente(dni).setClaveHomeBanking(claveHome);
	}
	
	public void generacionUsuario ( String usuario, long dni){
		this.verCliente(dni).setUsuario(usuario);
	}
	
	public void registroDeCelular ( String compania, String celular, long dni  ){
		Celular celularNuevo = new Celular(compania, celular);
		this.verCliente(dni).setCelular(celularNuevo);
	}
	
	
	// --------------------------- ALTA CUENTAS MONETARIAS -----------------------------------
	// 1. VALIDAR QUE EL DOCUMENTO EXISTA O NO. VER EXCEPCIONES.
	

	
	// EL CLIENTE EXISTE. 
	public void altaCajaAhorro ( long DNI ) throws NoExisteClienteExcepcion {
		if ( this.verCliente(DNI) == null ){
			throw new NoExisteClienteExcepcion();
		}
		else{
			CajaDeAhorro cajaDeAhorro = new CajaDeAhorro(this.getNumeroEntidad()*10000+this.listaCajasDeAhorro.size()+1L, this.listaCajasDeAhorro.size()+1L);
			this.listaCajasDeAhorro.add(cajaDeAhorro);
			verCliente(DNI).getCuentasMonetarias().put(cajaDeAhorro.getNumeroCuenta(), cajaDeAhorro);
			
			if ( verCliente(DNI).getTajetaDeDebito() == null ){
				TarjetaDebito tarjetaDebito = new TarjetaDebito(this.listaTarjetas.size()+1L);
				verCliente(DNI).setTajetaDeDebito(tarjetaDebito);
			} 
		}
		
	}
	
	// EL CLIENTE ES NUEVO.
	public void altaCajaDeAhorro ( long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento) throws NoExisteClienteExcepcion{
		if ( this.verCliente(dni) == null ){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
		}
		altaCajaAhorro(dni);	
	}
	
	// EL CLIENTE EXISTE. 
	public void altaCuentaCorriente ( long DNI ) throws NoExisteClienteExcepcion{
		if ( this.verCliente(DNI) == null ){
			throw new NoExisteClienteExcepcion();
		}
		else{
			CuentaCorriente cuentaCorriente = new CuentaCorriente(this.getNumeroEntidad()*10000+this.listaCajasDeAhorro.size()+1L, this.listaCajasDeAhorro.size()+1L,  0 );
			this.listaCuentasCorriente.add(cuentaCorriente);
			verCliente(DNI).getCuentasMonetarias().put(cuentaCorriente.getNumeroCuenta(), cuentaCorriente);
			if ( verCliente(DNI).getTajetaDeDebito() == null ){
				TarjetaDebito tarjetaDebito = new TarjetaDebito(this.listaTarjetas.size()+1L);
				verCliente(DNI).setTajetaDeDebito(tarjetaDebito);
			} 
		}
	}
	
	// EL CLIENTE ES NUEVO
	public void altaCuentaCorriente ( long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento){
		if ( this.verCliente(dni) == null ){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
		}
		altaCuentaCorriente(dni);
	}
	
	// 	EL CLIENTE EXISTE
	public void altaCuentaCredito( long DNI, String marca, double limiteCompra ){
		if ( this.verCliente(DNI) == null ){
			throw new NoExisteClienteExcepcion();
		}
		else{
			TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(this.listaTarjetas.size()+1L, limiteCompra,1);
			CuentaCredito cuentaCredito = new CuentaCredito( this.verCliente(DNI), this.listaCuentasCredito.size()+1L, marca, tarjetaDeCredito, limiteCompra );
			this.listaCuentasCredito.put(cuentaCredito.getNroCuenta(), cuentaCredito);
			verCliente(DNI).getCuentasCredito().put(cuentaCredito.getNroCuenta(), cuentaCredito);
		}
	}
	
	// EL CLIENTE ES NUEVO
	public void altaCuentaCredito(long dni, String apellido, String marca, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento, double limiteCompra){
		if ( this.verCliente(dni) == null ){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
		}
		altaCuentaCredito(dni, marca, limiteCompra);
	}

	
	// 
	
	// --------------------------- ALTA TARJETAS  -----------------------------------
	
	public TarjetaDebito nuevaTarjetaDebito ( String fechaEmision, String fechaVencimiento ){
		TarjetaDebito tarjetaDebito = new TarjetaDebito(this.listaTarjetas.size()+1L);
		this.listaTarjetas.add(tarjetaDebito);
		return tarjetaDebito;
	}
	
	public TarjetaDeCredito nuevaTarjetaDeCredito( double limiteCompra, double porcentajeLimite, String fechaEmision, String fechaVencimiento){
		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(this.listaTarjetas.size()+1L, limiteCompra, porcentajeLimite );
		this.listaTarjetas.add(tarjetaDeCredito);
		return tarjetaDeCredito;
	}
	
	public TarjetaDeCoordenadas nuevaTarjetaCoord (){
		TarjetaDeCoordenadas tarjetaCoord = new TarjetaDeCoordenadas(this.listaTarjetas.size());
		this.listaTarjetasCoord.add(tarjetaCoord);
		return tarjetaCoord;
	}
	
	// --------------------------- EXTRACCION, DEPOSITO, TRASNFERENCIA -----------------------------------
	
	public void extraccion ( CajaDeAhorro cuenta, double monto){
		if ( cuenta.getSaldoActual() >= monto ){
			cuenta.extraccion(monto);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
		
	}
	
	public void extraccion ( CuentaCorriente cuenta, double monto){
		if ( (cuenta.getSaldoActual()+cuenta.getGiroEnDescubierto()) >= monto ){
			cuenta.extraccion(monto);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	public void deposito ( Cheque cheque, CuentaCorriente emisora, CajaDeAhorro destino){
		LocalDate hoy = new LocalDate();
		if ( emisora.getSaldoActual()+emisora.getGiroEnDescubierto() >= cheque.getMonto() ){
			if ( destino.getFechaAlta().isBefore(hoy.minusMonths(6))){
				emisora.cobrarCheque(cheque);
				destino.depositar(cheque);
			}
			else{
				throw new NoSePuedeDepositarChequeExcepcion();
			}
			
		}
		else{
			emisora.getChequesRechazados().add(cheque);
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	
	public void deposito ( Cheque cheque, CuentaCorriente emisora, CuentaCorriente destino){
		if ( emisora.getSaldoActual()+emisora.getGiroEnDescubierto() >= cheque.getMonto() ){
			emisora.cobrarCheque(cheque);
			destino.depositar(cheque);
		}
		else{
			emisora.getChequesRechazados().add(cheque);
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	public void transferencia ( double monto, CajaDeAhorro emisora, Cuenta destino){
		if ( emisora.getSaldoActual() >= monto ){
			if ( this.listaCajasDeAhorro.contains(destino) || this.listaCuentasCorriente.contains(destino) ){
				emisora.transferir(monto, destino);
			}
			else{
				throw new NoExisteLaCuentaExcepcion();
			}
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	public void transferencia ( double monto, CuentaCorriente emisora, Cuenta destino){
		if ( emisora.getSaldoActual()+emisora.getGiroEnDescubierto() >= monto ){
			if ( this.listaCajasDeAhorro.contains(destino) || this.listaCuentasCorriente.contains(destino) ){
				emisora.transferir(monto, destino);
			}
			else{
				throw new NoExisteLaCuentaExcepcion();
			}
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	public void deposito ( double monto, Cuenta cuenta){
		if ( this.listaCajasDeAhorro.contains(cuenta) || this.listaCuentasCorriente.contains(cuenta) ){
		cuenta.depositar(monto);
		}
		else
			throw new NoExisteLaCuentaExcepcion();
	}
	
	
	
	// --------------------------- ALTA Y BAJA SEGUROS --------------------------------------------------
	

	// EL CLIENTE EXISTE
	public void altaSeguro ( long DNI, String tipo ){
		if ( this.verCliente(DNI) != null ){
		Seguro seguro = new Seguro( tipo, this.listaSeguros.size()+1L, CUOTA_MENSUAL_SEGURO);
		this.verCliente(DNI).getSeguros().add(seguro);
		}
		else
			throw new NoExisteClienteExcepcion();
		
	}

	// EL CLIENTE ES NUEVO
	public void altaSeguro (  String tipo, long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento){
		if ( this.verCliente(dni) == null){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
		}
		altaSeguro(dni,tipo);
	}
	
	// --------------------------- GETTERS -----------------------------------

	public Map<Long, CuentaCredito> getListaCuentasCredito() {
		return listaCuentasCredito;
	}

	public void setListaCuentasCredito(Map<Long, CuentaCredito> listaCuentasCredito) {
		this.listaCuentasCredito = listaCuentasCredito;
	}

	public void cierreDeTarjeta (){
		for (Long nroCuentaCredito : this.listaCuentasCredito.keySet()) {
			this.listaCuentasCredito.get(nroCuentaCredito).cierreLiquidacion(this.listaResumenes.size()+1L);
			
		}
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
	
	public static Banco recuperarMiBanco() {
		  if(bancoFrances == null) {
			  bancoFrances = new Banco();
		  }
		  return bancoFrances;
	}
	
	public Map<Long, Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(Map<Long, Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
}
