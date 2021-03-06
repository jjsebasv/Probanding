package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import banco.Cuenta;
//funciona

public class TransferenciasJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField montoField;
	private JLabel lblMontoIncorrecto;
	private JLabel lblUdNoDispone;
	private String msjDefault = "No registra mas Cuentas.";
	private Double montoS;
	private Long seleccionado;
	
	// ------------- Getters & Setters -------------->
	
	public Double getMontoS() {
		return montoS;
	}

	public void setMontoS(Double montoS) {
		this.montoS = montoS;
	}

	public Long getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Long seleccionado) {
		this.seleccionado = seleccionado;
	}

	// <-------------
	
	
	private OperacionJFrame padre;
	private long dni;
	

	/**
	 * Create the frame.
	 */
	public TransferenciasJFrame( long dni, OperacionJFrame padre) {
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
			clickAtras();
			}
		});
		button.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		contentPane.add(button);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(19, 119, 240, 50);
		contentPane.add(comboBox);
		
		for (Cuenta c : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().values() ) {
			Long aux = c.getNumeroCuenta();
			comboBox.addItem(aux);
		}
			comboBox.addItem(msjDefault);
			
		lblMontoIncorrecto = new JLabel("Monto Incorrecto");
		lblMontoIncorrecto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMontoIncorrecto.setEnabled(false);
		lblMontoIncorrecto.setForeground(Color.RED);
		lblMontoIncorrecto.setBounds(125, 215, 134, 14);
		contentPane.add(lblMontoIncorrecto);
		lblMontoIncorrecto.setVisible(false);
		
		
		JLabel cuentas = new JLabel("");
		cuentas.setIcon(new ImageIcon("./src/imagenes/LOGO BBV.gif"));
		cuentas.setHorizontalAlignment(SwingConstants.CENTER);
		cuentas.setForeground(new Color(0, 191, 255));
		cuentas.setFont(cuentas.getFont().deriveFont(cuentas.getFont().getStyle() | Font.BOLD | Font.ITALIC, cuentas.getFont().getSize() + 9f));
		cuentas.setBackground(Color.LIGHT_GRAY);
		cuentas.setBounds(6, 6, 440, 62);
		contentPane.add(cuentas);
		
		JLabel lblCuentaDeOrigen = new JLabel("CUENTA DE ORIGEN");
		lblCuentaDeOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuentaDeOrigen.setForeground(new Color(30, 144, 255));
		lblCuentaDeOrigen.setFont(lblCuentaDeOrigen.getFont().deriveFont(lblCuentaDeOrigen.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblCuentaDeOrigen.setBounds(19, 99, 237, 34);
		contentPane.add(lblCuentaDeOrigen);
		
		montoField = new JTextField();
		montoField.setBounds(125, 176, 134, 28);
		contentPane.add(montoField);
		montoField.setColumns(10);
		
			
		JButton otroClienteBoton = new JButton("Otro Cliente BBVA");
		otroClienteBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString() != msjDefault && montoField.getText() != null && isNumeric(montoField.getText())){
					lblMontoIncorrecto.setVisible(false);
					lblUdNoDispone.setVisible(false);
					setMontoS( Double.parseDouble(montoField.getText()) );
					setSeleccionado( Long.parseLong(comboBox.getSelectedItem().toString() ) ) ;
					eventoClickMismoBanco( getMontoS() ,getSeleccionado());
				}
				else{
					lblMontoIncorrecto.setVisible(true);
				}
			}
		});
		otroClienteBoton.setBounds(294, 130, 139, 29);
		contentPane.add(otroClienteBoton);
		
	
		
		JButton otroBancoBoton = new JButton("Otro Banco");
		otroBancoBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString() != msjDefault && montoField.getText() != null && isNumeric(montoField.getText())){
					lblMontoIncorrecto.setVisible(false);
					lblUdNoDispone.setVisible(false);
					setSeleccionado( Long.parseLong(comboBox.getSelectedItem().toString() ) );
					setMontoS(Double.parseDouble(montoField.getText()));
					eventoClickOtroBanco( getMontoS() ,getSeleccionado());
				}
				else{
					lblMontoIncorrecto.setVisible(true);
				}
			}
		});
		otroBancoBoton.setBounds(294, 171, 139, 29);
		contentPane.add(otroBancoBoton);
		
		
		
		JLabel lblNewLabel = new JLabel("Monto:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(19, 181, 94, 19);
		contentPane.add(lblNewLabel);
		
		lblUdNoDispone = new JLabel("Ud. No dispone de ese Dinero");
		lblUdNoDispone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUdNoDispone.setForeground(Color.RED);
		lblUdNoDispone.setEnabled(false);
		lblUdNoDispone.setBounds(125, 237, 173, 14);
		contentPane.add(lblUdNoDispone);
		lblUdNoDispone.setVisible(false);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button_1.setIcon(new ImageIcon("./src/imagenes/home.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(6, 227, 48, 44);
		contentPane.add(button_1);
		
		
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public void eventoClickOtroBanco(double monto, long miCuenta){
		if( monto < 0.0 ){
			this.lblMontoIncorrecto.setVisible(true);
		}
		else if (!(Banco.recuperarMiBanco().disponeSaldo(monto, miCuenta))) {
			this.lblUdNoDispone.setVisible(true);
		}
		else{
			Cuenta aux = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(miCuenta);
			TransfCBUJFrame trnsOtroBc = new TransfCBUJFrame(aux, monto,this);
			trnsOtroBc.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void eventoClickMismoBanco(double monto, long miCuenta){
	
		if( monto < 0.0 ){
			this.lblMontoIncorrecto.setVisible(true);
		}
		else if (!(Banco.recuperarMiBanco().disponeSaldo(monto, miCuenta)) ){
			this.lblUdNoDispone.setVisible(true);
		}
		else {
			Cuenta aux = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(miCuenta);
			TransfNroCuentaJFrame trnsMismoBc = new TransfNroCuentaJFrame(aux, monto, this);
			trnsMismoBc.setVisible(true);
			this.setVisible(false);
		}
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
}
