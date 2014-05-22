// ARREGLAR:
// CUANDO SE ENTRA CON LOS USUARIOS NAN Y NOWI FUNCIONA. PRIMERO LOS AGREGE Y DSP LES AGREGUE PRODUCTOS.
// CUANDO SE ENTRA CON CAR NO ME TOMA EL DOCUMENTO, ES COMO SI NO EXISTIERA. LOQUISIMO

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AutenticacionJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField dni;
	private JLabel lblNewLabel_2;
	private JButton botonAceptar;
	private JPasswordField pin;
	private JLabel datosErroneos;
	private long dniIngresado;
	private InicioJFrame padre;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public AutenticacionJFrame(InicioJFrame inicio) {
		this.padre = inicio;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{215, 176, 49, 0};
		gbl_contentPane.rowHeights = new int[]{112, 73, 38, 38, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(lblNewLabel_2.getFont().getStyle() | Font.BOLD | Font.ITALIC, lblNewLabel_2.getFont().getSize() + 9f));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setForeground(new Color(0, 191, 255));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("INGRESE DNI");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		dni = new JTextField();
		dni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dni.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_dni = new GridBagConstraints();
		gbc_dni.anchor = GridBagConstraints.NORTH;
		gbc_dni.fill = GridBagConstraints.HORIZONTAL;
		gbc_dni.insets = new Insets(0, 0, 5, 0);
		gbc_dni.gridwidth = 3;
		gbc_dni.gridx = 0;
		gbc_dni.gridy = 1;
		contentPane.add(dni, gbc_dni);
		dni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("INGRESE PIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton botonSalir = new JButton("");
		botonSalir.setHorizontalAlignment(SwingConstants.LEFT);
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCerrarSesion();
			}
		});
		
		botonAceptar = new JButton("ACEPTAR");
		botonAceptar.setForeground(new Color(30, 144, 255));
		botonAceptar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				eventoClickOperaciones();
			}
		});
		
		pin = new JPasswordField();
		GridBagConstraints gbc_pin = new GridBagConstraints();
		gbc_pin.gridwidth = 3;
		gbc_pin.insets = new Insets(0, 0, 5, 0);
		gbc_pin.fill = GridBagConstraints.HORIZONTAL;
		gbc_pin.gridx = 0;
		gbc_pin.gridy = 2;
		contentPane.add(pin, gbc_pin);
		GridBagConstraints gbc_botonAceptar = new GridBagConstraints();
		gbc_botonAceptar.fill = GridBagConstraints.BOTH;
		gbc_botonAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_botonAceptar.gridx = 0;
		gbc_botonAceptar.gridy = 3;
		contentPane.add(botonAceptar, gbc_botonAceptar);
		
		datosErroneos = new JLabel("Datos erroneos.");
		datosErroneos.setForeground(Color.RED);
		GridBagConstraints gbc_datosErroneos = new GridBagConstraints();
		gbc_datosErroneos.insets = new Insets(0, 0, 0, 5);
		gbc_datosErroneos.gridx = 1;
		gbc_datosErroneos.gridy = 3;
		contentPane.add(datosErroneos, gbc_datosErroneos);
		botonSalir.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		GridBagConstraints gbc_botonSalir = new GridBagConstraints();
		gbc_botonSalir.anchor = GridBagConstraints.WEST;
		gbc_botonSalir.fill = GridBagConstraints.VERTICAL;
		gbc_botonSalir.gridx = 2;
		gbc_botonSalir.gridy = 3;
		contentPane.add(botonSalir, gbc_botonSalir);
		datosErroneos.setVisible(false);
	}
	private static boolean isNumeric(String cadena){
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}
		}
	@SuppressWarnings("deprecation")
	public void eventoClickOperaciones(){
		Long dniIng = Long.valueOf(dni.getText());
		boolean existeDni = Banco.recuperarMiBanco().getListaUsuarios().containsKey(dniIng);
				
		System.out.println(dniIng);
		System.out.println(existeDni);
		
		
		if ( Banco.recuperarMiBanco().verCliente(dniIng) != null && existeDni && isNumeric(pin.getText())){
			int pinIng = Integer.valueOf(pin.getText());
			if ( Banco.recuperarMiBanco().verCliente(dniIng).getClavePin() == pinIng){
				OperacionJFrame operacion = new OperacionJFrame(dniIng, this.padre);
				operacion.setVisible(true);
				this.dispose();
			}
			//	else if ( dni.getText().equals("admin") && pin.getText().equals("admin")){
			//	ManagerJFrame manager = new ManagerJFrame();
			//	manager.setVisible(true);
			//}
			else{
				datosErroneos.setVisible(true);
			}
			
		}
		else{
			datosErroneos.setVisible(true);
		
	}
		
	}
	
	public void eventoClickCerrarSesion(){
		this.dispose();
		padre.enable();
	}
	
}
