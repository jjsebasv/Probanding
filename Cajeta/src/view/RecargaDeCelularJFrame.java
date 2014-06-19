package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import banco.Banco;
import banco.Cuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecargaDeCelularJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField montoField;
	private OtrasOperacionesJFrame padre;
	private long dni;
	private Cuenta cuentaS;
	private JLabel mensaje;

	public RecargaDeCelularJFrame(long dni, OtrasOperacionesJFrame padre) {
		this.dni = dni;
		this.padre = padre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 234, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./src/imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.WHITE);
		label.setBounds(6, 12, 440, 62);
		contentPane.add(label);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button_1.setIcon(new ImageIcon("./src/imagenes/home.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(6, 234, 48, 44);
		contentPane.add(button_1);
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoConfirmar();
			}
		});
		confirmar.setBounds(167, 234, 117, 29);
		contentPane.add(confirmar);
		
		JLabel celular = new JLabel("");
		celular.setForeground(Color.RED);
		celular.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		celular.setHorizontalAlignment(SwingConstants.CENTER);
		celular.setBounds(90, 73, 260, 29);
		contentPane.add(celular);
		if ( Banco.recuperarMiBanco().verCliente(dni).getCelular()  == null ){
			celular.setText("Primero debe registrar su celular");
			confirmar.setVisible(false);
		}
		else{
			celular.setText(Banco.recuperarMiBanco().verCliente(dni).getCelular().getNumeroCelular()+" "+Banco.recuperarMiBanco().verCliente(dni).getCelular().getCompania());
		}
		
		JLabel lblIngreseMonto = new JLabel("INGRESE MONTO:");
		lblIngreseMonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseMonto.setForeground(new Color(30, 144, 255));
		lblIngreseMonto.setFont(lblIngreseMonto.getFont().deriveFont(lblIngreseMonto.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseMonto.setBounds(27, 109, 141, 34);
		contentPane.add(lblIngreseMonto);
		

		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		final String[] nombreCuentas = new String[cantCuentas];
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta).toString();
			i++;
		}
		
		
		final JComboBox comboBox = new JComboBox(nombreCuentas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCombo(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(239, 152, 171, 27);
		contentPane.add(comboBox);
		
		montoField = new JTextField();
		montoField.setBounds(27, 150, 144, 28);
		contentPane.add(montoField);
		montoField.setColumns(10);
		
		JLabel lblSeleccioneCuenta = new JLabel("SELECCIONE CUENTA:");
		lblSeleccioneCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneCuenta.setForeground(new Color(30, 144, 255));
		lblSeleccioneCuenta.setFont(lblSeleccioneCuenta.getFont().deriveFont(lblSeleccioneCuenta.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblSeleccioneCuenta.setBounds(235, 109, 175, 34);
		contentPane.add(lblSeleccioneCuenta);
		
		mensaje = new JLabel("Monto incorrecto");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setForeground(Color.RED);
		mensaje.setBounds(167, 206, 117, 16);
		contentPane.add(mensaje);
		mensaje.setVisible(false);
	}

		public void clickAtras(){
			Banco.save(Banco.recuperarMiBanco());
			this.padre.clickAtras();
			this.dispose();
		}

		public void cerrarSesion(){
			Banco.save(Banco.recuperarMiBanco());
			this.padre.cerrarSesion();
			this.dispose();
		
		}
		
		public void eventoClickCombo( String nroCuenta ){
			Long nroCuentaSeleccionada = 0L;
			
			for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCajasDeAhorro().values()) {
				if ( cuenta.toString().equals(nroCuenta) ){
					nroCuentaSeleccionada = cuenta.getNumeroCuenta();
					cuentaS = Banco.recuperarMiBanco().getListaCajasDeAhorro().get(nroCuentaSeleccionada);
				}
			}
			
			if ( nroCuentaSeleccionada == 0 ){
				for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCuentasCorriente().values()) {
					if ( cuenta.toString().equals(nroCuenta) ){
						nroCuentaSeleccionada = cuenta.getNumeroCuenta();
						cuentaS = Banco.recuperarMiBanco().getListaCuentasCorriente().get(nroCuentaSeleccionada);
					}
				}
			}
		}
		
		private static boolean isNumeric(String cadena){
			try {
				Double.parseDouble(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}
		}
		
		public void eventoConfirmar(){
			double monto = Long.valueOf(montoField.getText());
			if( isNumeric(montoField.getText() ) && Double.parseDouble(montoField.getText()) > 0 && montoField.getText() != null ){
				try {
					cuentaS.cobrarRecargaCelular(Double.valueOf(montoField.getText()));
					OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(this);
					this.setVisible(false);
					op.setVisible(true);
				} catch (Exception e) {
					mensaje.setText("No posee saldo");
					mensaje.setVisible(true);
					e.getMessage();
				}
			}
			else{
				mensaje.setText("Monto incorrecto");
				mensaje.setVisible(true);
			}
		}
	
}
