
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;


public class OperacionJFrame extends JFrame {

	private JPanel contentPane;
	private final long dni;


	/**
	 * Create the frame.
	 */
	public OperacionJFrame(long dni) {
		this.dni = dni;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElTipo = new JLabel("SELECCIONE EL TIPO DE OPERACION");
		lblSeleccioneElTipo.setBackground(new Color(255, 255, 255));
		lblSeleccioneElTipo.setBounds(5, 5, 430, 48);
		lblSeleccioneElTipo.setIcon(null);
		lblSeleccioneElTipo.setForeground(new Color(0, 191, 255));
		lblSeleccioneElTipo.setFont(lblSeleccioneElTipo.getFont().deriveFont(lblSeleccioneElTipo.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblSeleccioneElTipo.getFont().getSize() + 5f));
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSeleccioneElTipo);
		
		JButton btnConsultas = new JButton("CONSULTAS");
		btnConsultas.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultas.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickConsultas();
			}
		});
		btnConsultas.setBounds(46, 75, 178, 29);
		contentPane.add(btnConsultas);
		
		JButton btnNewButton_1 = new JButton("PAGO DE SERVICIOS");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PagoDeServicioJFrame pagoDeServicios = new PagoDeServicioJFrame();
				pagoDeServicios.setVisible(true);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnNewButton_1.setBounds(46, 123, 178, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnTransferencias = new JButton("TRANSFERENCIAS");
		btnTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickTransferencias();
			}
		});
		btnTransferencias.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransferencias.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnTransferencias.setBounds(46, 173, 178, 29);
		contentPane.add(btnTransferencias);
		
		JButton btnDeposito = new JButton("DEPOSITOS");
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositosJFrame depositos = new DepositosJFrame();
				depositos.setVisible(true);
			}
		});
		btnDeposito.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeposito.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnDeposito.setBounds(46, 223, 178, 29);
		contentPane.add(btnDeposito);
		
		JButton btnExtraccion = new JButton("EXTRACCION");
		btnExtraccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExtraccionesJFrame extracciones = new ExtraccionesJFrame();
				extracciones.setVisible(true);
			}
		});
		btnExtraccion.setHorizontalAlignment(SwingConstants.LEFT);
		btnExtraccion.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnExtraccion.setBounds(272, 75, 178, 29);
		contentPane.add(btnExtraccion);
		
		JButton btnOtrasOperaciones = new JButton("OTRAS OPERACIONES");
		btnOtrasOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtrasOperacionesJFrame otrasOp = new OtrasOperacionesJFrame();
				otrasOp.setVisible(true);
			}
		});
		btnOtrasOperaciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnOtrasOperaciones.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnOtrasOperaciones.setBounds(272, 123, 178, 29);
		contentPane.add(btnOtrasOperaciones);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		btnNewButton.setBounds(396, 228, 48, 44);
		contentPane.add(btnNewButton);
		
		JButton btnClaves = new JButton("CLAVES");
		btnClaves.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnClaves.setHorizontalAlignment(SwingConstants.LEFT);
		btnClaves.setBounds(272, 173, 178, 29);
		contentPane.add(btnClaves);
	}
	
	private void eventoClickConsultas(){
		ConsultasJFrame consultas = new ConsultasJFrame(dni);
		consultas.setVisible(true);
	}
	
	public void eventoClickTransferencias(){
		TransferenciasJFrame transferencias = new TransferenciasJFrame(dni);
		transferencias.setVisible(true);
	}
}