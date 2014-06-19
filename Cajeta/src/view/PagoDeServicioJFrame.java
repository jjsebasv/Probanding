package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import exception.NoPoseeSaldoExcepcion;
import banco.Banco;
import banco.Cuenta;
import banco.Servicio;

import java.awt.Font;
import java.awt.Color;


public class PagoDeServicioJFrame extends JFrame {

	private JPanel contentPane;
	private long dni;
	private OperacionJFrame padre;
	private Cuenta cuentaSelec;
	private Servicio servicioSelec = null;
	private JComboBox cuentas;
	private JComboBox servicios;
	private JLabel lblNoPoseeSaldo;
	
	/**
	 * Create the frame.
	 */
	public void setCuentaSelec(Cuenta cuentaSelec) {
		this.cuentaSelec = cuentaSelec;
	}
	
	public PagoDeServicioJFrame(long dni, OperacionJFrame operacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.dni = dni;
		this.padre = operacion;
		

		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		final Cuenta[] nombreCuentas = new Cuenta[cantCuentas];
			
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta);
			i++;
		}
		
		cuentas = new JComboBox(nombreCuentas);
		cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecCuenta(cuentas.getSelectedItem().toString());
			}
		});
		
		cuentas.setBounds(10, 52, 247, 54);
		contentPane.add(cuentas);
		
		int j=0;
		int cantServicios = Banco.recuperarMiBanco().verCliente(dni).getListaServicios().size();
		final Servicio[] nombreServicios = new Servicio[cantServicios];
			
		for (Long nroServicio : Banco.recuperarMiBanco().verCliente(dni).getListaServicios().keySet()) {
			nombreServicios[j] = Banco.recuperarMiBanco().verCliente(dni).getListaServicios().get(nroServicio);
			i++;
		}
		
		servicios = new JComboBox(nombreServicios);
		servicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecServ(servicios.getSelectedItem().toString());
			}
		});
		servicios.setBounds(10, 133, 247, 54);
		contentPane.add(servicios);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(10, 207, 48, 44);
		contentPane.add(home);
		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(376, 207, 48, 44);
		contentPane.add(cerrarSesion);
		
		JButton pagar = new JButton("Pagar");
		pagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servicioSelec != null)
					pagar();
			}
		});
		pagar.setBounds(307, 106, 117, 29);
		contentPane.add(pagar);
		
		JLabel label = new JLabel("SELECCIONE \nLA CUENTA");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label.setBounds(10, 23, 247, 34);
		contentPane.add(label);
		
		JLabel lblSeleccioneElServicio = new JLabel("SELECCIONE \r\nEL SERVICIO");
		lblSeleccioneElServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElServicio.setForeground(new Color(30, 144, 255));
		lblSeleccioneElServicio.setFont(lblSeleccioneElServicio.getFont().deriveFont(lblSeleccioneElServicio.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblSeleccioneElServicio.setBounds(10, 103, 247, 34);
		contentPane.add(lblSeleccioneElServicio);
		
		lblNoPoseeSaldo = new JLabel("No posee Saldo");
		lblNoPoseeSaldo.setForeground(Color.RED);
		lblNoPoseeSaldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNoPoseeSaldo.setEnabled(false);
		lblNoPoseeSaldo.setBounds(68, 198, 117, 14);
		contentPane.add(lblNoPoseeSaldo);
		lblNoPoseeSaldo.setVisible(false);
		
	}
	
	public void selecCuenta( String nroCuenta ){
		Long nroCuentaSeleccionada = 0L;
		
		for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCajasDeAhorro().values()) {
			if ( cuenta.toString().equals(nroCuenta) ){
				nroCuentaSeleccionada = cuenta.getNumeroCuenta();
			}
		}
		
		if ( nroCuentaSeleccionada == 0 ){
			for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCuentasCorriente().values()) {
				if ( cuenta.toString().equals(nroCuenta) ){
					nroCuentaSeleccionada = cuenta.getNumeroCuenta();
				}
			}
		}
		cuentaSelec = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuentaSeleccionada);
		
	}
	
	public void selecServ( String nroServicio ){
	
		for (Servicio servicio : Banco.recuperarMiBanco().verCliente(dni).getListaServicios().values()) {
			if ( servicio.toString().equals(nroServicio) ){
				servicioSelec = Banco.recuperarMiBanco().verCliente(dni).getListaServicios().get(nroServicio);
			}
		}
	}

	public void pagar(){
		try {
			Banco.recuperarMiBanco().verCliente(dni).pagarServicio(servicioSelec.getTipo(), cuentaSelec.getNumeroCuenta());
		} catch (NoPoseeSaldoExcepcion e) {
			lblNoPoseeSaldo.setVisible(false);
		}
		done();
	}
	
	public void done(){
		OperacionRealizadaJFrame opRealizada = new OperacionRealizadaJFrame(this);
		opRealizada.setVisible(true);
		this.setVisible(false);
	}
	
	public void clickAtras(){
		Banco.recuperarMiBanco().guardar();
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		Banco.recuperarMiBanco().guardar();
		this.padre.cerrarSesion();
		this.dispose();
		
	}
}
