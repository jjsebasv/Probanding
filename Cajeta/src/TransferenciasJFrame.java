
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TransferenciasJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField montoField;
	private final long dni;


	/**
	 * Create the frame.
	 */
	public TransferenciasJFrame( long dni) {
		this.dni = dni;
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
			System.exit(0);
			}
		});
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		contentPane.add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(19, 119, 240, 50);
		contentPane.add(comboBox);
		
		JLabel cuentas = new JLabel("");
		cuentas.setIcon(new ImageIcon("/Users/user/Pictures/LOGO BBV.gif"));
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
		
		JButton otroClienteBoton = new JButton("Otro Cliente BBVA");
		otroClienteBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickOtroCliente();
			}
		});
		otroClienteBoton.setBounds(294, 130, 139, 29);
		contentPane.add(otroClienteBoton);
		
		JButton otroBancoBoton = new JButton("Otro Banco");
		otroBancoBoton.setBounds(294, 171, 139, 29);
		contentPane.add(otroBancoBoton);
		
		montoField = new JTextField();
		montoField.setBounds(125, 176, 134, 28);
		contentPane.add(montoField);
		montoField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Monto:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(19, 181, 94, 19);
		contentPane.add(lblNewLabel);
	}
	
	public void eventoClickOtroCliente(){
		// pasar cuenta
		//TransfNroCuentaJFrame transf = new TransfNroCuentaJFrame(dni, Double.valueOf(montoField.getText()));
	}

}
