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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// mismo banco --->> Validar!

public class TransfNroCuentaJFrame extends JFrame {

	private JPanel contentPane;
	private final double monto;
	private final Cuenta cuenta;
	private JTextField destino;

	/**
	 * Create the frame.
	 */
	public TransfNroCuentaJFrame(Cuenta miCuenta, double monto) {
		this.cuenta = miCuenta;
		this.monto = monto;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
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
		
		destino = new JTextField();
		destino.setBounds(255, 110, 134, 28);
		contentPane.add(destino);
		destino.setColumns(10);
		
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
		cuentaLabel.setText(miCuenta.toString());
		
		JButton confirmarBoton = new JButton("Confirmar");
		confirmarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( destino.getText()!= null && isNumeric( destino.getText() ) ) {
					if( Banco.recuperarMiBanco().getListaCuentasCorriente().containsKey(Long.valueOf(destino.getText() ) ) ) {
		
						Banco.recuperarMiBanco().transferencia(getMonto(), getCuenta(), Banco.recuperarMiBanco().getListaCuentasCorriente().get(Long.valueOf(destino.getText() ) ));
					}
					if( Banco.recuperarMiBanco().getListaCajasDeAhorro().containsKey(Long.valueOf(destino.getText() ) ) ) {
						Banco.recuperarMiBanco().transferencia(getMonto(), getCuenta(), Banco.recuperarMiBanco().getListaCajasDeAhorro().get(Long.valueOf(destino.getText() ) ));
					}
					
				}
			}
		});
		confirmarBoton.setBounds(66, 210, 117, 29);
		contentPane.add(confirmarBoton);
		
		
		
		
	}

	public double getMonto() {
		return monto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
}
