import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

// mismo banco --->> Validar!

public class TransfNroCuentaJFrame extends JFrame {

	private JPanel contentPane;
	private final double monto;
	private final Cuenta cuenta;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public TransfNroCuentaJFrame(Cuenta cuenta, double monto) {
		this.cuenta = cuenta;
		this.monto = monto;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/user/Pictures/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Numero de Cuenta Destino");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(38, 116, 213, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(255, 110, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel montoLabel = new JLabel("");
		montoLabel.setForeground(new Color(255, 0, 0));
		montoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		montoLabel.setBounds(255, 150, 125, 16);
		contentPane.add(montoLabel);
		montoLabel.setText("Monto: "+monto);
		
		JLabel cuentaLabel = new JLabel("New label");
		cuentaLabel.setForeground(new Color(255, 0, 0));
		cuentaLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		cuentaLabel.setBounds(255, 178, 125, 16);
		contentPane.add(cuentaLabel);
		cuentaLabel.setText(cuenta.toString());
		
		JButton confirmarBoton = new JButton("Confirmar");
		confirmarBoton.setBounds(66, 210, 117, 29);
		contentPane.add(confirmarBoton);
	}

}
