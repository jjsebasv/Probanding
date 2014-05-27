package banco;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

import exception.NoExisteClienteExcepcion;
import exception.NoExisteLaCuentaExcepcion;
import exception.NoPoseeSaldoExcepcion;
import exception.NoSePuedeDepositarChequeExcepcion;
public class Banco {
	//prueba
	private static Banco bancoFrances = null;

	private final int CUOTA_MENSUAL_SEGURO = 5;
	private final int numeroEntidad = 17;
	
	private Set<Tarjeta> listaTarjetas;
	private Set<TarjetaDeCoordenadas> listaTarjetasCoord;
	private Set<Seguro> listaSeguros;
	
	private Map<Long,CuentaCredito> listaCuentasCredito;
	private Map<Long,Resumen> listaResumenes;
	private Map<Usuario,Cliente> listaClientes;
	private Map<Long, Usuario> listaUsuarios;
	private Map<Long,CajaDeAhorro> listaCajasDeAhorro;
	private Map<Long,CuentaCorriente> listaCuentasCorriente;

	

	private final String nombre = "Banco Frances";

	private Banco (){
		listaClientes = new HashMap<Usuario,Cliente>();
		listaCajasDeAhorro = new HashMap<Long,CajaDeAhorro>();
		listaCuentasCorriente = new HashMap<Long,CuentaCorriente>();
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
		for (Usuario usuario : this.listaClientes.keySet() ) {
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
			CajaDeAhorro cajaDeAhorro = new CajaDeAhorro();
			this.listaCajasDeAhorro.put(cajaDeAhorro.getNumeroCuenta(), cajaDeAhorro);
			verCliente(DNI).getCuentasMonetarias().put(cajaDeAhorro.getNumeroCuenta(), cajaDeAhorro);
			
			if ( verCliente(DNI).getTajetaDeDebito() == null ){
				TarjetaDebito tarjetaDebito = nuevaTarjetaDebito();
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
	public void altaCuentaCorriente ( long DNI, double giroEnDescubierto ) throws NoExisteClienteExcepcion{
		if ( this.verCliente(DNI) == null ){
			throw new NoExisteClienteExcepcion();
		}
		else{
			CuentaCorriente cuentaCorriente = new CuentaCorriente( giroEnDescubierto );
			this.listaCuentasCorriente.put(cuentaCorriente.getNumeroCuenta(), cuentaCorriente);
			verCliente(DNI).getCuentasMonetarias().put(cuentaCorriente.getNumeroCuenta(), cuentaCorriente);
			if ( verCliente(DNI).getTajetaDeDebito() == null ){
				TarjetaDebito tarjetaDebito = nuevaTarjetaDebito();
				verCliente(DNI).setTajetaDeDebito(tarjetaDebito);
			} 
		}
	}
	
	// EL CLIENTE ES NUEVO
	public void altaCuentaCorriente ( long dni, String apellido, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento, double giroEnDescubierto){
		if ( verCliente(dni) == null ){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
			this.listaUsuarios.put(dni, usuario);
		}
		altaCuentaCorriente(dni,giroEnDescubierto);
	}
	
	// 	EL CLIENTE EXISTE
	public void altaCuentaCredito( long DNI, String marca, double limiteCompra ){
		if ( this.verCliente(DNI) == null ){
			throw new NoExisteClienteExcepcion();
		}
		else{
			CuentaCredito cuentaCredito = new CuentaCredito( this.verCliente(DNI), this.listaCuentasCredito.size()+1L, marca, null, limiteCompra );
			TarjetaDeCredito tarjetaDeCredito = nuevaTarjetaDeCredito(cuentaCredito, limiteCompra,1,marca);
			cuentaCredito.setTarTitular(tarjetaDeCredito);
			Banco.recuperarMiBanco().verCliente(DNI).getTarjetasCredito().put(tarjetaDeCredito.getNumeroTarjeta(), tarjetaDeCredito);
			verCliente(DNI).getCuentasCredito().put(cuentaCredito.getNroCuenta(), cuentaCredito);
		}
	}
	
	// EL CLIENTE ES NUEVO
	public void altaCuentaCredito(long dni, String apellido, String marca, String domicilio, String nombre, String telefono, LocalDate fechaNacimiento, double limiteCompra){
		if ( verCliente(dni) == null ){
			Usuario usuario = new Usuario(dni, apellido, domicilio, nombre, telefono, fechaNacimiento);
			Cliente cliente = new Cliente(this.listaClientes.size()+1L);
			this.listaClientes.put(usuario, cliente);
			this.listaUsuarios.put(dni, usuario);
		}
		altaCuentaCredito(dni, marca, limiteCompra);
	}

	
	// 
	
	// --------------------------- ALTA TARJETAS  -----------------------------------
	
	public TarjetaDebito nuevaTarjetaDebito (){
		TarjetaDebito tarjetaDebito = new TarjetaDebito();
		this.listaTarjetas.add(tarjetaDebito);
		return tarjetaDebito;
	}
	
	public TarjetaDeCredito nuevaTarjetaDeCredito( CuentaCredito cuenta, double limiteCompra, double porcentajeLimite,String marca){
		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(limiteCompra, porcentajeLimite, marca, cuenta );
		this.listaTarjetas.add(tarjetaDeCredito);
		return tarjetaDeCredito;
	}
	
	public TarjetaDeCoordenadas nuevaTarjetaCoord (){
		TarjetaDeCoordenadas tarjetaCoord = new TarjetaDeCoordenadas(this.listaTarjetas.size());
		this.listaTarjetasCoord.add(tarjetaCoord);
		return tarjetaCoord;
	}
	
	// --------------------------- EXTRACCION, DEPOSITO, TRASNFERENCIA -----------------------------------
	
	public void cobrarCheque(Cheque cheque, CuentaCorriente emisora){
		emisora.setSaldoActual(emisora.getSaldoActual()-cheque.getMonto());
		cheque.setCobrado(true);
	}
	
	// EXTRACCION //

	
	public void extraccion ( CajaDeAhorro cuenta, double monto){
		if ( cuenta.getSaldoActual() >= monto ){
			cuenta.setSaldoActual(cuenta.getSaldoActual()-monto);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
		
	}
	
	public void extraccion ( CuentaCorriente cuenta, double monto){
		if ( (cuenta.getSaldoActual()+cuenta.getGiroEnDescubierto()) >= monto ){
			cuenta.setSaldoActual(cuenta.getSaldoActual()-monto);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	// DEPOSITO //
	
	public void deposito ( Cuenta cuenta, double monto ){
		cuenta.setSaldoActual(cuenta.getSaldoActual()+monto);
	}

	
	public void deposito ( Cheque cheque, CajaDeAhorro destino) throws NoSePuedeDepositarChequeExcepcion, NoPoseeSaldoExcepcion{
		CuentaCorriente emisora = cheque.getEmisora();
		LocalDate hoy = new LocalDate();
		System.out.println("a caja de ahorro");
		if ( (emisora.getSaldoActual()+emisora.getGiroEnDescubierto()) >= cheque.getMonto() ){
			if ( destino.getFechaAlta().isBefore(hoy.minusMonths(6))){
				emisora.setSaldoActual(emisora.getSaldoActual()-cheque.getMonto());
				destino.depositar(emisora.getSaldoActual()+cheque.getMonto());
				cheque.setCobrado(true);
			}
			else{
				throw new NoSePuedeDepositarChequeExcepcion();
			}
			
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	// agregar 1344622638 chque eno cobrao
	
	public void deposito ( Cheque cheque, CuentaCorriente destino) throws NoSePuedeDepositarChequeExcepcion, NoPoseeSaldoExcepcion{
		CuentaCorriente emisora = cheque.getEmisora();
		System.out.println("a cuenta corriente");
		if ( (emisora.getSaldoActual()+emisora.getGiroEnDescubierto()) >= cheque.getMonto() ){
			destino.setSaldoActual(destino.getSaldoActual()+cheque.getMonto());
			cheque.setCobrado(true);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	
	// TRANSFERENCIA //
	
	// Desde una caja de ahorro a cualquier cuenta del mismo banco
	public void transferencia ( double monto, CajaDeAhorro emisora, Cuenta cuentaDestino){
		if ( emisora.getSaldoActual() >= monto ){
			if ( this.listaCajasDeAhorro.values().contains(cuentaDestino) || this.listaCuentasCorriente.values().contains(cuentaDestino) ){
				emisora.setSaldoActual(emisora.getSaldoActual()-monto);
				cuentaDestino.transferenciaRecibida(emisora, monto);
			}
			else{
				throw new NoExisteLaCuentaExcepcion();
			}
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	// Desde una cuenta corriente a cualquier cuenta del mismo banco
	public void transferencia ( double monto, CuentaCorriente emisora, Cuenta cuentaDestino){
		if ( emisora.getSaldoActual()+emisora.getGiroEnDescubierto() >= monto ){
			if ( this.listaCajasDeAhorro.values().contains(cuentaDestino) || this.listaCuentasCorriente.values().contains(cuentaDestino) ){
				emisora.setSaldoActual(emisora.getSaldoActual()-monto);
				cuentaDestino.transferenciaRecibida(emisora, monto);
			}
			else{
				throw new NoExisteLaCuentaExcepcion();
			}
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	// Desde cualquier cuenta, a un cbu de otro banco. Se transfiere el dinero a una cuenta del mismo banco
	public void transferencia (double monto, Cuenta emisora, long destino){
		if ( this.listaCajasDeAhorro.values().contains(destino) ){
			transferencia ( monto, this.listaCajasDeAhorro.get(emisora.getNumeroCuenta()),destino);
		}
		else if ( this.listaCuentasCorriente.values().contains(destino) ){
			transferencia ( monto, this.listaCuentasCorriente.get(emisora.getNumeroCuenta()),destino);
		}
		else{
			throw new NoExisteLaCuentaExcepcion();
		}
	}
	
	// Desde cualquier cuenta, a un cbu de otro banco. Se transfiere el dinero sin validar
	public void transferenciaPorCbu ( double monto, Cuenta emisora, long CBUdestino){
		if ( emisora.getSaldoActual() >= monto ){
			emisora.setSaldoActual(emisora.getSaldoActual()-monto);
		}
		else{
			throw new NoPoseeSaldoExcepcion();
		}
	}
	
	
	// ---------------------------------------------------------------------------------------------- //
	
	public boolean disponeSaldo(double monto, long nroCuenta){
		if ( this.listaCajasDeAhorro.containsKey(nroCuenta) || this.listaCuentasCorriente.containsKey(nroCuenta) ){
			if(this.listaCajasDeAhorro.get(nroCuenta).getSaldoActual() > monto || (this.listaCuentasCorriente.get(nroCuenta).getSaldoActual()+this.listaCuentasCorriente.get(nroCuenta).getGiroEnDescubierto()) > monto )
				return true;
			else
				return false;
		}
		else{
			throw new NoExisteLaCuentaExcepcion();
		}
	}
	
	public Map<Long, CajaDeAhorro> getListaCajasDeAhorro() {
		return listaCajasDeAhorro;
	}

	public void setListaCajasDeAhorro(Map<Long, CajaDeAhorro> listaCajasDeAhorro) {
		this.listaCajasDeAhorro = listaCajasDeAhorro;
	}

	public Map<Long, CuentaCorriente> getListaCuentasCorriente() {
		return listaCuentasCorriente;
	}

	public void setListaCuentasCorriente(
			Map<Long, CuentaCorriente> listaCuentasCorriente) {
		this.listaCuentasCorriente = listaCuentasCorriente;
	}

	public Set<TarjetaDeCoordenadas> getListaTarjetasCoord() {
		return listaTarjetasCoord;
	}

	public void setListaTarjetasCoord(Set<TarjetaDeCoordenadas> listaTarjetasCoord) {
		this.listaTarjetasCoord = listaTarjetasCoord;
	}

	public Map<Long, Resumen> getListaResumenes() {
		return listaResumenes;
	}

	public void setListaResumenes(Map<Long, Resumen> listaResumenes) {
		this.listaResumenes = listaResumenes;
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
