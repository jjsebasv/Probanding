package view;
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

import exception.NoExisteLaCuentaExcepcion;
import exception.NoPoseeSaldoExcepcion;
import banco.Banco;
import banco.Cuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// FUNCIONA! 
//Banco distinto -->> no validar

public class TransfCBUJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField destino;
	private double monto;
	private Cuenta cuenta;
	private JLabel lblCbuNoAdecuado;
	private TransferenciasJFrame padre;


	/**
	 * Create the frame.
	 */
	public TransfCBUJFrame(Cuenta cuenta, double monto, TransferenciasJFrame padre) {
		this.padre = padre;
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
				clickAtras();
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
		
		final JLabel lblCbuNoAdecuado = new JLabel("CBU no adecuado");
		lblCbuNoAdecuado.setForeground(Color.RED);
		lblCbuNoAdecuado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCbuNoAdecuado.setEnabled(false);
		lblCbuNoAdecuado.setBounds(255, 180, 134, 14);
		panel.add(lblCbuNoAdecuado);
		lblCbuNoAdecuado.setVisible(false);
		
		destino = new JTextField();
		destino.setColumns(10);
		destino.setBounds(255, 110, 134, 28);
		panel.add(destino);
		
		JLabel label_2 = new JLabel("Monto: " + getMonto());
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		label_2.setBounds(255, 150, 125, 16);
		panel.add(label_2);
		
		JButton button_1 = new JButton("Confirmar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// --- Estoy seguro de que tiene saldo, lo valida el JFrame anterior
				if( destino.getText()!= null && isNumeric( destino.getText() ) ){
						setMonto(Double.parseDouble(destino.getText().toString()));
						try {
							Banco.recuperarMiBanco().transferenciaPorCbu(getMonto(), getCuenta(), Long.parseLong(destino.getText()));
						} catch (NoPoseeSaldoExcepcion e1) {
							// TODO Auto-generated catch block
							// No va a entrar aca, el JFrame anterior chequea que tenga saldo
							e1.printStackTrace();
						}
						done();
				}
				else{
					lblCbuNoAdecuado.setVisible(true);
				}
			}
		});
		button_1.setBounds(66, 210, 117, 29);
		panel.add(button_1);
				
	}
	
	
	public void done(){
		OperacionRealizadaJFrame opRealizada = new OperacionRealizadaJFrame(this);
		opRealizada.setVisible(true);
		this.setVisible(false);
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
	
	public void clickAtras(){
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		this.padre.cerrarSesion();
		this.dispose();
		
	}
}
