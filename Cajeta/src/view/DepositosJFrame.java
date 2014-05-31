package view;
// VALIDAR CANT DINERO INGRESADO > 0 --> CHECKED!
// HACER OTROS FRAMES
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import banco.Banco;
import banco.Cuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DepositosJFrame extends JFrame {

	private JPanel contentPane;
	private long dni;
	private OperacionJFrame padre;
	private JTextField monto;
	private Cuenta cuentaSelec;

	public Cuenta getCuentaSelec() {
		return cuentaSelec;
	}

	public void setCuentaSelec(Cuenta cuentaSelec) {
		this.cuentaSelec = cuentaSelec;
	}

	/**
	 * Create the frame.
	 */
	public DepositosJFrame(final long dni, OperacionJFrame consultas) {
		this.dni = dni;
		this.padre = consultas;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(396, 228, 48, 44);
		contentPane.add(cerrarSesion);
		cerrarSesion.setIcon(new ImageIcon("./imagenes/shut-down.png"));

		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		final Cuenta[] nombreCuentas = new Cuenta[cantCuentas];
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta);
			i++;
		}
		
		
		final JComboBox cuentas = new JComboBox(nombreCuentas);
		cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCombo(cuentas.getSelectedItem().toString());
			}
		});
		cuentas.setBounds(6, 117, 240, 50);
		contentPane.add(cuentas);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("SELECCIONE \nLA CUENTA");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(6, 86, 240, 34);
		contentPane.add(label_1);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setIcon(new ImageIcon("./imagenes/home.png"));
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		
		monto = new JTextField();
		monto.setBounds(26, 188, 203, 28);
		contentPane.add(monto);
		monto.setColumns(10);
		
		final JLabel lblMontoNoAdecuado = new JLabel("Monto no adecuado");
		lblMontoNoAdecuado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMontoNoAdecuado.setForeground(Color.RED);
		lblMontoNoAdecuado.setEnabled(false);
		lblMontoNoAdecuado.setBounds(64, 228, 117, 14);
		contentPane.add(lblMontoNoAdecuado);
		lblMontoNoAdecuado.setVisible(false);
		
		JButton btnNewButton = new JButton("Efectivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( isNumeric(monto.getText() ) && Double.parseDouble(monto.getText()) > 0 && monto.getText() != null ){
					setCuentaSelec( Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get( (nombreCuentas[cuentas.getSelectedIndex()]).getNumeroCuenta() ));
					eventoEfectivo(Double.parseDouble(monto.getText()));
				}
				else
					lblMontoNoAdecuado.setVisible(true);					
			}
		});
		btnNewButton.setBounds(284, 128, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cheque");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoCheque();
			}
		});
		btnNewButton_1.setBounds(284, 187, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Ingrese \nmonto:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(16, 162, 213, 22);
		contentPane.add(lblNewLabel);
			
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
	
	public void eventoEfectivo(Double monto){
		OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(this);
		System.out.println(Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get( getCuentaSelec().getNumeroCuenta() ).getSaldoActual() );
		Banco.recuperarMiBanco().deposito(getCuentaSelec(), monto);
		System.out.println(Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get( getCuentaSelec().getNumeroCuenta() ).getSaldoActual() );
		op.setVisible(true);
		this.setVisible(false);
	}

	private static boolean isNumeric(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public void eventoClickCombo( String nroCuenta ){
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
		System.out.println("NRO DE CUENTA SELECCIONADA (ENDPJFRAME)" + nroCuentaSeleccionada);
		cuentaSelec = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuentaSeleccionada);
	}
	
	public void eventoCheque(){
		System.out.println("CUENTA SELECCIONADA: ( EN DEPOJFRAME) "+ getCuentaSelec());
		DepositoChequeJFrame depositos = new DepositoChequeJFrame(dni, Double.valueOf(monto.getText()), cuentaSelec, this);
		depositos.setVisible(true);
		this.setVisible(false);
		
	}
}
