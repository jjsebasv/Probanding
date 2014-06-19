package view;

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

import banco.Banco;
import banco.Cuenta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
// VALIDAR QUE SEA UN NUMERO LO QUE INGRESO COMO MONTO
public class ExtraccionJFrame extends JFrame {

	private JPanel contentPane;
	private OperacionJFrame padre;
	private long dni;
	private JTextField montoField;
	private Cuenta cuentaS;
	private static JLabel lblNewLabel;
	
	public ExtraccionJFrame(long dni, OperacionJFrame padre) {
		this.dni = dni;
		this.padre = padre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		String[] nombreCuentas = new String[cantCuentas];
		
		for (Cuenta cuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().values()) {
			nombreCuentas[i] = cuenta.toString();
			i++;
		}
		
		
		final JComboBox comboBox = new JComboBox(nombreCuentas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCombo(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(6, 117, 240, 50);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(396, 228, 48, 44);
		contentPane.add(button_1);
		button_1.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(0, 191, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, label_1.getFont().getSize() + 9f));
		label_1.setBackground(Color.LIGHT_GRAY);
		label_1.setBounds(6, 6, 440, 62);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("SELECCIONE \nLA CUENTA");
		label_2.setForeground(new Color(30, 144, 255));
		label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_2.setBounds(22, 93, 200, 34);
		contentPane.add(label_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(6, 228, 48, 44);
		contentPane.add(button);
		button.setIcon(new ImageIcon("./imagenes/home.png"));
		
		montoField = new JTextField();
		montoField.setBounds(16, 166, 222, 28);
		contentPane.add(montoField);
		montoField.setColumns(10);
		
		lblNewLabel = new JLabel("Monto Incorrecto");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(84, 219, 154, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoConfirmar();
			}
		});
		btnConfirmar.setBounds(298, 128, 117, 29);
		contentPane.add(btnConfirmar);
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
				cuentaS.extraccion(monto);
				OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(this);
				this.setVisible(false);
				op.setVisible(true);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		else{
			lblNewLabel.setVisible(true);
		}
	}
}
