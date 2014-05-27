
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
	private InicioJFrame padre;
	private static OperacionJFrame operacion = null;


	/**
	 * Create the frame.
	 */
	private OperacionJFrame(long dni, InicioJFrame inicio) {
		this.padre = inicio;
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
		btnConsultas.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultas();
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
		btnNewButton_1.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnNewButton_1.setBounds(46, 123, 178, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnTransferencias = new JButton("TRANSFERENCIAS");
		btnTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferencias();
			}
		});
		btnTransferencias.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransferencias.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnTransferencias.setBounds(46, 173, 178, 29);
		contentPane.add(btnTransferencias);
		
		JButton btnDeposito = new JButton("DEPOSITOS");
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositos();
			}
		});
		btnDeposito.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeposito.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
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
		btnExtraccion.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnExtraccion.setBounds(272, 75, 178, 29);
		contentPane.add(btnExtraccion);
		
		JButton btnOtrasOperaciones = new JButton("OTRAS OPERACIONES");
		btnOtrasOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otrasOperaciones();
			}
		});
		btnOtrasOperaciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnOtrasOperaciones.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnOtrasOperaciones.setBounds(272, 123, 178, 29);
		contentPane.add(btnOtrasOperaciones);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		btnNewButton.setBounds(396, 228, 48, 44);
		contentPane.add(btnNewButton);
		
		JButton btnClaves = new JButton("CLAVES");
		btnClaves.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnClaves.setHorizontalAlignment(SwingConstants.LEFT);
		btnClaves.setBounds(272, 173, 178, 29);
		contentPane.add(btnClaves);
	}
	
	public void depositos(){
		DepositosJFrame depositos = new DepositosJFrame(dni,this);
		depositos.setVisible(true);
		this.setVisible(false);
	}
	
	public void consultas(){
		ConsultasJFrame consultas = new ConsultasJFrame(dni, this);
		consultas.setVisible(true);
		this.setVisible(false);
	}
	
	public void transferencias(){
		TransferenciasJFrame transferencias = new TransferenciasJFrame(dni, this);
		transferencias.setVisible(true);
		this.setVisible(false);
	}
	
	public void otrasOperaciones(){
		OtrasOperacionesJFrame otrasOp = new OtrasOperacionesJFrame(dni, this);
		otrasOp.setVisible(true);
	}
	
	public void clickAtras(){
		this.setVisible(true);
	}

	public void cerrarSesion(){
		padre.setVisible(true);
		this.dispose();
	}
	
	public static OperacionJFrame recuperarOperacion(long dni, InicioJFrame inicio) {
		  if( operacion == null) {
			  operacion = new OperacionJFrame(dni, inicio);
		  }
		  operacion.setVisible(true);
		  return operacion;
	}
}
