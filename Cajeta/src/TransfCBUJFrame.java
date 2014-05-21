import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//Banco distinto -->> no validar

public class TransfCBUJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField destino;
	private double monto;
	private Cuenta cuenta;


	/**
	 * Create the frame.
	 */
	public TransfCBUJFrame(Cuenta cuenta, double monto) {
		this.monto = monto;
		this.cuenta = cuenta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 278);
		contentPane.add(panel);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("shut-down.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		panel.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		panel.add(label);
		
		JLabel lblNumeroDeCbu = new JLabel("Numero de CBU Destino");
		lblNumeroDeCbu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroDeCbu.setForeground(new Color(30, 144, 255));
		lblNumeroDeCbu.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNumeroDeCbu.setBounds(38, 116, 213, 16);
		panel.add(lblNumeroDeCbu);
		
		destino = new JTextField();
		destino.setColumns(10);
		destino.setBounds(255, 110, 134, 28);
		panel.add(destino);
		
		JLabel label_2 = new JLabel("Monto: 0.0");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		label_2.setBounds(255, 150, 125, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel((String) null);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		label_3.setBounds(255, 178, 125, 16);
		panel.add(label_3);
		
		JButton button_1 = new JButton("Confirmar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( destino.getText()!= null && isNumeric( destino.getText() ) ){
					Banco.recuperarMiBanco().transferencia(getMonto(), getCuenta(), Long.valueOf(destino.getText()));
				}
				// mostrar mensaje de mal CBU
			}
		});
		button_1.setBounds(66, 210, 117, 29);
		panel.add(button_1);
	}
	
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
