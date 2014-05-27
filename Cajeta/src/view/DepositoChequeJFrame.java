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

import exception.NoPoseeSaldoExcepcion;
import exception.NoSePuedeDepositarChequeExcepcion;
import banco.Banco;
import banco.Cheque;
import banco.Cuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// AGREGAR EL MSJ DE ERROR, EL MSJ DE LA EXCEPCION
public class DepositoChequeJFrame extends JFrame {

	private JPanel contentPane;
	private long dni;
	private double monto;
	private Cuenta cuenta;
	private JTextField nroChequeString;
	private DepositosJFrame padre;
	private JLabel lblNewLabel;
	private JTextField nroCuentatext;
	

	public DepositoChequeJFrame(long dni, double monto, Cuenta cuenta, DepositosJFrame padre) {
		this.dni = dni;
		this.monto = monto;
		this.padre = padre;
		this.cuenta = cuenta;
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
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel lblIngreseNroDe = new JLabel("Ingrese Nro de Cheque:");
		lblIngreseNroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseNroDe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIngreseNroDe.setForeground(new Color(30, 144, 255));
		lblIngreseNroDe.setFont(lblIngreseNroDe.getFont().deriveFont(lblIngreseNroDe.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseNroDe.setBounds(16, 94, 170, 34);
		contentPane.add(lblIngreseNroDe);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button_1.setIcon(new ImageIcon("./imagenes/home.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(6, 228, 48, 44);
		contentPane.add(button_1);
		
		nroChequeString = new JTextField();
		nroChequeString.setBounds(16, 134, 170, 28);
		contentPane.add(nroChequeString);
		nroChequeString.setColumns(10);
		
		lblNewLabel = new JLabel("No se puede depositar Cheque");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(125, 210, 214, 16);
		contentPane.add(lblNewLabel);
		
		final JButton confirmarBoton = new JButton("CONFIRMAR");
		confirmarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		confirmarBoton.setBounds(175, 174, 117, 29);
		contentPane.add(confirmarBoton);
		
		JLabel lblIngreseNroDe_1 = new JLabel("Ingrese Nro de Cta. Emisora:");
		lblIngreseNroDe_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIngreseNroDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseNroDe_1.setForeground(new Color(30, 144, 255));
		lblIngreseNroDe_1.setFont(lblIngreseNroDe_1.getFont().deriveFont(lblIngreseNroDe_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseNroDe_1.setBounds(211, 94, 200, 34);
		contentPane.add(lblIngreseNroDe_1);
		
		nroCuentatext = new JTextField();
		nroCuentatext.setColumns(10);
		nroCuentatext.setBounds(211, 134, 200, 28);
		contentPane.add(nroCuentatext);
		lblNewLabel.setVisible(false);
	}
	
	public void confirmar(){
		try {
			long nroC = Long.valueOf(nroChequeString.getText());
			Cheque cheque = Banco.recuperarMiBanco().getListaCuentasCorriente().get(Long.valueOf(nroCuentatext.getText())).getChequesEmitidos().get(nroC);
			System.out.println("SALDO DESTINO ANTES DEPOSITO"+cuenta.getSaldoActual());
			cuenta.depositar(cheque);
			cheque.getEmisora().cobrarCheque(cheque);
			System.out.println("SALDO DESTINO DSP DEPOSITO "+cuenta.getSaldoActual());
			OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(dni, this);
			op.setVisible(true);
			this.setVisible(false);
		} catch (NoSePuedeDepositarChequeExcepcion e) {
			lblNewLabel.setVisible(true);
		} catch ( NoPoseeSaldoExcepcion e){
			lblNewLabel.setText("No posee saldo");
			lblNewLabel.setVisible(true);
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
