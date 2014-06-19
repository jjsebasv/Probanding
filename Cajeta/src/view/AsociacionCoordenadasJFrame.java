package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import banco.Banco;
import banco.TarjetaDeCoordenadas;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AsociacionCoordenadasJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numeroTarjeta;
	private long dni;
	private OtrasOperacionesJFrame padre;
	private JLabel lblNumeroIncorrecto;
	
	/**
	 * Create the frame.
	 */
	public AsociacionCoordenadasJFrame(final long dni, OtrasOperacionesJFrame padre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.dni = dni;
		this.padre = padre;
		
		numeroTarjeta = new JTextField();
		numeroTarjeta.setBounds(128, 92, 186, 40);
		contentPane.add(numeroTarjeta);
		numeroTarjeta.setColumns(10);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(10, 207, 48, 44);
		contentPane.add(home);
		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(376, 207, 48, 44);
		contentPane.add(cerrarSesion);
		
		JLabel lblIngreseNumeroDe = new JLabel("Ingrese Numero de la Tarjeta de Coordenadas");
		lblIngreseNumeroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseNumeroDe.setForeground(new Color(30, 144, 255));
		lblIngreseNumeroDe.setFont(lblIngreseNumeroDe.getFont().deriveFont(lblIngreseNumeroDe.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseNumeroDe.setBounds(68, 47, 293, 34);
		contentPane.add(lblIngreseNumeroDe);
		
		JButton btnConfirmar = new JButton("Confirmar\r\n");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirmar();					
			}
		});
		btnConfirmar.setBounds(162, 143, 117, 29);
		contentPane.add(btnConfirmar);
		
		lblNumeroIncorrecto = new JLabel("Numero Incorrecto");
		lblNumeroIncorrecto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNumeroIncorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroIncorrecto.setForeground(Color.RED);
		lblNumeroIncorrecto.setEnabled(false);
		lblNumeroIncorrecto.setBounds(128, 183, 186, 14);
		contentPane.add(lblNumeroIncorrecto);
		lblNumeroIncorrecto.setVisible(false);
	}
	
	public void done(){
		OperacionRealizadaJFrame opRealizada = new OperacionRealizadaJFrame(this);
		opRealizada.setVisible(true);
		this.setVisible(false);
	}
	
	public void clickAtras(){
		Banco.recuperarMiBanco().guardar();
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		Banco.recuperarMiBanco().guardar();
		this.padre.cerrarSesion();
		this.dispose();
		
	}
	
	public void Confirmar(){
		for (TarjetaDeCoordenadas t : Banco.recuperarMiBanco().getListaTarjetasCoord() ) {
			if( t.getNumeroTarjeta() == Long.parseLong(numeroTarjeta.getText()) && numeroTarjeta.getText() != null ){
				
				Banco.recuperarMiBanco().verCliente(dni).getTajetaDeDebito().setTarjetaCoordVinculada(t);
				done();
			}
		}
		lblNumeroIncorrecto.setVisible(true);
	}
}
